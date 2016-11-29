package com.thinkive.mall.business;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.log4j.Logger;

import com.project.utils.HttpClientUtil;
import com.project.utils.JSONUtil;
import com.thinkive.base.config.Configuration;
import com.thinkive.base.jdbc.DataRow;
import com.thinkive.mall.service.OrderService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;

/**
 * 描述: 修改非金融订单状态-审核通过，进行退款
 * 版权: Copyright (c) 2013
 * 公司: 思迪科技 
 * 作者: 黄圣宙
 * 版本: 1.0 
 * 创建日期: May 8, 2014 
 * 创建时间: 2:16:50 PM
 */
public class Function9021221 extends BaseFunction
{
	private  OrderService orderService = new OrderService();
	
	private static Logger logger = Logger.getLogger(Function9021221.class);
	
	//银联后台退款
	private final static String PAY_BACK_UPOP_URL = Configuration.getString("mall_upop.pay_back_upop_url") ;
	
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo execute() throws InvokeException, Exception
	{
		ResultVo result = new ResultVo();
		String orderId = getStrParameter("order_id");
		String product_sub_type = getStrParameter("product_sub_type");
		String ip = this.getStrParameter("ip");
		List<DataRow> orderList = orderService.queryOrderQid(orderId) ;
		for (DataRow dataRow : orderList)
		{
			String orderQid = dataRow.getString("three_sno") ;
			String totalAmount = dataRow.getString("order_tot_price") ;
			int totalAmountInt =  (int)(Float.parseFloat(totalAmount) * 100) ;
			Map<String, Object> map = new HashMap<String, Object>() ;
			String orderNumber = System.currentTimeMillis() + orderId ;
			String orderTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) ;
			map.put("orderTime", orderTime) ;
			map.put("orderNumber", orderNumber) ;
			map.put("qid", orderQid) ;
			map.put("totalAmount", totalAmountInt) ;
			map.put("ip", ip) ;
			String res = HttpClientUtil.httpClientGet(PAY_BACK_UPOP_URL, map) ;
			Map<String, Object> resMap = JSONUtil.getMap4Json(res) ;
			if(resMap.containsKey("errorno") && "0".equals(resMap.get("errorno"))){
				logger.info("银联后台退款操作成功，订单号为：" + orderId + ",银联返回信息：" + resMap.get("errormsg")) ;
			}else{
				String error_msg = "银联后台退款操作失败，订单号为：" + orderId + ",银联返回信息：" + resMap.get("errormsg") ;
				String error_code = (resMap.get("errorno") == null ? "-1" : ObjectUtils.toString(resMap.get("errorno")));
				DataRow data = new DataRow() ;
				data.put("order_id", orderId) ;
				data.put("error_code", error_code) ;
				data.put("error_msg", error_msg) ;
				data.put("product_type", product_sub_type) ;//产品子类别
				data.put("create_time",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())) ;
				orderService.insertOrderError(data) ; //退款失败信息入库
				logger.info(error_msg) ;
			}
		}
		return result;
	}
}

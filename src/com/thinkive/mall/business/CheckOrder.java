package com.thinkive.mall.business;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.project.utils.HttpClientUtil;
import com.project.utils.JSONUtil;
import com.thinkive.base.config.Configuration;
import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.util.StringHelper;
import com.thinkive.mall.service.OrderService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;

/**
 * 描述: 订单定时对帐
 * 版权: Copyright (c) 2013
 * 公司: 思迪科技 
 * 作者: 黄圣宙
 * 版本: 1.0 
 * 创建日期: May 8, 2014 
 * 创建时间: 2:16:50 PM
 */
public class CheckOrder extends BaseFunction
{
	private  OrderService orderService = new OrderService();
	
	private static Logger logger = Logger.getLogger(CheckOrder.class);
	//银联对账查询
	private final static String QUERY_UPOP_URL = Configuration.getString("mall_upop.query_pay_url") ;
	
	private final static String ORDER_STATE_SUCCESS = "1" ;
	
	@Override
	public ResultVo execute() throws InvokeException, Exception
	{
		ResultVo result = new ResultVo();
		
		//查询所有新建和失败的订单下的支付流水
		List<DataRow> orderList = orderService.queryAllUnsuccessOrders();
		
		Set<String> hasModified = new HashSet<String>();
		//遍历所有支付流水，对比财付通流水状态与本地支付流水状态，如果有不同，则更新为与财会通一致
		try{
			for(DataRow dr: orderList){
				String order_id = dr.getString("order_id");
				if(hasModified.contains(order_id)){
					continue;
				}
				if(!StringHelper.isEmpty(order_id)){
					String mall_sno = dr.getString("mall_sno") ;
					String three_sno = dr.getString("three_sno");
					String order_state = dr.getString("order_state");
					
					Map<String, Object> map = new HashMap<String, Object>() ;
					map.put("mall_sno", mall_sno) ; 
					map.put("three_sno", three_sno) ; 
					String res = HttpClientUtil.httpClientGet(QUERY_UPOP_URL, map) ;
					Map<String, Object> resMap = JSONUtil.getMap4Json(res) ;
					String pay_status = resMap.get("pay_status") + "";  //支付结果状态码,0表示成功,其它为失败
					
					//判断本地订单号状态与返回值状态
					
					if(pay_status.equals("0")){  //成功

						if(!StringHelper.isEmpty(order_state) && !order_state.equals(ORDER_STATE_SUCCESS)){  //此流水没成交的话就改为成效，状态：4
							orderService.updateOrderState2Ready(order_id, ORDER_STATE_SUCCESS);
						}
						hasModified.add(order_id);
					}
				}
				
			}
		}catch(Exception e){
			result.setErrorMsg(e.getMessage());
			result.setErrorNo(-902905001);
			logger.info(e.getMessage());
		}
		
		return result;
	}
}

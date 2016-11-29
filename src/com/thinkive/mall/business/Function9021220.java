package com.thinkive.mall.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * ����: �޸ķǽ��ڶ���״̬-����
 * ��Ȩ: Copyright (c) 2013
 * ��˾: ˼�ϿƼ� 
 * ����: ��ʥ��
 * �汾: 1.0 
 * ��������: May 8, 2014 
 * ����ʱ��: 2:16:50 PM
 */
public class Function9021220 extends BaseFunction
{
	private  OrderService orderService = new OrderService();
	
	private static Logger logger = Logger.getLogger(Function9021220.class);
	//�������˲�ѯ
	private final static String QUERY_UPOP_URL = Configuration.getString("mall_upop.query_pay_url") ;
	
	private final static String ORDER_STATE_SUCCESS = "4" ;
	
	@Override
	public ResultVo execute() throws InvokeException, Exception
	{
		ResultVo result = new ResultVo();
		
		String orderId = getStrParameter("order_id");
		
		List<DataRow> orderList = orderService.queryOrderSno(orderId) ;
		for (DataRow dataRow : orderList)
		{
			String mall_sno = dataRow.getString("mall_sno") ;
			String three_sno = dataRow.getString("three_sno");
			String order_state = dataRow.getString("order_state");
			if(!StringHelper.isEmpty(order_state) && order_state.equals("0")){
				Map<String, Object> map = new HashMap<String, Object>() ;
				map.put("mall_sno", mall_sno) ; 
				map.put("three_sno", three_sno) ; 
				String res = HttpClientUtil.httpClientGet(QUERY_UPOP_URL, map) ;
				Map<String, Object> resMap = JSONUtil.getMap4Json(res) ;
				if(resMap.containsKey("errorno") && "0".equals(resMap.get("errorno"))){
					orderService.updateOrderState(orderId, ORDER_STATE_SUCCESS) ;
					logger.info("�����ɹ���������Ϊ��" + orderId) ;
					break ;
				}
			}
			
		}
		return result;
	}
}

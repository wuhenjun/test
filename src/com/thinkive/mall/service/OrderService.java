package com.thinkive.mall.service;

import java.util.Date;
import java.util.List;

import com.project.utils.Constants;
import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.jdbc.JdbcTemplate;
import com.thinkive.base.service.BaseService;
import com.thinkive.base.util.DateHelper;

/**"web"
 * 描述: 
 * 版权: Copyright (c) 2013
 * 公司: 思迪科技 
 * 作者: 黄圣宙
 * 版本: 1.0 
 * 创建日期: May 8, 2014 
 * 创建时间: 2:19:08 PM
 */
public class OrderService extends BaseService
{
	
	private JdbcTemplate getJdbcTemplate()
	{
		return getJdbcTemplate(Constants.DB_ID);
	}
	
	@SuppressWarnings("unchecked")
	public List<DataRow> queryOrderSno(String orderId)
	{
		String sql = "select  tms.order_id,tms.three_sno,tms.mall_sno,(select o.order_state from t_mall_order o where o.order_id=tms.order_id) order_state  from T_MALL_SNO tms where tms.order_id =? order by tms.order_id, tms.create_time desc ";
		List<DataRow> list = this.getJdbcTemplate().query(sql, new Object[] { orderId });
		return list;
	}
	
	public void updateOrderState(String orderId, String orderStateSuccess)
	{
		String sql = "update t_mall_order_not_financial set order_state=?, update_time=to_char(sysdate, 'yyyy-MM-dd HH24:mi:ss'), state_message='补单成功' where order_id = ?";
		this.getJdbcTemplate().update(sql, new Object[] { orderStateSuccess, orderId });
	}
	
	public void updateOrderState2Ready(String orderId, String orderStateSuccess)
	{
		String sql = "update t_mall_order_not_financial set order_state=?, update_time=to_char(sysdate, 'yyyy-MM-dd HH24:mi:ss'), state_message='新建状态更新为等提交状态' where order_id = ?";
		this.getJdbcTemplate().update(sql, new Object[] { orderStateSuccess, orderId });
	}
	
	@SuppressWarnings("unchecked")
	public List<DataRow> queryOrderQid(String orderId)
	{
		String sql = "select tms.*,o.order_tot_price from T_MALL_SNO tms left join t_mall_order_not_financial o on o.order_id=tms.order_id where tms.order_id =? and trim(three_sno)<>' ' and three_sno is not null order by tms.order_id, tms.create_time desc ";
		List<DataRow> list = this.getJdbcTemplate().query(sql, new Object[] { orderId });
		return list;
	}

	@SuppressWarnings("unchecked")
	public void insertOrderError(DataRow dataRow)
	{
		if(dataRow!=null){
			dataRow.put("error_id", getSeqValue(Constants.DB_ID,"T_MALL_ORDER_ERROR")) ;
		}
		this.getJdbcTemplate().insert("T_MALL_ORDER_ERROR", dataRow) ;
	}
	
	public List queryAllUnsuccessOrders(){   //只查24小时内的新建：0和失败：3的
		/*String date = DateHelper.formatDate(new Date(), "yyyyMMddHHmmss");
		long d = Long.parseLong(date) - 1020000;*/
		//String sql = "select tms.order_id,tms.three_sno,tms.mall_sno,(select o.order_state from t_mall_order o where o.order_id=tms.order_id) order_state from T_MALL_SNO tms where tms.order_id in (select order_id from t_mall_order_not_financial o where o.order_state='0' or o.order_state='3') and floor(sysdate - to_date(tms.create_time,'yyyymmddHH24miss')) <= 1";
		String sql = "select tms.order_id,tms.three_sno,tms.mall_sno,(select o.order_state from t_mall_order o where o.order_id=tms.order_id) order_state from T_MALL_SNO tms where tms.order_id in (select order_id from t_mall_order_not_financial o where o.order_state='0') and floor(sysdate - to_date(tms.create_time,'yyyymmddHH24miss')) <= 1";
		List<DataRow> list = this.getJdbcTemplate().query(sql);
		return list;
	}
	
}

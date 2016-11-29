package com.thinkive.mall.service;


import com.project.utils.Constants;
import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.jdbc.JdbcTemplate;
import com.thinkive.base.jdbc.session.Session;
import com.thinkive.base.service.BaseService;
import com.thinkive.server.InvokeException;

public class ProductFundManagerService extends BaseService
{
	
	private JdbcTemplate getJdbcTemplate()
	{
		return getJdbcTemplate(Constants.DB_ID);
	}
	
	
	/**
	 * 
	 * 描述：修改基金经理信息
	 */
	public void upateProductFundManager(DataRow dataRow) throws InvokeException,Exception
	{
		int fund_manager_id=Integer.parseInt(dataRow.getString("fund_manager_id"));
		getJdbcTemplate().update("T_MALL_PRODUCT_FUND_MANAGER", dataRow, "fund_manager_id" , fund_manager_id);
	}
	
	/**
	 * 
	 * 描述：修改基金经理信息
	 */
	public void insertProductFundManager(DataRow dataRow) throws InvokeException,Exception
	{
		String id = this.getSeqValue("T_MALL_PRODUCT_FUND_MANAGER");
		dataRow.set("fund_manager_id", id);
		dataRow.set("id", id);
		getJdbcTemplate().insert("T_MALL_PRODUCT_FUND_MANAGER", dataRow);
	}
}

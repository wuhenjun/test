package com.thinkive.mall.service;

import java.util.ArrayList;
import java.util.List;

import com.project.utils.Constants;
import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.jdbc.JdbcTemplate;
import com.thinkive.base.jdbc.session.Session;
import com.thinkive.base.service.BaseService;
import com.thinkive.server.InvokeException;

/**
 * 
 * ����: ��Ʒ������
 * ��Ȩ: Copyright (c) 2014
 * ��˾: ˼�ϿƼ� 
 * ����: ����
 * �汾: 1.0 
 * ��������: 2014-5-7 
 * ����ʱ��: ����01:15:01
 */

public class ProductService extends BaseService
{
	private JdbcTemplate getJdbcTemplate()
	{
		return getJdbcTemplate(Constants.DB_ID);
	}
	
	
	public DataRow queryNotFundProductById(String id)
	{
		List<String> argList = new ArrayList<String>();
		StringBuffer sql = new StringBuffer("SELECT * FROM t_mall_product_not_financial WHERE PRODUCT_ID = ?");
		argList.add(id);
		return this.getJdbcTemplate().queryMap(sql.toString(), argList.toArray());
	}
	
	public void upateFundProduct(DataRow product)  throws InvokeException, Exception
	{
		Session session = this.getSession(Constants.DB_ID);
		try
		{
			session.beginTrans();
			
			DataRow productFinancial = new DataRow();
			productFinancial.set("product_id", product.getString("product_id"));
			productFinancial.set("product_shelf", product.getString("product_shelf"));
			session.update("T_MALL_PRODUCT_FINANCIAL", productFinancial, new String[] { "product_id" }, new Object[] { productFinancial.getString("product_id") });
			
			DataRow productFund = new DataRow();
			productFund.set("product_id", product.getString("product_id"));
			productFund.set("pinyin", product.getString("pinyin"));
			session.update("T_MALL_PRODUCT_FUND", productFund, new String[] { "product_id" }, new Object[] { productFund.getString("product_id") });
			
			session.commitTrans();
		}
		catch (Exception ex)
		{
			session.rollbackTrans();
			ex.printStackTrace();
			throw new InvokeException(-10, "������Ϣ����ʧ��" + ex.getMessage());
		}
		finally
		{
			if (session != null)
			{
				session.close();
				session = null;
			}
		}
		
	}
	
	
	public DataRow queryInforProductById(String id)  throws InvokeException, Exception
	{
		List<String> argList = new ArrayList<String>();
		StringBuffer sql = new StringBuffer("SELECT * FROM V_MALL_PRODUCT_INFOR WHERE PRODUCT_ID = ?");
		argList.add(id);
		return this.getJdbcTemplate().queryMap(sql.toString(), argList.toArray());
	}
	
	public void upateInforProduct(DataRow product)  throws InvokeException, Exception
	{
		Session session = this.getSession(Constants.DB_ID);
		try
		{
			session.beginTrans();
			
			session.commitTrans();
		}
		catch (Exception ex)
		{
			session.rollbackTrans();
			ex.printStackTrace();
			throw new InvokeException(-10, "��Ѷ��Ϣ����ʧ��" + ex.getMessage());
		}
		finally
		{
			if (session != null)
			{
				session.close();
				session = null;
			}
		}
		
	}
	
	
	
	public void update(DataRow dataRow)
	{
		getJdbcTemplate().update("", dataRow, "id", dataRow.getString("id"));
	}
	
	public void insert(DataRow dataRow)
	{
		getJdbcTemplate().insert("", dataRow);
	}
	
	
	public void deleteProdService(String product_id, String service_type) 
	{
		List<String> argList = new ArrayList<String>();
		StringBuffer sql = new StringBuffer("DELETE T_MALL_PRODUCT_SERVICE WHERE product_id = ? and service_type = ?");
		argList.add(product_id);
		argList.add(service_type);
		getJdbcTemplate().update(sql.toString(), argList.toArray());
	}
	
	public int queryCountsByPrimaryKey(String product_id, String service_type)
	{
		List<String> argList = new ArrayList<String>();
		StringBuffer sql = new StringBuffer("SELECT count(*) FROM T_MALL_PRODUCT_SERVICE WHERE PRODUCT_ID = ? and service_type = ?");
		argList.add(product_id);
		argList.add(service_type);
		return getJdbcTemplate().queryInt(sql.toString(), argList.toArray());
	}
	
	public void addProductService(DataRow dataRow)
	{
		getJdbcTemplate().insert("T_MALL_PRODUCT_SERVICE", dataRow);
	}
}

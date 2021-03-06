package com.thinkive.mall.service;


import com.project.utils.Constants;

import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.jdbc.JdbcTemplate;
import com.thinkive.base.jdbc.session.Session;
import com.thinkive.base.service.BaseService;
import com.thinkive.base.service.SequenceGenerator;
import com.thinkive.server.InvokeException;

public class ProductManagerService extends BaseService
{
	
	private JdbcTemplate getJdbcTemplate()
	{
		return getJdbcTemplate(Constants.DB_ID);
	}
	
	/**
	 * 
	 * �����������û���������ӦͼƬ��Ϣ
	 */
	public void addProductManager(DataRow proManInfo, DataRow imgInfo) throws InvokeException,Exception
	{
		Session session = this.getSession(Constants.DB_ID);
		try
		{
			session.beginTrans();
			String manager_id= SequenceGenerator.getInstance().getNextSequence(Constants.DB_ID, "t_mall_product_manager");
			proManInfo.put("manager_id", manager_id);
			session.insert("t_mall_product_manager", proManInfo);
			if(null != imgInfo){
				imgInfo.put("img_vesting", manager_id);
				imgInfo.put("img_id", SequenceGenerator.getInstance().getNextSequence(Constants.DB_ID, "T_MALL_IMG"));
				session.insert("t_mall_img", imgInfo);
			}
			session.commitTrans();
		}
		catch (Exception ex)
		{
			session.rollbackTrans();
			ex.printStackTrace();
			throw new InvokeException(-10, "��Ʒ������������ʧ��" + ex.getMessage());
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
	
	/**
	 * 
	 * �������޸��û���������ӦͼƬ��Ϣ
	 */
	public void upateProductManager(DataRow proManInfo, DataRow imgInfo) throws InvokeException,Exception
	{
		Session session = this.getSession(Constants.DB_ID);
		try
		{
			session.beginTrans();
			//int manager_id=Integer.parseInt(proManInfo.getString("manager_id"));
			//session.update("T_MALL_PRODUCT_MANAGER", proManInfo, "manager_id" , manager_id);
			
			String sql1="select * from T_MALL_IMG where img_id="+imgInfo.getString("img_id");
			if(!session.query(sql1).isEmpty()){
				session.update("T_MALL_IMG", imgInfo, "img_id", Integer.parseInt(imgInfo.getString("img_id")));
			}else{
				session.insert("T_MALL_IMG", imgInfo);
			}
			
			session.commitTrans();
		}
		catch (Exception ex)
		{
			session.rollbackTrans();
			ex.printStackTrace();
			throw new InvokeException(-10, "��Ϣ����ʧ��" + ex.getMessage());
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
	
}

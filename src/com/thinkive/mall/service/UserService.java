package com.thinkive.mall.service;

import java.util.Date;

import com.project.utils.Constants;
import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.jdbc.JdbcTemplate;
import com.thinkive.base.jdbc.session.Session;
import com.thinkive.base.service.BaseService;
import com.thinkive.base.service.SequenceGenerator;
import com.thinkive.base.util.DateHelper;
import com.thinkive.base.util.RandomHelper;
import com.thinkive.base.util.StringHelper;
import com.thinkive.base.util.security.SHA512;
import com.thinkive.server.InvokeException;

public class UserService extends BaseService
{
	
	public JdbcTemplate getJdbcTemplate()
	{
		return this.getJdbcTemplate(Constants.DB_ID);
	}
	
	/**
	 * 
	 * 描述：增加用户、密码并记录日志
	 */
	public void insertUser(DataRow userInfo, DataRow pwdInfo, DataRow logInfo)  throws InvokeException, Exception
	{
		Session session = null;
		try
		{
			session = getSession(Constants.DB_ID);
			session.beginTrans();
			session.insert("T_ONLINE_USER", userInfo);
			session.insert("T_USER_PWD", pwdInfo);
			session.insert("T_USER_LOG", logInfo);
			session.commitTrans();
		}
		catch (Exception ex)
		{
			if (session != null)
				session.rollbackTrans();
			throw ex;
		}
		finally
		{
			session.close();
			session = null;
		}
	}
	
	/**
	 * 
	 * 描述：增加用户、密码、资金账号
	 */
	public void insertUserManager(DataRow userInfo, DataRow pwdInfo, DataRow accInfo)  throws InvokeException, Exception
	{
		Session session = null;
		try
		{
			session = getSession(Constants.DB_ID);
			session.beginTrans();
			session.insert("T_ONLINE_USER", userInfo);
			session.insert("T_USER_PWD", pwdInfo);
			session.insert("T_MALL_EXTERNAL_ACCOUNTS", accInfo);
			session.commitTrans();
		}
		catch (Exception ex)
		{
			if (session != null)
				session.rollbackTrans();
			throw ex;
		}
		finally
		{
			session.close();
			session = null;
		}
	}
	/**
	 * 
	 * 描述：增加用户、密码
	 */
	public void insertUserManager(DataRow userInfo, DataRow pwdInfo) throws InvokeException, Exception
	{
		Session session = null;
		try
		{
			session = getSession(Constants.DB_ID);
			session.beginTrans();
			session.insert("T_ONLINE_USER", userInfo);
			session.insert("T_USER_PWD", pwdInfo);
			session.commitTrans();
		}
		catch (Exception ex)
		{
			if (session != null)
				session.rollbackTrans();
			throw ex;
		}
		finally
		{
			session.close();
			session = null;
		}
	}
	
	
	/**
	 * 
	 * 描述：修改用户、密码、资金账号
	 */
	public void upateUserManager(DataRow userInfo, DataRow pwdInfo, DataRow logInfo) throws InvokeException, Exception
	{
		Session session = this.getSession(Constants.DB_ID);
		try
		{
			String time = DateHelper.formatDate(new Date());
			session.beginTrans();
			int user_id=Integer.parseInt(userInfo.getString("user_id"));
			
			session.update("T_ONLINE_USER", userInfo, "user_id" , user_id);
			
			String sql1="select * from T_USER_PWD where user_id="+user_id;
			if(!session.query(sql1).isEmpty()){
				session.update("T_USER_PWD", pwdInfo, "user_id", user_id);
			}else{
				pwdInfo.set("create_time", time);
				pwdInfo.set("update_time", time);
				session.insert("T_USER_PWD", pwdInfo);
			}
			String sql="select * from T_MALL_EXTERNAL_ACCOUNTS where user_id="+user_id;
			if(session.query(sql).isEmpty()){
				logInfo.set("external_accounts_id", SequenceGenerator.getInstance().getNextSequence("web", "T_MALL_EXTERNAL_ACCOUNTS"));
				logInfo.set("external_accounts_type", "0");
				logInfo.set("create_time", time);
				logInfo.set("update_time", time);
				session.insert("T_MALL_EXTERNAL_ACCOUNTS", logInfo);
			}else{
				int external_accounts_id=Integer.parseInt(logInfo.getString("external_accounts_id"));
				session.update("T_MALL_EXTERNAL_ACCOUNTS", logInfo, "external_accounts_id", external_accounts_id);
			}
			
			session.commitTrans();
		}
		catch (Exception ex)
		{
			session.rollbackTrans();
			ex.printStackTrace();
			throw new InvokeException(-10, "信息更新失败" + ex.getMessage());
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
	 * 描述：修改用户、密码
	 */
	public void upateUserManager(DataRow userInfo, DataRow pwdInfo) throws InvokeException, Exception
	{
		Session session = this.getSession(Constants.DB_ID);
		try
		{
			session.beginTrans();
			int user_id=Integer.parseInt(userInfo.getString("user_id"));
			session.update("T_ONLINE_USER", userInfo, "user_id" , user_id);
			
			String sql1="select * from T_USER_PWD where user_id="+user_id;
			if(session.query(sql1)!=null){
			session.update("T_USER_PWD", pwdInfo, "user_id", user_id);
			}else{
				session.insert("T_USER_PWD", pwdInfo);
			}
			
			session.commitTrans();
		}
		catch (Exception ex)
		{
			session.rollbackTrans();
			ex.printStackTrace();
			throw new InvokeException(-10, "信息更新失败" + ex.getMessage());
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

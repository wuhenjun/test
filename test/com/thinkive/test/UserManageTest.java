package com.thinkive.test;

import com.thinkive.framework.business.SqlExecFunction;
import com.thinkive.server.Function;
import com.thinkive.server.test.SimpleContextImpl;
import com.thinkive.server.test.SimpleRequestImpl;
import com.thinkive.server.test.SimpleResponseImpl;

import junit.framework.TestCase;

/**
 * 描述: 
 * 版权: Copyright (c) 2012 
 * 公司: 思迪信息
 * 作者: 李炜
 * 版本: 1.0 
 * 创建日期: May 28, 2014 
 * 创建时间: 9:49:58 AM
 */
public class UserManageTest extends TestCase
{
	
	private SimpleRequestImpl request = null;
	
	private SimpleResponseImpl response = null;
	
	private SimpleContextImpl context = null;
	
	public UserManageTest()
	{
		request = new SimpleRequestImpl();
		response = new SimpleResponseImpl();
		context = new SimpleContextImpl();
	}
	
	private boolean testQueryUserPage()
	{
		request.clear();
		response.clear();
		context.clear();
		context.setRequest(request);
		context.setResponse(response);
		request.setFuncNo(901900);
		request.addFieldValue("sqlId", "conf.sql.user.queryUserPage");
		request.addFieldValue("login_id", "hello");
		//request.addFieldValue("resultType", "page");
		
		Function function = new SqlExecFunction();
		function.invoke(context);
		
		System.out.println("=====================执行conf.sql.user.queryUserPage开始====================");
		System.out.println(response.getErrorNo() + "" + response.getErrorInfo());
		System.out.println(response.getData());
		System.out.println("=====================执行conf.sql.user.queryUserPage结束====================");
		return (response.getErrorNo() == 0);
		
	}
	
	private boolean testQueryUser()
	{
		
		request.clear();
		response.clear();
		context.clear();
		context.setRequest(request);
		context.setResponse(response);
		request.setFuncNo(901900);
		request.addFieldValue("sqlId", "conf.sql.user.queryUser");
		request.addFieldValue("user_id", "31");
		
		Function function = new SqlExecFunction();
		function.invoke(context);
		
		System.out.println("=====================执行conf.sql.user.queryUser开始====================");
		System.out.println(response.getErrorNo() + "" + response.getErrorInfo());
		System.out.println(response.getData());
		System.out.println("=====================执行conf.sql.user.queryUser结束====================");
		return (false);
		
	}
	
	public void testExecute()
	{
		boolean flag = false;
		flag = testQueryUserPage();
		assertTrue(flag);
		flag = testQueryUser();
		assertTrue(flag);
	}
	
}

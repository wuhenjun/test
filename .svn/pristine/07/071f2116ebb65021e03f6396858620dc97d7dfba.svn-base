package com.thinkive.test;

import junit.framework.TestCase;

import com.thinkive.framework.business.SqlExecFunction;
import com.thinkive.plat.function.Function901918;
import com.thinkive.server.Function;
import com.thinkive.server.test.SimpleContextImpl;
import com.thinkive.server.test.SimpleRequestImpl;
import com.thinkive.server.test.SimpleResponseImpl;

/**
 * 描述: 
 * 版权: Copyright (c) 2012 
 * 公司: 思迪信息
 * 作者: 李炜
 * 版本: 1.0 
 * 创建日期: May 27, 2014 
 * 创建时间: 8:29:02 PM
 */
public class SqlExecFunctionTest extends TestCase
{
	
	public void testExecute()
	{
		SimpleRequestImpl request = new SimpleRequestImpl();
		SimpleResponseImpl response = new SimpleResponseImpl();
		SimpleContextImpl context = new SimpleContextImpl();
		context.setRequest(request);
		context.setResponse(response);
		request.setFuncNo(901900);
		request.addFieldValue("sqlId", "conf.sql.sitecatalog.queryChildrenForSiteCatalogMananger");
		request.addFieldValue("id", "");
//		request.addFieldValue("resultType", "page");
		
		Function function = new SqlExecFunction();
		function.invoke(context);
		
		System.out.println(response.getData());
		assertTrue(response.getErrorNo() == 0);
	}
	
}

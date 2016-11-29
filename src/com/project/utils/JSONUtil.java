package com.project.utils;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

/**
 * ����: JsonUtil������
 * ��Ȩ: Copyright (c) 2013
 * ��˾: ˼�ϿƼ� 
 * ����: ��ʥ��
 * �汾: 1.0 
 * ��������: May 8, 2014 
 * ����ʱ��: 7:23:27 PM
 */
public class JSONUtil
{
	
	/**
	 * @���ܣ� ��һ��JSON �����ַ���ʽ�еõ�һ��java����.
	 * @author����ʥ��(HUANGRONALDO)
	 * @time��May 9, 2014 10:40:45 AM
	 * @param jsonString
	 * @param pojoCalss
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object getObject4JsonString(String jsonString, Class pojoCalss)
	{
		Object pojo;
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		pojo = JSONObject.toBean(jsonObject, pojoCalss);
		return pojo;
	}
	
	/**
	 *�� ��json HASH���ʽ�л�ȡһ��map����map֧��Ƕ�׹���.
	 * @param jsonString
	 * @return ����
	 */
	
	public static Map<String, Object> getMap4Json(String jsonString)
	{
		Map<String, Object> valueMap = new HashMap<String, Object>();
		if(StringUtils.isEmpty(jsonString)){
			return valueMap ;
		}
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		Iterator<?> keyIter = jsonObject.keys();
		String key;
		Object value;
		while (keyIter.hasNext())
		{
			key = (String) keyIter.next();
			value = jsonObject.get(key);
			valueMap.put(key, value);
		}
		return valueMap;
	}
	
	/**
	 * @���ܣ���json�����еõ���Ӧjava���� .����
	 * @author����ʥ��(HUANGRONALDO)
	 * @time��May 9, 2014 10:40:25 AM
	 * @param jsonString
	 * @return
	 */
	public static Object[] getObjectArray4Json(String jsonString)
	{
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		return jsonArray.toArray();
	}
	
	/**
	 * ������json���󼯺ϱ��ʽ�еõ�һ��java�����б� ��.��
	 * 
	 * @param jsonString
	 *            ����
	 * @param pojoClass
	 *            ����
	 * @return ����
	 */
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getList4Json(String jsonString, Class pojoClass)
	{
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		JSONObject jsonObject;
		Object pojoValue;
		List list = new ArrayList();
		for (int i = 0; i < jsonArray.size(); i++)
		{
			jsonObject = jsonArray.getJSONObject(i);
			pojoValue = JSONObject.toBean(jsonObject, pojoClass);
			list.add(pojoValue);
		}
		return list;
		
	}
	
	/**
	 * @���ܣ���json�����н�����java�ַ�������.
	 * @author����ʥ��(HUANGRONALDO)
	 * @time��May 9, 2014 10:41:10 AM
	 * @param jsonString
	 * @return
	 */
	public static String[] getStringArray4Json(String jsonString)
	{
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		String[] stringArray = new String[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++)
		{
			stringArray[i] = jsonArray.getString(i);
		}
		return stringArray;
		
	}
	
	/**
	 * @���ܣ���json�����н�����javaLong�Ͷ�������.
	 * @author����ʥ��(HUANGRONALDO)
	 * @time��May 9, 2014 10:41:23 AM
	 * @param jsonString
	 * @return
	 */
	public static Long[] getLongArray4Json(String jsonString)
	{
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		Long[] longArray = new Long[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++)
		{
			longArray[i] = jsonArray.getLong(i);
		}
		return longArray;
	}
	
	/**
	 * @���ܣ���json�����н�����java Integer�Ͷ�������.
	 * @author����ʥ��(HUANGRONALDO)
	 * @time��May 9, 2014 10:42:02 AM
	 * @param jsonString
	 * @return
	 */
	public static Integer[] getIntegerArray4Json(String jsonString)
	{
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		Integer[] integerArray = new Integer[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++)
		{
			integerArray[i] = jsonArray.getInt(i);
		}
		return integerArray;
	}
	
	/**
	 * @���ܣ���json�����н�����java Date �Ͷ������飬ʹ�ñ��������뱣֤.
	 * @author����ʥ��(HUANGRONALDO)
	 * @time��May 9, 2014 10:42:19 AM
	 * @param jsonString
	 * @param dataFormat
	 * @return
	 * @throws ParseException
	 */
	public static Date[] getDateArray4Json(String jsonString, String dataFormat) throws ParseException
	{
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		Date[] dateArray = new Date[jsonArray.size()];
		String dateString;
		Date date;
		for (int i = 0; i < jsonArray.size(); i++)
		{
			dateString = jsonArray.getString(i);
			if(StringUtils.isEmpty(dataFormat)){
				dataFormat = "yyyy-MM-dd" ;
			}
			SimpleDateFormat df = new SimpleDateFormat(dataFormat);
			date = df.parse(dateString);
			dateArray[i] = date;
		}
		return dateArray;
	}
	
	/**
	 * 
	 * @���ܣ� ��json�����н�����java Integer�Ͷ�������.
	 * @author����ʥ��(HUANGRONALDO)
	 * @time��May 9, 2014 10:43:29 AM
	 * @param jsonString
	 * @return
	 */
	public static Double[] getDoubleArray4Json(String jsonString)
	{
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		Double[] doubleArray = new Double[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++)
		{
			doubleArray[i] = jsonArray.getDouble(i);
		}
		return doubleArray;
	}

	/**
	 * @���ܣ���java����ת����json�ַ���.
	 * @author����ʥ��(HUANGRONALDO)
	 * @time��May 9, 2014 10:44:06 AM
	 * @param javaObj
	 * @return
	 */
	public static String getJsonString4JavaPOJO(Object javaObj)
	{
		JSONObject json;
		json = JSONObject.fromObject(javaObj);
		return json.toString();
	}
	
	/**
	 * ��JSON��ת����LIST.
	 * 
	 * <pre>
	 * eg:
	 * String str = "[{assignId:1000,result:1,pubAddrs:[{orgId:1001,result:2,confmName:'peking univ'},{orgId:1002,result:1,confmName:'beijing univ'}]}]";
	 * Map<String, Class> m = new HashMap<String, Class>();
	 * m.put("pubAddrs", PubConfirmAddr.class);
	 * List<PubConfirm> list = JsonUtils.covertToList(str, PubConfirm.class, m);
	 * </pre>
	 * 
	 * @param arrayStr
	 * @param objectClass
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List covertToList(String arrayStr, Class objectClass, Map<String, Class> map)
	{
		
		List list = new ArrayList();
		if (StringUtils.isBlank(arrayStr))
		{
			return list;
		}
		if (!net.sf.json.util.JSONUtils.mayBeJSON(arrayStr))
		{
			return list;
		}
		JSONArray ay = JSONArray.fromObject(arrayStr);
		if (ay.size() == 0)
		{
			return list;
		}
		for (int i = 0; i < ay.size(); i++)
		{
			JSONObject obj = (JSONObject) ay.get(i);
			if (map != null)
			{
				list.add(JSONObject.toBean(obj, objectClass, map));
			}
			else
			{
				list.add(JSONObject.toBean(obj, objectClass));
			}
		}
		return list;
	}
	
	private static final String dateformater = "yyyy-MM-dd hh:mm:ss";
	
	/**
	 * 
	 * <pre>
	 *       Description:
	 *       	ͳһ��������	
	 *       @param obj
	 *       @return
	 *       @throws Exception
	 *       Arlon.Yang created this method at 2011-2-4 22:52:29
	 * </pre>
	 */
	@SuppressWarnings("rawtypes")
	public static String FormatToJsonstr(Object obj)
	{
		StringBuffer sbf = null;
		try
		{
			if (obj == null)
			{
				return "";
			}
			else if (obj instanceof String || obj instanceof Integer || obj instanceof Double || obj instanceof Float || obj instanceof Long || obj instanceof Boolean || obj instanceof Character
					|| obj instanceof Byte)
			{
				return "\"" + obj.toString() + "\"";
			}
			
			else if (obj instanceof Date)
			{
				Date d = (Date) obj;
				SimpleDateFormat sdf = new SimpleDateFormat(dateformater);
				return sdf.format(d);
			}
			else if (obj instanceof java.sql.Date)
			{
				java.sql.Date d = (java.sql.Date) obj;
				SimpleDateFormat sdf = new SimpleDateFormat(dateformater);
				return sdf.format(d);
			}
			else if (obj instanceof Timestamp)
			{
				Timestamp ts = (Timestamp) obj;
				SimpleDateFormat sdf = new SimpleDateFormat(dateformater);
				return sdf.format(new Date(ts.getTime()));
			}
			else if (obj instanceof Collection)
			{
				sbf = FormatCollection((Collection) obj);
			}
			
			else if (obj instanceof Map)
			{
				sbf = FormatMap((Map) obj);
			}
			
			else if (obj instanceof Object[])
			{
				sbf = FormatObjectAttr((Object[]) obj);
			}
			else
			{
				sbf = FormatPojo(obj);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return sbf.toString();
	}
	
	/**
	 * <pre>
	 *      Description:
	 *      		��ʽ�� List,Set �Լ���ʵ�����������(֧����ȸ�ֵ)
	 *      @param col
	 *      @return
	 *      @throws Exception
	 *      Arlon.Yang created this method at 2011-2-4 22:52:52
	 * </pre>
	 */
	@SuppressWarnings("rawtypes")
	private static StringBuffer FormatCollection(Collection col) throws Exception
	{
		StringBuffer sbf = new StringBuffer();
		if (col == null || col.size() == 0)
		{
			sbf.append("[]");
			return sbf;
		}
		sbf.append("[");
		for (Object obj : col)
		{
			sbf.append(FormatToJsonstr(obj));
			sbf.append(",");
		}
		if (sbf.length() != -1)
		{
			sbf.deleteCharAt(sbf.length() - 1).append("]");
		}
		else
		{
			sbf.append("]");
		}
		return sbf;
	}
	
	/**
	 * <pre>
	 *      Description:
	 *      		��ʽ��Map����
	 *      @param map
	 *      @return
	 *      @throws Exception
	 *      Arlon.Yang created this method at 2011-2-4 22:53:04
	 * </pre>
	 */
	@SuppressWarnings("rawtypes")
	private static StringBuffer FormatMap(Map map) throws Exception
	{
		StringBuffer sbf = new StringBuffer();
		sbf.append("{");
		for (Object obj : map.keySet())
		{
			sbf.append("\"" + obj + "\":");
			sbf.append(FormatToJsonstr(map.get(obj)));
			sbf.append(",");
		}
		
		if (sbf.length() != 1)
		{
			sbf.deleteCharAt(sbf.length() - 1).append("}");
		}
		else
		{
			sbf.append("}");
		}
		return sbf;
	}
	
	/**
	 * <pre>
	 *      Description:
	 *      		��ʽ��������pojo(����snitf�������ɸ�ʽ��Pojo�������Ӧ)
	 *      @param obj
	 *      @return
	 *      Arlon.Yang created this method at 2011-2-4 22:53:15
	 * </pre>
	 */
	@SuppressWarnings("rawtypes")
	private static StringBuffer FormatPojo(Object obj)
	{
		StringBuffer sbf = new StringBuffer();
		Class c = null;
		Object resultobject = null;
		sbf.append("{");
		try
		{
			c = obj.getClass();
			/** Snitf ���е�Pojo */
			/** ��ȡ���е��ֶ� */
			for (Field field : c.getDeclaredFields())
			{
				field.setAccessible(true);
				resultobject = field.get(obj);
				sbf.append("\"" + field.getName() + "\":" + "\"" + FormatToJsonstr(resultobject) + "\",");
			}
			
			if (sbf.length() != 1)
			{
				sbf.deleteCharAt(sbf.length() - 1).append("}");
			}
			else
			{
				sbf.deleteCharAt(sbf.length() - 1);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			resultobject = null;
			c = null;
		}
		return sbf;
	}
	
	/**
	 * <pre>
	 *      Description:
	 *      		��ʽ���������
	 *      @param objattr
	 *      @return
	 *      @throws Exception
	 *      Arlon.Yang created this method at 2011-2-4 22:53:42
	 * </pre>
	 */
	private static StringBuffer FormatObjectAttr(Object[] objattr) throws Exception
	{
		StringBuffer sbf = new StringBuffer();
		sbf.append("[");
		for (Object obj : objattr)
		{
			sbf.append(FormatToJsonstr(obj));
			sbf.append(",");
		}
		if (sbf.length() != 1)
		{
			sbf.deleteCharAt(sbf.length() - 1).append("]");
		}
		else
		{
			sbf.append("]");
		}
		return sbf;
	}
	
	/** �ж��Ƿ�Ϊjson�ַ��� */
	public static boolean isJson(String jsonString)
	{
		if (jsonString == null)
		{
			return false;
		}
		try
		{
			JSONObject.fromObject(jsonString);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
}
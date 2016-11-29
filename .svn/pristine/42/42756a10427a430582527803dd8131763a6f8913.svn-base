package com.thinkive.mall.crm;

import java.sql.Time;
import java.util.Date;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;


import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.jdbc.JdbcTemplate;
import com.thinkive.base.util.DateHelper;
import com.thinkive.base.util.StringHelper;


public final class SmsHelper {

	
	
	/**
	 * 短信接口发送队列表表名
	 */
	public static final String TABLE_NAME_SMS = "queues";
	
	/**
	 * 日志
	 */
	private static Logger logger = Logger.getLogger(SmsHelper.class);
	
	/**
	 * 描述：发送短信
	 * 作者：李建
	 * 时间：Mar 15, 2011 5:07:33 PM
	 * @param phone 手机号码，多个手机号用逗号分隔
	 * @param content 短信内容
	 */
	public static void sendSms(String phone, String content)throws Exception
	{
		sendSms(phone, content, 0, "");
	}
	
	/**
	 * 描述：快速发送短信（优先级别最高）
	 * 作者：李建
	 * 时间：Mar 15, 2011 5:07:33 PM
	 * @param phone 手机号码，多个手机号用逗号分隔
	 * @param content 短信内容
	 */
	public static void fastSendSms(String phone, String content)throws Exception
	{
		sendSms(phone, content, 1, "");
	}
	
	/**
	 * 描述：
	 * 作者：李建
	 * 时间：Mar 15, 2011 5:07:33 PM
	 * @param phone 手机号码，多个手机号用逗号分隔
	 * @param content 短信内容
	 * @param retry 优先级别 （值为1～3，1级别最高）
	 */
	public static void sendSms(String phone, String content, int retry)throws Exception
	{
		sendSms(phone, content, retry, "");
	}
	
	/**
	 * 描述：
	 * 作者：李建
	 * 时间：Mar 15, 2011 5:07:33 PM
	 * @param phone 手机号码，多个手机号用逗号分隔
	 * @param content 短信内容
	 * @param sendTime 发送时间
	 */
	public static void sendSms(String phone, String content, Date sendTime)throws Exception
	{
		sendSms(phone, content, 0, sendTime);
	}
	
	/**
	 * 描述：
	 * 作者：李建
	 * 时间：Mar 15, 2011 5:07:33 PM
	 * @param phone 手机号码，多个手机号用逗号分隔
	 * @param content 短信内容
	 * @param sendTime 发送时间
	 */
	public static void sendSms(String phone, String content, String sendTime)throws Exception
	{
		sendSms(phone, content, 0, sendTime);
	}
	
	/**
	 * 描述：快速发送短信（优先级别最高）
	 * 作者：李建
	 * 时间：Mar 15, 2011 5:07:33 PM
	 * @param phone 手机号码，多个手机号用逗号分隔
	 * @param content 短信内容
	 * @param sendTime 发送时间
	 */
	public static void fastSendSms(String phone, String content, Date sendTime)throws Exception
	{
		sendSms(phone, content, 1, sendTime);
	}
	
	/**
	 * 描述：快速发送短信（优先级别最高）
	 * 作者：李建
	 * 时间：Mar 15, 2011 5:07:33 PM
	 * @param phone 手机号码，多个手机号用逗号分隔
	 * @param content 短信内容
	 * @param sendTime 发送时间
	 */
	public static void fastSendSms(String phone, String content, String sendTime)throws Exception
	{
		sendSms(phone, content, 1, sendTime);
	}
	
	/**
	 * 描述：
	 * 作者：李建
	 * 时间：Mar 15, 2011 5:07:33 PM
	 * @param phone 手机号码，多个手机号用逗号分隔
	 * @param content 短信内容
	 * @param retry 优先级别 （值为1～3，1级别最高）
	 * @param sendTime 发送时间
	 */
	public static void sendSms(String phone, String content, int retry, String sendTime)throws Exception
	{
		if(StringHelper.isNotEmpty(sendTime))
		{
			sendSms(phone, content, retry, DateHelper.parseString(sendTime));
		}
		else
		{
			Date date = null;
			sendSms(phone, content, retry, date);
		}
	}
	
	/**
	 * 描述：发送短信
	 * 作者：李建
	 * 时间：Mar 15, 2011 5:05:01 PM
	 * @param phone 手机号码，多个手机号用逗号分隔
	 * @param content 短信内容
	 * @param retry 优先级别 （值为1～3，1级别最高）
	 * @param sendTime 发送时间
	 */
	public static void sendSms(String phone, String content, int retry, Date sendTime)throws Exception
	{
		DataRow data = null;
		if(StringHelper.isNotEmpty(phone) && StringHelper.isNotEmpty(content))
		{
			String[] phones = phone.split(",");
			for (int i = 0; i < phones.length; i++)
			{
				if(Pattern.matches("^1\\d{10}$", phones[i]))
				{
					data = new DataRow();
					data.set("mobile", phones[i]);
					data.set("content", content);
					if(retry>0)
					{
						data.set("retry", String.valueOf(retry));
					}
					if(sendTime!=null)
					{
						data.set("sendtime", new Time(sendTime.getTime()));
					}
					
					if(data!=null && !data.isEmpty())
					{
						add(data);
					}
				}
				else
				{
					logger.error("“"+phones[i]+"”此手机号码不正确！");
				}
			}
			
		}
	}
	
	/**
	 * 描述：
	 * 作者：李建
	 * 时间：Mar 12, 2009 4:57:06 PM
	 * @param mobile 手机号
	 * @param code 验证码
	 * @throws Exception
	 */
	public static void sendCodeToMobile(String mobile, String code) throws Exception
	{
		sendCodeToMobile(mobile, code, null, null);
	}
	
	
	
	/**
	 * 描述：发送手机验证码
	 * 作者：李建
	 * 时间：Mar 24, 2011 11:27:51 AM
	 * @param mobile 手机号
	 * @param code 验证码
	 * @param prev 前缀
	 * @param after 后缀
	 * @throws Exception
	 */
	public static void sendCodeToMobile(String mobile, String code, String prev, String after) throws Exception
	{
		if (StringHelper.isEmpty(mobile))
		{
			return;
		}
		
		if(StringHelper.isEmpty(prev))
		{
			prev = "您的服务申请验证码：";
		}
		
		if(StringHelper.isEmpty(after))
		{
			after = "（验证码三分钟后自动失效，输入不区分大小写，此短信免费） ";
		}
		
		String content =  prev+ code + after;
		SmsHelper.fastSendSms(mobile, content);
	}
	
	/**
	 * 描述：增加一条记录到短信接口发送队列表
	 * 作者：李建
	 * 时间：Mar 15, 2011 3:58:08 PM
	 * @param sendsms 
	 * @param data
	 */
	

	private static void add(DataRow data) throws Exception
	{
		  //data.put("id",new JdbcTemplate(WebConstants.DB_SMS).queryInt("select nvl( max(id),0)from queues " )+1);
		    logger.info("成功执行到把短信记录放入到短信接口发送队列");
			new JdbcTemplate("sms").insert(TABLE_NAME_SMS, data);
			
			
	}
	public static void main(String[] args) {
		try {
			SmsHelper.sendSms("18603829918", "test");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

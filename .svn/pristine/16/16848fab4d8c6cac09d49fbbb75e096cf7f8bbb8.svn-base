package com.thinkive.mall.crm;

import org.apache.log4j.Logger;

import com.thinkive.mall.crm.SmsHelper;

public class SmsSender implements Runnable{

private static Logger logger = Logger.getLogger(SmsSender.class);
	
	private String phone;
	
	private String content;
	
	private int retry;
	
	private String sendTime;
	
	
	public SmsSender(String phone, String content)
	{
		this.phone = phone;
		this.content = content;
	}
	
	
	public SmsSender(String phone, String content, int retry, String sendTime)
	{
		this.phone = phone;
		this.content = content;
		this.retry = retry;
		this.sendTime = sendTime;
	}
	
	
	public void run()
	{
		// 发送邮件任务
		try
		{
			if(retry>0 && sendTime!=null && sendTime.length()>0)
			{
				SmsHelper.sendSms(phone, content, retry, sendTime);
			}
			else
			{
				SmsHelper.fastSendSms(phone, content);
			}
			logger.info("成功发送到"+phone);
		}
		catch (Exception ex)
		{
			logger.error(ex.getMessage(),ex);
		}
	}
	
}

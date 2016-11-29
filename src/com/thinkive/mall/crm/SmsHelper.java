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
	 * ���Žӿڷ��Ͷ��б����
	 */
	public static final String TABLE_NAME_SMS = "queues";
	
	/**
	 * ��־
	 */
	private static Logger logger = Logger.getLogger(SmsHelper.class);
	
	/**
	 * ���������Ͷ���
	 * ���ߣ��
	 * ʱ�䣺Mar 15, 2011 5:07:33 PM
	 * @param phone �ֻ����룬����ֻ����ö��ŷָ�
	 * @param content ��������
	 */
	public static void sendSms(String phone, String content)throws Exception
	{
		sendSms(phone, content, 0, "");
	}
	
	/**
	 * ���������ٷ��Ͷ��ţ����ȼ�����ߣ�
	 * ���ߣ��
	 * ʱ�䣺Mar 15, 2011 5:07:33 PM
	 * @param phone �ֻ����룬����ֻ����ö��ŷָ�
	 * @param content ��������
	 */
	public static void fastSendSms(String phone, String content)throws Exception
	{
		sendSms(phone, content, 1, "");
	}
	
	/**
	 * ������
	 * ���ߣ��
	 * ʱ�䣺Mar 15, 2011 5:07:33 PM
	 * @param phone �ֻ����룬����ֻ����ö��ŷָ�
	 * @param content ��������
	 * @param retry ���ȼ��� ��ֵΪ1��3��1������ߣ�
	 */
	public static void sendSms(String phone, String content, int retry)throws Exception
	{
		sendSms(phone, content, retry, "");
	}
	
	/**
	 * ������
	 * ���ߣ��
	 * ʱ�䣺Mar 15, 2011 5:07:33 PM
	 * @param phone �ֻ����룬����ֻ����ö��ŷָ�
	 * @param content ��������
	 * @param sendTime ����ʱ��
	 */
	public static void sendSms(String phone, String content, Date sendTime)throws Exception
	{
		sendSms(phone, content, 0, sendTime);
	}
	
	/**
	 * ������
	 * ���ߣ��
	 * ʱ�䣺Mar 15, 2011 5:07:33 PM
	 * @param phone �ֻ����룬����ֻ����ö��ŷָ�
	 * @param content ��������
	 * @param sendTime ����ʱ��
	 */
	public static void sendSms(String phone, String content, String sendTime)throws Exception
	{
		sendSms(phone, content, 0, sendTime);
	}
	
	/**
	 * ���������ٷ��Ͷ��ţ����ȼ�����ߣ�
	 * ���ߣ��
	 * ʱ�䣺Mar 15, 2011 5:07:33 PM
	 * @param phone �ֻ����룬����ֻ����ö��ŷָ�
	 * @param content ��������
	 * @param sendTime ����ʱ��
	 */
	public static void fastSendSms(String phone, String content, Date sendTime)throws Exception
	{
		sendSms(phone, content, 1, sendTime);
	}
	
	/**
	 * ���������ٷ��Ͷ��ţ����ȼ�����ߣ�
	 * ���ߣ��
	 * ʱ�䣺Mar 15, 2011 5:07:33 PM
	 * @param phone �ֻ����룬����ֻ����ö��ŷָ�
	 * @param content ��������
	 * @param sendTime ����ʱ��
	 */
	public static void fastSendSms(String phone, String content, String sendTime)throws Exception
	{
		sendSms(phone, content, 1, sendTime);
	}
	
	/**
	 * ������
	 * ���ߣ��
	 * ʱ�䣺Mar 15, 2011 5:07:33 PM
	 * @param phone �ֻ����룬����ֻ����ö��ŷָ�
	 * @param content ��������
	 * @param retry ���ȼ��� ��ֵΪ1��3��1������ߣ�
	 * @param sendTime ����ʱ��
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
	 * ���������Ͷ���
	 * ���ߣ��
	 * ʱ�䣺Mar 15, 2011 5:05:01 PM
	 * @param phone �ֻ����룬����ֻ����ö��ŷָ�
	 * @param content ��������
	 * @param retry ���ȼ��� ��ֵΪ1��3��1������ߣ�
	 * @param sendTime ����ʱ��
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
					logger.error("��"+phones[i]+"�����ֻ����벻��ȷ��");
				}
			}
			
		}
	}
	
	/**
	 * ������
	 * ���ߣ��
	 * ʱ�䣺Mar 12, 2009 4:57:06 PM
	 * @param mobile �ֻ���
	 * @param code ��֤��
	 * @throws Exception
	 */
	public static void sendCodeToMobile(String mobile, String code) throws Exception
	{
		sendCodeToMobile(mobile, code, null, null);
	}
	
	
	
	/**
	 * �����������ֻ���֤��
	 * ���ߣ��
	 * ʱ�䣺Mar 24, 2011 11:27:51 AM
	 * @param mobile �ֻ���
	 * @param code ��֤��
	 * @param prev ǰ׺
	 * @param after ��׺
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
			prev = "���ķ���������֤�룺";
		}
		
		if(StringHelper.isEmpty(after))
		{
			after = "����֤�������Ӻ��Զ�ʧЧ�����벻���ִ�Сд���˶�����ѣ� ";
		}
		
		String content =  prev+ code + after;
		SmsHelper.fastSendSms(mobile, content);
	}
	
	/**
	 * ����������һ����¼�����Žӿڷ��Ͷ��б�
	 * ���ߣ��
	 * ʱ�䣺Mar 15, 2011 3:58:08 PM
	 * @param sendsms 
	 * @param data
	 */
	

	private static void add(DataRow data) throws Exception
	{
		  //data.put("id",new JdbcTemplate(WebConstants.DB_SMS).queryInt("select nvl( max(id),0)from queues " )+1);
		    logger.info("�ɹ�ִ�е��Ѷ��ż�¼���뵽���Žӿڷ��Ͷ���");
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

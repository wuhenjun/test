package com.thinkive.mall.business;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.util.StringHelper;
import com.thinkive.mall.service.ProtocleService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;

/**
 * 
 * ����: ����Э��
 * ��Ȩ: Copyright (c) 2014
 * ��˾: ˼�ϿƼ� 
 * ����: ���ͷ�
 * �汾: 1.0 
 * ��������: 2014-06-30 
 * ����ʱ��: ����
 */

public class Function902908 extends BaseFunction
{
	
	@Override
	public ResultVo execute() throws InvokeException, Exception
	{
		ResultVo result = new ResultVo();
		/**
		 * #{$seq(t_mall_agreement)},
			#{agreement_title},
			#{agreement_type},
			#{url},
			#{agreement_content},
			#{$currDate()},
			#{$currDate()},
			#{is_every_time},
			#{is_online_sign},
			#{is_valid}
		 */
		String agreement_title = getStrParameter("agreement_title");
		String agreement_type = getStrParameter("agreement_type");
		String contract_type = getStrParameter("contract_type");
		String url = getStrParameter("url");
		String agreement_content = getStrParameter("agreement_content");
		String create_time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String is_every_time = getStrParameter("is_every_time");
		String is_online_sign = getStrParameter("is_online_sign");
		String is_valid = getStrParameter("is_valid");
		if(StringHelper.isEmpty(agreement_title) || StringHelper.isEmpty(agreement_type)){
			throw new InvokeException(-90245101, "Э�����[agreement_title] �� Э������[agreement_type]����Ϊ��");
		}
		if(agreement_type.trim().equals("0")){  //��������
			if(StringHelper.isEmpty(url)){
				result.setErrorMsg("���ص�ַ[url]����Ϊ��");
				result.setErrorNo(-90245102);
				throw new InvokeException(-90245102, "���ص�ַ[url]����Ϊ��");
			}
		}else if(agreement_type.trim().equals("1")){  //�ı�����
			if(StringHelper.isEmpty(agreement_content)){
				result.setErrorMsg("Э�����ݲ���Ϊ��");
				result.setErrorNo(-90245103);
				throw new InvokeException(-90245103, "Э�����ݲ���Ϊ��");
			}
		}
		if(url != null && url.startsWith("/upload")){
			url = "/mall" + url;
		}
		if(!(null !=is_every_time &&  is_every_time.equals("1"))){
			is_every_time = "0";
		}
		if(!(null !=is_online_sign &&  is_online_sign.equals("1"))){
			is_online_sign = "0";
		}
		if(!(null !=is_valid &&  is_valid.equals("1"))){
			is_valid = "0";
		}
		
		DataRow dataRow = new DataRow();
		
		dataRow.set("agreement_title", agreement_title);
		dataRow.set("agreement_type", agreement_type);
		dataRow.set("contract_type", contract_type);
		dataRow.set("url", url);
		dataRow.set("agreement_content", agreement_content);
		dataRow.set("create_time", create_time);
		dataRow.set("is_every_time", is_every_time);
		dataRow.set("is_online_sign", is_online_sign);
		dataRow.set("is_valid", is_valid);
		ProtocleService protocleService = new ProtocleService();
		try{
			protocleService.insertProtocle(dataRow);
			result.setErrorMsg("�����ɹ�");
		}catch(Exception e){
			result.setErrorNo(-90245104);
			result.setErrorMsg(e.getMessage());
		}
		
		return result;
	}
	
}

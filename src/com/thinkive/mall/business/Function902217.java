package com.thinkive.mall.business;

import java.util.Date;

import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.service.SequenceGenerator;
import com.thinkive.base.util.DateHelper;
import com.thinkive.base.util.RandomHelper;
import com.thinkive.base.util.StringHelper;
import com.thinkive.base.util.security.SHA512;
import com.thinkive.mall.service.UserService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;

/**
 * ����: ����û���Ϣ
 */

public class Function902217 extends BaseFunction
{
	
	@Override
	public ResultVo execute() throws InvokeException, Exception
	{
		ResultVo resultVo = new ResultVo();
			try {
				String accountName = getStrParameter("account_name");
				String userType = getStrParameter("user_type");
				String userName = getStrParameter("user_name");
				String nickName = getStrParameter("nick_name");
				String gender = getStrParameter("gender");
				String birthday = getStrParameter("birthday");
				String identityType = getStrParameter("identity_type");
				String identityNum = getStrParameter("identity_num");
				String mobilePhone = getStrParameter("mobile_phone");
				String userMail = getStrParameter("user_mail");
				String telephone = getStrParameter("telephone");
				String address = getStrParameter("address");
				String postCode = getStrParameter("post_code");
				String wechat = getStrParameter("wechat");
				String qq = getStrParameter("qq");
				String channelCode = getStrParameter("channel_code");
				String recommendPerson = getStrParameter("recommend_person");
				String loginPwd = getStrParameter("login_pwd");
				
				//�ǿռ��
				if (StringHelper.isEmpty(accountName))
				{
					throw new InvokeException(-1, "�û���[account_name]����Ϊ��");
				}
				if (StringHelper.isEmpty(loginPwd))
				{
					resultVo.setErrorMsg("����[login_pwd]����Ϊ��");
					resultVo.setErrorNo(-2);
					throw new InvokeException(-2, "����[login_pwd]����Ϊ��");
				}
				
				
				// �����û���Ϣ��T_ONLINE_USER��
				DataRow userInfo = new DataRow();
				String user_id =  SequenceGenerator.getInstance().getNextSequence("mall", "T_ONLINE_USER");
				userInfo.set("user_id", user_id);
				userInfo.set("account_name", accountName);
				userInfo.set("user_type", userType);
				userInfo.set("user_name", userName);
				userInfo.set("nick_name", nickName);
				userInfo.set("gender", gender);
				userInfo.set("birthday", birthday);
				userInfo.set("identity_type", identityType);
				userInfo.set("identity_num", identityNum);
				userInfo.set("mobile_phone", mobilePhone);
				userInfo.set("user_mail", userMail);
				userInfo.set("telephone", telephone);
				userInfo.set("address", address);
				userInfo.set("post_code", postCode);
				userInfo.set("wechat", wechat);
				userInfo.set("qq", qq);
				userInfo.set("channel_code", channelCode);
				userInfo.set("recommend_person", recommendPerson);
				String time = DateHelper.formatDate(new Date());
				userInfo.set("create_time", time);
				userInfo.set("last_login_time", time);
				
				// ����������Ϣ��T_MALL_USER_PWD��
				DataRow pwdInfo = new DataRow();
				String pwd_salt = RandomHelper.getRandomStr(6);
				pwdInfo.set("user_id", user_id);
				pwdInfo.set("pwd_value", new SHA512().getSHA512ofStr(loginPwd + pwd_salt)); //TODO Ӧ����SALT
				pwdInfo.set("pwd_salt", pwd_salt);
				pwdInfo.set("create_time", time);
				
				//������ˮ��T_MALL_USER_LOG��
				DataRow logInfo = new DataRow();
				logInfo.set("log_id", SequenceGenerator.getInstance().getNextSequence("mall", "T_USER_LOG"));
				logInfo.set("user_id", user_id);
				logInfo.set("log_type",userType);
				logInfo.set("log_result",true);
				logInfo.set("create_time", time);
				
				UserService service = new UserService();
				service.insertUser(userInfo, pwdInfo, logInfo);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				resultVo.setErrorMsg(e.getMessage());
				resultVo.setErrorNo(-3);
				e.printStackTrace();
			}
		return resultVo;
	}
	
}

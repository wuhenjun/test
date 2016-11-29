package com.thinkive.mall.business;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.util.DateHelper;
import com.thinkive.base.util.RandomHelper;
import com.thinkive.base.util.StringHelper;
import com.thinkive.base.util.security.SHA512;
import com.thinkive.mall.service.UserService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;

/**
 * ����: �޸��û�������Ϣ
 */

public class Function902926 extends BaseFunction
{
	
	@Override
	public ResultVo execute() throws InvokeException, Exception
	{
		ResultVo resultVo = new ResultVo();
			try {
				String userName = getStrParameter("user_name");
				String gender = getStrParameter("gender");
				String birthday = getStrParameter("birthday");
				String identityType = getStrParameter("identity_type");
				String identityNum = getStrParameter("identity_num");
				String mobilePhone = getStrParameter("mobile_phone");
				String userMail = getStrParameter("user_mail");
				String address = getStrParameter("address");
				String qq = getStrParameter("qq");
				String pwd_value = getStrParameter("login_pwd");
				String external_account=getStrParameter("external_account");
				String external_accounts_id=getStrParameter("external_accounts_id");
				String user_id=getStrParameter("user_id");
				//�ǿռ��
//				if (StringHelper.isEmpty(accountName))
//				{
//					throw new FrameException(-1, "�û���[account_name]����Ϊ��");
//				}
//				if (StringHelper.isEmpty(pwd_value))
//				{
//					throw new FrameException(-2, "����[login_pwd]����Ϊ��");
//				}
				
				
				// �޸��û���Ϣ��T_ONLINE_USER��
				DataRow userInfo = new DataRow();
				userInfo.set("user_id", user_id);
				userInfo.set("user_name", userName);
				userInfo.set("gender", gender);
				userInfo.set("birthday", birthday);
				userInfo.set("identity_type", identityType);
				userInfo.set("identity_num", identityNum);
				userInfo.set("mobile_phone", mobilePhone);
				userInfo.set("user_mail", userMail);
				userInfo.set("address", address);
				userInfo.set("qq", qq);
				
				String time = DateHelper.formatDate(new Date());
				userInfo.set("update_time", time);
				// ����������Ϣ��T_MALL_USER_PWD��
				DataRow pwdInfo = new DataRow();
				String pwd_salt = RandomHelper.getRandomStr(6);
				pwdInfo.set("user_id", user_id);
				pwdInfo.set("pwd_value", new SHA512().getSHA512ofStr(pwd_value + pwd_salt)); //TODO Ӧ����SALT
				pwdInfo.set("pwd_salt", pwd_salt);
				pwdInfo.set("update_time", time);
				
				// �����ʽ��˺���Ϣ��T_MALL_EXTERNAL_ACCOUNTS��
				DataRow accInfo = new DataRow();
				accInfo.set("external_accounts_id", external_accounts_id);
				accInfo.set("external_account", external_account);
				accInfo.set("update_time", time);
				accInfo.set("user_id", user_id);
				UserService service = new UserService();
				if(StringHelper.isEmpty(external_account)){
					service.upateUserManager(userInfo, pwdInfo);
				}else{
					service.upateUserManager(userInfo, pwdInfo, accInfo);
				}
				
			} catch (InvokeException e) {
				// TODO Auto-generated catch block
				resultVo.setErrorMsg(e.getMessage());
				resultVo.setErrorNo(e.getErrorCode());
				e.printStackTrace();
			}
		return resultVo;
	}
	
}

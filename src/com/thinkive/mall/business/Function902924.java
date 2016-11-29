package com.thinkive.mall.business;

import java.util.Date;

import com.project.utils.Constants;
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
 * 描述: 添加用户管理信息
 */

public class Function902924 extends BaseFunction
{
	
	@Override
	public ResultVo execute() throws InvokeException, Exception
	{
		ResultVo resultVo = new ResultVo();
			try {
				
				String userType = "0";
				String userName = getStrParameter("user_name");
				String account_name = getStrParameter("account_name");
				String gender = getStrParameter("gender");
				String birthday = getStrParameter("birthday");
				String identityType = getStrParameter("identity_type");
				String identityNum = getStrParameter("identity_num");
				String mobilePhone = getStrParameter("mobile_phone");
				String userMail = getStrParameter("user_mail");
				String address = getStrParameter("address");
				String qq = getStrParameter("qq");
				String channelCode = "0";
				String pwd_value = getStrParameter("pwd_value");
				String external_account=getStrParameter("external_account");
				//非空检测
//				if (StringHelper.isEmpty(account_name))
//				{
//					throw new InvokeException(-1, "用户名[account_name]不能为空");
//				}
//				if (StringHelper.isEmpty(pwd_value))
//				{
//					throw new InvokeException(-2, "密码[login_pwd]不能为空");
//				}
				
				
				// 保存用户信息到T_ONLINE_USER表
				DataRow userInfo = new DataRow();
				
				String user_id =  SequenceGenerator.getInstance().getNextSequence(Constants.DB_ID, "T_ONLINE_USER");
				userInfo.set("user_id", user_id);
				userInfo.set("account_name", account_name);
				userInfo.set("user_type", userType);
				userInfo.set("user_name", userName);
				userInfo.set("gender", gender);
				userInfo.set("birthday", birthday);
				userInfo.set("identity_type", identityType);
				userInfo.set("identity_num", identityNum);
				userInfo.set("mobile_phone", mobilePhone);
				userInfo.set("user_mail", userMail);
				userInfo.set("address", address);
				userInfo.set("qq", qq);
				userInfo.set("channel_code", channelCode);
				String time = DateHelper.formatDate(new Date());
				userInfo.set("create_time", time);
				userInfo.set("update_time", time);
				// 保存密码信息到T_MALL_USER_PWD表
				DataRow pwdInfo = new DataRow();
				String pwd_salt = RandomHelper.getRandomStr(6);
				pwdInfo.set("user_id", user_id);
				pwdInfo.set("pwd_value", new SHA512().getSHA512ofStr(pwd_value + pwd_salt)); //TODO 应加上SALT
				pwdInfo.set("pwd_salt", pwd_salt);
				pwdInfo.set("create_time", time);
				// 保存资金账号信息到T_MALL_EXTERNAL_ACCOUNTS表
				DataRow accInfo = new DataRow();
				String external_accounts_id =  SequenceGenerator.getInstance().getNextSequence(Constants.DB_ID, "T_MALL_EXTERNAL_ACCOUNTS");
				accInfo.set("external_accounts_id", external_accounts_id);
				accInfo.set("external_accounts_type", "0");
				accInfo.set("user_id", user_id);
				accInfo.set("external_account", external_account);
				accInfo.set("create_time", time);
				accInfo.set("update_time", time);
				accInfo.set("user_id", user_id);
				UserService service = new UserService();
				if(StringHelper.isEmpty(external_account)){
					service.insertUserManager(userInfo, pwdInfo);
				}else{
					service.insertUserManager(userInfo, pwdInfo, accInfo);
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

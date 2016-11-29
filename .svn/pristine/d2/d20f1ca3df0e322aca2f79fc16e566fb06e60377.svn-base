package com.thinkive.mall.constant;

import java.util.ArrayList;

import com.thinkive.base.config.Configuration;


public class MallConstant extends BaseConstants
{
	public static final String DB_ONLINE_SHOPPING = "onlineShopping";
	public static final String DB_WEB = "web";
	public static final String sendSMS = "sms";
	public static final String GATEWAY_NAME_TRADE = "trade";//交易网关
	public static final String GATEWAY_NAME_SECRET = "secret";//加密网关
	public static final String GATEWAY_NAME_ACCOUNT = "account";
	
	public static final int SECRET_TYPE_ENCRYPT = Configuration.getInt("other.secret_encrypt", 10000);
	public static final int SECRET_TYPE_DECODE = Configuration.getInt("other.secret_decode", 10001);
	
	public static ArrayList<String> LIST_LOGIN_ACCOUNT_TYPE = new ArrayList<String>();
	
	//用户登录或校验的账户类型
	public static final String TYPE_UID = "0";//用户编号
	public static final String TYPE_ACCOUNT = "1";//账户名
	public static final String TYPE_MOBILEPHONE = "2";//手机
	public static final String TYPE_EMAIL = "3";//邮箱
	public static final String TYPE_QQ = "4";//QQ
	public static final String TYPE_QQ_MICROBLOG = "5";//QQ微博
	public static final String TYPE_SINA_MICROBLOG = "6";//新浪微博
	public static final String TYPE_IDENTIFY_ID = "7";//身份证
	public static final String TYPE_ASSET_ACCOUNT = "8";//资产账户
	
	//用户是否已开通手机登录
	public static final String LOGIN_BY_PHONE_FALSE = "0";//手机登录验证，未验证
	public static final String LOGIN_BY_PHONE_TRUE = "1";//手机登录验证，已验证
	
	//用户是否已开通邮箱登录
	public static final String LOGIN_BY_EMAIL_FALSE = "0";//邮箱登录验证，未验证
	public static final String LOGIN_BY_EMAIL_TRUE = "1";//邮箱登录验证，已验证
	
	//理财 产品详细同步到字段product_detail. 详细对应 web库中的id。
	public static final String MONEY_DETAIL_WEB_CATALOG_ID ="3296";
	
	public static final String MONEY_INFO_PUBLIC_CATALOG_ID ="3052";//信息公告
	public static final String MONEY_DIVDEND_CATALOG_ID="3053";//分红信息
	public static final String MONEY_LOWINFO_CATALOG_ID="3054";//法律文件
	
	public static final String RISK_OPTION_STATE_ON="1";//风险测评的状态
	public static final String RISK_OPTION_STATE_OFF="0";//风险测评的状态

	public static final String IS_SELF_INFO_YES="1";// 手动更新
	public static final String IS_SELF_INFO_NO="0"; //自动同步数据
	
	static
	{
		LIST_LOGIN_ACCOUNT_TYPE.add("0");
		LIST_LOGIN_ACCOUNT_TYPE.add("1");
		LIST_LOGIN_ACCOUNT_TYPE.add("2");
		LIST_LOGIN_ACCOUNT_TYPE.add("3");
		LIST_LOGIN_ACCOUNT_TYPE.add("4");
		LIST_LOGIN_ACCOUNT_TYPE.add("5");
		LIST_LOGIN_ACCOUNT_TYPE.add("6");
		LIST_LOGIN_ACCOUNT_TYPE.add("7");
		LIST_LOGIN_ACCOUNT_TYPE.add("8");
	}
}

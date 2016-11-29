package com.thinkive.mall.constant;

import java.util.ArrayList;

public class BaseConstants
{

	//广告文件类别
	public static final String AD_FILE_TYPE_AD_FILE_TYPE_FLASH = "0"; //广告文件类型-flash
	public static final String AD_FILE_TYPE_AD_FILE_TYPE_IMG = "1"; //广告文件类型-img

	//广告类别
	public static final String AD_TYPE_AD_TYPE_NORMAL = "0"; //广告类型-普通
	public static final String AD_TYPE_AD_TYPE_CODE = "1"; //广告类型-代码

	//协议类型
	public static final String AGREEMENT_TXT = "1"; //文本
	public static final String AGREEMENT_ACCESSORY = "0"; //附件

	//文章类型
	public static final String ARTICLE_TYPE_FINA_DIVIDEND = "4"; //理财-分红信息
	public static final String ARTICLE_TYPE_FINA_LOW_FILE = "5"; //理财-法律文件
	public static final String ARTICLE_TYPE_FINA_NOTICE = "3"; //理财-信息公告
	public static final String ARTICLE_TYPE_FINA_JOIN_GUIDE = "2"; //理财-参与指南
	public static final String ARTICLE_TYPE_FUND_DIVIDEND = "1"; //基金分红
	public static final String ARTICLE_TYPE_FUND_NOTICE = "0"; //基金公告

	//标准布尔
	public static final String BOOL_FALSE = "0"; //否
	public static final String BOOL_TRUE = "1"; //是
	
	//用户资料状态
	public static final String INIT_STATE = "0"; //初始
	public static final String CAN_USE_STATE = "1"; //成功保存
	public static final String FAIL_STATE = "2"; //失败
	public static final String SUCCESS_STATE = "3"; //开户成功
	public static final String DEAL_STATE = "4"; //开户成功

	//交易业务类型
	public static final String BUSINESS_TYPE_REDEMPTION = "2"; //赎回
	public static final String BUSINESS_TYPE_SELL = "4"; //卖出
	public static final String BUSINESS_TYPE_PURCHASE = "1"; //申购
	public static final String BUSINESS_TYPE_SUBSCRIBE = "0"; //认购
	public static final String BUSINESS_TYPE_BUY = "3"; //买入

	//证件类型
	public static final String CARD_TYPE_ID_CARD = "0"; //身份证
	public static final String CARD_TYPE_HK_MACAO_PASS = "1"; //港澳通行证

	//统计类型
	public static final String COUNT_TYPE_TOT_FOCUS = "1"; //关注量
	public static final String COUNT_TYPE_TOT_SALE = "0"; //销售量

	//成交状态
	public static final String DEAL_STATE_NO = "0"; //未成交
	public static final String DEAL_STATE_ALREADY = "1"; //已成交

	//委托状态
	public static final String ENTRUST_STATE_FAIL = "2"; //委托失败
	public static final String ENTRUST_STATE_NO = "0"; //未委托
	public static final String ENTRUST_STATE_SUCCESS = "1"; //委托成功

	//账户类型
	public static final String EXTERNAL_ACCOUNTS_FUND_ACCOUNT = "0"; //资金账号
	public static final String EXTERNAL_ACCOUNTS_CHINA_BANK = "1"; //中国银行理财账号
	public static final String EXTERNAL_ACCOUNTS_CIB_BANK = "3"; //兴业银行理财账号
	public static final String EXTERNAL_ACCOUNTS_ICBC_BANK = "2"; //工商银行理财账号

	//基金类别
	public static final String FUND_TYPE_STOCK = "1"; //股票型
	public static final String FUND_TYPE_MONEY = "2"; //货币型
	public static final String FUND_TYPE_PARTIAL_STOCK = "3"; //偏股型
	public static final String FUND_TYPE_STOCK_BOND = "4"; //股债平衡型
	public static final String FUND_TYPE_PARTIAL_DEBT = "5"; //偏债型
	public static final String FUND_TYPE_BOND = "6"; //债券型
	public static final String FUND_TYPE_PRESERVATION = "7"; //保本型
	public static final String FUND_TYPE_INDEX = "8"; //指数型
	public static final String FUND_TYPE_SHORT_DEBT = "9"; //短债型

	//图片类别
	public static final String IMG_TYPE_PRODUCT_M = "5"; //产品中图
	public static final String IMG_TYPE_PRODUCT_L = "4"; //产品大图
	public static final String IMG_TYPE_PRODUCT_S = "3"; //产品小图
	public static final String IMG_TYPE_LOGO = "2"; //logo
	public static final String IMG_TYPE_HEAD = "1"; //用户头像

	//资讯类别
	public static final String INFO_TYPE_MARKETING_INFORMATION = "0"; //营销资讯

	//日志类型
	public static final String LOG_TYPE_LOGIN = "0"; //登录
	public static final String LOG_TYPE_USER_OPERATE_REGISTER = "1"; //注册
	public static final String LOG_TYPE_SUB_ACCOUNT_ADD = "2"; //子账号添加
	public static final String LOG_TYPE_USER_OPERATE_UPDATE = "6"; //用户信息编辑
	public static final String LOG_TYPE_PWD_ANSWER_ADD = "4"; //密码答案添加
	public static final String LOG_TYPE_RISK_RECORD_ADD = "5"; //风险测评答案记录添加
	public static final String LOG_TYPE_BANK_CARD_ADD = "3"; //银行卡加挂

	//消息类型
	public static final String MESSAGE_TYPE_DETAILED_CONTENT = "0"; //详细内容

	//币种
	public static final String MONEY_TYPE_RMB = "0"; //人民币
	public static final String MONEY_TYPE_DOLLAR = "1"; //美元

	//交易操作类型
	public static final String OPERATING_TYPE_NO = "0"; //无操作
	public static final String OPERATING_TYPE_CANCEL_PAY = "12"; //可取消可支付
	public static final String OPERATING_TYPE_SUBMIT_REVOKE = "4"; //已执行撤单操作
	public static final String OPERATING_TYPE_REVOKE = "3"; //可撤单
	public static final String OPERATING_TYPE_PAY = "2"; //可支付
	public static final String OPERATING_TYPE_CANCEL = "1"; //可取消
	
	//支付方式
	public static final String PAY_MONEY_BY_BANK = "1"; //银行卡购买
	public static final String PAY_MONEY_BY_ACCOUNT = "0"; //保证金购买

	//订单状态
	public static final String ORDER_TYPE_REVOKE = "6"; //已撤销
	public static final String ORDER_TYPE_NEW = "0"; //新建
	public static final String ORDER_TYPE_FAIL = "3"; //失败
	public static final String ORDER_TYPE_SUCCESS = "4"; //成交
	public static final String ORDER_TYPE_CANCEL = "5"; //已取消
	public static final String ORDER_TYPE_SUBMIT_SUCCESS = "2"; //提交成功
	public static final String ORDER_TYPE_SUBMIT = "1"; //待提交
	public static final String ORDER_TYPE_REFUND = "7"; //退款

	//支付途径
	public static final String PAY_WAY_MARGIN = "0"; //保证金
	public static final String PAY_WAY_QQPAY = "3"; //财富通
	public static final String PAY_WAY_ALIPAY = "2"; //支付宝
	public static final String PAY_WAY_UNIONPAY = "1"; //银联

	//推荐人群
	public static final String PERSION_ELDERLY = "1"; //老年
	public static final String PERSION_YOUTH = "0"; //青年

	//收费类型
	public static final String POUNDAGE_TYPE_BACK_END = "1"; //后端收费
	public static final String POUNDAGE_TYPE_FRONT_END = "0"; //前端收费

	//产品风险等级
	public static final String PRODUCT_RISK_LEVEL_HIGH = "5"; //高风险等级
	public static final String PRODUCT_RISK_LEVEL_MIDDLE_HIGH = "4"; //中高风险等级
	public static final String PRODUCT_RISK_LEVEL_LOW = "1"; //低风险等级
	public static final String PRODUCT_RISK_LEVEL_MIDDLE = "3"; //中风险等级
	public static final String PRODUCT_RISK_LEVEL_DEFAULT = "0"; //默认风险等级
	public static final String PRODUCT_RISK_LEVEL_MIDDLE_LOW = "2"; //中低风险等级

	//产品状态
	public static final String PRODUCT_STUTAS_JJZZ = "6"; //基金终止
	public static final String PRODUCT_STUTAS_QYDJ = "7"; //权益登记
	public static final String PRODUCT_STUTAS_TZJY = "5"; //停止交易
	public static final String PRODUCT_STUTAS_FBQ = "4"; //封闭期
	public static final String PRODUCT_STUTAS_TZSG = "3"; //停止申购
	public static final String PRODUCT_STUTAS_RGSQ = "1"; //认购时期
	public static final String PRODUCT_STUTAS_ZCKF = "0"; //正常开放
	public static final String PRODUCT_STUTAS_TZSH = "2"; //停止赎回

	//产品子类别
	public static final String PRODUCT_SUB_TYPE_FUND = "0"; //基金
	public static final String PRODUCT_SUB_TYPE_FINANCIAL = "1"; //理财
	public static final String PRODUCT_SUB_TYPE_SERVER = "2"; //服务
	public static final String PRODUCT_SUB_TYPE_INFOR = "3"; //资讯

	//产品类型
	public static final String PRODUCT_TYPE_NON_FINANCIAL  = "0"; //非金融产品
	public static final String PRODUCT_TYPE_FINANCIAL = "1"; //金融产品

	//产品上下架类型
	public static final String PROUDUCT_SHELF_OFF = "0"; //下架
	public static final String PROUDUCT_SHELF_ON = "1"; //上架

	//问题类型
	public static final String QUESTION_TYPE_SUBJECTIVE_TOPIC = "1"; //主观题
	public static final String QUESTION_TYPE_MULTIPLE_CHOICE = "0"; //选择题

	//风险等级
	public static final String RISK_LEVEL_RADICAL = "1"; //激进型
	public static final String RISK_LEVEL_CONSERVATIVE = "0"; //保守型

	//风险测评类型
	public static final String RISK_TYPE_FUND = "0"; //基金

	//周期单位
	public static final String RULES_UNIT_YEAR = "3"; //年
	public static final String RULES_UNIT_MONTH = "1"; //月
	public static final String RULES_UNIT_DAY = "0"; //天
	public static final String RULES_UNIT_SEASON = "2"; //季

	//推荐类型
	public static final String RECOMMEND_TYPE_BUY = "2"; //购买推荐
	public static final String RECOMMEND_TYPE_INDEX = "1"; //首页推荐

	//服务方式
	public static final String SERVICE_TYPE_MMS = "5"; //彩信
	public static final String SERVICE_TYPE_YOUXIANJI = "4"; //优先级
	public static final String SERVICE_TYPE_IY34 = "3,4"; //IM优先级
	public static final String SERVICE_TYPE_PHONE = "0"; //电话
	public static final String SERVICE_TYPE_EMAIL = "1"; //邮件
	public static final String SERVICE_TYPE_SMS = "2"; //短信
	public static final String SERVICE_TYPE_IM = "3"; //IM
	public static final String SERVICE_TYPE_YI43 = "4,3"; //优先级IM

	//性别
	public static final String SEX_SEX_UNKNOWN = "2"; //未知
	public static final String SEX_MAN = "0"; //男
	public static final String SEX_WOMAN = "1"; //女

	//用户来源
	public static final String USER_SOURCE_PC = "0"; //PC
	public static final String USER_SOURCE_IOS = "1"; //IOS
	public static final String USER_SOURCE_ANDROID = "2"; //ANDROID

	//用户类型
	public static final String USER_TYPE_INSTITUTIONAL = "1"; //机构用户
	public static final String USER_TYPE_PERSONAL  = "0"; //个人用户

	//会员等级
	public static final String VIP_LEVEL_MEMBERS_1 = "1"; //1级会员
	public static final String VIP_LEVEL_MEMBERS = "0"; //普通会员

	//关注类型
	public static final String FOLLOW_TYPE_USER_SHARE = "1"; //用户分享
	public static final String FOLLOW_TYPE_USER_FOLLOW = "0"; //用户关注
	public static ArrayList<String> LIST_AD_FILE_TYPE = new ArrayList<String>();
	public static ArrayList<String> LIST_AD_TYPE = new ArrayList<String>();
	public static ArrayList<String> LIST_AGREEMENT = new ArrayList<String>();
	public static ArrayList<String> LIST_ARTICLE_TYPE = new ArrayList<String>();
	public static ArrayList<String> LIST_BOOL = new ArrayList<String>();
	public static ArrayList<String> LIST_BUSINESS_TYPE = new ArrayList<String>();
	public static ArrayList<String> LIST_CARD_TYPE = new ArrayList<String>();
	public static ArrayList<String> LIST_COUNT_TYPE = new ArrayList<String>();
	public static ArrayList<String> LIST_DEAL_STATE = new ArrayList<String>();
	public static ArrayList<String> LIST_ENTRUST_STATE = new ArrayList<String>();
	public static ArrayList<String> LIST_EXTERNAL_ACCOUNTS = new ArrayList<String>();
	public static ArrayList<String> LIST_FUND_TYPE = new ArrayList<String>();
	public static ArrayList<String> LIST_IMG_TYPE = new ArrayList<String>();
	public static ArrayList<String> LIST_INFO_TYPE = new ArrayList<String>();
	public static ArrayList<String> LIST_LOG_TYPE = new ArrayList<String>();
	public static ArrayList<String> LIST_MESSAGE_TYPE = new ArrayList<String>();
	public static ArrayList<String> LIST_MONEY_TYPE = new ArrayList<String>();
	public static ArrayList<String> LIST_OPERATING_TYPE = new ArrayList<String>();
	public static ArrayList<String> LIST_ORDER_TYPE = new ArrayList<String>();
	public static ArrayList<String> LIST_PAY_WAY = new ArrayList<String>();
	public static ArrayList<String> LIST_PERSION = new ArrayList<String>();
	public static ArrayList<String> LIST_POUNDAGE_TYPE = new ArrayList<String>();
	public static ArrayList<String> LIST_PRODUCT_RISK_LEVEL = new ArrayList<String>();
	public static ArrayList<String> LIST_PRODUCT_STUTAS = new ArrayList<String>();
	public static ArrayList<String> LIST_PRODUCT_SUB_TYPE = new ArrayList<String>();
	public static ArrayList<String> LIST_PRODUCT_TYPE = new ArrayList<String>();
	public static ArrayList<String> LIST_PROUDUCT_SHELF = new ArrayList<String>();
	public static ArrayList<String> LIST_QUESTION_TYPE = new ArrayList<String>();
	public static ArrayList<String> LIST_RISK_LEVEL = new ArrayList<String>();
	public static ArrayList<String> LIST_RISK_TYPE = new ArrayList<String>();
	public static ArrayList<String> LIST_RULES_UNIT = new ArrayList<String>();
	public static ArrayList<String> LIST_Recommend_type = new ArrayList<String>();
	public static ArrayList<String> LIST_SERVICE_TYPE = new ArrayList<String>();
	public static ArrayList<String> LIST_SEX = new ArrayList<String>();
	public static ArrayList<String> LIST_USER_SOURCE = new ArrayList<String>();
	public static ArrayList<String> LIST_USER_TYPE = new ArrayList<String>();
	public static ArrayList<String> LIST_VIP_LEVEL = new ArrayList<String>();
	public static ArrayList<String> LIST_follow_type = new ArrayList<String>();

	static
	{
		LIST_AD_FILE_TYPE.add("0");
		LIST_AD_FILE_TYPE.add("1");
		LIST_AD_TYPE.add("0");
		LIST_AD_TYPE.add("1");
		LIST_AGREEMENT.add("1");
		LIST_AGREEMENT.add("0");
		LIST_ARTICLE_TYPE.add("4");
		LIST_ARTICLE_TYPE.add("5");
		LIST_ARTICLE_TYPE.add("3");
		LIST_ARTICLE_TYPE.add("2");
		LIST_ARTICLE_TYPE.add("1");
		LIST_ARTICLE_TYPE.add("0");
		LIST_BOOL.add("0");
		LIST_BOOL.add("1");
		LIST_BUSINESS_TYPE.add("2");
		LIST_BUSINESS_TYPE.add("4");
		LIST_BUSINESS_TYPE.add("1");
		LIST_BUSINESS_TYPE.add("0");
		LIST_BUSINESS_TYPE.add("3");
		LIST_CARD_TYPE.add("0");
		LIST_CARD_TYPE.add("1");
		LIST_COUNT_TYPE.add("1");
		LIST_COUNT_TYPE.add("0");
		LIST_DEAL_STATE.add("0");
		LIST_DEAL_STATE.add("1");
		LIST_ENTRUST_STATE.add("2");
		LIST_ENTRUST_STATE.add("0");
		LIST_ENTRUST_STATE.add("1");
		LIST_EXTERNAL_ACCOUNTS.add("0");
		LIST_EXTERNAL_ACCOUNTS.add("1");
		LIST_EXTERNAL_ACCOUNTS.add("3");
		LIST_EXTERNAL_ACCOUNTS.add("2");
		LIST_FUND_TYPE.add("1");
		LIST_FUND_TYPE.add("2");
		LIST_FUND_TYPE.add("3");
		LIST_FUND_TYPE.add("4");
		LIST_FUND_TYPE.add("5");
		LIST_FUND_TYPE.add("6");
		LIST_FUND_TYPE.add("7");
		LIST_FUND_TYPE.add("8");
		LIST_FUND_TYPE.add("9");
		LIST_IMG_TYPE.add("5");
		LIST_IMG_TYPE.add("4");
		LIST_IMG_TYPE.add("3");
		LIST_IMG_TYPE.add("2");
		LIST_IMG_TYPE.add("1");
		LIST_INFO_TYPE.add("0");
		LIST_LOG_TYPE.add("0");
		LIST_LOG_TYPE.add("1");
		LIST_LOG_TYPE.add("2");
		LIST_LOG_TYPE.add("6");
		LIST_LOG_TYPE.add("4");
		LIST_LOG_TYPE.add("5");
		LIST_LOG_TYPE.add("3");
		LIST_MESSAGE_TYPE.add("0");
		LIST_MONEY_TYPE.add("0");
		LIST_MONEY_TYPE.add("1");
		LIST_OPERATING_TYPE.add("0");
		LIST_OPERATING_TYPE.add("12");
		LIST_OPERATING_TYPE.add("4");
		LIST_OPERATING_TYPE.add("3");
		LIST_OPERATING_TYPE.add("2");
		LIST_OPERATING_TYPE.add("1");
		LIST_ORDER_TYPE.add("6");
		LIST_ORDER_TYPE.add("0");
		LIST_ORDER_TYPE.add("3");
		LIST_ORDER_TYPE.add("4");
		LIST_ORDER_TYPE.add("5");
		LIST_ORDER_TYPE.add("2");
		LIST_ORDER_TYPE.add("1");
		LIST_ORDER_TYPE.add("7");
		LIST_PAY_WAY.add("0");
		LIST_PAY_WAY.add("3");
		LIST_PAY_WAY.add("2");
		LIST_PAY_WAY.add("1");
		LIST_PERSION.add("1");
		LIST_PERSION.add("0");
		LIST_POUNDAGE_TYPE.add("1");
		LIST_POUNDAGE_TYPE.add("0");
		LIST_PRODUCT_RISK_LEVEL.add("5");
		LIST_PRODUCT_RISK_LEVEL.add("4");
		LIST_PRODUCT_RISK_LEVEL.add("1");
		LIST_PRODUCT_RISK_LEVEL.add("3");
		LIST_PRODUCT_RISK_LEVEL.add("0");
		LIST_PRODUCT_RISK_LEVEL.add("2");
		LIST_PRODUCT_STUTAS.add("6");
		LIST_PRODUCT_STUTAS.add("7");
		LIST_PRODUCT_STUTAS.add("5");
		LIST_PRODUCT_STUTAS.add("4");
		LIST_PRODUCT_STUTAS.add("3");
		LIST_PRODUCT_STUTAS.add("1");
		LIST_PRODUCT_STUTAS.add("0");
		LIST_PRODUCT_STUTAS.add("2");
		LIST_PRODUCT_SUB_TYPE.add("0");
		LIST_PRODUCT_SUB_TYPE.add("1");
		LIST_PRODUCT_SUB_TYPE.add("2");
		LIST_PRODUCT_SUB_TYPE.add("3");
		LIST_PRODUCT_TYPE.add("0");
		LIST_PRODUCT_TYPE.add("1");
		LIST_PROUDUCT_SHELF.add("0");
		LIST_PROUDUCT_SHELF.add("1");
		LIST_QUESTION_TYPE.add("1");
		LIST_QUESTION_TYPE.add("0");
		LIST_RISK_LEVEL.add("1");
		LIST_RISK_LEVEL.add("0");
		LIST_RISK_TYPE.add("0");
		LIST_RULES_UNIT.add("3");
		LIST_RULES_UNIT.add("1");
		LIST_RULES_UNIT.add("0");
		LIST_RULES_UNIT.add("2");
		LIST_Recommend_type.add("2");
		LIST_Recommend_type.add("1");
		LIST_SERVICE_TYPE.add("5");
		LIST_SERVICE_TYPE.add("4");
		LIST_SERVICE_TYPE.add("3,4");
		LIST_SERVICE_TYPE.add("0");
		LIST_SERVICE_TYPE.add("1");
		LIST_SERVICE_TYPE.add("2");
		LIST_SERVICE_TYPE.add("3");
		LIST_SERVICE_TYPE.add("4,3");
		LIST_SEX.add("2");
		LIST_SEX.add("0");
		LIST_SEX.add("1");
		LIST_USER_SOURCE.add("0");
		LIST_USER_SOURCE.add("1");
		LIST_USER_SOURCE.add("2");
		LIST_USER_TYPE.add("1");
		LIST_USER_TYPE.add("0");
		LIST_VIP_LEVEL.add("1");
		LIST_VIP_LEVEL.add("0");
		LIST_follow_type.add("1");
		LIST_follow_type.add("0");
	}
}

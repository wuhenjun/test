package com.thinkive.mall.constant;

import java.util.ArrayList;

public class BaseConstants
{

	//����ļ����
	public static final String AD_FILE_TYPE_AD_FILE_TYPE_FLASH = "0"; //����ļ�����-flash
	public static final String AD_FILE_TYPE_AD_FILE_TYPE_IMG = "1"; //����ļ�����-img

	//������
	public static final String AD_TYPE_AD_TYPE_NORMAL = "0"; //�������-��ͨ
	public static final String AD_TYPE_AD_TYPE_CODE = "1"; //�������-����

	//Э������
	public static final String AGREEMENT_TXT = "1"; //�ı�
	public static final String AGREEMENT_ACCESSORY = "0"; //����

	//��������
	public static final String ARTICLE_TYPE_FINA_DIVIDEND = "4"; //���-�ֺ���Ϣ
	public static final String ARTICLE_TYPE_FINA_LOW_FILE = "5"; //���-�����ļ�
	public static final String ARTICLE_TYPE_FINA_NOTICE = "3"; //���-��Ϣ����
	public static final String ARTICLE_TYPE_FINA_JOIN_GUIDE = "2"; //���-����ָ��
	public static final String ARTICLE_TYPE_FUND_DIVIDEND = "1"; //����ֺ�
	public static final String ARTICLE_TYPE_FUND_NOTICE = "0"; //���𹫸�

	//��׼����
	public static final String BOOL_FALSE = "0"; //��
	public static final String BOOL_TRUE = "1"; //��
	
	//�û�����״̬
	public static final String INIT_STATE = "0"; //��ʼ
	public static final String CAN_USE_STATE = "1"; //�ɹ�����
	public static final String FAIL_STATE = "2"; //ʧ��
	public static final String SUCCESS_STATE = "3"; //�����ɹ�
	public static final String DEAL_STATE = "4"; //�����ɹ�

	//����ҵ������
	public static final String BUSINESS_TYPE_REDEMPTION = "2"; //���
	public static final String BUSINESS_TYPE_SELL = "4"; //����
	public static final String BUSINESS_TYPE_PURCHASE = "1"; //�깺
	public static final String BUSINESS_TYPE_SUBSCRIBE = "0"; //�Ϲ�
	public static final String BUSINESS_TYPE_BUY = "3"; //����

	//֤������
	public static final String CARD_TYPE_ID_CARD = "0"; //���֤
	public static final String CARD_TYPE_HK_MACAO_PASS = "1"; //�۰�ͨ��֤

	//ͳ������
	public static final String COUNT_TYPE_TOT_FOCUS = "1"; //��ע��
	public static final String COUNT_TYPE_TOT_SALE = "0"; //������

	//�ɽ�״̬
	public static final String DEAL_STATE_NO = "0"; //δ�ɽ�
	public static final String DEAL_STATE_ALREADY = "1"; //�ѳɽ�

	//ί��״̬
	public static final String ENTRUST_STATE_FAIL = "2"; //ί��ʧ��
	public static final String ENTRUST_STATE_NO = "0"; //δί��
	public static final String ENTRUST_STATE_SUCCESS = "1"; //ί�гɹ�

	//�˻�����
	public static final String EXTERNAL_ACCOUNTS_FUND_ACCOUNT = "0"; //�ʽ��˺�
	public static final String EXTERNAL_ACCOUNTS_CHINA_BANK = "1"; //�й���������˺�
	public static final String EXTERNAL_ACCOUNTS_CIB_BANK = "3"; //��ҵ��������˺�
	public static final String EXTERNAL_ACCOUNTS_ICBC_BANK = "2"; //������������˺�

	//�������
	public static final String FUND_TYPE_STOCK = "1"; //��Ʊ��
	public static final String FUND_TYPE_MONEY = "2"; //������
	public static final String FUND_TYPE_PARTIAL_STOCK = "3"; //ƫ����
	public static final String FUND_TYPE_STOCK_BOND = "4"; //��ծƽ����
	public static final String FUND_TYPE_PARTIAL_DEBT = "5"; //ƫծ��
	public static final String FUND_TYPE_BOND = "6"; //ծȯ��
	public static final String FUND_TYPE_PRESERVATION = "7"; //������
	public static final String FUND_TYPE_INDEX = "8"; //ָ����
	public static final String FUND_TYPE_SHORT_DEBT = "9"; //��ծ��

	//ͼƬ���
	public static final String IMG_TYPE_PRODUCT_M = "5"; //��Ʒ��ͼ
	public static final String IMG_TYPE_PRODUCT_L = "4"; //��Ʒ��ͼ
	public static final String IMG_TYPE_PRODUCT_S = "3"; //��ƷСͼ
	public static final String IMG_TYPE_LOGO = "2"; //logo
	public static final String IMG_TYPE_HEAD = "1"; //�û�ͷ��

	//��Ѷ���
	public static final String INFO_TYPE_MARKETING_INFORMATION = "0"; //Ӫ����Ѷ

	//��־����
	public static final String LOG_TYPE_LOGIN = "0"; //��¼
	public static final String LOG_TYPE_USER_OPERATE_REGISTER = "1"; //ע��
	public static final String LOG_TYPE_SUB_ACCOUNT_ADD = "2"; //���˺����
	public static final String LOG_TYPE_USER_OPERATE_UPDATE = "6"; //�û���Ϣ�༭
	public static final String LOG_TYPE_PWD_ANSWER_ADD = "4"; //��������
	public static final String LOG_TYPE_RISK_RECORD_ADD = "5"; //���ղ����𰸼�¼���
	public static final String LOG_TYPE_BANK_CARD_ADD = "3"; //���п��ӹ�

	//��Ϣ����
	public static final String MESSAGE_TYPE_DETAILED_CONTENT = "0"; //��ϸ����

	//����
	public static final String MONEY_TYPE_RMB = "0"; //�����
	public static final String MONEY_TYPE_DOLLAR = "1"; //��Ԫ

	//���ײ�������
	public static final String OPERATING_TYPE_NO = "0"; //�޲���
	public static final String OPERATING_TYPE_CANCEL_PAY = "12"; //��ȡ����֧��
	public static final String OPERATING_TYPE_SUBMIT_REVOKE = "4"; //��ִ�г�������
	public static final String OPERATING_TYPE_REVOKE = "3"; //�ɳ���
	public static final String OPERATING_TYPE_PAY = "2"; //��֧��
	public static final String OPERATING_TYPE_CANCEL = "1"; //��ȡ��
	
	//֧����ʽ
	public static final String PAY_MONEY_BY_BANK = "1"; //���п�����
	public static final String PAY_MONEY_BY_ACCOUNT = "0"; //��֤����

	//����״̬
	public static final String ORDER_TYPE_REVOKE = "6"; //�ѳ���
	public static final String ORDER_TYPE_NEW = "0"; //�½�
	public static final String ORDER_TYPE_FAIL = "3"; //ʧ��
	public static final String ORDER_TYPE_SUCCESS = "4"; //�ɽ�
	public static final String ORDER_TYPE_CANCEL = "5"; //��ȡ��
	public static final String ORDER_TYPE_SUBMIT_SUCCESS = "2"; //�ύ�ɹ�
	public static final String ORDER_TYPE_SUBMIT = "1"; //���ύ
	public static final String ORDER_TYPE_REFUND = "7"; //�˿�

	//֧��;��
	public static final String PAY_WAY_MARGIN = "0"; //��֤��
	public static final String PAY_WAY_QQPAY = "3"; //�Ƹ�ͨ
	public static final String PAY_WAY_ALIPAY = "2"; //֧����
	public static final String PAY_WAY_UNIONPAY = "1"; //����

	//�Ƽ���Ⱥ
	public static final String PERSION_ELDERLY = "1"; //����
	public static final String PERSION_YOUTH = "0"; //����

	//�շ�����
	public static final String POUNDAGE_TYPE_BACK_END = "1"; //����շ�
	public static final String POUNDAGE_TYPE_FRONT_END = "0"; //ǰ���շ�

	//��Ʒ���յȼ�
	public static final String PRODUCT_RISK_LEVEL_HIGH = "5"; //�߷��յȼ�
	public static final String PRODUCT_RISK_LEVEL_MIDDLE_HIGH = "4"; //�и߷��յȼ�
	public static final String PRODUCT_RISK_LEVEL_LOW = "1"; //�ͷ��յȼ�
	public static final String PRODUCT_RISK_LEVEL_MIDDLE = "3"; //�з��յȼ�
	public static final String PRODUCT_RISK_LEVEL_DEFAULT = "0"; //Ĭ�Ϸ��յȼ�
	public static final String PRODUCT_RISK_LEVEL_MIDDLE_LOW = "2"; //�еͷ��յȼ�

	//��Ʒ״̬
	public static final String PRODUCT_STUTAS_JJZZ = "6"; //������ֹ
	public static final String PRODUCT_STUTAS_QYDJ = "7"; //Ȩ��Ǽ�
	public static final String PRODUCT_STUTAS_TZJY = "5"; //ֹͣ����
	public static final String PRODUCT_STUTAS_FBQ = "4"; //�����
	public static final String PRODUCT_STUTAS_TZSG = "3"; //ֹͣ�깺
	public static final String PRODUCT_STUTAS_RGSQ = "1"; //�Ϲ�ʱ��
	public static final String PRODUCT_STUTAS_ZCKF = "0"; //��������
	public static final String PRODUCT_STUTAS_TZSH = "2"; //ֹͣ���

	//��Ʒ�����
	public static final String PRODUCT_SUB_TYPE_FUND = "0"; //����
	public static final String PRODUCT_SUB_TYPE_FINANCIAL = "1"; //���
	public static final String PRODUCT_SUB_TYPE_SERVER = "2"; //����
	public static final String PRODUCT_SUB_TYPE_INFOR = "3"; //��Ѷ

	//��Ʒ����
	public static final String PRODUCT_TYPE_NON_FINANCIAL  = "0"; //�ǽ��ڲ�Ʒ
	public static final String PRODUCT_TYPE_FINANCIAL = "1"; //���ڲ�Ʒ

	//��Ʒ���¼�����
	public static final String PROUDUCT_SHELF_OFF = "0"; //�¼�
	public static final String PROUDUCT_SHELF_ON = "1"; //�ϼ�

	//��������
	public static final String QUESTION_TYPE_SUBJECTIVE_TOPIC = "1"; //������
	public static final String QUESTION_TYPE_MULTIPLE_CHOICE = "0"; //ѡ����

	//���յȼ�
	public static final String RISK_LEVEL_RADICAL = "1"; //������
	public static final String RISK_LEVEL_CONSERVATIVE = "0"; //������

	//���ղ�������
	public static final String RISK_TYPE_FUND = "0"; //����

	//���ڵ�λ
	public static final String RULES_UNIT_YEAR = "3"; //��
	public static final String RULES_UNIT_MONTH = "1"; //��
	public static final String RULES_UNIT_DAY = "0"; //��
	public static final String RULES_UNIT_SEASON = "2"; //��

	//�Ƽ�����
	public static final String RECOMMEND_TYPE_BUY = "2"; //�����Ƽ�
	public static final String RECOMMEND_TYPE_INDEX = "1"; //��ҳ�Ƽ�

	//����ʽ
	public static final String SERVICE_TYPE_MMS = "5"; //����
	public static final String SERVICE_TYPE_YOUXIANJI = "4"; //���ȼ�
	public static final String SERVICE_TYPE_IY34 = "3,4"; //IM���ȼ�
	public static final String SERVICE_TYPE_PHONE = "0"; //�绰
	public static final String SERVICE_TYPE_EMAIL = "1"; //�ʼ�
	public static final String SERVICE_TYPE_SMS = "2"; //����
	public static final String SERVICE_TYPE_IM = "3"; //IM
	public static final String SERVICE_TYPE_YI43 = "4,3"; //���ȼ�IM

	//�Ա�
	public static final String SEX_SEX_UNKNOWN = "2"; //δ֪
	public static final String SEX_MAN = "0"; //��
	public static final String SEX_WOMAN = "1"; //Ů

	//�û���Դ
	public static final String USER_SOURCE_PC = "0"; //PC
	public static final String USER_SOURCE_IOS = "1"; //IOS
	public static final String USER_SOURCE_ANDROID = "2"; //ANDROID

	//�û�����
	public static final String USER_TYPE_INSTITUTIONAL = "1"; //�����û�
	public static final String USER_TYPE_PERSONAL  = "0"; //�����û�

	//��Ա�ȼ�
	public static final String VIP_LEVEL_MEMBERS_1 = "1"; //1����Ա
	public static final String VIP_LEVEL_MEMBERS = "0"; //��ͨ��Ա

	//��ע����
	public static final String FOLLOW_TYPE_USER_SHARE = "1"; //�û�����
	public static final String FOLLOW_TYPE_USER_FOLLOW = "0"; //�û���ע
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

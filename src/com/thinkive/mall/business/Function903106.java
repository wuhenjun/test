package com.thinkive.mall.business;

import java.util.Arrays;
import java.util.Date;

import org.apache.log4j.Logger;

import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.service.SequenceGenerator;
import com.thinkive.base.util.DateHelper;
import com.thinkive.base.util.RandomHelper;
import com.thinkive.base.util.StringHelper;
import com.thinkive.base.util.security.SHA512;
import com.thinkive.mall.crm.SmsSender;
import com.thinkive.mall.service.ReserveService;
import com.thinkive.mall.service.UserService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;

/**
 * ����: �����ɺ� ���Ͷ���
 */

public class Function903106 extends BaseFunction
{
    private static final Logger logger = Logger.getLogger(Function903106.class);
	@Override
	public ResultVo execute() throws InvokeException, Exception
	{
		ResultVo resultVo = new ResultVo();
			try {
				String id = getStrParameter("id");
				logger.info("111111111111111��ȡ��idΪ"+id);
				 String[] ids= id.split(",");
				 logger.info("11111111111111��ȡ��id����Ϊ"+Arrays.toString(ids));
				 ReserveService service = new ReserveService();
				 for(int i=0;i<ids.length;i++)
				 {
				     String  user_id = ids[i];
				     DataRow user = service.findUserById(user_id);//��ѯ����״̬Ϊ1���û�
				     if(user != null)
				     {
				         String reserve_price = user.getString("reserve_price");
				         String product_code = user.getString("product_code");//��Ʒcode
				         String product_name  = user.getString("product_name");
				         String fund_account = user.getString("fund_account");
				         String mobile_phone = user.getString("mobile_phone");
				         String check_time = user.getString("check_time");
				         int num = Integer.parseInt(reserve_price)/10000;
				         //��ʼ����ʱ��
				         //����code��ѯ����Ʒ��Ϣ�Ϳ�ʼ����ʱ�䣬ִ�з�����
				         DataRow product = service.findProductByCode(product_code);
				         String start_buy_date = product.getString("start_buy_date");
				         
				         String right_end_date = product.getString("right_end_date");
				          
				         String message = "������"+check_time+"�ɹ�ԤԼ����˾����8��27�ڼ����ʲ������ƻ�"+num+"��Ԫ�Ĺ�����,�ò�Ʒ����"+formatDate(start_buy_date)+"09:30ͨ����ԭ֤ȯ��������ʼ����,�������,�ȵ��ȵá�������ǰ����׼��������ѯӪҵ��������Ա";
				        
				         //���Ͷ��Ŷ���
				         logger.info("1111111111111111111�༭�Ķ�������Ϊ"+message);
				         logger.info("1111111111111111111�ֻ�����Ϊ:"+mobile_phone);
				       
		                  new SmsSender(mobile_phone, message).run();
				         
				     }
				     else
				     {
				         //����Ա��δͨ����˵��û�Ҳ�����
				         //���ǲ���������û����Ͷ���
				     }
				     
				 }
				    resultVo.setErrorMsg("���ͳɹ�");
	               
				 
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				resultVo.setErrorMsg(e.getMessage());
				resultVo.setErrorNo(-3);
				e.printStackTrace();
			}
		return resultVo;
	}
	
	private String formatDate(String date){
	    String newDate = "";
	    String chars[] = date.split("-");
	    newDate = ""+chars[0]+"��"+chars[1]+"��"+chars[2]+"��";
	    return newDate;
	}
	
}
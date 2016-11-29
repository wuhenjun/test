package com.thinkive.mall.business;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.project.utils.Constants;
import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.service.SequenceGenerator;
import com.thinkive.base.util.DateHelper;
import com.thinkive.base.util.RandomHelper;
import com.thinkive.base.util.StringHelper;
import com.thinkive.base.util.security.SHA512;
import com.thinkive.mall.service.ReserveService;
import com.thinkive.mall.service.UserService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;

/**
 * 描述: 添加用户信息
 */

public class Function903107 extends BaseFunction
{
    private static final Logger logger = Logger.getLogger(Function903107.class);
	@Override
	public ResultVo execute() throws InvokeException, Exception
	{
		ResultVo resultVo = new ResultVo();
			try {
				String product_code = getStrParameter("product_code");
				String user_name = getStrParameter("user_name");
				String fund_account = getStrParameter("fund_account");
				String mobile_phone = getStrParameter("mobile_phone");
				String branch_no = getStrParameter("branch_no");
				String reserve_price = getStrParameter("reserve_price");
				String create_time = getStrParameter("create_time");
				String refe_name = getStrParameter("refe_name");//推荐人姓名
				int reserve_num = Integer.parseInt(reserve_price) * 10000;
				if(StringHelper.isEmpty(fund_account))
				{
				    throw new Exception("资金账号为空，不能进行添加");
				}
				ReserveService service = new ReserveService();
				/**
				 * 用户添加预约需要满足以下条件
				 * 1、预约的产品处于可预约状态
				 * 2、申请时间在预约期限范围内
				 * 3、预约金额不超过个人预约金额上限
				 * 4、不能重复添加
				 * 由于存在审核机制，所以不对预约金额超限控制(初次添加默认为未审核)
				 */
				
				
			
				
				
				 
				DataRow product = service.findProductByCode(product_code);
				if(product == null)
				{
				    throw new Exception("无可预约的产品，不能添加");
				}
				String start_reserve_date = product.getString("start_reserve_date").replace("-", "");
				String end_reserve_date = product.getString("end_reserve_date").replace("-", "");
			    String nowDate = DateHelper.formatDate(new Date(), "yyyy-MM-dd");
				String time = nowDate.replace("-", "");
				if(Integer.parseInt(time) < Integer.parseInt(start_reserve_date) || Integer.parseInt(time) > Integer.parseInt(end_reserve_date))
				{
				    throw new Exception("预约时间不在有效期内，不能添加");
				}
				
				DataRow user = service.findUserByFundAccount(fund_account,product_code);
				if(user != null)
				{
				    throw new Exception("该客户已申请预约了该产品，不能添加");
				}
				int user_max_reserve = product.getInt("user_max_reserve");
				if(reserve_num > user_max_reserve)
				{
				    throw new Exception("个人最大预约金额为"+user_max_reserve+"元，您的预约金额过大不能添加");
				}
				 
				     
				     DataRow newUser = new DataRow();
				     String id = SequenceGenerator.getInstance().getNextSequence(Constants.DB_ID, "t_mall_product_user_reserve");
				     newUser.set("id", id);
				     newUser.set("product_code", product_code);
				     newUser.set("user_name", user_name);
				     newUser.set("fund_account", fund_account);
				     newUser.set("mobile_phone", mobile_phone);
				     newUser.set("branch_no", branch_no);
				     newUser.set("reserve_price", reserve_num);
				     newUser.set("create_time", create_time);
				     newUser.set("source", "manager");
				     newUser.set("refe_name", refe_name);
				     newUser.set("check_state", "0");
				     newUser.set("send_msg_state", "0");
				     service.insertUser(newUser);
				     resultVo.setErrorMsg("操作成功");
				 
				     //此时，执行发送短信通知
				
				
				
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
	    newDate = ""+chars[0]+"年"+chars[1]+"月"+chars[2]+"日";
	    return newDate;
	}
	
}

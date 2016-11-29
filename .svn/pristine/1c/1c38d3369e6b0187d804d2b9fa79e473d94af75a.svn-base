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
 * 描述: 修改信息
 */

public class Function903108 extends BaseFunction
{
    private static final Logger logger = Logger.getLogger(Function903108.class);
	@Override
	public ResultVo execute() throws InvokeException, Exception
	{
		ResultVo resultVo = new ResultVo();
			try {
			    String id = getStrParameter("id");
				String product_code = getStrParameter("product_code");
				String user_name = getStrParameter("user_name");
				String fund_account = getStrParameter("fund_account");
				String mobile_phone = getStrParameter("mobile_phone");
				String branch_no = getStrParameter("branch_no");
				String reserve_price = getStrParameter("reserve_price");
				
				String refe_name = getStrParameter("refe_name");//推荐人姓名
				String check_state = getStrParameter("check_state");
				String check_time = getStrParameter("check_time");
				String update_time = getStrParameter("update_time");
				int reserve_num = Integer.parseInt(reserve_price) * 10000;
				if(StringHelper.isEmpty(fund_account))
				{
				    throw new Exception("资金账号为空，不能进行添加");
				}
				
				/**
				 * 
				 * 判断审核状态是否是已审核
				 *    如果不是已审核，则不进行预约金额的校验，直接进行修改
				 *    如果是已审核,则对预约金额进行校验，预约金额需要满足
				 *          本订单的预约金额+本订单以外所有通过审核的预约总金额  <= 该产品预约总金额
				 *              如果满足则进行修改，如果不满足，则提示错误信息
				 */
				ReserveService service = new ReserveService();
				boolean canModify = false;
				if("1".equals(check_state))
				{
				   
				    //查询除了本订单以外的所有审核通过的预约总金额
                    DataRow sum = service.sumOtherReservePrice(product_code,id);
                    int totReserve = sum.getInt("tot");
                    //查询该产品的预约总金额
                    DataRow product = service.findProductByCode(product_code);
                    int user_max_reserve = product.getInt("user_max_reserve");
                    if(reserve_num > user_max_reserve)
                    {
                        throw new Exception("个人最大预约金额为"+user_max_reserve+"元，您的预约金额过大不能添加");
                    }
                    int tot = product.getInt("tot_price");
                    
                    //转换传入的预约金额
                    if((reserve_num +totReserve) <= tot )
                    {
                        canModify = true;
                    }
                    else
                    {
                        throw new Exception("金额超限，请调低预约金额");
                    }
                    
                    
				}
				else
				{
				    canModify = true;
				}
				 
				 if(canModify)
				 {
                     DataRow user = new DataRow();
                     user.set("id", id);
                     user.set("product_code", product_code);
                     user.set("user_name", user_name);
                     user.set("fund_account", fund_account);
                     user.set("mobile_phone", mobile_phone);
                     user.set("branch_no", branch_no);
                     user.set("reserve_price", reserve_num);
                     user.set("refe_name", refe_name);
                     user.set("check_state", check_state);
                     user.set("check_time", check_time);
                     user.set("update_time", update_time);
                     service.updateUser(user);  
				 }
				     
				   resultVo.setErrorMsg("操作成功");
				 
				
				
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

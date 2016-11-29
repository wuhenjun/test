package com.thinkive.mall.business;

import org.apache.log4j.Logger;

import com.project.utils.Constants;
import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.service.SequenceGenerator;
import com.thinkive.base.util.StringHelper;
import com.thinkive.mall.service.ProductManagerService;
import com.thinkive.mall.service.ThrBankService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;

/**
 * 描述 : 新增三方存管
 */

public class Function9029074 extends BaseFunction
{
	/**
	 * <!-- 
					序号			id
银行名称		bank_name
创建时间		create_time
更新时间		update_time
是否支持在线验证	is_online_verify
银行图片地址		bank_img
密码规则		pwd_rule
银行开户步骤		bank_step
三方存管协议编号	agreement_id
银行代码		bank_no
					 -->
	 */
	@Override
	public ResultVo execute() throws InvokeException, Exception
	{
		Logger log = Logger.getLogger(Function9029074.class);
		ResultVo resultVo = new ResultVo();
			try {
				String id = getStrParameter("id");
				String is_online_verify = getStrParameter("is_online_verify");
				String bank_img = getStrParameter("bank_img");
				String pwd_rule = getStrParameter("pwd_rule");
				String bank_step = getStrParameter("bank_step");
				String agreement_id = getStrParameter("agreement_id");
				String bank_no = getStrParameter("bank_no");
				if(StringHelper.isEmpty(id)){
					resultVo.setErrorMsg("id不能为空");
					resultVo.setErrorNo(-902907401);
					throw new InvokeException(-902907401,"id不能为空");
				}
				if(StringHelper.isEmpty(bank_no)){
					resultVo.setErrorMsg("银行编号不能为空");
					resultVo.setErrorNo(-902907402);
					throw new InvokeException(-902907402,"银行编号不能为空");
				}
				if(!StringHelper.isEmpty(bank_img) && !bank_img.startsWith("/mall") && !bank_img.startsWith("http")){
					bank_img = "/mall" + bank_img;
				}
				DataRow datarow = new DataRow();
				
				datarow.set("id", id);
				datarow.set("is_online_verify", is_online_verify);
				datarow.set("bank_img", bank_img);
				datarow.set("pwd_rule", pwd_rule);
				datarow.set("bank_step", bank_step);
				datarow.set("agreement_id", agreement_id);
				datarow.set("bank_no", bank_no);
				ThrBankService thrBankService = new ThrBankService();
				thrBankService.updateThrBank(datarow);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				resultVo.setErrorMsg("银行编号不能为空");
				resultVo.setErrorNo(-902907403);
				e.printStackTrace();
			}
		return resultVo;
	}
	
}

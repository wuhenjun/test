package com.thinkive.mall.business;

import java.util.ArrayList;
import java.util.List;

import com.thinkive.base.jdbc.DataRow;
import com.thinkive.mall.service.ProtocleService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;
/**
 * 批量更新产品产品关联协议
 * @author 柯贤飞
 */
public class Function902909 extends BaseFunction {

	@Override
	public ResultVo execute() throws InvokeException, Exception {
		
		String old_agreement_id = this.getStrParameter("old_agreement_id");  //旧协议
		String new_agreement_id = this.getStrParameter("new_agreement_id");  //新协议
		String is_influence_all = this.getStrParameter("is_influence_all");  //是否全部更新1为全部0为部分
		ProtocleService service = new ProtocleService();
		
		
		ResultVo result = new ResultVo();
		try {
			if(null != is_influence_all && is_influence_all.equals("1")){
				service.batchUpdateProductPrototol(old_agreement_id, new_agreement_id, true, null);
			}else{
				String product_fund =  this.getStrParameter("product_fund");
				String product_money =  this.getStrParameter("product_money");
				String product_infor =  this.getStrParameter("product_infor");
				String product_server =  this.getStrParameter("product_server");
				List<String> list = new ArrayList<String>();
				list.add(product_fund);
				list.add(product_money);
				list.add(product_infor);
				list.add(product_server);
				service.batchUpdateProductPrototol(old_agreement_id, new_agreement_id, false, list);
			}
		} catch (Exception e) {
			result.setErrorMsg("协议批量更新失败！"+e.getMessage());
			result.setErrorNo(-90245501);
			e.printStackTrace();
			throw new InvokeException(90245501,"协议批量更新失败！"+e.getMessage());
		}
		return result;
	}

}

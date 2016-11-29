package com.thinkive.mall.business;

import com.thinkive.base.util.StringHelper;
import com.thinkive.mall.service.ProductServerService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;

public class Function9020404 extends BaseFunction {

	@Override
	public ResultVo execute() throws InvokeException, Exception {
		ResultVo result = new ResultVo();
		String product_id_ = this.getStrParameter("product_id");
		if(StringHelper.isEmpty(product_id_)){
			result.setErrorMsg("product can not null !");
			result.setErrorNo(-902905001);
			throw new InvokeException(902040401,"product is null !");
		}
		
		String[] product_ids = StringHelper.split(product_id_, ",");
		ProductServerService pss = new ProductServerService();
		for(String product_id:product_ids){
			try {
				pss.deleteProductServer(product_id);
			} catch (Exception e) {
				result.setErrorMsg("É¾³ýÊ§°Ü,"+e.getMessage());
				result.setErrorNo(-902905001);
				e.printStackTrace();
				throw new InvokeException(902040402,"É¾³ýÊ§°Ü,"+e.getMessage());
			}
		}
		
		return result;
	}

}

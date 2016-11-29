package com.thinkive.mall.business;

import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.util.StringHelper;
import com.thinkive.mall.service.ProductServerService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;

public class Function902940 extends BaseFunction {

	@Override
	public ResultVo execute() throws InvokeException, Exception {
		ResultVo result = new ResultVo();
		String product_id = this.getStrParameter("product_id");
		if(StringHelper.isEmpty(product_id)){
			result.setErrorMsg("product_id is null !");
			result.setErrorNo(-902040601);
			throw new InvokeException(902040601, "product_id is null !");
		}
		ProductServerService pss = new ProductServerService();
		DataRow data = pss.queryProductServer(product_id);
		if(data==null || data.size()<=0){
			result.setErrorMsg("未找到该产品信息!");
			result.setErrorNo(-902040602);
			throw new InvokeException(902040602, "未找到该产品信息!");
		}
		result.setResult(data);
		return result;
	}

}

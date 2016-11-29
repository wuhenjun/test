package com.thinkive.mall.business;

import com.thinkive.base.util.StringHelper;
import com.thinkive.mall.service.ProductInfoService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;

public class Function9029047 extends BaseFunction{

	@Override
	public ResultVo execute() throws InvokeException, Exception {
		ResultVo result = new ResultVo();
		String product_id = this.getStrParameter("product_id");
		if (StringHelper.isEmpty(product_id)) {
			result.setErrorMsg("产品编号不能为空！");
			result.setErrorNo(-902904703);
			throw new InvokeException(-902904701, "产品编号不能为空！");
		}
		if (!product_id.matches("\\d+[,\\d+]*")) {
			result.setErrorMsg("产品编号非法！");
			result.setErrorNo(-902904702);
			throw new InvokeException(-902904702, "产品编号非法！");
		}
		ProductInfoService productInfoService = new ProductInfoService();
		
		try {
			productInfoService.deleteProductInfor(product_id);
		} catch (Exception e) {
			result.setErrorMsg(e.getMessage());
			result.setErrorNo(-902904701);
			e.printStackTrace();
			throw new InvokeException(-902904703, e.getMessage());
		}
		
		return result;
	}

}

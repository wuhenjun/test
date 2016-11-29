package com.thinkive.mall.business;

import com.thinkive.base.util.StringHelper;
import com.thinkive.mall.service.ProductDeleteService;
import com.thinkive.mall.service.RiskService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;

public class Function9029031 extends BaseFunction{

	@Override
	public ResultVo execute() throws InvokeException, Exception{
		ResultVo result = new ResultVo();
		String product_id = this.getStrParameter("product_id");
		String comp_ecommend_type = this.getStrParameter("comp_ecommend_type");
			
		if(StringHelper.isEmpty(product_id) || StringHelper.isEmpty(comp_ecommend_type)){
			result.setErrorNo(-902903101);
			result.setErrorMsg("product_id or comp_ecommend_type is null!");
			throw new InvokeException(-902903101,"product_id or comp_ecommend_type is null!");
		}
		
		ProductDeleteService productDeleteService = new ProductDeleteService();
		try {
				productDeleteService.deleteProductComp(product_id, comp_ecommend_type);
			}catch(InvokeException e){
				result.setErrorMsg(e.getMessage());
				result.setErrorNo(e.getErrorCode());
			}
			
		return result;
	}
}

package com.thinkive.mall.business;

import com.thinkive.base.util.StringHelper;
import com.thinkive.mall.service.ProductDeleteService;
import com.thinkive.mall.service.RiskService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;

public class Function902964 extends BaseFunction{

	@Override
	public ResultVo execute() throws InvokeException, Exception{
		ResultVo result = new ResultVo();
		String product_id = this.getStrParameter("product_id");
		String secu_category_code = this.getStrParameter("secu_category_code");
			
		if(StringHelper.isEmpty(product_id) || StringHelper.isEmpty(secu_category_code)){
			result.setErrorMsg("product_id or feerate_type is null!");
			result.setErrorNo(-90296401);
			throw new InvokeException(-90296401,"product_id or feerate_type is null!");
		}
		
		ProductDeleteService productDeleteService = new ProductDeleteService();
		
		try {
				productDeleteService.deleteAssetallocation(product_id, secu_category_code);
			}catch(InvokeException e){
				result.setErrorMsg(e.getMessage());
				result.setErrorNo(e.getErrorCode());
			}
			
		return result;
	}
}

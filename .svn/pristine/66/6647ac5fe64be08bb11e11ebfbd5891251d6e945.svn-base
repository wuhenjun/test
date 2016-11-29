package com.thinkive.mall.business;

import java.util.List;
import java.util.Map;

import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.util.StringHelper;
import com.thinkive.mall.service.ProductDeleteService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;

/**
 * É¾³ý×Ê²úÅäÖÃ
 * @author 
 *
 */
public class Function902968 extends BaseFunction{

	@Override
	public ResultVo execute() throws InvokeException, Exception{
		
		ResultVo result = new ResultVo();
		String product_id = this.getStrParameter("product_id");
		String feerate_type = this.getStrParameter("feerate_type");
			
		if(StringHelper.isEmpty(product_id) || StringHelper.isEmpty(feerate_type)){
			result.setErrorMsg("product_id or feerate_type is null!");
			result.setErrorNo(-90285401);
			throw new InvokeException(-90285401,"product_id or feerate_type is null!");
		}
		
		ProductDeleteService productDeleteService = new ProductDeleteService();
		try {
				productDeleteService.deleteFundFeerate(product_id, feerate_type);
			}catch(InvokeException e){
				result.setErrorMsg(e.getMessage());
				result.setErrorNo(e.getErrorCode());
			}
			
		return result;
	}
}

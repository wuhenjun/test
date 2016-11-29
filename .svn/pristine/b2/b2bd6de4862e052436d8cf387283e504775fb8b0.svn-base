package com.thinkive.mall.business;

import com.thinkive.base.util.StringHelper;
import com.thinkive.mall.service.ProductService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;

public class Function902947 extends BaseFunction{

	@Override
	public ResultVo execute() throws InvokeException, Exception {
		ResultVo result = new ResultVo();
		String product_ids = this.getStrParameter("product_id");
		String service_types = this.getStrParameter("service_type");
		String[] product_idArr = StringHelper.split(product_ids, ",");
		String[] service_typeArr = StringHelper.split(service_types, ",");
		
		if (product_idArr == null || product_idArr.length <= 0) {
			result.setErrorMsg("产品编号不能为空！");
			result.setErrorNo(-902063201);
			throw new InvokeException(902063201, "产品编号不能为空！");
		}
		if (product_idArr == null || product_idArr.length <= 0) {
			result.setErrorMsg("服务方式不能为空！");
			result.setErrorNo(-902063202);
			throw new InvokeException(902063202, "服务方式不能为空！");
		}
		
		ProductService rs = new ProductService();
		
		try {
			for (int i = 0; i < product_idArr.length; i++) {
				String product_id = StringHelper.replace(product_idArr[i], "$@#", ",");
				String service_type = StringHelper.replace(service_typeArr[i], "$@#", ",");
				rs.deleteProdService(product_id, service_type);
			}
		} catch (Exception e) {
			result.setErrorMsg(e.getMessage());
			result.setErrorNo(-902063203);
			e.printStackTrace();
			throw new InvokeException(902063203, e.getMessage());
		}
		
		return result;
	}

}

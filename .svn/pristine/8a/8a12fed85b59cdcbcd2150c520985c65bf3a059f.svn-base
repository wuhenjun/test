package com.thinkive.mall.business;

import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.util.StringHelper;
import com.thinkive.mall.service.ProductService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;

public class Function902945 extends BaseFunction {
	
	@Override
	public ResultVo execute() throws InvokeException, Exception {
		ResultVo result = new ResultVo();
		String product_id = this.getStrParameter("product_id");
		String service_type = this.getStrParameter("service_type");
		String priority = this.getStrParameter("priority");
		
		if (StringHelper.isEmpty(product_id)) {
			result.setErrorMsg("产品编号不能为空！");
			result.setErrorNo(-902061101);
			throw new InvokeException(902061101, "产品编号不能为空！");
		}
		if (StringHelper.isEmpty(service_type)) {
			result.setErrorMsg("服务方式不能为空！");
			result.setErrorNo(-902061102);
			throw new InvokeException(902061102, "服务方式不能为空！");
		}

		ProductService rs = new ProductService();
		DataRow dr = rs.queryNotFundProductById(product_id);
		if(dr == null){
			result.setErrorMsg("该产品编号对应的产品不存在！");
			result.setErrorNo(-902061103);
			throw new InvokeException(902061103, "该产品编号对应的产品不存在！");
		}
		
		int counts = rs.queryCountsByPrimaryKey(product_id, service_type);
		if(counts > 0){
			result.setErrorMsg("该产品已存在该服务方式！");
			result.setErrorNo(-902061104);
			throw new InvokeException(902061104, "该产品已存在该服务方式！");
		}
		
		
		try {
			DataRow dataRow = new DataRow();
			dataRow.put("product_id", product_id);
			dataRow.put("service_type", service_type);
			dataRow.put("priority", priority);
			rs.addProductService(dataRow);
		} catch (Exception e) {
			result.setErrorMsg(e.getMessage());
			result.setErrorNo(-902061105);
			e.printStackTrace();
			throw new InvokeException(902061105, e.getMessage());
		}
		
		return result;
	}

}

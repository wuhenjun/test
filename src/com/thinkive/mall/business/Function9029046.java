package com.thinkive.mall.business;

import java.io.File;
import java.util.List;

import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.util.StringHelper;
import com.thinkive.base.util.office.ExcelHelper;
import com.thinkive.mall.service.ProductInfoService;
import com.thinkive.mall.service.RiskService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;

public class Function9029046 extends BaseFunction{

	@Override
	public ResultVo execute() throws InvokeException, Exception
	{
		ResultVo resultVo = new ResultVo();
			try {
				/**
				 * data.put("product_code", product_code);
					data.put("product_name", product_name);
					data.put("product_abbr", product_abbr);
					data.put("product_description", product_description);
					data.put("risk_level", risk_level);
					data.put("product_info", product_info);
				 */
				String product_shelf = getStrParameter("product_shelf");
				String product_name = getStrParameter("product_name");
				String product_code = getStrParameter("product_code");
				String product_abbr = getStrParameter("product_abbr");
				String serviceType = getStrParameter("serviceType");
				//String subscribe_start_time = getStrParameter("subscribe_start_time");
				//String subscribe_end_time = getStrParameter("subscribe_end_time");
				String product_info = getStrParameter("product_info");
				String product_description = getStrParameter("product_description");
				
				DataRow productInfo = new DataRow();
				productInfo.set("product_name", product_name);
				productInfo.set("product_code", product_code);
				productInfo.set("product_abbr", product_abbr);
				productInfo.set("serviceType", serviceType);
				//productInfo.set("subscribe_start_time", subscribe_start_time);
				//productInfo.set("subscribe_end_time", subscribe_end_time);
				productInfo.set("product_info", product_info);
				productInfo.set("product_description", product_description);
				productInfo.set("product_shelf", product_shelf);
				productInfo.set("product_sub_type", "3");
				
				ProductInfoService productInforService = new ProductInfoService();
				productInforService.insertProductInfo(productInfo);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				resultVo.setErrorMsg(e.getMessage());
				resultVo.setErrorNo(-902903201);
				e.printStackTrace();
			}
		return resultVo;
	}

}

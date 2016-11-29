package com.thinkive.mall.business;

import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.util.DateHelper;
import com.thinkive.base.util.StringHelper;
import com.thinkive.mall.service.ProductInfoService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;

/**
 * 描述: 添加资讯产品信息
 */

public class Function9029032 extends BaseFunction
{
	
	@Override
	public ResultVo execute() throws InvokeException, Exception
	{
		ResultVo resultVo = new ResultVo();
			try {
				String product_shelf = getStrParameter("product_shelf");
				String product_name = getStrParameter("product_name");
				String product_code = getStrParameter("product_code");
				String product_abbr = getStrParameter("product_abbr");
				String risk_level = getStrParameter("risk_level");
				String subscribe_start_time = getStrParameter("subscribe_start_time");
				String subscribe_end_time = getStrParameter("subscribe_end_time");
				String product_info = getStrParameter("product_info");
				String product_description = getStrParameter("product_description");
				if(!(product_shelf != null && product_shelf.equals("1"))){
					product_shelf = "0";
				}
				if(!subscribe_start_time.matches("\\d{4}-\\d{2}-\\d{2}")){
					subscribe_start_time = "";
				}
				if(!subscribe_end_time.matches("\\d{4}-\\d{2}-\\d{2}")){
					subscribe_end_time = "";
				}
				DataRow productInfo = new DataRow();
				productInfo.set("product_name", product_name);
				productInfo.set("product_code", product_code);
				productInfo.set("product_abbr", product_abbr);
				productInfo.set("risk_level", risk_level);
				productInfo.set("subscribe_start_time", subscribe_start_time);
				productInfo.set("subscribe_end_time", subscribe_end_time);
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

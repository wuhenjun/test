package com.thinkive.mall.business;

import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.service.SequenceGenerator;
import com.thinkive.mall.service.ProductServerService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;
/**
 * 修改服务产品信息
 * @author yyy
 */
public class Function902941 extends BaseFunction {

	@Override
	public ResultVo execute() throws InvokeException, Exception {
		
		String product_id = this.getStrParameter("product_id");
		String product_code = this.getStrParameter("product_code");
		ProductServerService pss = new ProductServerService();
		//先判断product_code是否已存在
		/*int n = pss.isProductCODE(product_code);
		if(n>0){
			throw new FrameException(902040501,"该服务产品代号已存在！");
		}*/
		String product_name = this.getStrParameter("product_name");
		String product_abbr = this.getStrParameter("product_abbr");
		String product_info = this.getStrParameter("product_info");
		String product_description = this.getStrParameter("product_description");
		String product_shelf = this.getStrParameter("product_shelf");
		String risk_level = this.getStrParameter("risk_level");
		String subscribe_start_time = this.getStrParameter("subscribe_start_time");
		String subscribe_end_time = this.getStrParameter("subscribe_end_time");
		String product_sub_type = this.getStrParameter("product_sub_type");
		String consumption_type = this.getStrParameter("consumption_type");
		String user_for_type = this.getStrParameter("user_for_type");
		String level_for_type = this.getStrParameter("level_for_type");
		String server_type = this.getStrParameter("server_type");

		DataRow data1 = new DataRow();
		data1.set("product_code", product_code);
		data1.set("product_name", product_name);
		data1.set("product_abbr", product_abbr);
		data1.set("product_info", product_info);
		data1.set("product_description", product_description);
		data1.set("product_shelf", product_shelf);
		data1.set("risk_level", risk_level);
		data1.set("subscribe_start_time", subscribe_start_time);
		data1.set("subscribe_end_time", subscribe_end_time);
		data1.set("product_sub_type", product_sub_type);
		//data1.set("consumption_type", consumption_type);
		//data1.set("user_for_type", user_for_type);
		//data1.set("level_for_type", level_for_type);
		
		DataRow data2 = new DataRow();
		data2.set("server_type", server_type);
		ResultVo result = new ResultVo();
		try {
			pss.updateProductServer(product_id,data1, data2);
		} catch (Exception e) {
			result.setErrorMsg("修改失败！"+e.getMessage());
			result.setErrorNo(-902040501);
			e.printStackTrace();
			throw new InvokeException(902040501,"修改失败！"+e.getMessage());
		}
		return result;
	}

}

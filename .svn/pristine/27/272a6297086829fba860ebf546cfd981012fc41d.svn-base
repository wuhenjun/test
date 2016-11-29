package com.thinkive.mall.business;

import com.thinkive.base.jdbc.DataRow;
import com.thinkive.mall.service.RiskService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;
/**
 * 修改服务产品信息
 * @author yyy
 */
public class Function902950 extends BaseFunction {

	@Override
	public ResultVo execute() throws InvokeException, Exception {
		ResultVo result = new ResultVo();
		String risk_type_title = this.getStrParameter("risk_type_title");
		String risk_type_description = this.getStrParameter("risk_type_description");
		String risk_type_state = this.getStrParameter("risk_type_state");
		String risk_valid = this.getStrParameter("risk_valid");
		
		String min_value = this.getStrParameter("min_value");
		String max_value = this.getStrParameter("max_value");
		String risk_evel = this.getStrParameter("risk_evel");
		String risk_name = this.getStrParameter("risk_name");
		if(risk_valid != null && !risk_valid.matches("\\d+")){
			result.setErrorMsg("风险评测有效期必须为数字");
			result.setErrorNo(-902040502);
			throw new InvokeException(902040502,"风险评测有效期必须为数字");
		}
		DataRow data1 = new DataRow();
		data1.set("risk_type_title", risk_type_title);
		data1.set("risk_type_description", risk_type_description);
		data1.set("risk_type_state", risk_type_state);
		data1.set("risk_valid", risk_valid);
		
		DataRow data2 = new DataRow();
		data2.set("min_value", min_value);
		data2.set("max_value", max_value);
		data2.set("risk_evel", risk_evel);
		data2.set("risk_name", risk_name);
		
		try{
			RiskService riskService = new RiskService();
			riskService.inSertRiskType(data1, data2);
		}catch (Exception e) {
			result.setErrorNo(902040501);
			result.setErrorMsg(e.getMessage());
		}
		
		return result;
	}

}

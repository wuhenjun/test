package com.thinkive.mall.business;

import com.thinkive.base.util.StringHelper;
import com.thinkive.mall.service.RiskService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;

public class Function903105 extends BaseFunction{

	@Override
	public ResultVo execute() throws InvokeException, Exception {
		String risk_type_id_ = this.getStrParameter("risk_type_id"); 
		String[] risk_type_ids =  StringHelper.split(risk_type_id_, ",");
		if(risk_type_ids==null || risk_type_ids.length<=0){
			throw new InvokeException(90310501,"风险评测类型ID不能为空！");
		}
		RiskService rs = new RiskService();
		ResultVo result = new ResultVo();
		try {
			for(String risk_type_id:risk_type_ids){
				 rs.deleteRiskType(risk_type_id);
			}
		} catch (InvokeException e) {
			result.setErrorNo(e.getErrorCode());
			result.setErrorMsg(e.getMessage());
			e.printStackTrace();
			return result;
		}
		return result;
	}

}

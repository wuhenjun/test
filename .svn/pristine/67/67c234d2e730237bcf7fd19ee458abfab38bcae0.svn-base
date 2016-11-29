package com.thinkive.mall.business;

import com.thinkive.base.jdbc.DataRow;
import com.thinkive.mall.service.RiskService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;

/**
 * 描述: 修改测试试题信息
 */

public class Function9029038 extends BaseFunction
{
	
	@Override
	public ResultVo execute() throws InvokeException, Exception
	{
		ResultVo resultVo = new ResultVo();
			try {
				String question_id = getStrParameter("question_id");
				
				RiskService riskService = new RiskService();
				riskService.deleteRiskQuestion(question_id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				resultVo.setErrorMsg(e.getMessage());
				resultVo.setErrorNo(-902903801);
				e.printStackTrace();
			}
		return resultVo;
	}
	
}

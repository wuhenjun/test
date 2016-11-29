package com.thinkive.mall.business;

import com.thinkive.base.jdbc.DataRow;
import com.thinkive.mall.service.RiskService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;

/**
 * 描述: 修改测试试题信息
 */

public class Function9029037 extends BaseFunction
{
	
	@Override
	public ResultVo execute() throws InvokeException, Exception
	{
		ResultVo resultVo = new ResultVo();
			try {
				String question_id = getStrParameter("question_id");
				String question_no = getStrParameter("question_no");
				String question_title = getStrParameter("question_title");
				String question_description = getStrParameter("question_description");
				String question_type = getStrParameter("question_type");
				
				String option_no = getStrParameter("option_no");
				String option_state = getStrParameter("option_state");
				String description = getStrParameter("description");
				String selection_mark = getStrParameter("selection_mark");
				
				DataRow question = new DataRow();
				question.put("question_id", question_id);
				question.put("question_no", question_no);
				question.put("question_title", question_title);
				question.put("question_description", question_description);
				question.put("question_type", question_type);
				
				DataRow option = new DataRow();
				option.put("option_no", option_no);
				option.put("option_state", option_state);
				option.put("description", description);
				option.put("selection_mark", selection_mark);
				
				RiskService riskService = new RiskService();
				riskService.updateRiskQuestion(question, option);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				resultVo.setErrorNo(-9029037);
				resultVo.setErrorMsg(e.getMessage());
			}
		return resultVo;
	}
	
}

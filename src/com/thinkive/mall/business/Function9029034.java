package com.thinkive.mall.business;

import java.util.Date;

import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.util.DateHelper;
import com.thinkive.mall.service.RiskService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;

/**
 * 描述: 添加风险测试试题信息
 */

public class Function9029034 extends BaseFunction
{
	
	@Override
	public ResultVo execute() throws InvokeException, Exception
	{
		ResultVo resultVo = new ResultVo();
			try {
				String option_no = getStrParameter("option_no");
				String description = getStrParameter("description");
				String option_state = getStrParameter("option_state");
				String selection_mark = getStrParameter("selection_mark");
				
				String risk_type_id = getStrParameter("risk_type_id");
				String question_no = getStrParameter("question_no");
				String question_title = getStrParameter("question_title");
				String question_type = getStrParameter("question_type");
				String question_description = getStrParameter("question_description");
				if(!(question_type != null && question_type.equals("1"))){
					question_type = "0";
				}
				DataRow question = new DataRow();
				question.put("risk_type_id", risk_type_id);
				question.set("question_no", question_no);
				question.set("question_title", question_title);
				question.set("question_type", question_type);
				question.set("question_description", question_description);
				question.put("create_time", DateHelper.formatDate(new Date(),"yyyy-MM-dd"));
				
				DataRow option = new DataRow();
				option.put("option_no", option_no);
				option.put("description", description);
				option.put("option_state", option_state);
				option.put("selection_mark", selection_mark);
				option.put("create_time", new Date());
				RiskService riskService = new RiskService();
				riskService.insertRiskQuestion(question, option);
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				resultVo.setErrorMsg(e.getMessage());
				resultVo.setErrorNo(-902903401);
				e.printStackTrace();
			}
		return resultVo;
	}
	
}

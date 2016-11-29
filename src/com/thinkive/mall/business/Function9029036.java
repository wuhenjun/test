package com.thinkive.mall.business;

import java.util.List;

import com.thinkive.base.jdbc.DataRow;
import com.thinkive.mall.service.RiskService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;

/**
 * 描述: 查询试题下风险测试题目详细
 */

public class Function9029036 extends BaseFunction
{
	
	@Override
	public ResultVo execute() throws InvokeException, Exception
	{
		ResultVo resultVo = new ResultVo();
		DataRow dataRow = new DataRow();
			try {
				String question_id=getStrParameter("question_id");
				RiskService riskService = new RiskService();
				dataRow = riskService.queryQuestion(question_id);
				List<DataRow> list = riskService.queryQuestionOption(question_id);
				if(list.size() > 0){
					String option_no = "",option_state = "",description = "",selection_mark = "";
					for(DataRow dr:list){
						option_no += "$%&" + dr.getObject("option_no");
						option_state += "$%&" + dr.getObject("option_state");
						description += "$%&" + dr.getObject("description");
						selection_mark += "$%&" + dr.getObject("selection_mark");
					}
					option_no = option_no.substring(3);
					option_state = option_state.substring(3);
					description = description.substring(3);
					selection_mark = selection_mark.substring(3);
					dataRow.put("option_no_all", option_no);
					dataRow.put("option_state_all", option_state);
					dataRow.put("description_all", description);
					dataRow.put("selection_mark_all", selection_mark);
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				resultVo.setErrorMsg(e.getMessage());
				resultVo.setErrorNo(-902903601);
				e.printStackTrace();
			}
			resultVo.setResult(dataRow);
			return resultVo;
	}
	
}

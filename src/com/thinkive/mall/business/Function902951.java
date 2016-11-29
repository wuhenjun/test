package com.thinkive.mall.business;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.thinkive.base.jdbc.DataRow;
import com.thinkive.mall.service.RiskService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;
/**
 * 修改服务产品信息
 * @author yyy
 */
public class Function902951 extends BaseFunction {

	@Override
	public ResultVo execute() throws InvokeException, Exception {
		ResultVo result = new ResultVo();
		Logger log = Logger.getLogger(Function902951.class);
		String risk_type_id = this.getStrParameter("risk_type_id");
		if(risk_type_id == null || !risk_type_id.matches("\\d+")){
			result.setErrorMsg("id不能为空");
			result.setErrorNo(-902040502);
			throw new InvokeException(-902040502,"id不能为空");
		}
		
		try {
			RiskService riskService = new RiskService();
			DataRow data = riskService.queryRiskType(risk_type_id);
			log.info("data--" + data.toString());
			List<Map<String,Object>> list = riskService.queryRiskInterval(risk_type_id);
			log.info("list--" + list.toString());
			if(list.size() > 0){
				StringBuffer s = new StringBuffer();
				for(Map<String,Object> map:list){
					Object min_value = map.get("min_value");
					Object max_value = map.get("max_value");
					Object risk_evel = map.get("risk_evel");
					Object risk_name = map.get("risk_name");
					s.append("%&$*").append("" + min_value).append("$&#" + max_value)
						.append("$&#" + risk_evel).append("$&#" + risk_name);
				}
				data.put("riskInterval", s.substring(4));
				
			}
			result.setResult(data);
		} catch (Exception e) {
			result.setErrorMsg("查询失败！"+e.getMessage());
			result.setErrorNo(-902040501);
			e.printStackTrace();
			throw new InvokeException(-902040501,"查询失败！"+e.getMessage());
		}
		
		return result;
	}

}

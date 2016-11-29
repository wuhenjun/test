package com.thinkive.mall.business;

import java.util.List;
import java.util.Map;

import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.util.StringHelper;
import com.thinkive.mall.service.ProductFundManagerService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;

/**
 * 查询产品详细信息
 * @author yyy
 *
 */
public class Function902993 extends BaseFunction{

	@Override
	public ResultVo execute() throws InvokeException, Exception {
		//(fund_manager_id,fund_manager_name,fund_manager_info,fund_manager_photo,fund_manager_photo_url,fund_manager_type,is_self_info)
		String fund_manager_id = this.getStrParameter("fund_manager_id");
		String fund_manager_name = this.getStrParameter("fund_manager_name");
		String fund_manager_info = this.getStrParameter("fund_manager_info");
		String fund_manager_photo_url = this.getStrParameter("fund_manager_photo_url");
		//String fund_manager_type = this.getStrParameter("fund_manager_type");
		ResultVo result = new ResultVo();
		
		if(StringHelper.isEmpty(fund_manager_id) || !fund_manager_id.matches("\\d+")){
			result.setErrorMsg("编号[fund_manager_id]不能为空或非法");
			result.setErrorNo(-90299301);
			throw new InvokeException(-1, "编号[fund_manager_id]不能为空或非法");
		}
		
		if(!StringHelper.isEmpty(fund_manager_photo_url) && !fund_manager_photo_url.startsWith("/mall")){
			fund_manager_photo_url = "/mall" + fund_manager_photo_url;
		}
		
		try{
			ProductFundManagerService pfs = new ProductFundManagerService();
			DataRow dataRow = new DataRow() ;
			dataRow.set("fund_manager_id", fund_manager_id);
			dataRow.set("fund_manager_name", fund_manager_name);
			dataRow.set("fund_manager_info", fund_manager_info);
			dataRow.set("fund_manager_photo_url", fund_manager_photo_url);
			dataRow.set("is_self_info", "1");
			pfs.upateProductFundManager(dataRow);
		}catch(InvokeException e){
			result.setErrorNo(e.getErrorCode());
			result.setErrorMsg(e.getMessage());
		}
		
		return result;
	}

}

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
public class Function902991 extends BaseFunction{

	@Override
	public ResultVo execute() throws InvokeException, Exception {
		//(fund_manager_id,fund_manager_name,fund_manager_info,fund_manager_photo,fund_manager_photo_url,fund_manager_type,is_self_info)
		String fund_manager_name = this.getStrParameter("fund_manager_name");
		String fund_manager_info = this.getStrParameter("fund_manager_info");
		String fund_manager_photo_url = this.getStrParameter("fund_manager_photo_url");
		//String fund_manager_type = this.getStrParameter("fund_manager_type");
		
		if(!StringHelper.isEmpty(fund_manager_photo_url) && !fund_manager_photo_url.startsWith("/mall")){
			fund_manager_photo_url = "/mall" + fund_manager_photo_url;
		}
		ResultVo result = new ResultVo();
		try{
			ProductFundManagerService pfs = new ProductFundManagerService();
			DataRow dataRow = new DataRow() ;
			dataRow.set("fund_manager_name", fund_manager_name);
			dataRow.set("fund_manager_info", fund_manager_info);
			dataRow.set("fund_manager_photo_url", fund_manager_photo_url);
			//dataRow.set("fund_manager_type", fund_manager_type);
			dataRow.set("is_self_info", "1");
			pfs.insertProductFundManager(dataRow);
		}catch(InvokeException e){
			result.setErrorNo(e.getErrorCode());
			result.setErrorMsg(e.getMessage());
		}
		
		return result;
	}

}

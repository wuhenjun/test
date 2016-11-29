package com.thinkive.mall.business;

import com.project.utils.Constants;
import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.service.SequenceGenerator;
import com.thinkive.base.util.StringHelper;
import com.thinkive.mall.service.ProductManagerService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;

/**
 * 描述: 新增单条产品管理方及对应图片信息
 */

public class Function9029056 extends BaseFunction
{
	
	@Override
	public ResultVo execute() throws InvokeException, Exception
	{
		ResultVo resultVo = new ResultVo();
			try {
				String manager_name=getStrParameter("manager_name");
				String ta_code=getStrParameter("ta_code");
				String img_type="2";//写死logo
				String img_class="5";//写死logo
				String img_url=getStrParameter("img_url");
				DataRow proManInfo = new DataRow();
				
				proManInfo.set("manager_name", manager_name);
				proManInfo.set("ta_code", ta_code);
				
				DataRow imgInfo = new DataRow();
				if(img_url != null && !img_url.trim().equals("")){
					if(!img_url.startsWith("/mall")){
						img_url = "/mall"+img_url;
					}
					imgInfo.set("img_type",img_type);
					imgInfo.set("img_class",img_class);
					imgInfo.set("img_url", img_url);
				}else{
					imgInfo = null;
				}
				
				ProductManagerService service =new ProductManagerService();
				service.addProductManager(proManInfo,imgInfo);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				resultVo.setErrorMsg(e.getMessage());
				resultVo.setErrorNo(-902905602);
				e.printStackTrace();
			}
		return resultVo;
	}
	
}

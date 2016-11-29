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
 * 描述: 修改单条产品管理方及对应图片信息
 */

public class Function902921 extends BaseFunction
{
	
	@Override
	public ResultVo execute() throws InvokeException, Exception
	{
		ResultVo resultVo = new ResultVo();
			try {
				String manager_id=getStrParameter("manager_id");
				String manager_name=getStrParameter("manager_name");
				String ta_code=getStrParameter("ta_code");
				String img_id=getStrParameter("img_id");
				String img_type="2";//写死logo
				String img_class="5";//写死logo
				String img_url=getStrParameter("img_url");
				String img_vesting=manager_id;
				//非空检测
				if (StringHelper.isEmpty(manager_id))
				{
					resultVo.setErrorMsg("编号[manager_id]不能为空");
					resultVo.setErrorNo(-90292101);
					throw new InvokeException(-1, "编号[manager_id]不能为空");
				}
				DataRow proManInfo = new DataRow();
				
				proManInfo.set("manager_id", manager_id);
				proManInfo.set("manager_name", manager_name);
				proManInfo.set("ta_code", ta_code);
				
				DataRow imgInfo = new DataRow();
				if(StringHelper.isEmpty(img_id)){
					img_id=SequenceGenerator.getInstance().getNextSequence(Constants.DB_ID, "T_MALL_IMG");
				}
				if(!img_url.startsWith("/mall")){
					img_url = "/mall"+img_url;
				}
				imgInfo.set("img_id", img_id);
				imgInfo.set("img_type",img_type);
				imgInfo.set("img_class",img_class);
				imgInfo.set("img_url", img_url);
				imgInfo.set("img_vesting", img_vesting);
				ProductManagerService service =new ProductManagerService();
				service.upateProductManager(proManInfo,imgInfo);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				resultVo.setErrorMsg(e.getMessage());
				resultVo.setErrorNo(-90292102);
				e.printStackTrace();
			}
		return resultVo;
	}
	
}

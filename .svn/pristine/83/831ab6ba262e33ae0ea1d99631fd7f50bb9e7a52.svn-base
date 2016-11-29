package com.thinkive.mall.business;

import com.thinkive.base.util.StringHelper;
import com.thinkive.mall.service.ProductBrandService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;

public class Function902525 extends BaseFunction{

	@Override
	public ResultVo execute() throws InvokeException, Exception {
		ResultVo  result = new ResultVo();
		String brand_id_ = this.getStrParameter("brand_id");
		if(StringHelper.isEmpty(brand_id_)){
			result.setErrorMsg("传入brand_id为空！");
			result.setErrorNo(-90252501);
			throw new InvokeException(90252501,"传入brand_id为空！");
		}
		String[] brand_ids = StringHelper.split(brand_id_, ",");
		ProductBrandService pb = new ProductBrandService();
		
		try {
			for(String brand_id:brand_ids){
				pb.deleteRiskType(brand_id);
			}
		} catch (InvokeException e) {
			result.setErrorNo(-90252502);
			result.setErrorMsg("删除产品品牌失败!");
			e.printStackTrace();
			return result;
		}
		return result;
	}

}

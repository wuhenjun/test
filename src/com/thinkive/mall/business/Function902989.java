package com.thinkive.mall.business;

import java.util.List;
import java.util.Map;

import com.project.utils.JSONUtil;
import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.util.StringHelper;
import com.thinkive.mall.service.ProductPropertyService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;

/**
 * 查询产品详细信息
 * @author yyy
 *
 */
public class Function902989 extends BaseFunction{

	@Override
	public ResultVo execute() throws InvokeException, Exception
	{
		ResultVo result = new ResultVo();
		
		String product_id = getStrParameter("product_id");
		if(StringHelper.isEmpty(product_id)){
			result.setErrorMsg("产品编号[product_id]不能为空");
			result.setErrorNo(-90298901);
			throw new InvokeException(90298901, "产品编号[product_id]不能为空");
		}
		
		String recommend_ = this.getStrParameter("recommends");
		String recommend_sort_ = this.getStrParameter("recommend_sort");//推荐方式排序
		String product_shelf = getStrParameter("product_shelf");//是否上架product_info
		String product_detail = getStrParameter("product_detail");//详情
		String product_description = getStrParameter("product_description");//详情
		
		String purchase_rates = getStrParameter("purchase_rates");//基金申购费率
		String fund_manager_id = getStrParameter("fund_manager_id");//基金经理
		
		
		if(StringHelper.isEmpty(product_shelf)){
			product_shelf = "0";
		}
		
		String img_type_ = getStrParameter("img_type_");//图片类型
		String img_url_ = getStrParameter("img_url");//图片url
		img_url_ = ""; 
		for(int i=1;i<=80;i++){
			String img_url = getStrParameter("img_url_"+i);//图片url
			if(!StringHelper.isEmpty(img_url)){
				img_url_ += img_url+",";
			}
		}
		if(!StringHelper.isEmpty(img_url_)){
			img_url_ = img_url_.substring(0, img_url_.length());
		}
		
		//判断图片url是否为空
		if(!StringHelper.isEmpty(img_url_) && !StringHelper.isEmpty(img_type_)){
			String[] img_urls = StringHelper.split(img_url_, ",");
			String[] img_types = StringHelper.split(img_type_, ",");
			if(img_urls.length!=img_types.length){
				result.setErrorMsg("图片地址不能为空!");
				result.setErrorNo(-902032103);
				throw new InvokeException(-902032103, "图片地址不能为空!");
			}
		}
		
		ProductPropertyService pps = new ProductPropertyService();
		
		/********************5、更新推荐方式***************************/
		try {
			pps.updateProductRecommend(recommend_,recommend_sort_, product_id,"0");//先删除,再添加
		} catch (Exception e) {
			result.setErrorMsg("更新推荐方式失败!"+e.getMessage());
			result.setErrorNo(-902032105);
			e.printStackTrace();
			throw new InvokeException(-902032105, "更新推荐方式失败!"+e.getMessage());
		}
		/********************1、更新金融产品信息***************************/
		DataRow data1 = new DataRow();
		data1.set("product_id", product_id);
		data1.set("product_shelf", product_shelf);
		//data1.set("product_detail", product_detail);
		data1.set("product_description", product_description);
		data1.set("product_sort", this.getStrParameter("product_sort"));
		//更新金融产品信息
		try {
			pps.updateProductFinance(data1,product_id);
		} catch (Exception e) {
			result.setErrorMsg("更新金融产品信息失败!"+e.getMessage());
			result.setErrorNo(-902032102);
			e.printStackTrace();
			throw new InvokeException(-902032102, "更新金融产品信息失败!"+e.getMessage());
		}
		
		
		data1 = new DataRow();
		data1.set("product_id", product_id);
		data1.set("purchase_rates", purchase_rates);
		data1.set("fund_type", this.getStrParameter("fund_type"));
		
		/*if(StringHelper.isNotBlank(fund_manager_id)){
			data1.set("fund_manager_id", fund_manager_id);
		}*/
		//更新金融产品信息
		try {
			pps.updateProduct(data1, product_id, 0);
		} catch (Exception e) {
			result.setErrorMsg("更新基金产品信息失败!"+e.getMessage());
			result.setErrorNo(-902032103);
			e.printStackTrace();
			throw new InvokeException(-902032103, "更新基金产品信息失败!"+e.getMessage());
		}
		
		//更新基金经理信息
		String manager_id = this.getStrParameter("manager_id");
		try {
			pps.updateFundManager(manager_id, product_id);
		} catch (Exception e) {
			result.setErrorMsg("更新基金产品信息失败!"+e.getMessage());
			result.setErrorNo(-902032104);
			e.printStackTrace();
			throw new InvokeException(-902032104, "更新基金产品信息失败!"+e.getMessage());
		}
		/********************4、更新图片信息***************************/
		try {
			pps.updateImg(img_url_,img_type_, product_id,"0");//先删除,再添加  3表示资讯产品
		} catch (Exception e) {
			result.setErrorMsg("图片上传失败!"+e.getMessage());
			result.setErrorNo(-902032105);
			e.printStackTrace();
			throw new InvokeException(-902032105, "图片上传失败!"+e.getMessage());
		}
		
		
		/********************更新关联协议***************************/
		String agreement_ = this.getStrParameter("agreement");
		try {
			pps.updateProductAgreement(agreement_, product_id);
		} catch (Exception e) {
			result.setErrorMsg("更新服务方式失败!"+e.getMessage());
			result.setErrorNo(-902032106);
			e.printStackTrace();
			throw new InvokeException(-902032106, "更新服务方式失败!"+e.getMessage());
		}
		return result;
	}

}

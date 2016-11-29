package com.thinkive.mall.business;


import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.util.StringHelper;
import com.thinkive.mall.service.ProductPropertyService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;

/**
 * 更新理财产品详细信息
 * @author yyy
 *
 */
public class Function902998 extends BaseFunction{

	@Override
	public ResultVo execute() throws InvokeException, Exception
	{
		ResultVo result = new ResultVo();
		
		String product_id = getStrParameter("product_id");
		if(StringHelper.isEmpty(product_id)){
			result.setErrorMsg("产品编号[product_id]不能为空");
			result.setErrorNo(-902032101);
			throw new InvokeException(902032101, "产品编号[product_id]不能为空");
		}
		
		String recommend_ = this.getStrParameter("recommends");
		String recommend_sort_ = this.getStrParameter("recommend_sort");//推荐方式排序
		String product_shelf = getStrParameter("product_shelf");//是否上架
		String investment_horizon = getStrParameter("investment_horizon");//投资期限
		String product_abbr = getStrParameter("product_abbr");//产品简称
		String share_date = this.getStrParameter("share_date");
		String product_status = this.getStrParameter("product_status");
		
		String subscribe_start_time = this.getStrParameter("subscribe_start_time");
		String subscribe_end_time = this.getStrParameter("subscribe_end_time");
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
		
		//String join_guide = getStrParameter("join_guide");//产品参与指南
		String product_description = getStrParameter("product_description");//产品详情

		ProductPropertyService pps = new ProductPropertyService();
		
		/********************5、更新推荐方式***************************/
		try {
			pps.updateProductRecommend(recommend_,recommend_sort_, product_id,"1");//先删除,再添加
		} catch (Exception e) {
			result.setErrorMsg("更新推荐方式失败!"+e.getMessage());
			result.setErrorNo(-902032105);
			e.printStackTrace();
			throw new InvokeException(-902032105, "更新推荐方式失败!"+e.getMessage());
		}
		
		
		/********************1、更新金融产品信息***************************/
		DataRow data0 = new DataRow();
		data0.set("fina_type", this.getStrParameter("fina_type"));
		data0.set("target", this.getStrParameter("target"));
		data0.set("is_show_income", this.getStrParameter("is_show_income"));
		String fina_belongs = this.getStrParameter("fina_belongs");
		if(fina_belongs != null && fina_belongs.equals("5") ){
			data0.set("product_special", this.getStrParameter("product_special"));
			data0.set("income_certificate", this.getStrParameter("income_certificate"));
			
		}
		
		//更新理财产品信息
		try {
			pps.updateProduct(data0,product_id,1);
		} catch (Exception e) {
			result.setErrorMsg("更新理财产品信息失败!"+e.getMessage());
			result.setErrorNo(-902032106);
			e.printStackTrace();
			throw new InvokeException(-902032106, "更新理财产品信息失败!"+e.getMessage());
		}
		
		
		DataRow data1 = new DataRow();
		data1.set("product_id", product_id);
		data1.set("product_shelf", product_shelf);
		data1.set("product_description", product_description);
		data1.set("product_detail", this.getStrParameter("product_detail"));
		data1.set("product_sort", this.getStrParameter("product_sort"));
		data1.set("current_price", this.getStrParameter("current_price"));
		data1.set("per_buy_limit",this.getStrParameter("per_buy_limit"));
		data1.set("person_pace",this.getStrParameter("person_pace"));
		data1.set("cumulative_net", this.getStrParameter("cumulative_net"));
		data1.set("product_abbr", product_abbr);
		data1.set("product_status", product_status);
		data1.set("subscribe_start_time", subscribe_start_time);
		data1.set("subscribe_end_time", subscribe_end_time);
		if(share_date != null){
			data1.set("share_date", share_date);
		}
		
		//更新金融产品信息
		try {
//			pms.updateProductMoney(data1,product_id);
			pps.updateProductFinance(data1,product_id);
		} catch (Exception e) {
			result.setErrorMsg("更新金融产品信息失败!"+e.getMessage());
			result.setErrorNo(-902032102);
			e.printStackTrace();
			throw new InvokeException(-902032102, "更新金融产品信息失败!"+e.getMessage());
		}
		
		data1 = new DataRow();
		data1.set("product_id", product_id);
		data1.set("investment_horizon", investment_horizon);
		data1.set("earnings", this.getStrParameter("earnings"));
		//更新金融产品信息
		try {
			pps.updateProduct(data1, product_id, 1);
		} catch (Exception e) {
			result.setErrorMsg("更新金融产品信息失败!"+e.getMessage());
			result.setErrorNo(-902032102);
			e.printStackTrace();
			throw new InvokeException(-902032102, "更新金融产品信息失败!"+e.getMessage());
		}
		
		
		/********************4、更新图片信息***************************/
		try {
			if(!StringHelper.isEmpty(img_url_) && !StringHelper.isEmpty(img_type_)){
				pps.updateImg(img_url_,img_type_, product_id,"1");//先删除,再添加  3表示资讯产品
			}
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
			result.setErrorNo(-902032104);
			e.printStackTrace();
			throw new InvokeException(-902032104, "更新服务方式失败!"+e.getMessage());
		}
		
		//更新基金经理信息
		String manager_id = this.getStrParameter("manager_id");
		try {
			pps.updateFundManager(manager_id, product_id);
		} catch (Exception e) {
			result.setErrorMsg("更新理财产品信息失败!"+e.getMessage());
			result.setErrorNo(-902032102);
			e.printStackTrace();
			throw new InvokeException(-902032102, "更新理财产品信息失败!"+e.getMessage());
		}
		return result;
	}

}

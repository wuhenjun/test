package com.thinkive.mall.business;


import java.util.ArrayList;
import java.util.List;

import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.util.StringHelper;
import com.thinkive.mall.service.ProductPropertyService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;

/**
 * 
 * 描述: 修改资讯产品信息
 * 版权: Copyright (c) 2014
 * 公司: 思迪科技 
 * 作者: 江晨
 * 版本: 1.0 
 * 创建日期: 2014-5-7 
 * 创建时间: 下午01:15:25
 */

public class Function902937 extends BaseFunction
{
	
	@Override
	public ResultVo execute() throws InvokeException, Exception
	{
		ResultVo result = new ResultVo();
		
		String product_id = getStrParameter("product_id");
		if(StringHelper.isEmpty(product_id)){
			result.setErrorMsg("产品编号[product_id]不能为空");
			result.setErrorNo(-902032101);
			//throw new InvokeException(-902032102,"recommends:" +  this.getStrParameter("recommends") + "-recommend_sort:" + this.getStrParameter("recommend_sort") + "-img_type_:" +getStrParameter("img_type_") + "-img_url:" + getStrParameter("img_url") + "-discounts:" + getStrParameter("discounts") + "-priority_sort:" + getStrParameter("priority_sort")  );
			throw new InvokeException(902032101, "产品编号[product_id]不能为空");
		}
		String recommend_ = this.getStrParameter("recommends");//推荐方式
		String recommend_sort_ = this.getStrParameter("recommend_sort");//推荐方式排序
		//判断排序是否为整数
		if(!StringHelper.isEmpty(recommend_sort_)){
			String[] recommend_sorts = StringHelper.split(recommend_sort_, ",");
			for(String recomend_sort:recommend_sorts){
				try{
					Integer.valueOf(recomend_sort);
				}catch(Exception e){
					result.setErrorMsg(e.getMessage() + "   传参如下： recommends:" + recommend_ + "-recommend_sort:" + recommend_sort_ + "-img_type_:" +getStrParameter("img_type_") + "-img_url:" + getStrParameter("img_url") + "-discounts:" + getStrParameter("discounts") + "-priority_sort:" + getStrParameter("priority_sort"));
					result.setErrorNo(-902032102);
					throw new InvokeException(-902032102,"recommends:" + recommend_ + "-recommend_sort:" + recommend_sort_ + "-img_type_:" +getStrParameter("img_type_") + "-img_url:" + getStrParameter("img_url") + "-discounts:" + getStrParameter("discounts") + "-priority_sort:" + getStrParameter("priority_sort")  );
				}
			}
		}
		
		String product_shelf = getStrParameter("product_shelf");//是否上架
		if(StringHelper.isEmpty(product_shelf)){
			product_shelf = "0";
		}
		
		String brand_id = getStrParameter("brond_id");//营销品牌  注意  这个字段是个坑  brand_id  brond_id
		
		
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
		
		String discounts = getStrParameter("discounts");//价格规格
		String priority_sort_ = getStrParameter("priority_sort");//优先级
		
		String service_type_ = getStrParameter("service_type");//服务方式
		String product_info = getStrParameter("product_info");//产品详情
		String product_abbr = getStrParameter("product_abbr");//产品简称
		String product_description = getStrParameter("product_description");//产品简述
		
		ProductPropertyService pps = new ProductPropertyService();
		
		/********************6、更新产品营销品牌***************************/
		try {
			pps.updateBrand(product_id, brand_id);
		} catch (Exception e) {
			result.setErrorMsg("更新产品营销品牌失败!"+e.getMessage());
			result.setErrorNo(-902032107);
			throw new InvokeException(-902032107, "更新产品营销品牌失败!"+e.getMessage());
		}
		
		/********************5、更新推荐方式***************************/
		try {
//			if(!StringHelper.isEmpty(recommend_) && !StringHelper.isEmpty(recommend_sort_)){
//				String[] recommends = StringHelper.split(recommend_, ",");
//				String[] recommend_sorts = StringHelper.split(recommend_sort_, ",");
//				if(recommends.length!=recommend_sorts.length){
//					throw new InvokeException(-902032106, "推荐方式排序不能为空!");
//				}
//				if(recommends!=null){
					pps.updateProductRecommend(recommend_,recommend_sort_, product_id,"3");//先删除,再添加
//				}
//			}
		} catch (Exception e) {
			result.setErrorMsg("更新推荐方式失败!"+e.getMessage());
			result.setErrorNo(-902032105);
			e.printStackTrace();
			throw new InvokeException(-902032105, "更新推荐方式失败!"+e.getMessage());
		}
		
		/********************1、更新非金融产品信息***************************/
		DataRow data1 = new DataRow();
		data1.set("product_id", product_id);
		data1.set("product_shelf", product_shelf);
		data1.set("product_info", product_info);
		data1.set("product_abbr", product_abbr);
		data1.set("product_description", product_description);
		data1.set("product_sort", this.getStrParameter("product_sort"));
		//更新非金融产品信息
		try {
			pps.updateProductNotFinance(data1, product_id);
		} catch (Exception e) {
			result.setErrorMsg("更新非金融产品信息失败!"+e.getMessage());
			result.setErrorNo(-902032102);
			e.printStackTrace();
			throw new InvokeException(-902032102, "更新非金融产品信息失败!"+e.getMessage());
		}
		
		
		/********************2、更新价格规格信息***************************/
		try {
//			if(!StringHelper.isEmpty(discounts) && !StringHelper.isEmpty(priority_sort_)){
//				String[] rules_ids = StringHelper.split(discounts, ",");
//				String[] priority_sorts = StringHelper.split(priority_sort_, ",");
//				if(rules_ids.length!=priority_sorts.length){
//					throw new InvokeException(-902032107, "价格规格序号不能为空!");
//				}
//				if(rules_ids!=null){
					pps.updatePriceRules(discounts,priority_sort_, product_id);//先删除,再添加
//				}
//			}
		} catch (Exception e) {
			result.setErrorMsg("更新价格规格信息失败!"+e.getMessage());
			result.setErrorNo(-902032103);
			e.printStackTrace();
			throw new InvokeException(-902032103, "更新价格规格信息失败!"+e.getMessage());
		}
		
		/********************3、更新服务方式***************************/
		try {
			String product_service_type = this.getStrParameter("product_service_type");
			List<String[]> list = new ArrayList<String[]>();
			if(!StringHelper.isEmpty(product_service_type)){
				String[] serviceTypes = product_service_type.split(",");
				for(String s:serviceTypes){
					if(!StringHelper.isEmpty(s)){
						String is_default = this.getStrParameter("is_default_" + s);
						String is_must = this.getStrParameter("is_must_" + s);
						String priority = this.getStrParameter("product_priority_" + s);
						String[] service = new String[4];
						service[0] = s;
						service[1] = (is_default != null && is_default.equals("1")) ? "1" : "0";
						service[2] = (is_must != null && is_must.equals("1")) ? "1" : "0";
						service[3] = priority;
						list.add(service);
					}
				}
				pps.updateProductServices(list, product_id);
			}
//			if(!StringHelper.isEmpty(service_type_)){
//				String[] service_types = StringHelper.split(service_type_, ",");
//				if(service_types!=null){
					//pps.updateProductService(service_type_, product_id);//先删除,再添加
//				}
//			}
			
		} catch (Exception e) {
			result.setErrorMsg("更新服务方式失败!"+e.getMessage());
			result.setErrorNo(-902032104);
			e.printStackTrace();
			throw new InvokeException(-902032104, "更新服务方式失败!"+e.getMessage());
		}
		
		/********************4、更新图片信息***************************/
		try {
//			if(!StringHelper.isEmpty(img_url_) && !StringHelper.isEmpty(img_type_)){
//				String[] img_urls = StringHelper.split(img_url_, ",");
//				String[] img_types = StringHelper.split(img_type_, ",");
//				if(img_urls.length!=img_types.length){
//					throw new InvokeException(-902032106, "图片地址不能为空!");
//				}
//				
//				if(img_urls!=null){
					pps.updateImg(img_url_,img_type_, product_id,"3");//先删除,再添加  3表示资讯产品
//				}
//			}
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
		return result;
	}
	
}

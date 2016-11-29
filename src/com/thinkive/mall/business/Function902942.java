package com.thinkive.mall.business;

import java.util.List;
import java.util.Map;

import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.util.StringHelper;
import com.thinkive.mall.service.ProductPropertyService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;

/**
 * 查询服务产品详细信息
 * @author yyy
 *
 */
public class Function902942 extends BaseFunction{
	@Override
	public ResultVo execute() throws InvokeException, Exception {
		ResultVo result = new ResultVo();
		String product_id = this.getStrParameter("product_id");
			
		if(StringHelper.isEmpty(product_id)){
			result.setErrorMsg("product_id is null!");
			result.setErrorNo(-902040201);
			throw new InvokeException(-902040201,"product_id is null!");
		}
		ProductPropertyService pps = new ProductPropertyService();
		DataRow data = null ;
		try{
			data = pps.queryProductDetail(3, product_id);//查新非金融产品表
			if(data==null||data.size()<=0){
				result.setErrorMsg("未找到该产品信息!");
				result.setErrorNo(-902040202);
				throw new InvokeException(-902040202, "未找到该产品信息!");
			}
			
			List<Map<String,Object>> services = pps.queryProductServices(product_id);//查询服务方式
			if(services!=null){
				String str = "";
				String str2 = "";
				for(Map<String,Object> map:services){
					str += String.valueOf(map.get("service_type")).replace(",", "$@#")+",";
					str2 += String.valueOf(map.get("service_type")).replace(",", "$@#")+"==" + String.valueOf(map.get("is_default")).replace(",", "$@#")+"==" + String.valueOf(map.get("is_must")).replace(",", "$@#")+"==" + String.valueOf(map.get("priority")).replace(",", "$@#")+",";
				}
				if(!StringHelper.isEmpty(str)){
					str = str.substring(0, str.length()-1);
					str2 = str2.substring(0, str2.length()-1);
				}
				data.put("service_type", str);
				data.put("service_type_detail", str2);
			}
		}catch (Exception e) {
			result.setErrorMsg("查询产品信息失败!--" +product_id);
			result.setErrorNo(-90204022);
			e.printStackTrace();
			throw new InvokeException(-90204022,"查询产品信息失败!--" +product_id);
		}
		
		try{
			//查询已推荐产品
			List<Map<String,Object>> recommends = pps.queryProductRecommends(product_id);
			if(recommends!=null){
				String str = "";
				String str2 = "";
				for(Map<String,Object> map:recommends){
					str += String.valueOf(map.get("recommend_type"))+",";
					str2 += String.valueOf(map.get("recommend_type"))+"=="+String.valueOf(map.get("item_name"))+"=="+String.valueOf(map.get("recommend_sort"))+",";
				}
				if(!StringHelper.isEmpty(str)){
					str = str.substring(0, str.length()-1);
				}
				if(!StringHelper.isEmpty(str2)){
					str2 = str2.substring(0, str2.length()-1);
				}
				data.put("recommend_type", str);
				data.put("recommend", str2);
				
				//查询所有推荐类型
				List<Map<String,Object>> recommendLists = pps.queryRecommends();
				if(recommendLists!=null){
					String str3 = "";
					for(Map<String,Object> map:recommendLists){
						str3 += String.valueOf(map.get("item_value"))+"=="+String.valueOf(map.get("item_name"))+"=="+String.valueOf(map.get("recommend_sort"))+",";
					}
					if(!StringHelper.isEmpty(str3)){
						str3 = str3.substring(0, str3.length()-1);
					}
					data.put("all_recommend", str3);
				}
			}
		}catch (Exception e) {
			result.setErrorMsg("查询推荐类型失败!"+e.getMessage());
			result.setErrorNo(-90204024);
			e.printStackTrace();
			throw new InvokeException(-90204024,"查询推荐类型失败!"+e.getMessage());
		}
		
		try{
			/**************查询产品及其序号************/
			List<Map<String,Object>> productSorts = pps.queryProductAndSorts("2",0);
			if(productSorts!=null){
				String str = "";
				for(Map<String,Object> map:productSorts){//
					str += String.valueOf(map.get("product_id"))+"=="+String.valueOf(map.get("product_name"))+"=="+String.valueOf(map.get("recommend_sort"))+"=="+String.valueOf(map.get("recommend_type"))+",";
				}
				if(!StringHelper.isEmpty(str)){
					str = str.substring(0, str.length()-1);
				}
				data.put("productSorts", str);
			}
		}catch (Exception e) {
			result.setErrorMsg("查询查询产品及其序号失败!"+e.getMessage());
			result.setErrorNo(-90204025);
			e.printStackTrace();
			throw new InvokeException(-90204025,"查询查询产品及其序号失败!"+e.getMessage());
		}
		
		try{
			/**************查询上架产品及其排序值************/
			List<Map<String,Object>> product_Sorts = pps.queryProductSort(2);
			if(product_Sorts!=null){
				String str = "";
				for(Map<String,Object> map:product_Sorts){//recommend_type
					//str += String.valueOf(map.get("product_id"))+"=="+String.valueOf(map.get("brond_id"))+"=="+String.valueOf(map.get("product_name"))+"=="+String.valueOf(map.get("recommend_sort"))+",";
					str += String.valueOf(map.get("product_name"))+"=="+String.valueOf(map.get("product_sort"))+"=="+String.valueOf(map.get("recommend_sort"))+",";
				}
				if(!StringHelper.isEmpty(str)){
					str = str.substring(0, str.length()-1);
				}
				data.put("product_shelf_Sorts", str);
			}
		}catch(Exception e){
			result.setErrorMsg("查询上架产品及其排序值失败!"+e.getMessage());
			result.setErrorNo(-90204025);
		}
		try{
			/*******查询已添加价格规格*******/
			List<Map<String,Object>> productRules = pps.queryProductPriceRuls(product_id);
			if(productRules!=null){
				String str = "";
				String str2 = "";
				for(Map<String,Object> map:productRules){
					str += String.valueOf(map.get("rules_id"))+",";//价格规格ID
					String time = (map.get("begin_time") == null ? " -":"从" + String.valueOf(map.get("begin_time"))) + (map.get("end_time") == null ? " -": " 到" + String.valueOf(map.get("end_time")));
					str2 += String.valueOf(map.get("rules_id"))+"=="+String.valueOf(map.get("item_name"))+"=="+String.valueOf(map.get("priority"))+"=="+time+",";
				}
				if(!StringHelper.isEmpty(str)){
					str = str.substring(0, str.length()-1);
				}
				if(!StringHelper.isEmpty(str2)){
					str2 = str2.substring(0, str2.length()-1);
				}
				data.put("discount_id", str);
				data.put("discount", str2);
				
				/*******查询所有价格规格*******/
				List<Map<String,Object>> priceRules = pps.queryPriceRuls();
				if(priceRules!=null){
					String str3 = "";
					for(Map<String,Object> map:priceRules){
						str3 += String.valueOf(map.get("rules_id"))+"=="+String.valueOf(map.get("item_name"))+",";
					}
					if(!StringHelper.isEmpty(str3)){
						str3 = str3.substring(0, str3.length()-1);
					}
					data.put("all_discount", str3);
				}
			}
		}catch (Exception e) {
			result.setErrorMsg("查询价格规格失败!"+e.getMessage());
			result.setErrorNo(-90204027);
			e.printStackTrace();
			throw new InvokeException(-90204027,"查询价格规格失败!"+e.getMessage());
		}
		
		try{
			/*******查询图片*******/
			List<Map<String,Object>> productImgs = pps.queryProductImgs(product_id,"2");
			if(productImgs!=null){
				String str = "";
				String str2 = "";
				for(Map<String,Object> map:productImgs){
					str += String.valueOf(map.get("item_value"))+",";//图片类型
					str2 += String.valueOf(map.get("item_value"))+"=="+String.valueOf(map.get("item_name"))+"=="+String.valueOf(map.get("img_url"))+",";
				}
				if(!StringHelper.isEmpty(str)){
					str = str.substring(0, str.length()-1);
				}
				if(!StringHelper.isEmpty(str2)){
					str2 = str2.substring(0, str2.length()-1);
				}
				//data.put("img_type", str);
				data.put("img", str2);
				
				/*******查询所有图片*******/
				List<Map<String,Object>> imgTypes = pps.queryImgTypes();
				if(imgTypes!=null){
					String str3 = "";
					for(Map<String,Object> map:imgTypes){
						str3 += String.valueOf(map.get("item_value"))+"=="+String.valueOf(map.get("item_name"))+"=="+String.valueOf(map.get("img_url"))+",";
					}
					if(!StringHelper.isEmpty(str3)){
						str3 = str3.substring(0, str3.length()-1);
					}
					data.put("all_img", str3);
				}
			}
			
			/**************查询产品关联协议************/
			List<Map<String,Object>> product_agreement = pps.queryProductAgreement(product_id);
			if(product_agreement!=null){
				String str = "";
				for(Map<String,Object> map:product_agreement){
					str += String.valueOf(map.get("agreement_id"))+",";
				}
				if(!StringHelper.isEmpty(str)){
					str = str.substring(0, str.length()-1);
				}
				data.put("agreements", str);
			}
		}catch (Exception e) {
			result.setErrorMsg("查询图片失败!"+e.getMessage());
			result.setErrorNo(-90204028);
			e.printStackTrace();
			throw new InvokeException(-90204027,"查询图片失败!"+e.getMessage());
		}
		result.setResult(data);
		return result;
	}
}

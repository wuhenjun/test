package com.thinkive.mall.business;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.util.StringHelper;
import com.thinkive.mall.service.ProductPropertyService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;

/**
 * 查询理财产品详细信息
 * @author yyy
 *
 */
public class Function902997 extends BaseFunction{

	@Override
	public ResultVo execute() throws InvokeException, Exception {
		String product_id = this.getStrParameter("product_id");
		ResultVo result = new ResultVo();
		
		if(StringHelper.isEmpty(product_id)){
			result.setErrorMsg("product_id is null!");
			result.setErrorNo(-90299701);
			throw new InvokeException(-1,"product_id is null!");
		}
		ProductPropertyService pps = new ProductPropertyService();
		DataRow data = null ;
		try {
			data = pps.queryProductDetail(1, product_id);//查金融产品表
			if(data==null||data.size()<=0){
				throw new InvokeException(902030202, "未找到该产品信息!");
			}
			//查询已推荐产品
			List<Map<String,Object>> recommends = pps.queryProductRecommends(product_id);
			if(recommends!=null){
				String str = "";
				String str2 = "";
				for(Map<String,Object> map:recommends){
					str += String.valueOf(map.get("recommend_type"))+",";//
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

			/**************查询产品及其序号************/
			List<Map<String,Object>> productSorts = pps.queryProductAndSorts("1",1);
			if(productSorts!=null){
				String str = "";
				for(Map<String,Object> map:productSorts){//
					str += String.valueOf(map.get("product_id"))+"=="+String.valueOf(map.get("product_name"))+"=="+String.valueOf(map.get("recommend_sort"))+"=="+String.valueOf(map.get("recommend_type")) + ",";
				}
				if(!StringHelper.isEmpty(str)){
					str = str.substring(0, str.length()-1);
				}
				data.put("productSorts", str);
			}

			/**************查询上架产品及其排序值************/
			List<Map<String,Object>> product_Sorts = pps.queryProductSort(1);
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
			/*******查询图片*******/
			List<Map<String,Object>> productImgs = pps.queryProductImgs(product_id,"1");
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
			
			/**************查询产品基金经理************/
			List<Map<String,Object>> fundManager = pps.queryProductFundManager(product_id);
			if(fundManager!=null){
				String str = "";
				for(Map<String,Object> map:fundManager){
					str += String.valueOf(map.get("fund_manager_id"))+ "==" + String.valueOf(map.get("fund_manager_name")) + ",";
				}
				if(!StringHelper.isEmpty(str)){
					str = str.substring(0, str.length()-1);
				}
				data.put("fund_managers", str);
			}
		} catch (Exception e) {
			result.setErrorMsg("查询失败!"+e.getMessage());
			result.setErrorNo(-90299702);
			e.printStackTrace();
			throw new InvokeException(-902030204,"查询失败!"+e.getMessage());
		}
		
		result.setResult(data);
		return result;
	}

}

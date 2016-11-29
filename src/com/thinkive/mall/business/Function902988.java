package com.thinkive.mall.business;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.project.utils.JSONUtil;
import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.util.StringHelper;
import com.thinkive.mall.service.ProductPropertyService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;

/**
 * 查询基金产品详细信息
 * @author yyy
 *
 */
public class Function902988 extends BaseFunction{

	@Override
	public ResultVo execute() throws InvokeException, Exception {
		Logger log = Logger.getLogger(Function902988.class);
		ResultVo result = new ResultVo();
		
		String product_id = this.getStrParameter("product_id");
		
		if(StringHelper.isEmpty(product_id)){
			result.setErrorMsg("product_id is null!");
			result.setErrorNo(-90298801);
			throw new InvokeException(-1,"product_id is null!");
		}
		ProductPropertyService pps = new ProductPropertyService();
		log.info("查询基金产品信息开始");
		DataRow data = new DataRow() ;
		try {
			data = pps.queryProductDetail(0, product_id);//查金融产品表
			log.info("info:  " + JSONUtil.getJsonString4JavaPOJO(data));
			if(data==null||data.size()<=0){
				result.setErrorMsg("未找到该产品信息!");
				result.setErrorNo(-90298802);
				throw new InvokeException(-902030202, "未找到该产品信息!");
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
				log.info("recommend:  " + JSONUtil.getJsonString4JavaPOJO(data));
				
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
					log.info("recommend2:  " + JSONUtil.getJsonString4JavaPOJO(data));
				}
			}

			/**************查询产品及其序号************/
			List<Map<String,Object>> productSorts = pps.queryProductAndSorts("0",1);
			if(productSorts!=null){
				String str = "";
				for(Map<String,Object> map:productSorts){//
					str += String.valueOf(map.get("product_id"))+"=="+String.valueOf(map.get("product_name"))+"=="+String.valueOf(map.get("recommend_sort"))+"=="+String.valueOf(map.get("recommend_type")) + ",";
				}
				if(!StringHelper.isEmpty(str)){
					str = str.substring(0, str.length()-1);
				}
				data.put("productSorts", str);
				log.info("productSorts:  " + JSONUtil.getJsonString4JavaPOJO(data));
			}
			
			/**************查询上架产品及其排序值************/
			List<Map<String,Object>> product_Sorts = pps.queryProductSort(0);
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
				log.info("product_shelf_Sorts:  " + JSONUtil.getJsonString4JavaPOJO(data));
			}
			/*******查询图片*******/
			List<Map<String,Object>> productImgs = pps.queryProductImgs(product_id,"0");
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
					log.info("all_img:  " + JSONUtil.getJsonString4JavaPOJO(data));
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
				log.info("fund_managers:  " + JSONUtil.getJsonString4JavaPOJO(data));
			}
		} catch (Exception e) {
			result.setErrorMsg(e.getMessage() + "查询失败!content:-----"+JSONUtil.getJsonString4JavaPOJO(data));
			result.setErrorNo(-90298803);
			e.printStackTrace();
			throw new InvokeException(-90298803,"查询失败!content:-----"+JSONUtil.getJsonString4JavaPOJO(data));
		}
		
		//data.set("abc", "[aaaa]");
		//data.set("abc", "sdfsf{[aaaa}fdsaf");
		//String desc = data.getString("product_description");
		//System.out.println(desc);
		//data.remove("product_description");
		//data.put("product_description", new String(desc.getBytes()));
		//System.out.println("==================================================");
		//System.out.println(data.get("product_description"));
		result.setResult(data);
		return result;
	}

}

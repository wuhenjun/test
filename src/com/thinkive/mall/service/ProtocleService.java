package com.thinkive.mall.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.project.utils.Constants;
import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.jdbc.JdbcTemplate;
import com.thinkive.base.jdbc.session.Session;
import com.thinkive.base.service.BaseService;

/**"web"
 * 描述: 
 * 版权: Copyright (c) 2013
 * 公司: 思迪科技 
 * 作者: 
 * 版本: 1.0 
 * 创建日期: May 8, 2014 
 * 创建时间: 2:19:08 PM
 */
public class ProtocleService extends BaseService
{
	
	private JdbcTemplate getJdbcTemplate()
	{
		return getJdbcTemplate(Constants.DB_ID);
	}
	
	@SuppressWarnings("unchecked")
	public void insertProtocle(DataRow dataRow)
	{
		
		if(null == dataRow){
			return ;
		}
		Session session = null;
		//先删除，再添加
		try {
			session = this.getSession(Constants.DB_ID);
			session.beginTrans();
			String article_id = getSeqValue(Constants.DB_ID,"t_mall_article");
			dataRow.put("AGREEMENT_ID", getSeqValue(Constants.DB_ID,"T_MALL_AGREEMENT")) ;
			dataRow.put("article_id", article_id);
			session.insert("T_MALL_AGREEMENT", dataRow) ;
			
			DataRow articleDataRow = new DataRow();
			articleDataRow.put("article_id", article_id);
			articleDataRow.put("article_title", dataRow.get("agreement_title"));
			articleDataRow.put("article_content", dataRow.get("agreement_content"));
			articleDataRow.put("article_url", dataRow.get("url"));
			articleDataRow.put("create_time", dataRow.get("create_time"));
			articleDataRow.put("article_state", dataRow.get("is_valid"));
			session.insert("t_mall_article", articleDataRow) ;
			session.commitTrans();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
				session=null ;
			}
		}
	}
	
	
	public void batchUpdateProductPrototol(String old_agreement_id, String new_agreement_id, boolean isUpdateAll,List<String> product_id_list){
		if(null != old_agreement_id && null != new_agreement_id && old_agreement_id.equals(new_agreement_id)){
			return ;
		}
		
		Session session = null;
		try {
			session = this.getSession(Constants.DB_ID);
			session.beginTrans();
			//查出所有上架的且与旧协议有关联的产品id
			String[] product_ids = null;
			if(isUpdateAll){  //更新全部产品协议
				//删除原先关联协议
				if(null != old_agreement_id && old_agreement_id.matches("\\d+")){
					product_ids = session.queryStringArray("select distinct(product_id) from t_mall_product_agreement t where t.agreement_id=? and t.product_id in(select product_id from t_Mall_Product_Financial where product_shelf='1' union select product_id from t_mall_product_not_financial  where product_shelf='1')", new String[]{old_agreement_id });
					session.update("delete from t_mall_product_agreement t where t.agreement_id=? and t.product_id in(select distinct(product_id) from t_Mall_Product_Financial where product_shelf='1' union select product_id from t_mall_product_not_financial  where product_shelf='1')",new String[]{old_agreement_id });
					//session.delete("t_mall_product_agreement", "agreement_id", old_agreement_id);
				}else{
					product_ids = session.queryStringArray("select distinct(product_id) from t_Mall_Product_Financial where product_shelf='1' union select distinct(product_id) from t_mall_product_not_financial  where product_shelf='1'");
				}
				//更换新协议
				if(null != new_agreement_id && new_agreement_id.matches("\\d+")){
					session.update("delete from t_mall_product_agreement t where t.agreement_id=? and t.product_id in(select product_id from t_Mall_Product_Financial where product_shelf='1' union select product_id from t_mall_product_not_financial  where product_shelf='1')",new String[]{new_agreement_id });
					for(Object o:product_ids){
						String id = (String)o;
						DataRow dataRow = new DataRow();
						dataRow.put("product_id", o);
						dataRow.put("agreement_id", new_agreement_id);
						session.insert("t_mall_product_agreement", dataRow);
					}
				}
			}else{
				Set<String> set = new HashSet<String>();  //保存所有选择产品的id
				StringBuffer all = new StringBuffer();
				for(String str: product_id_list){
					String[] ids = str.split(",");
					for(String s:ids){
						if(s.matches("\\d+")){
							set.add(s);
						}
					}
				}
				
				for(String s:set){
					all.append("," + s);
				}
				
				//删除原先关联协议
				if(null != old_agreement_id && old_agreement_id.matches("\\d+")){
					if(set.size() > 0){
						session.update("delete from t_mall_product_agreement t where t.agreement_id=? and t.product_id in(" + all.substring(1) + ")",new String[]{old_agreement_id });
					}
				}
				//更换新协议
				if(null != new_agreement_id && new_agreement_id.matches("\\d+")){
					session.update("delete from t_mall_product_agreement t where t.agreement_id=? and t.product_id in(" + all.substring(1) + ")",new String[]{new_agreement_id });
					for(String o:set){
						DataRow dataRow = new DataRow();
						dataRow.put("product_id", o);
						dataRow.put("agreement_id", new_agreement_id);
						session.insert("t_mall_product_agreement", dataRow);
					}
				}
			}
			
			session.commitTrans();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
				session=null ;
			}
		}
	}
	
}

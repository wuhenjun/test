package com.thinkive.mall.service;

import com.project.utils.Constants;
import com.thinkive.base.jdbc.JdbcTemplate;
import com.thinkive.base.jdbc.session.Session;
import com.thinkive.base.service.BaseService;
import com.thinkive.base.util.StringHelper;

public class ProductDeleteService extends BaseService{
	public JdbcTemplate getJdbcTemplate(){
		return this.getJdbcTemplate(Constants.DB_ID);
	}
	
	//删除基金费率
	public void deleteFundFeerate(String product_id,String feerate_type) throws Exception{
		if(!StringHelper.isEmpty(product_id) && !StringHelper.isEmpty(feerate_type)){
			if(product_id.contains(",") && feerate_type.contains(",")){
				String[] product_ids = product_id.split(",");
				String[] feerate_types = feerate_type.split(",");
				if(product_ids.length == feerate_types.length){
					Session session = null;
					try{
						session = this.getSession(Constants.DB_ID);
						session.beginTrans();
						String s1 = null,s2 = null,sql = null;
						for(int i=0; i < product_ids.length; i++){
							s1 = product_ids[i];
							s2 = feerate_types[i];
							sql = "DELETE FROM T_MALL_FUND_FEERATE WHERE product_id = ? and feerate_type = ?";
							session.update(sql, new String[]{s1,s2});
						}
						session.commitTrans();
					}catch(Exception e){
						e.printStackTrace();
						session.rollbackTrans();
					}finally{
						if(null != session){
							session.close();
							session = null;
						}
					}
					
				}
			}else{
				getJdbcTemplate().update("DELETE FROM T_MALL_FUND_FEERATE WHERE product_id = ? and feerate_type = ?", new String[]{product_id,feerate_type });
			}
		}
		
	}
	
	//删除资产配置
	public void deleteAssetallocation(String product_id, String secu_category_code)
			throws Exception {
		if (!StringHelper.isEmpty(product_id)
				&& !StringHelper.isEmpty(secu_category_code)) {
			if (product_id.contains(",") && secu_category_code.contains(",")) {
				String[] product_ids = product_id.split(",");
				String[] feerate_types = secu_category_code.split(",");
				if (product_ids.length == feerate_types.length) {
					Session session = null;
					try {
						session = this.getSession(Constants.DB_ID);
						session.beginTrans();
						String s1 = null, s2 = null, sql = null;
						for (int i = 0; i < product_ids.length; i++) {
							s1 = product_ids[i];
							s2 = feerate_types[i];
							sql = "DELETE FROM T_MALL_FUND_ASSETALLOCATION WHERE product_id = ? and secu_category_code = ?";
							session.update(sql, new String[] { s1, s2 });
						}
						session.commitTrans();
					} catch (Exception e) {
						e.printStackTrace();
						session.rollbackTrans();
					} finally {
						if (null != session) {
							session.close();
							session = null;
						}
					}

				}
			} else {
				getJdbcTemplate().update("DELETE FROM T_MALL_FUND_ASSETALLOCATION WHERE product_id = ? and secu_category_code = ?",
								new String[] { product_id, secu_category_code });
			}
		}

	}
	
	
	//删除产品组合推荐
	public void deleteProductComp(String product_id, String comp_ecommend_type)
			throws Exception {
		if (!StringHelper.isEmpty(product_id)
				&& !StringHelper.isEmpty(comp_ecommend_type)) {
			if (product_id.contains(",") && comp_ecommend_type.contains(",")) {
				String[] product_ids = product_id.split(",");
				String[] feerate_types = comp_ecommend_type.split(",");
				if (product_ids.length == feerate_types.length) {
					Session session = null;
					try {
						session = this.getSession(Constants.DB_ID);
						session.beginTrans();
						String s1 = null, s2 = null, sql = null;
						for (int i = 0; i < product_ids.length; i++) {
							s1 = product_ids[i];
							s2 = feerate_types[i];
							sql = "DELETE FROM T_MALL_PRODUCT_COMP_RECOMMEND WHERE product_id = ? and comp_ecommend_type = ?";
							session.update(sql, new String[] { s1, s2 });
						}
						session.commitTrans();
					} catch (Exception e) {
						e.printStackTrace();
						session.rollbackTrans();
					} finally {
						if (null != session) {
							session.close();
							session = null;
						}
					}

				}
			} else {
				getJdbcTemplate()
						.update("DELETE FROM T_MALL_PRODUCT_COMP_RECOMMEND WHERE product_id = ? and comp_ecommend_type = ?",
								new String[] { product_id, comp_ecommend_type });
			}
		}

	}
}

package com.thinkive.mall.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.project.utils.Constants;
import com.project.utils.JSONUtil;
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
public class ProductInfoService extends BaseService
{
	Logger log = Logger.getLogger(ProductInfoService.class);
	private JdbcTemplate getJdbcTemplate()
	{
		return getJdbcTemplate(Constants.DB_ID);
	}
	
	@SuppressWarnings("unchecked")
	public void insertProductInfo(DataRow dataRow)
	{
		
		if(null == dataRow){
			return ;
		}
		Session session = null;
		//
		try {
			session = this.getSession(Constants.DB_ID);
			session.beginTrans();
			log.info("插入资讯产品开始");
			String serviceType = dataRow.getString("serviceType");
			log.info("---serviceType:"  + serviceType);
			dataRow.remove("serviceType");
			log.info(JSONUtil.FormatToJsonstr(dataRow));
			String product_id = getSeqValue(Constants.DB_ID,"T_MALL_PRODUCT");
			dataRow.put("product_id", product_id) ;
			session.insert("t_mall_product_not_financial", dataRow) ;
			log.info("插入资讯产品成功");
			DataRow product_infor_dr = new DataRow();
			product_infor_dr.put("product_id", product_id);
			product_infor_dr.put("info_type", "");
			session.insert("t_mall_product_infor", product_infor_dr) ;
			log.info("插入资讯产品成功2");
			//产品服务方式的添加
			if(null != serviceType && !serviceType.trim().equals("")){
				String[] type_ids = serviceType.trim().split("===");
				log.info("serviceType:   " + serviceType);
				for(String id:type_ids){
					if(null != id){
						DataRow service = new DataRow();
						service.put("SERVICE_TYPE", id);
						service.put("PRODUCT_ID", product_id);
						service.put("is_default", 0);
						service.put("is_must", 0);
						log.info("type: " + id + "  product_id:  " + product_id);
						session.insert("t_mall_product_service", service);
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
	
	
	public void deleteProductInfor(String product_id){
		Session session = null;
		//
		try {
			session = this.getSession(Constants.DB_ID);
			session.beginTrans();
			String[] ids = product_id.split(",");
			for(String id : ids){
				session.delete("t_mall_product_not_financial", "product_id", id);
				session.delete("t_mall_product_infor", "product_id", id);
				session.delete("t_mall_product_service", "product_id", id);
				session.delete("t_mall_price", "product_id", id);
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

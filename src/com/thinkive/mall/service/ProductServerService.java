package com.thinkive.mall.service;

import com.project.utils.Constants;
import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.jdbc.JdbcTemplate;
import com.thinkive.base.jdbc.session.Session;
import com.thinkive.base.service.BaseService;

public class ProductServerService extends BaseService{
	
	private JdbcTemplate getJdbcTemplate()
	{
		return getJdbcTemplate(Constants.DB_ID);
	}
	
	//查询服务产品详情
	public DataRow queryProductServer(String product_id){
		DataRow data = null ;
		//data = getJdbcTemplate().queryMap("select t1.server_type,t2.* from  T_MALL_PRODUCT_SERVER t1 left join  T_MALL_PRODUCT_NOT_FINANCIAL t2 on t1.product_id=t2.product_id where t1.product_id='"+product_id+"'");
		getJdbcTemplate().queryMap("select t1.server_type,t2.* from  T_MALL_PRODUCT_SERVER t1 left join  T_MALL_PRODUCT_NOT_FINANCIAL t2 on t1.product_id=t2.product_id where t1.product_id=?",new String[]{product_id});
		return data ;
	}
	
	//修改服务产品信息
	public void updateProductServer(String product_id,DataRow data1,DataRow data2) throws Exception{
		getJdbcTemplate().update("t_mall_product_not_financial", data1, "product_id", product_id);//修改到非金融产品表

	}
	
	//删除服务产品
	public void deleteProductServer(String product_id) throws Exception{
		Session session = null ;
		try {
			session = getSession(Constants.DB_ID);
			session.beginTrans();
			session.delete("t_mall_product_server", "product_id",product_id);//添加到服务产品表
			session.delete("t_mall_product_not_financial", "product_id",product_id);//添加到非金融产品表
			session.commitTrans();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollbackTrans();
			throw new Exception("删除失败,"+e.getMessage());
		}finally{
			if(session!=null){
				session.close();
				session=null ;
			}
		}
	}
	
	//判断产品代号是否已存在
	public int isProductCODE(String product_code){
		//return getJdbcTemplate().queryInt("select count(*) n from T_MALL_PRODUCT_NOT_FINANCIAL where product_code='"+product_code+"' ");
		return getJdbcTemplate().queryInt("select count(*) n from T_MALL_PRODUCT_NOT_FINANCIAL where product_code=?",new String[]{product_code});
	}
	
	//添加服务产品
	public void addProductServer(DataRow data1,DataRow data2) throws Exception{
		Session session = null ;
		try {
			session = getSession(Constants.DB_ID);
			session.beginTrans();
			session.insert("t_mall_product_not_financial", data1);//添加到非金融产品表
			session.insert("t_mall_product_server", data2);//添加到服务产品表
			session.commitTrans();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollbackTrans();
			throw new Exception("添加失败,"+e.getMessage());
		}finally{
			if(session!=null){
				session.close();
				session=null ;
			}
		}
	}
}

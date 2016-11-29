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
	
	//��ѯ�����Ʒ����
	public DataRow queryProductServer(String product_id){
		DataRow data = null ;
		//data = getJdbcTemplate().queryMap("select t1.server_type,t2.* from  T_MALL_PRODUCT_SERVER t1 left join  T_MALL_PRODUCT_NOT_FINANCIAL t2 on t1.product_id=t2.product_id where t1.product_id='"+product_id+"'");
		getJdbcTemplate().queryMap("select t1.server_type,t2.* from  T_MALL_PRODUCT_SERVER t1 left join  T_MALL_PRODUCT_NOT_FINANCIAL t2 on t1.product_id=t2.product_id where t1.product_id=?",new String[]{product_id});
		return data ;
	}
	
	//�޸ķ����Ʒ��Ϣ
	public void updateProductServer(String product_id,DataRow data1,DataRow data2) throws Exception{
		getJdbcTemplate().update("t_mall_product_not_financial", data1, "product_id", product_id);//�޸ĵ��ǽ��ڲ�Ʒ��

	}
	
	//ɾ�������Ʒ
	public void deleteProductServer(String product_id) throws Exception{
		Session session = null ;
		try {
			session = getSession(Constants.DB_ID);
			session.beginTrans();
			session.delete("t_mall_product_server", "product_id",product_id);//��ӵ������Ʒ��
			session.delete("t_mall_product_not_financial", "product_id",product_id);//��ӵ��ǽ��ڲ�Ʒ��
			session.commitTrans();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollbackTrans();
			throw new Exception("ɾ��ʧ��,"+e.getMessage());
		}finally{
			if(session!=null){
				session.close();
				session=null ;
			}
		}
	}
	
	//�жϲ�Ʒ�����Ƿ��Ѵ���
	public int isProductCODE(String product_code){
		//return getJdbcTemplate().queryInt("select count(*) n from T_MALL_PRODUCT_NOT_FINANCIAL where product_code='"+product_code+"' ");
		return getJdbcTemplate().queryInt("select count(*) n from T_MALL_PRODUCT_NOT_FINANCIAL where product_code=?",new String[]{product_code});
	}
	
	//��ӷ����Ʒ
	public void addProductServer(DataRow data1,DataRow data2) throws Exception{
		Session session = null ;
		try {
			session = getSession(Constants.DB_ID);
			session.beginTrans();
			session.insert("t_mall_product_not_financial", data1);//��ӵ��ǽ��ڲ�Ʒ��
			session.insert("t_mall_product_server", data2);//��ӵ������Ʒ��
			session.commitTrans();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollbackTrans();
			throw new Exception("���ʧ��,"+e.getMessage());
		}finally{
			if(session!=null){
				session.close();
				session=null ;
			}
		}
	}
}

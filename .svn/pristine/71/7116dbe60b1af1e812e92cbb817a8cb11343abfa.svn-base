package com.thinkive.mall.service;

import java.util.List;
import java.util.Map;

import com.project.utils.Constants;
import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.jdbc.JdbcTemplate;
import com.thinkive.base.jdbc.session.Session;
import com.thinkive.base.service.BaseService;
import com.thinkive.base.service.SequenceGenerator;

public class ArticleManageService extends BaseService{
	
	private JdbcTemplate getJdbcTemplate()
	{
		return getJdbcTemplate(Constants.DB_ID);
	}
	
	//更新金融产品信息
	public void updateProductMoney(DataRow data,String product_id){
		getJdbcTemplate().update("T_MALL_PRODUCT_MONEY", data, "product_id", product_id);
	}
	
	// 更新金融产品信息
	public void updateProductFinance(DataRow data, String product_id) {
		getJdbcTemplate().update("T_MALL_PRODUCT_FINANCIAL", data,"product_id", product_id);
	}
	//查询理财产品详情
	public DataRow queryArticle(String product_id){
		DataRow data = null ;
		String sql = "select t1.fina_type,t1.join_guide,t2.*,t3.*,(case when(select count(*)n  from T_MALL_PRODUCT_RECOMMEND where t1.product_id=product_id) >0 then 1 else 0 end)is_recommend from  T_MALL_PRODUCT_MONEY t1 left join  T_MALL_PRODUCT_FINANCIAL t2 on t1.product_id=t2.product_id left join t_mall_img t3 on t1.product_id=t3.img_vesting where t1.product_id = ?";
		data = getJdbcTemplate().queryMap(sql,new String[]{product_id});
		return data ;
	}
	
	//查询所有产品推荐序号列表
	public List<Map<String,Object>> queryProductAndSorts(){
		return getJdbcTemplate().query("select t2.product_id,t2.product_name,TO_NUMBER(t3.recommend_sort) recommend_sort,t3.recommend_type from T_MALL_PRODUCT_FINANCIAL t2 ,T_MALL_PRODUCT_RECOMMEND t3  where t2.product_id=t3.product_id and t3.product_type='1' order by recommend_sort,t3.recommend_type");
	}
}

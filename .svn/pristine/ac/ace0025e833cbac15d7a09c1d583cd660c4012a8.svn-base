package com.thinkive.mall.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.project.utils.Constants;
import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.jdbc.JdbcTemplate;
import com.thinkive.base.jdbc.session.Session;
import com.thinkive.base.service.BaseService;
import com.thinkive.base.service.SequenceGenerator;
import com.thinkive.base.util.StringHelper;


public class ProductPropertyService extends BaseService{
	Logger log = Logger.getLogger(ProductPropertyService.class);
	/**
	 * 
	 * @param dataRow   数据包装集
	 * @param pojoName  表名
	 * @param id         主键名称
	 * @param id_value    主键值
	 */
	public JdbcTemplate getJdbcTemplate(){
		return this.getJdbcTemplate(Constants.DB_ID);
	}
	
	//更新非金融产品信息
	public void updateProductNotFinance(DataRow data,String product_id){
		log.info("更新非金融产品信息开始...." );
		getJdbcTemplate().update("T_MALL_PRODUCT_NOT_FINANCIAL" ,data, "product_id" ,product_id);
		log.info("更新非金融产品信息结束...." );
	}
	
	//更新金融产品信息
	public void updateProductFinance(DataRow data,String product_id){
		log.info("更新金融产品信息开始...." );
		getJdbcTemplate().update("T_MALL_PRODUCT_FINANCIAL" ,data, "product_id" ,product_id);
		log.info("更新金融产品信息结束...." );
	}
	
	public void updateProduct(DataRow data,String product_id, int type){
		String product_name = type==0?"T_MALL_PRODUCT_FUND":"T_MALL_PRODUCT_MONEY";
		getJdbcTemplate().update(product_name ,data, "product_id" ,product_id);
	}
	
	//更新推荐方式
	public void updateProductRecommend(String recommend_,String recommend_sort_,String product_id,String product_type) throws Exception{
		Session session = null;
		//先删除，再添加
		try {
			session = this.getSession(Constants.DB_ID);
			session.beginTrans();
			log.info("更新更新推荐方式开始...." );
			session.delete("T_MALL_PRODUCT_RECOMMEND", "product_id", product_id);
			
			if(!StringHelper.isEmpty(recommend_) && !StringHelper.isEmpty(recommend_sort_)){
				String[] recommends = StringHelper.split(recommend_, ",");
				String[] recommend_sorts = StringHelper.split(recommend_sort_, ",");
				
				for(int i=0,j=recommends.length;i<j;i++){
					DataRow data = new DataRow();
					data.put("product_id", product_id);
					data.put("recommend_type", recommends[i]);
					data.put("product_type", product_type);  //0-基金  1-理财 2-服务  3-资讯
					data.put("recommend_sort", recommend_sorts[i]);  //推荐排序
					session.insert("T_MALL_PRODUCT_RECOMMEND", data);
				}
			}
			log.info("更新更新推荐方式结束...." );
			session.commitTrans();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("更新推荐方式失败!"+e.getMessage());
		}finally{
			if(session!=null){
				session.close();
				session=null ;
			}
		}
	}
	
	//更新服务方式
	public void updateProductService(String service_type_,String product_id){
		Session session = null;
		//先删除，再添加
		try {
			session = this.getSession(Constants.DB_ID);
			session.beginTrans();
			log.info("更新服务方式开始...." );
			session.delete("T_MALL_PRODUCT_SERVICE", "product_id", product_id);
			if(!StringHelper.isEmpty(service_type_)){
				String[] service_types = StringHelper.split(service_type_, ",");
				for(String service:service_types){
					if(!StringHelper.isEmpty(service)){
						DataRow data = new DataRow();
						data.put("product_id", product_id);
						data.put("service_type", service.replace("$@#", ","));
						session.insert("T_MALL_PRODUCT_SERVICE", data);
					}
					
				}
			}
			log.info("更新服务方式结束...." );
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
	
	
	//更新服务方式
		public void updateProductServices(List<String[]> list,String product_id){
			Session session = null;
			//先删除，再添加
			try {
				session = this.getSession(Constants.DB_ID);
				session.beginTrans();
				log.info("更新服务方式开始1...." );
				session.delete("T_MALL_PRODUCT_SERVICE", "product_id", product_id);
				for(String[] s:list){
					if(s.length == 4){
						DataRow data = new DataRow();
						data.put("product_id", product_id);
						data.put("service_type", s[0].replace("$@#", ","));
						data.put("IS_DEFAULT", s[1]);
						data.put("IS_MUST", s[2]);
						data.put("PRIORITY", s[3]);
						session.insert("T_MALL_PRODUCT_SERVICE", data);
					}
					
				}
				log.info("更新服务方式结束1...." );
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
	//更新价格规格
	public void updatePriceRules(String discounts,String priority_sort_,String product_id){
		Session session = null;
		//先删除，再添加
		try {
			session = this.getSession(Constants.DB_ID);
			session.beginTrans();
			log.info("更新价格规格开始...." );
			//删除 产品与价格规则对应表中相关信息
			session.delete("T_MALL_PRICE", "PRODUCT_ID", product_id);
			
			if(!StringHelper.isEmpty(discounts) && !StringHelper.isEmpty(priority_sort_)){
				String[] rules = StringHelper.split(discounts, ",");
				String[] priority_sorts = StringHelper.split(priority_sort_, ",");
				//新增 产品与价格规则对应表
				for(int i=0,j=rules.length;i<j;i++){
					String discount_id =  SequenceGenerator.getInstance().getNextSequence("web", "T_MALL_PRICE");
					
					String img_vesting = product_id;//图片归属对应产品ID号
					DataRow data = new DataRow();
					data.put("discount_id", discount_id);
					data.put("rules_id", rules[i]);
					data.put("product_id", product_id);
					data.put("priority", priority_sorts[i]);//优先级
					session.insert("T_MALL_PRICE", data);
				}
			}
			log.info("更新价格规格结束...." );
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
	
	//更新图片
	public void updateImg(String img_url_,String img_type_ ,String product_id,String img_class){
		Session session = null;
		//先删除，再添加
		try {
			session = this.getSession(Constants.DB_ID);
			session.beginTrans();
			log.info("更新图片开始...." );
			//session.delete("T_MALL_IMG", "img_vesting", product_id);
			String sql = "delete from T_MALL_IMG where img_vesting = ? and img_class = ? ";
			session.update(sql, new Object[]{product_id, img_class});
			
			if(!StringHelper.isEmpty(img_url_) && !StringHelper.isEmpty(img_type_)){
				String[] imgs = StringHelper.split(img_url_, ",");
				String[] img_types = StringHelper.split(img_type_, ",");
				for(int i=0,j=imgs.length;i<j;i++){
					String img_type = "";
					String img_url = "";
					img_type = img_types[i];
					img_url = imgs[j-i-1];
					
					if(!img_url.startsWith("/mall")){
						img_url = "/mall"+img_url;
					}
					String img_id =  SequenceGenerator.getInstance().getNextSequence("web", "T_MALL_IMG");
					String img_vesting = product_id;//图片归属对应产品ID号
					DataRow imgData = new DataRow();
					imgData.put("img_id", img_id);
					imgData.put("img_type", img_type);//图片类别
					imgData.put("img_class", img_class);//图片所属分类
					imgData.put("img_url", img_url);//图片url
					imgData.put("img_vesting", img_vesting);//图片归属  即产品ID
					session.insert("T_MALL_IMG", imgData);
				}
			}
			log.info("更新图片结束...." );
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
	
	//更新营销品牌数据
	public void updateBrand(String product_id,String brand_id){
		Session session = null;
		try {
			session = this.getSession(Constants.DB_ID);
			session.beginTrans();
			log.info("更新营销品牌数据开始...." );
			//删除营销属性表中相关数据
			session.delete("t_mall_marketing_attr", "product_id", product_id);
			if(!StringHelper.isEmpty(brand_id)){
				DataRow data = new DataRow();
				data.set("product_id", product_id);
				data.set("product_type", "3");//product_type	产品子类别 3表示资讯
				data.set("brond_id", brand_id);//品牌ID
				session.insert("t_mall_marketing_attr", data);
			}
			log.info("更新营销品牌数据结束...." );
			session.commitTrans();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
				session=null ;
			}
		}
	}
	
	//查询产品详情
	public DataRow queryProductDetail(int type, String product_id){
		log.info("查询产品详情开始...." );
		String sql = null;
		if(type == 0){ //基金
			/**
			 * product_id
product_shelf
purchase_rates
product_sort
product_description
			 */
			sql = "select t1.product_id,t2.product_shelf,t1.purchase_rates,t1.fund_type,t2.product_sort,t2.product_description from T_MALL_PRODUCT_FUND t1,T_MALL_PRODUCT_FINANCIAL t2 where t1.product_id = t2.product_id and t1.product_id=?";
			//sql = "select t1.fund_type,t1.purchase_rates,t2.*,t3.*,(case when(select count(*) n  from T_MALL_PRODUCT_RECOMMEND where t1.product_id=product_id) >0 then 1 else 0 end) is_recommend from  T_MALL_PRODUCT_FUND t1 left join  T_MALL_PRODUCT_FINANCIAL t2 on t1.product_id=t2.product_id left join t_mall_img t3 on t1.product_id=t3.img_vesting where t1.product_id=?";
		}else if(type == 1){ //理财
			sql = "select t1.fina_type,t1.investment_horizon,t1.earnings,t1.fina_belongs,t1.product_special,t1.income_certificate,t1.join_guide,t1.is_show_income,t2.*,t3.*,(case when(select count(*)n  from T_MALL_PRODUCT_RECOMMEND where t1.product_id=product_id) >0 then 1 else 0 end)is_recommend from  T_MALL_PRODUCT_MONEY t1 left join  T_MALL_PRODUCT_FINANCIAL t2 on t1.product_id=t2.product_id left join t_mall_img t3 on t1.product_id=t3.img_vesting where t1.product_id=?";
		}else if(type == 2){ //咨询
			sql = "select t1.INFO_TYPE,t2.*,t3.*,t4.brond_id,(case when(select count(*)n  from T_MALL_PRODUCT_RECOMMEND where t1.product_id=product_id) >0 then 1 else 0 end)is_recommend from  T_MALL_PRODUCT_INFOR t1 left join  T_MALL_PRODUCT_NOT_FINANCIAL t2 on t1.product_id=t2.product_id left join t_mall_img t3 on t1.product_id=t3.img_vesting left join T_MALL_MARKETING_ATTR t4 on t1.product_id=t4.product_id where t1.product_id=?";
		}else{ //服务
			sql = "select t1.server_type,t2.* from  T_MALL_PRODUCT_SERVER t1 left join  T_MALL_PRODUCT_NOT_FINANCIAL t2 on t1.product_id=t2.product_id where t1.product_id=?";
		}
		DataRow data = null ;
		data = this.getJdbcTemplate().queryMap(sql,new String[]{product_id });
		log.info("查询产品详情结束...." );
		return data ;
	}
	
	//查询已上架产品排序值
	public List<Map<String,Object>> queryProductSort(int product_type){
		log.info("查询已上架产品排序值开始...." );
		String sql = null;
		switch(product_type){
		case 0:
			sql = "select product_name,product_sort from T_MALL_PRODUCT_FINANCIAL t where t.product_sub_type='0' and t.product_shelf='1' and t.product_sort is not null order by t.product_sort asc";
			break;
		case 1:
			sql = "select product_name,product_sort from T_MALL_PRODUCT_FINANCIAL t where t.product_sub_type='1' and t.product_shelf='1' and t.product_sort is not null order by t.product_sort asc";
			break;
		case 2:
			sql = "select product_name,product_sort from T_MALL_PRODUCT_NOT_FINANCIAL t where t.product_sub_type='2' and t.product_shelf='1' and t.product_sort is not null order by t.product_sort asc";
			break;
		case 3:
			sql = "select product_name,product_sort from T_MALL_PRODUCT_NOT_FINANCIAL t where t.product_sub_type='3' and t.product_shelf='1' and t.product_sort is not null order by t.product_sort asc";
			break;
			default:
				return null;
		}
		List<Map<String,Object>> list = null ;
		list = this.getJdbcTemplate().query(sql);
		log.info("查询已上架产品排序值结束...." );
		return list ;
	}
	//获取服务方式列表
	public List<Map<String,Object>> queryProductServices(String product_id){
		log.info("获取服务方式列表开始...." );
		List<Map<String,Object>> list = null ;
		list = this.getJdbcTemplate().query("select * from T_MALL_PRODUCT_SERVICE where product_id=?",new String[]{product_id });
		log.info("获取服务方式列表结束...." );
		return list ;
	}
	//获取推荐类型列表
	public List<Map<String,Object>> queryProductRecommends(String product_id){
		log.info("获取推荐类型列表开始...." );
		List<Map<String,Object>> list = null ;
		list = this.getJdbcTemplate().query("select t1.*,t2.item_name from T_MALL_PRODUCT_RECOMMEND t1 left join (select * from t_enum_value where enum_type_id=38) t2 on t1.recommend_type=t2.item_value where t1.product_id=?",new String[]{product_id });
		log.info("获取推荐类型列表结束...." );
		return list ;
	}
	//查询所有推荐类型
	public List<Map<String,Object>> queryRecommends(){
		log.info("查询所有推荐类型开始...." );
		return this.getJdbcTemplate().query("select * from t_enum_value where enum_type_id=38");
	}
	//查询所有产品推荐序号列表
	public List<Map<String,Object>> queryProductAndSorts(String product_type,int financeType){
		log.info("查询所有产品推荐序号列表开始...." );
		if(financeType == 0)
			return this.getJdbcTemplate().query("select t2.product_id,t2.product_name,TO_NUMBER(t3.recommend_sort) recommend_sort,t3.recommend_type from T_MALL_PRODUCT_NOT_FINANCIAL t2 ,T_MALL_PRODUCT_RECOMMEND t3  where t2.product_id=t3.product_id and t3.product_type=? order by recommend_sort,t3.recommend_type",new String[]{product_type });
		else
			return this.getJdbcTemplate().query("select t2.product_id,t2.product_name,TO_NUMBER(t3.recommend_sort) recommend_sort,t3.recommend_type from T_MALL_PRODUCT_FINANCIAL t2 ,T_MALL_PRODUCT_RECOMMEND t3  where t2.product_id=t3.product_id and t3.product_type=? order by recommend_sort,t3.recommend_type",new String[]{product_type });
	} 
	
	
	//查询价格规格
	public List<Map<String,Object>> queryPriceRuls(){ 
		log.info("查询价格规格开始...." );
		return this.getJdbcTemplate().query("select t1.*,t1.rules_price||'元/'||t2.item_name item_name from T_MALL_PRICE_RULES t1 left  join (select * from t_enum_value where enum_type_id=2) t2 on t1.rules_unit=t2.item_value where t1.end_time is null or (sysdate-to_date(t1.end_time,'yyyy-mm-dd hh24:mi:ss') <= 0)");
	}
	//查询已添加价格规格
	public List<Map<String,Object>> queryProductPriceRuls(String product_id){
		log.info("查询已添加价格规格开始...." );
		return this.getJdbcTemplate().query("select t1.*,t2.*,t2.rules_price||'元/'||t3.item_name item_name from T_MALL_PRICE t1 left join T_MALL_PRICE_RULES t2 on t1.rules_id=t2.rules_id  left  join (select * from t_enum_value where enum_type_id=2) t3 on t2.rules_unit=t3.item_value where t1.product_id=?",new String[]{product_id });
	}
	
	//查询图片类型
	public List<Map<String,Object>> queryImgTypes(){
		log.info("查询图片类型开始...." );
		return this.getJdbcTemplate().query("select * from t_enum_value where enum_type_id=39");
	}
	//查询已添加图片
	public List<Map<String,Object>> queryProductImgs(String product_id, String img_class){
		log.info("查询已添加图片开始...." );
		return this.getJdbcTemplate().query("select t1.*,t2.* from T_MALL_IMG t1 left join (select * from t_enum_value where enum_type_id=39) t2 on t1.img_type=t2.item_value where t1.img_class=? and t1.img_vesting=?" , new String[]{img_class,product_id});
	}
	
	//查询产品基金经理
	public List<Map<String,Object>> queryProductFundManager(String product_id){
		log.info("查询产品基金经理开始...." );
		return this.getJdbcTemplate().query("select fund_manager_id,fund_manager_name from T_MALL_PRODUCT_AND_MANAGER t1,T_MALL_PRODUCT_FUND_MANAGER t2 where t1.MANAGER_ID=t2.FUND_MANAGER_ID and t1.product_id=?" , new String[]{product_id });
	}
	//查询已关联协议
	public List<Map<String,Object>> queryProductAgreement(String product_id){
		log.info("查询已关联协议开始...." );
		return this.getJdbcTemplate().query("select agreement_id from T_MALL_PRODUCT_AGREEMENT where product_id=?" , new String[]{product_id });
	}
	
	//更新关联协议
		public void updateProductAgreement(String agreement_,String product_id){
			Session session = null;
			//先删除，再添加
			try {
				session = this.getSession(Constants.DB_ID);
				session.beginTrans();
				log.info("更新关联协议开始...." );
				session.delete("T_MALL_PRODUCT_AGREEMENT", "product_id", product_id);
				if(!StringHelper.isEmpty(agreement_)){
					String[] service_types = StringHelper.split(agreement_, ",");
					for(String agreement_id:service_types){
						if(!StringHelper.isEmpty(agreement_id)){
							DataRow data = new DataRow();
							data.put("product_id", product_id);
							data.put("agreement_id", agreement_id);
							session.insert("T_MALL_PRODUCT_AGREEMENT", data);
						}
						
					}
				}
				log.info("更新关联协议结束...." );
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
		
	//更新基金经理
	public void updateFundManager(String manager_id, String product_id) {
		Session session = null;
		// 先删除，再添加
		try {
			session = this.getSession(Constants.DB_ID);
			session.beginTrans();
			log.info("更新基金经理开始...." );
			session.delete("T_MALL_PRODUCT_AND_MANAGER", "product_id", product_id);
			if (!StringHelper.isEmpty(manager_id)) {
				String[] ids = StringHelper.split(manager_id, ",");
				Set<String> set = new HashSet<String>();
				for(String id:ids){
					set.add(id);
				}
				for(String id:set){
					if(!StringHelper.isEmpty(id)){
						DataRow data = new DataRow();
						data.put("product_id", product_id);
						data.put("manager_id", id);
						session.insert("T_MALL_PRODUCT_AND_MANAGER", data);
					}
				}
			}
			log.info("更新基金经理结束...." );
			session.commitTrans();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
	}
}

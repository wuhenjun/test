package com.thinkive.mall.service;

import java.util.Date;
import com.project.utils.Constants;
import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.jdbc.JdbcTemplate;
import com.thinkive.base.jdbc.session.Session;
import com.thinkive.base.service.BaseService;
import com.thinkive.base.service.SequenceGenerator;
import com.thinkive.base.util.DateHelper;
import com.thinkive.base.util.StringHelper;


public class ThrBankService extends BaseService{
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
	

	//添加三方存管
	public void addThrBank(DataRow datarow) throws Exception{
		String id =  SequenceGenerator.getInstance().getNextSequence(Constants.DB_ID, "t_mall_thr_bank");
		datarow.set("id", id);
		datarow.set("create_time", DateHelper.formatDate(new Date(),"yyyy-MM-dd"));
		String bank_no = datarow.getString("bank_no");
		Session session = null;
		//先删除，再添加
		try {
			session = this.getSession(Constants.DB_ID);
			session.beginTrans();
			String bank_name = session.queryString("select item_name from T_ENUM_VALUE where enum_type_id='1006' and item_value=?",new String[]{bank_no});
			datarow.set("bank_name", bank_name);
			session.insert("t_mall_thr_bank", datarow);
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
	
	
	//更新三方存管
	public void updateThrBank(DataRow datarow) throws Exception{
		String bank_no = datarow.getString("bank_no");
		datarow.set("update_time", DateHelper.formatDate(new Date(),"yyyy-MM-dd"));
		Session session = null;
		try {
			session = this.getSession(Constants.DB_ID);
			session.beginTrans();
			String bank_name = session.queryString("select item_name from T_ENUM_VALUE where enum_type_id='1006' and item_value=?",new String[]{bank_no});
			datarow.set("bank_name", bank_name);
			/**
			 * <!-- 
							序号			id
		银行名称		bank_name
		创建时间		create_time
		更新时间		update_time
		是否支持在线验证	is_online_verify
		银行图片地址		bank_img
		密码规则		pwd_rule
		银行开户步骤		bank_step
		三方存管协议编号	agreement_id
		银行代码		bank_no
							 -->
			 */
			String sql = "update t_mall_thr_bank set bank_name=?,update_time=?,is_online_verify=?,bank_img=?,pwd_rule=?,bank_step=?,agreement_id=?,bank_no=? where id=?";
			session.update(sql, new Object[]{datarow.get("bank_name"),datarow.get("update_time"),datarow.get("is_online_verify"),datarow.get("bank_img"),datarow.get("pwd_rule"),datarow.get("bank_step"),datarow.get("agreement_id"),datarow.get("bank_no"),datarow.get("id")});
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

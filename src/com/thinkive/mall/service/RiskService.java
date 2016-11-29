package com.thinkive.mall.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.project.utils.Constants;
import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.jdbc.JdbcTemplate;
import com.thinkive.base.jdbc.session.Session;
import com.thinkive.base.service.BaseService;
import com.thinkive.base.util.DateHelper;

public class RiskService extends BaseService{
	Logger log = Logger.getLogger(RiskService.class);
	public JdbcTemplate getJdbcTemplate(){
		return this.getJdbcTemplate(Constants.DB_ID);
	}
	
	//新增风险测评试题
	public void insertRiskQuestion(DataRow dataRow,DataRow option){

		if(null == dataRow){
			return ;
		}
		Session session = null;
		try {
			session = this.getSession(Constants.DB_ID);
			session.beginTrans();
			String question_id = getSeqValue(Constants.DB_ID,"t_risk_question");
			if(!question_id.matches("\\d+")){
				throw new Exception("试题编号不能为空");
			}
			dataRow.put("question_id", question_id) ;
			dataRow.put("create_time", DateHelper.formatDate(new Date(),"yyyy-MM-dd")) ;
			session.insert("t_risk_question", dataRow) ;
			log.info("风险测试试插入成功");
			String[] option_no = option.getString("option_no").trim().split(",");
			String[] description = option.getString("description").split(",");
			String[] option_state = option.getString("option_state").split(",");
			String[] selection_mark = option.getString("selection_mark").split(",");
			/*if(option_no.length != description.length || option_no.length != option_state.length || option_state.length != selection_mark.length){
				throw new Exception("未知错误,请重新输入");
			}*/
			if(!option.getString("option_no").trim().equals("")){
				for(int i = 0; i < option_no.length; i++){
					DataRow row_option = new DataRow();
					if(!option_no[i].matches("\\d+") ){
						throw new Exception("选项编号不能为空且必须为数字");
					}
					float mark = 0;
					try{
						mark = Float.parseFloat(selection_mark[i]);
					}catch(Exception e){
						mark = 0;
					}
					row_option.put("option_id", getSeqValue(Constants.DB_ID,"t_risk_quesition_option"));
					row_option.put("question_id", question_id);
					row_option.put("option_no", option_no[i]);
					row_option.put("description", description[i]);
					row_option.put("option_state", option_state[i]);
					row_option.put("selection_mark", mark);
					row_option.put("create_time",  DateHelper.formatDate(new Date(),"yyyy-MM-dd"));
					session.insert("t_risk_quesition_option", row_option) ;
					log.info("风险测试试题选项插入成功");
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
	
	
	public void inSertRiskType(DataRow risk_type,DataRow riskInterval){
		if(null == risk_type){
			return ;
		}
		Session session = null;
		try {
			session = this.getSession(Constants.DB_ID);
			session.beginTrans();
			String risk_type_id = getSeqValue(Constants.DB_ID,"t_risk_type");
			risk_type.put("risk_type_id", risk_type_id) ;
			risk_type.put("create_time", DateHelper.formatDate(new Date())) ;
			session.insert("t_risk_type", risk_type) ;
			log.info("风险测试类型插入成功");
			String[] min_value = riskInterval.getString("min_value").split(",");
			String[] max_value = riskInterval.getString("max_value").split(",");
			String[] risk_evel = riskInterval.getString("risk_evel").split(",");
			String[] risk_name = riskInterval.getString("risk_name").split(",");
			if(min_value.length != max_value.length || min_value.length != risk_evel.length || risk_name.length != risk_evel.length){
				throw new Exception("填写不完整,请重新输入");
			}
			for(int i = 0; i < min_value.length; i++){
				DataRow row_option = new DataRow();
				row_option.put("risk_id", getSeqValue(Constants.DB_ID,"t_mall_risk_interval"));
				row_option.put("min_value", min_value[i]);
				row_option.put("max_value", max_value[i]);
				row_option.put("risk_evel", risk_evel[i]);
				row_option.put("risk_name", risk_name[i]);
				row_option.put("risk_type_id", risk_type_id);
				System.out.println(row_option.getString("risk_id"));
				session.insert("t_mall_risk_interval", row_option) ;
				log.info("风险测试区间插入成功");
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
	
	
	//修改风险测评试题
	public void updateRiskQuestion(DataRow dataRow,DataRow option){

		if(null == dataRow){
			return ;
		}
		Session session = null;
		try {
			session = this.getSession(Constants.DB_ID);
			session.beginTrans();
			String question_id = dataRow.getString("question_id");
			if(!question_id.matches("\\d+")){
				throw new Exception("试题编号不能为空");
			}
			String sql = "update t_risk_question set question_no=?,question_title=?,question_description=?,question_type=?,update_time=? where question_id=?";
			log.info("风险测评试题  开始更新。。。。");
			log.info("question_no：" + dataRow.get("question_no") + "question_title:" + dataRow.get("question_title") + "question_description" + dataRow.get("question_description")+ "question_type:" + dataRow.get("question_type")+ "question_id: " + dataRow.get("question_id"));
			session.update(sql, new Object[]{dataRow.get("question_no"),dataRow.get("question_title"),dataRow.get("question_description"),dataRow.get("question_type"),DateHelper.formatDate(new Date(),"yyyy-MM-dd"),dataRow.get("question_id")});
			log.info("风险测评试题  更新成功 。。。。");
			String[] option_no = option.getString("option_no").split(",");
			String[] description = option.getString("description").split(",");
			String[] option_state = option.getString("option_state").split(",");
			String[] selection_mark = option.getString("selection_mark").split(",");
			/*if(option_no.length != description.length || option_no.length != option_state.length || option_state.length != selection_mark.length){
				throw new Exception("未知错误,请重新输入");
			}*/
			session.update("delete t_risk_quesition_option where question_id=?",new String[]{question_id});
			for(int i = 0; i < description.length; i++){
				DataRow row_option = new DataRow();
				if(!option_no[i].matches("\\d+") ){
					throw new Exception("选项编号不能为空");
				}
				float mark = 0;
				try{
					mark = Float.parseFloat(selection_mark[i]);
				}catch(Exception e){
					mark = 0;
				}
				
				row_option.put("option_id", getSeqValue(Constants.DB_ID,"t_risk_quesition_option"));
				row_option.put("question_id", question_id);
				row_option.put("option_no", option_no[i]);
				row_option.put("description", description[i]);
				row_option.put("option_state", option_state[i]);
				row_option.put("selection_mark", mark);
				row_option.put("create_time", DateHelper.formatDate(new Date(),"yyyy-MM-dd"));
				session.insert("t_risk_quesition_option", row_option) ;
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
	
	//修改风险测试类型
	public void updateRiskType(DataRow risk_type,DataRow riskInterval){
		if(null == risk_type){
			return ;
		}
		Session session = null;
		try {
			session = this.getSession(Constants.DB_ID);
			session.beginTrans();
			/**
			 * insert into t_risk_type(risk_type_id,risk_type_title,risk_type_description,risk_type_state,
		    	create_time,update_time,risk_valid) values(#{$seq(t_risk_type)},#{risk_type_title},#{risk_type_description},
		    	#{risk_type_state},#{$currDate()},#{$currDate()},#{risk_valid})
			 * @param dataRow
			 * @param option
			 * 
			 */
			String risk_type_id = risk_type.getString("risk_type_id");
			if(!risk_type_id.matches("\\d+")){
				throw new Exception("风险试题类型编号不能为空");
			}
			String sql = "update t_risk_type set risk_type_title=?,risk_type_description=?,risk_type_state=?,risk_valid=?,update_time=? where risk_type_id=?";
			session.update(sql, new Object[]{risk_type.get("risk_type_title"),risk_type.get("risk_type_description"),risk_type.get("risk_type_state"),risk_type.get("risk_valid"),DateHelper.formatDate(new Date(),"yyyy-MM-dd"),risk_type.get("risk_type_id")});
			log.info("风险测评区间  更新成功 。。。。");
			String[] min_value = riskInterval.getString("min_value").split(",");
			String[] max_value = riskInterval.getString("max_value").split(",");
			String[] risk_evel = riskInterval.getString("risk_evel").split(",");
			String[] risk_name = riskInterval.getString("risk_name").split(",");
			if(min_value.length != max_value.length || min_value.length != risk_evel.length || risk_name.length != risk_evel.length){
				throw new Exception("填写不完整,请重新输入");
			}
			
			session.update("delete t_mall_risk_interval where risk_type_id=?",new String[]{risk_type_id});
			for(int i = 0; i < min_value.length; i++){
				DataRow row_option = new DataRow();
				row_option.put("risk_id", getSeqValue(Constants.DB_ID,"t_mall_risk_interval"));
				row_option.put("min_value", min_value[i]);
				row_option.put("max_value", max_value[i]);
				row_option.put("risk_evel", risk_evel[i]);
				row_option.put("risk_name", risk_name[i]);
				row_option.put("risk_type_id", risk_type_id);
				session.insert("t_mall_risk_interval", row_option) ;
				log.info("风险测试区间插入成功");
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
	//查询试题风险试题类型
	public DataRow queryRiskType(String risk_type_id){
		return this.getJdbcTemplate().queryMap("select * from t_risk_type t where t.risk_type_id=?",new String[]{risk_type_id});
	}
	//查询试题风险试题类型
	public List<Map<String,Object>> queryRiskInterval(String risk_type_id){
		List<Map<String,Object>> list = null ;
		list = this.getJdbcTemplate().query("select * from t_mall_risk_interval where risk_type_id=?",new String[]{risk_type_id });
		return list ;
	}
	//查询试题信息
	public DataRow queryQuestion(String question_id){
		return this.getJdbcTemplate().queryMap("select * from t_risk_question t where t.question_id=?",new String[]{question_id});
	}
	//查询试题下的选项信息
	public List<DataRow> queryQuestionOption(String question_id){
		return this.getJdbcTemplate().query("select * from t_risk_quesition_option t where t.question_id=?",new String[]{question_id});
	}
	
	//删除风险评测试题类型
	public void deleteRiskType(String risk_type_id) throws Exception{
		getJdbcTemplate().delete("t_risk_type", "risk_type_id", risk_type_id);
	}
	
	//删除风险评测试题
	public void deleteRiskQuestion(String question_id) throws Exception{
		Session session = null;
		try {
			session = this.getSession(Constants.DB_ID);
			session.beginTrans();
			String[] ids = question_id.split(",");
			for(String id:ids){
				session.update("delete t_risk_question where question_id=?",new String[]{id});
				session.update("delete t_risk_quesition_option where question_id=?",new String[]{id});
			}
			
			session.commitTrans();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
				session=null ;
			}
		};
	}
	//public DataRow queryRisk
}

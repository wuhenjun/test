package com.thinkive.mall.service;

import java.util.List;

import com.project.utils.Constants;
import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.jdbc.JdbcTemplate;
import com.thinkive.base.service.BaseService;

/**"web"
 * ����: 
 * ��Ȩ: Copyright (c) 2013
 * ��˾: ˼�ϿƼ� 
 * ����: ��ʥ��
 * �汾: 1.0 
 * ��������: May 8, 2014 
 * ����ʱ��: 2:19:08 PM
 */
public class ArticleService extends BaseService
{
	
	private JdbcTemplate getJdbcTemplate()
	{
		return getJdbcTemplate(Constants.DB_ID);
	}
	
	@SuppressWarnings("unchecked")
	public void insertProtocle(DataRow dataRow)
	{
		if(dataRow!=null){
			dataRow.put("AGREEMENT_ID", getSeqValue(Constants.DB_ID,"T_MALL_AGREEMENT")) ;
		}
		this.getJdbcTemplate().insert("T_MALL_AGREEMENT", dataRow) ;
		
		if(dataRow.getInt("article_id") > 0){
			this.getJdbcTemplate().update("update t_mall_article set ARTICLE_STATE=? where article_id=?", new Object[]{dataRow.getString("is_valid"), dataRow.getString("article_id") });
		}
	}
	
}

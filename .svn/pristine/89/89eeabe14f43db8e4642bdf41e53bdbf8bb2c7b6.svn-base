package com.thinkive.mall.service;

import com.project.utils.Constants;
import com.thinkive.base.jdbc.JdbcTemplate;
import com.thinkive.base.service.BaseService;

public class ProductBrandService extends BaseService{
	private JdbcTemplate getJdbcTemplate()
	{
		return getJdbcTemplate(Constants.DB_ID);
	}
	
	public void deleteRiskType(String brand_id) throws Exception{
		getJdbcTemplate().delete("t_mall_product_brand", "brand_id", brand_id);
	}
}

package com.thinkive.mall.business;

import java.util.ArrayList;
import java.util.List;

import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.util.StringHelper;
import com.thinkive.mall.service.ProductPropertyService;
import com.thinkive.mall.service.ProductService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;

/**
 * 
 * ����: �޸ķ����Ʒ��Ϣ
 * ��Ȩ: Copyright (c) 2014
 * ��˾: ˼�ϿƼ� 
 * ����: ����
 * �汾: 1.0 
 * ��������: 2014-5-7 
 * ����ʱ��: ����01:15:25
 */

public class Function902943 extends BaseFunction
{
	
	@Override
	public ResultVo execute() throws InvokeException, Exception
	{
		ResultVo ResultVo = new ResultVo();
		
		String update_type = getStrParameter("update_type");//��������  1��ʾ���»�����Ϣ  ������ʾ������������
		String product_id = getStrParameter("product_id");
		if(StringHelper.isEmpty(product_id)){
			ResultVo.setErrorMsg("��Ʒ���[product_id]����Ϊ��");
			ResultVo.setErrorNo(-902042101);
			throw new InvokeException(-902042101, "��Ʒ���[product_id]����Ϊ��");
		}
		String product_description = getStrParameter("product_description");//��Ʒ����
		String product_code = getStrParameter("product_code");//��Ʒ����
		String product_name = getStrParameter("product_name");
		String risk_level = getStrParameter("risk_level");//���յȼ�
		
		String recommend_ = this.getStrParameter("recommends");//�Ƽ���ʽ
		String recommend_sort_ = this.getStrParameter("recommend_sort");//�Ƽ���ʽ����
		//�ж������Ƿ�Ϊ����
		if(!StringHelper.isEmpty(recommend_sort_)){
			String[] recommend_sorts = StringHelper.split(recommend_sort_, ",");
			for(String recomend_sort:recommend_sorts){
				try{
					Integer.valueOf(recomend_sort);
				}catch(Exception e){
					ResultVo.setErrorMsg("�Ƽ���Ʒ��Ҫ����Ϊ������" + recommend_sorts.length + "--" + recommend_sort_);
					ResultVo.setErrorNo(-902042102);
					throw new InvokeException(-902042102,"�Ƽ���Ʒ��Ҫ����Ϊ������" + recommend_sorts.length + "--" + recommend_sort_);
				}
			}
		}
		
		String product_shelf = getStrParameter("product_shelf");//�Ƿ��ϼ�
		if(StringHelper.isEmpty(product_shelf)){
			product_shelf = "0";
		}
		
		String img_type_ = getStrParameter("img_type_");//ͼƬ����
		String img_url_ = getStrParameter("img_url");//ͼƬurl
		
		//��ȡǰ�˴�������img_url  (���з�ʽ��Ҫ���ҳ��id�޷��ظ�������)
		img_url_ = ""; 
		for(int i=1;i<=80;i++){
			String img_url = getStrParameter("img_url_"+i);//ͼƬurl
			if(!StringHelper.isEmpty(img_url)){
				img_url_ += img_url+",";
			}
		}
		if(!StringHelper.isEmpty(img_url_)){
			img_url_ = img_url_.substring(0, img_url_.length());
		}
		//�ж�ͼƬurl�Ƿ�Ϊ��
		if(!StringHelper.isEmpty(img_url_) && !StringHelper.isEmpty(img_type_)){
			String[] img_urls = StringHelper.split(img_url_, ",");
			String[] img_types = StringHelper.split(img_type_, ",");
			if(img_urls.length!=img_types.length){
				ResultVo.setErrorMsg("ͼƬ��ַ����Ϊ��!");
				ResultVo.setErrorNo(-902042103);
				throw new InvokeException(-902042103, "ͼƬ��ַ����Ϊ��!");
			}
		}
		
		String discounts = getStrParameter("discounts");//�۸���
		String priority_sort_ = getStrParameter("priority_sort");//���ȼ�
		
		String service_type_ = getStrParameter("service_type");//����ʽ
		String product_info = getStrParameter("product_info");//��Ʒ����
		
		ProductPropertyService pps = new ProductPropertyService();
		
		if("1".equals(update_type)){//
			
		
			/********************1�����·ǽ��ڲ�Ʒ��Ϣ***************************/
			DataRow data1 = new DataRow();
			//data1.set("product_shelf", product_shelf);
			data1.set("risk_level", risk_level);
			data1.set("product_code", product_code);
			data1.set("product_name", product_name);
			data1.set("product_abbr", this.getStrParameter("product_abbr"));
			data1.set("product_description", product_description);
			
			//data1.set("product_info", product_info);
			//���·ǽ��ڲ�Ʒ��Ϣ
			try {
				pps.updateProductNotFinance(data1,product_id);
			} catch (Exception e) {
				ResultVo.setErrorMsg("���·ǽ��ڲ�Ʒ��Ϣʧ��!"+e.getMessage());
				ResultVo.setErrorNo(-902042102);
				e.printStackTrace();
				throw new InvokeException(-902042102, "���·ǽ��ڲ�Ʒ��Ϣʧ��!"+e.getMessage());
			}
		}else{
			/********************5�������Ƽ���ʽ***************************/
			try {
//				if(!StringHelper.isEmpty(recommend_) && !StringHelper.isEmpty(recommend_sort_)){
//					String[] recommends = StringHelper.split(recommend_, ",");
//					String[] recommend_sorts = StringHelper.split(recommend_sort_, ",");
//					if(recommends.length!=recommend_sorts.length){
//						throw new InvokeException(-902042106, "�Ƽ���ʽ������Ϊ��!");
//					}
//					if(recommends!=null){
					pps.updateProductRecommend(recommend_,recommend_sort_, product_id,"2");//��ɾ��,�����
//					}
//				}
			} catch (Exception e) {
				ResultVo.setErrorMsg("�����Ƽ���ʽʧ��!"+e.getMessage());
				ResultVo.setErrorNo(-902032105);
				e.printStackTrace();
				throw new InvokeException(-902032105, "�����Ƽ���ʽʧ��!"+e.getMessage());
			}
			
			/********************1�����·ǽ��ڲ�Ʒ��Ϣ***************************/
			DataRow data1 = new DataRow();
			data1.set("product_shelf", product_shelf);
			data1.set("product_info", product_info);
			data1.set("product_sort", this.getStrParameter("product_sort"));
			//���·ǽ��ڲ�Ʒ��Ϣ
			try {
				pps.updateProductNotFinance(data1,product_id);
			} catch (Exception e) {
				ResultVo.setErrorMsg("���·ǽ��ڲ�Ʒ��Ϣʧ��!"+e.getMessage());
				ResultVo.setErrorNo(-902042102);
				e.printStackTrace();
				throw new InvokeException(-902042102, "���·ǽ��ڲ�Ʒ��Ϣʧ��!"+e.getMessage());
			}	
			
			/********************2�����¼۸�����Ϣ***************************/
			try {
//				if(!StringHelper.isEmpty(discounts) && !StringHelper.isEmpty(priority_sort_)){
//					String[] rules_ids = StringHelper.split(discounts, ",");
//					String[] priority_sorts = StringHelper.split(priority_sort_, ",");
//					if(rules_ids.length!=priority_sorts.length){
//						throw new InvokeException(-902042107, "�۸�����Ų���Ϊ��!");
//					}
//					if(rules_ids!=null){
						pps.updatePriceRules(discounts,priority_sort_, product_id);//��ɾ��,�����
//					}
//				}
			} catch (Exception e) {
				ResultVo.setErrorMsg("���¼۸�����Ϣʧ��!"+e.getMessage());
				ResultVo.setErrorNo(-902042103);
				e.printStackTrace();
				throw new InvokeException(-902042103, "���¼۸�����Ϣʧ��!"+e.getMessage());
			}
			
			/********************3�����·���ʽ***************************/
			try {
				String product_service_type = this.getStrParameter("product_service_type");
				List<String[]> list = new ArrayList<String[]>();
				if(!StringHelper.isEmpty(product_service_type)){
					String[] serviceTypes = product_service_type.split(",");
					for(String s:serviceTypes){
						if(!StringHelper.isEmpty(s)){
							String is_default = this.getStrParameter("is_default_" + s);
							String is_must = this.getStrParameter("is_must_" + s);
							String priority = this.getStrParameter("product_priority_" + s);
							String[] service = new String[4];
							service[0] = s;
							service[1] = (is_default != null && is_default.equals("1")) ? "1" : "0";
							service[2] = (is_must != null && is_must.equals("1")) ? "1" : "0";
							service[3] = priority;
							list.add(service);
						}
					}
					pps.updateProductServices(list, product_id);
				}
//				if(!StringHelper.isEmpty(service_type_)){
//					String[] service_types = StringHelper.split(service_type_, ",");
//					if(service_types!=null){
						//pps.updateProductService(service_type_, product_id);//��ɾ��,�����
//					}
//				}
				
			} catch (Exception e) {
				ResultVo.setErrorMsg("���·���ʽʧ��!"+e.getMessage());
				ResultVo.setErrorNo(-902032104);
				e.printStackTrace();
				throw new InvokeException(-902032104, "���·���ʽʧ��!"+e.getMessage());
			}
			
			/********************4������ͼƬ��Ϣ***************************/
			try {
//				if(!StringHelper.isEmpty(img_url_) && !StringHelper.isEmpty(img_type_)){
//					String[] img_urls = StringHelper.split(img_url_, ",");
//					String[] img_types = StringHelper.split(img_type_, ",");
//					if(img_urls.length!=img_types.length){
//						throw new InvokeException(-902042106, "ͼƬ��ַ����Ϊ��!");
//					}
//					
//					if(img_urls!=null){
						pps.updateImg(img_url_,img_type_, product_id,"2");//��ɾ��,�����   2��ʾ�����Ʒ
//					}
//				}
			} catch (Exception e) {
				ResultVo.setErrorMsg("ͼƬ�ϴ�ʧ��!"+e.getMessage());
				ResultVo.setErrorNo(-902042105);
				e.printStackTrace();
				throw new InvokeException(-902042105, "ͼƬ�ϴ�ʧ��!"+e.getMessage());
			}
			
			/********************���¹���Э��***************************/
			String agreement_ = this.getStrParameter("agreement");
			try {
				pps.updateProductAgreement(agreement_, product_id);
			} catch (Exception e) {
				ResultVo.setErrorMsg("���·���ʽʧ��!"+e.getMessage());
				ResultVo.setErrorNo(-902032104);
				e.printStackTrace();
				throw new InvokeException(-902032104, "���·���ʽʧ��!"+e.getMessage());
			}
		}
		
		return ResultVo;
	}
	
}

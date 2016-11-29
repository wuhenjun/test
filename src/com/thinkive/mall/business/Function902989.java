package com.thinkive.mall.business;

import java.util.List;
import java.util.Map;

import com.project.utils.JSONUtil;
import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.util.StringHelper;
import com.thinkive.mall.service.ProductPropertyService;
import com.thinkive.server.BaseFunction;
import com.thinkive.server.InvokeException;
import com.thinkive.server.ResultVo;

/**
 * ��ѯ��Ʒ��ϸ��Ϣ
 * @author yyy
 *
 */
public class Function902989 extends BaseFunction{

	@Override
	public ResultVo execute() throws InvokeException, Exception
	{
		ResultVo result = new ResultVo();
		
		String product_id = getStrParameter("product_id");
		if(StringHelper.isEmpty(product_id)){
			result.setErrorMsg("��Ʒ���[product_id]����Ϊ��");
			result.setErrorNo(-90298901);
			throw new InvokeException(90298901, "��Ʒ���[product_id]����Ϊ��");
		}
		
		String recommend_ = this.getStrParameter("recommends");
		String recommend_sort_ = this.getStrParameter("recommend_sort");//�Ƽ���ʽ����
		String product_shelf = getStrParameter("product_shelf");//�Ƿ��ϼ�product_info
		String product_detail = getStrParameter("product_detail");//����
		String product_description = getStrParameter("product_description");//����
		
		String purchase_rates = getStrParameter("purchase_rates");//�����깺����
		String fund_manager_id = getStrParameter("fund_manager_id");//������
		
		
		if(StringHelper.isEmpty(product_shelf)){
			product_shelf = "0";
		}
		
		String img_type_ = getStrParameter("img_type_");//ͼƬ����
		String img_url_ = getStrParameter("img_url");//ͼƬurl
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
				result.setErrorMsg("ͼƬ��ַ����Ϊ��!");
				result.setErrorNo(-902032103);
				throw new InvokeException(-902032103, "ͼƬ��ַ����Ϊ��!");
			}
		}
		
		ProductPropertyService pps = new ProductPropertyService();
		
		/********************5�������Ƽ���ʽ***************************/
		try {
			pps.updateProductRecommend(recommend_,recommend_sort_, product_id,"0");//��ɾ��,�����
		} catch (Exception e) {
			result.setErrorMsg("�����Ƽ���ʽʧ��!"+e.getMessage());
			result.setErrorNo(-902032105);
			e.printStackTrace();
			throw new InvokeException(-902032105, "�����Ƽ���ʽʧ��!"+e.getMessage());
		}
		/********************1�����½��ڲ�Ʒ��Ϣ***************************/
		DataRow data1 = new DataRow();
		data1.set("product_id", product_id);
		data1.set("product_shelf", product_shelf);
		//data1.set("product_detail", product_detail);
		data1.set("product_description", product_description);
		data1.set("product_sort", this.getStrParameter("product_sort"));
		//���½��ڲ�Ʒ��Ϣ
		try {
			pps.updateProductFinance(data1,product_id);
		} catch (Exception e) {
			result.setErrorMsg("���½��ڲ�Ʒ��Ϣʧ��!"+e.getMessage());
			result.setErrorNo(-902032102);
			e.printStackTrace();
			throw new InvokeException(-902032102, "���½��ڲ�Ʒ��Ϣʧ��!"+e.getMessage());
		}
		
		
		data1 = new DataRow();
		data1.set("product_id", product_id);
		data1.set("purchase_rates", purchase_rates);
		data1.set("fund_type", this.getStrParameter("fund_type"));
		
		/*if(StringHelper.isNotBlank(fund_manager_id)){
			data1.set("fund_manager_id", fund_manager_id);
		}*/
		//���½��ڲ�Ʒ��Ϣ
		try {
			pps.updateProduct(data1, product_id, 0);
		} catch (Exception e) {
			result.setErrorMsg("���»����Ʒ��Ϣʧ��!"+e.getMessage());
			result.setErrorNo(-902032103);
			e.printStackTrace();
			throw new InvokeException(-902032103, "���»����Ʒ��Ϣʧ��!"+e.getMessage());
		}
		
		//���»�������Ϣ
		String manager_id = this.getStrParameter("manager_id");
		try {
			pps.updateFundManager(manager_id, product_id);
		} catch (Exception e) {
			result.setErrorMsg("���»����Ʒ��Ϣʧ��!"+e.getMessage());
			result.setErrorNo(-902032104);
			e.printStackTrace();
			throw new InvokeException(-902032104, "���»����Ʒ��Ϣʧ��!"+e.getMessage());
		}
		/********************4������ͼƬ��Ϣ***************************/
		try {
			pps.updateImg(img_url_,img_type_, product_id,"0");//��ɾ��,�����  3��ʾ��Ѷ��Ʒ
		} catch (Exception e) {
			result.setErrorMsg("ͼƬ�ϴ�ʧ��!"+e.getMessage());
			result.setErrorNo(-902032105);
			e.printStackTrace();
			throw new InvokeException(-902032105, "ͼƬ�ϴ�ʧ��!"+e.getMessage());
		}
		
		
		/********************���¹���Э��***************************/
		String agreement_ = this.getStrParameter("agreement");
		try {
			pps.updateProductAgreement(agreement_, product_id);
		} catch (Exception e) {
			result.setErrorMsg("���·���ʽʧ��!"+e.getMessage());
			result.setErrorNo(-902032106);
			e.printStackTrace();
			throw new InvokeException(-902032106, "���·���ʽʧ��!"+e.getMessage());
		}
		return result;
	}

}

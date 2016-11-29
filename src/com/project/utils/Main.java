package com.project.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.thinkive.base.service.BaseService;
import com.thinkive.base.util.office.ExcelHelper;
public class Main {

	private static BaseService service = new BaseService();
	
	private static Logger logger = Logger.getLogger(Main.class);
	public static void main(String[] args) throws Exception
	{
		String fileDir = "D:\\workspace\\workPlatDemo\\WebRoot\\WEB-INF\\classes/abc.xls";
		File file = new File(fileDir);
		List list = ExcelHelper.readExcelToList(file);
		List errorList = new ArrayList();
		//String content = JsonHelper.objectToString(list);
		System.out.println(list.size());
		System.out.println();
		/*if (list != null && list.size() > 1)
		{
			for (int i = 1; i < list.size(); i++)
			{
				List row = (List) list.get(i);
				if (row != null && row.size() > 9)
				{
					System.out.println("############" + row);
					DataRow data = service.getJdbcTemplate("web").queryMap(
							"select * from t_b_clinetmgr where certificate='" + row.get(5) + "' and mgrtype="
									+ (row.get(7).equals("��֤��Ա") ? "1" : row.get(7).equals("Ͷ����Ա") ? "2" : row.get(7).equals("Ӫҵ����Ա") ? "3" : row.get(7).equals("Ӫҵ������") ? "4" : ""));
					if (data == null)
					{
						data = new DataRow();
						String mgrid = service.getSeqValue("t_b_clinetmgr");
						data.set("mgrid", mgrid);
						if (row.size() > 0)
						{
							data.set("branchno", row.get(0) == null ? "" : row.get(0));
						}
						if (row.size() > 2)
						{
							data.set("mgrno", row.get(2) == null ? "" : row.get(2));
						}
						if (row.size() > 3)
						{
							data.set("sex", row.get(3) == null ? "" : row.get(3).equals("��") ? "0" : "1");
						}
						if (row.size() > 4)
						{
							data.set("phone", row.get(4) == null ? "" : row.get(4));
						}
						if (row.size() > 5)
						{
							data.set("certificate", row.get(5) == null ? "" : row.get(5));
						}
						if (row.size() > 6)
						{
							data.set("mgrname", row.get(6) == null ? "" : row.get(6));
						}
						if (row.size() > 7)
						{
							data.set("mgrtype",
									row.get(7) == null ? "" : row.get(7).equals("��֤��Ա") ? "1" : row.get(7).equals("Ͷ����Ա") ? "2" : row.get(7).equals("Ӫҵ����Ա") ? "3" : row.get(7).equals("Ӫҵ������") ? "4"
											: "");
						}
						if (row.size() > 8)
						{
							if (row.get(8) != null)
							{
								data.set("smallpicurl", "/upload/" + row.get(8) + ".jpg");
							}
						}
						if (row.size() > 9)
						{
							if (row.get(9) != null)
							{
								data.set("bigpicurl", "/upload/" + row.get(9) + ".jpg");
							}
						}
						if (row.size() > 10)
						{
							data.set("description", row.get(10));
						}
						data.set("score", 0);
						data.set("flower", 0);
						
						File sImag = new File("./classes" + data.getString("smallpicurl"));
						File bImag = new File("./classes" + data.getString("bigpicurl"));
						if ((!sImag.exists() && StringHelper.isNotEmpty(data.getString("smallpicurl"))) || (!bImag.exists() && StringHelper.isNotEmpty(data.getString("bigpicurl"))))
						{
							errorList.add(data);
						}
						else
						{
							service.getJdbcTemplate("web").insert("t_b_clinetmgr", data);
						}
					}
					else
					{
						if (row.size() > 0)
						{
							data.set("branchno", row.get(0) == null ? "" : row.get(0));
						}
						if (row.size() > 2)
						{
							data.set("mgrno", row.get(2) == null ? "" : row.get(2));
						}
						if (row.size() > 3)
						{
							data.set("sex", row.get(3) == null ? "" : row.get(3).equals("��") ? "0" : "1");
						}
						if (row.size() > 4)
						{
							data.set("phone", row.get(4) == null ? "" : row.get(4));
						}
						if (row.size() > 5)
						{
							data.set("certificate", row.get(5) == null ? "" : row.get(5));
						}
						if (row.size() > 6)
						{
							data.set("mgrname", row.get(6) == null ? "" : row.get(6));
						}
						if (row.size() > 7)
						{
							data.set("mgrtype",
									row.get(7) == null ? "" : row.get(7).equals("��֤��Ա") ? "1" : row.get(7).equals("Ͷ����Ա") ? "2" : row.get(7).equals("Ӫҵ����Ա") ? "3" : row.get(7).equals("Ӫҵ������") ? "4"
											: "");
						}
						if (row.size() > 8)
						{
							if (row.get(8) != null)
							{
								data.set("smallpicurl", "/upload/" + row.get(8) + ".jpg");
							}
						}
						if (row.size() > 9)
						{
							if (row.get(9) != null)
							{
								data.set("bigpicurl", "/upload/" + row.get(9) + ".jpg");
							}
						}
						if (row.size() > 10)
						{
							data.set("description", row.get(10));
						}
						File sImag = new File("./classes" + data.getString("smallpicurl"));
						File bImag = new File("./classes" + data.getString("bigpicurl"));
						if ((!sImag.exists() && StringHelper.isNotEmpty(data.getString("smallpicurl"))) || (!bImag.exists() && StringHelper.isNotEmpty(data.getString("bigpicurl"))))
						{
							errorList.add(data);
						}
						else
						{
							service.getJdbcTemplate("web").update("t_b_clinetmgr", data, "mgrid", data.getString("mgrid"));
						}
					}
				}
			}
		}*/
		logger.error("�������ݸ���;" + errorList.size());
		/*for (Iterator iterator = errorList.iterator(); iterator.hasNext();)
		{
			DataRow data = (DataRow) iterator.next();
			File sImag = new File("./classes" + data.getString("smallpicurl"));
			if (!sImag.exists())
			{
				logger.error("ְҵ֤����:" + data.getString("certificate") + "��Ա�����" + data.getString("mgrtype") + "��û���ҵ���Ӧ��Сͷ���ļ�!" + data.getString("smallpicurl"));
			}
			File bImag = new File("./classes" + data.getString("bigpicurl"));
			if (!bImag.exists())
			{
				logger.error("ְҵ֤����:" + data.getString("certificate") + "��Ա�����" + data.getString("mgrtype") + "��û���ҵ���Ӧ�Ĵ�ͷ���ļ�!" + data.getString("bigpicurl"));
			}
		}*/
	}
}

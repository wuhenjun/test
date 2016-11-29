import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.jdbc.JdbcTemplate;
import com.thinkive.base.jdbc.session.Session;
import com.thinkive.base.service.BaseService;
import com.thinkive.base.util.DateHelper;
import com.thinkive.base.util.StringHelper;
import com.thinkive.plat.service.user.UserService;

public class Ser extends BaseService
{
  private static Logger logger = Logger.getLogger(UserService.class);

  private JdbcTemplate getDcJdbcTemplate()
  {
    return getJdbcTemplate("web");
  }

  public int addCataLog(DataRow dataRow)
  {
    int reult = 0;
    Session session = null;
    try
    {
      session = getSession();
      session.beginTrans();
      String catalog_id = getSeqValue("T_CATALOG");
      dataRow.set("catalog_id", catalog_id);
      dataRow.set("orderline", catalog_id);
      String parent_id = dataRow.getString("parent_id");
      String route = "";
      if ((!StringHelper.isEmpty(parent_id)) && (!parent_id.equals("0")))
      {
        DataRow parentDataRow = session.queryMap("SELECT * FROM T_CATALOG WHERE CATALOG_ID = " + parent_id);
        System.out.println("查询成功");
        int childrennum = parentDataRow.getInt("childrennum");
        parentDataRow.set("childrennum", childrennum + 1);
        session.update("T_CATALOG", parentDataRow, "CATALOG_ID", Integer.valueOf(parentDataRow.getInt("catalog_id")));
        System.out.println("更新成功");
        route = parentDataRow.getString("route") + "|" + catalog_id;
      }
      else
      {
        route = catalog_id;
      }
      dataRow.set("route", route);
      System.out.println("开始插入");
      Set<String> set = dataRow.keySet();
      System.out.println(set.size());
      for(String o:set){
    	  System.out.println("key:" + o + "-- values:" + dataRow.getObject(o));
      }
      session.insert("T_CATALOG", dataRow);
      System.out.println("插入成功");
      session.commitTrans();
    }
    catch (Exception ex)
    {
    	ex.printStackTrace();
    	System.out.println("-------------------------------------------cc");
      reult = -1;
      session.rollbackTrans();
    }
    finally
    {
      if (session != null)
      {
        session.close();
        session = null;
      }
    }
    return reult;
  }

  public int updateCataLog(DataRow dataRow, String functionNum)
  {
    int reult = 0;
    Session session = null;
    try
    {
      session = getSession();
      session.beginTrans();
      String catalog_id = dataRow.getString("catalog_id");
      DataRow oldDataRow = session.queryMap("SELECT * FROM T_PLAT_CATALOG WHERE CATALOG_ID = " + catalog_id);
      dataRow.put("route", oldDataRow.getString("route"));
      dataRow.put("childrennum", oldDataRow.getString("childrennum"));
      int parent_id = dataRow.getInt("parent_id");
      String route = "";
      if (parent_id != 0)
      {
        DataRow parentDataRow = session.queryMap("SELECT * FROM T_PLAT_CATALOG WHERE CATALOG_ID = " + parent_id);
        DataRow oldParentDataRow = session.queryMap("SELECT * FROM T_PLAT_CATALOG WHERE CATALOG_ID = " + oldDataRow.getInt("parent_id"));
        if (oldDataRow.getInt("parent_id") != parent_id)
        {
          int childrennum = parentDataRow.getInt("childrennum");
          parentDataRow.set("childrennum", childrennum + 1);
          session.update("T_PLAT_CATALOG", parentDataRow, "CATALOG_ID", Integer.valueOf(parentDataRow.getInt("catalog_id")));
          int oldChildrennum = oldParentDataRow.getInt("childrennum");
          oldParentDataRow.set("childrennum", childrennum - 1);
          session.update("T_PLAT_CATALOG", oldParentDataRow, "CATALOG_ID", Integer.valueOf(oldParentDataRow.getInt("catalog_id")));
        }

        route = parentDataRow.getString("route") + "|" + catalog_id;
      }
      else
      {
        route = catalog_id;
      }
      dataRow.put("route", route);
      session.update("T_PLAT_CATALOG", dataRow, "catalog_id", catalog_id);
      session.delete("T_PLAT_CATALOG_FUNCTIOIN", "catalog_id", catalog_id);
      if (!StringHelper.isEmpty(functionNum))
      {
        String[] funcNo = functionNum.split("\\|");
        System.out.println("length:" + funcNo.length);
        for (int i = 0; i < funcNo.length; i++)
        {
          DataRow cataFunction = new DataRow();
          cataFunction.put("id", getSeqValue("T_PLAT_CATALOG_FUNCTIOIN"));
          cataFunction.put("catalog_id", catalog_id);
          cataFunction.put("function_no", funcNo[i]);
          cataFunction.put("description", "");
          session.insert("T_PLAT_CATALOG_FUNCTIOIN", cataFunction);
        }
      }
      session.commitTrans();
    }
    catch (Exception ex)
    {
      reult = -1;
      session.rollbackTrans();
    }
    finally
    {
      if (session != null)
      {
        session.close();
        session = null;
      }
    }
    return reult;
  }

  public int deleteCataLog(int catalog_id)
  {
    int reult = 0;
    Session session = null;
    try
    {
      session = getSession();
      session.beginTrans();
      session.delete("T_PLAT_CATALOG", "catalog_id", Integer.valueOf(catalog_id));
      session.delete("T_PLAT_CATALOG_FUNCTIOIN", "catalog_id", Integer.valueOf(catalog_id));
      session.commitTrans();
    }
    catch (Exception ex)
    {
      reult = -1;
      session.rollbackTrans();
    }
    finally
    {
      if (session != null)
      {
        session.close();
        session = null;
      }
    }
    return reult;
  }

  public DataRow queryCataLog(int catalog_id)
  {
    String sql = "SELECT * FROM T_PLAT_CATALOG WHERE 1=1 AND CATALOG_ID =" + catalog_id;
    return getDcJdbcTemplate().queryMap(sql);
  }

  public List queryCatalogFunction(int catalog_id)
  {
    String sql = "SELECT * FROM T_PLAT_CATALOG_FUNCTIOIN WHERE 1=1 AND CATALOG_ID =" + catalog_id + " ORDER BY ORDERLINE ASC ";
    return getDcJdbcTemplate().query(sql);
  }

  public List findRouteCatalogById(int catalogId, String siteNo)
  {
    ArrayList argList = new ArrayList();
    String sql = "SELECT CATALOG_ID,PARENT_ID,NAME,CATALOG_NO,ROUTE,LINK_URL,CHILDRENNUM FROM T_CATALOG WHERE STATE = 1 AND ROUTE like ? AND SITENO = ? ORDER BY ORDERLINE";
    if (catalogId == 1)
    {
      argList.add("1|%");
    }
    else
    {
      argList.add("%|" + catalogId + "|%");
    }

    argList.add(siteNo);
    List dataList = getDcJdbcTemplate().query(sql, argList.toArray());
    return dataList;
  }

  public int updateCataLog(DataRow dataRow)
  {
    int reult = 0;
    Session session = null;
    try
    {
      session = getSession();
      session.beginTrans();
      String catalog_id = dataRow.getString("catalog_id");
      DataRow oldDataRow = session.queryMap("SELECT * FROM T_CATALOG WHERE CATALOG_ID = " + catalog_id);
      dataRow.put("route", oldDataRow.getString("route"));
      dataRow.put("childrennum", oldDataRow.getString("childrennum"));
      int parent_id = dataRow.getInt("parent_id");
      String route = "";
      if (parent_id != 0)
      {
        DataRow parentDataRow = session.queryMap("SELECT * FROM T_CATALOG WHERE CATALOG_ID = " + parent_id);
        DataRow oldParentDataRow = session.queryMap("SELECT * FROM T_CATALOG WHERE CATALOG_ID = " + oldDataRow.getInt("parent_id"));
        if (oldDataRow.getInt("parent_id") != parent_id)
        {
          int childrennum = parentDataRow.getInt("childrennum");
          parentDataRow.set("childrennum", childrennum + 1);
          session.update("T_CATALOG", parentDataRow, "CATALOG_ID", Integer.valueOf(parentDataRow.getInt("catalog_id")));
          int oldChildrennum = oldParentDataRow.getInt("childrennum");
          oldParentDataRow.set("childrennum", childrennum - 1);
          session.update("T_CATALOG", oldParentDataRow, "CATALOG_ID", Integer.valueOf(oldParentDataRow.getInt("catalog_id")));
        }

        route = parentDataRow.getString("route") + "|" + catalog_id;
      }
      else
      {
        route = catalog_id;
      }
      dataRow.put("route", route);
      session.update("T_CATALOG", dataRow, "catalog_id", catalog_id);
      session.commitTrans();
    }
    catch (Exception ex)
    {
      reult = -1;
      session.rollbackTrans();
    }
    finally
    {
      if (session != null)
      {
        session.close();
        session = null;
      }
    }
    return reult;
  }

  public DataRow queryTemplatePublishPlant(String id)
  {
    String sql = "SELECT P.ID,P.CATALOG_ID,C.NAME,P.TYPE,P.PUBLISHTIME,P.RECURSION,P.TIME FROM T_PUBLISH_PLAN P ,T_CATALOG C  WHERE P.CATALOG_ID=C.CATALOG_ID AND P.ID = " + id;
    return getDcJdbcTemplate().queryMap(sql);
  }

  public int updatePublishPlan(DataRow dataRow, String id)
  {
    int result = 0;
    try
    {
      getDcJdbcTemplate().update("T_PUBLISH_PLAN", dataRow, "id", id);
    }
    catch (Exception ex) {
      result = -1;
    }
    return result;
  }

  public List querySietCatalogTemplate(int id, String site)
  {
    String sql2 = "";
    if (id == 0)
    {
      sql2 = "select * from t_template where siteno = '" + site + "'";
    }
    else
    {
      String sql = "select route from t_catalog t where catalog_id = " + id;
      DataRow dataRow = getDcJdbcTemplate().queryMap(sql);
      String route = dataRow.getString("route");
      sql2 = "select * from t_template where catalog_id in (select catalog_id from  t_catalog where route like '" + route + "%') and siteno = '" + site + "'";
    }
    List list = getDcJdbcTemplate().query(sql2);
    String sql3 = "select catalog_id,catalog_no,route from t_catalog";
    List list2 = getDcJdbcTemplate().query(sql3);
    Map nameMap = new HashMap();
    Map routeMap = new HashMap();
    for (Iterator i$ = list2.iterator(); i$.hasNext(); ) { Object object = i$.next();

      DataRow dataRow2 = (DataRow)object;
      nameMap.put(dataRow2.getString("catalog_id"), dataRow2.getString("catalog_no"));
      routeMap.put(dataRow2.getString("catalog_id"), dataRow2.getString("route"));
    }
    List lsit3 = new ArrayList();
    for (Iterator i$ = list.iterator(); i$.hasNext(); ) { Object object = i$.next();

      DataRow dataRow2 = (DataRow)object;
      String catalogId = dataRow2.getString("catalog_id");
      if (routeMap.get(catalogId) != null)
      {
        String route2 = routeMap.get(catalogId).toString();
        StringBuffer name = new StringBuffer("");
        String[] arryCatalogid = StringHelper.split(route2, "|");
        for (String string : arryCatalogid)
        {
          if (nameMap.get(string) != null)
            name.append("@" + nameMap.get(string));
        }
        dataRow2.put("fileName", name);
        lsit3.add(dataRow2);
      } }
    return lsit3;
  }

  public DataRow queryTemplateOne(String id, String catalog_id)
  {
    String sql = "select * from t_template where id = " + id + " and catalog_id=" + catalog_id;
    return getDcJdbcTemplate().queryMap(sql);
  }

  public int imporTemplate(DataRow dataRow)
  {
    int resultno = 0;
    Session session = null;
    String login_id = dataRow.getString("login_id");
    dataRow.remove("login_id");
    try
    {
      session = getSession();
      session.beginTrans();
      String fileName = dataRow.getString("fileName");
      String siteno = dataRow.getString("siteno");
      String[] name = StringHelper.split(fileName, "@");

      Boolean ishave = Boolean.valueOf(false);
      String calalogId = "";
      if (name.length >= 2)
      {
        String sql = "SELECT CATALOG_ID FROM T_CATALOG where CATALOG_NO = '" + name[(name.length - 2)] + "' and siteno = '" + siteno + "'";
        DataRow dataRow1 = session.queryMap(sql);
        if (dataRow1 != null)
        {
          sql = "SELECT CATALOG_ID FROM T_CATALOG where CATALOG_NO = '" + name[(name.length - 1)] + "' and PARENT_ID = " + dataRow1.getString("catalog_id") + " and siteno = '" + siteno + "'";
          DataRow dataRow2 = session.queryMap(sql);
          if (dataRow2 != null)
          {
            ishave = Boolean.valueOf(true);
            calalogId = dataRow2.getString("catalog_id");
          }
        }

      }
      else if (name.length == 1)
      {
        String sql = "SELECT CATALOG_ID FROM T_CATALOG where CATALOG_NO = '" + name[(name.length - 1)] + "' and siteno = '" + siteno + "'";
        DataRow dataRow2 = session.queryMap(sql);
        if (dataRow2 != null)
        {
          ishave = Boolean.valueOf(true);
          calalogId = dataRow2.getString("catalog_id");
        }
      }

      if (ishave.booleanValue())
      {
        String sql;
        if (dataRow.getString("type").equals("4"))
        {
          sql = "SELECT * FROM T_TEMPLATE where CATALOG_ID = " + calalogId + " and FILEPATH='" + dataRow.getString("filepath") + "' order by CREATE_DATE desc";
        }
        else
        {
          sql = "SELECT * FROM T_TEMPLATE where CATALOG_ID = " + calalogId + " and type =" + dataRow.getString("type") + " order by CREATE_DATE desc";
        }

        List list3 = session.queryPage(sql, 1, 1).getData();
        if ((list3 != null) && (list3.size() > 0))
        {
          DataRow dataRow3 = (DataRow)list3.get(0);
          String hid = getSeqValue("T_TEMPLATE_HISTORY");
          DataRow history = new DataRow();
          history.set("id", hid);
          history.set("template_id", dataRow3.get("id"));
          history.set("create_by", login_id);
          history.set("create_date", DateHelper.formatDate(new Date()));
          history.set("content", dataRow3.getString("content"));
          session.insert("T_TEMPLATE_HISTORY", history);
          dataRow3.set("modified_date", DateHelper.formatDate(new Date()));
          dataRow3.set("create_date", DateHelper.formatDate(new Date()));
          dataRow3.set("content", dataRow.getString("content"));
          dataRow3.remove("rownum_");
          session.update("T_TEMPLATE", dataRow3, "id", dataRow3.get("id"));
        }
        else if (StringHelper.isNotEmpty(calalogId))
        {
          dataRow.remove("updateType");
          dataRow.remove("fileName");
          dataRow.set("id", getSeqValue("T_TEMPLATE"));
          dataRow.set("modified_date", DateHelper.formatDate(new Date()));
          dataRow.set("create_date", DateHelper.formatDate(new Date()));
          dataRow.set("catalog_id", calalogId);
          session.insert("T_TEMPLATE", dataRow);
        }
      }

      session.commitTrans();
    }
    catch (Exception ex)
    {
      resultno = -1;
      session.rollbackTrans();
    }
    finally
    {
      if (session != null)
      {
        session.close();
        session = null;
      }
    }
    return resultno;
  }

  public int updateTemplateHistory(int id, int t_id, String user_id)
  {
    int reult = 0;
    Session session = null;
    try
    {
      session = getSession();
      session.beginTrans();
      String hid = getSeqValue("T_TEMPLATE_HISTORY");
      DataRow template = session.queryMap("select * from T_TEMPLATE where id = " + t_id);
      DataRow history = new DataRow();
      history.set("id", hid);
      history.set("template_id", t_id);
      history.set("create_by", user_id);
      history.set("create_date", DateHelper.formatDate(new Date()));
      history.set("content", template.getString("content"));
      session.insert("T_TEMPLATE_HISTORY", history);
      DataRow history2 = session.queryMap("select * from T_TEMPLATE_HISTORY where id = " + id);
      template.set("content", history2.getString("content"));
      session.update("T_TEMPLATE", template, "id", template.getString("id"));
      session.commitTrans();
    }
    catch (Exception ex)
    {
      reult = -1;
      session.rollbackTrans();
    }
    finally
    {
      if (session != null)
      {
        session.close();
        session = null;
      }
    }
    return reult;
  }

  public int updateTemplate(int id, String content, String user_id)
  {
    int reult = 0;
    Session session = null;
    try
    {
      session = getSession();
      session.beginTrans();
      String hid = getSeqValue("T_TEMPLATE_HISTORY");
      DataRow template = session.queryMap("select * from T_TEMPLATE where id = " + id);
      DataRow history = new DataRow();
      history.set("id", hid);
      history.set("template_id", id);
      history.set("create_by", user_id);
      history.set("create_date", DateHelper.formatDate(new Date()));
      history.set("content", template.getString("content"));
      session.insert("T_TEMPLATE_HISTORY", history);
      template.set("content", content);
      session.update("T_TEMPLATE", template, "id", template.getString("id"));
      session.commitTrans();
    }
    catch (Exception ex)
    {
      reult = -1;
      session.rollbackTrans();
    }
    finally
    {
      if (session != null)
      {
        session.close();
        session = null;
      }
    }
    return reult;
  }

  public List addPublishPlant(String id, String site)
  {
    if (site.equals("0"))
    {
      return getDcJdbcTemplate().query("SELECT '1' as ISMAIN,SITENO as SITE,'0' as ID,'0' as PID,NAME,'true' as ISPARENT FROM T_SITE");
    }

    return getDcJdbcTemplate().query("SELECT SITENO as site,CATALOG_ID as ID,PARENT_ID as PID,NAME,case when CHILDRENNUM > 0 then 'true' else 'false' end as isparent FROM T_CATALOG WHERE PARENT_ID = " + id + " and SITENO = '" + site + "' ORDER BY ORDERLINE ASC ");
  }

  public String updateSiteCataLogRout(int id, String catalog_id)
  {
    String reult = "";
    Session session = null;
    try
    {
      session = getSession();
      session.beginTrans();
      String sql = "SELECT * FROM T_CATALOG WHERE CATALOG_ID = " + catalog_id;
      DataRow ycdata = session.queryMap(sql);
      String sql3 = "SELECT * FROM T_CATALOG WHERE CATALOG_ID = " + ycdata.getInt("parent_id");
      DataRow ycfdata = session.queryMap(sql3);
      ycfdata.set("childrennum", ycfdata.getInt("childrennum") - 1);
      String sql2 = "SELECT * FROM T_CATALOG WHERE CATALOG_ID = " + id;
      DataRow yhfdata = session.queryMap(sql2);
      yhfdata.set("childrennum", yhfdata.getInt("childrennum") + 1);
      reult = yhfdata.getString("route") + "|" + ycdata.getString("catalog_id");
      ycdata.set("route", reult);
      ycdata.set("parent_id", yhfdata.getInt("catalog_id"));
      session.update("T_CATALOG", ycdata, "catalog_id", Integer.valueOf(ycdata.getInt("catalog_id")));
      session.update("T_CATALOG", yhfdata, "catalog_id", Integer.valueOf(yhfdata.getInt("catalog_id")));
      session.update("T_CATALOG", ycfdata, "catalog_id", Integer.valueOf(ycfdata.getInt("catalog_id")));
      session.commitTrans();
    }
    catch (Exception ex)
    {
      session.rollbackTrans();
    }
    finally
    {
      if (session != null)
      {
        session.close();
        session = null;
      }
    }
    return reult;
  }
}
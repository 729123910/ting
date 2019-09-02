package gs.bll;

import java.awt.Cursor;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.ParameterMode;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.procedure.ProcedureCall;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import gs.AppConfig;

public class test {

    public Map<Object,Object> DoTest() {
        AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext ();
        ctx.register(AppConfig.class);
        ctx.refresh();
        SessionFactory sessionFactory = ctx.getBean(SessionFactory.class);
        ctx.close();

        Map<Object,Object> map = new HashMap<Object,Object>();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            String DateStr="2019/06";
            ProcedureCall pc = session.createStoredProcedureCall("SE_PRO_FILETABLE_FIVE");
            pc.registerParameter("DateStr", String.class, ParameterMode.IN).bindValue(DateStr);
            pc.registerParameter("RefCursor", Cursor.class, ParameterMode.REF_CURSOR);
            ResultSet rs = (ResultSet) pc.getOutputs().getOutputParameterValue("RefCursor");

            List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
            ResultSetMetaData rsmd = rs.getMetaData();
            int colCount = rsmd.getColumnCount();
            List<String> colNameList = new ArrayList<String>();

            for (int i = 0; i < colCount; i++) {
                colNameList.add(rsmd.getColumnName(i + 1));
            }

            while (rs.next()) {
                Map<String, Object> TempMap = new HashMap<String, Object>();
                for (int i = 0; i < colCount; i++) {
                    String key = colNameList.get(i);
                    Object value = rs.getString(colNameList.get(i));
                    TempMap.put(key, value);
                }
                results.add(TempMap);
            }
            map.put("data", results);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
        return map;
    }

    public void DoTest1() {
        AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext ();
        ctx.register(AppConfig.class);
        ctx.refresh();
        //DataSource data = ctx.getBean(DataSource.class);
        ctx.close();
    }
}
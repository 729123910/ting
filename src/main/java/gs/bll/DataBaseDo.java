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
import org.springframework.jdbc.core.JdbcTemplate;


public class DataBaseDo {

    
    
    /** 
     * @param sessionFactory
     * @return Map<Object, Object>
     */
    public Map<Object, Object> HibernateDo(SessionFactory sessionFactory) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            String DateStr = "2019/06";
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

    
    /** 
     * @param ds
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> Jdbc_queryForList(DataSource ds) {
        JdbcTemplate jdbcTemplate=new JdbcTemplate(ds);
        String sql = "select * from A_USERS ";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }
}
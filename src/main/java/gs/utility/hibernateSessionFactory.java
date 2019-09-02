package gs.utility;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import com.alibaba.fastjson.JSON;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

//import gs.AppConfig;

@Configuration
public class hibernateSessionFactory {

    @Autowired
    private  EntityManagerFactory entityManagerFactory;

    @Autowired
    private  DataSourceProperties dataSourceProperties;
 
    @Autowired
    private  ApplicationContext applicationContext;


    public  Session getSession() {
        return entityManagerFactory.unwrap(SessionFactory.class).openSession();
    }

    public  void contextLoads() {
        try {
            //AppConfig aa=new AppConfig();
            //DataSource dd=aa.dataSource();
            // if (dd!=null) {
                

            // }
            System.out.println(dataSourceProperties.getUrl());    
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        // // 获取配置的数据源
        // DataSource dataSource = applicationContext.getBean(DataSource.class);
        // // 查看配置数据源信息
        // System.out.println(dataSource);
        // System.out.println(dataSource.getClass().getName());
        // System.out.println(dataSourceProperties);
        // //执行SQL,输出查到的数据
        // JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        // List<?> resultList = jdbcTemplate.queryForList("select 1 from dual");
        // System.out.println("===>>>>>>>>>>>" + JSON.toJSONString(resultList));
     }

}
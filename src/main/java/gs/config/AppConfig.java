package gs.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;

import gs.bll.Beantest;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    public AppConfig() {
        System.out.println("AppConfig容器启动初始化。。。");
    }

    // 一般情况下只能有@Controller的类下用@Autowired使用
    @Bean
    public Beantest beantest() {
        Beantest bean = new Beantest();
        bean.a = 1;
        bean.b = 2;
        bean.i = 3;
        return bean;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.oracle")
    public DataSource oracleDataSource() {
        return DataSourceBuilder.create().build();
    }
}
// package gs;

// // import net.sf.log4jdbc.sql.jdbcapi.DataSourceSpy;

// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
// // import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
// // import org.springframework.boot.context.properties.ConfigurationProperties;
// // import org.springframework.context.annotation.Primary;

// // import javax.sql.DataSource;

// // import org.hibernate.SessionFactory;
// // import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// // import javax.persistence.EntityManagerFactory;


// @Configuration
// public class AppConfig {
//     // @Autowired
//     // DataSourceProperties dataSourceProperties;

//     // @Bean
//     // @ConfigurationProperties(prefix = DataSourceProperties.PREFIX)
//     // DataSource realDataSource() {
//     //     DataSource dataSource = DataSourceBuilder
//     //             .create(this.dataSourceProperties.getClassLoader())
//     //             .url(this.dataSourceProperties.getUrl())
//     //             .username(this.dataSourceProperties.getUsername())
//     //             .password(this.dataSourceProperties.getPassword())
//     //             .build();
//     //     return dataSource;
//     // }

//     // @Bean
//     // @Primary
//     // DataSource dataSource() {
//     //     return new DataSourceSpy(realDataSource());
//     // }

//     // @Bean
//     // public SessionFactory sessionFactory(EntityManagerFactory factory) {
//     //     if (factory.unwrap(SessionFactory.class) == null) {
//     //         throw new NullPointerException("factory is not a hibernate factory");
//     //     }
//     //     return factory.unwrap(SessionFactory.class);
//     // }
// }
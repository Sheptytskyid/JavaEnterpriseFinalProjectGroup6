//package net.greatstart.configs;
//
//import org.apache.commons.dbcp.BasicDataSource;
//import org.flywaydb.core.Flyway;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.orm.hibernate5.HibernateTransactionManager;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.annotation.PostConstruct;
//import javax.sql.DataSource;
//import java.util.Properties;
//
//@Configuration
//@EnableTransactionManagement
//@PropertySource(value = {"classpath:database.properties"})
//public class DataBaseConfig {
//
//    @Autowired
//    private Environment env;
//
//    @Bean
//    public DataSource getDataSource() {
//        BasicDataSource ds = new BasicDataSource();
//        ds.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
//        ds.setUrl(env.getRequiredProperty("jdbc.url"));
//        ds.setUsername(env.getRequiredProperty("jdbc.username"));
//        ds.setPassword(env.getRequiredProperty("jdbc.password"));
//        return ds;
//    }
//
//
//    @Bean
//    @Autowired
//    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
//        HibernateTransactionManager htm = new HibernateTransactionManager();
//        htm.setSessionFactory(sessionFactory);
//        return htm;
//    }
//
//
//    @Bean
//    public LocalSessionFactoryBean getSessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(getDataSource());
//        sessionFactory.setPackagesToScan("net.greatstart");
//        sessionFactory.setHibernateProperties(getHibernateProperties());
//        return sessionFactory;
//    }
//
//    @Bean
//    public Properties getHibernateProperties() {
//        Properties properties = new Properties();
//        properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
//        properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
//        properties.put("hibernate.default_schema", env.getRequiredProperty("hibernate.default_schema"));
//        return properties;
//    }
//
//    @PostConstruct
//    public void migrateDatabase() {
//        Flyway flyway = new Flyway();
//        flyway.setDataSource(getDataSource());
//        flyway.migrate();
//    }
//}
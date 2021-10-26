//package ru.pel.rrs.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DataSourceConfig {
//
////    @Autowired
////    private Environment env;
//
//    //    //TODO реализовать пул соединений, что бы DAO брали connection из пула при необходимости, а потом возвращали.
//    @Bean
//    public DataSource dataSource() {
////        String dbType = env.getProperty("db.type");
////        String dbDriver = env.getProperty(dbType + ".driver");
////        String dbUrl = env.getProperty(dbType + ".url");
////        String dbUser = env.getProperty(dbType + ".user");
////        String dbPass = env.getProperty(dbType + ".pass");
////
////        DriverManagerDataSource dataSource = new DriverManagerDataSource();
////        dataSource.setDriverClassName(dbDriver);
////        dataSource.setUrl(dbUrl);
////        dataSource.setUsername(dbUser);
////        dataSource.setPassword(dbPass);
//
//
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.h2.Driver");
//        dataSource.setUrl("jdbc:h2:file:./rrs_db");
//        dataSource.setUsername("sa");
//        dataSource.setPassword("");
//
//        return dataSource;
//    }
//
//
//}

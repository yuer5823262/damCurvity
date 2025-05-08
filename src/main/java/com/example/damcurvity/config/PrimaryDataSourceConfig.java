//package com.example.damcurvity.config;
//import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//
//@Configuration
//@MapperScan(basePackages = "com.example.damcurvity.dao")
//public class PrimaryDataSourceConfig {
//
//    @Bean(name = "db1DataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.db1")
//    @Primary
//    public DataSource testDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "db1SqlSessionFactory")
//    @Primary
//    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource datasource)
//            throws Exception {
//        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
//        bean.setDataSource(datasource);
//        bean.setMapperLocations(
//                new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
//        return bean.getObject();
//    }
//}
//
//
//
//

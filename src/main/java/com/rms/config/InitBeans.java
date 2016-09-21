package com.rms.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 
 * Title:InitBeans
 * Description:初始化bean
 * @author    zwb
 * @date      2016年9月21日 下午9:48:58
 *
 */
@Configuration
public class InitBeans {
	
    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource() {
        return new ComboPooledDataSource();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());

        //PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        //sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:com/rms/test/mapper/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }
}

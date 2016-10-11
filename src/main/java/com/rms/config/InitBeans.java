package com.rms.config;

import java.util.Properties;

import javax.servlet.Filter;
import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.github.pagehelper.PageHelper;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.rms.converter.CustomDateConvert;
import com.rms.listener.InitListner;

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

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:**/mapper/*.xml"));
        
        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);
        
        //添加插件
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});
        
        return sqlSessionFactoryBean.getObject();
    }
    
    //注册监听器
    @Bean
    public InitListner initListner(){
    	return new InitListner();
    }
    
    //注册转换器
    @Bean
    public CustomDateConvert customDateConvert(){
    	return new CustomDateConvert();
    }
    
    //中文乱码
	@Bean
	public Filter characterEncodingFilter() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}
}

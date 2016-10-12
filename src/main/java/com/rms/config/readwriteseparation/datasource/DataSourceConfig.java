package com.rms.config.readwriteseparation.datasource;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.github.pagehelper.PageHelper;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 
 * Title:DataSourceConfig
 * Description:数据源配置
 * @author    zwb
 * @date      2016年10月11日 下午5:48:02
 *
 */
@Configuration
public class DataSourceConfig {

	/**
	 * 主
	 * @return
	 */
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "datasource.write")
	public DataSource writeDataSource() {
		return new ComboPooledDataSource();
	}
	
	/**
     * 从
     * @return
     */
	@Bean
    @ConfigurationProperties(prefix = "datasource.read")
    public DataSource readDataSource() {
    	return new ComboPooledDataSource();
    }

	@Bean
    @ConditionalOnBean({AbstractRoutingDataSource.class})
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(roundRobinDataSouceProxy());

		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

		sqlSessionFactoryBean
				.setMapperLocations(resolver.getResources("classpath:**/mapper/*.xml"));

		// 分页插件
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("reasonable", "true");
		properties.setProperty("supportMethodsArguments", "true");
		properties.setProperty("returnPageInfo", "check");
		properties.setProperty("params", "count=countSql");
		pageHelper.setProperties(properties);

		// 添加插件
		sqlSessionFactoryBean.setPlugins(new Interceptor[] { pageHelper });

		return sqlSessionFactoryBean.getObject();
	}

	/**
	 * 配置数据源
	 * 
	 * @return
	 */
	@Bean
	public AbstractRoutingDataSource roundRobinDataSouceProxy() {
	    int size = 2;
	    MyAbstractRoutingDataSource proxy = new MyAbstractRoutingDataSource(size);
		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
		// 写
		DataSource writeDataSource = writeDataSource();
		targetDataSources.put(DataSourceType.write.getType(), writeDataSource);
		// 读
		DataSource readDataSource = readDataSource();
		targetDataSources.put(DataSourceType.read.getType(), readDataSource);
		
		proxy.setDefaultTargetDataSource(writeDataSource);
		proxy.setTargetDataSources(targetDataSources);
		return proxy;
	}

}

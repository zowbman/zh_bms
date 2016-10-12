package com.rms.config.readwriteseparation.datasource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
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
	@ConfigurationProperties(prefix = "datasource.read")
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
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
		
		//数据源封装
		DataSource writeDataSource = writeDataSource();//读
		List<DataSource> readDataSources = new ArrayList<DataSource>();
		readDataSources.add(readDataSource());
		
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(roundRobinDataSouceProxy(writeDataSource,readDataSources));

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
	 * @param writeDataSource 主
	 * @param readDataSource 从
	 * @return
	 */
	@Bean
	public AbstractRoutingDataSource roundRobinDataSouceProxy(DataSource writeDataSource, List<DataSource> readDataSources) {
	    int size = readDataSources.size();
	    MyAbstractRoutingDataSource proxy = new MyAbstractRoutingDataSource(size);
		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
		// 写
		targetDataSources.put(DataSourceType.write.getType(), writeDataSource);
		// 读
		int index = 1;
		for (DataSource dataSource : readDataSources) {
			targetDataSources.put(index, dataSource);
			index++;
		}
		proxy.setDefaultTargetDataSource(writeDataSource);
		proxy.setTargetDataSources(targetDataSources);
		return proxy;
	}
	
}

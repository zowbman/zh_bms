package com.rms.config.readwriteseparation.datasource;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 
 * Title:MyAbstractRoutingDataSource
 * Description:简单负载
 * @author    zwb
 * @date      2016年10月11日 下午5:48:38
 *
 */
public class MyAbstractRoutingDataSource extends AbstractRoutingDataSource {
	private final int dataSourceNumber;
	private AtomicInteger count = new AtomicInteger(0);

	public MyAbstractRoutingDataSource(int dataSourceNumber) {
		this.dataSourceNumber = dataSourceNumber;
	}

	@Override
	protected Object determineCurrentLookupKey() {
		String typeKey = DataSourceContextHolder.getJdbcType();
		System.out.println("切换至 - >" + typeKey);
		if (typeKey.equals(DataSourceType.write.getType()))
			return DataSourceType.write.getType();
		// 读 简单负载均衡
		int number = count.getAndAdd(1);
		int lookupKey = number % dataSourceNumber;
		return new Integer(lookupKey);
	}
}
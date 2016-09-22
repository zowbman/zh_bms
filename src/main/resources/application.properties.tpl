spring.mvc.view.prefix: /WEB-INF/
spring.mvc.view.suffix: .jsp

spring.datasource.type = com.mchange.v2.c3p0.ComboPooledDataSource
spring.datasource.jdbcUrl = @spring.datasource.jdbcUrl@
spring.datasource.user = @spring.datasource.user@
spring.datasource.password = @spring.datasource.password@
spring.datasource.driverClass = com.mysql.jdbc.Driver
spring.datasource.minPoolSize = 3
spring.datasource.maxPoolSize = 15
spring.datasource.acquireIncrement = 3
spring.datasource.maxStatements = 8
spring.datasource.maxStatementsPerConnection = 5
spring.datasource.maxIdleTime = 1800

server.port = @server.port@
server.address = @server.address@
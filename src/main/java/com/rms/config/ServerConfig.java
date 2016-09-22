package com.rms.config;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.boot.context.embedded.jetty.JettyServerCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * Title:ServerConfig
 * Description:
 * @author    zwb
 * @date      2016年9月22日 下午8:17:05
 *
 */
@Configuration
public class ServerConfig implements JettyServerCustomizer {

    @ConfigurationProperties(prefix="server")
	@Override
	public void customize(Server server) {
		WebAppContext context = new WebAppContext();
        context.setResourceBase("lib/webapp");
        server.setHandler(context);
	}
}

package com.hqyj.SpringBootDemo.config;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hqyj.SpringBootDemo.filter.ParameterFilter;
import com.hqyj.SpringBootDemo.interceptor.UrlInterceptor;

@Configuration
@AutoConfigureAfter({WebMvcAutoConfiguration.class})
public class webMvcConfig implements WebMvcConfigurer{

	@Value("${server.http.port}")
	private int httpPort;
	
	@Autowired
	private UrlInterceptor urlInterceptor;
	
	@Autowired
	private ResourceConfigBean resourceConfigBean;
	
	
	
	@Bean
	public Connector connector() {
		Connector connector = new Connector();
		connector.setScheme("http");
		connector.setPort(httpPort);
		return connector;
	}
	
	@Bean
	public ServletWebServerFactory  servletWebServerFactory() {
		TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
		factory.addAdditionalTomcatConnectors(connector());
		return factory;
	}
	
	
	//过滤器    可以对整个系统字符集编码、URL访问权限过滤、过滤敏感词信息、session用户是否存在、日志记录等等
	@Bean
	public FilterRegistrationBean<ParameterFilter> filter() {
		FilterRegistrationBean<ParameterFilter> register = new FilterRegistrationBean<ParameterFilter>();
		register.setFilter(new ParameterFilter());
		return register;
	}

	//拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(urlInterceptor).addPathPatterns("/**");
	}
	
	

	//资源路径
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String systemName = System.getProperty("os.name");			//获取windows、Linux
		if (systemName.toLowerCase().startsWith("win")) {
			registry.addResourceHandler(resourceConfigBean.getResourcePathPattern())
				.addResourceLocations(ResourceUtils.FILE_URL_PREFIX + resourceConfigBean.getLocalPathForWindow());
		} else  {
			registry.addResourceHandler(resourceConfigBean.getResourcePathPattern())
				.addResourceLocations(ResourceUtils.FILE_URL_PREFIX + resourceConfigBean.getLocalPathForLinux());
		}
	}
	
	
}






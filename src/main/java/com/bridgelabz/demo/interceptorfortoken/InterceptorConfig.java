
package com.bridgelabz.demo.interceptorfortoken;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration

public class InterceptorConfig extends WebMvcConfigurerAdapter {

	// @Autowired Interceptor inter;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new Interceptor()).addPathPatterns("/notes/create");
	}

}

/*
 * public class InterceptorConfig extends HibernateJpaAutoConfiguration {
 * 
 * @Autowired Interceptor inter;
 * 
 * protected void customizeVendorProperties(Map<String, Object>
 * vendorProperties) {
 * vendorProperties.put("hibernate.session_factory.interceptor", inter); }
 * 
 * }
 */

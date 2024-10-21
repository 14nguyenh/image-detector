package com.henry.image_detector.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.henry.image_detector.filter.ServletRequestLoggingFilter;

@Configuration
public class JerseyConfig {
	@Bean
	public FilterRegistrationBean<ServletRequestLoggingFilter> servletRequestLoggingFilter() {
		FilterRegistrationBean<ServletRequestLoggingFilter> registration = new FilterRegistrationBean<>();
		registration.setFilter(new ServletRequestLoggingFilter());
		registration.addUrlPatterns("/*");
		return registration;
	}
}

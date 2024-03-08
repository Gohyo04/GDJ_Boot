package com.winter.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.winter.app.interceptors.LoginInterceptor;
import com.winter.app.interceptors.TestInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{

	@Autowired
	private TestInterceptor testInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 어떤 URL이 왔을 때 어떤 Interceptor를 거치게 할 것 인가
		
	}

	
}

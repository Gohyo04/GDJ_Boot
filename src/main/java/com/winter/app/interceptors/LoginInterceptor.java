package com.winter.app.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginInterceptor implements WebMvcConfigurer{

//	@Autowired
//	private TestInterceptor testInterceptor;
//	
//	
//	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		// 어떤 URL이 왔을 때 어떤 Interceptor를 거치게 할 것 인가
//		registry.addInterceptor(testInterceptor)
//				.addPathPatterns("/notice/*")
//				.excludePathPatterns("/notice/add");
//	}
}

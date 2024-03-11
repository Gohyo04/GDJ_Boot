package com.winter.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
	
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() throws Exception{
		// /resources/static/**	 ->  ignore
		return web -> web
						.ignoring()
						.requestMatchers("/css/**")
						.requestMatchers("/js/**")
						.requestMatchers("/vender/**")
						.requestMatchers("/img/**")
						.requestMatchers("/favicon/**")
						;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity security) throws Exception{
//		security.cors()
//				.and()
//				.csrf()
//				.disable();
		
		security.authorizeHttpRequests(
				(authorizeHttpRequests) ->
				
						authorizeHttpRequests
							.requestMatchers("/").permitAll()
							.requestMatchers("/member/add").permitAll()
							.requestMatchers("/notice/list").authenticated()			// 로그인한 사용자만 입장 auth = 인증
							.requestMatchers("/notice/add", "/notice/delete").hasRole("ADMIN")
							.requestMatchers("/notice/update").hasAnyRole("ADMIN", "MANAGER")
							.anyRequest().permitAll()
							
				)// authorizeHttpRequests 끝부분			
				.formLogin(										// 사용자가 만든 로그인 폼
						(login) -> 
							login
								.loginPage("/member/login")
								.defaultSuccessUrl("/") 		// login 성공시
//								.passwordParameter("pw")	파라미터 이름을 path에서 password이 아닐경우 설정
//								.usernameParameter("id")	파라미터 이름을 path에서 username이 아닐경우 설정
								.permitAll()
					) // formLogin 끝부분
					.logout(
							(logout -> 
								logout
									.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
									.logoutSuccessUrl("/")
									.invalidateHttpSession(true)	// 로그아웃시 세션 만료 (세션 삭제)
									.permitAll()
							)
					);
		
		return security.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		// password 암호화 해주는 객체
		return new BCryptPasswordEncoder();
	}
	
}
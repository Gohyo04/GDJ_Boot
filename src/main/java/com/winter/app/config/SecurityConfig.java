package com.winter.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.winter.app.member.MemberService;

@Configuration
//@EnableWebSecurity(debug = true)
@EnableWebSecurity
public class SecurityConfig{
	
	@Autowired
	private SecurityLoginSuccessHandler handler;
	
	@Autowired
	private SecurityLoginFailHandler failHandler;
	
	@Autowired
	private SecurityLogoutSuccessHandler successHandler;
	
	@Autowired
	private MemberService memberService;
	
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() throws Exception{
		// /resources/static/**	 ->  ignore(인증/인가가 불필요한 유형?경로)
		return web -> web
//						.debug(false)		//== @EnableWebSecurity(debug = true)
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
							.requestMatchers("/member/page", "/member/logout").authenticated()
							.requestMatchers("/notice/list").authenticated()			// 로그인한 사용자만 입장 auth = 인증
							.requestMatchers("/notice/add", "/notice/delete").hasRole("ADMIN")
							.requestMatchers("/notice/update").hasAnyRole("ADMIN", "MANAGER")
							.anyRequest().permitAll()
							
				)// authorizeHttpRequests 끝부분			
				.formLogin(										// 사용자가 만든 로그인 폼
						(login) -> 
							login
								.loginPage("/member/login")
//								.defaultSuccessUrl("/") 		// login 성공시
								.successHandler(handler)		// login 성공시2
//								.failureUrl("/") 				// login 실패시 default는 로그인 페이지
								.failureHandler(failHandler)				// login 실패시2
//								.passwordParameter("pw")	파라미터 이름을 path에서 password이 아닐경우 설정
//								.usernameParameter("id")	파라미터 이름을 path에서 username이 아닐경우 설정
								.permitAll()
				) // formLogin 끝부분
				.logout(
					(logout -> 
						logout
							.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
//							.logoutSuccessUrl("/")						// 로그아웃 성공시
							.logoutSuccessHandler(successHandler)		// 로그아웃 성공시2
							.invalidateHttpSession(true)				// 로그아웃시 세션 만료 (세션 삭제)
							.permitAll()
					)
				)// logout 끝부분
				.rememberMe(
					(rememberMe ->
						rememberMe
							.rememberMeParameter("rememberMe")
							.tokenValiditySeconds(600)				// 초단위
							.key("rememberMe")
							.userDetailsService(memberService)
							.authenticationSuccessHandler(handler)
							.useSecureCookie(false)
					)		
				)// rememberMe 끝부분
				.sessionManagement(
					(sessionManagement) -> 
						sessionManagement
							.maximumSessions(1)						// 최대 세션
							.maxSessionsPreventsLogin(false)		// 이전 사용자 session 만료
							.expiredUrl("/expired")
				) // sessionManagement 끝
				.oauth2Login(
					(oauth2Login) -> 
						oauth2Login.userInfoEndpoint(
								(ue) -> ue.userService(memberService)
					)
				)//oauth2Login 끝
				;
											

		
		return security.build();
	}
	
}

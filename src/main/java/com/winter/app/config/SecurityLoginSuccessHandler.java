package com.winter.app.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SecurityLoginSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		String rememberId = request.getParameter("rememberId");
		
		if(rememberId != null) {
			// 꺼낸 ID를 client의 Cookie에 저장
			log.info(authentication.getName());
			Cookie cookie = new Cookie("rememberId", authentication.getName());
			cookie.setMaxAge(600);
			cookie.setPath("/");
			
			response.addCookie(cookie);
			
		}else {
		   Cookie[]	cookies = request.getCookies();
		   for (Cookie cookie : cookies) {
			   if(cookie.getName().equals(rememberId)) {
				   cookie.setMaxAge(0);	// 쿠키 만료
				   cookie.setValue("");	// 쿠키 값 비우기
				   cookie.setPath("/");
				   response.addCookie(cookie);	// 응답으로 쿠키를 다시 보내줌
				   break;
			   }
		   }
		}
		
		log.info("Login 성공시 실행");
		authentication.getPrincipal();		// MemberVO
		
		// 로그인 성공 후 이동할 경로 (url)
		response.sendRedirect("/");
	}
}

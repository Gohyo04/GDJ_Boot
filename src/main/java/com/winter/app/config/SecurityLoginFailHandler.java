package com.winter.app.config;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SecurityLoginFailHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		String message = "로그인 실패";
		
		if(exception instanceof AuthenticationException) {
			message= "계정유효기간이 만료되었습니다(아이디 없음).";
		}
		
		if(exception instanceof BadCredentialsException) {
			message = "비밀번호가 틀립니다.";
		}
		
		if(exception instanceof InternalAuthenticationServiceException) {
			message = "존재하지않는 아이디 입니다.";
		}

		if(exception instanceof LockedException) {
			message = "계정이 잠겨있습니다.";
		}
		
		if(exception instanceof CredentialsExpiredException) {
			message = "비밀번호의 유효기간이 종료되었습니다.";
		}
		
		if(exception instanceof DisabledException) {
			message = "휴면 계정 입니다.";
		}

		// 한글 깨짐 방지
		message = URLEncoder.encode(message, "UTF-8");
		
		response.sendRedirect("/member/login?message="+message);
//		request.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(request, response);
	}
}










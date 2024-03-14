package com.winter.app;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.winter.app.ajax.RestTemplateTest;
import com.winter.app.lambda.TestInterface;
import com.winter.app.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class TestController {

	@Autowired
	private RestTemplateTest restTemplateTest;
	
	@GetMapping("/")
	public String test()throws Exception {
		
		// 람다는 JS 와 비슷 function(){} => ()->{}
		// java () -> {}
		TestInterface ti = (int a, int b) -> a+b;
		
		Supplier<MemberVO> s = () -> new MemberVO(); 
		MemberVO memberVO = s.get();
		
		System.out.println(ti.t1(0, 0));
		
		return "index";
	}
	
	@GetMapping("/expired")
	public String expired(Model model){
		
		model.addAttribute("msg", "로그아웃");
		model.addAttribute("path", "/");
		return "commons/result";
	}
}

package com.winter.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class TestController {

	@GetMapping("/expired")
	public String expired(Model model) {
		model.addAttribute("msg", "로그아웃");
		model.addAttribute("path", "/");
		return "commons/result";
	}
}

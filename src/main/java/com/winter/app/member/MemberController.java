package com.winter.app.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("add")
	public void add(@ModelAttribute MemberVO memberVO) throws Exception{
		// @ModelAttribute == model.addAttribute("vo",memberVO)
	}
	
	@PostMapping("add")
	public String add(@Valid MemberVO memberVO, BindingResult bindingResult) throws Exception{
		if(bindingResult.hasErrors()) {
			return "member/add";
		}
		// service로 보냄
		return "redirect:/";
	}
}







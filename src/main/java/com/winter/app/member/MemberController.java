package com.winter.app.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winter.app.member.groups.MemberJoinGroup;
import com.winter.app.member.groups.MemberUpdateGroup;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("login")
	public String login(@ModelAttribute MemberVO memberVO) throws Exception{
		return "member/login";
	}
	
	@GetMapping("add")
	public void add(@ModelAttribute MemberVO memberVO) throws Exception{
		// @ModelAttribute == model.addAttribute("vo",memberVO)
	}
	
	@PostMapping("add")
	public String add(@Validated(MemberJoinGroup.class) MemberVO memberVO, BindingResult bindingResult, Model model) throws Exception{

		boolean check = memberService.checkMember(memberVO, bindingResult);
		if(check) {
			return "member/add";
		}

//		if(bindingResult.hasErrors()) {
//			return "member/add";
//		}
		
		int result = memberService.add(memberVO);
		
		model.addAttribute("result","member.add.result");
		model.addAttribute("path", "/");
		
		// service로 보냄
		return "commons/result";
	}
	
	@GetMapping("update")
	public void update(Model model) throws Exception{
		
	}
	
	@PostMapping("update")
	public String update(@Validated(MemberUpdateGroup.class) MemberVO memberVO, BindingResult bindingResult) throws Exception{
		if(bindingResult.hasErrors()) {
			return "member/update";
		}
		return "redirect:/";
	}
}







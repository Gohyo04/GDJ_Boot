package com.winter.app.member;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class MemberService {// extends DefaultOAuth2UserService implements UserDetailsService{
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	//@Qualifier("ps")
	private PasswordEncoder passwordEncoder;
	
	// userDetailService
//	@Override
//	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//		log.info("kakao === {}", userRequest);
//		ClientRegistration c = userRequest.getClientRegistration();
//		
//		log.info("clientId ===> {}",c.getClientId());
//		log.info("clientName ===> {}",c.getClientName());
//		
//		OAuth2User user =  super.loadUser(userRequest);
//		
//		log.info("loginUser ===> {}",user.getName());
//		log.info("===> {}", user.getAuthorities());
//		log.info("===> {}",user.getAttributes());
//		log.info("===> {}", user.getAttribute("properties").toString());
//		
//		if(c.getClientName().equals("Kakao")) {
//			user = this.kakao(user);			
//		}
//		
//		
//		((MemberVO)user).setSocial(c.getClientName());
//		
//		return user;
//	}
//	
//	private OAuth2User kakao(OAuth2User oAuth2User){
//		Map<String, Object> map = oAuth2User.getAttribute("properties");
//		MemberVO memberVO = new MemberVO();
//		memberVO.setUsername(oAuth2User.getName());
//		memberVO.setName(map.get("nickname").toString());
//		memberVO.setAttributes(oAuth2User.getAttributes());
//		
//		List<RoleVO> list = new ArrayList<>();
//		RoleVO roleVo = new RoleVO();
//		roleVo.setRoleName("ROLE_MEMBER");
//		
//		list.add(roleVo);
//		
//		memberVO.setRoleVOs(list);
//		
//		return memberVO;
//	}
	
	
	public int add(MemberVO memberVO) throws Exception{
		// 평문 password -> 암호화
		memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
		// 회원의 Role 정보 저장
		int result = memberDAO.add(memberVO);
		
		result = memberDAO.addMemberRole(memberVO);
		return result; 		
	}
	
	// add 검증
	// 비번일치, id 중복 여부
	public boolean checkMember(MemberVO memberVO, BindingResult bindingResult) throws Exception{
		boolean check = false;
		
		// check 가 true 라면 error
		// check 가 false 라면 error 가 없다
		
		// annotation 검증결과
		check = bindingResult.hasErrors();
		
		// 비번 검증
		if(!memberVO.getPassword().equals(memberVO.getPasswordCheck())){
			check = true;
			bindingResult.rejectValue("passwordCheck", "memberVO.password.equals");
		}
		
		// id 중복
		MemberVO result = memberDAO.getDetail(memberVO);
		if(result != null) {
			check = true;
			bindingResult.rejectValue("username", "memberVO.username.equals");
		}
		
		
		return check;
	}

	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
//		MemberVO memberVO = new MemberVO();
//		memberVO.setUsername(username);
//		
//		log.info("로그인 진행 ====={}======",username);
//		
//		try {
//			memberVO = memberDAO.getDetail(memberVO);			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return memberVO;
//	}
	
	
}

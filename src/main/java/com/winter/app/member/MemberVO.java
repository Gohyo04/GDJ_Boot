package com.winter.app.member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.winter.app.member.groups.MemberJoinGroup;
import com.winter.app.member.groups.MemberUpdateGroup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter 
@ToString
public class MemberVO implements UserDetails{
	
	@NotBlank(message = "입력하세요", groups = {MemberJoinGroup.class, MemberUpdateGroup.class})
	private String username;
	
	@NotBlank(groups = MemberJoinGroup.class)
	@Size(min=8, max=16, groups = MemberJoinGroup.class)
	private String password;
	
	private String passwordCheck;

	private String phone;
	
	@Email(groups = {MemberJoinGroup.class, MemberUpdateGroup.class})
	private String email;
	private String address;
	private String name;
	
	private List<RoleVO> roleVOs;
	
	// security
	private boolean accountNonExpired;			// 아이디가 없거나 만료
	private boolean accountNonLocked;			// 계정 잠김
	private boolean credentialsNonExpired;		// 비밀번호 유효기간 종료
	private boolean enabled;					// 휴면 계정
	
	
	// security
	@Override		// 권한 관리(검증) 메서드
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(RoleVO roleVO : roleVOs) {
			GrantedAuthority g = new SimpleGrantedAuthority(roleVO.getRoleName());
			authorities.add(g);
		}
		return authorities;
	}
	
}

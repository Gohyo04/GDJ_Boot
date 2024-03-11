package com.winter.app.member;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
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
	
	// security
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	
	// true 일때 만료x, 잠김x, 사용o 
	@Override	// 계정 만료?
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override	// 계정 잠김?
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override	// 비밀번호 만료?
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override	// 사용가능한 계정인가?
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}

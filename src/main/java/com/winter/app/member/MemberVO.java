package com.winter.app.member;

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
public class MemberVO {
	
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
	
}

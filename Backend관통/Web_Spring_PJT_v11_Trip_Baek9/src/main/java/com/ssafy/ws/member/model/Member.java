package com.ssafy.ws.member.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Schema(title = "Member", description = "멤버 정보 : id, nickName, password, email")
public class Member {
	@Schema(description = "Member PK", example = "haram")
	private String id;
	@Schema(description = "닉네임 unique not null", example = "하람 동생 다람이")
    private String nickName;
	@Schema(description = "비밀번호 not null")
    private String password;
	@Schema(description = "이메일 not null", example = "ssafy@ssafy.com")
    private String email;
}           

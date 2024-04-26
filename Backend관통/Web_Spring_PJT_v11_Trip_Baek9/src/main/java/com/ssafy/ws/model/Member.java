package com.ssafy.ws.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Member {
	private String id;
    private String nickName;
    private String password;
    private String email;
}           

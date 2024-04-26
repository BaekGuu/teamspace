package com.ssafy.ws.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Board {
	private int boardId;
    private String writerId;
    private String title;
    private String content;
    private String writingTime;
}

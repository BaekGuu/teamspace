package com.ssafy.ws.board.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Schema(title = "Board", description = "게시글 정보 : boardId, writerId, title, content, writingTime")
public class Board {
	@Schema(description = "Board PK, 자동생성", example = "3")
	private int boardId;
    @Schema(description = "작성자 id, member에서 FK조건 만족해야 함",example = "haram")
	private String writerId;
    @Schema(description = "제목")
    private String title;
    @Schema(description = "내용")
    private String content;
    @Schema(description = "글 작성시간, 자동생성")
    private String writingTime;
}

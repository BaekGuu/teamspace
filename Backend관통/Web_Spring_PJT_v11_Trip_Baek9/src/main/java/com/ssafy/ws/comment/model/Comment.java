package com.ssafy.ws.comment.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Schema(title = "Comment", description = "댓글 정보 : commentId, comment, memberId, boardId, writingTime, nickName")
public class Comment {
	@Schema(description = "Comment PK, 자동생성", example = "3")
	private int commentId;
	@Schema(description = "Comment 내용",example = "좋아요 댓글 구독 알림설정까지")
    private String comment;
    @Schema(description = "작성자 id, member에서 FK조건 만족해야 함",example = "haram")
    private String memberId;
    @Schema(description = "댓글을 작성할 board id, board에서 FK 만족해야 함",example = "17")
    private int boardId;
    @Schema(description = "댓글 작성시간, 자동생성")
    private String writingTime; 
    @Schema(description = "작성자 닉네임, member테이블 join해서 사용",example = "Baek9")
    private String nickName;
}

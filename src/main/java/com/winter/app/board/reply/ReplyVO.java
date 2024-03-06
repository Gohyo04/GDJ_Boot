package com.winter.app.board.reply;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyVO{
	
	private Long replyNum;
	private Long boardNum;
	private String userName;
	private String replyContents;
	private Date replyDate;
}

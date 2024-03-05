package com.winter.app.board;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BoardVO {
	
	private Long boardNum;
	private String boardWriter;
	private String boardTitle;
	private String boardContents;
	private Date boardDate;
	private Long boardHit;
}

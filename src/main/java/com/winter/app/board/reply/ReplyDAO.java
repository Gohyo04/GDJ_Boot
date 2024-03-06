package com.winter.app.board.reply;

import org.apache.ibatis.annotations.Mapper;

import com.winter.app.board.BoardDAO;

@Mapper
public interface ReplyDAO{
	
	public int addReply() throws Exception; 
}

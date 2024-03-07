package com.winter.app.board.qna;

import com.winter.app.board.BoardDAO;
import com.winter.app.board.BoardVO;

public interface QnaDAO extends BoardDAO{
	
	public int refUpdate(BoardVO boardVO)throws Exception; 
}

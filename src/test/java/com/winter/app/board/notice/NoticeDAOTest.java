package com.winter.app.board.notice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.winter.app.board.BoardVO;

@SpringBootTest
class NoticeDAOTest {

	@Autowired
	private NoticeDAO noticeDAO;
	
	@Test
	void getListTest() throws Exception{
		List<BoardVO> no = noticeDAO.getList();
		assertEquals(10, no.size());
	}	
	
//	@Test
//	void addTest() throws Exception{
//		for(int i=0;i<120;i++) {
//			NoticeVO noticeVO = new NoticeVO();
//			noticeVO.setBoardWriter("tester"+i);
//			noticeVO.setBoardTitle("testTitle"+i);
//			noticeVO.setBoardContents("testContents"+i);
//			int result = noticeDAO.add(noticeVO);
//		}
//		System.out.println("finish");
//	}
}

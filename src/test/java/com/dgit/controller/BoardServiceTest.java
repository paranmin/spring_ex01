package com.dgit.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dgit.domain.BoardVO;
import com.dgit.service.BoardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BoardServiceTest {
	@Autowired
	private BoardService service;

	@Test
	public void test1Register() throws Exception {
		BoardVO vo = new BoardVO();
		vo.setTitle("title test");
		vo.setContent("content test");
		vo.setWriter("userTest");

		service.register(vo);
		
		List<BoardVO> list = service.listAll();
		for (BoardVO v : list) {
			System.out.println(v);
		}
	}

	@Test
	public void test2ListPage() {

		int start = 0;
		int offset = 10;
		Map<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("offset", offset);
		
	}
}

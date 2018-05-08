package com.dgit.controller;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dgit.dao.BoardDAO;
import com.dgit.domain.BoardVO;
import com.dgit.domain.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BoardDAOTest {

	@Autowired
	private BoardDAO dao;

	public void test1Create() throws Exception {
		BoardVO vo = new BoardVO();
		vo.setTitle("title test");
		vo.setContent("content test");
		vo.setWriter("userTest");

		int res = dao.create(vo);
		assertEquals(1, res);
	}

	@Test
	public void test2Read() throws Exception {
		BoardVO vo = dao.read(3);
		assertNotNull(vo);

		System.out.println(vo);
	}

	@Test
	public void test3Update() throws Exception {
		BoardVO vo = new BoardVO();
		vo.setBno(3);
		vo.setWriter("userTest update");
		vo.setTitle("title update test");
		vo.setContent("content update test");

		int res = dao.update(vo);
		assertEquals(1, res);
	}

	public void test4Delete() throws Exception {
		int res = dao.delete(2);

		assertEquals(1, res);
	}

	@Test
	public void test5ListAll() throws Exception {
		List<BoardVO> list = dao.listAll();
		assertNotNull(list);

		for (BoardVO vo : list) {
			System.out.println(vo);
		}
	}

	@Test
	public void test6ListCriteria() throws Exception {
		Criteria criteria = new Criteria();
		criteria.setPage(3);

		dao.listCriteria(criteria);
	}
}

package com.dgit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgit.dao.BoardDAO;
import com.dgit.domain.BoardVO;
import com.dgit.domain.Criteria;
import com.dgit.domain.SearchCriteria;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO dao;

	@Override
	public void register(BoardVO vo) throws Exception {
		dao.create(vo);
	}

	@Override
	public BoardVO read(int bno) throws Exception {
		return dao.read(bno);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return dao.listAll();
	}

	@Transactional
	@Override
	public void modify(BoardVO vo) throws Exception {
		dao.update(vo);
		dao.updateViewCnt(vo.getBno(), -1);
	}

	@Override
	public void upViewCount(int bno, int amount) throws Exception {
		dao.updateViewCnt(bno, amount);
	}

	@Override
	public void remove(int bno) throws Exception {
		dao.delete(bno);
	}

	@Override
	public List<BoardVO> listCriteria(Criteria criteria) throws Exception {
		return dao.listCriteria(criteria);
	}

	@Override
	public int countTotal() throws Exception {
		return dao.countTotal();
	}

	@Override
	public List<BoardVO> listCriteriaBySearch(SearchCriteria criteria) throws Exception {
		return dao.listSearch(criteria);
	}

	@Override
	public int countTotalBySearch(SearchCriteria criteria) throws Exception {
		return dao.countTotalBySearch(criteria);
	}

}

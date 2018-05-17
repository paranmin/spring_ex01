package com.dgit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgit.dao.BoardDAO;
import com.dgit.dao.ReplyDao;
import com.dgit.domain.Criteria;
import com.dgit.domain.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	private ReplyDao dao;
	
	@Autowired
	private BoardDAO boardDao;

	@Override
	public List<ReplyVO> list(int bno) {
		return dao.list(bno);
	}

	@Transactional
	@Override
	public void register(ReplyVO vo) throws Exception {
		dao.create(vo);
		boardDao.updateReplyCnt(vo.getBno(), 1);
	}

	@Override
	public int modify(ReplyVO vo) {
		return dao.update(vo);
	}

	@Transactional
	@Override
	public void remove(int rno) throws Exception {
		int bno = dao.getBnoFromRno(rno);
		dao.delete(rno);		
		boardDao.updateReplyCnt(bno, -1);
	}

	@Override
	public List<ReplyVO> listByPage(int bno, Criteria criteria) {
		return dao.listByPage(bno, criteria);
	}

	@Override
	public int countReply(int bno) {
		return dao.countReply(bno);
	}
}

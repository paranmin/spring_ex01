package com.dgit.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dgit.domain.BoardVO;
import com.dgit.domain.Criteria;

@Repository
public class BoardDAOImpl implements BoardDAO {
	private final String namespace = "com.dgit.mapper.BoardMapper.";

	@Autowired
	SqlSession session;

	@Override
	public int create(BoardVO vo) throws Exception {
		return session.insert(namespace + "create", vo);
	}

	@Override
	public BoardVO read(int bno) throws Exception {
		return session.selectOne(namespace + "read", bno);
	}

	@Override
	public int update(BoardVO vo) throws Exception {
		return session.update(namespace + "update", vo);
	}

	@Override
	public int updateViewCnt(int bno) throws Exception {
		return session.update(namespace + "updateViewCnt", bno);
	}

	@Override
	public int delete(int bno) throws Exception {
		return session.delete(namespace + "delete", bno);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return session.selectList(namespace + "listAll");
	}

	@Override
	public List<BoardVO> listCriteria(Criteria criteria) throws Exception {
		return session.selectList(namespace + "listCriteria", criteria);
	}

	@Override
	public int countTotal() throws Exception {
		return session.selectOne(namespace + "countTotal");
	}

}

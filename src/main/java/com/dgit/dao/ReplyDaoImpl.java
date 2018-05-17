package com.dgit.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dgit.domain.Criteria;
import com.dgit.domain.ReplyVO;

@Repository
public class ReplyDaoImpl implements ReplyDao {
	private static final String namespace = "com.dgit.mapper.ReplyMapper.";

	@Autowired
	private SqlSession session;

	@Override
	public List<ReplyVO> list(int bno) {
		return session.selectList(namespace + "list", bno);
	}

	@Override
	public int create(ReplyVO vo) {
		return session.insert(namespace + "create", vo);
	}

	@Override
	public int update(ReplyVO vo) {
		return session.update(namespace + "update", vo);
	}

	@Override
	public int delete(int rno) {
		return session.delete(namespace + "delete", rno);
	}

	@Override
	public List<ReplyVO> listByPage(int bno, Criteria criteria) {
		Map<String, Object> map = new HashMap<>();
		map.put("bno", bno);
		map.put("criteria", criteria);
		return session.selectList(namespace + "listByPage", map);
	}

	@Override
	public int countReply(int bno) {
		return session.selectOne(namespace + "countReply", bno);
	}

	@Override
	public int getBnoFromRno(int rno) {
		return session.selectOne(namespace + "getBnoFromRno", rno);
	}
}

package com.dgit.dao;

import java.util.List;

import com.dgit.domain.Criteria;
import com.dgit.domain.ReplyVO;

public interface ReplyDao {
	List<ReplyVO> list(int bno);
	int create(ReplyVO vo);
	int update(ReplyVO vo);
	int delete(int rno);

	List<ReplyVO> listByPage(int bno, Criteria criteria);
	int countReply(int bno);

	int getBnoFromRno(int rno);
}

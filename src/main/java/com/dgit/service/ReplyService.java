package com.dgit.service;

import java.util.List;

import com.dgit.domain.Criteria;
import com.dgit.domain.ReplyVO;

public interface ReplyService {
	List<ReplyVO> list(int bno);
	void register(ReplyVO vo) throws Exception;
	int modify(ReplyVO vo);
	void remove(int rno) throws Exception;

	List<ReplyVO> listByPage(int bno, Criteria criteria);
	int countReply(int bno);
}

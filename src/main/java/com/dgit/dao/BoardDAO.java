package com.dgit.dao;

import java.util.List;

import com.dgit.domain.BoardVO;
import com.dgit.domain.Criteria;

public interface BoardDAO {
	int create(BoardVO vo) throws Exception;

	BoardVO read(int bno) throws Exception;

	int update(BoardVO vo) throws Exception;

	int updateViewCnt(int bno) throws Exception;

	int delete(int bno) throws Exception;

	List<BoardVO> listAll() throws Exception;

	List<BoardVO> listCriteria(Criteria criteria) throws Exception;

	int countTotal() throws Exception;
}

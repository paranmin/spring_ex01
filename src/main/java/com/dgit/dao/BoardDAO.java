package com.dgit.dao;

import java.util.List;

import com.dgit.domain.BoardVO;
import com.dgit.domain.Criteria;
import com.dgit.domain.SearchCriteria;

public interface BoardDAO {
	int create(BoardVO vo) throws Exception;

	BoardVO read(int bno) throws Exception;

	int update(BoardVO vo) throws Exception;

	int updateViewCnt(int bno, int amount) throws Exception;

	int delete(int bno) throws Exception;

	List<BoardVO> listAll() throws Exception;

	List<BoardVO> listCriteria(Criteria criteria) throws Exception;

	int countTotal() throws Exception;

	List<BoardVO> listSearch(SearchCriteria criteria) throws Exception;

	int countTotalBySearch(SearchCriteria criteria) throws Exception;

	int updateReplyCnt(int bno, int amount) throws Exception;
}

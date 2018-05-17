package com.dgit.service;

import java.util.List;

import com.dgit.domain.BoardVO;
import com.dgit.domain.Criteria;
import com.dgit.domain.SearchCriteria;

public interface BoardService {

	void register(BoardVO vo) throws Exception;

	BoardVO read(int bno) throws Exception;

	List<BoardVO> listAll() throws Exception;

	void modify(BoardVO vo) throws Exception;

	void upViewCount(int bno, int amount) throws Exception;

	void remove(int bno) throws Exception;

	List<BoardVO> listCriteria(Criteria criteria) throws Exception;

	int countTotal() throws Exception;

	List<BoardVO> listCriteriaBySearch(SearchCriteria criteria) throws Exception;

	int countTotalBySearch(SearchCriteria criteria) throws Exception;

}

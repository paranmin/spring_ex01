package com.dgit.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dgit.domain.BoardVO;
import com.dgit.domain.Criteria;
import com.dgit.domain.PageMaker;
import com.dgit.domain.SearchCriteria;
import com.dgit.service.BoardService;

@Controller
@RequestMapping("/sboard/*")
public class SearchBoardController {
	private static final Logger logger = LoggerFactory.getLogger(SearchBoardController.class);

	@Autowired
	private BoardService service;

	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void ListByPage(Model model, SearchCriteria criteria) throws Exception {
		logger.info("board ListByPage Get...");
		logger.info("criteria : " + criteria);

		List<BoardVO> list = service.listCriteria(criteria);
		model.addAttribute("list", list);

		PageMaker pageMaker = PageMaker.getInstance();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(service.countTotal());
		logger.info("pagemaker : " + pageMaker);
		model.addAttribute("pageMaker", pageMaker);

		model.addAttribute("title", "List");
	}

	@RequestMapping(value = "/readPage", method = RequestMethod.GET)
	public void readPage(Model model, int bno, @ModelAttribute("criteria") Criteria criteria) throws Exception {
		logger.info("board readPage Get...");
		logger.info("bno : " + bno);
		logger.info("criteria : " + criteria);

		service.upViewCount(bno);
		BoardVO vo = service.read(bno);
		model.addAttribute("boardVO", vo);
		model.addAttribute("title", "Read");
	}

	@RequestMapping(value = "/removePage", method = RequestMethod.GET)
	public String removePage(int bno, Criteria criteria) throws Exception {
		logger.info("board remove Get...");
		logger.info("bno : " + bno);

		service.remove(bno);
		return "redirect:/sboard/listPage?page=" + criteria.getPage();
	}

	@RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
	public void modifyPageGet(Model model, int bno, @ModelAttribute("criteria") Criteria criteria) throws Exception {
		logger.info("board modifyPageGet Get...");
		logger.info("bno : " + bno);

		BoardVO vo = service.read(bno);
		model.addAttribute("boardVO", vo);
		model.addAttribute("title", "Modify");
	}

	@RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
	public String modifyPagePost(BoardVO vo, Criteria criteria) throws Exception {
		logger.info("board modifyPagePost Post...");
		logger.info("boardVO : " + vo);
		logger.info("criteria : " + criteria);

		service.modify(vo);
		return String.format("redirect:/sboard/readPage?bno=%s&page=%s", vo.getBno(), criteria.getPage());
	}
}

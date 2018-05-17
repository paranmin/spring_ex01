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
import com.dgit.service.BoardService;

@RequestMapping("/board/*")
@Controller
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardService service;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGet(Model model) {
		logger.info("board register Get...");

		model.addAttribute("title", "Register");
		// return "/board/register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPost(BoardVO vo) throws Exception {
		logger.info("board register Post...");
		logger.info(vo.toString());

		service.register(vo);

		return "redirect:/board/listPage";
	}

	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void listAll(Model model) throws Exception {
		logger.info("board listAll Get...");

		List<BoardVO> list = service.listAll();
		model.addAttribute("list", list);
		model.addAttribute("title", "List");
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(Model model, int bno) throws Exception {
		logger.info("board read Get...");
		logger.info("bno : " + bno);

		service.upViewCount(bno, 1);
		BoardVO vo = service.read(bno);
		model.addAttribute("boardVO", vo);
		model.addAttribute("title", "Read");
	}

	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String remove(int bno) throws Exception {
		logger.info("board remove Get...");
		logger.info("bno : " + bno);

		service.remove(bno);
		return "redirect:/board/listPage";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGet(Model model, int bno) throws Exception {
		logger.info("board modify Get...");
		logger.info("bno : " + bno);

		BoardVO vo = service.read(bno);
		model.addAttribute("boardVO", vo);
		model.addAttribute("title", "Modify");
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPost(BoardVO vo) throws Exception {
		logger.info("board modify Post...");
		logger.info("boardVO : " + vo);

		service.modify(vo);
		return "redirect:/board/read?bno=" + vo.getBno();
	}

	/* -- page -- */

	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void ListByPage(Model model, Criteria criteria) throws Exception {
		logger.info("board ListByPage Get...");

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

		service.upViewCount(bno, 1);
		BoardVO vo = service.read(bno);
		model.addAttribute("boardVO", vo);
		model.addAttribute("title", "Read");
	}

	@RequestMapping(value = "/removePage", method = RequestMethod.GET)
	public String removePage(int bno, Criteria criteria) throws Exception {
		logger.info("board remove Get...");
		logger.info("bno : " + bno);

		service.remove(bno);
		return "redirect:/board/listPage?page=" + criteria.getPage();
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
		return String.format("redirect:/board/readPage?bno=%s&page=%s", vo.getBno(), criteria.getPage());
	}
}

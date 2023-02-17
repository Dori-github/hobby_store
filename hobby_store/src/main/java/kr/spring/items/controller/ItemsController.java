package kr.spring.items.controller;

import java.net.http.HttpRequest;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.spring.items.service.ItemsService;
import kr.spring.items.vo.ItemsVO;

@Controller
public class ItemsController {
	private ItemsService itemsService;
	private static final Logger logger = LoggerFactory.getLogger(ItemsController.class);
	
	//1. 상품 등록//
	//1-1 등록 폼 호출//
	@GetMapping("/items/register.do")
	public String form() {
		return "ItemsRegister";
	}
	//1-2 등록 데이터 처리
	@PostMapping("/items/register.do")
	public String submit(@Valid ItemsVO itemsVO, BindingResult result, HttpSession session, HttpRequest request, RedirectAttributes redirect) {
		logger.debug("<<상품 등록 정보>> : " + itemsVO);

		return form();
	}
}

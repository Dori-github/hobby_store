package kr.spring.member.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import kr.spring.member.vo.MemberVO;

@Controller
public class MemberController {
	//로그생성 
		private static final Logger logger = 
		  LoggerFactory.getLogger(MemberController.class);
		
		//폼을 호출하기 위한 자바빈(VO) 초기화
		@ModelAttribute
		public MemberVO initCommand() {
			return new MemberVO();
		}
		
		//=========회원가입============//
		//1.회원가입 폼 호출
		@GetMapping("/member/registerUser.do")
		public String form() {
			return "memberRegister";//타일스 설정값
		}
		
		
}

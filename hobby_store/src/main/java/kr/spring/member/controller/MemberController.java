package kr.spring.member.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;

@Controller
public class MemberController {
	//로그생성 
		private static final Logger logger = 
		  LoggerFactory.getLogger(MemberController.class);
		
		@Autowired //memberSerVice 주입 
		private MemberService memberService;
		
		//폼을 호출하기 위한 자바빈(VO) 초기화
		@ModelAttribute
		public MemberVO initCommand() {
			return new MemberVO();
		}
		
		//=========회원가입============//
		//회원가입 폼 호출
		@GetMapping("/member/registerUser.do")
		public String form() {
			return "memberRegister";//타일스 설정값
		}
		
		//회원가입 데이터 전송
		@PostMapping("/member/registerUser.do")
		public String submit(@Valid MemberVO memberVO,
				     BindingResult result, Model model) {
			
			//로그처리
			//memberVO에 담긴 오류를 보여줌
			logger.debug("<<회원가입>> : " + memberVO);
			
			//유효성 체크 결과 오류가 있으면 폼 호출
			if(result.hasErrors()) {
				return form();
			}
			
			//회원가입
			memberService.insertMember(memberVO);
			
			model.addAttribute("accessMsg", 
					     "회원가입이 완료되었습니다.");
			
			//tiles 를 설정하지 않고 독립적으로 페이지가 보여지게함
			return "common/notice";
		}
		
}

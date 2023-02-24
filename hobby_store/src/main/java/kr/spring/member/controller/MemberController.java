package kr.spring.member.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	//아이디 중복  체크
	@RequestMapping("/member/confirmId.do")
	@ResponseBody
	public Map<String,String> Idprocess(
			             @RequestParam String mem_id){
		logger.debug("<<id>> : " + mem_id);
		
		//Map에 담아서 데이터를 처리
		Map<String,String> mapAjax = 
				new HashMap<String,String>();
		
		MemberVO member = 
				memberService.selectCheckMember(mem_id);
		if(member!=null) {
			//아이디 중복
			mapAjax.put("result", "idDuplicated");
		}else {
			if(!Pattern.matches("^[A-Za-z0-9]{4,12}$", mem_id)) {
				//패턴 불일치
				mapAjax.put("result", "notMatchPattern");
			}else {
				//패턴 일치하면서 아이디 미중복
				mapAjax.put("result", "idNotFound");
			}
		}
		
		return mapAjax;
	}
	
	//닉네임 중복 체크
		@RequestMapping("/member/confirmNickname.do")
		@ResponseBody
		public Map<String,String> Nicknameprocess(
				             @RequestParam String mem_nickname){
			logger.debug("<<nickname>> : " + mem_nickname);
			
			//Map에 담아서 데이터를 처리
			Map<String,String> mapAjax = 
					new HashMap<String,String>();
			
			MemberVO member = 
					memberService.selectCheckNickname(mem_nickname);
			if(member!=null) {
				//닉네임 중복
				mapAjax.put("result", "nicknameDuplicated");
			}else {
				//닉네임 미중복
					mapAjax.put("result", "nicknameNotFound");
			}
			
			return mapAjax;
		}

	
	
	//회원가입 폼 호출
	@GetMapping("/member/registerUser.do")
	public String form(Model model) {
		
		//선호지역 목록 
		List<MemberVO> countryList = memberService.getCountryList();
		logger.debug("<<회원가입 - countryList>> : " + countryList);
		model.addAttribute("countryList", countryList);
		
		//관심사 목록
		List<MemberVO> likeList = memberService.getLikeList();
		logger.debug("<<회원가입 - likeList>> : " + likeList);	
		model.addAttribute("likeList", likeList);
		
  	  
		
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
			return form(model);
		}

		//회원가입
		memberService.insertMember(memberVO);

		model.addAttribute("accessMsg", 
				"회원가입이 완료되었습니다.");

		//tiles 를 설정하지 않고 독립적으로 페이지가 보여지게함
		return "common/notice";

	}
	
	//=========회원로그인============//
	//로그인 폼 호출
		@GetMapping("/member/login.do")
		public String formLogin() {
			return "memberLogin";
		}
}










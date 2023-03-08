package kr.spring.talk.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.talk.service.TalkService;
import kr.spring.talk.vo.TalkRoomVO;

@Controller
public class TalkController {
	private static final Logger logger =
			LoggerFactory.getLogger(
					          TalkController.class);
	
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private TalkService talkService;

	
	//======채팅방 생성======//
	//채팅방 생성 폼 호출
	@GetMapping("/talk/talkRoomWrite.do")
	public String talkRoomWrite() {
		return "talkRoomWrite";
	}
	
	//=====채팅 목록========//
	@RequestMapping("/talk/talkList.do")
	public String chatList(String keyword,
			               HttpSession session,
			               Model model) {
		
		return "talkList";
	}
	
	//폼에서 전송된 데이터 처리
		@PostMapping("/talk/talkRoomWrite.do")
		public String submitTalkRoom(TalkRoomVO vo) {
			
			logger.debug("<<채팅방 생성>> : " + vo);
			
			talkService.insertTalkRoom(vo);
			
			return "redirect:/talk/talkList.do";
		}
	
	//=====채팅회원검색========//
	@RequestMapping("/talk/memberSearchAjax.do")
	@ResponseBody
	public Map<String,Object> SearchMember(
			             @RequestParam String mem_id,
			               HttpSession session){
		Map<String,Object> mapAjax = 
				new HashMap<String,Object>();
		
		MemberVO memberVO = 
			  (MemberVO)session.getAttribute("user");
		if(memberVO==null) {//로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		}else {//로그인 된 경우
			List<MemberVO> member = 
				memberService.selectSearchMember(mem_id);
			mapAjax.put("result", "success");
			mapAjax.put("member", member);
		}
		return mapAjax;
	}
	
}








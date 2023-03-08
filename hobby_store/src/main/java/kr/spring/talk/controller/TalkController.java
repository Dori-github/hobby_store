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
import kr.spring.talk.vo.TalkVO;

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

		MemberVO user = 
				(MemberVO) session.getAttribute("user");

		//Map에 담아서 데이터 처리
		Map<String,Object> map = 
				new HashMap<String,Object>();
		map.put("keyword", keyword); 
		map.put("mem_num", user.getMem_num()); //로그인 된 정보를 읽어온다.

		List<TalkRoomVO> list = 
				talkService.selectTalkRoomList(map);
		model.addAttribute("list", list);

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
			@RequestParam String mem_nickname,
			HttpSession session){
		Map<String,Object> mapAjax = 
				new HashMap<String,Object>();

		MemberVO memberVO = 
				(MemberVO)session.getAttribute("user");
		if(memberVO==null) {//로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		}else {//로그인 된 경우
			List<MemberVO> member = 
					memberService.selectSearchMember(mem_nickname);
			mapAjax.put("result", "success");
			mapAjax.put("member", member);
		}
		return mapAjax;
	}

	//=======채팅 메시지 페이지 호출=======//
	//채팅에 필요한 정보를 호출하는 곳 
	@RequestMapping("/talk/talkDetail.do")
	public String chatDetail(
			@RequestParam int talkroom_num,
			Model model) {
		//채팅방에 관련된 정보
		TalkRoomVO talkRoomVO = 
				talkService.selectTalkRoom(talkroom_num);
		//채팅방 멤버 정보
		List<TalkVO> list = 
				talkService.selectTalkMember(talkroom_num);

		model.addAttribute("talkRoomVO", talkRoomVO);
		model.addAttribute("list", list);

		return "talkDetail";
	}

	//=======채팅 메시지 읽기========//
	@RequestMapping("/talk/talkDetailAjax.do")
	@ResponseBody
	public Map<String,Object> talkDetailAjax(
			@RequestParam int talkroom_num,
			HttpSession session){
		Map<String,Object> mapAjax =
				new HashMap<String,Object>();
		MemberVO user = 
				(MemberVO)session.getAttribute("user");
		if(user==null) { //로그인이 되어있지 않은 경우
			mapAjax.put("result", "logout"); //logout이라고 알려줌 
		}else { //로그인이 된 경우
			Map<String,Integer> map = 
					new HashMap<String,Integer>();
			map.put("talkroom_num", talkroom_num);
			map.put("mem_num", user.getMem_num());

			List<TalkVO> list = 
					talkService.selectTalkDetail(map);
			mapAjax.put("result", "success");
			mapAjax.put("list", list);
		}

		return mapAjax;
	}

}








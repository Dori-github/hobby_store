package kr.spring.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.servlet.ModelAndView;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;

@Controller
public class MemberAdminController {
	private static final Logger logger =
			           LoggerFactory.getLogger(
					     MemberAdminController.class);
	@Autowired
	private MemberService memberService;
	
	//=========회원목록관리========//
	@RequestMapping("/member/admin_list.do")
	public ModelAndView process(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
											String keyfield, String keyword) {
		
		Map<String,Object> map = new HashMap<String,Object>();

		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		//총 글의 개수 또는 검색된 글의 갯수
		int count = memberService.selectMemberRowCount(map);
		
		logger.debug("<<회원관리>> : " + count);
		
		//페이지 처리
		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,count,20,10,"admin_list.do");
		
		List<MemberVO> list = null;
		if(count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			
			list = memberService.selectMemberList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin_memberList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());
		
		return mav;
	}
	
	//=======회원정보수정=======//
	//수정 폼 호출
	@GetMapping("/member/admin_update.do")
	public String form(@RequestParam int mem_num,
			                          Model model) {
		MemberVO memberVO = 
				memberService.selectMember(mem_num);
		model.addAttribute("memberVO", memberVO);
		
		return "admin_memberModify";
	}
	
	//수정 폼에서 전송된 데이터 처리
	@PostMapping("/member/admin_modify.do")
	public String submit(@RequestParam int mem_num,@RequestParam int mem_auth, Model model, HttpServletRequest request) {
		
		MemberVO memberVO = new MemberVO();
		
		memberVO.setMem_num(mem_num);
		memberVO.setMem_auth(mem_auth);
		
		logger.debug("<<관리자 회원권한수정>> : " + memberVO);
		
		//회원정보수정
		memberService.updateByAdmin(memberVO);
		
		//View에 표시할 메시지
		model.addAttribute("message", "회원권한수정 완료!");
		model.addAttribute("url", 
				request.getContextPath()+"/member/admin_list.do");
		
		return "common/resultView";
	}
	
}











package kr.spring.mypage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.member.controller.MemberController;
import kr.spring.member.vo.MemberVO;
import kr.spring.mypage.service.MypageService;
import kr.spring.mypage.vo.MypageVO;
import kr.spring.util.FileUtil;

@Controller
public class MypageController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	private MypageService mypageService;
	
	//자바빈 초기화
	@ModelAttribute
	public MemberVO initCommand() {
		return new MemberVO();
	}
	
	//======회원 상세======//
	/*@RequestMapping("/member/myPage.do")
	public String process(HttpSession session, Model model) {
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		MemberVO member = mypageService.selectMember(user.getMem_num());
		logger.debug("<<회원상세정보>> : " + member);
		
		model.addAttribute("member", member);
		
		return "myPage";
	}
	
	//=====프로필 사진 업로드======//
	@RequestMapping("/member/updateMyPhoto.do")
	@ResponseBody
	public Map<String,String> processProfile(MemberVO memberVO,HttpSession session){
			
		Map<String,String> mapAjax = new HashMap<String,String>();
			
			MemberVO user = (MemberVO)session.getAttribute("user");
			if(user==null) {
				mapAjax.put("result", "logout");
			}else {
				memberVO.setMem_num(user.getMem_num());
				mypageService.updateProfile(memberVO);
				
				mapAjax.put("result", "success");
			}
			
			return mapAjax;
		}
		
	//=====회원정보수정======//
	//수정 폼 호출
	@RequestMapping("/member/update.do")
	public String formUpdate(HttpSession session, Model model) {
		
		MemberVO user = (MemberVO)session.getAttribute("user");

		MemberVO memberVO = mypageService.selectMember(user.getMem_num());

		model.addAttribute("memberVO", memberVO);

		return "memberModify";
	}
	
	//수정 폼에서 전송된 데이터 호출
	@PostMapping("/member/update.do")
	public String submitUpdate(@Valid MemberVO memberVO,BindingResult result,HttpSession session) {

		logger.debug("<<회원정보수정 처리>> : " + memberVO);

		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return "memberModify";
		}

		MemberVO user = (MemberVO)session.getAttribute("user");
		memberVO.setMem_num(user.getMem_num());

		//회원정보수정
		mypageService.updateMember(memberVO);		

		return "redirect:/member/myPage.do";
	}
	
	//프로필 사진 출력(로그인 전용)
	@RequestMapping("/member/photoView.do")
	public String getProfile(HttpSession session, HttpServletRequest request, Model model) {
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		logger.debug("<<photoView>> : " + user);
		
		if(user==null) {
			byte[] readbyte = FileUtil.getBytes(request.getServletContext().getRealPath("/image_bundle/face.png"));
			
			model.addAttribute("imageFile",readbyte);
			model.addAttribute("filename","face.png");
		}else {
			MemberVO memberVO = mypageService.selectMember(user.getMem_num());
			logger.debug("<<프로필 사진 처리(memberVO)>>" + memberVO);
			viewProfile(memberVO,request,model);
		}
		
		return "imageView";
	}
	
	//프로필 사진 출력(회원번호 지정)
	@RequestMapping("/member/viewProfile.do")
	public String getProfileByMem_num(@RequestParam int mem_num,HttpSession session,HttpServletRequest request,Model model) {

		MemberVO memberVO = mypageService.selectMember(mem_num);
		viewProfile(memberVO,request,model);

		return "imageView";
	}
	
	//프로필 사진 처리
	public void viewProfile(MemberVO memberVO, HttpServletRequest request, Model model) {
		if(memberVO==null || memberVO.getMem_pname()==null) {
			byte[] readbyte = FileUtil.getBytes(request.getServletContext().getRealPath("/image_bundle/face.png"));
			model.addAttribute("imageFile",readbyte);
			model.addAttribute("filename","face.png");
		}else {
			model.addAttribute("imageFile",memberVO.getMem_photo());
			model.addAttribute("filename",memberVO.getMem_pname());
		}
	}*/
}

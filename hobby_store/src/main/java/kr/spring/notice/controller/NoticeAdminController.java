package kr.spring.notice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.servlet.ModelAndView;

import kr.spring.member.vo.MemberVO;
import kr.spring.notice.service.NoticeService;
import kr.spring.notice.vo.NoticeVO;
import kr.spring.util.PagingUtil;

@Controller
public class NoticeAdminController {
	private static final Logger logger = 
			LoggerFactory.getLogger(NoticeAdminController.class);
	@Autowired
	private NoticeService noticeService;

	@ModelAttribute
	public NoticeVO initCommand() {
		return new NoticeVO();
	}   

	//=======공지사항 목록====//
	@RequestMapping("/notice/admin_list.do")
	public ModelAndView process(
			@RequestParam(value="pageNum",defaultValue="1") 
			int currentPage,
			@RequestParam(value="order",defaultValue="1") 
			int order,
			String keyfield,
			String keyword) {

		Map<String,Object> map = 
				new HashMap<String,Object>();
		map.put("keyfield",keyfield);
		map.put("keyword", keyword);
		map.put("order", order);
		//status가 0이면 미표시(1),표시(2) 모두 체크
		map.put("status", 0);

		//상품의 총개수 또는 검색된 상품의 개수
		int count = noticeService.selectNoticeCount(map);

		logger.debug("<<상품 목록>> : " + count);

		//페이지 처리
		PagingUtil page = new PagingUtil(keyfield,
				keyword,currentPage,count,20,10,
				"list.do","&order="+order);

		List<NoticeVO> list = null;
		if(count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());

			list = noticeService.selectNoticeList(map);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("adminList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());

		return mav;
	}

	//=========상품등록=========//
	//상품등록 폼 호출
	@GetMapping("/notice/admin_write.do")
	public String form() {
		return "noticeAdminWrite";
	}
	//폼에서 전송된 데이터 처리
	@PostMapping("/notice/admin_write.do")
	public String submit(@Valid NoticeVO vo,
			BindingResult result, Model model,
			HttpServletRequest request,
			HttpSession session) {

		logger.debug("<<공지사항 등록>> : " + vo);

		//공지사항 이미지 유효성 체크
		if(vo.getPhoto1().length==0) {
			//upload1은 자바빈(vo)에 필드가 없기때문에 명시
			//할 수 없음
			result.rejectValue("photo1", "required");
		}
		if(vo.getPhoto2().length==0) {
			result.rejectValue("photo2", "required");
		}

		//이미지 용량 체크
		if(vo.getPhoto1().length > 5*1024*1024) {//5MB
			result.rejectValue("photo1", 
					"limitUploadSize",new Object[] {"5MB"},null);
		}
		if(vo.getPhoto2().length > 5*1024*1024) {
			result.rejectValue("photo2", 
					"limitUploadSize",new Object[] {"5MB"},null);
		}

		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return form();
		}

		MemberVO user = (MemberVO)session.getAttribute("user");
		vo.setMem_num(user.getMem_num());

		noticeService.insertNotice(vo);

		//View에 표시할 메시지
		model.addAttribute("message", 
				"공지사항 등록이 완료되었습니다.");
		model.addAttribute("url", 
				request.getContextPath()+"/notice/noticeList.do");


		return "common/resultView";
	}

	//========공지수정=========//
	//공지수정 폼 호출
	@GetMapping("/notice/admin_modify.do")
	public String formUpdate(
			@RequestParam int noti_num,
			Model model) {
		NoticeVO noticeVO = 
				noticeService.selectNotice(noti_num);
		model.addAttribute("noticeVO", noticeVO);

		return "noticeModify";
	}

	//폼에서 전송된 데이터 처리
	@PostMapping("/notice/admin_modifyForm.do")
	public String submitUpdate(
			@Valid NoticeVO noticevo,BindingResult result,
			Model model, HttpServletRequest request) {

		logger.debug("<<공지수정>> : " + noticevo);

		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return "noticeModify";
		}

		//공지수정 
		noticeService.updateNotice(noticevo);

		//View에 표시할 메시지
		model.addAttribute("message", 
				"공지수정이 완료되었습니다.");
		model.addAttribute("url", 
				request.getContextPath() 
				+ "/notice/detail.do?noti_num="
				+noticevo.getNoti_num());

		return "common/resultView";
	}

	//=====게시판 글삭제=======//
	@RequestMapping("/notice/delete.do")
	public String submitDelete(
			@RequestParam int noti_num,
			Model model,
			HttpServletRequest request) {

		logger.debug("<<공지 삭제>> : " + noti_num);

		//글삭제
		noticeService.deleteNotice(noti_num);

		return "redirect:/notice/noticeList.do";
	}

	//=====파일 삭제=======//
	@RequestMapping("/notice/deleteFile.do")
	@ResponseBody
	public Map<String,String> processFile(
			int noti_num, int noti_type,
			HttpSession session){
		Map<String,String> mapJson = 
				new HashMap<String,String>();

		MemberVO user = 
				(MemberVO)session.getAttribute("user");
		if(user==null) {
			mapJson.put("result", "logout");
		}else {
			Map<String,Integer> map = new HashMap<String,Integer>();
		    map.put("noti_num", noti_num);
		    map.put("noti_type",noti_type);
		    
			noticeService.deleteFile(map);
			mapJson.put("result", "success");
		}

		return mapJson;
	}
	/*
	//=====이미지 출력=====//
		@RequestMapping("/notice/imageView.do")
		public ModelAndView viewImage(
				        @RequestParam int noti_num,
				        @RequestParam int noti_type) {
			
			NoticeVO notice = 
					noticeService.selectNotice(noti_num);
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("imageView");
			
			if(noti_type==2) {//프로필 사진
				mav.addObject("imageFile",notice.getPhoto2());
				mav.addObject("filename", notice.getPhoto_name2());
			}
			else if(noti_type==2) {//업로드된 이미지
				mav.addObject("imageFile", notice.getPhoto2());
				mav.addObject("filename", notice.getPhoto_name2());
			}
			
			return mav;
		}
		*/
}
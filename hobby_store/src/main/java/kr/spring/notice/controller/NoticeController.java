package kr.spring.notice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.member.vo.MemberVO;
import kr.spring.notice.service.NoticeService;
import kr.spring.notice.vo.NoticeFavVO;
import kr.spring.notice.vo.NoticeVO;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;

@Controller
public class NoticeController {
	//로그생성 
	private static final Logger logger = 
			LoggerFactory.getLogger(NoticeController.class);

	@Autowired
	private NoticeService noticeService;

	//자바빈 초기화
	@ModelAttribute
	public NoticeVO initCommand() {
		return new NoticeVO();
	}

	//공지사항 목록 
	@RequestMapping("/notice/noticeList.do")
	public ModelAndView process(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
								@RequestParam(value="order",defaultValue="1") String order,
			String keyfield, String keyword) {

		Map<String,Object> map = 
				new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		map.put("order", order);

		//글의 총개수 또는 검색된 글의 개수
		int count = noticeService.selectNoticeCount(map);
		

		logger.debug("<<count>> : " + count);

		//페이지 처리
		PagingUtil page = 
				new PagingUtil(keyfield,keyword,
						currentPage,count,20,10,"list.do");

		List<NoticeVO> list = null;
		if(count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());

			list = noticeService.selectNoticeList(map);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("noticeList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());

		return mav;
	}

	//========공지사항 상세=======//
	@RequestMapping("/notice/detail.do")
	public ModelAndView process(
			@RequestParam int noti_num) {
		logger.debug("<<공지상세>> : " + noti_num);

		//해당 글의 조회수 증가
		noticeService.updateHit(noti_num);

		NoticeVO notice = 
				noticeService.selectNotice(noti_num);

		//제목에 태그를 허용하지 않음
		notice.setNoti_title(StringUtil.useNoHtml(
				notice.getNoti_title()));
		//내용에 태그를 허용하지 않음
		//CKEditor 사용시 주석 처리
		//board.setContent(StringUtil.useBrNoHtml(board.getContent()));
		//뷰이름      속성명   속성값
		return new ModelAndView("noticeView","notice",notice);
	}
	//이미지 출력
	@RequestMapping("/notice/imageView.do")
	public ModelAndView viewImage(
			@RequestParam int noti_num,
			@RequestParam int noti_type) {

		NoticeVO noticeVO = 
				noticeService.selectNotice(noti_num);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");

		if(noti_type==1) {
			mav.addObject("imageFile", noticeVO.getPhoto1());
			mav.addObject("filename", noticeVO.getPhoto_name1());
		}else if(noti_type==2) {
			mav.addObject("imageFile", noticeVO.getPhoto2());
			mav.addObject("filename", noticeVO.getPhoto_name2());
		}
		return mav;
	}

	//======부모글 좋아요=======//
	//좋아요 읽기
	@RequestMapping("/notice/getFav.do")
	@ResponseBody
	public Map<String,Object> getFav(NoticeFavVO fav,
			HttpSession session){
		logger.debug("<<게시판 좋아요>> : " + fav);

		Map<String,Object> mapJson = 
				new HashMap<String,Object>();

		MemberVO user = 
				(MemberVO)session.getAttribute("user");
		if(user==null) {
			mapJson.put("status", "noFav");
		}else {
			//로그인된 아이디 셋팅
			fav.setMem_num(user.getMem_num());

			NoticeFavVO noticeFav = 
					noticeService.selectFav(fav);
			if(noticeFav!=null) {
				mapJson.put("status", "yesFav");
			}else {
				mapJson.put("status", "noFav");
			}
		}
		mapJson.put("count", 
				noticeService.selectFavCount(
						fav.getNoti_num()));

		return mapJson;
	}
	
	//좋아요 등록
	@RequestMapping("/notice/writeFav.do")
	@ResponseBody
	public Map<String,Object> writeFav(
			NoticeFavVO fav,
			HttpSession session){
		logger.debug("<<부모글 좋아요 등록>> : " + fav);

		Map<String,Object> mapJson =
				new HashMap<String,Object>();

		MemberVO user = 
				(MemberVO)session.getAttribute("user");
		if(user==null) {
			mapJson.put("result", "logout");
		}else {
			//로그인된 회원번호 셋팅
			fav.setMem_num(user.getMem_num());

			logger.debug("<<부모글 좋아요 등록>> : " + fav);

			NoticeFavVO noticeFav = 
					noticeService.selectFav(fav);
			if(noticeFav!=null) {
				//좋아요가 이미 등록되어있으면 삭제
				noticeService.deleteFav(
						noticeFav.getFav_num());

				mapJson.put("result", "success");
				mapJson.put("status", "noFav");
			}else {
				//좋아요 미등록이면 등록
				noticeService.insertFav(fav);

				mapJson.put("result", "success");
				mapJson.put("status", "yesFav");
			}

			mapJson.put("count", 
					noticeService.selectFavCount(
							fav.getNoti_num()));
		}
		return mapJson;
	}
}

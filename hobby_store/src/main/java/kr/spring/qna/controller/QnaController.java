package kr.spring.qna.controller;

import java.util.Collections;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.spring.member.vo.MemberVO;
import kr.spring.qna.service.QnaService;
import kr.spring.qna.vo.QnaReplyVO;
import kr.spring.qna.vo.QnaVO;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;


@Controller
public class QnaController {
	private static final Logger logger = 
			LoggerFactory.getLogger(
					QnaController.class);

	private int rowCount = 20;

	@Autowired
	private QnaService qnaService;

	//자바빈(VO) 초기화
	@ModelAttribute
	public QnaVO initCommand() {
		return new QnaVO();
	}
	//=======게시판 글 목록=========//
	@RequestMapping("/qna/list.do")
	public ModelAndView process(
			@RequestParam(value="pageNum",defaultValue="1") 
			int currentPage,
			String keyfield,String keyword, 
			@RequestParam(value="order",defaultValue="1") int order) {

		Map<String,Object> map = 
				new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);

		//글의 총개수 또는 검색된 글의 개수
		int count = qnaService.selectRowCount(map);

		logger.debug("<<count>> : " + count);

		//페이지 처리
		PagingUtil page = 
				new PagingUtil(keyfield,keyword,
						currentPage,count,20,10,"list.do");

		List<QnaVO> list = null;
		if(count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());

			list = qnaService.selectList(map);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("qnaList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());

		return mav;
	}
	//====== QnA쓰기 ======//
	//등록 폼 호출
	@GetMapping("/qna/write.do")
	public String form(QnaVO qnaVO) {
		return "qnaWrite";
	}

	//등록 폼에서 전송된 데이터 처리
	@PostMapping("/qna/write.do")
	public String submit(@Valid QnaVO qnaVO,
			BindingResult result,
			HttpServletRequest request,
			RedirectAttributes redirect,
			HttpSession session) {
		logger.debug("<<QnA 글쓰기>> : " + qnaVO);

		//유효성 체크 결과 오류가 있으면 폼을 호출
		if(result.hasErrors()) {
			return "qnaWrite";
		}

		//강의번호,상품,공간대 셋팅 (session에 저장된 각 번호를 VO에 저장
		//qnaVO.setCourse_num(((CourseVO)session.getAttribute(null)).getCourse_num());

		//회원번호 셋팅
		qnaVO.setMem_num(
				((MemberVO)session.getAttribute("user")).getMem_num());

		//글쓰기
		qnaService.insertQna(qnaVO);

		//RedirectAttributes 객체는 리다이렉트 시점에
		//한 번만 사용되는 데이터를 전송.
		//브라우저에 데이터를 전송하지만 URL상에 보이지 않는
		//숨겨진 데이터의 형태로 전달
		redirect.addFlashAttribute("result","success");

		return "redirect:/qna/list.do";
	}

	//==== QnA 글 상세 ====//
	@RequestMapping("/qna/detail.do")
	public ModelAndView process(
			@RequestParam int qna_num) {
		logger.debug("<<qna_num>> : " + qna_num);

		//해당 글의 조회수 증가
		qnaService.updateHit(qna_num);

		QnaVO qna = 
				qnaService.selectQna(qna_num);

		//제목에 태그를 허용하지 않음
		qna.setQna_title(StringUtil.useNoHtml(
				qna.getQna_title()));
		//내용에 태그를 허용하지 않음
		//CKEditor 사용시 주석 처리
		//board.setContent(StringUtil.useBrNoHtml(board.getContent()));
		//뷰이름      속성명   속성값
		return new ModelAndView("qnaView","qna",qna);
	}

	//===== QnA 글수정 =====//
	//수정 폼 호출
	@GetMapping("/qna/update.do")
	public String formUpdate(
			@RequestParam int qna_num,
			Model model) {
		QnaVO qnaVO = 
				qnaService.selectQna(qna_num);

		model.addAttribute("qnaVO",qnaVO);

		return "qnaModify";
	}

	//수정 폼에서 전송된 데이터 처리
	@PostMapping("/qna/update.do")
	public String submitUpdate(@Valid QnaVO qnaVO,
			BindingResult result,
			HttpServletRequest request,
			Model model) {

		logger.debug("<<QnA수정>> : " + qnaVO);

		//유효성 체크 결과 오류가 있으면 폼을 호출
		if(result.hasErrors()) {
			//title 또는 content가 입력되지 않아서 유효성
			//체크에 걸리면 파일 정보를 잃어버리기 때문에
			//폼을 호출할 때 파일 정보를 다시 셋팅
			QnaVO vo = qnaService.selectQna(
					qnaVO.getQna_num());
			return "qnaModify";
		}

		//글수정
		qnaService.updateQna(qnaVO);

		//View에 표시할 메시지
		model.addAttribute("message", "글수정 완료!");
		model.addAttribute("url", 
				request.getContextPath()
				+"/qna/detail.do?qna_num="+qnaVO.getQna_num());

		return "common/resultView";
	}

	//========댓글 등록==========//
	@RequestMapping("/qna/writeReply.do")
	@ResponseBody
	public Map<String,String> writeReply(
			QnaReplyVO vo,
			HttpSession session,
			HttpServletRequest request){

		logger.debug("<<댓글 등록>> : " + vo);

		Map<String,String> mapJson = 
				new HashMap<String,String>();

		MemberVO user = 
				(MemberVO)session.getAttribute("user");
		if(user==null) {
			//로그인 안 됨
			mapJson.put("result","logout");
		}else {
			//회원번호 등록
			vo.setMem_num(user.getMem_num());
			//댓글 등록
			qnaService.insertReply(vo);
			mapJson.put("result", "success");
		}
		return mapJson;
	}
	
	//======댓글 목록========//
		@RequestMapping("/qna/listReply.do")
		@ResponseBody
		public Map<String,Object> getList(
				@RequestParam(value="pageNum",defaultValue="1")
				int currentPage,
				@RequestParam int qna_num,
				HttpSession session){
			
			logger.debug("<<currentPage>> : " + currentPage);
			logger.debug("<<qna_num>> : " + qna_num);
			
			Map<String,Object> map = 
					new HashMap<String,Object>();
			map.put("qna_num", qna_num);
			
			//총 글의 개수
			int count = qnaService.selectRowCountReply(map);
			
			//페이지 처리
			PagingUtil page = 
					new PagingUtil(currentPage,count,rowCount,1,null);
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			
			//목록 데이터 읽기
			List<QnaReplyVO> list = null;
			if(count > 0) {
				list = qnaService.selectListReply(map);
			}else {
				list = Collections.emptyList();
			}
			
			Map<String,Object> mapJson =
					new HashMap<String,Object>();
			mapJson.put("count", count);
			mapJson.put("rowCount", rowCount);
			mapJson.put("list", list);
			
			//===== 로그인 한 회원정보 셋팅 =====//
			MemberVO user = 
					(MemberVO)session.getAttribute("user");
			if(user!=null) {
				mapJson.put("user_num", user.getMem_num());
			}	
			
			return mapJson;
		}
}

package kr.spring.member.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import kr.spring.course.vo.CourseVO;
import kr.spring.event.vo.EventVO;
import kr.spring.items.vo.ItemsVO;
import kr.spring.member.service.EmailSender;
import kr.spring.member.service.MemberService;
import kr.spring.member.vo.Email;
import kr.spring.member.vo.MemberVO;
import kr.spring.order.vo.OrderDetailVO;
import kr.spring.order.vo.OrderVO;
import kr.spring.space.vo.SpaceVO;
import kr.spring.util.AuthCheckException;
import kr.spring.util.FileUtil;
import kr.spring.util.PagingUtil;


@Controller
public class MemberController {
	//로그생성 
	private static final Logger logger = 
			LoggerFactory.getLogger(MemberController.class);

	@Autowired //memberSerVice 주입 
	private MemberService memberService;
	@Autowired
	private EmailSender emailSender;
	@Autowired
	private Email email;



	//폼을 호출하기 위한 자바빈(VO) 초기화
	@ModelAttribute
	public MemberVO initCommand() {
		return new MemberVO();
	}

	//=========회원가입============//
	//아이디 중복 체크
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


	//로그인 폼에 전송된 데이터 처리
	@PostMapping("/member/login.do")
	//자동 로그인 처리에 필요한 session,response 저장 
	public String submitLogin(@Valid MemberVO memberVO,
			BindingResult result,
			HttpSession session,
			HttpServletResponse response) {

		logger.debug("<<회원로그인>> : " + memberVO);

		//유효성 체크 결과 오류가 있으면 폼을 호출
		//id와 passwd 필드만 체크
		if(result.hasFieldErrors("mem_id") || 
				result.hasFieldErrors("mem_pw")) {
			return formLogin();
		}

		//로그인 체크
		MemberVO member = null;
		//예외를 던지는 방법을 사용 
		//id를 selectCheckMember에 넘겨서 존재하는지 안하는지 체크 
		try {
			member = memberService.selectCheckMember(
					memberVO.getMem_id());
			//check가 false면 로그인 실패
			boolean check = false;

			if(member!=null) {
				//비밀번호 일치 여부 체크
				//입력한 비밀번호 넣어주기
				check = member.isCheckedPassword(
						memberVO.getMem_pw());
			}
			if(check) {//인증 성공(chect값 확인하기)

				//자동로그인 체크
				boolean autoLogin = memberVO.getAuto() != null 
						&& memberVO.getAuto().equals("on");
				if(autoLogin) {
					//자동로그인 체크를 한 경우
					String mem_au_id = member.getMem_au_id();
					if(mem_au_id==null) {
						//자동로그인 체크 식별값 생성
						mem_au_id = UUID.randomUUID().toString();
						logger.debug("<<au_id>> : " + mem_au_id);
						memberService.updateAu_id(mem_au_id, 
								memberVO.getMem_id());
					}

					Cookie auto_cookie = 
							new Cookie("au-log",mem_au_id);
					//쿠키의 유효기간은 1주일
					auto_cookie.setMaxAge(60*60*24*7);
					auto_cookie.setPath("/");

					//생성한 쿠키를 클라이언트에 전송
					response.addCookie(auto_cookie);

				}

				//인증 성공, 로그인 처리
				//필요한 내용을 user에 저장해서 필요하면 가져다쓰기 
				session.setAttribute("user", member);

				logger.debug("<<인증 성공>> : " + member.getMem_id());


				//관리자는 관리자 메인으로 이동
				//관리자 페이지 만들어지면 수정 예정 
				if(member.getMem_auth() == 9) {
					return "redirect:/main/main.do";

					//사용자는 사용자 메인으로 이동
				}else {
					return "redirect:/main/main.do";
				}
			}
			//인증 실패 > 예외 처리
			throw new AuthCheckException();
			//예외처리 
		}catch(AuthCheckException e) {
			//인증 실패로 로그인폼 호출
			if(member!=null && member.getMem_auth()==0) {
				//정지회원 메시지 표시
				result.reject("noAuthority");
			}else {
				result.reject("invalidIdOrPassword");
			}

			logger.debug("<<인증 실패>>");

			return formLogin();
		}
	}
	//=========회원로그아웃============//
	@RequestMapping("/member/logout.do")
	public String processLogout(HttpSession session,
			HttpServletResponse response) {

		//로그아웃
		session.invalidate();

		//자동로그인 클라이언트 쿠키 처리
		//자동로그인 쿠키 삭제
		Cookie auto_cookie = new Cookie("au-log","");

		auto_cookie.setMaxAge(0);//쿠키 유효시간 만료
		auto_cookie.setPath("/");

		//클라이언트에 쿠키 전송
		response.addCookie(auto_cookie);		

		return "redirect:/main/main.do";
	}


	//=========아이디찾기============//
	//아이디찾기 폼 호출 
	@GetMapping("/member/idSearch.do")
	public String idSearchForm() {
		logger.debug("<<아이디 찾기 진입>>");

		return "memberIdSearch"; //타일스 식별자
	}

	//아이디찾기 폼에 전송된 데이터 처리
	@RequestMapping("/member/idSearchResult.do")
	public String idSearchprocess(@Valid MemberVO vo,BindingResult result,Model model) {

		if(result.hasFieldErrors("mem_email") || result.hasFieldErrors("mem_cell")) {
			return idSearchForm();
		}

		//아이디찾기
		String mem_id = memberService.selectIdSearch(vo);

		logger.debug("<<회원 아이디 찾기>> : " + mem_id);

		model.addAttribute("mem_id", mem_id);

		return "memberIdSearchResult";
	}  

	//=========비밀번호 찾기============//
	//비밀번호 폼 호출 
	@GetMapping("/member/pwSearch.do")
	public String passwdSerchForm() {
		logger.debug("<<비밀번호 찾기 진입>>");

		return "memberPasswdSearch";	// 타일스 식별자
	}

	//비밀번호찾기 폼에 전송된 데이터 처리
	// 비밀번호 찾기 - 데이터 처리
	@PostMapping("/member/pwSearchResult.do")
	public String passwdSerchProcess(@Valid MemberVO memberVO,BindingResult result,
			HttpSession session, String mem_name) throws Exception {

		logger.debug("<<비밀번호 찾기 데이터 확인>> : " + memberVO);
		//유효성 체크 결과 오류가 있으면 폼 호출
		//id와 passwd 필드만 체크
		if(result.hasFieldErrors("mem_id") || result.hasFieldErrors("mem_cell") || result.hasFieldErrors("mem_email")) {
			logger.debug("<<비밀번호 찾기 오류>> : " + result.getFieldErrors());
			return passwdSerchForm();
		}

		//로그인 체크(id,phone,email 일치 여부 체크)
		try {
			// 새로운 자바빈 객체에 담기								// 입력된 ID
			MemberVO member = memberService.selectCheckMember(memberVO.getMem_id());	// 입력된 ID를 토대로 회원 정보 담기	
			// 입력 아이디 넣어서 생성 존재하지 않다면 null

			boolean check = false;

			if(member!=null) {	//아이디 일치한 경우 이메일과 전화번호 인증 작업
				if(member.getMem_id().equals(memberVO.getMem_id()) && member.getMem_email().equals(memberVO.getMem_email()) && member.getMem_cell().equals(memberVO.getMem_cell())) {
					// 입력한 email과 phone가 DB에 저장된 회원 정보(email, phone)와 같은 경우
					check = true;

				}
			}

			if(check) {	//인증 성공, 로그인 처리
				

				//이메일 내용 작성 
				// JAVA Random 객체를 사용하여 숫자 + 문자 8자리 난수 생성
				String emailCheckCode = excuteGenerate();
				session.setAttribute("pwCode", emailCheckCode);
				logger.info("임시 비밀번호 : " + emailCheckCode);	
				
				//수정해야할 부분 
				//String mem_name = memberService.getMem_name(memberVO.getMem_num());
				
				email.setContent(
						"안녕하세요. 취미상점 임시비밀번호 안내 관련 이메일 입니다. " + "임시 비밀번호는 "
								+ emailCheckCode+ " 입니다.");
				email.setReceiver(memberVO.getMem_email());
				email.setSubject("취미상점 임시비밀번호 안내 메일입니다.");
				emailSender.sendEmail(email); //이메일 전송 

				// 회원 비밀번호 변경
				member.setMem_pw(emailCheckCode); 		// 생성한 난수 코드 비밀번호 지정
				memberService.updateMemberPasswd(member);

				return "memberPasswdSearchResult";

			}else {
				//인증 실패
				throw new AuthCheckException();
			}
		}catch(AuthCheckException e) {
			//인증 실패로 메시지 생성 및 로그인 폼 호출
			result.reject("invalidSearchPassword");
			return passwdSerchForm();
		}
	}

	//=========이메일 발송 ============//
	@RequestMapping("/member/mailCheck.do")
	@ResponseBody
	public Map<String,String> mailCheck(String mem_email,HttpSession session) throws Exception {

		//뷰(View)로부터 넘어온 데이터 확인
		logger.info("이메일 데이터 전송 확인");
		logger.info("수신자 이메일 : " + mem_email);

		// JAVA Random 객체를 사용하여 숫자 + 문자 8자리 난수 생성
		String emailCheckCode = excuteGenerate();
		session.setAttribute("emailCheckCode", emailCheckCode);
		logger.info("인증번호 : " + emailCheckCode);	

		email.setContent(
				"안녕하세요. 취미상점 인증번호 안내 관련 이메일 입니다. " + "인증번호는 "
						+ emailCheckCode+ " 입니다.");
		email.setReceiver(mem_email);
		email.setSubject("취미상점 인증 메일입니다.");
		emailSender.sendEmail(email);


		//Map에 담아서 데이터 처리 
		Map<String,String> mapAjax = new HashMap<String,String>();
		mapAjax.put("result","success");


		return mapAjax;
	}

	//=========인증번호 중복체크============//
	@RequestMapping("/member/mailCheckcode.do")
	@ResponseBody
	public Map<String,String> mailCheckprocess
	(@RequestParam String userInputcode,HttpSession session) throws Exception {

		//뷰(View)로부터 넘어온 데이터 확인
		logger.info("<<userInputcode>> : " + userInputcode);

		//Map에 담아서 데이터를 처리
		Map<String,String> mapAjax = 
				new HashMap<String,String>();

		//session에 저장된 인증번호
		String code = (String)session.getAttribute("emailCheckCode");
		logger.info("<<session 인증 코드>> : " + code);
		if(code.equals(userInputcode)) {
			//인증 성공
			mapAjax.put("result", "success");
		}else {
			//인증 실패
			mapAjax.put("result", "failure");
		}

		return mapAjax;
	}


	//=========이메일 인증 난수 생성 컨트롤러============//
	private int certCharLength = 8;

	private final char[] characterTable = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 
			'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 
			'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };

	public String excuteGenerate() {
		Random random = new Random(System.currentTimeMillis());
		int tablelength = characterTable.length;
		StringBuffer buf = new StringBuffer();

		for(int i = 0; i < certCharLength; i++) {
			buf.append(characterTable[random.nextInt(tablelength)]);
		}

		return buf.toString();
	}

	public int getCertCharLength() {
		return certCharLength;
	}

	public void setCertCharLength(int certCharLength) {
		this.certCharLength = certCharLength;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//===========마이페이지===========//
	//======회원 상세======//
	@RequestMapping("/member/myPage.do")
	public String process(HttpSession session, Model model) {
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		MemberVO member = memberService.selectMember(user.getMem_num());
		logger.debug("<<회원상세정보>> : " + member);
			
		model.addAttribute("member", member);
			
		return "myPage";
	}
	
	//=====회원정보수정======//
	//수정 폼 호출
	@RequestMapping("/member/update.do")
	public String formUpdate(HttpSession session, Model model) {

		MemberVO user = (MemberVO)session.getAttribute("user");

		MemberVO memberVO = memberService.selectMember(user.getMem_num());

		model.addAttribute("memberVO", memberVO);

		return "memberModify";
	}

	//수정 폼에서 전송된 데이터 호출
	@PostMapping("/member/update.do")
	public String submitUpdate(@Valid MemberVO memberVO,BindingResult result,HttpSession session, Model model, HttpServletRequest request) {

		logger.debug("<<회원정보수정 처리>> : " + memberVO);

		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return "memberModify";
		}

		MemberVO user = (MemberVO)session.getAttribute("user");
		memberVO.setMem_num(user.getMem_num());

		//회원정보수정
		memberService.updateMember(memberVO);	
		
		//View에 메시지 표시
		model.addAttribute("message","회원정보 수정이 완료되었습니다.");
		model.addAttribute("url",request.getContextPath()+"/member/myPage.do");
				
		return "common/resultView";
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
			MemberVO memberVO = memberService.selectMember(user.getMem_num());
			logger.debug("<<프로필 사진 처리(memberVO)>> : " + memberVO);
			viewProfile(memberVO,request,model);
		}

		return "imageView";
	}

	//프로필 사진 출력(회원번호 지정)
	@RequestMapping("/member/viewProfile.do")
	public String getProfileByMem_num(@RequestParam int mem_num,HttpSession session,HttpServletRequest request,Model model) {

		MemberVO memberVO = memberService.selectMember(mem_num);
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
			memberService.updateProfile(memberVO);

			mapAjax.put("result", "success");
		}

		return mapAjax;
	}
	
	//=====비밀번호 변경=====//
	//비밀번호 변경 폼 호출
	@GetMapping("/member/changePassword.do")
	public String formChangePassword() {
		return "changePassword";
	}
	
	//비밀번호 변경 폼에서 전송된 데이터 처리
	@PostMapping("/member/changePassword.do")
	public String submitChangePassword(@Valid MemberVO memberVO,BindingResult result,
			HttpSession session,Model model,HttpServletRequest request) {

		logger.debug("<<비밀번호변경 처리>> : " + memberVO);

		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasFieldErrors("now_pw") || result.hasFieldErrors("pw")) {
			return formChangePassword();
		}

		MemberVO user = (MemberVO)session.getAttribute("user");
		memberVO.setMem_num(user.getMem_num());

		MemberVO db_member = memberService.selectMember(memberVO.getMem_num());
		
		//폼에서 전송한 현재 비밀번호와 DB에서 받아온 비밀번호 일치 여부 체크
		if(!db_member.getMem_pw().equals(memberVO.getNow_pw())) {
			result.rejectValue("now_pw","invalidPassword");
			return formChangePassword();
		}

		//비밀번호변경
		memberService.updatePassword(memberVO);

		//모든 브라우저에 설정된 자동로그인 해제
		memberService.deleteAu_id(memberVO.getMem_num());

		model.addAttribute("message", "비밀번호 변경이 완료되었습니다");
		model.addAttribute("url", request.getContextPath()+"/member/myPage.do");

		return "common/resultView";
	}
	
	//좋아요 게시물 조회
	@RequestMapping("/member/fav.do")
	public ModelAndView favList(@RequestParam int cate_num, HttpSession session) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(cate_num==1) {
			
			List<CourseVO> list = null;
			map.put("mem_num", user.getMem_num());
			list = memberService.selectCourseFav(map);
			
			logger.debug("<<좋아요 목록>> : " + list);
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("favList");
			mav.addObject("list",list);
			mav.addObject("cate_num",cate_num);
			
			return mav;
			
		}else if(cate_num==2) {
			
			List<ItemsVO> list = null;
			map.put("mem_num", user.getMem_num());
			list = memberService.selectItemsFav(map);
			
			logger.debug("<<좋아요 목록>> : " + list);
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("favList");
			mav.addObject("list",list);
			mav.addObject("cate_num",cate_num);
			
			return mav;
			
		}else if(cate_num==3) {
			
			List<SpaceVO> list = null;
			map.put("mem_num", user.getMem_num());
			list = memberService.selectSpaceFav(map);
			
			logger.debug("<<좋아요 목록>> : " + list);
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("favList");
			mav.addObject("list",list);
			mav.addObject("cate_num",cate_num);
			
			return mav;
			
		}else {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("notice");
			return mav;
		}
	}
	
	//회원삭제 폼 호출
	@RequestMapping("/member/delete.do")
	public String formDelete() {
		return "memberDelete";
	}
	
	//등록 상품 조회
	@RequestMapping("/member/regisList.do")
	public ModelAndView regisList(HttpSession session, int cate_num) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(cate_num==1) {

			List<CourseVO> list = null;
			map.put("mem_num", user.getMem_num());
			list = memberService.selectCourseList(map);;
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("regisList");
			mav.addObject("list",list);
			mav.addObject("cate_num",cate_num);
			
			return mav;
			
		}else if(cate_num==2) {

			List<ItemsVO> list = null;

			map.put("mem_num", user.getMem_num());
			list = memberService.selectItemsList(map);
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("regisList");
			mav.addObject("list",list);
			mav.addObject("cate_num",cate_num);
			
			return mav;
			
		}else if(cate_num==3) {
			
			List<SpaceVO> list = null;
			
			map.put("mem_num", user.getMem_num());
			list = memberService.selectSpaceList(map);
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("regisList");
			mav.addObject("list",list);
			mav.addObject("cate_num",cate_num);
			
			return mav;
			
		}else {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("notice");
			return mav;
		}
	}
	
	//사용자 주문목록
		@RequestMapping("/member/order.do")
		public ModelAndView orderList(
				@RequestParam(value="pageNum",defaultValue="1") 
				int currentPage,
				String keyfield,String keyword,
				HttpSession session) {

			MemberVO user = 
					(MemberVO)session.getAttribute("user");
			Map<String,Object> map = 
					new HashMap<String,Object>();
			map.put("keyfield", keyfield);
			map.put("keyword", keyword);
			map.put("mem_num", user.getMem_num());

			//총글의 개수 또는 검색된 글의 개수
			int count = 
					memberService.selectOrderCountByMem_num(map);
			logger.debug("<<사용자 주문정보 count>> : " + count);

			//페이지 처리
			PagingUtil page = 
					new PagingUtil(keyfield,keyword,
							currentPage,count,10,10,
							"order.do");
			List<OrderVO> list = null;
			if(count > 0) {
				map.put("start", page.getStartRow());
				map.put("end", page.getEndRow());
				list = memberService.selectListOrderByMem_num(map);
			}

			ModelAndView mav = new ModelAndView();
			mav.setViewName("orderList");
			mav.addObject("count", count);
			mav.addObject("list", list);
			mav.addObject("page", page.getPage());

			return mav;
		}
	
	//사용자 주문정보수정 폼 호출
	@GetMapping("/member/orderModify.do")
	public String formUserModify(@RequestParam int order_num,Model model) {
			
		//주문정보
		OrderVO order = memberService.selectOrder(order_num);
		//개별 상품의 주문정보
		List<OrderDetailVO> detailList = memberService.selectListOrderDetail(order_num);
		logger.debug("<<사용자 주문정보수정 - 주문상세>> : " + detailList);
		
		model.addAttribute("order", order);
		model.addAttribute("detailList", detailList);		
			
		return "orderModify";
	}
		
	//사용자 주문취소
	@RequestMapping("/member/orderCancel.do")
	public String submitCancel(@RequestParam int order_num,Model model,HttpServletRequest request) {
		OrderVO db_order = memberService.selectOrder(order_num);
		if(db_order.getOrder_status()>2) {
			return "common/notice";
		}
			
		//주문취소
		memberService.updateOrderCancel(order_num);
		
		model.addAttribute("message","주문취소가 완료되었습니다.");
		model.addAttribute("url", request.getContextPath() +"/member/orderModify.do?order_num="+order_num);
			
		return "common/resultView";
		
		}
	
	//폼에서 전송된 데이터 처리
	@PostMapping("/member/orderModify.do")
	public String submitUserModify(OrderVO orderVO,Model model,HttpServletRequest request) {
		OrderVO db_order = memberService.selectOrder(orderVO.getOrder_num());
		if(db_order.getOrder_status()>2) {
			return "common/notice";
		}
		
		memberService.updateOrder(orderVO);
		
		model.addAttribute("message","주문정보가 변경되었습니다.");
		model.addAttribute("url", request.getContextPath()+"/member/orderModify.do?order_num="+orderVO.getOrder_num());
		
		return "common/resultView";
	}
	
	//관리자 주문목록
	@RequestMapping("/member/lec_order.do")
	public ModelAndView admin_list(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
			String keyfield,String keyword,HttpSession session) {

		MemberVO user = (MemberVO)session.getAttribute("user");
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		map.put("mem_num", user.getMem_num());

		//총글의 개수 또는 검색된 글의 개수
		int count = memberService.selectDeliveryCount(map);
		logger.debug("<<전체 주문정보 count>> : " + count);

		//페이지 처리
		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,count,10,10,"admin_orderList.do");
		List<OrderVO> list = null;
		if(count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			list = memberService.selectListDelivery(map);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("lecOrderList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());

		return mav;
	}

	//주문정보수정 폼 호출
	@GetMapping("/member/lec_modify.do")
	public String formModify(@RequestParam int order_num, Model model) {
		OrderVO order = memberService.selectOrder(order_num);
		
		List<OrderDetailVO> detailList = memberService.selectListOrderDetail(order_num);
		logger.debug("<<강사 주문정보수정 - 주문상세>> : " + detailList);
		
		model.addAttribute("order", order);
		model.addAttribute("detailList", detailList);
		
		return "lecOrderModify";
	}
	
	@PostMapping("/member/lec_modify.do")
	public String submitModify(OrderVO orderVO, Model model, HttpServletRequest request) {
		
		OrderVO db_order = memberService.selectOrder(orderVO.getOrder_num());
		if(db_order.getRefund_status()==1) {
			//주문자가 주문취소한 상품의 정보를 변경할 수 없음
			return "common/notice";
		}else {
			if(orderVO.getOrder_status() < 2 && (Integer)orderVO.getRefund_status()!=null &&
				db_order.getOrder_status()!=orderVO.getOrder_status()) {
				orderVO.setReceive_name(
						db_order.getReceive_name());
				orderVO.setReceive_post(
						db_order.getReceive_post());
				orderVO.setReceive_address1(
						db_order.getReceive_address1());
				orderVO.setReceive_address2(
						db_order.getReceive_address2());
				orderVO.setReceive_phone(
						db_order.getReceive_phone());
				orderVO.setNotice(
						db_order.getNotice());
			}
			
			memberService.updateOrder(orderVO);
			
			model.addAttribute("message", "주문 정보가 변경되었습니다.");
			model.addAttribute("url", request.getContextPath()+
					"/member/lec_modify.do?order_num="+orderVO.getOrder_num());
		}
		return "common/resultView";
	}

	//이벤트 신청목록
	@RequestMapping("/member/event.do")
	public ModelAndView eventApplyList(@RequestParam(value="pageNum",defaultValue="1") 
			int currentPage,String keyfield,String keyword,HttpSession session) {

		MemberVO user = (MemberVO)session.getAttribute("user");
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		map.put("mem_num", user.getMem_num());

		//총글의 개수 또는 검색된 글의 개수
		int count = memberService.selectEventApplyCount(map);
		logger.debug("<<신청이벤트 조회 count>> : " + count);

		//페이지 처리
		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,count,10,10,"event.do");
		List<EventVO> list = null;
		if(count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			list = memberService.selectListEventApply(map);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("eventApplyList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());

		return mav;
	}
	
	//배송조회
	/*@RequestMapping("/member/order.do")
	public ModelAndView orderList(@RequestParam(value="pageNum", defaultValue="1") int currentPage, HttpSession session) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		int count = memberService.selectOrderCount(user.getMem_num());
		
		PagingUtil page = new PagingUtil(currentPage,count,5,10,"order.do");
		
		List<OrderVO> list = null;
		if(count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			map.put("mem_num", user.getMem_num());
			list = memberService.selectOrderList(map);
		}
		
		logger.debug("<<주문 목록>> : " + count);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("orderList");
		mav.addObject("count",count);
		mav.addObject("list",list);
		mav.addObject("page",page.getPage());
		
		return mav;
	}*/
}










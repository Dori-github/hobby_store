package kr.spring.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;

public class AutoLoginCheckInterceptor 
                       implements HandlerInterceptor{
	private static final Logger logger =
			            LoggerFactory.getLogger(
					AutoLoginCheckInterceptor.class);
	
	@Autowired
	MemberService memberService;
	
	@Override
	public boolean preHandle(
			            HttpServletRequest request,
			            HttpServletResponse response,
			            Object handler)
			            		throws Exception{
		logger.debug("<<AutoLoginCheckInterceptor 진입>>");
		
		//로그인이 되어있으면 
		HttpSession session = request.getSession();
		MemberVO user = 
			  (MemberVO)session.getAttribute("user");
		if(user==null) { //로그인이 되어있으면
			Cookie now_cookie = findCookie( //쿠키처리 하지 않아도됨
					     request.getCookies(),"au-log");
			if(now_cookie!=null) { //로그인이 되어있지 않으면 
				MemberVO memberVO = 
						memberService.selectAu_id(
						          now_cookie.getValue()); //findCookie가 쿠키를 반환
				//자동로그인 처리
				//일반회원이상만 자동로그인처리(탈퇴,정지회원 자동로그인 불가)
				if(memberVO!=null && memberVO.getMem_auth()>=2) {
					session.setAttribute("user", memberVO);
					logger.debug("<<AutoLogin Success>>");
				}
			}
		}
		return true;
	}
	
	private Cookie findCookie(Cookie[] cookies,
			                  String name) { //1.쿠키 이름을 전송
		//필요로 하는 쿠키 구하기
		if(cookies == null || cookies.length == 0) {
			return null; //쿠키가 없을 경우 null을 반환
			
		}else { //쿠키가 있을 경우
			for(int i=0;i<cookies.length;i++) {
				String cookie_name = cookies[i].getName();
				if(cookie_name.equals(name)) { //2.쿠키 이름 같은 것을 반환
					return cookies[i]; //배열로 표시해서 보여줌
				}
			}
			return null;
		}
	}
	
}







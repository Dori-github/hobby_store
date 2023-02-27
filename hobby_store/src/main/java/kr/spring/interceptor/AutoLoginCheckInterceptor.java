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
		
		HttpSession session = request.getSession();
		MemberVO user = 
			  (MemberVO)session.getAttribute("user");
		if(user==null) {
			Cookie now_cookie = findCookie(
					     request.getCookies(),"au-log");
			if(now_cookie!=null) {
				MemberVO memberVO = 
						memberService.selectAu_id(
						          now_cookie.getValue());
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
			                  String name) {
		if(cookies == null || cookies.length == 0) {
			return null;
		}else {
			for(int i=0;i<cookies.length;i++) {
				String cookie_name = cookies[i].getName();
				if(cookie_name.equals(name)) {
					return cookies[i];
				}
			}
			return null;
		}
	}
	
}







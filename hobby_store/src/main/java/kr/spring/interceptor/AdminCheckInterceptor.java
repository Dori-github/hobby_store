package kr.spring.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import kr.spring.member.vo.MemberVO;

public class AdminCheckInterceptor implements 
                                 HandlerInterceptor{
	private static final Logger logger = 
			           LoggerFactory.getLogger(
					     AdminCheckInterceptor.class);
	
	@Override
	public boolean preHandle(
			             HttpServletRequest request,
			            HttpServletResponse response,
			              Object handler)throws Exception{
		
		//관리자가 로그인한지 체크
		HttpSession session = request.getSession();
		MemberVO user = 
			  (MemberVO)session.getAttribute("user");
		if(user == null || user.getMem_auth()!=9) {
			//관리자 권한이 아닐 때
			RequestDispatcher dispatcher = 
				request.getRequestDispatcher(
						"/WEB-INF/views/common/notice.jsp");
			dispatcher.forward(request, response);
			
			return false;
		}
		return true;
	}
}





package kr.spring.main.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.spring.member.vo.MemberVO;

@Controller
public class CommonController {
	private static final Logger logger = 
			         LoggerFactory.getLogger(
					       CommonController.class);
	
	@RequestMapping("/common/imageUploader.do")
	@ResponseBody
	public Map<String,Object> uploadImage(
			          MultipartFile upload,
			          HttpSession session,
			          HttpServletResponse response,
			          HttpServletRequest request)
			                        throws Exception{
		//업로드할 폴더 경로
		String realFolder = 
				session.getServletContext()
				       .getRealPath("/image_upload");
		
		//업로드할 파일 이름
		String org_filename = upload.getOriginalFilename();
		String str_filename = System.currentTimeMillis() + "_" + org_filename;
		
		logger.debug("<<원본 파일명>> : " + org_filename);
		logger.debug("<<저장할 파일명>> : " + str_filename);
		
		String subpath;//중간 경로
		MemberVO user = 
				(MemberVO)session.getAttribute("user");
		if(user==null) {
			subpath = "general";
		}else {
			subpath = String.valueOf(user.getMem_num());
		}
		
		String filepath = realFolder + "\\" + subpath 
				                       + "\\" + str_filename;
		logger.debug("<<파일경로>> : " + filepath);
		
		File f = new File(filepath);
		if(!f.exists()) {
			f.mkdirs();
		}
		//지정한 경로에 파일 복사
		upload.transferTo(f);
		
		Map<String,Object> map = 
				         new HashMap<String,Object>();
		map.put("uploaded", true);
		map.put("url", 
				request.getContextPath()+"/image_upload/"
		                      +subpath+"/"+str_filename);
		
		return map;
	}
}

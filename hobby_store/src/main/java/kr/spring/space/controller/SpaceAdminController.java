package kr.spring.space.controller;

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
import kr.spring.space.service.SpaceService;
import kr.spring.space.vo.SpaceVO;
import kr.spring.util.PagingUtil;

@Controller
public class SpaceAdminController {
	private static final Logger logger = 
			LoggerFactory.getLogger(SpaceAdminController.class);
	@Autowired
	private SpaceService spaceService;

	@ModelAttribute
	public SpaceVO initCommand() {
		return new SpaceVO();
	}
  // 공간등록 폼 호출
	@GetMapping("/space/admin_write.do")
	public String form() {
		return "spaceAdminWrite";
	}
	//폼에서 전송된 데이터 처리
		@PostMapping("/space/admin_write.do")
		public String submit(@Valid SpaceVO vo,
				BindingResult result, Model model,
				HttpServletRequest request,
				HttpSession session) {
			
			logger.debug("<<공간등록>> : " + vo);
			
			//상품 이미지 유효성 체크
			//MultipartFile -> byte[]로 변환할 경우 파일을
			//업로드하지 않으면 byte[]은 생성되고 length는 0
			if(vo.getSpace_photo().length==0) {
				//upload1은 자바빈(vo)에 필드가 없기때문에 명시
				//할 수 없음
				result.rejectValue("space_photo", "required");
			}
			if(vo.getSpace_photo1().length==0) {
				result.rejectValue("space_photo1", "required");
			}
			if(vo.getSpace_photo2().length==0) {
			
				result.rejectValue("space_photo2", "required");
			}
		
			//이미지 용량 체크
			if(vo.getSpace_photo().length > 5*1024*1024) {//5MB
				result.rejectValue("space_photo", 
						"limitUploadSize",new Object[] {"5MB"},null);
			}
			if(vo.getSpace_photo1().length > 5*1024*1024) {
				result.rejectValue("space_photo1", 
						"limitUploadSize",new Object[] {"5MB"},null);
			}
			if(vo.getSpace_photo2().length > 5*1024*1024) {//5MB
				result.rejectValue("space_photo2", 
						"limitUploadSize",new Object[] {"5MB"},null);
			}
		
			//유효성 체크 결과 오류가 있으면 폼 호출
			if(result.hasErrors()) {
		        return form();
			}
			vo.setMem_num(((MemberVO)session.getAttribute("user")).getMem_num());
			spaceService.insertspace(vo);
			logger.debug("<<공간등록>> : " + vo);
			
			//View에 표시할 메시지
			model.addAttribute("message", 
					          "상품 등록이 완료되었습니다.");
			model.addAttribute("url", 
			 request.getContextPath()+"/space/list.do");
			
			
			return "common/resultView";
		}
		//공간 수정
		//공간수정 폼 호출
		@GetMapping("/space/admin_modify.do")
		//@RequestMapping(value="excel", method = {RequestMethod.GET, RequestMethod.POST})
		public String formUpdate(
				       @RequestParam int space_num,
				                       Model model) {
			SpaceVO spaceVO = 
					spaceService.selectSpace(space_num);
			model.addAttribute("spaceVO", spaceVO);
			
			return "spaceAdminModify";
		}
		
		//폼에서 전송된 데이터 처리
		@PostMapping("/space/admin_modify.do")
		public String submitUpdate(
				@Valid SpaceVO spaceVO,BindingResult result,
				Model model, HttpServletRequest request) {
			
			logger.debug("<<상품수정>> : " + spaceVO);
			
			//유효성 체크 결과 오류가 있으면 폼 호출
			if(result.hasErrors()) {
				SpaceVO vo = spaceService.selectSpace(
						spaceVO.getSpace_num());
				vo.setSpace_photo(vo.getSpace_photo());
				
				return "spaceAdminModify";
			
			}
			spaceService.updateSpace(spaceVO);
			
			//View에 표시할 메시지
			model.addAttribute("message", 
					"공간 수정이 완료되었습니다.");
			model.addAttribute("url", 
					request.getContextPath() 
			      + "/space/detail.do?space_num="
							       +spaceVO.getSpace_num());
			
			return "common/resultView";
			}
		//=====게시판 글삭제=======//
		@RequestMapping("/space/delete.do")
		public String submitDelete(
				@RequestParam int space_num,
				Model model,
				HttpServletRequest request) {
			
			logger.debug("<<게시판 글삭제>> : " + space_num);
			
			//글삭제
			spaceService.deleteSpace(space_num);
			
			return "redirect:/space/list.do";
		}
		 
		 
		//=====파일 삭제=======//
		@RequestMapping("/space/deleteFile.do")
		@ResponseBody
		public Map<String,String> processFile(
				                   int space_num,
				                   HttpSession session){
			Map<String,String> mapJson = 
					new HashMap<String,String>();
			
			MemberVO user = 
				 (MemberVO)session.getAttribute("user");
			if(user==null) {
				mapJson.put("result", "logout");
			}else {
				spaceService.deletePhoto(space_num);
				
				mapJson.put("result", "success");
			}
			
			return mapJson;
		}
		
	
}

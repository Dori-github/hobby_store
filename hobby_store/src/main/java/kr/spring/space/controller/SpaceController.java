package kr.spring.space.controller;


import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import kr.spring.course.vo.CourseVO;
import kr.spring.items.vo.ItemsVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.space.service.SpaceService;
import kr.spring.space.vo.SpaceFavVO;
import kr.spring.space.vo.SpaceVO;
import kr.spring.util.PagingUtil;


@Controller
public class SpaceController {
	private static final Logger logger =
			LoggerFactory.getLogger(SpaceController.class);
	@Autowired
	private SpaceService spaceService;
	
	@ModelAttribute
	public SpaceVO initCommand() {
		return new SpaceVO();
	}

	// =======공간대여 상품 목록=========//
		@RequestMapping("/space/list.do")
		public ModelAndView process(@RequestParam(value = "pageNum", defaultValue = "1")  int currentPage,
		                            @RequestParam(value="order",defaultValue="1") int order,
		                                         String keyfield,String keyword,
		                                         @RequestParam(value="cate",defaultValue="") String cate,String location,
		                                         HttpSession session) {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("keyfield", keyfield);
			map.put("keyword", keyword);
			map.put("cate", cate);
			map.put("location", location);
			map.put("order", "1");
			
			//로그인한 회원정보
			MemberVO user = (MemberVO)session.getAttribute("user");
			
			//상품의 총개수 또는 검색된 상품의 개수
			int count = spaceService.selectSpaceCount(map);
					
			logger.debug("<<공간 목록>> : " + count);
					
			//페이지 처리
			PagingUtil page = new PagingUtil(keyfield,
					keyword,currentPage,count,20,10, "list.do","&cate="+cate);
							                    
			//목록불러오기		
			List<SpaceVO> list = null;
			if(count > 0) {
				map.put("mem_num", user.getMem_num());
			    map.put("start", page.getStartRow());
				map.put("end", page.getEndRow());
						
				list = spaceService.selectSpaceList(map);
					}

			ModelAndView mav = new ModelAndView();
			mav.setViewName("spaceList");//rang.xml ==name
			mav.addObject("count", count);
			mav.addObject("list", list);
			mav.addObject("page", page.getPage());		
		
			return mav;
		}

	
	
	//======공간상세========//
		@RequestMapping("/space/detail.do")
		public String detail(@RequestParam int space_num,
				                  Model model,
				                  HttpSession session){
			logger.debug("<<상품상세>> : " + space_num);
			
			//조회수 증가
			spaceService.updateHit(space_num);
			
			//로그인한 회원정보
			MemberVO user = (MemberVO)session.getAttribute("user");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mem_num", user);
			
			SpaceVO spaceVO = 
					spaceService.selectSpace(space_num);
			model.addAttribute("space", spaceVO);
			
			return "spaceView";
		}
	
	//이미지 출력
	@RequestMapping("/space/imageView.do")
	public ModelAndView viewImage(
			@RequestParam int space_num,
			@RequestParam int space_type) {
		
		SpaceVO spaceVO = 
				spaceService.selectSpace(space_num);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		
		if(space_type==1) {
			mav.addObject("imageFile", spaceVO.getSpace_photo());
			mav.addObject("filename", spaceVO.getSpace_photo_name());			
		}else if(space_type==2) {	
			mav.addObject("imageFile", spaceVO.getSpace_photo1());
			mav.addObject("filename", spaceVO.getSpace_photo_name1());
		}else if(space_type==3) {
			mav.addObject("imageFile", spaceVO.getSpace_photo2());
			mav.addObject("filename", spaceVO.getSpace_photo_name2());
		}else if(space_type==4) {
			mav.addObject("imageFile", spaceVO.getSpace_photo3());
			mav.addObject("filename", spaceVO.getSpace_photo_name3());	
		}
		return mav;
		
	
		}
	//==========부모글 좋아요 읽기==============//
	
	@RequestMapping("/space/getFav.do")
	@ResponseBody
	public Map<String,Object> getFav(SpaceFavVO fav,
			                    HttpSession session){
		logger.debug("<<공간대여본문 좋아요>> : " + fav);
		
		Map<String,Object> mapJson = 
				new HashMap<String,Object>();
		
		MemberVO user = 
			 (MemberVO)session.getAttribute("user");
		if(user==null) {
			mapJson.put("status", "noFav");
		}else {
			//로그인된 아이디 셋팅
			fav.setFmem_num(user.getMem_num());
			
			SpaceFavVO spaceFav = 
					spaceService.selectFav(fav);
			if(spaceFav!=null) {
				mapJson.put("status", "yesFav");
			}else {
				mapJson.put("status", "noFav");
			}
		}
		mapJson.put("count", 
				spaceService.selectFavCount(
							fav.getSpace_num()));
		
		return mapJson;
	}
	//좋아요 등록
		@RequestMapping("/space/writeFav.do")
		@ResponseBody
		public Map<String,Object> writeFav(
				                      SpaceFavVO fav,
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
				fav.setFmem_num(user.getMem_num());
				
				logger.debug("<<부모글 좋아요 등록>> : " + fav);
				
				SpaceFavVO spaceFav = 
						spaceService.selectFav(fav);
				if(spaceFav!=null) {
					//좋아요가 이미 등록되어있으면 삭제
					spaceService.deleteFav(
							spaceFav.getFav_num());
					
					mapJson.put("result", "success");
					mapJson.put("status", "noFav");
				}else {
					//좋아요 미등록이면 등록
					spaceService.insertFav(fav);
					
					mapJson.put("result", "success");
					mapJson.put("status", "yesFav");
				}
				
				mapJson.put("count", 
						spaceService.selectFavCount(
								fav.getSpace_num()));
			}
			return mapJson;
		}
}

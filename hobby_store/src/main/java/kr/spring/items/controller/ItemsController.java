package kr.spring.items.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import kr.spring.items.service.ItemsService;
import kr.spring.items.vo.ItemsFavVO;
import kr.spring.items.vo.ItemsReplyFavVO;
import kr.spring.items.vo.ItemsReplyVO;
import kr.spring.items.vo.ItemsVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;

@Controller
public class ItemsController {

	@Autowired
	private ItemsService itemsService;

	@ModelAttribute // 컨트롤러 접근 후 VO 초기화
	public ItemsVO initCommend() {
		return new ItemsVO();
	}

	private static final Logger logger = LoggerFactory.getLogger(ItemsController.class);

	// 1. 상품 등록//
	// 1-1 등록 폼 호출//
	@GetMapping("/items/itemsRegister.do")
	public ModelAndView form() {
		// Model = interface(객체를 인자로 사용) ModelAndView = class(내부에 객체 생성)
		ModelAndView mav = new ModelAndView();

		List<ItemsVO> items_cate = null;

		items_cate = itemsService.selectCate1();
		logger.debug("<<items_cate>> : " + items_cate);

		mav.setViewName("itemsRegister");
		mav.addObject("items_cate", items_cate);

		return mav;
	}

	// 1-2 상세 카테고리 및 아이템 등록 폼
	@ResponseBody
	@GetMapping("/items/itemsRegister2.do")
	public Map<String, Object> form(@RequestParam int cate_num) {
		// ModelAndView mav = new ModelAndView();
		List<ItemsVO> items_child = null;

		items_child = itemsService.selectCate2(cate_num);
		logger.debug("<<items_child>> :" + items_child);

		Map<String, Object> mapJson = new HashMap<String, Object>();

		mapJson.put("items_child", items_child);

		logger.debug("<<Map_items_child>> : " + mapJson);

		return mapJson;

	}

	// 1-3 아이템 등록 데이터 처리
	@PostMapping("/items/itemsRegister2.do")
	public ModelAndView submit(ItemsVO vo, BindingResult result, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView();

		logger.debug("<<상품 등록 :>>" + vo);

		// 이미지 유효성 체크 (photo1만 not null)
		if (vo.getItems_photo1().length == 0) {
			// upload1은 자바빈(vo)에 필드가 없기때문에 명시할 수 없음
			result.rejectValue("items_photo1", "required");
		}
		// 이미지 용량 체크
		if (vo.getItems_photo1().length > 5 * 1024 * 1024) {// 5MB
			result.rejectValue("photo1", "limitUploadSize", new Object[] { "5MB" }, null);
		}
		if (vo.getItems_photo2().length > 5 * 1024 * 1024) {
			result.rejectValue("photo2", "limitUploadSize", new Object[] { "5MB" }, null);
		}
		if (vo.getItems_photo3().length > 5 * 1024 * 1024) {
			result.rejectValue("photo3", "limitUploadSize", new Object[] { "5MB" }, null);
		}
		// 유효성 쳌
		if (result.hasErrors()) {
			return form();
		}
		// 회원번호 조회 후 회원번호 -> VO에 저장
		vo.setMem_num(((MemberVO) session.getAttribute("user")).getMem_num());
		vo.setMem_nickname(((MemberVO) session.getAttribute("user")).getMem_nickname());

		// 상품 등록
		itemsService.insertItems(vo);

		mav.setViewName("itemsList");
		mav.addObject("itemsVO", vo);
		mav.addObject("url", request.getContextPath() + "/items/itemsList.do");
		return mav;
	}

	// 2. 상품 목록
	@RequestMapping("/items/itemsList.do")
	public ModelAndView process(@RequestParam(value = "pageNum", defaultValue = "1") int currentPage,
			@RequestParam(value = "check", defaultValue = "1") String check, String packaging, String keyfield,
			String keyword, String cate, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 검색
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		// 상품 표시 여부
		map.put("status", 1);
		// 정렬(최신 ~ 가격)
		map.put("check", check);
		// 정렬(포장 가능 여부)
		map.put("packaging", packaging);
		// 현재 접속중인 회원의 mem_num 전달
		MemberVO user = (MemberVO) session.getAttribute("user");

		// 사이드바 카테고리
		map.put("cate", cate);
		logger.debug("<<check>> :" + (String) map.get("check"));

		logger.debug("<<cate>> :" + (String) map.get("cate"));

		// 상품 개수, 검색된 상품 개수
		int count = itemsService.selectItemsCount(map);

		// 사이드바 카테고리 적용
		// 인기, 좋아요, 조회순 적용
		// 가격 정렬 적용
		logger.debug("<<상품 목록>> :  " + count);

		PagingUtil page = new PagingUtil(keyfield, keyword, currentPage, count, 12, 4, "itemsList.do");

		List<ItemsVO> list = null;

		if (count > 0) {
			if (user != null) {
				map.put("mem_num", user.getMem_num());
			} else {
				map.put("mem_num", 0);
			}
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());

			list = itemsService.selectItemsList(map);

			logger.debug("<<상품 리스트 >> : " + list);
		}
		// cate_num, name, parent
		List<ItemsVO> items_cate = null;
		items_cate = itemsService.selectCate();

		// 좋아요를 누른 사람을 리스트로
		List<ItemsVO> Favlist = null;
		Favlist = itemsService.selectFavMem();

		// model 객체에 데이터를 담아 view에 전달
		ModelAndView mav = new ModelAndView();
		mav.setViewName("itemsList");
		mav.addObject("count", count);
		mav.addObject("items_cate", items_cate);
		mav.addObject("list", list);
		// <c:foreach 로 list를 뿌릴 때 각 list마다 현재 접속 중인 회원(user) = 좋아요를 누른 회원인지 체크, 같다면 좋아요
		// 등록,삭제 가능
		mav.addObject("user", user);
		mav.addObject("Favlist", Favlist);

		mav.addObject("page", page.getPage());
		return mav;
	}

	// 2-1 상품 수정
	// @RequestMapping("/items/modify.do")
	
	// 2-2 상품 삭제
	@RequestMapping("/items/delete.do")
	public Map<String, String> deleteItem(@RequestParam int items_num, HttpSession session) {
		MemberVO user = (MemberVO) session.getAttribute("user");

		Map<String, String> mapJson = new HashMap<String, String>();
		// mem_num = 상품 등록자
		int mem_num = itemsService.itemsOner(items_num);

		if (user == null) {
			mapJson.put("result", "logout");
		}

		// 접속한 사람 = 상품 등록자 일 때
		else if (user != null && user.getMem_num() == mem_num) {
			//몽땅 삭제 	
			itemsService.deleteReplyNum(items_num);
			
	
			mapJson.put("result", "success");
		}

		return mapJson;
	}

	// 3. 이미지 출력
	@RequestMapping("/items/imageView.do")
	public ModelAndView viewImage(@RequestParam int items_num, @RequestParam int items_type) {

		ItemsVO itemsVO = itemsService.selectItems(items_num);

		ModelAndView mav = new ModelAndView();
		// ImageView Class/ renderMergedOuputModel에서 model 사용
		mav.setViewName("imageView");

		if (items_type == 1) {
			mav.addObject("imageFile", itemsVO.getItems_photo1());
			mav.addObject("filename", itemsVO.getItems_photo_name1());
		} else if (items_type == 2) {
			mav.addObject("imageFile", itemsVO.getItems_photo2());
			mav.addObject("filename", itemsVO.getItems_photo_name2());
		} else if (items_type == 3) {
			mav.addObject("imageFile", itemsVO.getItems_photo3());
			mav.addObject("filename", itemsVO.getItems_photo_name3());
		}

		return mav;
	}

	// 4. 아이템 상세정보 출력
	@RequestMapping("/items/itemsDetail.do")
	public String detail(@RequestParam int items_num, Model model) {

		ItemsVO items = itemsService.selectItems(items_num);
		/*
		 * //별점 평균 ItemsVO itemsStar = itemsService.selectStar(items_num); //후기 개수
		 * ItemsVO itemsReply = itemsService.selectReplyCount(items_num);
		 */

		itemsService.updateHit(items_num);
		logger.debug("<<상세 보기할 상품의 정보  >> : " + items);
		model.addAttribute("items", items);
		/*
		 * model.addAttribute("itemsReply",itemsReply);
		 * model.addAttribute("itemsStar",itemsStar);
		 */

		return "itemsView";
	}

	// 5-1 아이템 좋아요 등록
	@RequestMapping("/items/itemsWriteFav.do")
	@ResponseBody
	public Map<String, Object> itemsWriteFav(ItemsFavVO fav, HttpSession session) {
		logger.debug("<<아이템 좋아요 등록1 >>: " + fav);
		Map<String, Object> mapJson = new HashMap<String, Object>();

		MemberVO user = (MemberVO) session.getAttribute("user");

		if (user == null) {
			mapJson.put("result", "logout");
		}

		else {
			// fav (mem_num)
			fav.setFmem_num(user.getMem_num());

			logger.debug("<<아이템 좋아요 등록2 >>: " + fav);

			ItemsFavVO itemsFav = itemsService.selectItemsFav(fav);
			logger.debug("<<좋아요가 있나 ? >>" + itemsFav);

			// 좋아요 삭제
			if (itemsFav != null) {
				itemsService.deleteItemsFav(itemsFav.getFav_num());
				mapJson.put("result", "success");
				mapJson.put("status", "noFav");
			}
			// 좋아요 등록
			else {
				itemsService.insertItemsFav(fav);

				mapJson.put("result", "success");
				mapJson.put("status", "yesFav");
			}

			mapJson.put("count", itemsService.selectItemsFavCount(fav.getItems_num()));
		}
		return mapJson;

	}

	// 5-2 아이템 좋아요 읽기
	@RequestMapping("/items/itemsGetFav.do")
	@ResponseBody
	public Map<String, Object> itemsGetFav(ItemsFavVO fav, HttpSession session) {
		logger.debug("<<아이템 좋아요 읽기 1>> : " + fav);

		Map<String, Object> mapJson = new HashMap<String, Object>();

		MemberVO user = (MemberVO) session.getAttribute("user");

		if (user == null) {
			mapJson.put("status", "noFav");
		} else {
			// 로그인된 아이디 셋팅
			fav.setFmem_num(user.getMem_num());

			ItemsFavVO itemsFav = itemsService.selectItemsFav(fav);
			if (itemsFav != null) {
				mapJson.put("status", "yesFav");
			} else {
				mapJson.put("status", "noFav");
			}
		}
		mapJson.put("count", itemsService.selectItemsFavCount(fav.getItems_num()));

		return mapJson;
	}

	// 6. 후기 등록
	@RequestMapping("/items/itemsReply.do")
	@ResponseBody
	public Map<String, String> writeReply(ItemsReplyVO vo, HttpSession session, HttpServletRequest request) {
		logger.debug("<<댓글 등록을 위한 VO>>" + vo);

		Map<String, String> mapJson = new HashMap<String, String>();
		MemberVO user = (MemberVO) session.getAttribute("user");
		// 추가 조건 : 접속한 회원이 해당 상품을 구매했을 경우에만 리뷰 등록이 가능할 수 있도록
		// 로그아웃 상태일 때
		if (user == null) {
			mapJson.put("result", "logout");
		} else {
			// mem_num 저장
			vo.setMem_num(user.getMem_num());

			// 리뷰, 별점 등록
			itemsService.insertReply(vo);

			mapJson.put("result", "success");
		}
		return mapJson;
	}

	// 6-2 후기 목록
	@RequestMapping("/items/itemsReplyList.do")
	@ResponseBody
	public Map<String, Object> getList(@RequestParam(value = "pageNum", defaultValue = "1") int currentPage,
			@RequestParam(value = "check", defaultValue = "1") String check, @RequestParam int items_num,
			HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("items_num", items_num);
		map.put("check", check);
		// 해당 글의 전체 후기 갯수
		int count = itemsService.selectRowCountReply(map);
		logger.debug("후기 갯수 " + count);
		logger.debug("pageNum" + currentPage);
		logger.debug("items_num" + items_num);

		MemberVO user = (MemberVO) session.getAttribute("user");
		map.put("mem_num", user.getMem_num());

		// 별점 평균
		Float itemsStar = itemsService.selectStar(items_num);
		// 후기 개수
		int itemsReply = itemsService.selectReplyCount(items_num);
		// 전체 후기 중 5점의 퍼센트
		int star5 = itemsService.select5star(items_num);
		int starall = itemsService.selectallstar(items_num);
		float star5_per = Math.round((float) star5 / starall * 100);

		PagingUtil page = new PagingUtil(currentPage, itemsReply, 4, 3, null);
		map.put("start", page.getStartRow());
		map.put("end", page.getEndRow());

		// 목록 데이터 읽기
		List<ItemsReplyVO> list = null;
		if (itemsReply > 0) {
			list = itemsService.selectListReply(map);
			logger.debug("후기 리스트" + list);
		} else {
			// list<ItemsReplyVO> 타입으로 텅텅 빈 객체를 반환
			list = Collections.emptyList();
		}
		logger.debug("댓글 목록 뿌리" + list);
		Map<String, Object> mapJson = new HashMap<String, Object>();
		// mapJson.put("count", count);
		mapJson.put("list", list);
		mapJson.put("itemsStar", itemsStar);
		mapJson.put("itemsReply", itemsReply);
		mapJson.put("star5_per", star5_per);

		// fmem_num == user/ reply_num

		if (user != null) {
			mapJson.put("user_num", user.getMem_num());
		}
		// 좋아요를 누른 사람
		// List<ItemsReplyFavVO> FavCheck = null;
		// FavCheck = itemsService.selectReplyFavCheck(null)

		return mapJson;
	}

	// 6-3 후기 수정
	@RequestMapping("items/itemsUpdateReply.do")
	@ResponseBody
	public Map<String, String> modifyReply(ItemsReplyVO reply, HttpSession session, HttpServletRequest request) {
		logger.debug("후기 수정 :" + reply);

		Map<String, String> mapJson = new HashMap<String, String>();

		MemberVO user = (MemberVO) session.getAttribute("user");
		ItemsReplyVO selectReply = itemsService.selectReply(reply.getReply_num());

		if (user == null) {
			// 로그인이 안 되어있는 경우
			mapJson.put("result", "logout");
		} else if (user != null && user.getMem_num() == selectReply.getMem_num()) {
			// 후기 수정
			itemsService.updateReply(reply);
		} else {
			mapJson.put("result", "wrongAccess");
		}

		return mapJson;
	}

	// 6-4 후기 이미지 출력
	@RequestMapping("/items/replyImageView.do")
	public ModelAndView viewReplyImage(@RequestParam int reply_num, @RequestParam int reply_type) {

		ItemsReplyVO vo = itemsService.selectReply(reply_num);

		logger.debug("<<리뷰에 관한 모든 것 >> :" + vo);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");

		if (reply_type == 1) {
			mav.addObject("imageFile", vo.getReply_photo1());
			mav.addObject("filename", vo.getReply_photo_name1());
		} else if (reply_type == 2) {
			mav.addObject("imageFile", vo.getReply_photo2());
			mav.addObject("filename", vo.getReply_photo_name2());
		} else if (reply_type == 3) {
			mav.addObject("imageFile", vo.getReply_photo3());
			mav.addObject("filename", vo.getReply_photo_name3());
		}

		return mav;
	}

	// 6-5 후기 삭제
	@RequestMapping("/items/replyDelete.do")
	@ResponseBody
	public Map<String, String> replyDelete(ItemsVO reply, HttpSession session) {
		Map<String, String> mapJson = new HashMap<String, String>();
		MemberVO user = (MemberVO) session.getAttribute("user");
		logger.debug("reply" + reply);

		ItemsReplyVO selectReply = itemsService.selectReply(reply.getReply_num());
		logger.debug("selectReply" + selectReply);
		if (user == null) {
			mapJson.put("result", "logout");
		}

		else if (user != null && user.getMem_num() == selectReply.getMem_num()) {
			// 1.reply_num을 사용하여 삭제해야 할 모든 좋아요를 검색 -> VO에 저장
			// 2.검색된 VO를 가지고 검색된 fav_num을 모두 삭제
			// 3.reply_num을 사용하여 후기 삭제
		/*
			ItemsReplyVO vo = itemsService.deleteFav(reply);
			logger.debug("삭제해야 할 모든 좋아요 "+vo); //2 itemsService.deleteAllFav(vo);
			 
			itemsService.deleteReply(reply.getReply_num());
		*/	 
			
			//후기의 좋아요 삭제
			itemsService.deleteFavByReplyNum(reply);
			//후기 삭제 
			itemsService.deleteReply(reply);

			mapJson.put("result", "success");
		} else {
			mapJson.put("result", "wrongAccess");
		}

		return mapJson;
	}

	// 7-1 후기 좋아요 등록
	@RequestMapping("/items/replyWriteFav.do")
	@ResponseBody
	public Map<String, Object> replyWriteFav(ItemsReplyFavVO rfav, HttpSession session) {
		logger.debug("<<후기 좋아요 등록 >>: " + rfav);
		Map<String, Object> mapJson = new HashMap<String, Object>();

		MemberVO user = (MemberVO) session.getAttribute("user");

		if (user == null) {
			mapJson.put("result", "logout");
		}

		else {
			// mem_num
			rfav.setFmem_num(user.getMem_num());

			logger.debug("<<후기 등록2 >>: " + rfav);

			ItemsReplyFavVO itemsFav = itemsService.selectReplyFav(rfav);
			logger.debug("<<좋아요가 있나 ? >>" + itemsFav);

			// 좋아요 삭제
			if (itemsFav != null) {
				itemsService.deleteReplyFav(itemsFav.getFav_num());
				mapJson.put("result", "success");
				mapJson.put("status", "noFav");
			}
			// 좋아요 등록
			else {
				itemsService.insertReplyFav(rfav);

				mapJson.put("result", "success");
				mapJson.put("status", "yesFav");
			}

			mapJson.put("count", itemsService.selectReplyFavCount(rfav.getReply_num()));
		}
		return mapJson;
	}

	// 7-2 후기 좋아요 읽기
	@RequestMapping("/items/replyGetFav.do")
	@ResponseBody
	public Map<String, Object> replyGetFav(ItemsReplyFavVO rfav, HttpSession session) {
		logger.debug("<<아이템 좋아요 읽기 1>> : " + rfav);

		Map<String, Object> mapJson = new HashMap<String, Object>();

		MemberVO user = (MemberVO) session.getAttribute("user");

		if (user == null) {
			mapJson.put("status", "noFav");
		} else {
			// 로그인된 아이디 셋팅
			rfav.setFmem_num(user.getMem_num());


			ItemsReplyFavVO replyFav = itemsService.selectReplyFav(rfav);
			if (replyFav != null) {
				mapJson.put("status", "yesFav");
			} else {
				mapJson.put("status", "noFav");
			}
		}
		mapJson.put("count", itemsService.selectReplyFavCount(rfav.getReply_num()));

		return mapJson;
	}
}

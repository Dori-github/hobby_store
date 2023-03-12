package kr.spring.items.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.items.vo.ItemsFavVO;
import kr.spring.items.vo.ItemsReplyFavVO;
import kr.spring.items.vo.ItemsReplyVO;
import kr.spring.items.vo.ItemsVO;

public interface ItemsService {
	//상품 등록
	public void insertItems(ItemsVO items);
	
	//상품 목록
	public List<ItemsVO> selectItemsList(Map<String, Object> map);
	//상품 카운트
	public int selectItemsCount(Map<String, Object> map); 
	//상품 선택
	public ItemsVO selectItems(Integer items_num);
	//상품 수정
	public void updateItems(ItemsVO itemsVO);
	//상품 삭제
	public void deleteItems(Integer items_num);
	
	//상품 수정 관련
	//카테고리 이름 찾기
	public int searchCateName(Integer items_num);
	//부모 카테고리 찾기 
	public ItemsVO searchCateParent(Integer cate_num);
	
	public ItemsVO selectParentCate1();
	public ItemsVO selectChildCate2(Integer cate_num);
	
	//부모 카테고리 
	public List<ItemsVO> selectCate1();
	
	public List<ItemsVO> selectCate2(Integer cate_num);
	
	public List<ItemsVO> selectCate();
	
	public ItemsFavVO selectItemsFav(ItemsFavVO fav);
	
	public void deleteItemsFav(Integer fav_num);
	
	public void insertItemsFav(ItemsFavVO fav);
	
	public int selectItemsFavCount(Integer items_num);
	
	public void updateHit(Integer items_num);
	
	public List<ItemsVO> selectFavMem();
	
	public List<ItemsReplyVO> selectListReply(Map<String, Object> map);
	
	public int selectRowCountReply(Map<String, Object> map);
	
	public void insertReply(ItemsReplyVO itemsReply);
	
	public ItemsReplyVO selectReply(Integer reply_num);
	
	public void updateReply(ItemsReplyVO itemsReply);
	public void deleteReply(ItemsVO reply);
	public void deleteReply2(Integer items_num);
	public void deleteFavByReplyNum(ItemsVO reply_num);
	public ItemsVO selectReplyByItemsNum(Integer items_num);
	

	
	public Float selectStar(Integer items_num);
	public int selectReplyCount(Integer items_num);
	
	public int select5star(Integer items_num);
	public int selectallstar(Integer items_num);
	
	//후기 좋아요
	public ItemsReplyFavVO selectReplyFav(ItemsReplyFavVO rfav);
	//후기 좋아요 삭제 
	public void deleteReplyFav(Integer fav_num);
	//후기 좋아요 등록
	public void insertReplyFav(ItemsReplyFavVO fav);
	//후기 좋아요 췍
	public int selectReplyFavCount(Integer fav_num);
	//후기 좋아요 누른 사람 	
	public List<ItemsReplyFavVO> selectReplyFavMem();
	
	public int selectReplyFavCheck(Integer reply_num);
	
	public ItemsReplyVO deleteFav(ItemsReplyVO reply);
	
	public void deleteAllFav(ItemsReplyVO fav);
	
	public int itemsOner(Integer items_num);
	
	public void deleteReplyByItemsNum(Integer items_num);

	public void deleteItemsAllFav(Integer items_num);
	
	public ItemsVO searchFav(Integer items_num);
/////////////////////////////////////////////////////////////////
	
	public List<Integer> selectReplyNum(Integer items_num);
	public void deleteReplyNum(Integer items_num);
	public void deleteReplyItems(Integer items_num);
	public void deleteReplytoFav(Integer reply_num);
	public void deleteItemsCart(Integer items_num);
	
}

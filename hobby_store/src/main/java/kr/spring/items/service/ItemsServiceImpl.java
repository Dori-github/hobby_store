package kr.spring.items.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.items.dao.ItemsMapper;
import kr.spring.items.vo.ItemsFavVO;
import kr.spring.items.vo.ItemsReplyFavVO;
import kr.spring.items.vo.ItemsReplyVO;
import kr.spring.items.vo.ItemsVO;



@Service
public class ItemsServiceImpl implements ItemsService  {
	
	@Autowired
	private ItemsMapper itemsMapper;
	
	@Override
	public void insertItems(ItemsVO items) {
		itemsMapper.insertItems(items);
		
	}

	@Override
	public int selectItemsCount(Map<String, Object> map) {
		
		return itemsMapper.selectItemsCount(map);
	}

	@Override
	public ItemsVO selectItems(Integer items_num) {
	
		return itemsMapper.selectItems(items_num);
	}

	@Override
	public void updateItems(ItemsVO itemsVO) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public List<ItemsVO> selectCate1() {
	
		return itemsMapper.selectCate1();
	}

	@Override
	public List<ItemsVO> selectCate2(Integer cate_num) {
		
		return itemsMapper.selectCate2(cate_num);
	}



	@Override
	public List<ItemsVO> selectItemsList(Map<String, Object> map) {
		
		return itemsMapper.selectItemsList(map);
	}

	@Override
	public List<ItemsVO> selectCate() {
		
		return itemsMapper.selectCate();
	}

	@Override
	public ItemsFavVO selectItemsFav(ItemsFavVO fav) {
			
		return itemsMapper.selectItemsFav(fav);
	}

	@Override
	public void deleteItemsFav(Integer fav_num) {
		itemsMapper.deleteItemsFav(fav_num);
		
	}

	@Override
	public void insertItemsFav(ItemsFavVO fav) {
		itemsMapper.insertItemsFav(fav);
		
	}

	@Override
	public int selectItemsFavCount(Integer items_num) {
	
		return itemsMapper.selectItemsFavCount(items_num);
	}

	@Override
	public void updateHit(Integer items_num) {
		itemsMapper.updateHit(items_num);
		
	}

	@Override
	public List<ItemsVO> selectFavMem() {
		
		return itemsMapper.selectFavMem();
	}

	@Override
	public List<ItemsReplyVO> selectListReply(Map<String, Object> map) {
		
		return itemsMapper.selectListReply(map);
	}

	@Override
	public int selectRowCountReply(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return itemsMapper.selectRowCountReply(map);
	}

	@Override
	public void insertReply(ItemsReplyVO itemsReply) {
		itemsMapper.insertReply(itemsReply);
		
	}

	@Override
	public ItemsReplyVO selectReply(Integer reply_num) {
		
		return itemsMapper.selectReply(reply_num);
	}

	@Override
	public void updateReply(ItemsReplyVO itemsReply) {
		itemsMapper.updateReply(itemsReply);
		
	}

	





	@Override
	public Float selectStar(Integer items_num) {

		return itemsMapper.selectStar(items_num);
	}

	@Override
	public int selectReplyCount(Integer items_num) {
		
		return itemsMapper.selectReplyCount(items_num);
	}

	@Override
	public int select5star(Integer items_num) {
		
		return itemsMapper.select5star(items_num);
	}

	@Override
	public int selectallstar(Integer items_num) {
		
		return itemsMapper.selectallstar(items_num);
	}

	@Override
	public ItemsReplyFavVO selectReplyFav(ItemsReplyFavVO rfav) {
		
		return itemsMapper.selectReplyFav(rfav);
	}

	@Override
	public void deleteReplyFav(Integer fav_num) {
		itemsMapper.deleteReplyFav(fav_num);
		
	}

	@Override
	public void insertReplyFav(ItemsReplyFavVO fav) {
		itemsMapper.insertReplyFav(fav);
		
	}

	@Override
	public int selectReplyFavCount(Integer reply_num) {
		// TODO Auto-generated method stub
		return itemsMapper.selectReplyFavCount(reply_num);
	}

	

	@Override
	public List<ItemsReplyFavVO> selectReplyFavMem() {
		// TODO Auto-generated method stub
		return itemsMapper.selectReplyFavMem();
	}

	@Override
	public int selectReplyFavCheck(Integer reply_num) {
		
		return itemsMapper.selectReplyFavCheck(reply_num);
	}

	@Override
	public ItemsReplyVO deleteFav(ItemsReplyVO reply) {
		// TODO Auto-generated method stub
		return itemsMapper.deleteFav(reply);
	}

	@Override
	public void deleteAllFav(ItemsReplyVO fav) {
		// TODO Auto-generated method stub
		itemsMapper.deleteAllFav(fav);
	}

	

	@Override
	public int itemsOner(Integer items_num) {
		
		return itemsMapper.itemsOner(items_num);
	}

	@Override
	public void deleteItems(Integer items_num) {
		itemsMapper.deleteItems(items_num);
		
	}

	@Override
	public void deleteReplyByItemsNum(Integer items_num) {
		itemsMapper.deleteReplyByItemsNum(items_num);
		
	}

	@Override
	public ItemsVO selectReplyByItemsNum(Integer items_num) {
		
		return itemsMapper.selectReplyByItemsNum(items_num);
	}

	@Override
	public void deleteFavByReplyNum(ItemsVO reply_num) {
		itemsMapper.deleteFavByReplyNum(reply_num);
		
	}

	@Override
	public void deleteItemsAllFav(Integer items_num) {
		itemsMapper.deleteItemsAllFav(items_num);
		
	}

	@Override
	public void deleteReply(ItemsVO reply) {
		itemsMapper.deleteReply(reply);
		
	}

	@Override
	public void deleteReply2(Integer items_num) {
		itemsMapper.deleteReply2(items_num);
		
	}

	@Override
	public ItemsVO searchFav(Integer items_num) {
	
		return itemsMapper.searchFav(items_num);
	}
////////////////////////////////////////////////////////////////////////
	
	public List<Integer> selectReplyNum(Integer items_num) {
		return itemsMapper.selectReplyNum(items_num);
	}
	@Override
	public void deleteReplyItems(Integer items_num) {
		itemsMapper.deleteReplyItems(items_num);
	}

	@Override
	public void deleteReplytoFav(Integer reply_num) {
		itemsMapper.deleteReplytoFav(reply_num);
	}
	@Override
	public void deleteItemsCart(Integer items_num) {
		itemsMapper.deleteItemsCart(items_num);
	}

	@Override
	public void deleteReplyNum(Integer items_num) {
		//items_num을 사용하여 reply_num을 list 형태로 저장
		//원시 타입은 null도 저장 가능
		List<Integer> reply_num = itemsMapper.selectReplyNum(items_num);
		
		for(Iterator<Integer> reply = reply_num.iterator(); reply.hasNext();) {
			while(reply.hasNext()) {
				Integer rep =  reply.next();
				if(rep != null) {
					//댓글 번호가 있을때 댓글 번호를 통한 댓글의 좋아요 삭제	
					itemsMapper.deleteReplytoFav(rep);
					//댓글 번호가 있을때 댓글 삭제 
					itemsMapper.deleteReplyItems(items_num);
					
				}
			}
		}
	}

	



}

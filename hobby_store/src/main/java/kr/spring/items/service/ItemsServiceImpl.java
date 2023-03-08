package kr.spring.items.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.items.dao.ItemsMapper;
import kr.spring.items.vo.ItemsFavVO;
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
	public void deleteItems(ItemsVO itemsVO) {
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
	public void deleteReply(Integer reply_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteReplyByItemsNum(Integer items_num) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public ItemsVO selectStar(Integer items_num) {

		return itemsMapper.selectStar(items_num);
	}

	@Override
	public ItemsVO selectReplyCount(Integer items_num) {
		
		return itemsMapper.selectReplyCount(items_num);
	}

	






}

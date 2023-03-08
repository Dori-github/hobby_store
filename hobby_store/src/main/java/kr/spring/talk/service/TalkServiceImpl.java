package kr.spring.talk.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.talk.dao.TalkMapper;
import kr.spring.talk.vo.TalkRoomVO;
import kr.spring.talk.vo.TalkVO;

@Service
@Transactional
public class TalkServiceImpl implements TalkService{

	@Autowired
	private TalkMapper talkMapper;
	
	@Override
	public List<TalkRoomVO> selectTalkRoomList(Map<String, Object> map) {
		return talkMapper.selectTalkRoomList(map);
	}

	@Override
	public TalkRoomVO selectTalkRoom(Integer talkroom_num) {
		return talkMapper.selectTalkRoom(talkroom_num);
	}

	@Override
	public void insertTalkRoom(TalkRoomVO talkRoomVO) {
		//기본키 값 생성
		talkRoomVO.setTalkroom_num(
				    talkMapper.selectTalkRoomNum());
		//채팅방 생성
		talkMapper.insertTalkRoom(talkRoomVO);
		//채팅방 멤버 생성
		for(Integer mem_num : talkRoomVO.getMembers()) {
			talkMapper.insertTalkRoomMember(
			  talkRoomVO.getTalkroom_num(), mem_num);
		}
	}

	@Override
	public void insertTalk(TalkVO talkVO) {
		talkVO.setTalk_num(talkMapper.selectTalkNum());
		//채팅메시지 등록
		talkMapper.insertTalk(talkVO);
		//채팅방 멤버가 읽지 않은 채팅 정보 저장
		for(TalkVO vo : talkMapper.selectTalkMember(
				           talkVO.getTalkroom_num())) {
			talkMapper.insertTalkRead(
					     talkVO.getTalkroom_num(),
					     talkVO.getTalk_num(),
					     vo.getMem_num());
		}
	}

	@Override
	public List<TalkVO> selectTalkDetail(Map<String,Integer> map) {
		talkMapper.deleteTalkRead(map);
		return talkMapper.selectTalkDetail(map.get("talkroom_num"));
	}

	@Override
	public List<TalkVO> selectTalkMember(Integer talkroom_num) {
		return talkMapper.selectTalkMember(talkroom_num);
	}

	@Override
	public void deleteTalkRoomMember(TalkVO talkVO) {
		//채팅방 멤버
		List<TalkVO> list = 
					talkMapper.selectTalkMember(
						   talkVO.getTalkroom_num());
		//멤버 삭제
		talkMapper.deleteTalkRoomMember(talkVO);
		
		if(list.size()>1) {
			//채팅방 나가기 메시지
			talkVO.setTalk_num(
					talkMapper.selectTalkNum());
			talkMapper.insertTalk(talkVO);
		}else {
			//채팅멤버가 1명인데 마지막 채팅멤버가 방을 나갈 경우
			//남아있는 채팅 내용을 모두 지우고 채팅방도 삭제
			talkMapper.deleteTalk(
					   talkVO.getTalkroom_num());
			talkMapper.deleteTalkRoom(
					talkVO.getTalkroom_num());
		}
	}

}

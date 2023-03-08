package kr.spring.talk.service;

import java.util.List;
import java.util.Map;

import kr.spring.talk.vo.TalkRoomVO;
import kr.spring.talk.vo.TalkVO;

public interface TalkService {
	//채팅방 목록
	public List<TalkRoomVO> selectTalkRoomList(
			                Map<String,Object> map);
	//채팅방 상세
	public TalkRoomVO selectTalkRoom(
			                 Integer talkroom_num);
	//채팅방 생성
	public void insertTalkRoom(TalkRoomVO talkRoomVO);
	//채팅 메시지 등록
	public void insertTalk(TalkVO talkVO);
	//채팅 메시지 읽기
	public List<TalkVO> selectTalkDetail(Map<String,Integer> map);
	//채팅 멤버 읽기
	public List<TalkVO> selectTalkMember(
			Integer talkroom_num);
	//채팅방 나가기
	public void deleteTalkRoomMember(TalkVO talkVO);
}







package kr.spring.websocket.ws;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class AlarmHandler extends TextWebSocketHandler {

	private Map<String, WebSocketSession> users = new ConcurrentHashMap<String, WebSocketSession>();

	/*
	 * 클라이언트가 연결되면, 클라이언트와 관련된 WebSocketSession을 users 맵에 저장한다. 이 users 맵은
	 * 채팅 메시지를 연결된 전체 클라이언트에 전달할 때 사용
	 */
	@Override
	public void afterConnectionEstablished(
			WebSocketSession session) throws Exception {
		log(session.getId() + " 연결 됨");
		users.put(session.getId(), session);
	}

	/*
	 * 클라이언트와의 연결이 종료되면, 클라이언트에 해당하는 WebSocketSession을 users 맵에서 제거한다.
	 */
	@Override
	public void afterConnectionClosed(
			WebSocketSession session, CloseStatus status) throws Exception {
		log(session.getId() + " 연결 종료됨");
		users.remove(session.getId());
	}

	/*
	 * 클라이언트가 전송한 메시지를 users 맵에 보관한 전체 WebSocketSession에 다시 전달한다. 클라이언트는
	 * 메시지를 수신하면 채팅 영역에 보여주도록 구현, 특정 클라이언트가 채팅 메시지를 서버에 보내면 전체 클라이언트는
	 * 다시 그 메시지를 받아서 화면에 보여주게 된다.
	 */
	@Override
	protected void handleTextMessage(
			WebSocketSession session, TextMessage message) throws Exception {
		log(session.getId() + "로부터 메시지 수신: " + message.getPayload());
		for (WebSocketSession s : users.values()) {
			s.sendMessage(message);
			log(s.getId() + "에 메시지 발송: " + message.getPayload());
		}
	}

	@Override
	public void handleTransportError(
			WebSocketSession session, Throwable exception) throws Exception {
		log(session.getId() + " 익셉션 발생: " + exception.getMessage());
	}

	private void log(String logmsg) {
		System.out.println(new Date() + " : " + logmsg);
	}

}

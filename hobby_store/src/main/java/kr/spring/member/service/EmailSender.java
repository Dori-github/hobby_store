package kr.spring.member.service;
  
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import kr.spring.member.vo.Email;

@Component
public class EmailSender{
	
	@Autowired
	protected JavaMailSender mailSender;

	public void sendEmail(Email email) throws Exception {

		MimeMessage msg = mailSender.createMimeMessage();
		try {
                        //송신자를 설정해도 소용없지만 없으면 오류가 발생한다
			msg.setFrom();
			msg.setSubject(email.getSubject());
			msg.setText(email.getContent());
			msg.setRecipients(MimeMessage.RecipientType.TO , InternetAddress.parse(email.getReceiver()));

		}catch(MessagingException e) {
			System.out.println("MessagingException");
			e.printStackTrace();
		}
		try {
			mailSender.send(msg);
		}catch(MailException e) {
			System.out.println("MailException발생");
			e.printStackTrace();
		}
	}
}
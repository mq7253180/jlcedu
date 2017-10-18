package com.quincy.core.service.impl;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.quincy.core.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {
	@Value("${mail.smtp}")
	private String emailSMTP;
	@Value("${mail.user}")
	private String emailUser;
	@Value("${mail.pwd}")
	private String emailPwd;

	public void send(String to, String subject, String content) throws AddressException, MessagingException {
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable","true");
		properties.put("mail.smtp.host", emailSMTP);
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override 
			protected PasswordAuthentication getPasswordAuthentication() {  
				return new PasswordAuthentication(emailUser, emailPwd);  
			}
		});
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(emailUser));
		msg.setRecipient(RecipientType.TO, new InternetAddress(to));
		msg.setSubject(subject);
		msg.setContent(content, "text/html;charset=utf-8");
	    Transport.send(msg);
	}
}
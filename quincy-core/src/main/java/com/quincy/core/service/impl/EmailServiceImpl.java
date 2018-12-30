package com.quincy.core.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

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

	public void send(String to, String subject, String content, String attachment, String fileName) throws AddressException, MessagingException, IOException {
		this.send(to, subject, content, new File(attachment), fileName);
	}

	public void send(String to, String subject, String content, File attachment, String fileName) throws AddressException, MessagingException, IOException {
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
		DataSource ds = new FileDataSource(attachment);
		DataHandler dh = new DataHandler(ds);
		MimeBodyPart attchment = new MimeBodyPart();
		attchment.setDataHandler(dh);
		attchment.setFileName(fileName);
		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setText(content);

		MimeMultipart mimeMultipart = new MimeMultipart("mixed");
		mimeMultipart.addBodyPart(attchment);
		mimeMultipart.addBodyPart(mimeBodyPart);
		
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(emailUser));
		msg.setRecipient(RecipientType.TO, new InternetAddress(to));
		msg.setSubject(subject);
//		msg.setContent(content, "text/html;charset=utf-8");
		msg.setContent(mimeMultipart);
	    Transport.send(msg);
	}
}
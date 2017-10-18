package com.quincy.core.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface EmailService {
	public void send(String to, String subject, String content) throws AddressException, MessagingException;
}

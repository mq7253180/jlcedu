package com.quincy.core.service;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface EmailService {
	public void send(String to, String subject, String content, String attachment, String fileName) throws AddressException, MessagingException, IOException;
	public void send(String to, String subject, String content, File attachment, String fileName) throws AddressException, MessagingException, IOException;
}

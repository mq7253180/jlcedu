package com.quincy.core.service.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quincy.core.entity.User;
import com.quincy.core.service.AuthorizationService;

public abstract class AuthorizationAbstract implements AuthorizationService {
	protected abstract Object getUserObject(HttpServletRequest request) throws Exception;
	protected abstract void saveVcode(HttpServletRequest request, String vcode) throws Exception;

	public User getUser(HttpServletRequest request) throws Exception {
		Object obj = this.getUserObject(request);
		return obj==null?null:(User)obj;
	}

	private final static int width = 88;
	private final static int height = 40;
	private final static int lines = 5;
	
	private final static String VCODE_COMBINATION_FROM = "23456789abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";

	public void vcode(HttpServletRequest request, HttpServletResponse response, int length) throws Exception {
		Random random = new Random();
		StringBuilder sb = new StringBuilder(length);
		for(int i=0;i<length;i++) {
			sb.append(VCODE_COMBINATION_FROM.charAt(random.nextInt(VCODE_COMBINATION_FROM.length())));
		}
		String vcode = sb.toString();
		this.saveVcode(request, vcode);
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			this.drawAsByteArray(vcode, random, out);
			out.flush();
		} finally {
			if(out!=null)
				out.close();
		}
	}

	private void drawAsByteArray(String arg, Random random, OutputStream output) throws IOException {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		Graphics g = image.getGraphics();
		g.fillRect(0, 0, width, height);
		g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
		for(int i=0;i<lines;i++) {
			g.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width), random.nextInt(height));
		}
		Font font = new Font("Times New Roman", Font.ROMAN_BASELINE, 25);
		g.setFont(font);
		g.setColor(new Color(random.nextInt(101), random.nextInt(111), random.nextInt(121)));
//		g.translate(random.nextInt(3), random.nextInt(3));
		g.drawString(arg, 13, 25);
		ImageIO.write(image, "jpg", output);
	}
}

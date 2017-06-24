package com.quincy.core.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader {
	protected Class findClass(String name) throws ClassNotFoundException {
		InputStream in = null;
		try {
//			in = new FileInputStream("E:/workspace-30trunk/Test2/bin/com/quincy/test/BBB.class");
			in = new FileInputStream("G:/java/classes2/com/quincy/test/BBB.class");
			byte[] bytes = new byte[in.available()];
			in.read(bytes);
			Class theClass = defineClass(name, bytes, 0, bytes.length);
			return theClass;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if(in!=null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}

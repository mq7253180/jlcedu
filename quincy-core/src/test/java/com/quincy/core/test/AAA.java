package com.quincy.core.test;

import java.lang.reflect.Method;

public class AAA {
	public void test() throws Exception {
		MyClassLoader classLoader = new MyClassLoader();
		Class clazz = classLoader.loadClass("com.quincy.test.BBB");
		Method method = clazz.getMethod("test");
		method.invoke(clazz.newInstance());
//		Class.forName("com.quincy.test.BBB");
	}
	public static void main(String[] args) throws Exception {
		new AAA().test();
	}
}

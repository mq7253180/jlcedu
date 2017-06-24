package com.quincy.core.helper;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FindInJar {
	public static void main(String args[]) {
        FindInJar findInJar = new FindInJar("org.mybatis.spring.transaction.SpringManagedTransaction");
        List<String> jarFiles;
		try {
			jarFiles = findInJar.findClass("E:/maven/.m2/repository", true);
			if (jarFiles.size() == 0) {
	            System.out.println("Not Found");
	        } else {
	            for (int i = 0; i < jarFiles.size(); i++) {
	                System.out.println(jarFiles.get(i));
	            }
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	public String className;
    public ArrayList<String> jarFiles = new ArrayList<String>();

    public FindInJar() {
    	
    }
    public FindInJar(String className) {
        this.className = className.replaceAll("/", ".");
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<String> findClass(String dir, boolean recurse) throws IOException {
        searchDir(dir, recurse);
        return this.jarFiles;
    }

    protected void searchDir(String dir, boolean recurse) throws IOException {
    	File d = new File(dir);
        if (!d.isDirectory()) {
            return;
        }
        File[] files = d.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (recurse && files[i].isDirectory()) {
                searchDir(files[i].getAbsolutePath(), true);
            } else {
                String filename = files[i].getAbsolutePath();
                if (filename.endsWith(".jar")||filename.endsWith(".zip")) {
                    ZipFile zip = null;
                    try {
						zip = new ZipFile(filename);
						Enumeration<? extends ZipEntry> entries = zip.entries();
	                    while (entries.hasMoreElements()) {
	                        ZipEntry entry = (ZipEntry) entries.nextElement();
	                        String thisClassName = getClassName(entry);
	                        if (thisClassName.equals(this.className) || thisClassName.equals(this.className + ".class")) {
	                            this.jarFiles.add(filename);
	                        }
	                    }
					} catch (IOException e) {
						System.err.println(filename+": "+getStackTrace(e));
					} finally {
						if(zip!=null)
							zip.close();
					}
                }
            }
        }
    }

    public List<String> getFilenames() {
        return this.jarFiles;
    }

    protected String getClassName(ZipEntry entry) {
        StringBuffer className = new StringBuffer(entry.getName().replace('/', '.'));
        return className.toString();
    }

    public static String getStackTrace(Exception e) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuffer msg = new StringBuffer("\r\n***********");
		msg.append(df.format(new Date()));
		msg.append("***********\r\n");
		msg.append(e.toString());
		StackTraceElement[] elements = e.getStackTrace();
		for(int j=0;j<elements.length;j++) {
			msg.append("\r\n\tat ");
			msg.append(elements[j].toString());
		}
		msg.append("\r\n==========================");
		return msg.toString();
	}

}
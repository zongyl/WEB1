package com.manniu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;


public class TestZip {
	
	private static final int BUFFEREDSIZE = 1024;
	
	private ZipOutputStream zipOut;
	
	public static void main(String[] args){
		try {
			new TestZip().zip();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void zip() throws Exception{
		zipOut = new ZipOutputStream(new FileOutputStream(new File("C:\\Users\\pc\\Desktop\\ckplayer\\QQQ.zip")));
		zipOut.putNextEntry(new ZipEntry("C:\\Users\\pc\\Desktop\\ckplayer\\test.html"));
		InputStream in = new FileInputStream(new File("C:\\Users\\pc\\Desktop\\ckplayer\\test.html"));
		int c;
	     byte[] by = new byte[BUFFEREDSIZE];
	     while ((c = in.read(by)) != -1)
	     {
	    	 zipOut.write(by, 0, c);
	     }
		
		zipOut.close();
	}
}

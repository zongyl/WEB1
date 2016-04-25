package com.manniu;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

public class FileUtil {

	public static String TAG = "FileUtil";
	
	static Logger LOG = Logger.getLogger(FileUtil.class);
	
	private static File FILE = null;
	private static FileOutputStream FOS = null;
	private static BufferedOutputStream BOS = null;
	private static FileInputStream FIS = null;
	private static ByteArrayOutputStream BAOS = null;
	
	/**
	 * 指定文件中, 替换字符串
	 * @param file 
	 * @param string
	 * @param replaceString
	 */
	@SuppressWarnings("unchecked")
	public static void replace(File file, String oldString, String newString){
		if(file.isDirectory()){
			File[] files = file.listFiles();
			for(File f : files){
				replace(f, oldString, newString);
			}
		}else{
			LOG.info(file.getAbsolutePath() + ", [" + oldString + "] replace [" + newString +"]");
			System.out.println(file.getAbsolutePath() + ", [" + oldString + "] replace [" + newString +"]");
			InputStream input;
			String str;
			int count = 0;
			List<String> retLines = new ArrayList<String>();
			try {
				input = new FileInputStream(file);
				List<String> lines = IOUtils.readLines(input);
				for(String line : lines){
					if(line.contains(oldString)){
						count++;
						str = line.replaceAll(oldString, newString);
					}else{
						str = line;
					}
					retLines.add(str);
				}
				FileUtils.writeLines(file, retLines);
				if(count > 0){
					System.out.printf(" [%s] contains [%d] %s %n", file.getAbsolutePath(), count, oldString);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void findRegex(String str, String regex){
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		if(matcher.find()){
			System.out.println(matcher.regionStart()+","+matcher.regionEnd());
		}else{
			System.out.println(" Not found!");
		}
		//matcher.matches();
		
	}
	
	/**
	 * 获取byte[] 
	 * @param fileName 文件名称(带路径 全名)
	 * @return
	 */
	public static byte[] getBytes(String fileName){
		byte[] buffer =  null;
		try {
			FIS = new FileInputStream(fileName);
			BAOS = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = FIS.read(b)) != -1) {
				BAOS.write(b, 0, n);
			}
			FIS.close();
			BAOS.close();
			buffer = BAOS.toByteArray();
		} catch (Exception e) {
		}
		return buffer;
	}
	
	public static void merge(String toFile, File fromFiles){
		if(fromFiles.isDirectory()){
			merge(toFile, fromFiles.listFiles());
		}
	}
	
	public static void merge(String toFile, List<File> fromFiles){
		File[] fsFiles = null;
		//fsFiles = (File[])fromFiles.toArray();
		fsFiles = new File[fromFiles.size()];
		for(int i = 0; i < fsFiles.length; i++){
			fsFiles[i] = fromFiles.get(i);
		}
		merge(toFile, fsFiles);
	}
	
	/**
	 * 合并文件
	 * @param toFile
	 * @param fromFiles
	 */
	public static void merge(String toFile, File[] fromFiles){
		try {
			FILE = new File(toFile);
			if(!FILE.getParentFile().exists()){
				FILE.getParentFile().mkdirs();
			}
			FOS = new FileOutputStream(FILE);
			BOS = new BufferedOutputStream(FOS);
			for(File file : fromFiles){
				System.out.println("reading:" + file.getAbsolutePath());
				BOS.write(getBytes(file.getAbsolutePath()));
			}
		} catch (Exception e) {
		} finally {
			if(BOS != null){
				try {
					BOS.close();
				} catch (IOException e) {
				}
			}
			if(FOS != null){
				try {
					FOS.close();
				} catch (IOException e) {
				}
			}
		}
	}
	
	/**
	 * create file
	 * @param bytes
	 * @param fileName 文件名
	 * @return
	 */
	public static File toFile(byte[] bytes, String fileName){
		try {
			FILE = new File(fileName);
			if(!FILE.getParentFile().exists()){
				FILE.getParentFile().mkdirs();
			}
			FOS = new FileOutputStream(FILE);
			BOS = new BufferedOutputStream(FOS);
			BOS.write(bytes);
		} catch (Exception e) {
		} finally {
			if(BOS != null){
				try {
					BOS.close();
				} catch (IOException e) {
				}
			}
			if(FOS != null){
				try {
					FOS.close();
				} catch (IOException e) {
				}
			}
		}
		return FILE;
	}
	
	public static void readFile(String fileName){
		InputStream in;
		List<String> lines;
		int count = 0;
		try {
			in = new FileInputStream(new File(fileName));
			lines = IOUtils.readLines(in);
			for(String line : lines){
				System.out.println(line +"           "+ count);
				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
//		List<File> files = new ArrayList<File>();
//		files.add(new File("C:\\Users\\pc\\Desktop\\111.txt"));
//		files.add(new File("C:\\Users\\pc\\Desktop\\222.txt"));
//		merge("C:\\Users\\pc\\Desktop\\merge.txt", files);
		
		//merge("C:\\Users\\pc\\Desktop\\merge.txt", new File("C:\\Users\\pc\\Desktop\\111"));
		
	//	replace(new File("C:\\Users\\pc\\Desktop\\replace.txt"), "2", "88");
		
		//E:\manniu\manniu\src

		/*replace(new File("E:\\manniu\\manniu\\src"),
				"com.manniu.manniu.en.R",
				"com.manniu.manniu.R");
		
		replace(new File("E:\\manniu\\manniu\\src\\com\\manniu\\manniu"), 
				"package com.manniu.manniu.en;",
				"package com.manniu.manniu;");
		*/
		//replace(new File("D:\\android_workspace\\volley\\src\\com\\android\\volley"), "Executor", "Executor");
		
		//readFile("F:\\BaiduYunDownload\\zaw.txt");
		
		System.exit(0);
	}
}

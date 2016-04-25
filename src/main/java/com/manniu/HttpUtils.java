package com.manniu;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

/**
 * HTTP UTILS
 * @author long
 *
 */
public class HttpUtils {

	//static Logger log = Logger.getLogger(HttpUtils.class);

	/**
	 * 2string
	 * @param url
	 * @return 
	 * @throws Exception
	 */
	public static String getString(String url) throws Exception{
		HttpClient client = new HttpClient();
		client.getHttpConnectionManager().getParams().setConnectionTimeout(12000);
		client.getHttpConnectionManager().getParams().setSoTimeout(12000);
		GetMethod method = new GetMethod(url);
		client.executeMethod(method);
		String htmlString = method.getResponseBodyAsString();
		method.releaseConnection();
		return htmlString;
	}

	/**
	 * 通过sessionId 和 url 获取页面信息
	 * @param sessionId
	 * @param uri
	 * @return
	 * @throws Exception
	 */
	public static String getString(String sessionId, String uri) throws Exception{
		URLConnection conn = null;
		URL url = new URL(uri);
		conn = url.openConnection();
		conn.setRequestProperty("Cookie", sessionId);
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)");
		conn.connect();
		
		InputStream is = conn.getInputStream();  
        BufferedReader br = new BufferedReader(new InputStreamReader(is));  
        String response = "";  
        String readLine = null;  
        while((readLine =br.readLine()) != null){  
            response = response + readLine+"\n";  
        }  
        is.close();  
        br.close();  
		return response;
		}
	
	/**
	 * toFile
	 * @param url
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	/*public static void toFile(String url, String filePath) throws Exception{
		HttpClient client = new HttpClient();
		client.getHttpConnectionManager().getParams().setConnectionTimeout(12000);
		client.getHttpConnectionManager().getParams().setSoTimeout(12000);
		GetMethod method = new GetMethod(url);
		client.executeMethod(method);
		InputStream in = method.getResponseBodyAsStream();
		FileUtils.stream2file(in, filePath);
		method.releaseConnection();
	}*/
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

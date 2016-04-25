package com.manniu;

import java.util.Set;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Test {

	static int i = 0;
	
	public static void main(String[] args){
		System.out.println("..........");
		
		Jedis jedis = new Jedis("localhost");
		
		/*Set<String> keys = jedis.keys("*");
		for(String key : keys){
			System.out.println(".........." + key +"::"+jedis.get(key));
		}*/
		
		//抓图
		//Set<String> ks = jedis.keys("screen_*");
		
		//设备升级包
		Set<String> ks = jedis.keys("S_*");
		for(String k : ks){
			//jedis.del(k);
			//System.out.println("---------" + k +"::"+jedis.get(k));
			parseJSON(jedis.get(k));
		}
		
//		String value = jedis.get("qq");
//		System.out.println("value:" + value);
		
	}
	
	public static void parseJSON(String text){
	//	System.out.println("jsonString:" + text);
		JSONObject obj = JSON.parseObject(text);
		if(obj.containsKey("version")){
			//System.out.println("version:" + obj.getString("version"));
			update(obj.getString("sid"), obj.getString("version"));			
		}
	}
	
	/**
	 * 获取升级信息
	 * @param deviceId
	 * @param version
	 */
	public static void update(String deviceId, String version){
		
		/*if("Q04hAQEAbDAwMTIzNzNjAAAAAAAA".equals(deviceId)){
			System.out.println("这是 zhiluofcw设备!");
		}
		if("Q04hAQEAbDAwMjg3YjI5AAAAAAAA".equals(deviceId)){
			System.out.println("这是 蛮牛zhinuotest001设备!");
		}*/
		
		String httpret = "";
		try {
			httpret = HttpUtils.getString("http://120.26.56.240:6084/query_upd?sid="+deviceId+"&ver="+version);
			if(httpret.startsWith("http")){
				//System.out.println(i + "设备" + deviceId + "有升级包!\n" + httpret);
				/*if("Q04hAQEAbDAwMTIzN2E4AAAAAAAA".equals(deviceId)){
					System.out.println("这是 蛮牛001237a8设备!");
				}
				if("Q04hAQEAbDAwMjkzZjc4AAAAAAAA".equals(deviceId)){
					System.out.println("这是 蛮牛211设备!");
				}*/
				
				if("Q04hAQEAbDAwMjk0MjBiAAAAAAAA".equals(deviceId)){//217 不升级
					System.out.println(i + " 217设备" + deviceId + "有升级包!\n" + httpret);
					//String ret = HttpUtils.getString("http://www.mny9.com/device/update?sid="+deviceId+"&userId=Q04hAQ0AADE0Mzg2OTQ1NTI0NTBo");
					//System.out.println("update return:" + ret);
				}else{
					System.out.println(i + "  设备" + deviceId + "有升级包!\n" + httpret);
					//升级
//					String ret = HttpUtils.getString("http://www.mny9.com/device/update?sid="+deviceId+"&userId=Q04hAQ0AADE0Mzg2OTQ1NTI0NTBo");
//					System.out.println("update return:" + ret);
				}
				
				if("Q04hAQEAbDAwMjkzZjBkAAAAAAAA".equals(deviceId)){
					//System.out.println(i + "218设备" + deviceId + "有升级包!\n" + httpret);
//					String ret = HttpUtils.getString("http://www.mny9.com/device/update?sid="+deviceId+"&userId=Q04hAQ0AADE0Mzg2OTQ1NTI0NTBo");
//					System.out.println("update return:" + ret);
				}
				
//				if("Q04hAQEAbDAwMDEwYmY4AAAAAAAA".equals(deviceId)){
//					System.out.println(i + "设备" + deviceId + "有升级包!\n" + httpret);
//					//String ret = HttpUtils.getString("http://www.mny9.com/device/update?sid="+deviceId+"&userId=Q04hAQ0AADE0Mzg2OTQ1NTI0NTBo");
//					//System.out.println("update return:" + ret);
//				}
//				if("Q04hAQEAbDAwMDEwYmUxAAAAAAAA".equals(deviceId)){
//					System.out.println(i + "设备" + deviceId + "有升级包!\n" + httpret);
//					//String ret = HttpUtils.getString("http://www.mny9.com/device/update?sid="+deviceId+"&userId=Q04hAQ0AADE0Mzg2OTQ1NTI0NTBo");
//					//System.out.println("update return:" + ret);
//				}
//				if("Q04hAQEAbDAwMDEwYmMzAAAAAAAA".equals(deviceId)){
//					System.out.println(i + "设备" + deviceId + "有升级包!\n" + httpret);
//					//String ret = HttpUtils.getString("http://www.mny9.com/device/update?sid="+deviceId+"&userId=Q04hAQ0AADE0Mzg2OTQ1NTI0NTBo");
//					//System.out.println("update return:" + ret);
//				}
//				if("Q04hAQEAbDAwMDEwYjNkAAAAAAAA".equals(deviceId)){
//					System.out.println(i + "设备" + deviceId + "有升级包!\n" + httpret);
//					//String ret = HttpUtils.getString("http://www.mny9.com/device/update?sid="+deviceId+"&userId=Q04hAQ0AADE0Mzg2OTQ1NTI0NTBo");
//					//System.out.println("update return:" + ret);
//				}
//				if("Q04hAQEAbDAwMjk0MTk1AAAAAAAA".equals(deviceId)){
//					System.out.println(i + "设备" + deviceId + "有升级包!\n" + httpret);
//					//String ret = HttpUtils.getString("http://www.mny9.com/device/update?sid="+deviceId+"&userId=Q04hAQ0AADE0Mzg2OTQ1NTI0NTBo");
//					//System.out.println("update return:" + ret);
//				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("httpret:"+httpret);
		i++;
	}
	
	
	
}

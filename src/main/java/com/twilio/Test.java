package com.twilio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.instance.ShortCode;
import com.twilio.sdk.resource.list.ShortCodeList;

public class Test {

	public static final String ACCOUNT_SID = "ACdae6456d60b257e7df50fbfbfe7fb19c";
	public static final String AUTH_TOKEN = "d6209c9f3e8d478e229fb3c4a2d7af26";
	
	public static void main(String[] args){

		String toNubmer = "15104971518";
		String text = "current Times:" + System.currentTimeMillis();
		
		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("To", toNubmer));
		params.add(new BasicNameValuePair("Body", text));
		params.add(new BasicNameValuePair("From", "16503979760"));
		
		ShortCode code = client.getAccount().getShortCode("SC6b20cb705c1e8f00210049b20b70fce2"/*ACCOUNT_SID*/);
		System.out.println("ShortCode:"+code.toString());
		
		 Map<String, String> maps = new HashMap<String, String>();
		 maps.put("ShortCode", "898");
		
		ShortCodeList list; 
		list = client.getAccount().getShortCodes(maps);
		
		System.out.println("total:"+list.toString());
		
		for(Iterator<ShortCode> iter = list.iterator(); iter.hasNext();){
			System.out.println("ShortCode:" + iter.next().getShortCode());
		}
		
		/*MessageFactory messageFactory = client.getAccount().getMessageFactory();
		try {
			Message message =  messageFactory.create(params);
			System.out.println("sid:"+message.getSid());
		} catch (TwilioRestException e) {
			e.printStackTrace();
		}*/
	}
	
}

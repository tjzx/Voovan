package org.voovan.test.http;


import org.voovan.http.client.HttpClient;
import org.voovan.http.message.Response;
import org.voovan.http.message.packet.Part;
import org.voovan.tools.log.Logger;

import junit.framework.TestCase;

public class HttpClientUnit extends TestCase {

	public HttpClientUnit(String name){
		super(name);
	}

	public void testGetHeader() {
		HttpClient httpClient = new HttpClient("http://127.0.0.1:28080");
		httpClient.putParameters("name", "测试");
		assertEquals(httpClient.getHeader().get("Host"),"127.0.0.1");
	}

	public void testParameters() {
		HttpClient httpClient = new HttpClient("http://127.0.0.1:28080");
		httpClient.putParameters("name", "测试");
		assertEquals(httpClient.getParameters().get("name"), "测试");
	}
	
	public void testGet() throws Exception{
		HttpClient getClient = new HttpClient("http://webserver.voovan.com","GB2312");
		Response response  = getClient.setMethod("GET")
			.putParameters("name", "测试")
			.putParameters("age", "32").connect();
		Logger.simple(response.body().getBodyString("GB2312"));
		assertTrue(response.protocol().getStatus()!=500);
	}

	public void testPost() throws Exception {
	HttpClient postClient = new HttpClient("http://webserver.voovan.com","GB2312");
	Response response = postClient.setMethod("POST") 
		.putParameters("name", "测试")
		.putParameters("age", "32").connect();
	Logger.simple(response.body().getBodyString("GB2312"));
	assertTrue(response.protocol().getStatus() != 500);
	}
	
	public void testMultiPart() throws Exception {
		HttpClient mpClient = new HttpClient("http://webserver.voovan.com");
		Response response = mpClient.setMethod("POST")
			.addPart(new Part("name","测试","GB2312"))
			.addPart(new Part("age","23","GB2312")).connect();
		
		Logger.simple(response.body().getBodyString("GB2312"));
		assertTrue(response.protocol().getStatus()!=500);
	}
}

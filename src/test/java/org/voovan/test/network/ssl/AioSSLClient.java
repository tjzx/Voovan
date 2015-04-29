package org.voovan.test.network.ssl;

import org.voovan.network.SSLManager;
import org.voovan.network.aio.AioSocket;
import org.voovan.network.filter.StringFilter;
import org.voovan.test.network.ClientHandlerTest;

public class AioSSLClient {
	
	public static void main(String[] args) throws Exception {
		SSLManager sslManager = new SSLManager("SSL");
		sslManager.loadCertificate("/Users/helyho/Work/Java/MyPlatform/src/test/java/org/hocate/test/network/ssl/ssl_ks", "passStr","123123");
		
		AioSocket socket = new AioSocket("127.0.0.1",2031,500);
		socket.setSSLManager(sslManager);
		socket.handler(new ClientHandlerTest());
		socket.filterChain().add(new StringFilter());
		socket.start();
	}
}
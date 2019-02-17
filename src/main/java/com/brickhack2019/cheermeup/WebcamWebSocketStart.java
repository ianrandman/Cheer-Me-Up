package com.brickhack2019.cheermeup;

import com.github.sarxos.webcam.ds.buildin.WebcamDefaultDriver;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.sarxos.webcam.Webcam;


/**
 * This example demonstrates how webcam capture IP camera driver can be used with conjunction with
 * websockets to feed data to the web application frontend.
 * 
 * @author Bartosz Firyn (sarxos)
 */
public class WebcamWebSocketStart {

	public WebcamWebSocketStart() {
		try {
			startServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static {
		//Webcam.setDriver(new IpCamDriver(new IpCamStorage("src/main/resources/cameras.xml")));
		Webcam.setDriver(new WebcamDefaultDriver());
	}

	private static final Logger LOG = LoggerFactory.getLogger(WebcamWebSocketStart.class);

	public static void main(String[] args) throws Exception {
		startServer();
	}

	public static void startServer() throws Exception {
		for (String name : WebcamCache.getWebcamNames()) {
			LOG.info("Will read webcam {}", name);
		}

		Server server = new Server(8123);
		WebSocketHandler wsHandler = new WebSocketHandler() {

			@Override
			public void configure(WebSocketServletFactory factory) {
				factory.register(WebcamWebSocketHandler.class);
			}
		};

		server.setHandler(wsHandler);
		server.start();
		server.join();
	}
}

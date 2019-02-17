package com.brickhack2019.cheermeup;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.StatusCode;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;

/**
 * Basic Echo Client Socket
 */
@WebSocket(maxTextMessageSize = 64 * 1024)
public class SimpleEchoSocket
{
    private final CountDownLatch closeLatch;
    @SuppressWarnings("unused")
    private Session session;

    private int imgCount = 0;
    private static final int IMG_SKIP = 10;

    public SimpleEchoSocket()
    {
        this.closeLatch = new CountDownLatch(1);
    }

    public boolean awaitClose(int duration, TimeUnit unit) throws InterruptedException
    {
        return this.closeLatch.await(duration,unit);
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason)
    {
        System.out.printf("Connection closed: %d - %s%n",statusCode,reason);
        this.session = null;
        this.closeLatch.countDown(); // trigger latch
    }

    @OnWebSocketConnect
    public void onConnect(Session session)
    {
        System.out.printf("Got connect: %s%n",session);
        this.session = session;
    }

    @OnWebSocketMessage
    public void onMessage(String msg) {
        final JSONObject data = new JSONObject(msg);
        if (data.get("type").toString().equals("image") && imgCount == IMG_SKIP) {
            String imgData = data.get("image").toString();
            // create a buffered image
            BufferedImage image = null;
            byte[] imageByte;

            BASE64Decoder decoder = new BASE64Decoder();
            try {
                imageByte = decoder.decodeBuffer(imgData);
                ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                image = ImageIO.read(bis);
                bis.close();

                FaceDetectApp faceDetectApp = new FaceDetectApp();
                String emotions = faceDetectApp.detectFaces(image);
                System.out.println(emotions + '\n');
            } catch (Exception e) {
                e.printStackTrace();
            }
            imgCount = 0;
        } else {
            imgCount++;
        }
    }
}
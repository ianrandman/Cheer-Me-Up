package com.brickhack2019.cheermeup;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

/**
 * Example of a simple Echo Client.
 */
public class SimpleEchoClient
{

    public static void main(String[] args) {
        wsConnect();
    }

    public static void wsConnect() {
        String destUri = "ws://127.0.0.1:8123/";

        WebSocketClient client = new WebSocketClient();
        SimpleEchoSocket socket = new SimpleEchoSocket();
        try
        {
            client.start();

            URI echoUri = new URI(destUri);
            ClientUpgradeRequest request = new ClientUpgradeRequest();
            client.connect(socket,echoUri,request);
            System.out.printf("Connecting to : %s%n",echoUri);

            // wait for closed socket connection.
            socket.awaitClose(2,TimeUnit.SECONDS);
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }
        finally
        {
            try
            {
                //client.stop();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
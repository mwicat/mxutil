/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mwicat.mobile.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.io.SocketConnection;



/**
 *
 * @author mwicat
 */
public class NetUtil {
	
    public static void send(String url, String msg) throws IOException {
        SocketConnection connection = (SocketConnection) Connector.open(url);
        OutputStream output = connection.openOutputStream();
        output.write(msg.getBytes());
        output.flush();
        output.close();
        connection.close();
    }

    public static boolean gotNetwork() {
        boolean valid = true;
        try {
            HttpConnection connection = (HttpConnection) ConnectorHelper.open(
                    "http://www.google.pl/",
                    Connector.READ,
                    15000);
            InputStream inp = connection.openInputStream();
            inp.close();
            connection.close();
        } catch (IOException ex) {
            valid = false;
            //#debug info
System.out.println("checkNetwork exception " + ex);
        } catch (SecurityException se) {
            valid = false;
            //#debug info
System.out.println("checkNetwork exception " + se);
        }
        return valid;
    }

    public static HttpConnection doGet(String url) throws IOException {
        HttpConnection conn = (HttpConnection) Connector.open(url);
        //HTTP Request
        conn.setRequestMethod(HttpConnection.GET);
        conn.setRequestProperty("Content-Type", "//text plain");
        conn.setRequestProperty("Connection", "close");
        return conn;
    }

    
}

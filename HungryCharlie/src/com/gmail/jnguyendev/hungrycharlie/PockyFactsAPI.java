package com.gmail.jnguyendev.hungrycharlie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.StrictMode;

public class PockyFactsAPI {

	private static final int SERVERPORT = 80;
	private static final String SERVER_IP = "android.lf.lc";
    private static final String HTTP_REQUEST = "GET / HTTP/1.0\r\nHost: android.lf.lc\r\n\r\n";
	
	public static String getFact() {
		String fact = null;
		InetAddress serverAddr;
		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy); 

            serverAddr = InetAddress.getByName(SERVER_IP);
            Socket s = new Socket(serverAddr, SERVERPORT);
            s.setSoTimeout(1000);

            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            output.write(HTTP_REQUEST);
            output.flush();

            BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String line = input.readLine();
            while ((line = input.readLine()) != null) { fact = line; }
            s.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fact;
	}
	
}

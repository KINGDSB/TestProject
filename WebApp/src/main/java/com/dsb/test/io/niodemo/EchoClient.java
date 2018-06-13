package com.dsb.test.io.niodemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class EchoClient {

	private static Socket clientSocket;

	private static int port = 10000;

	public static void main(String[] args) {

		PrintWriter writer = null;
		BufferedReader reader = null;
		try {
			clientSocket = new Socket();
			clientSocket.connect(new InetSocketAddress("localhost", port));

			writer = new PrintWriter(clientSocket.getOutputStream(), true);
			writer.println("hello !");

			reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
//			StringBuffer msg = new StringBuffer();
//			String line = null;
//			while ((line = reader.readLine()) != null) {
//				System.out.println(line);
//				msg.append(line);
//			}
//			System.out.println("from server : " + msg.toString());
			
			char[] charArr = new char[1024];
			reader.read(charArr);
			String str = new String(charArr);
			System.out.println("from server : \n" + str);


		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (writer != null) {
				writer.close();
			}
			if (clientSocket != null) {
				try {
					clientSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}

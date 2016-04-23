package Server;

import java.io.*;
import java.net.*;

public class Server {

	ServerSocket serverSocket;
	Socket clientSocket;
	InputStream is;
	InputStreamReader isreader;
	String inputLine, outputLine;
	
	public Server() {
		
		
		try {
			this.serverSocket = new ServerSocket(81);
			this.clientSocket = serverSocket.accept();
			
			System.out.println("Connection established...");
			
			this.is = clientSocket.getInputStream();
			isreader = new InputStreamReader(is);
			
			BufferedReader in = new BufferedReader(isreader);
			
			while ((inputLine = in.readLine()) != null) {
					System.out.println(inputLine);
				
				if (inputLine.equals("Bye.")) {
			            break;
				}
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	

}

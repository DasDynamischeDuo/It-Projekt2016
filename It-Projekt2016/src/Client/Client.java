package Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class Client {

	
	Socket clientSocket;
	PrintWriter out;
	Scanner s;
	String input;
	
	public Client() {
		
		this.s = new Scanner(System.in);
		
		initClient();		
		write();

	}

	private void write() {

		input = "";
		
		while(!(input).equals("Bye.")) {
			
			System.out.print("Message: ");
			input = s.nextLine();
			out.write(input);
			out.write("\n");
			out.flush();
			
		}
		
	}

	
	private void initClient() {
		try {
			this.clientSocket = new Socket("localhost", 81);
			System.out.println("Connection established...");
			
			out = new PrintWriter(clientSocket.getOutputStream(), true);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	

}

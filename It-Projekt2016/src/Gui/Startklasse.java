package Gui;

import java.io.IOException;
import java.net.URISyntaxException;

import Twitter.TwitterLogin;

public class Startklasse {


	public static void main(String[] args) {
		
		Gui gui = null;
		TwitterLogin twitterLogin = new TwitterLogin();
		try {
			gui = new Gui();
			gui.setVisible(true);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}

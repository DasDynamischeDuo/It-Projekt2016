package Gui;

import java.io.IOException;
import java.net.URISyntaxException;

public class Startklasse {


	public static void main(String[] args) {
		
		Gui gui = null;
		try {
			gui = new Gui();
			gui.setVisible(true);
			gui.pack();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}

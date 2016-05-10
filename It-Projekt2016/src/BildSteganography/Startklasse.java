package BildSteganography;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Startklasse {

	public static void main(String[] args) {
		
		BildSteganography bildSteganography = new BildSteganography();
		URI uri = null;
		try {
			uri = BildSteganography.class.getResource("/Pictures/CuteChick.jpg").toURI();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File img = new File(uri);
		try {
			int[] pixel = bildSteganography.readImage(img);
			bildSteganography.hideText("test", img);
			String text = bildSteganography.extractText(img);
			System.out.println(text);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}
	
	
}

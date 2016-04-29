package BildSteganography;

import java.io.IOException;
import java.net.URISyntaxException;

public class Startklasse {

	public static void main(String[] args) {
		
		BildSteganography bildSteganography = null;

		bildSteganography = new BildSteganography();
		
		try {
			byte[] data = bildSteganography.readImage();
			bildSteganography.writeImage(data);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}

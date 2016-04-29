package BildSteganography;

import java.io.IOException;
import java.net.URISyntaxException;

public class Startklasse {

	public static void main(String[] args) {
		
		BildSteganography bildSteganography = null;

		try {
			bildSteganography = new BildSteganography();
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		int[] pixel = bildSteganography.readImage();
		try {
			bildSteganography.writeImage(pixel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
}

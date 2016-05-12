package BildSteganography;

import javax.crypto.*;

import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.imageio.ImageIO;

import Gui.Gui;

public class BildSteganography {

	private Gui gui;
	
	public BildSteganography(Gui gui) throws NoSuchAlgorithmException, NoSuchPaddingException {

		this.gui = gui;
	}

	/**
	 * Gibt die den Farbwert jedes Pixels eines Bildes in einem Array zurueck.
	 * 
	 * @param img
	 * @return int[]
	 * @throws IOException
	 */
	
	public int[] readImage(File img) throws IOException {

		BufferedImage bufferedImage = ImageIO.read(img);
		int w = bufferedImage.getWidth();
		int h = bufferedImage.getHeight();

		int[] pixel = bufferedImage.getRGB(0, 0, w, h, null, 0, w);

		return pixel;

	}

	
	/**
	 * Versteckt einen Text in einem Bild und gibt dieses als BufferedImage zurueck. 
	 * 
	 * @param text
	 * @param img
	 * @return BufferedImage
	 * @throws IOException
	 */
	
	public BufferedImage hideText(String text, File img) throws IOException {

		BufferedImage bufferedImage = ImageIO.read(img);
		int w = bufferedImage.getWidth();
		int h = bufferedImage.getHeight();


		if (w*h -16 <= text.length() * 8) {
			gui.error("Text ist zu lang");
			return null;
		}
		
		int stelle = 0;

		int[] textBits = new int[text.length() + 8];
		textBits = bitsInText(text);

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (textBits.length <= stelle) {
					break;
				}

				bufferedImage.setRGB(j, i, setLastBit(bufferedImage.getRGB(j, i), textBits[stelle]));
				stelle++;

			}
		}

		return bufferedImage;

	}

	
	/**
	 * Lies den versteckten Text aus einem Bild aus und gibt ihn zurueck.
	 * 
	 * @param img
	 * @return String
	 * @throws IOException
	 */
	
	
	public String extractText(File img) throws IOException {

		BufferedImage bufferedImage = ImageIO.read(img);
		int w = bufferedImage.getWidth();
		int h = bufferedImage.getHeight();
		String bits = "";
		int anzEinser = 0;
		
		
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				
				if (anzEinser >= 16) {
					break;
				}

				bits += Integer.toBinaryString(bufferedImage.getRGB(j, i)).charAt(31);							
				
				if (Integer.toBinaryString(bufferedImage.getRGB(j, i)).charAt(31) == '1') {
					anzEinser++;
				} else {
					anzEinser = 0;
				}
			}
		}
		

		int[] textBits = new int[bits.length()];

		for (int i = 0; i < bits.length(); i++) {
			textBits[i] = Integer.parseInt(bits.charAt(i) + "");
		}

		
		
		String text = bitsToText(textBits);

		text = text.substring(0, text.length() - 3);

		return text;
	}

	
	/**
	 * Die Methode setzt oder loescht das least significant bit eines Integer-Werts. 
	 * 
	 * @param value
	 * @param bit
	 * @return int
	 */
	
	
	
	public int setLastBit(int value, int bit) {

		if (value % 2 == 1 | value % 2 == -1) {
			value--;
		}

		value = value + bit;

		return value;

	}

	
	/**
	 * Die Methode wandelt Text in binaeren Asciicode um und gibt diese als Array zurueck. Zusaetzlich fuellt sie die letzten 16 Bits mit Einsern auf.
	 * 
	 * @param text
	 * @return int[]
	 */
	
	
	public int[] bitsInText(String text) {

		int[] bitsInText = new int[text.length() * 8 + 24];
		String chr;
		for (int i = 0; i < text.length(); i++) {
			chr = Integer.toBinaryString((int) text.charAt(i));
			
			if (chr.length() > 8) {
				gui.error("Der Text muss aus Asciizeichen bestehen");
				return null;
			}
			
			while (chr.length() < 8) {
				chr = "0" +chr;
			}
			
			for (int j = 0; j < 8; j++) {
				bitsInText[(i * 8) + j] = Integer.parseInt(chr.charAt(j) + "");
			}
		}
		

		for (int i = text.length() * 8; i < bitsInText.length - 14; i++) {
			bitsInText[i] = 0;
		}
		
		
		for (int i = text.length() * 8 + 8; i < bitsInText.length; i++) {
			bitsInText[i] = 1;
		}
		
		
		return bitsInText;

	}
	
	
	/**
	 * Wandelt ein Array mit binaeren Asciicode in einen Text um.
	 * 
	 * @param bits
	 * @return String
	 */
	

	public String bitsToText(int[] bits) {

		String chr = "";
		String text = "";
		for (int i = 0; i < bits.length / 8; i++) {
			for (int j = 0; j < 8; j++) {
				chr += bits[(i * 8) + j];
			}
			text += (char) Integer.parseInt(chr, 2);
			chr = "";
		}

		return text;

	}

}

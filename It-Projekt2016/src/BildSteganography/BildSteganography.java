package BildSteganography;

import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BildSteganography {

	public BildSteganography() {

	}

	public int[] readImage(File img) throws IOException {

		BufferedImage bufferedImage = ImageIO.read(img);
		int w = bufferedImage.getWidth();
		int h = bufferedImage.getHeight();

		int[] pixel = bufferedImage.getRGB(0, 0, w, h, null, 0, w);

		return pixel;

	}

	public void hideText(String text, File img) throws IOException {

		BufferedImage bufferedImage = ImageIO.read(img);
		int w = bufferedImage.getWidth();
		int h = bufferedImage.getHeight();

		int stelle = 0;

		int[] textBits = new int[text.length() + 7];
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

		ImageIO.write(bufferedImage, "png", img);

	}

	public String extractText(File img) throws IOException {

		BufferedImage bufferedImage = ImageIO.read(img);
		int w = bufferedImage.getWidth();
		int h = bufferedImage.getHeight();
		String chr = "";
		int anzEinser = 0;

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {

				if (anzEinser >= 6) {
					break;
				}

				chr += Integer.toBinaryString(bufferedImage.getRGB(j, i)).charAt(31);

				if (Integer.toBinaryString(bufferedImage.getRGB(j, i)).charAt(31) == '1') {
					anzEinser++;
				} else {
					anzEinser = 0;
				}
			}
		}

		int[] textBits = new int[chr.length()];

		for (int i = 0; i < chr.length(); i++) {
			textBits[i] = Integer.parseInt(chr.charAt(i) + "");
		}

		String text = bitsToText(textBits);

		text = text.substring(0, text.length() - 1);

		return text;
	}

	public int setLastBit(int value, int bit) {

		if (value % 2 == 1 | value % 2 == -1) {
			value--;
		}

		value = value + bit;

		return value;

	}

	public int[] bitsInText(String text) {

		int[] bitsInText = new int[text.length() * 8 + 7];
		String chr;
		for (int i = 0; i < text.length(); i++) {
			chr = Integer.toBinaryString((int) text.charAt(i));
			if (chr.length() < 7) {
				chr = "0" +chr;
			}
			for (int j = 0; j < 7; j++) {
				bitsInText[(i * 7) + j] = Integer.parseInt(chr.charAt(j) + "");
			}
		}

		for (int i = text.length() * 8; i < bitsInText.length; i++) {
			bitsInText[i] = 1;
		}

		for (int i = 0; i < bitsInText.length; i++) {
		}
		
		return bitsInText;

	}

	public String bitsToText(int[] bits) {

		String chr = "";
		String text = "";
		for (int i = 0; i < bits.length / 7; i++) {
			for (int j = 0; j < 7; j++) {
				chr += bits[(i * 7) + j];
			}
			text += (char) Integer.parseInt(chr, 2);
			chr = "";
		}

		return text;

	}

}

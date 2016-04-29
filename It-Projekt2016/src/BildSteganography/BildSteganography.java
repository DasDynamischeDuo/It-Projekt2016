package BildSteganography;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.sun.javafx.tk.Toolkit;

public class BildSteganography {

	
	private BufferedImage bufferedImage;
	private int w;
	private int h;
	
	
	
	public BildSteganography() throws URISyntaxException, IOException  {

		URI uri;
		uri = BildSteganography.class.getResource("/Pictures/CuteChick.jpg").toURI();
		File img = new File(uri);
		bufferedImage = ImageIO.read(img);
		w = bufferedImage.getWidth();
		h = bufferedImage.getHeight();
		
	}

	public int[] readImage() {
		
		 int[] pixel = bufferedImage.getRGB(0, 0, w, h, null, 0, h);
		
		return pixel;
		

	}
	
	public void writeImage(int[] pixel) throws IOException, URISyntaxException {
		
		URI uri = BildSteganography.class.getResource("/Pictures").toURI();
		File outImg = new File(uri.toString(), "/catNew.jpg");
		BufferedImage bufferedImageOut = new BufferedImage(this.bufferedImage.getHeight(), this.bufferedImage.getWidth(), this.bufferedImage.getType());
		
		System.out.println(pixel.length);
		System.out.println(w * h);
		
		bufferedImageOut.setRGB(0, 0, w, h, pixel, 0, h);
			
		
		ImageIO.write(bufferedImageOut, "jpg", outImg);
		
	}

}

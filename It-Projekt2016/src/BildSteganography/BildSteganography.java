package BildSteganography;

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

import Test.Audiostream;

public class BildSteganography {


	public BildSteganography()  {

		
	}

	public byte[] readImage() throws URISyntaxException, IOException {

		URI uri;
		uri = BildSteganography.class.getResource("/Pictures/cat.jpg").toURI();
		File img = new File(uri);
		BufferedImage bufferedImage = ImageIO.read(img);
		WritableRaster raster = bufferedImage .getRaster();
		DataBufferByte data = (DataBufferByte) raster.getDataBuffer();
		return data.getData();

	}
	
	public void writeImage(byte[] dataByte) throws IOException, URISyntaxException {
		
		URI uri = BildSteganography.class.getResource("/Pictures").toURI();
		File outImg = new File(uri.toString(), "/catNew.jpg");
		
		ByteArrayInputStream bin = new ByteArrayInputStream(dataByte);
		BufferedImage img = ImageIO.read(bin);
		System.out.println(img);
		ImageIO.write(img, "jpg", outImg);
		
		
	}

}

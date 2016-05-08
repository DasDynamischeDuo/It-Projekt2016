package Twitter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveImageFromUrl {
	
	
	private static String destinationFile = "images/image.jpg";
	
	
	

	public SaveImageFromUrl() {
		
	}

	public static void saveImage(String imageUrl) throws IOException {
		
		URL url = new URL(imageUrl);
		InputStream is = url.openStream();
		OutputStream os = new FileOutputStream(destinationFile);

		byte[] b = new byte[2048];
		int length;

		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}

		is.close();
		os.close();
	}
	
	public static void deleteImage() throws MalformedURLException{
		
		Path p = Paths.get(destinationFile);
		
		try {
			Files.delete(p);
		} catch (Exception e) {
			
		}
		
		System.out.println("deleted");
	}
	
	public static void setDestinationFile (String destination){
		
		destinationFile = destination;
		
	}

}


package Twitter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class SaveImageFromUrl {
	
	private String imageUrl = "http://pbs.twimg.com/media/ChYaSWoWwAIGjms.jpg";
	private static String destinationFile = "images/image.jpg";
	private static File index = new File("/images");
	
	

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
		
		String[]entries = index.list();
		for(String s: entries){
		    File currentFile = new File(index.getPath(),s);
		    currentFile.delete();
		}
		
		
	}

}
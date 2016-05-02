package Twitter;

import java.io.IOException;

public class StartKlasse {

	public static void main(String[] args) {
		
		TwitterLogin tl = new TwitterLogin();
		SaveImageFromUrl s = new SaveImageFromUrl();
		
		String str;
		
		str = tl.getTweetandMediafromHash();
		
		System.out.println("Hier: " +str);
		
		try {
			s.saveImage(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}

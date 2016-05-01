package Twitter;

import java.io.IOException;

public class StartKlasse {

	public static void main(String[] args) {
		
		TwitterLogin tl = new TwitterLogin();
		SaveImageFromUrl s = new SaveImageFromUrl();
		
		String Str = new String();
		
		
		try {
			s.saveImage(Str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}

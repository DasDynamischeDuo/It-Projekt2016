package StenographyChat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

import javax.sound.sampled.*;

import sun.audio.AudioStream;


/**
 * Spielt ein Sample ab
 * 
 * @author Emanuel
 * @version 0.1
 */

public class Audiostream implements Runnable{

	private Mixer mixer;
	private Clip clip;
	private DataLine.Info dataInfo;
	private Mixer.Info[] mixerInfos;
	private URL soundURL;
	private Thread thread;

	/**
	 * Spielt einen Clap-Ton ab
	 * 
	 * @author Emanuel
	 */

	public Audiostream() {

		thread = new Thread(this);
		
		this.mixerInfos = AudioSystem.getMixerInfo();
		this.mixer = AudioSystem.getMixer(mixerInfos[0]);
		this.dataInfo = new DataLine.Info(Clip.class, null);

		try {
			clip = (Clip) mixer.getLine(this.dataInfo);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}

		
		/*
		 for (int i = 0; i < mixerInfos.length; i++) {
			 
			 System.out.println(mixerInfos[i] +"----" +mixerInfos[i].getDescription()); 
		 
		 }		 
		*/
		 
		 thread.run();
		 
	}



	public void run() {
		
		soundURL = Audiostream.class.getResource("/Sounds/waterfall1.wav");
		String soundURLStr = soundURL.toString();
		soundURLStr = soundURLStr.replace("file:", "");
		
		
		try {
			File wavFile = new File(soundURLStr);
			InputStream inputStream = AudioSystem.getAudioInputStream(wavFile);
			
			FileReader fr = new FileReader(wavFile);
			BufferedReader br = new BufferedReader(fr);

			
			
			String line;
			while ((line = br.readLine()) != null) {
				
				for (int i = 0; i < line.length(); i++) {
					System.out.print((int) line.charAt(i));
				}
				
				System.out.println();
				
			};
			
			
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(wavFile);
					
			
			
			System.out.println(audioInputStream.getFormat());
			
			clip.open(audioInputStream);
			
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		clip.start();
		
		try {
			thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
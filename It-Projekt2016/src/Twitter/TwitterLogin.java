package Twitter;

import java.io.File;

import Gui.Gui;

import twitter4j.*;
import twitter4j.auth.Authorization;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.conf.*;

public class TwitterLogin {

	private  String ConsumerKey = null;
	private  String ConsumerSecret = null;
	private  String AccesToken = null;
	private  String AccesTokenSecret = null;

	private ConfigurationBuilder cb;
	private Gui gui;
	
	private  TwitterFactory tf;
	private  Twitter twitter;

	public TwitterLogin(Gui gui) {

		reConfiguration();
		this.gui = gui;

	}

	public void reConfiguration() {
		cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey(ConsumerKey)
								.setOAuthConsumerSecret(ConsumerSecret)
								.setOAuthAccessToken(AccesToken)
								.setOAuthAccessTokenSecret(AccesTokenSecret);
		tf = new TwitterFactory(cb.build());
		twitter = tf.getInstance();
	}

	public void tweetStatus(String tweet) {
		tf.getSingleton();
		Status status = null;
		try {
			status = twitter.updateStatus(tweet);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
	
	public void tweetImage(File photo){
		
		tf.getSingleton();
				
		StatusUpdate status = new StatusUpdate("Neues Bild");
		status.setMedia(photo); // set the image to be uploaded here.
		try {
			twitter.updateStatus(status);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	public String getTweetandMediafromHash(String Hash) {

		String returnvalue = null;

		tf.getSingleton();
		Query query = new Query(Hash);
		QueryResult result = null;
		try {
			result = twitter.search(query);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		
		for (Status status : result.getTweets()) {
			for (MediaEntity mediaEntity : status.getMediaEntities()) {
				returnvalue = mediaEntity.getMediaURL();
			}
		}
		
		
		return returnvalue;

	}
	
	
	public boolean checkLogin() {
		
		boolean isValid = true;
		
			try {
				twitter.getId();
			} catch (IllegalStateException e) {
				isValid = false;
				e.printStackTrace();
			} catch (TwitterException e) {
				isValid = false;
				e.printStackTrace();
			}

		return isValid;
		
	}
	
	

	public void setConsumerKey(String consumerKey) {
		ConsumerKey = consumerKey;
	}

	public void setConsumerSecret(String consumerSecret) {
		ConsumerSecret = consumerSecret;
	}

	public void setAccesToken(String accesToken) {
		AccesToken = accesToken;
	}

	public void setAccesTokenSecret(String accesTokenSecret) {
		AccesTokenSecret = accesTokenSecret;
	}

}
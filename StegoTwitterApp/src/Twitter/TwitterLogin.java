package Twitter;

import java.awt.List;
import java.io.File;
import java.util.Vector;

import Gui.Gui;

import twitter4j.*;
import twitter4j.auth.Authorization;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.conf.*;

public class TwitterLogin {

	private ConfigurationBuilder cb;
	private Gui gui;
	private LoginData loginData;

	private TwitterFactory tf;
	private Twitter twitter;

	public TwitterLogin(Gui gui, LoginData loginData) {

		this.gui = gui;
		this.loginData = loginData;
		reConfiguration();
		
	}

	public void reConfiguration() {
		
		cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey(loginData.getConsumerKey())
								.setOAuthConsumerSecret(loginData.getConsumerSecret())
								.setOAuthAccessToken(loginData.getAccesToken())
								.setOAuthAccessTokenSecret(loginData.getAccesTokenSecret());
		
		tf = new TwitterFactory(cb.build());
		twitter = tf.getInstance();
	}

	public void setLoginData(LoginData loginData) {
		this.loginData = loginData;
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

	public void tweetImage(File photo, String hash) {

		tf.getSingleton();

		StatusUpdate status = new StatusUpdate("#" +hash);
		status.setMedia(photo); // set the image to be uploaded here.
		try {
			twitter.updateStatus(status);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getTweetandMediafromHash(String hash, String user) {

		String returnvalue = null;
		
		try {
			ResponseList<Status> timeline = twitter.getUserTimeline(twitter.showUser(user).getId());		
			
			for (Status status : timeline) {
				for (HashtagEntity hashtags : status.getHashtagEntities()) {
					if (hashtags.getText().equals(hash)) {
						for (MediaEntity mediaEntity : status.getMediaEntities()) {
							returnvalue = mediaEntity.getMediaURL();
						}
					}
				}
			}
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
		return returnvalue;

	}

	public boolean checkLogin() {

		boolean isValid = true;

		try {
			twitter.getId();
		} catch (IllegalStateException e) {
			gui.error("Login Fehlgeschlagen");
			isValid = false;
			e.printStackTrace();
		} catch (TwitterException e) {
			gui.error("Login Fehlgeschlagen");
			isValid = false;
			e.printStackTrace();
		}

		return isValid;

	}

}
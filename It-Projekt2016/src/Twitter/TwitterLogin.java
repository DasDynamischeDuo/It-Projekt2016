package Twitter;

import java.io.File;

import twitter4j.*;
import twitter4j.conf.*;

public class TwitterLogin {

	private static String ConsumerKey = null;
	private static String ConsumerSecret = null;
	private static String AccesToken = null;
	private static String AccesTokenSecret = null;

	private static TwitterFactory tf;
	private static Twitter twitter;

	public TwitterLogin() {

		reConfiguration();

	}

	public void reConfiguration() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey(ConsumerKey)
								.setOAuthConsumerSecret(ConsumerSecret)
								.setOAuthAccessToken(AccesToken)
								.setOAuthAccessTokenSecret(AccesTokenSecret);
		tf = new TwitterFactory(cb.build());
		twitter = tf.getInstance();
	}

	public static void tweetStatus(String tweet) {
		tf.getSingleton();
		Status status = null;
		try {
			status = twitter.updateStatus(tweet);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		System.out.println("Successfully updated the status to [" + status.getText() + "].");
	}
	
	public static void tweetImage(File photo){
		
		tf.getSingleton();
				
		StatusUpdate status = new StatusUpdate("Neues Bild");
		status.setMedia(photo); // set the image to be uploaded here.
		try {
			twitter.updateStatus(status);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Successfully updated the image" +status);
		
		
	}

	public static String getTweetandMediafromHash(String Hash) {

		String returnvalue = null;
		Hash = "meinpersoehnlicherhash";

		tf.getSingleton();
		Query query = new Query(Hash);
		QueryResult result = null;
		try {
			result = twitter.search(query);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		
		for (Status status : result.getTweets()) {
			System.out.println("@" + status.getUser().getScreenName() + " : " + status.getText());
			for (MediaEntity mediaEntity : status.getMediaEntities()) {
				System.out.println(mediaEntity.getType() + ": " + mediaEntity.getMediaURL());
				returnvalue = mediaEntity.getMediaURL();
			}
		}
		
		
		return returnvalue;

	}

	public static void setConsumerKey(String consumerKey) {
		ConsumerKey = consumerKey;
	}

	public static void setConsumerSecret(String consumerSecret) {
		ConsumerSecret = consumerSecret;
	}

	public static void setAccesToken(String accesToken) {
		AccesToken = accesToken;
	}

	public static void setAccesTokenSecret(String accesTokenSecret) {
		AccesTokenSecret = accesTokenSecret;
	}

}
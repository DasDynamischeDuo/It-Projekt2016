package Twitter;

import javax.naming.directory.SearchControls;

import twitter4j.*;
import twitter4j.conf.*;

public class TwitterLogin {
	
	static String ConsumerKey = "fryDhzHTK7dOomLWgQkFx2AyQ";
	static String ConsumerSecret = "xbc0ad7wivaxs0QS1V2T1INlqxXWHg1aSu73IyoeB5qgHI5ylG";
	static String AccesToken = "2387628402-oVhA0NDOD4ZwWVBeI07Uo4SEFly4aqhKdS1TCze";
	static String AccesTokenSecret = "pF7o4Z1fkEn4BHALI3MzonbxKffnLDNtHpBDFLpCc0iQ9";
	
	public static void main(String[]args){
		
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey(ConsumerKey)
		  .setOAuthConsumerSecret(ConsumerSecret)
		  .setOAuthAccessToken(AccesToken)
		  .setOAuthAccessTokenSecret(AccesTokenSecret);
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		
		//tweetStatus(tf, twitter);
		getTweetandMediafromHash(tf, twitter);
		
		
		
		
		
		
		
		
	}

	private static void tweetStatus(TwitterFactory tf, Twitter twitter) {
		tf.getSingleton();
		    Status status = null;
			try {
				status = twitter.updateStatus("Test");
			} catch (TwitterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    System.out.println("Successfully updated the status to [" + status.getText() + "].");
	}
	
	private static void getTweetandMediafromHash(TwitterFactory tf, Twitter twitter) {

		tf.getSingleton();
		Query query = new Query("meinpersoehnlicherHash");
		QueryResult result = null;
		try {
			result = twitter.search(query);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Status status : result.getTweets()) {
			System.out.println("@" + status.getUser().getScreenName() + " : " + status.getText());
			for (MediaEntity mediaEntity : status.getMediaEntities()) {
		        System.out.println(mediaEntity.getType() + ": " + mediaEntity.getMediaURL());
		    }
		}
		

	}
	

	
	
	
	
	
	
	
	
	
}

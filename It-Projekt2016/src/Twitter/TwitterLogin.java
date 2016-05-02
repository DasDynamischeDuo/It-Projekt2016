package Twitter;

import twitter4j.*;
import twitter4j.conf.*;

public class TwitterLogin {

	static String ConsumerKey = "fryDhzHTK7dOomLWgQkFx2AyQ";
	static String ConsumerSecret = "xbc0ad7wivaxs0QS1V2T1INlqxXWHg1aSu73IyoeB5qgHI5ylG";
	static String AccesToken = "2387628402-oVhA0NDOD4ZwWVBeI07Uo4SEFly4aqhKdS1TCze";
	static String AccesTokenSecret = "pF7o4Z1fkEn4BHALI3MzonbxKffnLDNtHpBDFLpCc0iQ9";

	static TwitterFactory tf;
	static Twitter twitter;

	public TwitterLogin() {

		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
			.setOAuthConsumerKey(ConsumerKey)
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

	public static String getTweetandMediafromHash() {

		String returnvalue = null;

		tf.getSingleton();
		Query query = new Query("meinpersoehnlicherHash");
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

}

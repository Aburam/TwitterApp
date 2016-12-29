package twitterApp;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterConnect {

	static final String CONSUMER_KEY = "LUhYEhmcheKaM6XOMeRMzRUM9";

	static final String CONSUMER_SECRET= "W0aMWaWD0ZiSE8HeiFAsFCMHhEl7OWQJ5vqInYHZD5IZrFpe73";

	static final String ACCESS_TOKEN="1049921594-sdPtIgZA4Ag9vnSXK3GlDWTCsmsiUJIa2zGnPNa";

	static final String ACCESS_TOKEN_SECRET="W6cqO2H31n7zxEv6jNsHzCp9uYQZV237WDYfpWnI2xcnX";


	public static Twitter getTwitter(){
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey(TwitterConnect.CONSUMER_KEY);
		cb.setOAuthConsumerSecret(TwitterConnect.CONSUMER_SECRET);
		cb.setOAuthAccessToken(TwitterConnect.ACCESS_TOKEN);
		cb.setOAuthAccessTokenSecret(TwitterConnect.ACCESS_TOKEN_SECRET);
		TwitterFactory tt = new TwitterFactory(cb.build());
		Twitter tweet = tt.getInstance();
		return tweet;
	}
	
	public static TwitterStream getStreamTwitter(){
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey(TwitterConnect.CONSUMER_KEY);
		cb.setOAuthConsumerSecret(TwitterConnect.CONSUMER_SECRET);
		cb.setOAuthAccessToken(TwitterConnect.ACCESS_TOKEN);
		cb.setOAuthAccessTokenSecret(TwitterConnect.ACCESS_TOKEN_SECRET);
		TwitterStreamFactory tt = new TwitterStreamFactory(cb.build());
		TwitterStream tweet = tt.getInstance();
		return tweet;
	}
	



}

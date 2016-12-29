package twitterApp;

import javax.swing.JTextPane;

import twitter4j.FilterQuery;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;

import com.google.gson.Gson;

public class OperationTwitter{
	
	private Twitter myTwitter;
	private TwitterStream myTwitterStream;

	private JTextPane txtTwitterUser;
	private CollectView p;

	private String filter;
	
	private OperationTwitter opTw = this;

	
	public OperationTwitter(JTextPane txtTwitterUser, String filter, CollectView parent){
		this.myTwitter = TwitterConnect.getTwitter();
		this.myTwitterStream = TwitterConnect.getStreamTwitter();
		this.txtTwitterUser = txtTwitterUser;
		this.filter = filter;
		this.p = parent;


	}

	public void sendTweet(String msg){

		try {
			myTwitter.updateStatus(msg);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void receiveTweet(String user){

		Query query = new Query("from:"+user);
        try {
            QueryResult result = myTwitter.search(query);
            for (Status tweet : result.getTweets()) {
                System.out.println("text : " + tweet.getText());
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }
	}
	
	public void readStatus(){

		//TwitterStream stream = TwitterConnect.getStreamTwitter();

		StatusListener listener = new StatusListener(){
			
			public void onException(Exception arg0) {
				// TODO Auto-generated method stub
				
			}

			public void onDeletionNotice(StatusDeletionNotice arg0) {
				// TODO Auto-generated method stub
				
			}

			public void onScrubGeo(long arg0, long arg1) {
				// TODO Auto-generated method stub
				
			}

			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub
				
			}

			public void onStatus(Status status) {
				if(CollectView.collect){
					opTw.insertInCouchDB(status);
				}
			}

			public void onTrackLimitationNotice(int arg0) {
				// TODO Auto-generated method stub
				
			}
		};
		FilterQuery query = new FilterQuery();
		String[] keywords= { this.filter };
		query.track(keywords);
		myTwitterStream.addListener(listener);
		myTwitterStream.filter(query);
	}
	
	public void insertInCouchDB(Status status){
			Gson gson = new Gson();
	        String json = gson.toJson( status );
			Thread t = new ThreadCouchdb(json, txtTwitterUser, p);
			t.start();
	}
	
}

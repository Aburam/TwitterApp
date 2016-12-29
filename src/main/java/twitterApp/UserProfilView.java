package twitterApp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import twitter4j.JSONException;
import twitter4j.JSONObject;

public class UserProfilView extends JFrame{
	private static final long serialVersionUID = -2027268548220138693L;
	private JPanel contentPane;
	private JPanel southPanel;
	
	private JFrame parent;
	private JSONObject jsonUser;

	public UserProfilView(JFrame p, JSONObject jsonObjectUser) {
		super("Twitter collect");

		try {
			this.jsonUser = jsonObjectUser.getJSONObject("user");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.parent = p;

		setResizable(false);
		setSize(600, 400);

		setAlwaysOnTop(true);

		contentPane = new JPanel();
		southPanel =new JPanel();
		
		
		setContentPane(contentPane);

		contentPane.setLayout(new GridLayout(2,1));
		contentPane.setBackground(new Color(0, 153, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		southPanel.setLayout(new GridLayout(2,3));
		southPanel.setBackground(new Color(255, 255, 255));
		southPanel.setBorder(new EmptyBorder(5, 5, 5, 5));


		//Partie BackgroundImage
		setImageBackground();

		//Partie ImageProfile
		setImageProfile();
		
		//Partie NameProfile
		setNameProfile();
		
		//Partie UserName
		setUserName();
		
		//PartieNbTweets
		setNbTweets();
		
		//PartieNbFriends
		setNbFriends();
		
		//PartieNbAbonnes
		setNbFollowers();
		
		
		
		contentPane.add(southPanel);
		
		//Partie
		
		ImageIcon ic = new ImageIcon("src/main/resources/twitter.gif");
		setIconImage(ic.getImage());
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - getWidth() / 2, dim.height / 2
				- getHeight() / 2);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				parent.setVisible(true);
			}
		});

		setVisible(true);
	}

	private void setImageProfile() {
		URL urlImageProfile;
		try {
			String urlProfile = getImageProfile();
			if(urlProfile !=null){
				urlImageProfile = new URL(urlProfile);
				Image imageBackground = java.awt.Toolkit.getDefaultToolkit().createImage(urlImageProfile);
				ImageIcon imageIconBackground = new ImageIcon(imageBackground);
				JLabel labelImageProfile = new JLabel();
			    labelImageProfile.setIcon(imageIconBackground);
				southPanel.add(labelImageProfile);
			}
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}


	private void setImageBackground() {
		URL urlImageBackground;
		try {
			String urlBackground = getImageBackground();
			if(urlBackground !=null){
				urlImageBackground = new URL(urlBackground);
				Image imageBackground = java.awt.Toolkit.getDefaultToolkit().createImage(urlImageBackground);
				ImageIcon imageIconBackground = new ImageIcon(imageBackground);
				JLabel labelBackground = new JLabel();
			    labelBackground.setIcon(imageIconBackground);
				contentPane.add(labelBackground);
			}
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	

	private void setNameProfile() {
		String nameProfile = getNameProfile();
		JLabel labelName = new JLabel();
	    labelName.setText(nameProfile);
	    labelName.setForeground(new Color(0, 153, 255));
	    southPanel.add(labelName);
	}
	
	
	private void setUserName() {
		String userName = getUserName();
		JLabel labelUser = new JLabel();
	    labelUser.setText("@" + userName);
	    labelUser.setForeground(new Color(0, 153, 255));
	    southPanel.add(labelUser);
	}
	
	



	private void setNbTweets() {
		String nbTweets = getNbTweets();
		JLabel labelNbTweets = new JLabel();
	    labelNbTweets.setText("TWEETS :" + nbTweets);
	    labelNbTweets.setForeground(new Color(0, 153, 255));
	    southPanel.add(labelNbTweets);
	}
	
	private void setNbFriends() {
		String nbFriends = getNbFriends();
		JLabel labelNbFriends = new JLabel();
	    labelNbFriends.setText("Abonnements :" + nbFriends);
	    labelNbFriends.setForeground(new Color(0, 153, 255));
	    southPanel.add(labelNbFriends);
	}

	private void setNbFollowers() {
		String nbFollowers = getNbFollowers();
		JLabel labelNbFollowers = new JLabel();
	    labelNbFollowers.setText("Abonnés :" + nbFollowers);
	    labelNbFollowers.setForeground(new Color(0, 153, 255));
	    southPanel.add(labelNbFollowers);
	}

	

	private String getImageProfile() {
		String urlImage = null;
		if(!jsonUser.isNull("profileImageUrl")){
			try {
				urlImage = jsonUser.getString("profileImageUrl");
			} catch (JSONException e) {		
				e.printStackTrace();
			}
		}
		return urlImage;
	}


	private String getImageBackground(){
		String urlBackground = null;
		if(!jsonUser.isNull("profileBannerImageUrl")){
			try {
				urlBackground = jsonUser.getString("profileBannerImageUrl");
			} catch (JSONException e) {		
				e.printStackTrace();
			}
		}
		return urlBackground;
	}
	
	private String getNameProfile() {
		String nameProfile = null;
		if(!jsonUser.isNull("name")){
			try {
				nameProfile = jsonUser.getString("name");
			} catch (JSONException e) {		
				e.printStackTrace();
			}
		}
		return nameProfile;
	}

	private String getUserName() {
		String userName = null;
		if(!jsonUser.isNull("screenName")){
			try {
				userName = jsonUser.getString("screenName");
			} catch (JSONException e) {		
				e.printStackTrace();
			}
		}
		return userName;
	}
	
	private String getNbTweets() {
		String nbTweets = null;
		if(!jsonUser.isNull("statusesCount")){
			try {
				nbTweets = jsonUser.getString("statusesCount");
			} catch (JSONException e) {		
				e.printStackTrace();
			}
		}
		return nbTweets;
	}
	
	private String getNbFriends() {
		String nbFriends = null;
		if(!jsonUser.isNull("friendsCount")){
			try {
				nbFriends = jsonUser.getString("friendsCount");
			} catch (JSONException e) {		
				e.printStackTrace();
			}
		}
		return nbFriends;
	}
	
	private String getNbFollowers() {
		String nbFollowers = null;
		if(!jsonUser.isNull("followersCount")){
			try {
				nbFollowers = jsonUser.getString("followersCount");
			} catch (JSONException e) {		
				e.printStackTrace();
			}
		}
		return nbFollowers;
	}



}

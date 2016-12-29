package twitterApp;

import java.awt.event.MouseListener;

import twitter4j.JSONObject;

public class MyMouseListener implements MouseListener{
	
	private JSONObject jsonUser;
	private CollectView parentFrame;
	
	public MyMouseListener(CollectView p, JSONObject jsonObject){
		parentFrame = p;
		jsonUser = jsonObject;
	}

	public void mouseClicked(java.awt.event.MouseEvent e) {
		new UserProfilView(parentFrame, jsonUser);
		parentFrame.setVisible(false);

		
	}

	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

package twitterApp;

import java.awt.Color;
import java.awt.Cursor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import twitter4j.JSONException;
import twitter4j.JSONObject;

public class ThreadCouchdb extends Thread {
	private String json;
	private JTextPane txt;
	private CollectView parentFrame;

	public ThreadCouchdb(String json, JTextPane txt, CollectView p) {
		this.json = json;
		this.txt = txt;
		this.parentFrame = p;
	}

	public void run() {
		try {
			HttpURLConnection connection = (HttpURLConnection) (new URL(
					"http://localhost:5984/twitter_aburam").openConnection());
			
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");

			// envoi de la requête
			OutputStream out = connection.getOutputStream();
			OutputStreamWriter wout = new OutputStreamWriter(out, "UTF-8");
			wout.write(json);
			wout.flush();
			out.close();

			// lecture de la réponse
			InputStream in = connection.getInputStream();
			int car;
			String reponse = "";
			while ((car = in.read()) != -1) {
				reponse += (char) car;
			}
			in.close();
			out.close();
			connection.disconnect();
			System.out.println(reponse);
			if (reponse.startsWith("{\"ok\":true,")) {
				StyledDocument doc = (StyledDocument) txt.getDocument();
				try {
					
					JSONObject jsonUser = new JSONObject(json);
					String name = jsonUser.getJSONObject("user").getString("name");
					JLabel label = new JLabel();
					
					label.setText(name);
					label.addMouseListener(new MyMouseListener(parentFrame, jsonUser));
					label.setFont(txt.getFont());
					label.setForeground(new Color(0, 153, 255));
					
					label.setCursor(new Cursor(Cursor.HAND_CURSOR));
					txt.insertComponent(label);
					
					try {
						doc.insertString(0, "\n", null);
					} catch (BadLocationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
			}
		} catch (IOException e) {
			System.out.println("Json mal formé");
		}
	}
}
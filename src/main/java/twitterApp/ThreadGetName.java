package twitterApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import twitter4j.JSONArray;
import twitter4j.JSONException;
import twitter4j.JSONObject;

public class ThreadGetName extends Thread {
	private JTextPane txt;

	public ThreadGetName(JTextPane txt) {
		this.txt = txt;
	}

	public void run() {

		try {
			txt.setText("Requête à la base de données en cours...");
			HttpURLConnection con = (HttpURLConnection) (new URL(
					"http://localhost:5984/twitter_aburam/_design/application/_view/getName?group=true"))
					.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");

			BufferedReader in2 = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in2.readLine()) != null) {
				response.append(inputLine);
			}
			in2.close();
			String reponse = response.toString();

			txt.setText("");
			try {
				JSONObject obj = new JSONObject(reponse);
				JSONArray res = obj.getJSONArray("rows");
				Map<String,Integer> m = new HashMap<String, Integer>();

				for (int i = 0; i < res.length(); i++) {
						m.put(res.getJSONObject(i).getString("key"), Integer.valueOf(res.getJSONObject(i).getString("value")));
				}
				
				Comparator<String> comparator = new ValueComparator(m);
				TreeMap<String, Integer> result = new TreeMap<String, Integer>(comparator);
				result.putAll(m);
				
				for (Map.Entry<String, Integer> entree : result.entrySet()) {
					StyledDocument doc = (StyledDocument) txt.getDocument();
					try {
						doc.insertString(doc.getLength(), entree.getValue()
								+ " : "
								+ entree.getKey() + "\n",
								null);
					} catch (BadLocationException e) {
						e.printStackTrace();
					}					
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Json mal formé");
		}
	}
	
	class ValueComparator implements Comparator<String>{
		 
		Map<String, Integer> map = new HashMap<String, Integer>();
	 
		public ValueComparator(Map<String, Integer> map){
			this.map.putAll(map);
		}
	 
		public int compare(String s1, String s2) {
			if(map.get(s1) > map.get(s2)){
				return -1;
			}else if (map.get(s1) < map.get(s2)){
				return 1;
			}
			else{
				return s1.compareToIgnoreCase(s2);
			}
		}
	}
}
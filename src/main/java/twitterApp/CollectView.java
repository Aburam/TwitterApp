package twitterApp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class CollectView extends JFrame{
	
	private static final long serialVersionUID = -2027268548220138693L;
	private JPanel contentPanel;

	private String hashtag;
	private App parent;

	private JTextPane txtTwitterUser;

	static boolean collect;
	private CollectView c = this;


	public CollectView(App p, String h) {
	
		super("Twitter collect");

		this.parent = p;
		this.hashtag = h;

		setResizable(false);
		setSize(600, 400);

		setAlwaysOnTop(true);
		setBounds(100, 100, 600, 400);

		contentPanel = new JPanel();
		contentPanel.setBackground(new Color(0, 153, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblHashtag = new JLabel("Collecte des tweets (nom des twitteurs)");
		lblHashtag.setForeground(new Color(255, 255, 255));
		lblHashtag.setBounds(170, 12, 290, 15);
		contentPanel.add(lblHashtag);

		txtTwitterUser = new JTextPane();
		txtTwitterUser.setEditable(false);
		txtTwitterUser.setForeground(new Color(0, 153, 255));
		txtTwitterUser.setBounds(12, 39, 576, 262);
		JScrollPane sp = new JScrollPane(txtTwitterUser);
	    contentPanel.add( sp );
		
		contentPanel.add(txtTwitterUser);
		

		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				collect = false;
			}
		});
		
		
		btnStop.setForeground(new Color(0, 153, 255));
		btnStop.setBackground(new Color(255, 255, 255));
		btnStop.setBounds(235, 335, 117, 25);
		contentPanel.add(btnStop);

		ImageIcon ic = new ImageIcon("src/main/resources/twitter.gif");
		setIconImage(ic.getImage());
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - getWidth() / 2, dim.height / 2
				- getHeight() / 2);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				collect = false;
				parent.setVisible(true);
			}
		});

		setVisible(true);

		collect = true;
		OperationTwitter myOpps = new OperationTwitter(txtTwitterUser, hashtag, c);
		myOpps.readStatus();
	}
}

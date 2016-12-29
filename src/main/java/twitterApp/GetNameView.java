package twitterApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class GetNameView extends JFrame {

	private static final long serialVersionUID = -2027268548220138693L;
	private JPanel contentPane;
	private JFrame parent;

	private JTextPane txtpnHello;

	public GetNameView(JFrame p) {
		super("Twitter collect");

		this.parent = p;

		setResizable(false);
		setSize(600, 400);

		setAlwaysOnTop(true);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());

		JLabel lblHashtag = new JLabel("Consultations des tweetteurs (nombre de tweets)");
		lblHashtag.setHorizontalAlignment(SwingConstants.CENTER);
		lblHashtag.setForeground(new Color(255, 255, 255));
		contentPane.add(lblHashtag,BorderLayout.NORTH);

		txtpnHello = new JTextPane();
		txtpnHello.setEditable(false);
		txtpnHello.setForeground(new Color(0, 153, 255));
		
		JScrollPane jsp = new JScrollPane(txtpnHello);
		contentPane.add(jsp,BorderLayout.CENTER);

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
		
		Thread t = new ThreadGetName(txtpnHello);
		t.start();
	}
}
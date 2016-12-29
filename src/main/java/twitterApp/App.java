package twitterApp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class App extends JFrame {

	private static final long serialVersionUID = -5665308658894049865L;
	private JPanel contentPane;
	private JTextField textField;
	private App app = this;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (UnsupportedLookAndFeelException e) {
		}
		new App();
	}

	public App() {

		super("Twitter collect");
		setResizable(false);
		setSize(600, 300);

		setAlwaysOnTop(true);
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnCco = new JButton("Collecter");
		btnCco.setForeground(new Color(0, 153, 255));
		btnCco.setBackground(Color.WHITE);
		btnCco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textField.getText().length() != 0) {
					new CollectView(app, textField.getText());
					setVisible(false);
				}
			}
		});
		btnCco.setBounds(232, 200, 117, 25);
		contentPane.add(btnCco);

		JLabel lblHashtag = new JLabel("Hashtag");
		lblHashtag.setForeground(new Color(255, 255, 255));
		lblHashtag.setBounds(261, 58, 70, 15);
		contentPane.add(lblHashtag);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(232, 100, 117, 25);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnConsulter = new JButton("Consulter");
		btnConsulter.setBackground(new Color(255, 255, 255));
		btnConsulter.setForeground(new Color(0, 153, 255));
		btnConsulter.setBounds(232, 152, 117, 25);
		btnConsulter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ConsultView(app);
				setVisible(false);

			}
		});
		contentPane.add(btnConsulter);

		ImageIcon ic = new ImageIcon("src/main/resources/twitter.gif");
		setIconImage(ic.getImage());
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - getWidth() / 2, dim.height / 2
				- getHeight() / 2);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
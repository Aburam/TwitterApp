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
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class ConsultView extends JFrame{


	private static final long serialVersionUID = -2027268548220138693L;
	private JPanel contentPane;
	private JFrame parent;

	private ConsultView c = this;

	public ConsultView(JFrame p) {
		super("Twitter collect");

		this.parent = p;

		setResizable(false);
		setSize(600, 400);

		setAlwaysOnTop(true);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnConsulterLesNoms = new JButton("Consulter les noms des utilisateurs");
		btnConsulterLesNoms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new GetNameView(c);
				setVisible(false);
			}
		});
		btnConsulterLesNoms.setForeground(new Color(0, 153, 255));
		btnConsulterLesNoms.setBackground(new Color(255, 255, 255));
		btnConsulterLesNoms.setBounds(165, 48, 286, 25);
		contentPane.add(btnConsulterLesNoms);

		JButton btnConsulterLaLangue = new JButton("Consulter la langue des tweets");
		btnConsulterLaLangue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GetLanguageView(c);
				setVisible(false);
			}
		});
		btnConsulterLaLangue.setBackground(new Color(255, 255, 255));
		btnConsulterLaLangue.setForeground(new Color(0, 153, 255));
		btnConsulterLaLangue.setBounds(165, 100, 286, 25);
		contentPane.add(btnConsulterLaLangue);

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
}

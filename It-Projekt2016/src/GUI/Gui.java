package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;




/**
 * Die GUI des Staganographieprogramms *
 * 
 * @author Fabian
 *
 */

public class Gui extends JFrame {
	


	private JPanel contentpane, stegopane, twitterpane;

	private JPanel stegoButtonpane;

	private JPanel twitterButtonpane1, twitterButtonpane2;

	// Buttons
	private JButton btVerstecken, btEntstecken, btTwitterLogin, btTwitterSuche, btTwittertweet, btTwitterBild;
	private JTextField txtStego, txtHash, txtUser;
	private JLabel lHash, lUser;

	// BildButtons
	private JButton btBild1, btBild2;

	private ImageIcon iconIn;
	private ImageIcon iconOut;
	
	private Image image1,image2;
	
	private String uri;

	

	// Komponenten der Menubar
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;

	/**
	 * Der Konstruktor der Gui
	 * 
	 * @author Fabian
	 * @throws URISyntaxException 
	 * @throws IOException 
	 */

	public Gui() throws IOException, URISyntaxException {

		VorschauBildLaden();
		init();
		
		zuordnung();
		ButtonActionListener();

		this.setJMenuBar(menuBar);
		this.setTitle("Stego");
		this.setSize(1300, 800);
		this.setContentPane(contentpane);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

	private void ButtonActionListener() {

		btTwitterLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				btTwitterLoginClicked();

			}
		});
		
		btBild1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
			    int returnVal = chooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			            uri = chooser.getSelectedFile().toString();
			    }
			    
			    try {
					image1 = ImageIO.read(getClass().getResource(uri));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				iconIn = new ImageIcon(image1);
				iconIn.setImage(iconIn.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT));
				btBild1.setIcon(iconIn);
				
			}
		});

	}

	private void btTwitterLoginClicked() {

		Login l = new Login();
		l.setVisible(true);

	}

	private void zuordnung() {

		stegopane.add(btBild1);
		stegopane.add(stegoButtonpane);
		stegopane.add(btBild2);

		twitterpane.add(btTwitterLogin);
		twitterpane.add(twitterButtonpane1);
		twitterpane.add(twitterButtonpane2);

		menu.add(menuItem);
		menuBar.add(menu);

		contentpane.add(stegopane);
		contentpane.add(twitterpane);

	}

	private void init() {
		
		iconIn = new ImageIcon(image1);
		iconOut = new ImageIcon(image2);
		
		//Labels
		lHash = new JLabel("Hash");
		lUser = new JLabel("User");
		
		//Textfelder
		txtStego = new JTextField(25);
		txtHash = new JTextField(25);
		txtUser = new JTextField(25);
		
		//Buttons
		btVerstecken = new JButton("Verstecken");
		btEntstecken = new JButton("Entstecken");
		btTwitterLogin = new JButton("Twitter Login");
		btTwitterSuche = new JButton("Suche");
		btTwittertweet = new JButton("Tweete Nachricht");
		btTwitterBild = new JButton("Tweete Bild");
		
		btBild1 = new JButton(iconIn);
		btBild2 = new JButton(iconOut);
		
		//Überkomponenten
		contentpane = new JPanel(new GridLayout(2,1));
		stegopane = new JPanel(new GridLayout(1,3));
		twitterpane = new JPanel(new GridLayout(1,3));
		
		stegoButtonpane = new JPanel(new GridLayout(3,1));
		
		twitterButtonpane1 = new JPanel(new GridLayout(2,2));
		twitterButtonpane2 = new JPanel(new GridLayout(3,1));

		// Komponenten der Menubar
		menuBar = new JMenuBar();
		menu = new JMenu("Hilfe");
		menuItem = new JMenuItem("Item");

	}
	
	private void VorschauBildLaden(){
		
		try {
			image1 = ImageIO.read(getClass().getResource("image.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			image2 = ImageIO.read(getClass().getResource("image.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
	

	}

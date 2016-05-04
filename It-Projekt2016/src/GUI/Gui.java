package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Die GUI des Staganographieprogramms
 *  * 
 * @author Fabian
 *
 */

public class Gui extends JFrame {
	
	private JPanel contentpane,stegopane,vorschaupane,twitterpane;
	
	//Komponenten der Stegopane
	private JPanel bildpane;
	private JPanel bildBtPane1, bildTxtPane, bildBtPane2, bildLabelPane;
	
	private JButton btBildVerS, btBildEntS;
	private JLabel lBild;
	private String sLabelBild = "leer";
	
	private JTextField txtBild;
	
	//Komponenten der Vorschaupane
	private JPanel vorschauBild;
	private JLabel bild;
	private JButton btVorschauBild;
		
	private Image image;
	private ImageIcon icon;
	
	//Komponenten der Twitterpane
	private JPanel twitterVerbindungsPane, twitterArbeitsPane;
	private JPanel twitterBtPane, twitterTxtPane, twitterBtPane2, twitterTxtPane2,twitterBtVerbindungsPane;
	
	private JButton btTwitterLogin, btTwitterSucheHash, btTwitterPosteTweet;
	private JTextField txtTwitterHash,txtTwitterTweet;
	
	
	
	//Komponenten der Menubar
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	
	/**
	 * Der Konstruktor der Gui
	 * 
	 * @author Fabian
	 */
	
	
	public Gui(){
		
		
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
		
	}

	private void btTwitterLoginClicked() {
		
		Login l = new Login();
		l.setVisible(true);
		
	}

	private void zuordnung() {
		
		//Zuordnung Stegopane
		bildBtPane1.add(btBildVerS);
		bildTxtPane.add(txtBild);
		bildBtPane2.add(btBildEntS);
		bildLabelPane.add(lBild);
				
		bildpane.add(bildBtPane1);
		bildpane.add(bildTxtPane);
		bildpane.add(bildBtPane2);
		bildpane.add(bildLabelPane);
		
		
		//Zuordnung Vorsschau
		vorschauBild.add(bild);
		vorschauBild.add(btVorschauBild,BorderLayout.SOUTH);
		vorschaupane.add(vorschauBild);
		
		
		//Zuordnung Twitter
		twitterBtVerbindungsPane.add(btTwitterLogin,BorderLayout.CENTER);
		twitterVerbindungsPane.add(twitterBtVerbindungsPane);
		
		twitterBtPane.add(btTwitterSucheHash);
		twitterTxtPane.add(txtTwitterHash);
		twitterBtPane2.add(btTwitterPosteTweet);
		twitterTxtPane2.add(txtTwitterTweet);
		
		twitterArbeitsPane.add(twitterBtPane);
		twitterArbeitsPane.add(twitterTxtPane);
		twitterArbeitsPane.add(twitterBtPane2);
		twitterArbeitsPane.add(twitterTxtPane2);
		
		
		
		twitterpane.add(twitterVerbindungsPane);
		twitterpane.add(twitterArbeitsPane);
		
		stegopane.add(bildpane);
		stegopane.add(vorschaupane);
		
		menu.add(menuItem);
		menuBar.add(menu);
		
		contentpane.add(stegopane);
		contentpane.add(twitterpane);
		
	}

	private void init() {
		
		
		contentpane = new JPanel(new GridLayout(1,2));
		stegopane = new JPanel();
		stegopane.setLayout(new GridLayout(2,1));
		vorschaupane = new JPanel(new GridLayout(1,2));
		twitterpane = new JPanel(new GridLayout(2,1));
		
		
		//Komponenten der Stegopane
		bildpane = new JPanel(new GridLayout(4,1));
		
		bildBtPane1 = new JPanel();
		bildBtPane2 = new JPanel();
		bildTxtPane = new JPanel();
		bildLabelPane = new JPanel();
		btBildVerS = new JButton("Bild verschlüsseln");
		btBildEntS = new JButton("Bild entschlüsseln");
		
		
		
		lBild = new JLabel(sLabelBild);
		
		
		txtBild = new JTextField(25);
		
		//Komponenten der Vorschau
		vorschauBild = new JPanel();
		
		btVorschauBild = new JButton("Bild Laden");
		
		icon = new ImageIcon(image);
		icon.setImage(icon.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT));
		bild =  new JLabel(icon);
		
		//Komponenten der Twitterpane
		twitterVerbindungsPane = new JPanel();
		twitterArbeitsPane = new JPanel(new GridLayout(4,1));
		
		twitterBtVerbindungsPane = new JPanel();
		twitterBtPane = new JPanel();
		twitterBtPane2 = new JPanel();
		twitterTxtPane = new JPanel();
		twitterTxtPane2 = new JPanel();
		
		btTwitterLogin = new JButton("Mit Twitter verbinden");
		btTwitterLogin.setSize(200, 50);
		btTwitterSucheHash = new JButton("Suche nach Hash");
		btTwitterPosteTweet = new JButton("Tweete");
		
		txtTwitterHash = new JTextField(25);
		txtTwitterTweet = new JTextField(25);
		
		
		
		
		//Komponenten der Menubar
		menuBar = new JMenuBar();
		menu = new JMenu("Hilfe");
		menuItem = new JMenuItem("Item");
		
	}
	
	private void VorschauBildLaden(){
		
		try {
			image = ImageIO.read(getClass().getResource("image.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
	protected void paintComponent(Graphics g) {
	      super.paintComponents(g);
	      if(image != null) {
	         g.drawImage(image, 0, 0, this);
	      }
	   }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

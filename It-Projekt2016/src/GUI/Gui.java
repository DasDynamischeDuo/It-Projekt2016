package GUI;

import java.awt.*;
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
	private JPanel bildpane,audiopane;
	private JPanel bildBtPane1, bildTxtPane, bildBtPane2, bildLabelPane;
	private JPanel audioBtPane1, audioTxtPane, audioBtPane2, audioLabelPane;
	private JButton btBildVerS, btBildEntS, btAudioVerS, btAudioEntS;
	private JLabel lBild, lAudio;
	private String sLabelBild = "leer";
	private String sLabelAudio = "leer";
	private JTextField txtBild,txtAudio;
	
	//Komponenten der Vorschaupane
	private JPanel vorschauBild;
	
	private JLabel bild;
	
	private JButton btVorschauBild;
	
	
	private JPanel Audiokontrolle;
	
	private Image image;
	private ImageIcon icon;
	
	
	
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
		
		
		this.setJMenuBar(menuBar);
		this.setTitle("Stego");
		this.setSize(1300, 800);
		this.setContentPane(contentpane);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	
		
		
	}

	
	private void zuordnung() {
		
		//Zuordnung Stegopane
		bildBtPane1.add(btBildVerS);
		bildTxtPane.add(txtBild);
		bildBtPane2.add(btBildEntS);
		bildLabelPane.add(lBild);
		
		audioBtPane1.add(btAudioVerS);
		audioTxtPane.add(txtAudio);
		audioBtPane2.add(btAudioEntS);
		audioLabelPane.add(lAudio);
		
		
		bildpane.add(bildBtPane1);
		bildpane.add(bildTxtPane);
		bildpane.add(bildBtPane2);
		bildpane.add(bildLabelPane);
		
		audiopane.add(audioBtPane1);
		audiopane.add(audioTxtPane);
		audiopane.add(audioBtPane2);
		audiopane.add(audioLabelPane);
		
		//Zuordnung Vorsschau
		vorschauBild.add(bild);
		vorschauBild.add(btVorschauBild,BorderLayout.SOUTH);
		vorschaupane.add(vorschauBild);
		
		
		stegopane.add(bildpane);
		stegopane.add(audiopane);
		
		menu.add(menuItem);
		menuBar.add(menu);
		
		contentpane.add(stegopane);
		contentpane.add(vorschaupane);
		contentpane.add(twitterpane);
		
	}

	private void init() {
		
		
		contentpane = new JPanel(new GridLayout(1,3));
		stegopane = new JPanel();
		stegopane.setLayout(new GridLayout(2,1));
		vorschaupane = new JPanel(new GridLayout(2,1));
		twitterpane = new JPanel();
		
		
		//Komponenten der Stegopane
		bildpane = new JPanel(new GridLayout(4,1));
		audiopane = new JPanel(new GridLayout(4,1));		
		
		bildBtPane1 = new JPanel();
		bildBtPane2 = new JPanel();
		bildTxtPane = new JPanel();
		bildLabelPane = new JPanel();
		
		audioBtPane1 = new JPanel();
		audioBtPane2 = new JPanel();
		audioTxtPane = new JPanel();
		audioLabelPane = new JPanel();
		
		btBildVerS = new JButton("Bild verschlüsseln");
		btBildEntS = new JButton("Bild entschlüsseln");
		
		btAudioVerS = new JButton("Audio verschlüsseln");
		btAudioEntS = new JButton("Audio entschlüsseln");
		
		lBild = new JLabel(sLabelBild);
		lAudio = new JLabel(sLabelAudio);
		
		txtBild = new JTextField(25);
		txtAudio = new JTextField(25);
		
		//Komponenten der Vorschau
		vorschauBild = new JPanel();
		
		btVorschauBild = new JButton("Bild Laden");
		
		icon = new ImageIcon(image);
		icon.setImage(icon.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT));
		bild =  new JLabel(icon);
		
		
		
		
		
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

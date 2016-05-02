package GUI;

import java.awt.*;
import javax.swing.*;

public class Gui extends JFrame {
	
	private JPanel contentpane,stegopane,vorschaupane,twitterpane;
	private JPanel bildpane,audiopane;
	
	private JButton btBildVerS, btBildEntS, btAudioVerS, btAudioEntS;
	
	private JLabel lBild, lAudio;
	
	private String sLabelBild = "leer";
	private String sLabelAudio = "leer";
	
	private JTextField txtBild,txtAudio;
	
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	
	
	
	public Gui(){
		
		init();
		zuordnung();
		
		this.setJMenuBar(menuBar);
		this.setTitle("Stego");
		this.setContentPane(contentpane);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	
		
		
	}

	
	private void zuordnung() {
		
		
		bildpane.add(btBildVerS);
		bildpane.add(txtBild);
		bildpane.add(btBildEntS);
		bildpane.add(lBild);
		
		audiopane.add(btAudioVerS);
		audiopane.add(txtAudio);
		audiopane.add(btAudioEntS);
		audiopane.add(lAudio);
		
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
		vorschaupane = new JPanel();
		twitterpane = new JPanel();
		
		bildpane = new JPanel(new GridLayout(4,1));
		audiopane = new JPanel(new GridLayout(4,1));		
		
		btBildVerS = new JButton("Bild verschlüsseln");
		btBildEntS = new JButton("Bild entschlüsseln");
		
		btAudioVerS = new JButton("Audio verschlüsseln");
		btAudioEntS = new JButton("Audio entschlüsseln");
		
		lBild = new JLabel(sLabelBild);
		lAudio = new JLabel(sLabelAudio);
		
		txtBild = new JTextField();
		txtAudio = new JTextField();
		
		menuBar = new JMenuBar();
		menu = new JMenu("Hilfe");
		menuItem = new JMenuItem("Item");
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

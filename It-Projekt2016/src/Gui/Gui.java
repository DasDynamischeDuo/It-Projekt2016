package Gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import BildSteganography.BildSteganography;

import sun.tools.jar.JarImageSource;

public class Gui extends JFrame{

	private JPanel contentpane;
	private JPanel pHide;
	private JPanel pExtract;
	private JPanel pBild;
	private JTextField tfMassage;
	private JButton bBildInLaden;
	private JButton bBildOutLaden;
	private JLabel lMessage;
	
	private BildSteganography bildSteganography;
	
	public Gui() {
		
		bildSteganography = new BildSteganography();
		init();
		actionListener();
		
	}

	private void actionListener() {
		bBildInLaden.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		})
		
	}

	private void init() {
		contentpane = new JPanel(new BorderLayout());
		pBild = new JPanel();
		pExtract = new JPanel();
		pHide = new JPanel();
		tfMassage = new JTextField("Message", 25);
		bBildInLaden = new JButton("Bild Laden");
		bBildOutLaden = new JButton("Bild Laden");
		lMessage = new JLabel("Message");
		
		this.setContentPane(contentpane);
		contentpane.add(pHide, BorderLayout.WEST);
		contentpane.add(pExtract, BorderLayout.EAST);
		
		pHide.add(tfMassage);
		pHide.add(bBildInLaden);
		
		pExtract.add(bBildOutLaden);
		pExtract.add(lMessage);
		
	}
	
	
}

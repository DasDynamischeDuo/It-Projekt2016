package Gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Twitter.TwitterLogin;

public class Login extends JFrame {

	private JPanel contentpane, arbeitsPane, btPane;
	private JButton btOk, btAbr;
	private JLabel lConsumerKey, lConsumerSecret, lAccesToken, lAccesTokenSecret;
	private JTextField txtConsumerKey, txtConsumerSecret, txtAccesToken, txtAccesTokenSecret;

	public Login() {

		init();
		zuordnung();
		buttonActionListener();
		this.setSize(new Dimension(600, 160));
		this.setContentPane(contentpane);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
	}

	private void buttonActionListener() {
		
		btAbr.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				btAbrClicked();
				
			}
		});
		
		btOk.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				btOkClicked();
				
			}
		});
	}

	private void btOkClicked() {
		
		TwitterLogin.setAccesToken(txtAccesToken.getText());
		TwitterLogin.setAccesTokenSecret(txtAccesTokenSecret.getText());
		TwitterLogin.setConsumerKey(txtConsumerKey.getText());
		TwitterLogin.setConsumerSecret(txtConsumerSecret.getText());
		
		this.dispose();
		
		
	}

	private void btAbrClicked() {
		
		this.dispose();
		
	}

	private void zuordnung() {
		
		arbeitsPane.add(lAccesToken);
		arbeitsPane.add(txtAccesToken);
		arbeitsPane.add(lAccesTokenSecret);
		arbeitsPane.add(txtAccesTokenSecret);
		arbeitsPane.add(lConsumerKey);
		arbeitsPane.add(txtConsumerKey);
		arbeitsPane.add(lConsumerSecret);		
		arbeitsPane.add(txtConsumerSecret);
		
		
		btPane.add(btOk);
		btPane.add(btAbr);
		
		contentpane.add(arbeitsPane);
		contentpane.add(btPane);
	}

	private void init() {
		contentpane = new JPanel();
		arbeitsPane = new JPanel(new GridLayout(4,4));
		btPane = new JPanel(new GridLayout(1,2));
		
		btOk = new JButton("OK");
		btAbr = new JButton("Abrechen");
		
		lConsumerKey = new JLabel("Consumer Key");
		lConsumerSecret = new JLabel("Consumer Sectret");
		lAccesToken = new JLabel("AccesToken");
		lAccesTokenSecret = new JLabel("AccesTokenSecret");
		
		txtConsumerKey = new JTextField(25);
		txtConsumerSecret = new JTextField(25);
		txtAccesToken = new JTextField(25);
		txtAccesTokenSecret = new JTextField(25);
		
		
	}

}
package Gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import twitter4j.TwitterException;

import Twitter.TwitterLogin;

public class Login extends JFrame {

	private JPanel contentpane, arbeitsPane, btPane;
	private JButton btOk, btAbr;
	private JLabel lConsumerKey, lConsumerSecret, lAccesToken, lAccesTokenSecret;
	private JTextField txtConsumerKey, txtConsumerSecret, txtAccesToken, txtAccesTokenSecret;
	private TwitterLogin twitterLogin;
	private Gui gui;
	

	public Login(TwitterLogin twitterLogin, Gui gui) {

		init();
		zuordnung();
		buttonActionListener();
		this.setSize(new Dimension(600, 160));
		this.setContentPane(contentpane);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.twitterLogin = twitterLogin;
		this.gui = gui;
		
		
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
		
		if (txtAccesToken.getText() == null && txtAccesTokenSecret.getText() == null && txtConsumerKey.getText() == null && txtConsumerSecret.getText() == null) {
			
			JOptionPane.showMessageDialog(this, "Felder nicht ausgefuellt!", "Warnung", JOptionPane.WARNING_MESSAGE);
		}
		
		else {
			
			twitterLogin.setAccesToken(txtAccesToken.getText());
			twitterLogin.setAccesTokenSecret(txtAccesTokenSecret.getText());
			twitterLogin.setConsumerKey(txtConsumerKey.getText());
			twitterLogin.setConsumerSecret(txtConsumerSecret.getText());
			
			twitterLogin.reConfiguration();
			gui.setLogin(twitterLogin.checkLogin());
			
		}

		
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
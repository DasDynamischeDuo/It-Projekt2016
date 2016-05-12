package Gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import twitter4j.TwitterException;

import Twitter.LoginData;
import Twitter.TwitterLogin;

public class Login extends JFrame {

	private JPanel contentpane, arbeitsPane, btPane;
	private JButton btOk, btAbr;
	private JLabel lConsumerKey, lConsumerSecret, lAccesToken, lAccesTokenSecret, lName;
	private JTextField txtConsumerKey, txtConsumerSecret, txtAccesToken, txtAccesTokenSecret, txtName;
	
	private TwitterLogin twitterLogin;
	private Gui gui;
	private LoginData loginData;
	

	public Login(TwitterLogin twitterLogin, Gui gui, LoginData loginData) {

		init();
		zuordnung();
		buttonActionListener();
		this.setSize(new Dimension(600, 160));
		this.setContentPane(contentpane);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.twitterLogin = twitterLogin;
		this.gui = gui;
		this.loginData = loginData;
		
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
			
			loginData.setName(txtName.getText());
			loginData.setAccesToken(txtAccesToken.getText());
			loginData.setAccesTokenSecret(txtAccesTokenSecret.getText());
			loginData.setConsumerKey(txtConsumerKey.getText());
			loginData.setConsumerSecret(txtConsumerSecret.getText());
			
			try {
				loginData.saveLoginData();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			twitterLogin.reConfiguration();
			gui.setLogin(twitterLogin.checkLogin());
			
		
		this.dispose();
			
		
	}

	private void btAbrClicked() {
		
		this.dispose();
		
	}

	private void zuordnung() {
		
		arbeitsPane.add(lName);
		arbeitsPane.add(txtName);
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
		arbeitsPane = new JPanel(new GridLayout(6,4));
		btPane = new JPanel(new GridLayout(1,2));
		
		btOk = new JButton("OK");
		btAbr = new JButton("Abrechen");
		
		btOk.setEnabled(false);
		
		lName = new JLabel("Name");
		lConsumerKey = new JLabel("Consumer Key");
		lConsumerSecret = new JLabel("Consumer Sectret");
		lAccesToken = new JLabel("AccesToken");
		lAccesTokenSecret = new JLabel("AccesTokenSecret");
		
		txtName = new JTextField(25);
		txtConsumerKey = new JTextField(25);
		txtConsumerSecret = new JTextField(25);
		txtAccesToken = new JTextField(25);
		txtAccesTokenSecret = new JTextField(25);
		
		txtConsumerKey.getDocument().addDocumentListener(new DocumentListener() {
			
			public void removeUpdate(DocumentEvent arg0) {
				changed();
				
			}
			
			public void insertUpdate(DocumentEvent arg0) {
				changed();
				
			}
			
			public void changedUpdate(DocumentEvent arg0) {
				changed();
				
			}
		});
		
		txtConsumerSecret.getDocument().addDocumentListener(new DocumentListener() {
			
			public void removeUpdate(DocumentEvent arg0) {
				changed();
				
			}
			
			public void insertUpdate(DocumentEvent arg0) {
				changed();
				
			}
			
			public void changedUpdate(DocumentEvent arg0) {
				changed();
				
			}
		});
		
		txtAccesToken.getDocument().addDocumentListener(new DocumentListener() {
			
			public void removeUpdate(DocumentEvent arg0) {
				changed();
				
			}
			
			public void insertUpdate(DocumentEvent arg0) {
				changed();
				
			}
			
			public void changedUpdate(DocumentEvent arg0) {
				changed();
				
			}
		});
		
		txtAccesTokenSecret.getDocument().addDocumentListener(new DocumentListener() {
			
			public void removeUpdate(DocumentEvent arg0) {
				changed();
				
			}
			
			public void insertUpdate(DocumentEvent arg0) {
				changed();
				
			}
			
			public void changedUpdate(DocumentEvent arg0) {
				changed();
				
			}
		});
		
	}

	protected void changed() {

		if (txtAccesToken.getText().equals("") | txtAccesTokenSecret.getText().equals("") | txtConsumerKey.getText().equals("") | txtConsumerSecret.getText().equals("")) {
			btOk.setEnabled(false);
		} else {
			btOk.setEnabled(true);
		}
		
		
	}

}
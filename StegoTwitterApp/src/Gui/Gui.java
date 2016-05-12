package Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.Vector;

import javax.crypto.NoSuchPaddingException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.omg.CORBA.PUBLIC_MEMBER;

import BildSteganography.BildSteganography;
import Twitter.LoginData;
import Twitter.SaveImageFromUrl;
import Twitter.TwitterLogin;


public class Gui extends JFrame{

	private boolean loginDatenGesetzt = false;
	
	private String TwitterUrl = "https://apps.twitter.com/";
	
	//VorschauStego
	private JPanel contentpane;
	private JPanel pHide;
	private JPanel pExtract;
	private JPanel pMessage;
	
	private JPanel pBildIn;
	private JPanel pBildOut;
	
	private JPanel pButton;
	private JPanel pButtonSteno;
	
	private JTextField tfMessage;
	
	private JButton bBildInLaden;
	private JButton bBildOutLaden;
	private JButton bHide;
	private JButton bExtract;
	
	private BildSteganography bildSteganography;
	private TwitterLogin twitterLogin;
	private LoginData loginData;
	
	
	private File imgIn;
	private File imgOut;
	private File twitterImage;
	private BufferedImage bufferedImgIn;
	private BufferedImage bufferedImgOut;

	private ImageIcon iconIn;
	private ImageIcon iconOut;
	
	//Twitter
	private JPanel pTwitterLeer,pTwitterSuche,pTwitterTweet;
	
	private JButton btTwitterSuche,btTweetBild;
	
	private JTextField txtHash,txtHashEingabe, txtAccount;
	private JLabel lHash, lAccount, lHashEingabe;
	
	//MenuBar
	private JMenuBar menuBar;
	private JMenu menuHilfe, menuLogin;

	private JMenuItem menuItemTwitterHilfe, menuItemStegoHilfe, menuItemNewLogin, menuLoadLogin;
	
	//Verschönerungspanels
	
	private JPanel pLeer;
	private JPanel pbextract,pbhide,ptxtmessage;
	
	private JPanel ptxtUBt;
	private JPanel plhashlinks,ptxtlinks,pbtBildtwittern;
	
	private JPanel pRGridL,pRGridTxt;
	private JPanel plAcc,plHash, ptxtAcc,ptxtHash;

	

	
	
	
	public Gui() throws IOException, URISyntaxException, NoSuchAlgorithmException, NoSuchPaddingException {
		
		loginData = new LoginData();
		bildSteganography = new BildSteganography(this);
		twitterLogin = new TwitterLogin(this, loginData);
		
		initBild();
		init();
		actionListener();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(new Dimension(1150,700));
		
	}

	private void initBild() throws IOException, URISyntaxException {
		
		URI uri = null;
		uri = BildSteganography.class.getResource("/Pictures/Vorschau.png").toURI();
		
		imgIn = new File(uri);
		bufferedImgIn = ImageIO.read(imgIn);
		iconIn = new ImageIcon(bufferedImgIn);
		iconIn.setImage(iconIn.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT));
		
		imgOut = new File(uri);
		bufferedImgOut = ImageIO.read(imgOut);
		iconOut = new ImageIcon(bufferedImgOut);
		iconOut.setImage(iconOut.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT));
		
		
		
	}

	private void actionListener() {
		bBildInLaden.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				URI uri = null;
				JFileChooser chooser = new JFileChooser();
			    int returnVal = chooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			            uri = chooser.getSelectedFile().toURI();
			    }
			    
			    imgIn = new File(uri);
				try {
					iconIn = new ImageIcon(ImageIO.read(imgIn));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				iconIn.setImage(iconIn.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT));
				bBildInLaden.setIcon(iconIn);
				
			}
		});
		
		
		bBildOutLaden.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				URI uri = null;
				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					uri = chooser.getSelectedFile().toURI();
				}
				    
				showImageSteno(uri);
					
			}
		});
		
		
		bHide.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				hideInImage();
				
			}
		});
		
		
		bExtract.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					String text = bildSteganography.extractText(imgOut);
					tfMessage.setText(text);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		menuItemNewLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				btTwitterLoginClicked();
				
			}
		});
		
		menuLoadLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				btLoadLoginClicked();
				
			}
		});
		
		
		btTwitterSuche.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				btTwitterSucheClicked();
				
			}
		});
		
		
		btTweetBild.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				btTweetBildClocked();
				
			}

			
		});
		

		menuItemTwitterHilfe.addActionListener(new ActionListener() {

		
			
			public void actionPerformed(ActionEvent e) {
				twitterHilfe();
				
			}
		});
		
		menuItemStegoHilfe.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				stegoHilfe();
				
				
			}
		});
		
		
	}

	protected File hideInImage() {
		
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			imgOut = chooser.getSelectedFile();
		}
		
		try {
			bufferedImgOut = bildSteganography.hideText(tfMessage.getText(), imgIn);
			ImageIO.write(bufferedImgOut, "png", imgOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return imgOut;
		
	}

	protected void showImageSteno(URI uri) {
		
		imgOut = new File(uri);
		try {
			iconOut = new ImageIcon(ImageIO.read(imgOut));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		iconOut.setImage(iconOut.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT));
		bBildOutLaden.setIcon(iconOut);
		
	}

	protected void btLoadLoginClicked() {
		
		

		
			String url = LoginData.class.getResource("").toString();
			url = url.substring(5, url.length());
			url = url.replace("Twitter/", "") + "LoginData/";
			File folder = new File(url);
			File[] listOfFiles = folder.listFiles();
			Vector<String> fileNames = new Vector<String>();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile() && listOfFiles[i].getName().contains(".bin")) {
					fileNames.add(listOfFiles[i].getName().replace(".bin", ""));
				}
			}
			String[] fileNamesArray = fileNames.toArray(new String[0]);
			String fileName = (String) JOptionPane.showInputDialog(null, "Choose now...", "The Choice of a Lifetime",
					JOptionPane.QUESTION_MESSAGE, null, fileNamesArray, fileNamesArray[0]);
			url = url + fileName + ".bin";
			try {
				loginData = loginData.loadLoginData(url);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
			twitterLogin.setLoginData(loginData);
			twitterLogin.reConfiguration();
			loginDatenGesetzt = twitterLogin.checkLogin();
			
		
		
		
		
		
	}

	protected void stegoHilfe() {
		
		JOptionPane.showMessageDialog(this,"Um eine Nachricht in einem Bild zu verschluesseln muss man auf den Button vorschau, klicken.");
		
	}

	private void twitterHilfe() {
		
		
		JOptionPane.showMessageDialog(this, "Um ihre Login Daten zu erhalten muessen sie sich hier: "+ TwitterUrl + " anmelden.");
		
		
	}

	private void btTweetBildClocked() {
		
		if (loginDatenGesetzt == true) {
			
			URI uri = hideInImage().toURI();
			twitterImage = new File(uri);
			System.out.println(twitterImage);
			twitterLogin.tweetImage(twitterImage, txtHashEingabe.getText());
		}
		else {
		JOptionPane.showMessageDialog(this,
			    "Login Daten nicht gesetzt",
			    "Warnung",
			    JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void btTweetClicked() {
		
		if (loginDatenGesetzt == true) {
			twitterLogin.tweetStatus(txtHashEingabe.getText());
		} else { 
			JOptionPane.showMessageDialog(this, "Login Daten nicht gesetzt",
				    "Warnung",
				    JOptionPane.WARNING_MESSAGE);
		}
			   
		
	}

	private void btTwitterSucheClicked() {
		
		
		String dest = null;
		if (loginDatenGesetzt == true) {
			String file = twitterLogin.getTweetandMediafromHash(txtHash.getText(), txtAccount.getText());
			System.out.println(file);
			JFileChooser chooser = new JFileChooser();
			int returnVal = chooser.showSaveDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				dest = chooser.getSelectedFile().getAbsolutePath();
				SaveImageFromUrl.setDestinationFile(dest);
				try {
					SaveImageFromUrl.saveImage(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			showImageSteno(new File(dest).toURI());
			
			
		} else {
		
		JOptionPane.showMessageDialog(this,
			    "Login Daten nicht gesetzt",
			    "Warnung",
			    JOptionPane.WARNING_MESSAGE);
				
		}
		
	}

	private void btTwitterLoginClicked() {
		
		Login l = new Login(twitterLogin, this, loginData);
		l.setVisible(true);
		l.pack();
		
	}

	private void init() {
		
		//OberesPanel
		contentpane = new JPanel(new GridLayout(2,3));
		pHide = new JPanel();
		pButton = new JPanel(new GridLayout(5,1));
		pExtract = new JPanel();
		pMessage = new JPanel();
		pBildIn = new JPanel();
		
		tfMessage = new JTextField("Message");
		tfMessage.setColumns(25);
		bBildInLaden = new JButton(iconIn);
		bBildOutLaden = new JButton(iconOut);
		bHide = new JButton("Hide ->");
		bExtract = new JButton("<- Extract");
		
		
		txtAccount = new JTextField();
		
		
		bBildInLaden.setBorder(BorderFactory.createEmptyBorder());
		bBildOutLaden.setBorder(BorderFactory.createEmptyBorder());
		
		//Unteres Panel
		
		pTwitterLeer = new JPanel();
		pTwitterSuche = new JPanel(new BorderLayout());
		pTwitterTweet = new JPanel(new BorderLayout());
		
		btTwitterSuche = new JButton("Suche");
		btTweetBild = new JButton("Bild twittern");
		
		txtHash = new JTextField(25);
		
		txtHashEingabe = new JTextField(25);

		txtAccount = new JTextField(25);
		
		lHash = new JLabel("Hashtag");
		lHashEingabe = new JLabel("Hashtag");
		lAccount = new JLabel("Account ");
		
		//MenuBar

		menuBar = new JMenuBar();
		menuLogin = new JMenu("Login");
		menuItemNewLogin = new JMenuItem("Neuer Login");
		menuBar.add(menuLogin);
		menuLogin.add(menuItemNewLogin);
		menuLoadLogin = new JMenuItem("Login laden");
		menuLogin.add(menuLoadLogin);
		
		menuHilfe = new JMenu("Hilfe");
		menuBar.add(menuHilfe);
		
		menuItemTwitterHilfe = new JMenuItem("Twitter Login");
		menuItemStegoHilfe = new JMenuItem("Steganographie");
		menuHilfe.add(menuItemStegoHilfe);
		menuHilfe.add(menuItemTwitterHilfe);
		
		//Verschönerung
		
		pLeer = new JPanel();
		
		pbextract = new JPanel();
		pbextract.add(bExtract,BorderLayout.CENTER);
		pbhide = new JPanel();
		pbhide.add(bHide,BorderLayout.CENTER);
		ptxtmessage = new JPanel();
		ptxtmessage.add(tfMessage,BorderLayout.CENTER);
		
		ptxtUBt = new JPanel(new GridLayout(3,1));
		
		plhashlinks = new JPanel();
		plhashlinks.add(lHashEingabe);
		ptxtlinks = new JPanel();
		ptxtlinks.add(txtHashEingabe);
	

		ptxtUBt.add(ptxtlinks);
		
		pRGridL = new JPanel(new GridLayout(2,1));
		plAcc = new JPanel();
		plAcc.add(lAccount);
		pRGridL.add(plAcc);
		plHash = new JPanel();
		plHash.add(lHash);
		pRGridL.add(plHash);
		
		pRGridTxt = new JPanel(new GridLayout(2,1));
		ptxtAcc = new JPanel();
		ptxtAcc.add(txtAccount);
		pRGridTxt.add(ptxtAcc);
		
		ptxtHash = new JPanel();
		ptxtHash.add(txtHash);
		pRGridTxt.add(ptxtHash);
		
		
		
		
		
		
		
		
		
		
		
		this.setJMenuBar(menuBar);
		this.setContentPane(contentpane);
		contentpane.add(pHide);
		contentpane.add(pButton);
		contentpane.add(pExtract);
		contentpane.add(pTwitterTweet);
		contentpane.add(pTwitterLeer);
		contentpane.add(pTwitterSuche);
		
		//UnteresPanel
		pTwitterSuche.add(pRGridL,BorderLayout.WEST);
		pTwitterSuche.add(pRGridTxt,BorderLayout.EAST);
		pTwitterSuche.add(btTwitterSuche,BorderLayout.SOUTH);
		
		pTwitterTweet.add(pLeer,BorderLayout.NORTH);
		pTwitterTweet.add(plhashlinks,BorderLayout.WEST);
		pTwitterTweet.add(ptxtUBt,BorderLayout.CENTER);
		pTwitterTweet.add(btTweetBild,BorderLayout.SOUTH);

		
		//OberesPanel
		pButton.add(pLeer);
		pButton.add(pbhide);
		pButton.add(pbextract);
		pButton.add(ptxtmessage);
		
		pBildIn.add(bBildInLaden);
		pHide.add(pBildIn, BorderLayout.CENTER);
		pExtract.add(bBildOutLaden);
		
	}
	
	public void setLogin(boolean b){
		
		loginDatenGesetzt = b;
		
	}
	
	public void error(String text) {
		
		JOptionPane.showMessageDialog(this, text);
		
	}
	
}

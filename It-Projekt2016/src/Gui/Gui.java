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
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.omg.CORBA.PUBLIC_MEMBER;

import BildSteganography.BildSteganography;
import Twitter.SaveImageFromUrl;
import Twitter.TwitterLogin;



public class Gui extends JFrame{

<<<<<<< HEAD
	//Prï¿½fe LoginDaten
	private static boolean LoginDatenGesetzt = false;
=======
	//Pruefe LoginDaten
	private boolean LoginDatenGesetzt = false;
>>>>>>> branch 'Emanuel' of https://github.com/DasDynamischeDuo/It-Projekt2016.git
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
	
	
	private URI uri;
	private File imgIn;
	private File imgOut;
	private File twitterImage;
	private BufferedImage bufferedImgIn;
	private BufferedImage bufferedImgOut;

	private ImageIcon iconIn;
	private ImageIcon iconOut;
	
	//Twitter
	private JPanel pTwitterLogin,pTwitterButtons,pTwitterSonst;
	
	private JButton btTwitterLogin,btTwitterSuche,btTweet,btTweetBild;
	
	private JTextField txtHash,txtTweet;
	private JLabel lHash;
	
	//MenuBar
	private JMenuBar menuBar;
	private JMenu menu;

	private JMenuItem menuItemTwitterHilfe,menuItemStegoHilfe;

	
	
	
	public Gui() throws IOException, URISyntaxException, NoSuchAlgorithmException, NoSuchPaddingException {
		
		bildSteganography = new BildSteganography(this);
		twitterLogin = new TwitterLogin(this);
		
		initBild();
		init();
		actionListener();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(new Dimension(1200,700));
		
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
				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					uri = chooser.getSelectedFile().toURI();
				}
				    
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
		});
		
		
		bHide.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
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
		btTwitterLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				btTwitterLoginClicked();
				
			}
		});
		
		btTwitterSuche.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				btTwitterSucheClicked();
				
			}
		});
		btTweet.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				btTweetClicked();
				
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

	protected void stegoHilfe() {
		
		JOptionPane.showMessageDialog(this,"Um eine Nachricht in einem Bild zu verschlüsseln muss man auf den Button vorschau, klicken.");
		
	}

	private void twitterHilfe() {
		
		
		JOptionPane.showMessageDialog(this,
<<<<<<< HEAD
		    "Um ihre Login Daten zu erhalten mï¿½ssen sie sich hier: "+ TwitterUrl + " anmelden.");
=======
		    "Um ihre Login Daten zu erhalten muessen sie sich hier: "+ TwitterUrl + " anmelden.");
>>>>>>> branch 'Emanuel' of https://github.com/DasDynamischeDuo/It-Projekt2016.git
		
		
	}

	private void btTweetBildClocked() {
		
		if (LoginDatenGesetzt == true) {
			JFileChooser chooser = new JFileChooser();
			int returnVal = chooser.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				uri = chooser.getSelectedFile().toURI();
			}
			twitterImage = new File(uri);
			System.out.println(twitterImage);
			twitterLogin.tweetImage(twitterImage);
		}
		
		JOptionPane.showMessageDialog(this,
			    "Login Daten nicht gesetzt",
			    "Warnung",
			    JOptionPane.WARNING_MESSAGE);
		
	}
	
	private void btTweetClicked() {
		
		if (LoginDatenGesetzt == true) {
			twitterLogin.tweetStatus(txtTweet.getText());
		} else { 
			JOptionPane.showMessageDialog(this, "Login Daten nicht gesetzt",
				    "Warnung",
				    JOptionPane.WARNING_MESSAGE);
		}
			   
		
	}

	private void btTwitterSucheClicked() {
		
		if (LoginDatenGesetzt == true) {
			String file = twitterLogin.getTweetandMediafromHash(txtHash.getText());
			System.out.println(file);
			JFileChooser chooser = new JFileChooser();
			int returnVal = chooser.showSaveDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				String dest = chooser.getSelectedFile().getAbsolutePath();
				SaveImageFromUrl.setDestinationFile(dest);
				try {
					SaveImageFromUrl.saveImage(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			
			
		} else {
		
		JOptionPane.showMessageDialog(this,
			    "Login Daten nicht gesetzt",
			    "Warnung",
			    JOptionPane.WARNING_MESSAGE);
				
		}
		
	}

	private void btTwitterLoginClicked() {
		
		Login l = new Login(twitterLogin, this);
		l.setVisible(true);
		
	}

	private void init() {
		
		//OberesPanel
		contentpane = new JPanel(new GridLayout(2,3));
		pHide = new JPanel();
		pButton = new JPanel(new BorderLayout());
		pExtract = new JPanel();
		pMessage = new JPanel();
		pBildIn = new JPanel();
		pButtonSteno = new JPanel(new BorderLayout());
		tfMessage = new JTextField("Message");
		bBildInLaden = new JButton(iconIn);
		bBildOutLaden = new JButton(iconOut);
		bHide = new JButton("Hide ->");
		bExtract = new JButton("<- Extract");
		
		
		bBildInLaden.setBorder(BorderFactory.createEmptyBorder());
		bBildOutLaden.setBorder(BorderFactory.createEmptyBorder());
		
		//Unteres Panel
		
		pTwitterLogin = new JPanel();
		pTwitterButtons = new JPanel();
		pTwitterSonst = new JPanel();
		
		btTwitterLogin = new JButton("Login Twitter");
		btTwitterSuche = new JButton("Suche");
		btTweet = new JButton("Tweet");
		btTweetBild = new JButton("TweeteBild");
		
		txtHash = new JTextField(25);
		
		txtTweet = new JTextField(25);
		
		lHash = new JLabel("Hashtag");
		
		//MenuBar
		
		
		menuBar = new JMenuBar();
		menu = new JMenu("Hilfe");
		menuBar.add(menu);

		menuItemTwitterHilfe = new JMenuItem("TwitterLogin");
		menuItemStegoHilfe = new JMenuItem("StegoHilfe");
		menu.add(menuItemStegoHilfe);
		menu.add(menuItemTwitterHilfe);

		
		
		this.setJMenuBar(menuBar);
		this.setContentPane(contentpane);
		contentpane.add(pHide);
		contentpane.add(pButton);
		contentpane.add(pExtract);
		contentpane.add(pTwitterLogin);
		contentpane.add(pTwitterButtons);
		contentpane.add(pTwitterSonst);
		
		//UnteresPanel
		pTwitterLogin.add(btTwitterLogin,BorderLayout.CENTER);
		
		pTwitterButtons.add(lHash,BorderLayout.WEST);
		pTwitterButtons.add(txtHash,BorderLayout.EAST);
		pTwitterButtons.add(btTwitterSuche,BorderLayout.SOUTH);
		
		pTwitterSonst.add(btTweet);
		pTwitterSonst.add(txtTweet);
		pTwitterSonst.add(btTweetBild);
		
		
		
		
		
		//OberesPanel
		pButtonSteno.add(bHide, BorderLayout.WEST);
		pButtonSteno.add(bExtract, BorderLayout.EAST);
		pButton.add(pButtonSteno, BorderLayout.NORTH);
		pButton.add(tfMessage, BorderLayout.SOUTH);
		pBildIn.add(bBildInLaden);
		pHide.add(pBildIn, BorderLayout.CENTER);
		pExtract.add(bBildOutLaden);
		
	}
	
	public void setLogin(boolean b){
		
		LoginDatenGesetzt = b;
		
	}
	
	public void error(String text) {
		
		JOptionPane.showMessageDialog(this, text);
		
	}
	
	
}

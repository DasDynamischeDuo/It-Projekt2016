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

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.sun.org.apache.bcel.internal.generic.LMUL;

import BildSteganography.BildSteganography;
import Twitter.TwitterLogin;



public class Gui extends JFrame{

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
	
	
	
	public Gui() throws IOException, URISyntaxException {
		
		bildSteganography = new BildSteganography();
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
					System.out.println(imgOut.getAbsolutePath());
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
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btTwitterLoginClicked();
				
			}
		});
		
		btTwitterSuche.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btTwitterSucheClicked();
				
			}
		});
		btTweet.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btTweetClicked();
				
			}
		});
		
		btTweetBild.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					uri = chooser.getSelectedFile().toURI();
				}
				    
				twitterImage = new File(uri);
				System.out.println(twitterImage);
				TwitterLogin.tweetImage(twitterImage);
				
			}
		});
		
		
	}

	private void btTweetClicked() {
		
		TwitterLogin.tweetStatus(txtTweet.getText());
		
	}

	private void btTwitterSucheClicked() {
		
		TwitterLogin.getTweetandMediafromHash(txtHash.getText());
		
	}

	private void btTwitterLoginClicked() {
		
		Login l = new Login();
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
	
	
}

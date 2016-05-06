package GUI;

import java.awt.BorderLayout;
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

import Steganography.BildSteganography;



public class Gui extends JFrame{

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
	private BufferedImage bufferedImgIn;
	private BufferedImage bufferedImgOut;
	
	private ImageIcon iconIn;
	private ImageIcon iconOut;
	
	
	public Gui() throws IOException, URISyntaxException {
		
		bildSteganography = new BildSteganography();
		initBild();
		init();
		actionListener();
		
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
		
		
	}

	private void init() {
		contentpane = new JPanel(new BorderLayout());
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
		
		
		this.setContentPane(contentpane);
		contentpane.add(pHide, BorderLayout.WEST);
		contentpane.add(pButton, BorderLayout.CENTER);
		contentpane.add(pExtract, BorderLayout.EAST);
		
		
		pButtonSteno.add(bHide, BorderLayout.WEST);
		pButtonSteno.add(bExtract, BorderLayout.EAST);
		pButton.add(pButtonSteno, BorderLayout.NORTH);
		pButton.add(tfMessage, BorderLayout.SOUTH);
		
		pBildIn.add(bBildInLaden);
		
		pHide.add(pMessage, BorderLayout.NORTH);
		pHide.add(pBildIn, BorderLayout.CENTER);
		
		pExtract.add(bBildOutLaden);
		
	}
	
	
}

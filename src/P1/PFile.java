package P1;


import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class PFile extends JFrame {
	private DrawingPanel dp;
	private JButton Button;
	private String mn;
	public static String filename;
	
	private FileInputStream fis;
	
	PFile(){
		dp=new DrawingPanel();
		this.add(dp);
		Button=new JButton("FileChoose");
		this.add(Button,BorderLayout.SOUTH);
		this.setVisible(true);
		this.setSize(400,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Button.addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser("./");
			    int returnValue = fileChooser.showOpenDialog(null);
			    if (returnValue == JFileChooser.APPROVE_OPTION) 
			    {
			    File selectedFile = fileChooser.getSelectedFile();
			    filename=selectedFile.getName();
			    try {
			    	
					fis = new FileInputStream(selectedFile);
					mn=getMagicNumber();
					switch(mn)  {
					
						case "P1":
							new denemep1();
							break;
						
						case "P2":
							new denemep2();
							break;
						
						case "P3":
							new denemep3();
							break;
						
						case "P4":
							new denemep4();
							break;
						case "P5":
							new denemep5();
							break;
						
						case "P6":
							new denemep6();
							break;
					}
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    
			    }
			}	
			
			
		});
				
	}
	
	private String getMagicNumber() {
		byte [] magicNum = new byte[2];
		try {
			fis.read(magicNum);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return new String(magicNum);
	}
	
	
	
	class DrawingPanel extends JPanel{
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
		}
	}

	public static void main(String[] args) {

		new PFile();
	}

}
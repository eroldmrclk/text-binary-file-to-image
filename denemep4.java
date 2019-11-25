package P1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class denemep4 extends JFrame{
	private DrawingPanel dp;
	private int ws;
	private int w,h,i=0;
	private FileInputStream fis;
	private int [] Image;
	private int [] [] ImageColor;
	private Timer timer;
	denemep4(){
		timer=new Timer(1000,new TimerListener());
		try {
			fis = new FileInputStream(PFile.filename);
			String mn = getMagicNumber();
			System.out.println(mn);
			
			skipWhitespace();
			
			w = readNumber();
			
			skipWhitespace();
			
			h = readNumber();
			
			int extra=8-(w%8);
			int sizevalue=(w*h/8)+(extra*h/8);
			Image=new int[sizevalue];
			ImageColor=new int[h][w+extra];			

				for(int i=0;i<sizevalue;i++) {
						Image[i]=fis.read();
				}
								
				int a=-1;
				for(int j=0;j<h;j++) {
					for(int k=0;k<(w+extra)/8;k++) {
						a++;
						for(int i=8;i>0;i--) {
							ImageColor[j][(k*8)+(i-1)]=Image[a]%2;
							Image[a]=Image[a]/2;
						}
					}
				}
				
				dp=new DrawingPanel();
				this.add(dp);
				this.setSize(600,600);
				this.setVisible(true);
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(IOException e2) {}
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600,600);
		this.setVisible(true);
	}
	
	private String getMagicNumber() {
		byte [] magicNum = new byte[2];
		try {
			fis.read(magicNum);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String(magicNum);
	}
	
	private void skipWhitespace() {
		try {
			ws = fis.read();

			while(Character.isWhitespace(ws))
				ws = fis.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	private int readNumber() {
		String wstr="";
		try {
			while(!Character.isWhitespace(ws)) {
					wstr = wstr + (ws-'0');
					ws = fis.read();
				}
		}catch(IOException e2) {} 
		
		System.out.println(wstr);
		return Integer.parseInt(wstr);
	}
	
	
class TimerListener implements ActionListener{
		
		public void actionPerformed(ActionEvent evt) {
			i++;
			if(i<6)
			   dp.repaint();
		}
}
	
	
	class DrawingPanel extends JPanel{
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			for(int a=0; a<h; a++) {
				for(int b=0; b<((w/5)+(i*w/5)); b++) {
					if(a<h && b<w) {
						if(ImageColor[a][b]==0)
							g.setColor(Color.WHITE);
						if(ImageColor[a][b]==1)
							g.setColor(Color.BLACK);
						g.fillRect(b, a, 1, 1);
					}
				}
			}
			timer.start();
		}
	}
	public static void main(String [] args) {
		new denemep4();
	}
}
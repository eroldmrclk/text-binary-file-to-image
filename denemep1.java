package P1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class denemep1 extends JFrame{
	private int w,h,i=0;
	private int Image [];
	private DrawingPanel dp;
	private Timer timer;
	denemep1(){
		timer=new Timer(1000,new TimerListener());
		try {
			Scanner p1= new Scanner(new File(PFile.filename));
			String magicnumber=p1.next();
			System.out.println(magicnumber);
			w=p1.nextInt();
			h=p1.nextInt();
			System.out.println(w);
			System.out.println(h);
			int pixel=w*h;
			Image=new int[pixel];
			for(int i=0;i<pixel;i++) {
				Image[i]=p1.nextInt();
			}
			dp=new DrawingPanel();
			this.add(dp);
			this.setSize(600,600);
			this.setVisible(true);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
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
			
			for(int a=0; a<w/5+(i*w/5); a++)
				for(int b=0; b<h; b++) {
					if(a<w && b<h) {
						if(Image[a*h+b]==0)
							g.setColor(Color.BLACK);
						if(Image[a*h+b]==1)
							g.setColor(Color.WHITE);
//						g.setColor(Image[a*x+b] == 0 ? Color.WHITE: Color.BLACK);
						g.fillRect(b, a, 1, 1);
					}	
				}
			timer.start();
		}
	}
	public static void main(String[] args) {
		new denemep1();
	}

}

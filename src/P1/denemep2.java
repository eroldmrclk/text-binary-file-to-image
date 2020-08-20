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

public class denemep2 extends JFrame{

	private DrawingPanel dp;
	private int w,h,i=0;
	private Color [] ImageColor;
	private Timer timer;
	denemep2(){
		timer=new Timer(1000,new TimerListener());
		try {
			dp=new DrawingPanel();
			Scanner p2= new Scanner(new File(PFile.filename));
			String magicnumber=p2.next();
			System.out.println(magicnumber);
			w=p2.nextInt();
			System.out.println(w);
			h=p2.nextInt();
			System.out.println(h);
			int t=p2.nextInt();
			System.out.println(t);
			int pixel=w*h;
			int [] Image=new int[pixel];
			for(int i=0;i<pixel;i++) 
				Image[i]=p2.nextInt();
			ImageColor=new Color[pixel];
			for(int i=0;i<pixel;i++)
				ImageColor[i]=new Color(Image[i],Image[i],Image[i]);
			this.add(dp);
			this.setSize(700,700);
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
			for(int a=0; a<(w/5)+(i*w/5); a++)
				for(int b=0; b<h; b++) {
					if(a<w && b<h) {
						g.setColor(ImageColor[a*h+b]);
						g.fillRect(b, a, 1, 1);
					}	
				}
			timer.start();

		}
	}
	public static void main(String[] args) {

		new denemep2();
	}

}
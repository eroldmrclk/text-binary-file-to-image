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

public class denemep3 extends JFrame{

	private DrawingPanel dp;
	private int w,h,i=0;
	private Color [] ImageColor;
	private Timer timer;
	denemep3(){
		timer=new Timer(1000,new TimerListener());
		try {
			dp=new DrawingPanel();
			Scanner p1= new Scanner(new File(PFile.filename));
			String magicnumber=p1.next();
			System.out.println(magicnumber);
			w=p1.nextInt();
			System.out.println(w);
			h=p1.nextInt();
			System.out.println(h);
			int t=p1.nextInt();
			System.out.println(t);
			int pixel=w*h*3;
			int [] Image=new int[pixel];
			for(int i=0;i<pixel;i++) 
				Image[i]=p1.nextInt();
			ImageColor=new Color[w*h];
			for(int i=0;i<pixel/3;i++)
				ImageColor[i]=new Color(Image[3*i],Image[3*i+1],Image[3*i+2]);
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

		new denemep3();
	}

}
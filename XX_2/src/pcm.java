import java.awt.*;
import javax.swing.*;
public class pcm {
	public static void main(String args[]){
		JFrame w=new JFrame();
		w.setSize(1366,768);
		w.setBackground(Color.BLACK);
		MyPanel mp=new MyPanel();
		mp.setBackground(Color.BLACK);
		w.add(mp);
		Thread t=new Thread(mp);
		t.start();
		w.setVisible(true);
	}
}
class MyPanel extends JPanel implements Runnable{
	final int len=400;
	public void paint(Graphics g){
		g.setColor(Color.WHITE);
		for(int i=0;i<len;i++){
			g.drawString("*",(int)(Math.random()*1366),(int)(Math.random()*768));
		}
		g.setColor(Color.BLACK);
		Font  f=new Font("窗体",Font.BOLD,40);
		g.setFont(f);
		g.drawString("雪花飘飘飘！！！",450,600);
		int x1[]={800,770,850,750,830};
		int y1[]={60,160,100,100,160};
		g.setColor(Color.BLACK);
		g.fillPolygon(x1, y1, x1.length);
		int x2[]={600,570,650,550,630};
		int y2[]={300,400,340,340,400};
		g.setColor(Color.BLACK);
		g.fillPolygon(x2, y2, x2.length);
		int x3[]={60,30,110,10,90};
		int y3[]={60,160,100,100,160};
		g.setColor(Color.BLACK);
		g.fillPolygon(x3, y3, x3.length);
		int x4[]={1300,1270,1350,1250,1330};
		int y4[]={300,400,340,340,400};
		g.setColor(Color.BLACK);
		g.fillPolygon(x4, y4, x4.length);
	}
	public void setBackground(String black) {
		// TODO Auto-generated method stub

	}
	@Override
	public void run() {
	// TODO Auto-generated method stub
		while(true){
			try{Thread.sleep(40);}
			catch(Exception e){}
			repaint();
		}
	}
	public void repaint() {
		// TODO Auto-generated method stub

	}
}

package comp.tools.paint;

import java.awt.*;
import comp.gui.*;
import exemple.Exemple;


public class StatusBar extends Panels {
	public Exemple ex;
	public ImageEdit ie;
	String info1 = "", info2 = "", info3 = "", info4 = "";
	int a = 10, b = 100, c = 200, d = 300;
	Font f = new Font("Helvetica", 0, 12);
 

    public StatusBar(ImageEdit ie) {
    	super();
		this.ie = ie;
        this.ex = ie.ex;
		setBackground(new Color(38, 104, 165));
		setCursor(ie.defaultCursor);
		setFont(f);
    }
	
	public void paint(Graphics g){
		super.paint(g); 
		update(g);
	}

	public void update(Graphics g){
		g.clearRect(4,4,600,50);
		g.setColor(Color.white);
		g.drawString(info1, a, 20);
		g.drawString(info2, b, 20);
		g.drawString(info3, c, 20);
		g.drawString(info4, d, 20);
	}	
	
	public void setInfo1(String info1){
		this.info1 = info1;
		repaint();
	}

	public void setInfo2(String info2){
		this.info2 = info2;
		repaint();
	}

	public void setInfo3(String info3){
		this.info3 = info3;
		repaint();
	}	
	
	public void setInfo4(String info4){
		this.info4 = info4;
		repaint();
	}	
     

}

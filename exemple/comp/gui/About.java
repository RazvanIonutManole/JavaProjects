package comp.gui;

import java.awt.*;
import java.awt.image.*;
import exemple.Exemple;

public class About extends Window implements Runnable{
	Exemple ex;
	Thread t = null; 
	Image im, im1, im2; 
	Graphics gr, gr1, gr2; 
	int nr=0, imW=0, imH=0;
	boolean  animatie = true;
	final int NUMAR_CADRE = 16; 
	long delay = 1000/NUMAR_CADRE;	

	public About(Exemple ex){
		super(ex);
		this.ex = ex;
		im = ex.uaic;
		imW  = im.getWidth(this);
		imH = im.getHeight(this);		
		resize(imW, 2*imH);	  
		Toolkit tool=getToolkit();
		Dimension res=tool.getScreenSize();
		move((int)((res.width-imW)/2),(int)((res.height-2*imH)/2));	
		show();
		
		gr = getGraphics();	
	
		im1 = createImage((NUMAR_CADRE + 1) * imW, imH);
		gr1 = im1.getGraphics();
		
		im2=createImage(imW, 2*imH);
		gr2=im2.getGraphics();
		gr2.drawImage(im, 0, -1, this);
		for(int i=0; i<imH; i++) gr2.copyArea(0, i, imW, 1, 0, 2*imH - 2*i - 3);		
		
		creaza_Animatia();		

		display(gr);		
		repaint();	
				
		t = new Thread(this); 
		t.start();	
	}

	public void run(){		
		while (true){
			try {
				if (animatie){
					display(gr);
					if (++nr == NUMAR_CADRE) nr = 0;
					Thread.sleep(delay);
        			}
      			}
			catch (InterruptedException e){}  
		}
		
	}

	void display(Graphics g){
		g.drawImage(im1, (-nr * imW), imH - 1, this);
	}
	
	public void paint(Graphics g){
		g.drawImage(im2,0,0,this);
	}	
	
	void creaza_Animatia(){
		Image backImg = createImage(imW, imH + 1);
		Graphics backG = backImg.getGraphics();
		backG.drawImage(im, 0, 1, this);
		for (int i = 0; i <= (imH >> 1); i++){           	
			backG.copyArea (0, i, imW, 1, 0, imH - i + 1);
			backG.copyArea (0, imH - i, imW, 1, 0, -imH + (i << 1));
			backG.copyArea (0, imH + 1, imW, 1, 0, -1 - i);
		}   	

		gr1.drawImage(backImg, NUMAR_CADRE * imW, 0, this);
		for (int i = 0; i < NUMAR_CADRE; i++) makeWaves(gr1, i);
	}
	
	void makeWaves(Graphics g, int nr) {
		double p1 = 2 * Math.PI * (double)nr / (double)NUMAR_CADRE;
		int dx = (NUMAR_CADRE - nr) * imW;
		for(int i = 0; i < imH; i++){
			int dy = (int)(((double)i/(double)NUMAR_CADRE + 2D)* Math.sin ((double)((imH/NUMAR_CADRE)*(imH - i))/(double)(i + 1)+ p1));
			if (i < -dy)
				g.copyArea(NUMAR_CADRE * imW, i, imW, 1, -dx, 0);
			else 
				g.copyArea (NUMAR_CADRE * imW, i + dy, imW, 1, -dx, -dy);		
		}
	}	

	public boolean mouseDown(Event e, int x, int y){
		dispose();
		return true;
	}

}
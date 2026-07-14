package comp.progl.vanator;

import java.util.Date;
import java.awt.*;

class Logo extends Thread{

Teren teren;
int w, h;

public boolean activ = true;

public int x, y = 0;
public long t0;
long delay = 1;

public String text = "ANUL III MI PREZINT\u0102";
int[] xx = new int[text.length()];
public int k;
int xf, yf=200;


Logo(Teren teren, int w, int h){
	this.teren = teren;
	this.w = w;
	this.h = h;
	t0 = (new Date()).getTime();
	xx[0] = (w - teren.fm.stringWidth(text))/2;
	xf = xx[0] + teren.fm.stringWidth(text);
	x = xf;
	for(int i=1; i<text.length(); i++){
		xx[i] = xx[0] + teren.fm.stringWidth(text.substring(0,i));
	}
} 


public void run(){
	while(activ){		
		if(k<text.length()){	
			y++;
			if(y==yf){
				k++;
				y = 0;
			}
			teren.repaint();
			try{Thread.sleep(delay);}
			catch(Exception e){}				
		}
		long t = (new Date()).getTime()-t0;
		if(t > 20) activ = false;
	}	
}





}
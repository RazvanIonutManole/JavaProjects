package comp.progl.vanator;

class Rata extends Thread{

Teren teren;
int w, h;
int par=1;

public boolean activ = true;

public int x, y = 700;
public long delay = 1;
public long delay1 = 1000;
int dirx, diry;


Rata(Teren teren, int w, int h){
	this.teren = teren;
	this.w = w;
	this.h = h;
	x = w;
	dirx=(int)(-1+Math.random()*3);
	diry=(-1)*(int)(1+Math.random()*2);
}

public void run(){
	while(activ){
		x+=dirx;
		if(par==1) y+=diry;
		par=1-par;
		if(x >= w || x<=0 || y<-100) {
			System.out.println("new duck");
			dirx=(int)(-1+Math.random()*3);
			diry=(-1)*(int)(1+Math.random()*2);
			x = 50+(int)(Math.random()*(w-100));
			y = 690+(int)(Math.random()*60);
			teren.nimeritRata = false;
		}
		teren.repaint();
		try{Thread.sleep(delay);}
		catch(Exception e){}	
	}
}
}
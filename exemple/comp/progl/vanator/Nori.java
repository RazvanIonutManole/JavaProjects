package comp.progl.vanator;

class Nori extends Thread{

Teren teren;
int w, h;
int nr_img = 1;

public boolean activ = true;

public int x, y = 420;
public long delay = 50;

int width[] = new int[5];


Nori(Teren teren, int w, int h){
	this.teren = teren;
	this.w = w;
	this.h = h;
	x = w;
	width[1] = 290;
	width[2] = 400;
	width[3] = 320;
	width[4] = 277;
}

public void run(){
	while(activ){
		x--;
		if(x == -width[nr_img]) {
			x = w;
			nr_img++;
			if(nr_img==5) nr_img = 1;
		}
		teren.repaint();
		try{Thread.sleep(delay);}
		catch(Exception e){return;}		
	}	
}



}
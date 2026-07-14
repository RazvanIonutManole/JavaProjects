package comp.progl.vanator;

class Iepure extends Thread{

Teren teren;
int w, h;
int nr_img = 1;

public boolean activ = true;

public int x, y = 700;
public long delay = 1;
public long delay1 = 1000;
int[] yy = new int[14];
int[] dir = new int[14];


Iepure(Teren teren, int w, int h){
	this.teren = teren;
	this.w = w;
	this.h = h;
	x = w;
	dir[1] = dir[2] = dir[3] = dir[4] = -1;
	dir[5] = dir[6] = dir[7] = dir[8] = 1;
	dir[9] = dir[10] = dir[11] = dir[12] = dir[13] = 0;
	nr_img = (int)(Math.random()*13)+1;
	if(nr_img<=4) x = 50+(int)(Math.random()*(w-100));
	else if(nr_img<=8) x = 0;
	else x = 50+(int)(Math.random()*(w-100));
	
	yy[1] = 700; yy[2] = 710; yy[3] = 690; yy[4] = 700;
	yy[5] = 720; yy[6] = 730; yy[7] = 740; yy[8] = 750;
	yy[9] = 700; yy[10] = 700; yy[11] = 700; yy[12] = 700; yy[13] = 700;
}

public void run(){
	while(activ){
		if(nr_img<=4){
			x--;
			if(x == -200) {
				x = 50+(int)(Math.random()*(w-100));
				nr_img = (int)(Math.random()*13)+1;
				teren.nimerit = false;
			}	
			teren.repaint();
			try{Thread.sleep(delay);}
			catch(Exception e){}				
		}else if(nr_img<=8){
			x++;
			if(x == w) {
				x = 50+(int)(Math.random()*(w-100));
				nr_img = (int)(Math.random()*13)+1;
				teren.nimerit = false;
			}
			teren.repaint();
			try{Thread.sleep(delay);}
			catch(Exception e){}				
		}else{
			x = 50+(int)(Math.random()*(w-100));
			teren.nimerit = false;
			teren.repaint();
			nr_img = (int)(Math.random()*13)+1;
			try{Thread.sleep(delay1);}
			catch(Exception e){}			
		}
	}	
}



}
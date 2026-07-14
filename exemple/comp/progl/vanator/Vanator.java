package comp.progl.vanator;

public class Vanator extends Thread{

Teren teren;
int w, h;

boolean direction_x = true;
boolean direction_y = true;
public boolean activ = true;

public int x = 250, y;
public long delay = 25;


Vanator(Teren teren, int w, int h){
	this.teren = teren;
	this.w = w;
	this.h = h;
	y = h - 100;
}



public void run(){
	while(activ){
		if(direction_x){
			x++;
			if(x == 650) direction_x = false;
		}else{
			x--;
			if(x == -80) direction_x = true;
		}
		if(direction_y){
			y++;
			if(y == teren.h-100+5) direction_y = false;
		}else{
			y--;
			if(y == teren.h-100-5) direction_y = true;
		}		
		teren.repaint();
		try{Thread.sleep(delay);}
		catch(Exception e){return;}		
	}	
}



}
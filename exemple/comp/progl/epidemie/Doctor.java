package comp.progl.epidemie;
import java.awt.Color;
import comp.progl.epidemie.exceptions.DangerException;
import comp.progl.epidemie.Brownian;
public class Doctor{
	public int x,y;
	Brownian brow;
	private int vaccin;
	private boolean activ=false;
	public boolean avertisment=false;
	int t=10;
	int index;
	public Doctor(Brownian brow, int vaccin, int index){
		this.brow=brow;
		this.vaccin=vaccin;
		this.index=index;
		if(vaccin>0) activ=true;
	}
	public void setPosition(){
		x+=-3+(int)(Math.random()*7);
		y+=-3+(int)(Math.random()*7);
		if(x<0)x=0;
		if(y<0)y=0;
		if(x>brow.ww)x=brow.ww;
		if(y>brow.hh)y=brow.hh;
	}
	public void vaccineaza(){
		if(vaccin>0) vaccin--;
			if(vaccin<10){
				try{danger();}
				catch(DangerException de){
				t--;
				if(t>0){
				System.out.println(de.getMessage());
				de.reactie();
				}
			}
		}
	}
	public void danger() throws DangerException{
		if(vaccin>0)avertisment=true;
		else{
			activ=false;
			throw(new DangerException(brow,index));
		}
	}
	public int getVaccin(){return vaccin;}
	public boolean isActive(){return activ;}
	public void setActive(boolean activ){this.activ=activ;}
}
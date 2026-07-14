package comp.progl.epidemie.exceptions;
import comp.progl.epidemie.Brownian;

import java.awt.*;
public class DangerException extends Exception{
	private Brownian brow;
	public DangerException(Brownian brow,int index){
	super("Doctorul "+index+" nu mai are vaccin!");
	this.brow=brow;
	}
	public void reactie(){
		brow.img.clearRect(0,0,brow.ww,brow.hh);
		brow.img.drawImage(brow.im1,(brow.ww-brow.im1.getWidth(brow))/2,(brow.hh-brow.im1.getHeight(brow))/2,brow);
		brow.repaint();
	}
}
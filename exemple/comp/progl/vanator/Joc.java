package comp.progl.vanator;

import java.awt.*;

public class Joc extends Frame{
	int w, h;
    Teren teren;
	
	public static void main(String[] args){
		new Joc();
	}
   
    public Joc(){
		Toolkit tool = getToolkit();
		Dimension res=tool.getScreenSize();
        w = res.width; h = res.height;	
		teren = new Teren(this, w, h);
    }
	

}

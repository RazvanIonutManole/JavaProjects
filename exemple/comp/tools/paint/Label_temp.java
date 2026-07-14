package comp.tools.paint;

import java.awt.*;



public class Label_temp extends Thread {
	ImageEdit ie;
    
    public Label_temp(ImageEdit ie) {
		this.ie = ie;
		start();
    } 

	public void run(){
		try{
			Thread.sleep(10000);
		}
		catch(InterruptedException e){}
		ie.label.setVisible(false);
	}    
        


}

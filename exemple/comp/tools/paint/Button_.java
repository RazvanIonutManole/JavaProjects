package comp.tools.paint;

import java.awt.*;
import java.awt.event.*;

public class Button_ extends Button implements MouseListener, MouseMotionListener{
	ToolBar tb;
    Image img;
	String name;	
        
    public Button_(ToolBar tb, Image img, String name) {
        this.tb = tb;
		this.img=img;
		this.name = name;
		setCursor(new Cursor(12));
		addMouseListener(this);
		addMouseMotionListener(this);		
    }    
    
    public void paint(Graphics g) {
		g.drawImage(img,1,1,this);
    }
	
	
	public void mouseClicked(MouseEvent e){
		
	}

	public void mouseEntered(MouseEvent e){
		if(name.equals("New")) {setLabel(tb.New_label, tb.a);return;}
		if(name.equals("Open")) {setLabel(tb.Open_label, tb.a+50);return;}
		if(name.equals("Save")) {setLabel(tb.Save_label, tb.a+2*50); return;} 			
		if(name.equals("Pen")) {setLabel(tb.Pen_label, tb.b); return;}
		if(name.equals("Peng")) {setLabel(tb.Peng_label, tb.b+50); return;}
		if(name.equals("Brush")) {setLabel(tb.Brush_label, tb.b+2*50); return;}
		if(name.equals("Fill")) {setLabel(tb.Fill_label, tb.b+3*50); return;}
		if(name.equals("Eraser")) {setLabel(tb.Eraser_label, tb.b+4*50); return;}
		if(name.equals("Stylus")) {setLabel(tb.Stylus_label, tb.c); return;}
		if(name.equals("Palette")) {setLabel(tb.Palette_label, tb.c+50); return;} 			
		if(name.equals("Fliph")) {setLabel(tb.Fliph_label, tb.d); return;} 
		if(name.equals("Flipv")) {setLabel(tb.Flipv_label, tb.d+50); return;} 
		if(name.equals("Zoomin")) {setLabel(tb.Zoomin_label, tb.e); return;} 
		if(name.equals("Zoomout")) {setLabel(tb.Zoomout_label, tb.e+50); return;} 
		if(name.equals("Move")) {setLabel(tb.Move_label, tb.e+2*50); return;}
		if(name.equals("Text")) {setLabel(tb.Text_label, tb.f); return;}
		if(name.equals("Undo")) {setLabel(tb.Undo_label, tb.g); return;}			
		if(name.equals("Redo")) {setLabel(tb.Redo_label, tb.g+50); return;}			
	}
	
	void setLabel(String label, int position){
		String s = "  " + label + "  ";
		int width = tb.ie.fm.stringWidth(s);
		tb.ie.label.setText(s);
		tb.ie.label.reshape(position+15, 88, width, 16);
		tb.ie.label.setVisible(true);
		//new Label_temp(tb.ie);
	}	

	public void mouseExited(MouseEvent e){
		tb.ie.label.setVisible(false);
	}

	public void mousePressed(MouseEvent e){
		if(name.equals("New")) {tb.newpaint(); return;}
		if(tb.New_bool){
			if(name.equals("Open")) {tb.open(); return;}
			if(name.equals("Save")) {tb.save(); return;} 			
			if(name.equals("Pen")) {tb.pen(); return;}
			if(name.equals("Peng")) {tb.peng(); return;}
			if(name.equals("Brush")) {tb.brush(); return;}
			if(name.equals("Fill")) {tb.fill(); return;}
			if(name.equals("Eraser")) {tb.eraser(); return;}
			if(name.equals("Stylus")) {tb.stylus(); return;}
			if(name.equals("Palette")) {tb.showpalette(); return;} 			
			if(name.equals("Fliph")) {tb.fliph(); return;} 
			if(name.equals("Flipv")) {tb.flipv(); return;} 
			if(name.equals("Zoomin")) {tb.zoomin(); return;} 
			if(name.equals("Zoomout")) {tb.zoomout(); return;} 
			if(name.equals("Move")) {tb.move(); return;}
			if(name.equals("Text")) {tb.addtext(); return;}
			if(name.equals("Undo")) {tb.undo(); return;}			
			if(name.equals("Redo")) {tb.redo(); return;}			
		}	
	}

	public void mouseReleased(MouseEvent e){

	}
	
	public void mouseDragged(MouseEvent e){

	}

	public void mouseMoved(MouseEvent e){

	}	

}

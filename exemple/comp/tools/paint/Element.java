package comp.tools.paint;

import java.awt.*;
import java.util.Vector;

public class Element{
    public Image image;
	public int x,y;
	public int w,h;
	public int Ox = -10000, Oy = -10000;
	public Color color;
	
	public boolean isBackgroundColor = false;
	public boolean isOval = false;
	public boolean isFillOval = false;
	public boolean isRectangle = false;
	public boolean isFillRectangle = false;	
	public boolean isBezier = false;
	
	public boolean isEraser = false;
	public boolean isBrush = false;
	public boolean isFlipv = false;
	public boolean isFliph = false;
	public boolean isFinished = false;

	public int k;
	public int[] X = new int[1000];
	public int[] Y = new int[1000];
	
    public Element(int Ox, int Oy) {
		this.Ox = Ox;
		this.Oy = Oy;
    } 	
    
    public Element(Image image) {
		this.image = image;
		w = image.getWidth(null);
		h = image.getHeight(null);
    } 

    public Element(Color color) {
		this.color = color;
    } 

    public Element(Color color, boolean isBackgroundColor) {
		this(color);
		this.isBackgroundColor = isBackgroundColor;
    }  
	
	public Element(Color color, String type) {
		this.color = color;
		if (type=="Oval") isOval=true;
		else if (type=="FillOval") isFillOval=true;
		else if (type=="Rect") isRectangle=true;
		else if (type=="FillRect") isFillRectangle=true;
		else if (type=="Bezier") isBezier=true;
		else if (type=="Eraser") isEraser=true;
		else if (type=="Brush") isBrush=true;
		else if (type=="Flipv") isFlipv=true;
    } 

    public Element() {}    	
        

}

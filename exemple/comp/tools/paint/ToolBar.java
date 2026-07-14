package comp.tools.paint;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.Vector;
import comp.gui.*;
import exemple.Exemple;
import javax.swing.JColorChooser;

public class ToolBar extends Panels {
    public Exemple ex;
	public ImageEdit ie;	

    public Button_ New;
    public Button_ Open;
    public Button_ Save;
	public Button_ Pen;
	public Button_ Peng;
	public Button_ Brush;
	public Button_ Fill;
	public Button_ Eraser;
	public Button_ Stylus;
	public Button_ Palette;	
	public Button_ Fliph;
	public Button_ Flipv;
	public Button_ Zoomin;
	public Button_ Zoomout;
	public Button_ Move;
	public Button_ Text;
	public Button_ Undo;
	public Button_ Redo;
	
    public boolean New_bool;
    public boolean Open_bool;
    public boolean Save_bool;
	public boolean Pen_bool;
	public boolean Peng_bool;
	public boolean Brush_bool;
	public boolean Fill_bool;
	public boolean Eraser_bool;
	public boolean Stylus_bool;
	public boolean Palette_bool;	
	public boolean Fliph_bool;
	public boolean Flipv_bool;
	public boolean Zoomin_bool;
	public boolean Zoomout_bool;
	public boolean Move_bool;
	public boolean Text_bool;	
	
    String New_label = "New paint";
    String Open_label = "Open an image";
    String Save_label = "Save As ..";
	String Pen_label = "Pen";
	String Peng_label = "Marker";
	String Brush_label = "Brush";
	String Fill_label = "Fill background";
	String Eraser_label = "Eraser";
	String Stylus_label = "Color picker";
	String Palette_label = "Color palette";	
	String Fliph_label = "Rectangle horizontal flip";
	String Flipv_label = "Rectangle vertical flip";
	String Zoomin_label = "Zoom in";
	String Zoomout_label = "Zoom out";
	String Move_label = "Move the workspace";
	String Text_label = "Add text";	
	String Undo_label = "Undo";	
	String Redo_label = "Redo";	
	
	public int a = 5, b = a+3*50+20, c = b+5*50+20, d = c+3*50+20, e = d+2*50+20; 
	public int f = e+3*50+20, g = f+50+20;
	
	public int red, green, blue;
	Color culoare_curenta = Color.black, old_color = Color.white;
	Panel culoare_curenta_p;
	
	public String path, dir, fileName = "";
	public Vector removed_elements = new Vector();

    public ToolBar(ImageEdit ie) {
        super(); 
		this.ie = ie;
        this.ex = ie.ex;
		setBackground(new Color(38, 104, 165));
		setCursor(ie.defaultCursor);
        setLayout(null);             
		
        New = new Button_(this, ex.newIm, "New");
		New.setBackground(new Color(38, 104, 165));
        add(New);
        New.setBounds(a, 5, 50, 50);	

        Open = new Button_(this, ex.open, "Open");  
		Open.setBackground(new Color(38, 104, 165));
        add(Open);
        Open.setBounds(a+50, 5, 50, 50);        

        Save = new Button_(this, ex.save, "Save");
		Save.setBackground(new Color(38, 104, 165));		
        add(Save);
        Save.setBounds(a+2*50, 5, 50, 50);
		
        Pen = new Button_(this, ex.pen, "Pen");
		Pen.setBackground(new Color(38, 104, 165));
        add(Pen);
        Pen.setBounds(b, 5, 50, 50);	

        Peng = new Button_(this, ex.peng, "Peng");
		Peng.setBackground(new Color(38, 104, 165));
        add(Peng);
        Peng.setBounds(b+50, 5, 50, 50);

        Brush = new Button_(this, ex.brush, "Brush");
		Brush.setBackground(new Color(38, 104, 165));
        add(Brush);
        Brush.setBounds(b+2*50, 5, 50, 50);	
		
        Fill = new Button_(this, ex.fill, "Fill");
		Fill.setBackground(new Color(38, 104, 165));
        add(Fill);
        Fill.setBounds(b+3*50, 5, 50, 50);			
		
        Eraser = new Button_(this, ex.eraser, "Eraser");
		Eraser.setBackground(new Color(38, 104, 165));
        add(Eraser);
        Eraser.setBounds(b+4*50, 5, 50, 50);			

        Stylus = new Button_(this, ex.stylus, "Stylus");
		Stylus.setBackground(new Color(38, 104, 165));
        add(Stylus);
        Stylus.setBounds(c, 5, 50, 50);

        Palette = new Button_(this, ex.palette, "Palette");
		Palette.setBackground(new Color(38, 104, 165));
        add(Palette);
        Palette.setBounds(c+50, 5, 50, 50);	
		
        culoare_curenta_p = new Panel();
		culoare_curenta_p.setBackground(culoare_curenta);
        add(culoare_curenta_p);
        culoare_curenta_p.setBounds(c+2*50, 5, 50, 50);			
		
		Fliph = new Button_(this, ex.fliph, "Fliph");
		Fliph.setBackground(new Color(38, 104, 165));
        add(Fliph);
        Fliph.setBounds(d, 5, 50, 50);

		Flipv = new Button_(this, ex.flipv, "Flipv");
		Flipv.setBackground(new Color(38, 104, 165));
        add(Flipv);
        Flipv.setBounds(d+50, 5, 50, 50);
		
		Zoomin = new Button_(this, ex.zoomin, "Zoomin");
		Zoomin.setBackground(new Color(38, 104, 165));
        add(Zoomin);
        Zoomin.setBounds(e, 5, 50, 50);

		Zoomout = new Button_(this, ex.zoomout, "Zoomout");
		Zoomout.setBackground(new Color(38, 104, 165));
        add(Zoomout);
        Zoomout.setBounds(e+50, 5, 50, 50);	

		Move = new Button_(this, ex.move, "Move");
		Move.setBackground(new Color(38, 104, 165));
        add(Move);
        Move.setBounds(e+2*50, 5, 50, 50);

		Text = new Button_(this, ex.text, "Text");
		Text.setBackground(new Color(38, 104, 165));
        add(Text);
        Text.setBounds(f, 5, 50, 50);	

		Undo = new Button_(this, ex.undo, "Undo");
		Undo.setBackground(new Color(38, 104, 165));
        add(Undo);
        Undo.setBounds(g, 5, 50, 50);

		Redo = new Button_(this, ex.redo, "Redo");
		Redo.setBackground(new Color(38, 104, 165));
        add(Redo);
        Redo.setBounds(g+50, 5, 50, 50);			
		  
        repaint();   
    }

	
	public void paint(Graphics g){
		super.paint(g);
	}

    
    public boolean handleEvent(Event event) {
		if(event.id==Event.MOUSE_DOWN){
			if(event.target == culoare_curenta_p) {changecolor(); return true;}	
		}	
    	return super.handleEvent(event);
    }
	

	void newpaint(){
		allFalse(); 
		New_bool = true;
		ie.setCursor(ie.defaultCursor);
		ie.elements.clear();
		removed_elements.clear();
		ie.elements.add(new Element(Color.white,true));
		ie.isBackgroundColors.clear();
		ie.isBackgroundColors.add(Color.white);
		ie.Ox=0; 
		ie.Oy=0;
		ie.repaint();
	}
	
	void open(){
		allFalse(); 
		Open_bool = true;
		ie.setCursor(ie.defaultCursor);
	   	try{ 	
			FileDialog fd=new FileDialog(ie, "Open a image file", 0);
			if(dir!=null) fd.setDirectory(dir);
			fd.setFile("*.png;*.jpg;*.jpeg;*.bmp;*.gif;");
			fd.setVisible(true);
			if(fd.getFile() != null) { 
				dir = fd.getDirectory();
				String fisier = fd.getFile();
				path = dir + fisier;
				try {
					Image image = null;
					try {
						MediaTracker mt = new MediaTracker(this);
						image = ex.tool.getImage(path);
						mt.addImage(image, 0);
						mt.waitForAll();
					}catch(Exception e) {System.out.println("Eroare la incarcarea imaginii!");}			Element element = new Element(image);
					ie.elements.add(element);
					ie.setCursor(ie.defaultCursor);
				}
				catch(Throwable e) {System.out.println("Eroare la incarcarea imaginii!");}
		   	}else Open_bool = false;
		}
		catch(Exception e) {e.printStackTrace();}	
	}
	
	void save(){
		allFalse(); 
		Save_bool = true;	
		ie.setCursor(ie.defaultCursor);
	   	try{ 	
			FileDialog fd=new FileDialog(ie, "Save as ..", FileDialog.SAVE);
			if(dir!=null) fd.setDirectory(dir);
			fd.setFile("*.png");
			fd.setVisible(true);
			if(fd.getFile() != null) { 
				dir = fd.getDirectory();
				String fisier = fd.getFile();
				path = dir + fisier;
				try {
					ie.bim = new BufferedImage(ie.im.getWidth(null), ie.im.getHeight(null), BufferedImage.TYPE_INT_ARGB);
					Graphics2D bimgr = ie.bim.createGraphics();
					bimgr.drawImage(ie.im, 0, 0, null);
					bimgr.dispose();
					File saveFile = new File(path);
					ImageIO.write(ie.bim, "png", saveFile);
				} catch (IOException e) {}

		   	}
		}
		catch(Exception e) {e.printStackTrace();}	
		Save_bool = false;
	}
	
	void pen(){
		allFalse(); 
		Pen_bool = true;	
		ie.setCursor(ie.penCursor);
	}	
	
	void peng(){
		allFalse(); 
		Peng_bool = true;	
		ie.setCursor(ie.pengCursor);
	}	
	
	void brush(){
		allFalse(); 
		Brush_bool = true;		
		ie.setCursor(ie.brushCursor);
	}

	void fill(){
		allFalse(); 
		Fill_bool = true;		
		ie.setCursor(ie.fillCursor);
	}

	void eraser(){
		allFalse(); 
		Eraser_bool = true;		
		ie.setCursor(ie.eraserCursor);
	}	
	
	void stylus(){
		allFalse(); 
		Stylus_bool = true;		
		ie.setCursor(ie.stylusCursor);
		ie.bim = new BufferedImage(ie.im.getWidth(null), ie.im.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D bimgr = ie.bim.createGraphics();
		bimgr.drawImage(ie.im, 0, 0, null);
		bimgr.dispose();
	}	

	void showpalette(){
		allFalse(); 
		Palette_bool = true;		
		Color selectColor = JColorChooser.showDialog(this, "Alege\u021Bi o culoare", culoare_curenta);
		if(selectColor != null){
			old_color = culoare_curenta;
			culoare_curenta = selectColor;
			red = culoare_curenta.getRed();
			green = culoare_curenta.getGreen();
			blue = culoare_curenta.getBlue();
			culoare_curenta_p.setBackground(culoare_curenta);
			ie.elements.add(new Element(culoare_curenta));
		}
	}	
	
	void changecolor(){
		Color tmp = culoare_curenta;
		culoare_curenta = old_color;
		old_color = tmp;
		red = culoare_curenta.getRed();
		green = culoare_curenta.getGreen();
		blue = culoare_curenta.getBlue();
		culoare_curenta_p.setBackground(culoare_curenta);		
	}
	
	void fliph(){
		allFalse(); 
		Fliph_bool = true;		
		ie.setCursor(ie.fliphCursor);
	}	
	
	void flipv(){
		allFalse(); 
		Flipv_bool = true;		
		ie.setCursor(ie.flipvCursor);
	}	
	
	void zoomin(){
		allFalse(); 
		Zoomin_bool = true;		
		ie.setCursor(ie.zoominCursor);
	}

	void zoomout(){
		allFalse(); 
		Zoomout_bool = true;		
		ie.setCursor(ie.zoomoutCursor);
	}	
	
	void move(){
		allFalse(); 
		Move_bool = true;		
		ie.setCursor(ie.moveCursor);
	}
	
	void addtext(){
		allFalse(); 
		Text_bool = true;
		ie.setCursor(ie.textCursor);		
	}

	void undo(){
		allFalse(); 
		ie.setCursor(ie.defaultCursor);
		if(ie.elements.size()>0){
			Element element = (Element)ie.elements.lastElement();
			removed_elements.add(element);
			ie.elements.removeElementAt(ie.elements.size()-1);
			if(element.isBackgroundColor) 
				ie.isBackgroundColors.removeElementAt(ie.isBackgroundColors.size()-1);
			if(ie.elements.size()>0){
				Element element1 = (Element)ie.elements.lastElement();
				if(element1.color!=null){
					Color tmp = culoare_curenta;
					culoare_curenta = element1.color;
					old_color = tmp;					
					culoare_curenta_p.setBackground(culoare_curenta);
				}else if(element1.Ox!=-10000){
					ie.Ox = element1.Ox;
					ie.Oy = element1.Oy;
				}
			}
		}
		if(ie.elements.size()==0) {
			New_bool = false;
			removed_elements.clear();
		}
		ie.repaint();
	}

	void redo(){
		allFalse(); 
		ie.setCursor(ie.defaultCursor);
		if(removed_elements.size()>0){
			Element element = (Element)removed_elements.lastElement();
			ie.elements.add(element);
			New_bool = true;
			if(element.color!=null){
				Color tmp = culoare_curenta;
				culoare_curenta = element.color;
				old_color = tmp;
				culoare_curenta_p.setBackground(culoare_curenta);
				if(element.isBackgroundColor) ie.isBackgroundColors.add(culoare_curenta);
			}else if(element.Ox!=-10000){
					ie.Ox = element.Ox;
					ie.Oy = element.Oy;
			}						
			removed_elements.removeElementAt(removed_elements.size()-1);
			ie.repaint();
		}		
	}	
	
	void allFalse(){
		Open_bool = false;
		Save_bool = false;
		Pen_bool = false;
		Peng_bool = false;
		Brush_bool = false;
		Fill_bool = false;
		Eraser_bool = false;
		Stylus_bool = false;
		Palette_bool = false;	
		Fliph_bool = false;
		Flipv_bool = false;
		Zoomin_bool = false;
		Zoomout_bool = false;
		Move_bool = false;
		Text_bool = false;		

	}
	        
}

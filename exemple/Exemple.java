package exemple;

import java.awt.*;
import java.awt.event.*;
import comp.gui.*;
import comp.tools.*;
import comp.tools.paint.ImageEdit;
import comp.progl.clock.Clock;
import comp.progl.snow.Snow;
import comp.progc.folder.FolderDimension;
import comp.progl.buffon.Buffon;
import comp.progl.bezier.Bezier;
import comp.progl.epidemie.*;
import comp.progl.vanator.*;
import comp.progl.X0.xo;

public class Exemple extends Frame {     
	public Image student, notepad, paint, calculator;
	public Image uaic;
	public Image newIm, open, save;
	public Image pen, pen_, peng, peng_, brush, brush_, fill, eraser, eraser_;
	public Image stylus, stylus_, palette;
	public Image fliph, flipv, zoomin, zoomout, move, text, undo, redo;
	public String appdir = System.getProperty("user.dir");
	Font f = new Font("Helvetica", Font.BOLD+Font.ITALIC, 12);
	public Toolkit tool;
        
           
public static void main (String args[]){new Exemple();}

public Exemple(){
	tool=getToolkit(); 
	Dimension res=tool.getScreenSize();
	setBackground(new Color(38, 104, 165));
	setForeground(new Color(255,255,0));
	setResizable(false);
	loadImages();
	setFont(f);
	setTitle("Exemple");
	setIconImage(student);       
	adaugaMenuBar();       	
	setLayout(null);

	Poezie poezie = new Poezie(this);
	add(poezie);
	poezie.setBounds(50, 90, 300, 260);      
	poezie.setVisible(true);
	
	//reshape((int)((res.width-400)/2-200),(int)((res.height-400)/2-100), 400, 400);
	reshape(200,200, 400, 400);
	show();
}

public boolean handleEvent(Event e){
	if(e.id==Event.WINDOW_DESTROY) System.exit(0);
	else if(e.id==Event.ACTION_EVENT && e.target instanceof MenuItem){
		if("Exit".equals(e.arg))System.exit(0);
		else if("Notepad".equals(e.arg)){
			new Notepad(this);
			return true; 
		}else if("Calculator".equals(e.arg)){
			new Calculator(this);
			return true; 
		}else if("ImageEdit".equals(e.arg)){
			new ImageEdit(this);
			return true; 			
		}else if("About".equals(e.arg)){
			new About(this);
			return true; 
		}else if("Clock".equals(e.arg)){
			new Clock();
			return true;
		}else if("Snow".equals(e.arg)){
			String[] args = {"nr_fulgi=6000", "viteza_fulgi=15"};
			try{new Snow(args, appdir);}
			catch(Exception ex){}
			return true;
		}else if("FolderDimension".equals(e.arg)){
			new FolderDimension();
			return true;
		}else if("Buffon".equals(e.arg)){
			new Buffon();
			return true;	
		}else if("Bezier".equals(e.arg)){
			new Bezier();
			return true;
		}else if("Epidemie".equals(e.arg)){
			try{new Brownian("100", "10", "10");}
			catch(Exception ex){}
			return true;
		}else if("Marele vanator".equals(e.arg)){
			new Joc();
			return true;
		}else if("X si 0".equals(e.arg)){
			new xo();
			return true;
		}else{ 
			return true;	
		}	 
	}else return false;	
	return false;
}

public void adaugaMenuBar(){
	MenuBar men=new MenuBar();
	
	Menu exemple=new Menu("Exemple");
	Menu prog=new Menu("Programe");
	Menu progl=new Menu("Programe Laborator");
	Menu progc=new Menu("Programe Curs");
	
	exemple.add("Notepad");
	exemple.add("ImageEdit");
	exemple.add("Calculator");
	exemple.add("-");
	exemple.add("About");
	exemple.add("-");
	exemple.add("Exit");

	MenuItem Ecuatie = new MenuItem("Ecuatie");
	Ecuatie.enable(false);
	MenuItem Operatori = new MenuItem("Operatori");
	Operatori.enable(false);
	MenuItem Normap = new MenuItem("Normap");
	Normap.enable(false);
	MenuItem Bezier = new MenuItem("Bezier");
	Bezier.enable(true);
	MenuItem Buffon = new MenuItem("Buffon");
	Buffon.enable(true);
	MenuItem Epidemie = new MenuItem("Epidemie");
	Epidemie.enable(true);
	MenuItem Clock = new MenuItem("Clock");
	Clock.enable(true);
	MenuItem Marele_vanator = new MenuItem("Marele vanator");
	Marele_vanator.enable(true);
	MenuItem Rime = new MenuItem("Rime");
	Rime.enable(false);
	MenuItem Snow = new MenuItem("Snow");
	Snow.enable(true);
	MenuItem X0 = new MenuItem("X si 0");
	X0.enable(true);
	
	progl.add(Ecuatie);
	progl.add(Operatori);
	progl.add(Normap);
	progl.add(Bezier);
	progl.add(Buffon);
	progl.add(Epidemie);
	progl.add(Clock);
	progl.add(Marele_vanator);
	progl.add(Rime);
	progl.add(Snow);
	progl.add(X0);
	
	MenuItem Euclid = new MenuItem("Euclid");
	Euclid.enable(false);
	MenuItem JP = new MenuItem("Joc pachete");
	JP.enable(false);	
	MenuItem Threads1 = new MenuItem("Threads1");
	Threads1.enable(false);
	MenuItem Threads2 = new MenuItem("Threads2");
	Threads2.enable(false);
	MenuItem Threads3 = new MenuItem("Threads3");
	Threads3.enable(false);
	MenuItem McDonalds = new MenuItem("McDonalds");
	McDonalds.enable(false);
	MenuItem FolderDimension = new MenuItem("FolderDimension");
	FolderDimension.enable(true);
	MenuItem Secretariat = new MenuItem("Secretariat");
	Secretariat.enable(false);
	MenuItem Secretariat_s = new MenuItem("Secretariat_s");
	Secretariat_s.enable(false);
	MenuItem Layout = new MenuItem("Layout");
	Layout.enable(false);
	
	progc.add(Euclid);
	progc.add(JP);
	progc.add(Threads1);
	progc.add(Threads2);
	progc.add(Threads3);
	progc.add(McDonalds);
	progc.add(FolderDimension);
	progc.add(Secretariat);
	progc.add(Secretariat_s);
	progc.add(Layout);
		
	prog.add(progl);
	prog.add(progc);
	
	men.add(exemple);
	men.add(prog);
	
	setMenuBar(men);	
}	



	public void loadImages(){
        try {
        	MediaTracker mt = new MediaTracker(this);  
	        student = tool.getImage(appdir + "/exemple/comp/imagini/student.png");
			notepad = tool.getImage(appdir + "/exemple/comp/imagini/notepad.png");
			paint = tool.getImage(appdir + "/exemple/comp/imagini/paint.png");
			calculator = tool.getImage(appdir + "/exemple/comp/imagini/calculator.png");
			uaic = tool.getImage(appdir + "/exemple/comp/imagini/uaic.png");						
	        newIm = tool.getImage(appdir + "/exemple/comp/imagini/new.png");  
	        open = tool.getImage(appdir + "/exemple/comp/imagini/open.png");
	        save = tool.getImage(appdir + "/exemple/comp/imagini/save.png");
			pen = tool.getImage(appdir + "/exemple/comp/imagini/pen.png");
			pen_ = tool.getImage(appdir + "/exemple/comp/imagini/pen_.png");
			peng = tool.getImage(appdir + "/exemple/comp/imagini/peng.png");
			peng_ = tool.getImage(appdir + "/exemple/comp/imagini/peng_.png");
			brush = tool.getImage(appdir + "/exemple/comp/imagini/brush.png");
			brush_ = tool.getImage(appdir + "/exemple/comp/imagini/brush_.png");
			eraser = tool.getImage(appdir + "/exemple/comp/imagini/eraser.png");
			eraser_ = tool.getImage(appdir + "/exemple/comp/imagini/eraser_.png");
			stylus = tool.getImage(appdir + "/exemple/comp/imagini/stylus.png");
			stylus_ = tool.getImage(appdir + "/exemple/comp/imagini/stylus_.png");
			palette = tool.getImage(appdir + "/exemple/comp/imagini/palette.png");			
			fliph = tool.getImage(appdir + "/exemple/comp/imagini/fliph.png");
			flipv = tool.getImage(appdir + "/exemple/comp/imagini/flipv.png");
			zoomin = tool.getImage(appdir + "/exemple/comp/imagini/zoomin.png");
			zoomout = tool.getImage(appdir + "/exemple/comp/imagini/zoomout.png");
			move = tool.getImage(appdir + "/exemple/comp/imagini/move.png");
			fill = tool.getImage(appdir + "/exemple/comp/imagini/fill.png");
			text = tool.getImage(appdir + "/exemple/comp/imagini/text.png");			
			undo = tool.getImage(appdir + "/exemple/comp/imagini/undo.png");
			redo = tool.getImage(appdir + "/exemple/comp/imagini/redo.png");
			
	        mt.addImage(student, 0);
			mt.addImage(notepad, 1);
			mt.addImage(paint, 2);
			mt.addImage(calculator, 3);
			mt.addImage(uaic, 4);					
	        mt.addImage(newIm, 5); 
	        mt.addImage(open, 6);
	        mt.addImage(save, 7);
			mt.addImage(pen, 8);
			mt.addImage(peng, 9);
			mt.addImage(brush, 10);
			mt.addImage(eraser, 11);
			mt.addImage(stylus, 12);
			mt.addImage(palette, 13);
			mt.addImage(fliph, 14);
			mt.addImage(flipv, 15);
			mt.addImage(zoomin, 16);
			mt.addImage(zoomout, 17);
			mt.addImage(move, 18);
			mt.addImage(fill, 19);
			mt.addImage(text, 20);
			mt.addImage(undo, 21);
			mt.addImage(redo, 22);
			mt.addImage(pen_, 23);
			mt.addImage(peng_, 24);
			mt.addImage(brush_, 25);
			mt.addImage(eraser_, 26);
			mt.addImage(stylus_, 27);			
			
	        mt.waitForAll();
        }
        catch(Throwable e) {System.out.println("Eroare la incarcarea imaginilor!");}
	}

}




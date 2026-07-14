package comp.progl.clock;

import java.awt.*;
import java.util.Date;
import java.net.URL;

public class Clock extends Frame implements Runnable {
	
	//culoarea fundalului, a acelor si a textului
    Color bgcolor = new Color(0,0,0);
	Color orar = new Color(60,60,60);
	Color minutar = new Color(120,120,120);
	Color secundar = new Color(255,0,0);
	Color digital = new Color(0,0,0);
	Color centru = new Color(60,60,60);
	
	//fontul folosit la ceasul digital
    Font f = new Font("Helvetica", 1, 24);
	FontMetrics fm = getFontMetrics(f);	

    Thread t;
    Image im, cim;
    Graphics imgr; 
	int w, h, wd, hd;
	
	//ora, minutul, secunda	
    int hh, mm, ss;	
	
	//lungimea secundarului, minutarului, orarului
    int rs=150, rm=120, rh=90; 
	int dh=12, dm=8; //grosimea orarului si a minutarului
	int rc = 16; //raza cercului secundar

	//(x0,y0) coord centrului ceasului, (x1,y1) coord varfului unui ac
	int x0=174, y0=174, x1, y1;
	
	//(x2,y2) unde se afiseaza ceasul digital
	int x2, y2=230;	
	
	//unghiul minutarului, orarului, secundarului
    double fim, fih, fis;	
    double PI2 = 2*Math.PI;
	
    Date d;				//data sistemului
    int delay = 100;	//nr de milisecunde intre doua redesenari
	
	int Ox = 5, Oy = 30;
	
	public static final String[] zile = {"Duminic\u0103", "Luni", "Mar\u021Bi", "Miercuri", "Joi", "Vineri", "S\u00E2mb\u0103t\u0103"};
	
   
    public Clock(){
		Toolkit tool = getToolkit();
		cim = tool.getImage(getResource("imagini/clock.png"));
		setIconImage(tool.getImage(getResource("imagini/ico.gif")));
		setBackground(bgcolor);		
		Dimension res=tool.getScreenSize();
        wd = res.width; hd = res.height;	//dimensiunile display-ului
		w = 348; h = 348; 					//dimensiunile imaginii de fundal
		int wf = w + 10, hf = h + 20;		//dimensiunile ferestrei
		setResizable(false);
		resize(wf,hf);
		move((wd-wf)/2+200,(hd-hf)/2+100); 
		show();				
		im = createImage(w, h);
		imgr = im.getGraphics();
		t = new Thread(this); 
		t.start();
    }
	
	public URL getResource(String path){
		return getClass().getResource(path);
	}	
    
    public void run(){
        while(true) {
            repaint();
            try{Thread.sleep(delay);}
            catch(Exception e){return;}
        }
    }             
	
	public void paint(Graphics g){
		update(g);
	}
	
    public void update(Graphics g){
    	
    	//obtinerea orei, minutului, secundei
        Date date = new Date();
        hh = date.getHours();
        mm = date.getMinutes();
        ss = date.getSeconds();
        
        fis = (PI2 * (double)ss) / 60D;
        fim = (PI2 * (double)mm + fis) / 60D;
        fih = (PI2 * (double)(hh % 12) + fim) / 12D;
        
        //desenarea ceasului
        imgr.setColor(bgcolor);
        imgr.fillRect(0, 0, w, h);
        imgr.drawImage(cim, 0, 0, this);
        
        //AM sau PM
        String s = "AM";
        if(hh >= 12)s = "PM";
        hh = hh % 12;
        if(hh == 0)hh = 12;
        
        //afisajul digital
        imgr.setColor(digital);
        int d1 = hh / 10;
        int d2 = hh % 10;
        int d3 = mm / 10;
        int d4 = mm % 10;
        int d5 = ss / 10;
        int d6 = ss % 10;
        imgr.setFont(f);
		String ss = d1 + "" + d2 + ":" + d3 + "" + d4 + " " + s;
		x2 = 174 - fm.stringWidth(ss)/2;
        imgr.drawString(ss, x2, y2);
		hh = date.getHours();
        d1 = hh / 10;
        d2 = hh % 10;		
		ss = d1 + "" + d2 + ":" + d3 + "" + d4 + ":" + d5 + "" + d6 + " / ";
		ss += zile[date.getDay()] + " ";
		ss += date.getDate() / 10 + "" + date.getDate() % 10 + ".";
		ss += date.getMonth() / 10 + "" + date.getMonth() % 10 + "."; 
		ss += date.getYear()+1900;
		setTitle(ss);
        
        //desenarea acelor
       
        //orarul
        x1 = (int)((double)x0 + (double)rh * Math.sin(fih));
        y1 = (int)(((double)y0 - (double)rh * Math.cos(fih)));
		imgr.setColor(orar); 
		Point[] p = puncte(new Point(x0,y0),new Point(x1,y1), (double)dh, (double)rh);
		int xm = (x0+x1)/2;
		int ym = (y0+y1)/2;
		double t = 0.5;				
		int x2 = (int)(x0+(xm-x0)*t);
		int y2 = (int)(y0+(ym-y0)*t);
		int x3 = (int)(p[0].x+(xm-p[0].x)*t);
		int y3 = (int)(p[0].y+(ym-p[0].y)*t);	
		int x4 = (int)(x1+(xm-x1)*t);
		int y4 = (int)(y1+(ym-y1)*t);		
		int x5 = (int)(p[1].x+(xm-p[1].x)*t);
		int y5 = (int)(p[1].y+(ym-p[1].y)*t);									
		int[] x = new int[10];
		int[] y = new int[10];		
		x[0] = x0; x[1] = p[0].x; x[2] = x1; x[3] = p[1].x; x[4] = x0; x[5] = x2; x[6] = x3; x[7] = x4; x[8] = x5; x[9] = x2;
		y[0] = y0; y[1] = p[0].y; y[2] = y1; y[3] = p[1].y; y[4] = y0; y[5] = y2; y[6] = y3; y[7] = y4; y[8] = y5; y[9] = y2;
		imgr.fillPolygon(x,y,10);
		imgr.setColor(Color.black); 
 		imgr.drawPolygon(x,y,10);

		//minutarul
        x1 = (int)((double)x0 + (double)rm * Math.sin(fim));
        y1 = (int)(((double)y0 - (double)rm * Math.cos(fim)));
		imgr.setColor(minutar);  
		p = puncte(new Point(x0,y0),new Point(x1,y1), (double)dm, (double)rm);		
		xm = (x0+x1)/2;
		ym = (y0+y1)/2;
		t = 0.5;				
		x2 = (int)(x0+(xm-x0)*t);
		y2 = (int)(y0+(ym-y0)*t);
		x3 = (int)(p[0].x+(xm-p[0].x)*t);
		y3 = (int)(p[0].y+(ym-p[0].y)*t);	
		x4 = (int)(x1+(xm-x1)*t);
		y4 = (int)(y1+(ym-y1)*t);		
		x5 = (int)(p[1].x+(xm-p[1].x)*t);
		y5 = (int)(p[1].y+(ym-p[1].y)*t);										
		x[0] = x0; x[1] = p[0].x; x[2] = x1; x[3] = p[1].x; x[4] = x0; x[5] = x2; x[6] = x3; x[7] = x4; x[8] = x5; x[9] = x2;
		y[0] = y0; y[1] = p[0].y; y[2] = y1; y[3] = p[1].y; y[4] = y0; y[5] = y2; y[6] = y3; y[7] = y4; y[8] = y5; y[9] = y2;
		imgr.fillPolygon(x,y,10);
		imgr.setColor(Color.black); 
 		imgr.drawPolygon(x,y,10);		
		
        //secundarul
        x1 = (int)((double)x0 + (double)rs * Math.sin(fis));
        y1 = (int)(((double)y0 - (double)rs * Math.cos(fis)));
        imgr.setColor(secundar);   
		xm = (x0+x1)/2;
		ym = (y0+y1)/2;		
		t = 0.75;
		x2 = (int)(x0+(xm-x0)*t);
		y2 = (int)(y0+(ym-y0)*t);		
		x3 = (int)(x1+(xm-x1)*t);
		y3 = (int)(y1+(ym-y1)*t);					
        imgr.drawLine(x0, y0, x2, y2);	
		imgr.drawLine(x3, y3, x1, y1);
		int r = (int)(rs*(1-t)/2.);
        imgr.drawOval(xm-r, ym-r, 2*r, 2*r);		
		
        //centrul ceasului
		imgr.setColor(centru);
        imgr.fillOval(x0 - 10, y0 - 10, 20, 20);
        imgr.setColor(Color.black);
        imgr.drawOval(x0 - 10, y0 - 10, 20, 20);
		imgr.drawOval(x0 - 9, y0 - 9, 18, 18);
		imgr.drawOval(x0 - 8, y0 - 8, 16, 16);
		imgr.drawOval(x0 - 7, y0 - 7, 14, 14);
        
        //desenarea imaginii
        g.drawImage(im, Ox, Oy, this);
    }
	
	Point[] puncte(Point p1, Point p2, double d1, double d2){	//d1=diagonala mica, d2=diagonala mare
		Point[] p = new Point[2];
		if(Math.abs(p1.y - p2.y)>10){
			int a = (int)((d1/d2)*Math.abs(p1.y-p2.y)+0.5);
			double m = -(double)(p1.x-p2.x)/(p1.y-p2.y);
			int xm = (p1.x+p2.x)/2;
			int ym = (p1.y+p2.y)/2;
			p[0] = new Point(xm+a, ym+(int)(m*a+0.5));
			p[1] = new Point(xm-a, ym-(int)(m*a+0.5));
		}else{
			int a = (int)((d1/d2)*Math.abs(p1.x-p2.x)+0.5);
			double m = -(double)(p1.y-p2.y)/(p1.x-p2.x);
			int xm = (p1.x+p2.x)/2;
			int ym = (p1.y+p2.y)/2;
			p[0] = new Point(xm+(int)(m*a+0.5), ym+a);
			p[1] = new Point(xm-(int)(m*a+0.5), ym-a);			
		}
		return p;
	}
	
	
    public boolean handleEvent(Event e){
		if(e.id==Event.WINDOW_DESTROY){dispose();}        	 	
		return super.handleEvent(e);
    }	
	
	public boolean mouseDown(Event e, int x, int y){
		dispose();
		return true;
	}	

}

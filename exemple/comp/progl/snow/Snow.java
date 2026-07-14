package comp.progl.snow;

import java.awt.*;
import java.util.*;
import java.net.*;
import java.io.*;
import javax.sound.sampled.*; 

public class Snow extends Frame implements Runnable
{
	Toolkit tool;
    Thread th;
    Image im;
    Graphics imgr;
    Random rand;
    int stopFlag;
    long stopTime;
	int snows;
    int snowX[];
    int snowY[];
	int snowS[];    
    int wind[];
    long threadSleep;
	int speed;
    Dimension dim;
    Image scena;
	int imwidth = 1280, imheight = 800;
	int maxflake = 6;
	long t0, t;
	boolean flag;
	String mesaj = "S\u0103rb\u0103tori Fericite!";
	String mesaj1 = "Limbajul JAVA v\u0103 ureaz\u0103 LA MUL\u021AI ANI!";
	String mesaj2 = "LA MUL\u021AI ANI!";
	Font f, f1;
	FontMetrics fm, fm1;
	int mesajw, mesaj1w;
	int mesajy;
	boolean tdir;
	int tx=10, tx1;
	long nr_iter, nr_iter1;
	int title_type = 1;
	String reni = "", reni1;
	Clip clip;

    public Snow(String[] args, String appdir) throws Exception{
		tool = getToolkit();
		Dimension res=tool.getScreenSize();
		setBackground(new Color(0,0,64));
		setIconImage(tool.getImage(getResource("ico.png")));
		setResizable(false); 
		resize(imwidth,imheight);
		move((res.width-imwidth)/2,(res.height-imheight)/2);
		dim = new Dimension(imwidth, imheight);
		MediaTracker mt = new MediaTracker(this);		
		scena = tool.getImage(getResource("scena.png"));
        mt.addImage(scena, 0);
        mt.waitForID(0);				
		show();	
		playSound(appdir+"\\exemple\\comp\\progl\\snow\\bsound.au", true);
        im = createImage(imwidth, imheight);
        imgr = im.getGraphics();		
		rand = new Random();
        
		String s = args[0];
        if(s == null)
            snows = 6000;
        else
            snows = Integer.valueOf(s.split("=")[1]).intValue();
		if(snows < 10)
			snows = 10;
		else
			if(snows > 10000)
				snows = 10000;
        
		s = args[1];		
		if(s == null)
            speed = 15;
        else
            speed = Integer.valueOf(s.split("=")[1]).intValue();
        if(speed < 1)
            speed = 1;
        else
			if(speed > 19)
				speed = 19;	

		threadSleep = 200 - 10*speed;	

		f = new Font("Helvetica", 3, 128);
		fm = getFontMetrics(f);
		mesajw = fm.stringWidth(mesaj);
		f1 = new Font("Helvetica", 3, 64);
		fm1 = getFontMetrics(f1);		
		mesaj1w = fm1.stringWidth(mesaj1);
				
		snowX = new int[snows];
		snowY = new int[snows];
		snowS = new int[snows];
		wind = new int[snows];
		
        for(int i = 0; i < snows; i++){
            snowX[i] = rand.nextInt() % (dim.width / 2) + dim.width / 2;
			snowY[i] = (int)(Math.random()*imheight);
			snowS[i] = (int)(Math.random()*maxflake)+1;
			
			int j = rand.nextInt() % 100;
			if(Math.abs(j) < 3)
				wind[i] = j;			
        }
		
		for(int i=0; i<80; i++) reni += "\u2744";
		reni += "\u0020\u0020\u0020\u0020\u0020\u0020\u0020";
		reni += "\uD83E\uDD8C\uD83E\uDD8C\uD83E\uDD8C\uD83E\uDD8C\uD83E\uDD8C\uD83E\uDD8C\uD83E\uDD8C  \uD83D\uDEF7";
		reni += "\u0020\u0020\u0020\u0020\u0020\u0020\u0020";
		reni1 = new String(reni);
		
		t0 = (new Date()).getTime();
		th = new Thread(this);
		th.start();
		
    }

    public void run()
    {
        while(th != null) 
            try
            {
				nr_iter++;
				t = (new Date()).getTime() - t0;
				if(t > 60000) flag = true;
				else mesajy++;
				switch(title_type){
					case 1:
						if(nr_iter%3==0){
							tx1++;
							if(tx1>100) {
								tx1=0;
								title_type = 2;
								nr_iter1 = nr_iter;
							}
							reni1 = reni1.substring(1)+"\u2744";
							if(tx1==0) reni1 = new String(reni);						
							setTitle(reni1);
						}						
						break;
					case 2:
						if(nr_iter%2==0){
							String ss = "";
							for(int i=0; i<150; i++){
								if((int)(Math.random()*2)==0)
									ss += "\u2744";
								else
									ss += "*";
							}				
							setTitle(ss);
							if(nr_iter - nr_iter1 > 300) {
								title_type = 3;
								nr_iter1 = nr_iter;
							}
						}
						break;
					case 3:
						if(nr_iter%2==0){
							if(!tdir){
								tx++;
								if(tx>50) tdir = !tdir;
							}else{
								tx--;
								if(tx<10) tdir = !tdir;					
							}					
							String ss = "";
							for(int i=0; i<tx; i++) ss += "\u2744";
							String ss1 = "";
							for(int i=0; i<80; i++) ss1 += "\u2744";				
							setTitle(ss+"     "+mesaj2+"     "+ss1);
							if(nr_iter - nr_iter1 > 500) {
								title_type = 1;
							}							
						}				
						break;
				}
                Thread.sleep(threadSleep);
                repaint();
            }
            catch(InterruptedException e)
            {
                return;
            }
    }

    public void paint(Graphics g)
    {
		update(g);
    }

    public void update(Graphics g){
		if(im!=null && imgr!=null){
			imgr.drawImage(scena, 0, 0, this);
			if(flag){
				long t1 = t/1000;
				if(t1%70>=60){
					imgr.setColor(new Color(172,236,247));
					imgr.setFont(f1);
					imgr.drawString(mesaj1, (dim.width - mesaj1w)/2, 400);					
				}
				deseneazaFulgii();				
			}else{
				imgr.setColor(new Color(133,185,219));
				imgr.setFont(f);
				imgr.drawString(mesaj, (dim.width - mesajw)/2, mesajy);
				deseneazaFulgii();
			}
			g.drawImage(im, 0, 0, null);
		}
    }

    public void deseneazaFulgii()
    {
        imgr.setColor(Color.white);
        for(int i = 0; i < snows; i++)
        {
			snowX[i] += rand.nextInt() % 2 + wind[i];
            snowY[i] += (rand.nextInt() % 6 + 5) / 5 + 1;
            if(snowX[i] >= dim.width)
                snowX[i] = 0;
            if(snowX[i] < 0)
                snowX[i] = dim.width - 1;
            if(snowY[i] >= 200+(dim.height-200)*snowS[i]/maxflake){
				snowX[i] = rand.nextInt() % (dim.width / 2) + dim.width / 2;
				snowY[i] = (int)(Math.random()*200);
				snowS[i] = (int)(Math.random()*maxflake)+1;
				int j = rand.nextInt() % 100;
				if(Math.abs(j) < 3)
					wind[i] = j;				
            }
			if(snowS[i]<=3){
				imgr.fillOval(snowX[i], snowY[i], snowS[i], snowS[i]);					
			}else{
				imgr.drawLine(snowX[i]+1, snowY[i]+1, snowX[i]+snowS[i]-1, snowY[i]+snowS[i]-1);
				imgr.drawLine(snowX[i]+snowS[i]-1, snowY[i], snowX[i], snowY[i]+snowS[i]-1);
				imgr.drawLine(snowX[i]+snowS[i]/2, snowY[i], snowX[i]+snowS[i]/2, snowY[i]+snowS[i]);
				imgr.drawLine(snowX[i], snowY[i]+snowS[i]/2, snowX[i]+snowS[i], snowY[i]+snowS[i]/2);	
			}
        }

    }
	
	public boolean handleEvent(Event e){
		if(e.id==Event.WINDOW_DESTROY){
			clip.stop(); 
			dispose();
		}
		return super.handleEvent(e);
	}

	void playSound(String nume_fisier_sunet, boolean repeta) {
		try{
			File f = new File(nume_fisier_sunet);			
			AudioInputStream ais = AudioSystem.getAudioInputStream(f); 
			clip = AudioSystem.getClip(); 
			clip.open(ais); 
			clip.loop(0); 
			clip.start(); 		
			if(repeta) clip.loop(Clip.LOOP_CONTINUOUSLY);                			
		}
		catch(Exception e){e.printStackTrace();}
	}	

	public URL getResource(String path){
		return getClass().getResource(path);
	}	

}

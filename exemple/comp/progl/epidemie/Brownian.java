package comp.progl.epidemie;

import java.awt.*;
import java.net.URL;
import comp.progl.epidemie.virus.Virus;
import comp.progl.epidemie.exceptions.DangerException;
import comp.progl.epidemie.Individ;
import comp.progl.epidemie.Doctor;


public class Brownian extends Frame implements Runnable {
    Individ[] indivizi;
    public Doctor[] doctori;
    int vaccin;
    Virus virus;
    Thread th = null;
    public int ww, hh;
    public Image im, im1;
    public Graphics img;
    int dim = 10;
    long tm = 10;
    public boolean isrun = false;
    Toolkit tool;
	Image manSafe, manInfected, manSemi;

    public static void main(String[] args) {
        try {
            new Brownian(args[0], args[1], args[2]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Nu ati introdus parametrii necesari (nr_indivizi, nr_doctori, unitati_vaccin)!");
        }
    }

    public URL getResource(String path) {
        return getClass().getResource(path);
    }

    public Brownian(String Nr_indivizi, String Nr_doctori, String Unitati_vaccin) {
		System.out.println(Nr_indivizi + " " + Nr_doctori + " " + Unitati_vaccin);
        int nr_indivizi = 500;
        int nr_doctori = 10;
        int unitati_vaccin = 100;

        try { nr_indivizi = Integer.parseInt(Nr_indivizi); } catch (NumberFormatException e) {}
        try { nr_doctori = Integer.parseInt(Nr_doctori); } catch (NumberFormatException e) {}
        try { unitati_vaccin = Integer.parseInt(Unitati_vaccin); } catch (NumberFormatException e) {}

        tool = getToolkit();
        Dimension res = tool.getScreenSize();
        ww = res.width;
        hh = res.height;

        im1 = tool.getImage(getResource("im.png"));
		
		manSafe=tool.getImage(getResource("manSafe.png"));
		manInfected=tool.getImage(getResource("manInfected.png"));
		manSemi=tool.getImage(getResource("manSemi.png"));

        indivizi = new Individ[nr_indivizi];
        for (int i = 0; i < indivizi.length; i++) {
            double b = Math.random();
            int imun = 0;
            if (b > .5) imun = (int)(Math.random() * 10);
            indivizi[i] = new Individ(this, imun);
            indivizi[i].x = (int)(Math.random() * ww);
            indivizi[i].y = (int)(Math.random() * hh);
        }

        doctori = new Doctor[nr_doctori];
        for (int i = 0; i < doctori.length; i++) {
            doctori[i] = new Doctor(this, unitati_vaccin, i);
            doctori[i].x = (int)(Math.random() * ww);
            doctori[i].y = (int)(Math.random() * hh);
        }

        setResizable(false);
        setTitle("Simularea raspandirii unei epidemii");
        setBackground(Color.black);
        setLayout(null);

        MenuBar mb = new MenuBar();
        Menu file = new Menu("File");
        file.add("Start");
        file.add("Stop");
        file.add("-");
        file.add("Exit");
        mb.add(file);
        setMenuBar(mb);

        resize(ww, hh);
        move(0, 0);
        setVisible(true);

        im = createImage(ww, hh);
        img = im.getGraphics();
    }

    public boolean handleEvent(Event e) {
        if (e.id == Event.WINDOW_DESTROY) this.dispose();
        else if (e.id == Event.ACTION_EVENT && e.target instanceof MenuItem) {
            if (e.arg.equals("Start")) {
                th = new Thread(this);
                isrun = true;
                th.start();
                return true;
            } else if (e.arg.equals("Stop")) {
                isrun = false;
                th.stop();
                th = null;
                img.clearRect(0, 0, ww, hh);
                img.setColor(Color.black);
                img.fillRect(0, 0, ww, hh);
                repaint();
                return true;
            } else if (e.arg.equals("Exit"))
                System.exit(0);
        }
        return false;
    }

    public void run() {
        lanseazaVirus();
        while (isrun) {
            img.clearRect(0, 0, ww, hh);
            img.setColor(Color.black);
            img.fillRect(0, 0, ww, hh);

            for (Individ ind : indivizi)
                if (ind.isAlive()) {
                    ind.setPosition();
                    ind.setAlive();
                }

            raspandire();

            for (Individ ind : indivizi) {
                if (ind.isAlive()) {
                    int x = ind.x;
                    int y = ind.y;

                    if (!ind.isInfected()) img.drawImage(manSafe,x-8 ,y-8 ,this); //not infected and not malign
                    else if (ind.isMalign()) img.drawImage(manSemi,x-8 ,y-8 ,this); //infected and malign
                    else img.drawImage(manInfected,x-8 ,y-8 ,this); //infected and not malign
                }
            }

            boolean exista_doctori = false;
            for (Doctor d : doctori)
                if (d.isActive()) exista_doctori = true;

            if (exista_doctori) moveDoctors();

            isrun = false;
            for (Individ ind : indivizi)
                if (ind.isAlive()) isrun = true;

            if (!isrun)
                img.drawImage(im1, (ww - im1.getWidth(this)) / 2, (hh - im1.getHeight(this)) / 2, this);

            repaint();

            try { Thread.sleep(tm); }
            catch (InterruptedException e) {}
        }
    }

    public void paint(Graphics g) { update(g); }
    public void update(Graphics g) { g.drawImage(im, 0, 0, this); }

    void raspandire() {
        for (int i = 0; i < indivizi.length; i++) {
            if (indivizi[i].isAlive()) {
                int x = indivizi[i].x;
                int y = indivizi[i].y;
                
                for (int j = i + 1; j < indivizi.length; j++) {
                    if (indivizi[j].isAlive()) {
                        int u = indivizi[j].x;
                        int v = indivizi[j].y;

                        if (Math.abs(x - u) <= dim && Math.abs(y - v) <= dim) {
                            if (!indivizi[i].isInfected() && !indivizi[j].isInfected())
                                continue;

                            if (!indivizi[i].isInfected()) {
                                indivizi[i].infect(indivizi[j].getVirus());
                                continue;
                            } else {
                                if (indivizi[j].isMalign()) {
                                    indivizi[i].decreaseImuninte();
                                    continue;
                                }
                            }

                            if (!indivizi[j].isInfected()) {
                                indivizi[j].infect(indivizi[i].getVirus());
                                continue;
                            } else {
                                if (indivizi[i].isMalign()) {
                                    indivizi[j].decreaseImuninte();
                                    continue;
                                }
                            }

                            Virus virus = indivizi[i].getVirus();
                            indivizi[i].infect(indivizi[j].getVirus());
                            indivizi[j].infect(virus);

                            if (indivizi[j].isMalign()) indivizi[i].decreaseImuninte();
                            if (indivizi[i].isMalign()) indivizi[j].decreaseImuninte();
                        }
                    }
                }
            }
        }
    }

    void moveDoctors() {
        for (Doctor d : doctori) {
            if (d.isActive()) {
                d.setPosition();
                int u = d.x;
                int v = d.y;

                img.setColor(Color.white);
                img.fillRect(u, v, 2 * dim, 2 * dim);
                img.setColor(Color.red);
                img.drawLine(u+5, v+dim, u+2*dim-5, v+dim);
                img.drawLine(u+5, v+dim+1, u+2*dim-5, v+dim+1);
                img.drawLine(u+dim, v+5, u+dim, v+2*dim-5);
                img.drawLine(u+dim+1, v+5, u+dim+1, v+2*dim-5);

                for (Individ ind : indivizi) {
                    if (ind.isAlive()) {
                        int x = ind.x;
                        int y = ind.y;

                        if (Math.abs(x - u) <= 2 * dim && Math.abs(y - v) <= 2 * dim && ind.isInfected()) {
                            d.vaccineaza();
                            if (!ind.isVaccinated()) ind.vaccin();
                        }
                    }
                }

                img.setColor(Color.white);
                if (d.avertisment)
                    img.drawString("Atentie! Mai sunt " + d.getVaccin() + " doze de vaccin!", u + 2 * dim, v + 2 * dim);
            }
        }
    }

    void lanseazaVirus() {
        Virus virus = new Virus("Gripa Galactica");
        int index = (int)(Math.random() * indivizi.length);
        indivizi[index].infect(virus);
	}
}

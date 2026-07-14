package comp.progl.epidemie;
import java.util.Date;
import comp.progl.epidemie.virus.Virus;
import comp.progl.epidemie.Brownian;

public class Individ {
    public int x, y;
    private Virus virus;
    private boolean infected = false;
    private boolean vaccinated = false;
    private int imun;
    private boolean alive = true;
    private long t0, prag1, prag2;
    Brownian brow;

    public Individ(Brownian brow, int imun) {
        this.brow = brow;
        this.imun = imun;
        prag1 = (int)(Math.random() * 120000);
        prag2 = (int)(Math.random() * 240000);
    }

    public void setPosition() {
        x += -3 + (int)(Math.random() * 7);
        y += -3 + (int)(Math.random() * 7);
        if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (x > brow.ww) x = brow.ww;
        if (y > brow.hh) y = brow.hh;
    }

    public void infect(Virus virus) {
        this.virus = virus;
        this.infected = true;
        t0 = (new Date()).getTime();
    }

    public Virus getVirus() { return virus; }
    public boolean isInfected() { return infected; }
    public boolean isVaccinated() { return vaccinated; }

    public void vaccin() {
        vaccinated = true;
        t0 = (new Date()).getTime();
    }

    public int getImunite() { return imun; }
    public boolean isImun() { return imun > 0; }
    public boolean isMalign() { return imun == 0; }
    public boolean isAlive() { return alive; }

    public void setAlive() {
        if (imun == 0) {
            long t = (new Date()).getTime() - t0;
            if (t > prag1) alive = false;
        } else if (vaccinated) {
            long t = (new Date()).getTime() - t0;
            if (t > prag2) imun += 10;
        }

        if (imun > 15) {
            infected = false;
            virus = null;
        }
    }

    public void decreaseImuninte() {
        if (imun > 0) imun--;
    }
}
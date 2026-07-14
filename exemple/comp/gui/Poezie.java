package comp.gui;

import java.awt.*;
import exemple.Exemple;

public class Poezie extends Panels implements Runnable {
	Exemple ex;
    Thread th;
    Font f = new Font("Helvetica", 1, 12);
	FontMetrics fm = getFontMetrics(f);
    int i=0, j=0, k=0, jj=1;  
	int left;
	String pt,pa;
	int leftp;
	String[][] p = new String[11][9];
	String qt,qa;
	int leftq;
	String[][] q = new String[3][14];
	String rt,ra;
	int leftr;
	String[][] r = new String[10][14];
	
	int nr_poezii = 3, poezie_curenta = 1;
	boolean schimba_strofa = false;
	boolean schimba_poezia = false;
	boolean start;

    public Poezie(Exemple ex) {
    	super();
		this.ex = ex;
        setFont(f); 

		pt = "Gloss\u0103";
		pa = "        de Mihai Eminescu";
		leftp = (320 - fm.stringWidth(pt))/2;

		p[1][1] = "Vreme trece, vreme vine,  ";
		p[1][2] = "Toate-s vechi \u015Fi nou\u0103 toate;  ";
		p[1][3] = "Ce e r\u0103u \u015Fi ce e bine  ";
		p[1][4] = "Tu te-ntreab\u0103 \u015Fi socoate;  ";
		p[1][5] = "Nu spera \u015Fi nu ai team\u0103,  ";
		p[1][6] = "Ce e val ca valul trece;  ";
		p[1][7] = "De te-ndeamn\u0103, de te cheam\u0103,  ";
		p[1][8] = "Tu r\u0103m\u00E2i la toate rece.  ";

		p[2][1] = "Multe trec pe dinainte,  ";
		p[2][2] = "\u00CEn auz ne sun\u0103 multe,  ";
		p[2][3] = "Cine \u021Bine toate minte  ";
		p[2][4] = "\u015Fi ar sta s\u0103 le asculte ?...  ";
		p[2][5] = "Tu a\u015Feaz\u0103-te deoparte,  ";
		p[2][6] = "Reg\u0103sindu-te pe tine,  ";
		p[2][7] = "C\u00E2nd cu zgomote de\u015Farte  ";
		p[2][8] = "Vreme trece, vreme vine.  ";

		p[3][1] = "Nici \u00EEncline a ei limb\u0103  ";
		p[3][2] = "Recea cump\u0103n-a g\u00E2ndirii  ";
		p[3][3] = "\u00CEnspre clipa ce se schimb\u0103  ";
		p[3][4] = "Pentru masca fericirii,  ";
		p[3][5] = "Ce din moartea ei se na\u015Fte  ";
		p[3][6] = "\u015Fi o clip\u0103 \u021Bine poate;  ";
		p[3][7] = "Pentru cine o cunoa\u015Fte  ";
		p[3][8] = "Toate-s vechi \u015Fi nou\u0103 toate.  ";

		p[4][1] = "Privitor ca la teatru  ";
		p[4][2] = "Tu \u00EEn lume s\u0103 te-nchipui:  ";
		p[4][3] = "Joace unul \u015Fi pe patru,  ";
		p[4][4] = "Totu\u015Fi tu ghici-vei chipu-i,  ";
		p[4][5] = "\u015Fi de pl\u00E2nge, de se ceart\u0103,  ";
		p[4][6] = "Tu \u00EEn col\u021B petreci \u00EEn tine  ";
		p[4][7] = "\u015Fi-n\u021Belegi din a lor art\u0103  ";
		p[4][8] = "Ce e r\u0103u \u015Fi ce e bine.  ";

		p[5][1] = "Viitorul \u015Fi trecutul  ";
		p[5][2] = "Sunt a filei dou\u0103 fe\u021Be,  ";
		p[5][3] = "Vede-n cap\u0103t \u00EEnceputul  ";
		p[5][4] = "Cine \u015Ftie s\u0103 le-nve\u021Be;  ";
		p[5][5] = "Tot ce-a fost ori o s\u0103 fie  ";
		p[5][6] = "\u00CEn prezent le-avem pe toate,  ";
		p[5][7] = "Dar de-a lor z\u0103d\u0103rnicie  ";
		p[5][8] = "Te \u00EEntreab\u0103 \u015Fi socoate.  ";

		p[6][1] = "C\u0103ci acelora\u015Fi mijloace  ";
		p[6][2] = "Se supun c\u00E2te exist\u0103,  ";
		p[6][3] = "\u015Fi de mii de ani \u00EEncoace  ";
		p[6][4] = "Lumea-i vesel\u0103 \u015Fi trist\u0103;  ";
		p[6][5] = "Alte m\u0103\u015Fti, aceea\u015Fi pies\u0103,  ";
		p[6][6] = "Alte guri, aceea\u015Fi gam\u0103,  ";
		p[6][7] = "Am\u0103git at\u00E2t de-adese  ";
		p[6][8] = "Nu spera \u015Fi nu ai team\u0103.  ";

		p[7][1] = "Nu spera c\u00E2nd vezi mi\u015Feii  ";
		p[7][2] = "La izb\u00E2nd\u0103 f\u0103c\u00E2nd punte,  ";
		p[7][3] = "Te-or \u00EEntrece n\u0103t\u0103r\u0103ii,  ";
		p[7][4] = "De ai fi cu stea \u00EEn frunte;  ";
		p[7][5] = "Team\u0103 n-ai, c\u0103ta-vor iar\u0103\u015Fi  ";
		p[7][6] = "\u00CEntre d\u00E2n\u015Fii s\u0103 se plece,  ";
		p[7][7] = "Nu te prinde lor tovar\u0103\u015F:  ";
		p[7][8] = "Ce e val, ca valul trece.  ";

		p[8][1] = "Cu un c\u00E2ntec de siren\u0103,  ";
		p[8][2] = "Lumea-ntinde lucii mreje;  ";
		p[8][3] = "Ca s\u0103 schimbe-actorii-n scen\u0103,  ";
		p[8][4] = "Te mome\u015Fte \u00EEn v\u00E2rteje;  ";
		p[8][5] = "Tu pe-al\u0103turi te strecoar\u0103,  ";
		p[8][6] = "Nu b\u0103ga nici chiar de seam\u0103,  ";
		p[8][7] = "Din c\u0103rarea ta afar\u0103  ";
		p[8][8] = "De te-ndeamn\u0103, de te cheam\u0103.  ";

		p[9][1] = "De te-ating, s\u0103 feri \u00EEn laturi,  ";
		p[9][2] = "De hulesc, s\u0103 taci din gur\u0103;  ";
		p[9][3] = "Ce mai vrei cu-a tale sfaturi,  ";
		p[9][4] = "Dac\u0103 \u015Ftii a lor m\u0103sur\u0103;  ";
		p[9][5] = "Zic\u0103 to\u021Bi ce vor s\u0103 zic\u0103,  ";
		p[9][6] = "Treac\u0103-n lume cine-o trece;  ";
		p[9][7] = "Ca s\u0103 nu-ndr\u0103ge\u015Fti nimic\u0103,  ";
		p[9][8] = "Tu r\u0103m\u00E2i la toate rece.  ";

		p[10][1] = "Tu r\u0103m\u00E2i la toate rece,  ";
		p[10][2] = "De te-ndeamn\u0103, de te cheam\u0103;  ";
		p[10][3] = "Ce e val, ca valul trece,  ";
		p[10][4] = "Nu spera \u015Fi nu ai team\u0103;  ";
		p[10][5] = "Te \u00EEntreab\u0103 \u015Fi socoate  ";
		p[10][6] = "Ce e r\u0103u \u015Fi ce e bine;  ";
		p[10][7] = "Toate-s vechi \u015Fi nou\u0103 toate:  ";
		p[10][8] = "Vreme trece, vreme vine.  ";

		//////////////////////////////////////////////////

		qt = "Pentru cine ?";
		qa = "        de Costache Ioanid";
		leftq = (320 - fm.stringWidth(qt))/2;

		q[1][1] = "Omule cu mintea plin\u0103,  ";
		q[1][2] = "du-te-o clip\u0103 \u00EEn gr\u0103din\u0103,  ";
		q[1][3] = "treci prin v\u0103ile-\u00EEnflorite  ";
		q[1][4] = "\u0219i te uit\u0103 la sulfin\u0103,  ";
		q[1][5] = "la bujori, la m\u0103rg\u0103rite,  ";
		q[1][6] = "la conduri, la inimioare,  ";
		q[1][7] = "la gherghina din c\u0103rare,  ";
		q[1][8] = "la n\u0103frama de m\u0103tase  ";
		q[1][9] = "ce ne-o d\u0103ruie ciclama !  ";
		q[1][10] = "Las\u0103-te s\u0103 te desfete  ";
		q[1][11] = "ochii-ad\u00E2nci de violete,  ";
		q[1][12] = "clopo\u0163eii cu trei cupe  ";
		q[1][13] = "\u0219i narcisele trompete !  ";

		q[2][1] = "\u0218i ia seama !  ";
		q[2][2] = "Dac\u0103 inima-\u0163i r\u0103m\u00E2ne  ";
		q[2][3] = "ca un bulg\u0103r, ca o piatr\u0103,  ";
		q[2][4] = "ca un bolovan \u00EEn vatr\u0103 ...  ";
		q[2][5] = "Leap\u0103d-o !  ";
		q[2][6] = "Zdrobe\u0219te-o-n cioburi !  ";
		q[2][7] = "Sparge-o pe il\u0103u cu dalta !...  ";
		q[2][8] = "\u0219i apleac\u0103-te \u00EEn rug\u0103  ";
		q[2][9] = "s\u0103 ceri alta !  ";	

		//////////////////////////////////////////////////

		rt = "Rapsodii de prim\u0103var\u0103";
		ra = "        de George Top\u00E2rceanu";	
		leftr = (320 - fm.stringWidth(rt))/2;

		r[1][1] = "Sus prin cr\u00E2ngul adormit,  ";
		r[1][2] = "A trecut \u00EEn tain\u0103 mare,  ";
		r[1][3] = "De cu noapte, risipind  ";
		r[1][4] = "\u0218iruri de m\u0103rg\u0103ritare  ";
		r[1][5] = "Din panere de argint,  ";
		r[1][6] = "Stol b\u0103lai  ";
		r[1][7] = "De \u00EEngera\u0219i,  ";
		r[1][8] = "Cu alai  ";
		r[1][9] = "De topora\u0219i.  ";
		r[1][10] = "Prim\u0103var\u0103, cui le dai ?  ";
		r[1][11] = "Prim\u0103var\u0103, cui le la\u0219i ?  ";

		r[2][1] = "Se-nal\u021B\u0103 abur moale din gr\u0103din\u0103.  ";
		r[2][2] = "Pe jos, pornesc furnicile la drum.  ";
		r[2][3] = "Acoperi\u0219uri ve\u0219tede-n lumin\u0103  ";
		r[2][4] = "\u00CEntind spre cer ogeacuri f\u0103r\u0103 fum.  ";
		r[2][5] = "Pe l\u00E2ng\u0103 garduri s-a zv\u00E2ntat p\u0103m\u00E2ntul  ";
		r[2][6] = "\u0218i ies g\u00E2ndacii-Domnului pe zid.  ";
		r[2][7] = "Ferestre amor\u021Bite se deschid,  ";
		r[2][8] = "S\u0103 intre-n cas\u0103 soarele \u0219i v\u00E2ntul.  ";

		r[3][1] = "De prin balcoane  ";
		r[3][2] = "\u0218i coridoare  ";
		r[3][3] = "Albe tulpane  ";
		r[3][4] = "F\u00E2lf\u00E2ie-n soare.  ";
		r[3][5] = "Ies gospodinele  ";
		r[3][6] = "Iu\u021Bi ca albinele,  ";
		r[3][7] = "P\u0103rul le flutur\u0103,  ";
		r[3][8] = "Toate dau zor.  ";
		r[3][9] = "Unele m\u0103tur\u0103,  ";
		r[3][10] = "Altele scutur\u0103  ";
		r[3][11] = "Colbul din p\u0103tur\u0103  ";
		r[3][12] = "\u0218i din covor.  ";

		r[4][1] = "Un zarz\u0103r mic, \u00EEn mijlocul gr\u0103dinii,  ";
		r[4][2] = "\u0218i-a r\u0103sfirat crengu\u021Bele ca spinii  ";
		r[4][3] = "De fric\u0103 s\u0103 nu-i cad\u0103 la picioare,  ";
		r[4][4] = "Din cre\u0219tet, v\u0103lul sub\u021Birel de floare.  ";
		r[4][5] = "C\u0103 s-a trezit a\u0219a de diminea\u021B\u0103  ";
		r[4][6] = "Cu ramuri albe – \u0219i se poate spune  ";
		r[4][7] = "C\u0103-i pentru-nt\u00E2ia oar\u0103 \u00EEn via\u021B\u0103  ";
		r[4][8] = "C\u00E2nd i se-nt\u00E2mpl\u0103-asemenea minune.  ";

		r[5][1] = "Un nor sihastru  ";
		r[5][2] = "\u0218i-adun\u0103-n poal\u0103  ";
		r[5][3] = "Argintul tot.  ";
		r[5][4] = "Cerul e-albastru  ";
		r[5][5] = "Ca o petal\u0103  ";
		r[5][6] = "De miozot.  ";

		r[6][1] = "Soare crud \u00EEn liliac,  ";
		r[6][2] = "Zbor sub\u021Bire de g\u00E2ndac,  ";
		r[6][3] = "Glasuri mici  ";
		r[6][4] = "De r\u00E2ndunici,  ";
		r[6][5] = "Viorele \u0219i urzici…  ";

		r[7][1] = "Prim\u0103var\u0103, din ce rai  ";
		r[7][2] = "Nevisat de p\u0103m\u00E2nteni  ";
		r[7][3] = "Vii cu m\u00E2ndrul t\u0103u alai  ";
		r[7][4] = "Peste cr\u00E2nguri \u0219i poieni?  ";
		r[7][5] = "Pogor\u00E2t\u0103 pe p\u0103m\u00E2nt  ";
		r[7][6] = "\u00CEn m\u0103t\u0103suri lungi de v\u00E2nt,  ";
		r[7][7] = "La\u0219i \u00EEn urm\u0103, pe c\u00E2mpii,  ";
		r[7][8] = "Galbeni vii  ";
		r[7][9] = "De p\u0103p\u0103dii,  ";
		r[7][10] = "B\u0103l\u021Bi albastre \u0219i-nsorite  ";
		r[7][11] = "De om\u0103t topit abia,  ";
		r[7][12] = "\u0218i pe dealuri mucezite  ";
		r[7][13] = "Ar\u0103turi de catifea.  ";

		r[8][1] = "\u0218i porne\u0219ti departe-n sus  ";
		r[8][2] = "Dup\u0103 iarna ce s-a dus,  ";
		r[8][3] = "Dup\u0103 trena-i de ninsori  ";
		r[8][4] = "A\u0219ternut\u0103 pe colini…  ";
		r[8][5] = "Drumuri nalte de cocori,  ";
		r[8][6] = "C\u0103l\u0103uzii cei str\u0103ini,  ";
		r[8][7] = "\u00CE\u021Bi \u00EEndreapt\u0103 an cu an  ";
		r[8][8] = "Pasul tainic \u0219i te mint  ";
		r[8][9] = "Spre \u021Binutul diafan  ";
		r[8][10] = "Al c\u00E2mpiilor de-argint.  ";
		r[8][11] = "Iar acolo te opre\u0219ti  ";

		r[9][1] = "\u0218i doar pasul t\u0103u u\u0219or,  ";
		r[9][2] = "\u00CEn om\u0103t str\u0103lucitor,  ";
		r[9][3] = "Las\u0103 urme viorii  ";
		r[9][4] = "De conduri \u00EEmp\u0103r\u0103te\u0219ti  ";
		r[9][5] = "Peste albele stihii…  ";
		r[9][6] = "Prim\u0103var\u0103, unde e\u0219ti ?  ";

		
		th = new Thread(this);
		th.start();		
    }
	
 
    
    public void run(){  
		try{Thread.sleep(2000);}
		catch(InterruptedException e){}
		start = true;
		repaint();
		try{Thread.sleep(2000);}
		catch(InterruptedException e){}		
		while(true){
			switch(poezie_curenta){
				case 1:
					for(i=1; i<q.length; i++){
						for(k=1; k<q[i].length; k++){
							if(q[i][k]==null)break;
							jj=1;
							for(j=jj; j<q[i][k].length(); j++){
								repaint();
								try{Thread.sleep(100);}
								catch(InterruptedException e){} 
							} 
						}
						try{Thread.sleep(3000);}
						catch(InterruptedException e){} 
						schimba_strofa = true;
					}
					break;				
				case 2:
					for(i=1; i<p.length; i++){
						for(k=1; k<p[i].length; k++){
							jj=1;
							for(j=jj; j<p[i][k].length(); j++){
								repaint();
								try{Thread.sleep(100);}
								catch(InterruptedException e){} 
							} 
						}
						try{Thread.sleep(3000);}
						catch(InterruptedException e){} 
						schimba_strofa = true;
					}
					break;
				case 3:
					for(i=1; i<r.length; i++){
						for(k=1; k<r[i].length; k++){
							if(r[i][k]==null)break;
							jj=1;
							for(j=jj; j<r[i][k].length(); j++){
								repaint();
								try{Thread.sleep(100);}
								catch(InterruptedException e){} 
							} 
						}
						try{Thread.sleep(3000);}
						catch(InterruptedException e){} 
						schimba_strofa = true;
					}
					break;
					
			}
			poezie_curenta++;
			if(poezie_curenta==nr_poezii+1)poezie_curenta=1;
			schimba_poezia = true;
			i = k = 1; j = 0;
			repaint(); 				
			try{Thread.sleep(3000);}
			catch(InterruptedException e){}
    	}	
    }


    public void paint(Graphics g){super.paint(g); update(g);}
    
    
    public void update(Graphics g){
		if(start){
			g.setColor(Color.white);
			if(schimba_poezia){
				super.paint(g);
				schimba_poezia = false;
			}
			if(schimba_strofa){
				g.clearRect(5,60,290,190);
				schimba_strofa = false;
			}			
			switch(poezie_curenta){
				case 1:
					left = 80;
					g.drawString(qt, left, 30);
					g.drawString(qa, left, 44);
					if(k<q[i].length && 0<j && j<q[i][k].length()) g.drawString(q[i][k].substring(0, j), left, 72+14*(k-1));
					break;				
				case 2:
					left = 80;
					g.drawString(pt, left, 30);
					g.drawString(pa, left, 44);
					if(k<p[i].length && 0<j && j<p[i][k].length()) g.drawString(p[i][k].substring(0, j), left, 86+14*(k-1));
					break;
				case 3:
					left = 60;
					g.drawString(rt, left, 30);
					g.drawString(ra, left, 44);
					if(k<r[i].length && 0<j && j<r[i][k].length()) g.drawString(r[i][k].substring(0, j), left, 72+14*(k-1));
					break;	
					
			}
		}
    } 




}
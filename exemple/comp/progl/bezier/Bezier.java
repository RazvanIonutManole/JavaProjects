package comp.progl.bezier;

import java.awt.*;

public class Bezier extends Frame{
	Point[] cp;
	int nr_puncte_control;
	int nr_puncte;
	Image im;
	Graphics img;
	double k=0.025;
	int moveflag=nr_puncte_control +1;
	Button restart;
	TextField tf;
	int w,h;
	public static void main(String[] args){
		new Bezier();
	}
	
	public Bezier(){
		Dimension res=getToolkit().getScreenSize();
		setBackground(Color.white);
		setLayout(null);
		restart=new Button("Restart");
		restart.setBounds(50,50,50,25);
		add(restart);
		tf=new TextField("4");
		tf.setBounds(125,50,50,25);
		add(tf);
		tf.enable(true);
		try{nr_puncte_control=Integer.parseInt(tf.getText());}
		catch(NumberFormatException e){nr_puncte_control=4;}
		setTitle("Deseneaza o curba bezier cu "+nr_puncte_control+"puncte de control");
		w=res.width;
		h=res.height;
		resize(w,h);
		move(0,0);
		show();
		im=createImage(w,h);
		img=im.getGraphics();
		cp=new Point[nr_puncte_control];
	}
	public void update(Graphics g){paint(g);}
	
	public void paint(Graphics g){
		setBackground(Color.white);
		img.setColor(Color.black);
		img.clearRect(0,0,size().width,size().height);
		for(int i=0;i<nr_puncte;i++){
			img.setColor(Color.red);
			img.fillRect(cp[i].x-3,cp[i].y-3,6,6);
			img.setColor(Color.blue);
			if(nr_puncte>1 && i<(nr_puncte-1)){
				img.drawLine(cp[i].x,cp[i].y,cp[i+1].x,cp[i+1].y);
			}
		}
		if(nr_puncte==nr_puncte_control){
			int x=cp[0].x, y=cp[0].y;
			img.setColor(Color.black);
			for(double t=k;t<=1+k;t+=k){
				Point p=Bernstein(cp, t);
				img.drawLine(x,y,p.x,p.y);
				x=p.x;
				y=p.y;
			}
		}
		g.drawImage(im,0,0,this);
	}
		static long binomialCoefficient(int n, int k) {
		long result = 1;
		for (int i = 1; i <= k; i++) {
			result = result * (n - i + 1) / i;
		}
		return result;
	}
	public Point Bernstein(Point[] pList, double t){
		//double x=(1-t)*(1-t)*(1-t)*p1.x + (3*t)*(1-t)*(1-t)*p2.x + (3*t*t)*(1-t)*p3.x + t*t*t*p4.x;
		//double y=(1-t)*(1-t)*(1-t)*p1.y + (3*t)*(1-t)*(1-t)*p2.y + (3*t*t)*(1-t)*p3.y + t*t*t*p4.y;
		double x = 0, y=-0;
		int n = pList.length;
		for (int i = 0; i < n; i++) {
			double binomial = binomialCoefficient(n - 1, i);
			double term = binomial * Math.pow(1 - t, n - 1 - i) * Math.pow(t, i);
			x += term * pList[i].x;
			y += term * pList[i].y;
		}
		return new Point((int)Math.round(x),(int)Math.round(y));
	}
	public boolean action(Event e, Object o){
		if (e.target==restart){
			nr_puncte=0;
			try {
				nr_puncte_control=Integer.parseInt(tf.getText());
			} catch (NumberFormatException e1) {
				nr_puncte_control=4;
				tf.setText("4");
			}
			if (nr_puncte_control<4 || nr_puncte_control>30){
				nr_puncte_control=4;
				tf.setText("4");
			}
			cp = new Point[nr_puncte_control];
			repaint();
			return true;
		}
		return false;
	}
	public boolean mouseDown(Event evt, int x, int y){
		Point point=new Point(x,y);
		if(nr_puncte<nr_puncte_control){
			cp[nr_puncte]=point;
			nr_puncte++;
			repaint();
		}else{
			for(int i=0;i<nr_puncte;i++)
				for (int j=-3;j<=3;j++)
					for (int l=-3;l<=3;l++)
						if(point.equals(new Point(cp[i].x+j,cp[i].y+l))) moveflag=i;
		}
		return true;
	}
	public boolean mouseDrag(Event evt, int x, int y){
		if(moveflag<nr_puncte){
			cp[moveflag].move(x,y);
			repaint();
		}
		return true;
	}
	public boolean mouseUp(Event evt, int x, int y){
		moveflag=nr_puncte_control+1;
		return true;
	}
	public boolean handleEvent(Event e){
		if(e.id==Event.WINDOW_DESTROY){
			this.dispose();
		}
		return super.handleEvent(e);
	}
}
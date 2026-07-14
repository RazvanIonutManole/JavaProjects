package comp.tools.paint;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import exemple.Exemple;

public class ImageEdit extends Frame implements MouseListener, MouseMotionListener{
	Exemple ex;
	public ToolBar tb;
	public StatusBar sb;
	public Cursor blankCursor, defaultCursor, moveCursor, zoominCursor, zoomoutCursor;
	public Cursor penCursor, pengCursor, brushCursor, fillCursor, eraserCursor;
	public Cursor flipvCursor, fliphCursor, stylusCursor, textCursor;

	int imwidth = 6000, imheight = 4000;
	public Image im;
	public Graphics imgr;
	public BufferedImage bim;
	public int w, h;
	public int Ox=0, Oy=0, xc=0, yc=0;
	boolean init = true;
	public Vector elements = new Vector();
	public Vector isBackgroundColors = new Vector();
	public boolean showim;
	public Label label;
	Font f = new Font("TimesRoman", 0, 12);
	public FontMetrics fm = getFontMetrics(f);
	
	public ImageEdit(Exemple ex){
		this.ex = ex;
		setTitle("Paint");
		setBackground(new Color(38, 104, 165));
		setFont(new Font("TimesRoman", Font.BOLD, 12));
		setForeground(new Color(255,255,255));		
		loadCursors();		
		setCursor(defaultCursor);
		setIconImage(ex.paint);
		
		isBackgroundColors.add(Color.white);

		setLayout(null);		
		Dimension dim = getSize();
		
		label = new Label();
		label.setBackground(new Color(242, 231, 191));
		label.setForeground(Color.black);
		label.setFont(f);
		add(label);
		label.setBounds(15, 88, 100, 16);
		label.setVisible(false);

	   	tb = new ToolBar(this);
		add(tb);
		tb.setBounds(5, 30, 3000, 58);
		
		sb = new StatusBar(this);
		add(sb);
		sb.setBounds(5, dim.height-36, 3000, 58); 
				
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				Dimension dim = getSize();
				sb.setBounds(5, dim.height-53, 3000, 50); 
			}			
        });	
		
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
				dispose();
            }                               
        });
		
		addMouseListener(this);
		addMouseMotionListener(this);
  
		Toolkit tool=getToolkit();
		Dimension res=tool.getScreenSize();
		resize(400,400);
		move((int)((res.width-400)/2+200),(int)((res.height-400)/2+100));
		show();
	}
	
void loadCursors(){
	BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
	blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blankCursor");
	defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);	
	penCursor = Toolkit.getDefaultToolkit().createCustomCursor(ex.pen_, new Point(0, 0), "penCursor");
	pengCursor = Toolkit.getDefaultToolkit().createCustomCursor(ex.peng_, new Point(0, 0), "pengCursor");
	brushCursor = Toolkit.getDefaultToolkit().createCustomCursor(ex.brush_, new Point(0, 0), "brushCursor");
	fillCursor = Toolkit.getDefaultToolkit().createCustomCursor(ex.fill, new Point(0, 0), "fillCursor");eraserCursor = Toolkit.getDefaultToolkit().createCustomCursor(ex.eraser_, new Point(0, 0), "eraserCursor");
	flipvCursor = Toolkit.getDefaultToolkit().createCustomCursor(ex.flipv, new Point(0, 0), "flipvCursor");
	fliphCursor = Toolkit.getDefaultToolkit().createCustomCursor(ex.fliph, new Point(0, 0), "fliphCursor");
	moveCursor = Toolkit.getDefaultToolkit().createCustomCursor(ex.move, new Point(0, 0), "moveCursor");
	zoominCursor = Toolkit.getDefaultToolkit().createCustomCursor(ex.zoomin, new Point(0, 0), "zoominCursor");
	zoomoutCursor = Toolkit.getDefaultToolkit().createCustomCursor(ex.zoomout, new Point(0, 0), "zoomoutCursor");
	stylusCursor = Toolkit.getDefaultToolkit().createCustomCursor(ex.stylus_, new Point(0, 0), "stylusCursor");
	textCursor = Toolkit.getDefaultToolkit().createCustomCursor(ex.text, new Point(0, 0), "textCursor");	
}
	
	public void paint(Graphics g) {
		update(g);
	}
	
	public void update(Graphics g) {
		if(init){
			init = false;
			im = createImage(imwidth, imheight);
			imgr = im.getGraphics();
			imgr.setColor((Color)isBackgroundColors.lastElement());	
			imgr.fillRect(0,0,im.getWidth(null),im.getHeight(null));
		}
		if(im!=null) {
			if(tb.New_bool){
				Graphics2D g2D= (Graphics2D) imgr;
				Color bkg = isBackgroundColors.size()>0?(Color)isBackgroundColors.lastElement():Color.white;
				imgr.setColor(bkg);	
				imgr.fillRect(0,0,im.getWidth(null),im.getHeight(null));			
				for(int i=0; i<elements.size(); i++){
					Element element = (Element)elements.elementAt(i);
					if(element.image!=null){
						imgr.drawImage(element.image,3000+Ox+element.x,2000+Oy+element.y,this);
					}
					if(element.k > 0){
						Color col=new Color(element.color.getRed(), element.color.getGreen(), element.color.getBlue(), 255);
						//Color colNext;
						if(element.isEraser==true) imgr.setColor(bkg);
						else imgr.setColor(col);
						float thickness = 1;
						if(element.isFillOval || element.isEraser || element.isBrush) thickness = 6;
						g2D.setStroke(new BasicStroke(thickness));
						for(int j=1; j<element.k-1; j++){
							imgr.setColor(col);
							g2D.drawLine(3000+Ox+element.X[j], 2000+Oy+element.Y[j], 3000+Ox+element.X[j+1], 2000+Oy+element.Y[j+1]);
							if (element.isBrush==true) 
								if (col.getAlpha()>0){
									//colNext= new Color(col.getRed(), col.getGreen(), col.getBlue(), col.getAlpha()-5);
									//imgr.setColor(col);
									//GradientPaint gradient = new GradientPaint(3000+Ox+element.X[j], 2000+Oy+element.Y[j], col, 3000+Ox+element.X[j+1], 2000+Oy+element.Y[j+1], col);
									//g2D.setPaint(gradient);
									//col=colNext;
									
									imgr.setColor(bkg);
									g2D.fillRect(2997+Ox+element.X[j], 1997+Oy+element.Y[j], 6, 6);
									imgr.setColor(col);
									g2D.fillRect(2997+Ox+element.X[j], 1997+Oy+element.Y[j], 6, 6);
									col= new Color(col.getRed(), col.getGreen(), col.getBlue(), col.getAlpha()-5);
								}
						}
						if(element.isFlipv==true || element.isFliph==true)
							if(element.isFinished = false){
								imgr.setColor(Color.blue);
								g2D.drawRect(element.X[1], element.Y[1], element.X[2]-element.X[1], element.Y[2]-element.Y[1]);
							}
					}					
				}		
				g.drawImage(im,-3000,-2000,this);
			}else{
				g.setColor(new Color(38, 104, 165));	
				g.fillRect(0,0,im.getWidth(null),im.getHeight(null));					
			}
		}
    } 
	
	//private void deplasarePeDirectie(int xa, int xb, int ya, int yb){
	//	int t,z;
	//	t=
	//}

	public void mouseClicked(MouseEvent e){}

	public void mouseEntered(MouseEvent e){
		if(tb.Open_bool){
			Element element = (Element)elements.lastElement();
			if(element.image!=null){
				element.x = e.getX()-Ox;
				element.y = e.getY()-Oy;
				repaint();
			}			
		}			
	}

	public void mouseExited(MouseEvent e){}

	public void mousePressed(MouseEvent e){
		if(tb.Fill_bool){
			elements.add(new Element(tb.culoare_curenta,true));
			isBackgroundColors.add(tb.culoare_curenta);
			setCursor(defaultCursor);
			tb.Fill_bool = false;
			repaint();
		}else if(tb.Move_bool){	
			xc = e.getX();
			yc = e.getY();
		}else if(tb.Stylus_bool){
			tb.Stylus_bool = false;
			int c = bim.getRGB(3000+e.getX(), 2000+e.getY());
			int a = (c>>24)&0xff;
			int r = (c>>16)&0xff;
			int g = (c>>8)&0xff;
			int b = c&0xff;
			Color color = new Color(r,g,b);
			tb.culoare_curenta = color;
			tb.culoare_curenta_p.setBackground(tb.culoare_curenta);
			setCursor(defaultCursor);
		}else if(tb.Pen_bool){
			Element element = new Element();
			element.color = tb.culoare_curenta;
			element.k++;
			element.X[element.k] = e.getX()-Ox;
			element.Y[element.k] = e.getY()-Oy;
			elements.add(element);
			repaint();
		}			
		else if(tb.Peng_bool){
			Element element = new Element(tb.culoare_curenta, "FillOval");
			element.k++;
			element.X[element.k] = e.getX()-Ox;
			element.Y[element.k] = e.getY()-Oy;
			elements.add(element);
			repaint();
		}
		else if(tb.Eraser_bool){
			Element element = new Element(tb.culoare_curenta, "Eraser");
			element.k++;
			element.X[element.k] = e.getX()-Ox;
			element.Y[element.k] = e.getY()-Oy;
			elements.add(element);
			repaint();
		}
		else if(tb.Brush_bool){
			Element element = new Element(tb.culoare_curenta, "Brush");
			element.k++;
			element.X[element.k] = e.getX()-Ox;
			element.Y[element.k] = e.getY()-Oy;
			elements.add(element);
			repaint();
		}
		else if(tb.Flipv_bool){
			Element element = new Element(tb.culoare_curenta, "Flipv");
			element.k=2;
			element.X[1] = e.getX()-Ox;
			element.Y[1] = e.getY()-Oy;
			elements.add(element);
			repaint();
		}
	}

	public void mouseReleased(MouseEvent e){		
		if(tb.Open_bool){
			tb.Open_bool = false;
			tb.Open.repaint();
		}
		if(tb.Flipv_bool){
			
		}
	}
	
	public void mouseDragged(MouseEvent e){
		if(tb.Move_bool){
			Ox = Ox+e.getX()-xc;
			Oy = Oy+e.getY()-yc;
			xc = e.getX();
			yc = e.getY();			
			repaint();
		}else if(tb.Pen_bool){
			Element element = (Element)elements.lastElement();
			element.k++;
			element.X[element.k] = e.getX()-Ox;
			element.Y[element.k] = e.getY()-Oy;
			repaint();
		}
		else if(tb.Peng_bool){
			Element element = (Element)elements.lastElement();
			element.k++;
			element.X[element.k] = e.getX()-Ox;
			element.Y[element.k] = e.getY()-Oy;
			repaint();
		}
		else if(tb.Eraser_bool){
			Element element = (Element)elements.lastElement();
			element.k++;
			element.X[element.k] = e.getX()-Ox;
			element.Y[element.k] = e.getY()-Oy;
			repaint();
		}
		else if(tb.Brush_bool){
			Element element = (Element)elements.lastElement();
			element.k++;
			element.X[element.k] = e.getX()-Ox;
			element.Y[element.k] = e.getY()-Oy;
			repaint();
		}
		else if(tb.Flipv_bool){
			Element element = (Element)elements.lastElement();
			Point mousePos=MouseInfo.getPointerInfo().getLocation();
			element.X[2]= (int)(mousePos.getX())-Ox;
			element.Y[2]= (int)(mousePos.getY())-Oy;
			repaint();
		}

	}

	public void mouseMoved(MouseEvent e){
		if(tb.Open_bool){
			Element element = (Element)elements.lastElement();
			if(element.image!=null){
				element.x = e.getX()-Ox;
				element.y = e.getY()-Oy;			
				repaint();
			}			
		}else if(tb.Stylus_bool){
			int c = bim.getRGB(3000+e.getX(), 2000+e.getY());
			int a = (c>>24)&0xff;
			int r = (c>>16)&0xff;
			int g = (c>>8)&0xff;
			int b = c&0xff;
			Color color = new Color(r,g,b);
			tb.culoare_curenta_p.setBackground(color);
		}
		sb.setInfo1((e.getX()-7) + ", " + (e.getY()-87) + "px");
	}

	
}
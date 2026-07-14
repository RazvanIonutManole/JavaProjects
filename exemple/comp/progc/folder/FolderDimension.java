package comp.progc.folder;

import java.awt.*; 
import java.io.*;

public class FolderDimension extends Frame{
  String dir; 
  TextArea ta;
  TextField tf;
  Button b;
  long len;
  String s;
  int level;

public FolderDimension(){
  Dimension res=getToolkit().getScreenSize();
  setResizable(false); 
  setTitle("Dimensiunea unui director [octeti]"); 
  setLayout(null);
  
  int y = 60;
  
  b = new Button("Dimensiune");
  add(b);
  b.setBounds(20, y, 100, 20);

  tf = new TextField("");
  add(tf);
  tf.setBounds(180, y, 600, 20); 
  tf.setForeground(Color.black); 
  tf.enable(false);
 
  ta = new TextArea("");
  add(ta);
  ta.setBounds(20, y+40, 760, 480); 
  ta.setForeground(Color.black); 
  
  resize(800,600);
  move((int)((res.width-800)/2)+200,(int)((res.height-600)/2)+100);   
  show();
}

public boolean handleEvent(Event e){
  if(e.id==Event.WINDOW_DESTROY){
    dispose();
  }else if(e.id==Event.ACTION_EVENT){ 
	if(e.target == b){ 
		b.enable(false);		
		s = "";
		len = 0;
		level = -1;
		ta.setText(s);
		tf.setText("");
		FileDialog fd = new FileDialog(this, "Alege un fisier", 0); 
		if(dir!=null) fd.setDirectory(dir);
		fd.setVisible(true);
		if(fd.getFile() != null) {
			dir = fd.getDirectory();
			getLength(new File(dir));
			ta.setText(s);
			long kb = len/1024;
			long mb = len/1048576;
			long gb = len/1073741824;
			tf.setText(dir + ":  " + len + " Bytes / " + kb + " KB / " + mb + " MB / " + gb + " GB");
			b.enable(true);
			return true;
		}else{
			b.enable(true);
			return false;
		}
	}else return false;
  }
  return super.handleEvent(e);
}

void getLength(File f){
	if(f!=null && f.exists()){
		if(f.isFile()){
			len += f.length();
			String ss = "";
			for(int i=0; i<level; i++) ss += "      ";
			s += ss + f.getName() + ":  " +f.length() + " Bytes \n";
		}else{
			String ss = "";
			for(int i=0; i<level; i++) ss += "      ";	
			s += ss + f.getName() + " / \n";
			level++;
			File[] list = f.listFiles();
			for(int i=0; i<list.length; i++){
				getLength(list[i]);
			}
			level--;
		}
	}
}



}

package comp.tools;

import java.awt.*;
import java.io.*;
import exemple.Exemple;

public class Notepad extends Frame{
	Exemple ex;
	TextArea ta;
	String path, dir, fileName = "";
	String clipboard = "";

public Notepad(Exemple ex){
       this.ex = ex;
       setTitle("Notepad");
       setIconImage(ex.notepad);
	   adaugaMenuBar();
       ta = new TextArea();
       add("Center", ta);
       ta.requestFocus();
       Toolkit tool=getToolkit();
       Dimension res=tool.getScreenSize();
       reshape((int)((res.width-400)/2+200),(int)((res.height-400)/2+100), 400, 400);
	   show();
}

public void adaugaMenuBar(){
	MenuBar mb=new MenuBar();
	
	Menu file=new Menu("File");
	Menu edit=new Menu("Edit");
	
	file.add("New");
	file.add("Open");
	file.add("Save");
	file.add("Save As..");
	file.add("-");
	file.add("Exit");
	
	edit.add("Cut");
	edit.add("Copy");
	edit.add("Paste");
	edit.add("Delete");
	edit.add("-");
	edit.add("Select All");

	mb.add(file);
	mb.add(edit);
	
	setMenuBar(mb);	
}	

public boolean handleEvent(Event e){
    if(e.id==Event.WINDOW_DESTROY) dispose();
	else if(e.id==Event.ACTION_EVENT && e.target instanceof MenuItem){
		if("Exit".equals(e.arg))dispose();
		else if("New".equals(e.arg)){
			path = null;
			ta.setText("");
			return true; 
		}else if("Open".equals(e.arg)){
			openFile();
			return true;
		}else if("Save".equals(e.arg)){
			if(path!=null)
				saveFile();
			else
				saveFileAs();
			return true; 
		}else if("Save As..".equals(e.arg)){
			saveFileAs();
			return true;	
		}else if("Cut".equals(e.arg)){
			cut();
			return true;
		}else if("Copy".equals(e.arg)){
			copy();
			return true;
		}else if("Delete".equals(e.arg)){
			delete();
			return true;
		}else if("Paste".equals(e.arg)){
			paste();
			return true;
		}else if("Select All".equals(e.arg)){
			selectAll();
			return true;			
		}else{ 
		
			return true;	
		}	 
	}else return false;	
	return false;	
}

	void cut(){
		copy();
		delete();
	}
	
	void copy(){
		clipboard=ta.getSelectedText();
	}
	
	void paste(){
		ta.insert(clipboard, ta.getCaretPosition());
	}
	
	void selectAll(){
		ta.selectAll();
	}
	
	void delete(){
		ta.replaceRange("", ta.getSelectionStart(), ta.getSelectionEnd());
	}

	void openFile(){
	   	try{ 	
			FileDialog fd=new FileDialog(this, "Open a txt file", 0);
			if(dir!=null) fd.setDirectory(dir);
			fd.setFile("*.txt");
			fd.setVisible(true);
			if(fd.getFile() != null) { 
				dir = fd.getDirectory();
				String fisier = fd.getFile();
				path = dir + fisier;
	        	FileInputStream fis = new FileInputStream(new File(path));
				DataInputStream dis = new DataInputStream(fis);
				ta.setText("");
				String s;				
				while ((s = dis.readLine()) != null) ta.append(s+"\n");
				dis.close();
				fis.close();
		   	}
		}
		catch(Exception e) {e.printStackTrace();}	
	}
	
	void saveFile(){		
		try{ 	
			writeFile(new File(path));
		}
		catch(Exception e) {e.printStackTrace();}
	}	

	void saveFileAs(){		
		try{ 	
			FileDialog fd=new FileDialog(this, "Save As ...", 1);
			if(dir!=null)fd.setDirectory(dir);
			fd.setFile("*.txt");
			fd.setVisible(true);
			if(fd.getFile() != null) {
		        String path1 = fd.getDirectory() + fd.getFile();
				if(path1.indexOf(".")==-1) path1+=".txt";
				File f = new File(path1);
				writeFile(f);
			}
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	
void writeFile(File f) throws Exception {
	if (path!="") {
		if(f.exists()) f.delete();
		FileWriter fw = new FileWriter(f);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(ta.getText()); 
		bw.flush();
		bw.close();
	}
}	

}
package comp.progl.X0;

import java.awt.*;
import java.awt.event.*;
public class xo extends Frame implements ActionListener{
	Button[] b;
	int[][] board;
	String turn="X";
	Label WinnerTop;
	Label Winner;
	int scorX, scorO;
	Label sX, sO;
	Label turnDisplay, randulLui;
	
	public static void main(String[] args){
		xo x=new xo();
	}
	
	public xo(){
		this.setTitle("X si O");
		this.setSize(500, 500);
		this.move(400, 200);
		this.setBackground(Color.CYAN);
		
		this.setLayout(null);
		
		this.setVisible(true);
		
		b=new Button[9];
		for(int i=0;i<9;i++){
			b[i]=new Button("");
			b[i].setBounds(100+100*(i%3), 100+100*(i/3), 100, 100);
			b[i].addActionListener(this);
			b[i].setBackground(Color.WHITE);
			b[i].setFont(new Font("Arial", Font.BOLD, 24));
			this.add(b[i]);
		}
		
		WinnerTop=new Label("Winner:");
		WinnerTop.setBounds(200, 30, 100, 20);
		WinnerTop.setAlignment(Label.CENTER);
		this.add(WinnerTop);
		Winner=new Label("---");
		Winner.setBounds(200, 50, 100, 20);
		Winner.setAlignment(Label.CENTER);
		this.add(Winner);
		
		board=new int[3][3];
		scorX=0;
		scorO=0;
		
		sX=new Label("Scor X: " + scorX);
		sX.setBounds(20, 40, 100, 20);
		sX.setAlignment(Label.LEFT);
		this.add(sX);
		
		sO=new Label("Scor O: " + scorO);
		sO.setBounds(400, 40, 80, 20);
		sO.setAlignment(Label.RIGHT);
		this.add(sO);
		
		randulLui=new Label("Randul lui: ");
		randulLui.setBounds(200, 410, 100, 20);
		randulLui.setAlignment(Label.CENTER);
		this.add(randulLui);
		
		turnDisplay=new Label("X");
		turnDisplay.setBounds(200, 430, 100, 20);
		turnDisplay.setAlignment(Label.CENTER);
		this.add(turnDisplay);
	}
	
	private void changeTurn(){
		if (turn=="X") turn="O";
		else if (turn=="O") turn="X";
		turnDisplay.setText(turn);
	}
	
	private String checkWinner(){
		String Winner="---";
		int win;
		int x=0;
		for(int i=0;i<3;i++){  //verificam pe linii
			win=1;
			x=board[i][0];
			for(int j=1;j<3;j++) if (board[i][j]!=x) win=0;
			if (win==1){
				if (x==1) {Winner="X"; return Winner;}
				if (x==2) {Winner="O"; return Winner;}
			}
		}
		for(int i=0;i<3;i++){  //verificam pe coloane
			win=1;
			x=board[0][i];
			for(int j=1;j<3;j++) if (board[j][i]!=x) win=0;
			if (win==1){
				if (x==1) {Winner="X"; return Winner;}
				else if (x==2) {Winner="O"; return Winner;}
			}
		}
		x=board[0][0]; //diagonala principala
		if (x==board[1][1] && x==board[2][2]){
			if (x==1) {Winner="X"; return Winner;}
			else if (x==2) {Winner="O"; return Winner;}
		}
		x=board[0][2]; //diagonala secundara
		if (x==board[1][1] && x==board[2][0]){
			if (x==1) {Winner="X"; return Winner;}
			else if (x==2) {Winner="O"; return Winner;}
		}
		for(int i=0;i<3;i++) //verificam daca s-a terminat jocul
			for(int j=0;j<3;j++)
				if (board[i][j]==0) {Winner="---"; return Winner;}
		Winner="DRAW";
		return Winner;
	}
	
	public void endOfGame(String Win){
		Winner.setText(Win);
		if (Win=="X") scorX=scorX+1;
		if (Win=="O") scorO=scorO+1;
		sX.setText("Scor X: " + scorX);
		sO.setText("Scor O: " + scorO);
		for(int i=0;i<9;i++){
			b[i].setEnabled(false);
		}
		try {
			Thread.sleep(2000); //asteapta 2 secunde
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		restartGame();
	}
	public void restartGame(){
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++){
				board[i][j]=0;
			}
		for(int i=0;i<9;i++){
			b[i].setEnabled(true);
			b[i].setLabel("");
		}
		Winner.setText("---");
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		Button sursa=(Button)e.getSource();
		for(int i=0;i<9;i++)
			if (sursa==b[i]){
				b[i].setLabel(turn);
				b[i].setEnabled(false);
				if(turn=="X")board[i%3][i/3]=1;
				if(turn=="O")board[i%3][i/3]=2;
				changeTurn();
				String Win=checkWinner();
				if (Win!="---")endOfGame(Win);
			}
	}
	
	public boolean handleEvent(Event e){
		if(e.id==Event.WINDOW_DESTROY){
			this.dispose();
		}
		return super.handleEvent(e);
	}
}
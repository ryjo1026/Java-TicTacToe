package main;

import javax.swing.*;

public class TicTacToe {

	public static void main(String[] args) {
		int gameMode;
		String[] Options={"9 by 9 Play", "3 by 3 Play","Cancel"};
		
		//Shows JOptionPane with three modes of play to choose from
		gameMode= JOptionPane.showOptionDialog(null, "What game mode would you like to play in?", "Choose a Game Mode", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, Options, null);
		
		//If 9by9 Mode Picked
		if(gameMode==JOptionPane.YES_OPTION){
			JFrame f= new NineFrame();
			
			f.setTitle("TicTacToe");
			f.setSize(600,600);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setLocationRelativeTo(null);
			f.setVisible(true);
		}
		
		//If 3by3Mode Picked
		if(gameMode==JOptionPane.NO_OPTION){
			JFrame f= new Frame();
		
			f.setTitle("TicTacToe");
			f.setSize(600,600);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setLocationRelativeTo(null);
			f.setVisible(true);
		}
		
		//If Cancel Picked
		if(gameMode==JOptionPane.CANCEL_OPTION)
			System.exit(0);
		
	}

}

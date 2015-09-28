package main;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

import main.Frame;
import main.Frame.Cell;

/**
 * JFrame to hold 9x9 version of TicTacToe
 * @author Ryan
 *
 */

public class NineFrame extends JFrame{
	private char turn='X';
	
	boolean gameOver=false;
	
	private Cell[][][] cells= new Cell[3][3][9];
	private Cell[][] majorCells=new Cell[3][3];
	
	JLabel jlblStatus= new JLabel("X's turn", SwingConstants.CENTER);
	
	public NineFrame(){
		JPanel panel=new JPanel(new GridLayout(9,9,0,0));

		for(int i=0; i<3; i++)
			for(int j=0; j<3; j++)
				for(int k=0; k<9; k++){
					panel.add(cells[i][j][k]= new Cell(i+k+j));	
				}

		
		
		add(panel, BorderLayout.CENTER);
	}
	
	public class Cell extends JPanel{
		int value=0;
		
		public Cell(){
			setBorder(new LineBorder(Color.BLACK,1));
			addMouseListener(new MyMouseListener());
		}
		
		public Cell(int a){
			setBorder(new LineBorder(Color.BLACK,1));
			addMouseListener(new MyMouseListener());
			value= a;
		}
		
		public int getValue(){
			return value;
		}
	}
	
	private class MyMouseListener extends MouseAdapter{
		@Override
		public void mouseReleased(MouseEvent e){
			System.out.println(Cell.getValue());
		}
	}
	
}

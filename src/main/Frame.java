package main;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * JFrame to hold TicTacToe
 * @author Ryan
 *
 */

public class Frame extends JFrame{
	//Indicates whose turn it is
	private char turn= 'x';
	
	//Create cell grid
	private Cell[][] cells= new Cell[3][3];
	
	//Create a status label
	JLabel jlblStatus= new JLabel("X's turn");
	

	
	/**
	 * No-argument constructor
	 */
	public Frame(){
		//Panel to hold cells
		JPanel panel=new JPanel(new GridLayout(3,3,0,0));
		
		
		
		for(int i=0; i<3; i++)
			for(int j=0; j<3; j++)
				panel.add(cells[i][j]= new Cell());
		
		jlblStatus.setPreferredSize(new Dimension(25,25));
		jlblStatus.setFont(new Font("Arial", 9, 25));
		
		panel.setBorder(new LineBorder(Color.BLACK, 1));
		
		add(panel, BorderLayout.CENTER);
		add(jlblStatus, BorderLayout.NORTH);
	}
	
	/**
	 * Determine if board is full
	 * @return True, if game board is full. Otherwise, false
	 */
	public boolean isFull(){
		for(int i=0; i<3; i++)
			for(int j=0; j<3; j++)
				if (cells[i][j].getToken()==' ')
					return false;
		return true;
	}
	
	/**
	 * Determines if a given token has won
	 * @param token The token to search a win for
	 * @return True, if game is won. Otherwise,false
	 */
	public boolean isWon(char token){
		// check rows
	       for (int i = 0; i < 3; i++)
	           if ((cells[i][0].getToken() == token)
	                   && (cells[i][1].getToken() == token)
	                   && (cells[i][2].getToken() == token))
	           {
	               return true;
	           }
	 
	       // check columns
	       for (int j = 0; j < 3; j++)
	           if ((cells[0][j].getToken() == token)
	               && (cells[1][j].getToken() == token)
	               && (cells[2][j].getToken() == token))
	           {
	               return true;
	           }
	       // check diagonal
	       if ((cells[0][0].getToken() == token)
	               && (cells[1][1].getToken() == token)
	               && (cells[2][2].getToken() == token))
	           {
	               return true;
	           }
	 
	       if ((cells[0][2].getToken() == token)
	               && (cells[1][1].getToken() == token)
	               && (cells[2][0].getToken() == token))
	           {
	               return true;
	           }
	 
	       return false;
	}


	/**
	 * Defines a cell in the TicTacToe Game
	 * @author Ryan
	 *
	 */
	public class Cell extends JPanel{
		//token of cell
		private char token=' ';
		
		/**
		 * No-argument constructor
		 */
		public Cell(){
			setBorder(new LineBorder(Color.BLACK, 1));
			addMouseListener(new MyMouseListener());
		}
		
		/**
		 * Gets the token of the cell
		 * @return token value of the cell
		 */
		public char getToken(){
			return token;
		}
		
		/**
		 * Sets the token of the cell
		 * @param c Token to set the cell to
		 */
		public void setToken(char c){
			token =c;
			repaint();
		}
		
		@Override
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			
			
			if (token=='x'){
				g.drawLine(10, 10, getWidth()-10, getHeight()-10);
				g.drawLine(getWidth()-10, 10, 10, getHeight()-10);
			}
			else if(token=='o')
				g.drawOval(10, 10, getWidth()-20, getHeight()-20);
		}
	
		/**
		 * 
		 * @author Ryan
		 *
		 */
		private class MyMouseListener extends MouseAdapter{
			@Override
			public void mouseReleased(MouseEvent e){
				//if the cell is empty and game not over
				if (token==' ' && turn != ' ')
					setToken(turn);
				
				//Check game status
				if(isWon(turn)){
					jlblStatus.setText(turn+" won!");
				}
				else if(isFull()){
					jlblStatus.setText("Stalemate");
				}
				else{
					turn=(turn=='x') ? 'o' : 'x';
					jlblStatus.setText(turn + "'s turn");
				}
					
				
			}
		}
		
	}

}

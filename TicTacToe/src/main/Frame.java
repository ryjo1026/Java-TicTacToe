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
	private char turn= 'X';
	
	//Indicates weather game is done
	boolean gameOver= false;
	
	//Create cell grid
	private Cell[][] cells= new Cell[3][3];
	
	//Create a status label
	JLabel jlblStatus= new JLabel("X's turn", SwingConstants.CENTER);
	
	//Create Overlay color
	int transparentWhiteColorValue=200;
	Color transparentWhite= new Color(transparentWhiteColorValue,transparentWhiteColorValue,transparentWhiteColorValue,250);
	

	
	/**
	 * No-argument constructor
	 */
	public Frame(){
		//Panel to hold cells
		JPanel panel=new JPanel(new GridLayout(3,3,0,0));
		
		
		for(int i=0; i<3; i++)
			for(int j=0; j<3; j++)
				panel.add(cells[i][j]= new Cell());
		
		jlblStatus.setPreferredSize(new Dimension(50,50));
		jlblStatus.setFont(new Font("Arial", 9, 50));
		jlblStatus.setForeground(Color.GREEN);
		
		
		
		
		
		//panel.setBorder(new LineBorder(Color.BLACK, 1));
		
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
	
	public void reset(){
		for(int i=0; i<3; i++)
			for(int j=0; j<3; j++)
				cells[i][j].setToken(' ');
		jlblStatus.setText(null);
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
			
			Graphics2D g2= (Graphics2D)(g);
			
			//Sets new stroke and anti-aliasing for shapes and text
			g2.setStroke(new BasicStroke(10));
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			
			if (token=='X'){
				jlblStatus.setForeground(Color.RED);
				g2.setColor(Color.darkGray);
				g2.drawLine(10, 10, getWidth()-10, getHeight()-10);
				g2.drawLine(getWidth()-10, 10, 10, getHeight()-10);
			}
			else if(token=='O'){
				jlblStatus.setForeground(Color.GREEN);
				g2.setColor(Color.darkGray);
				g2.drawOval(10, 10, getWidth()-20, getHeight()-20);
			}
		}
	
		/**
		 * 
		 * @author Ryan
		 *
		 */
		private class MyMouseListener extends MouseAdapter{
			@Override
			public void mouseReleased(MouseEvent e){
				
				JFrame f= new Frame();
				int playAgain;
				
//				if(gameOver){
//				
//				}
				
				if (!gameOver){
					//if the cell is empty and game not over
					if (token==' ' && turn != ' ')
						setToken(turn);
					
					//Check game status
					if(isWon(turn)){
						jlblStatus.setText(turn +" won!");
						gameOver=true;
						
						playAgain= JOptionPane.showConfirmDialog(f, "Would you like to play again?" ,"TicTacToe",JOptionPane.YES_NO_OPTION);
						
						if(playAgain== JOptionPane.YES_OPTION){
							reset();
							gameOver=false;
						}
						else if(playAgain== JOptionPane.NO_OPTION)
							System.exit(0);
						
					}
					else if(isFull()){
						jlblStatus.setText("Stalemate");
					}
					else{
						turn=(turn=='X') ? 'O' : 'X';
						jlblStatus.setText(turn + "'s turn");
					}
				}
				
			}
		}
		
	}

}

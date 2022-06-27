package project02;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MineSweeper {
	
	private static final int NUM_MINES = 60;
	private static final int SIZE = 20;

	public static void main(String[] args) {
		
		ImageIcon mineIcon = new ImageIcon("mineIconFF.png");
		infoPanel infoPanel = new infoPanel(NUM_MINES);
		
		JFrame frame = new JFrame("MineSweeper | # of mines: " + NUM_MINES);
		frame.setLayout(new BorderLayout());
		frame.add(infoPanel, BorderLayout.PAGE_START);
		frame.add(new MineSweeperGUI(SIZE, SIZE, NUM_MINES,infoPanel), BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 900);
		frame.setIconImage(mineIcon.getImage());
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public static int getSize() {
		return SIZE;
	}

	public static int getNumMines() {
		return NUM_MINES;
	}
	

}

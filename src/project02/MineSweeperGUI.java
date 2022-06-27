package project02;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MineSweeperGUI extends JPanel{
	
	private MineGrid grid;
	private JButton[][] buttons;
	private int mineCounter = MineSweeper.getNumMines();
	private boolean[][] openedCells = new boolean[MineSweeper.getSize()][MineSweeper.getSize()];
	private boolean[][] flaggedCells = new boolean[MineSweeper.getSize()][MineSweeper.getSize()];
	
	public MineSweeperGUI(int numRows, int numCols, int numMines, infoPanel infoPanel) {
		
		infoPanel.setFaceHappy();
		buttons = new JButton[numRows][numCols];		
		grid = new MineGrid(numRows, numCols, numMines);
		
		this.setLayout(new GridLayout(numRows, numCols));
		
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
		// Depending on the grid size, button font size changes
				JButton button = new JButton();
				if (MineSweeper.getSize() <= 20) {
					button.setFont(new Font("MINE-SWEEPER Regular", Font.PLAIN,18));
					button.setMargin(new Insets(0,0,0,0));
				}
				
					else if (MineSweeper.getSize() > 20) {
						button.setFont(new Font("MINE-SWEEPER Regular", Font.PLAIN, 12));
						button.setMargin(new Insets(0,0,0,0));
					}
				
				buttons[i][j] = button;
				buttons[i][j].setBackground(new Color(204,204,204));
				this.add(button);
			}
		}
		// Giving each button a button handler
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				buttons[i][j].addMouseListener(new ButtonHandler(i, j, grid, this, infoPanel));
			}
		}
		
	}
	
	public JButton[][] getButtons(){
		return buttons;
	}

	public int getMineCounter() {
		return mineCounter;
	}

	public void setMineCounter(int mineCounter) {
		this.mineCounter = mineCounter;
	}

	public boolean[][] getOpenedCells() {
		return openedCells;
	}
	
	public boolean[][] getFlaggedCells() {
		return flaggedCells;
	}

}

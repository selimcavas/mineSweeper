package project02;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


public class ButtonHandler extends MouseAdapter {
	
	private int row, col;
	private MineGrid grid;
	private MineSweeperGUI gui;
	private infoPanel infoPanel = new infoPanel(MineSweeper.getSize());
	
	public ButtonHandler(int x, int y, MineGrid g, MineSweeperGUI gui,infoPanel infoPanel) {
		row = x;
		col = y;
		grid = g;
		this.gui = gui;
		this.infoPanel = infoPanel;
	}
	

	@Override
	public void mouseClicked(MouseEvent event) {
		
		if (event.getSource() instanceof JButton) {
	// Left Click on a unflagged cell with mine
				if (grid.isMINE(row, col) && SwingUtilities.isLeftMouseButton(event) && !isFlagged(row, col)) {
					
					JButton button = (JButton)event.getSource();
					
					for (int i = 0; i < gui.getButtons().length; i++) {
						for (int j = 0; j < gui.getButtons()[0].length; j++) {
							if (grid.getCellContent(i, j) == -1) {
								revealButton(i, j);
							}
							if (isFlagged(i, j) && grid.getCellContent(i, j) == -1) {
								ImageIcon flagIcon = new ImageIcon("flag_icon.png");
								gui.getButtons()[i][j].setIcon(flagIcon);
								gui.getButtons()[i][j].setBackground(new Color(50,143,70));
							}
							
						}
					}
					
					button.setBackground(Color.RED);
					infoPanel.setFaceSad();
					infoPanel.getTimer1().cancel();
					
					
					JOptionPane.showMessageDialog(null, "OOOPS!!");
					System.exit(0);
				}
	// Left Click on a unflagged cell without mine
				if (SwingUtilities.isLeftMouseButton(event) && !isFlagged(row, col) && !isOpened(row, col)) {
					JButton button = (JButton)event.getSource();
					button.setBackground(Color.GRAY);
					if (grid.getCellContent(row, col) > 0) {
						revealButton(row, col);
						gui.getOpenedCells()[row][col] = true;
					}
					else if (grid.getCellContent(row, col) == 0) {
						revealEmptyCells(row, col);
					}
				
					gui.getFlaggedCells()[row][col] = true;
					
				}
	// Right Click to put flag 
				else if (SwingUtilities.isRightMouseButton(event) && !isFlagged(row, col) && !isOpened(row, col)) {
					JButton button = (JButton)event.getSource();
					ImageIcon flagIcon = new ImageIcon("flag_icon.png");
					button.setIcon(flagIcon);
					gui.getFlaggedCells()[row][col] = true;
					gui.setMineCounter(gui.getMineCounter()-1);
					infoPanel.getMineCounterLabel().setText(""+gui.getMineCounter());
					
					if (Win() == true) {
						infoPanel.getTimer1().cancel();
						JOptionPane.showMessageDialog(null, "YOU'RE A GENIOUS!");
						System.exit(0);
					}
					
				}
	// Right Click to remove flag
				else if (SwingUtilities.isRightMouseButton(event) && isFlagged(row, col) && !isOpened(row, col)) {
					JButton button = (JButton)event.getSource();
					button.setIcon(null);
					gui.getFlaggedCells()[row][col] = false;
					gui.setMineCounter(gui.getMineCounter()+1);
					infoPanel.getMineCounterLabel().setText(""+gui.getMineCounter());
					
					if (Win() == true) {
						infoPanel.getTimer1().cancel();
						JOptionPane.showMessageDialog(null, "YOU'RE A GENIOUS!");
						System.exit(0);
					}
				}
			}
	}

	// Reveal Cells with text color depending on their cell content
	private void revealButton( int i, int j) {
		
		gui.getOpenedCells()[i][j] = true;
		
		if (grid.isMINE(i, j)) {
			ImageIcon mineIcon = new ImageIcon("mineIconResized.png");
			gui.getButtons()[i][j].setBackground(new Color(204,204,204));
			gui.getButtons()[i][j].setIcon(mineIcon);
		}
		else if (grid.getCellContent(i, j) == 0) {
			gui.getButtons()[i][j].setBackground(new Color(173, 173, 173));
		}
		
		else if (grid.getCellContent(i, j) == 1) {
			gui.getButtons()[i][j].setBackground(new Color(173, 173, 173));
			gui.getButtons()[i][j].setForeground(Color.BLUE);
			gui.getButtons()[i][j].setText(String.valueOf(grid.getCellContent(i, j)));
		}
		
		else if (grid.getCellContent(i, j) == 2) {
			gui.getButtons()[i][j].setBackground(new Color(173, 173, 173));
			gui.getButtons()[i][j].setForeground(new Color(0, 138, 57));
			gui.getButtons()[i][j].setText(String.valueOf(grid.getCellContent(i, j)));
		}
		
		else if (grid.getCellContent(i, j) == 3) {
			gui.getButtons()[i][j].setBackground(new Color(173, 173, 173));
			gui.getButtons()[i][j].setForeground(Color.RED);
			gui.getButtons()[i][j].setText(String.valueOf(grid.getCellContent(i, j)));
		}
		
		else if (grid.getCellContent(i, j) == 4) {
			gui.getButtons()[i][j].setBackground(new Color(173, 173, 173));
			gui.getButtons()[i][j].setForeground(new Color(0,0,153));
			gui.getButtons()[i][j].setText(String.valueOf(grid.getCellContent(i, j)));
		}
		
		else if (grid.getCellContent(i, j) == 5) {
			gui.getButtons()[i][j].setBackground(new Color(173, 173, 173));
			gui.getButtons()[i][j].setForeground(new Color(153,0,0));
			gui.getButtons()[i][j].setText(String.valueOf(grid.getCellContent(i, j)));
		}
		
		else if (grid.getCellContent(i, j) == 6) {
			gui.getButtons()[i][j].setBackground(new Color(173, 173, 173));
			gui.getButtons()[i][j].setForeground(new Color(10, 168, 129));
			gui.getButtons()[i][j].setText(String.valueOf(grid.getCellContent(i, j)));
		}
		
		else if (grid.getCellContent(i, j) == 7) {
			gui.getButtons()[i][j].setBackground(new Color(173, 173, 173));
			gui.getButtons()[i][j].setForeground(new Color(146,52,235));
			gui.getButtons()[i][j].setText(String.valueOf(grid.getCellContent(i, j)));
		}
		
		else if (grid.getCellContent(i, j) == 8) {
			gui.getButtons()[i][j].setBackground(new Color(173, 173, 173));
			gui.getButtons()[i][j].setForeground(new Color(255,136,25));
			gui.getButtons()[i][j].setText(String.valueOf(grid.getCellContent(i, j)));
		}
	}
	// Opens empty neighbour cells with recursion
	private void revealEmptyCells(int i, int j) {
		if (!isOpened(i, j)) {
			revealButton(i, j);
			for (int b = -1; b <= 1; b++) {
				for (int c = -1; c <= 1; c++) {
					if ((b != 0 || c != 0) && (0 <= i + b && i + b < MineSweeper.getSize()) && (0 <= j + c && j + c < MineSweeper.getSize())) {
						if (!isOpened(i + b, j + c)) {
							if (grid.getCellContent(i, j) == 0 && !isFlagged(i + b , j + c)) {
								revealEmptyCells(i + b, j + c);
							}
						}
					}
				}
			}
		}
		
	}

	private boolean isOpened(int i, int j) {
		return gui.getOpenedCells()[i][j] == true;
	}
	
	private boolean isFlagged(int i, int j) {
		return gui.getFlaggedCells()[i][j] == true;
	}
	// Method to check if game is won
	private boolean Win() {
		int winCount = 0;
		if (gui.getMineCounter() == 0) {
		for (int i = 0; i < gui.getButtons().length; i++) {
			for (int j = 0; j <gui.getButtons()[i].length; j++) {
				if (isFlagged(i, j) && grid.getCellContent(i, j) == -1) {
					winCount++;
				}
			}
		}
			if (winCount == MineSweeper.getNumMines()) {
				revealAllCells();
				return true;
			}
		}
		return false;
	}
	// Method that reveals all cells
	private void revealAllCells() {
		for (int i = 0; i < gui.getButtons().length; i++) {
			for (int j = 0; j <gui.getButtons()[i].length; j++) {
				if(!isFlagged(i, j)) {
					revealButton(i, j);
				}
				else if (isFlagged(i, j) && grid.getCellContent(i, j) == -1) {
					ImageIcon flagIcon = new ImageIcon("flag_icon.png");
					gui.getButtons()[i][j].setIcon(flagIcon);
					gui.getButtons()[i][j].setBackground(new Color(50,143,70));
				}
			}
		}
	}
}

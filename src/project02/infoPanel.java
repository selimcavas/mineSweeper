package project02;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class infoPanel extends JPanel{
	
	private JLabel faceIcon;
	private JLabel mineCounter;
	JLabel timer;
	private Timer timer1;
	
	public infoPanel(int numMines) { 
		
		Border thinBorder = LineBorder.createBlackLineBorder();
		
		Font font = new Font("Courier", Font.PLAIN, 40);
		mineCounter = new JLabel();
		timer = new JLabel();
		faceIcon = new JLabel();
		
		faceIcon.setSize(new Dimension(50,50));
		faceIcon.setHorizontalAlignment(JLabel.CENTER);
		faceIcon.setVerticalAlignment(JLabel.CENTER);
		this.setFaceHappy();
		
		timer.setFont(font);
		timer.setOpaque(true);
		timer.setBackground(Color.BLACK);
		timer.setForeground(Color.RED);
		
		mineCounter.setFont(font);
		mineCounter.setText(""+numMines);
		mineCounter.setOpaque(true);
		mineCounter.setBackground(Color.BLACK);
		mineCounter.setForeground(Color.RED);
		
		this.setLayout(new BorderLayout(10,10));
		this.add(mineCounter, BorderLayout.WEST);
		
		this.add(faceIcon, BorderLayout.CENTER);
		
		this.add(timer, BorderLayout.EAST);
		this.setBorder(thinBorder);
		
		// Timer
		
		TimerTask task = new TimerTask() {
			int seconds = 0;

			@Override
			public void run() {
				
				if (seconds < 10) {
					timer.setText("00"+seconds++);
				}
				
				else if(seconds >= 10 && seconds < 100){
					timer.setText("0"+seconds++);
				}
				
				else if (seconds >= 100) {
					timer.setText(""+seconds++);
				}
				
				else if(seconds >= 999){
					timer.setText(""+999);
				}
			}
		};
		
		timer1 = new Timer();
		timer1.schedule(task, 0, 1000);
		
	}
	
	public void setFaceHappy(){
		ImageIcon happyIcon = new ImageIcon("happyface.png");
		faceIcon.setIcon(happyIcon);
	}
	
	public void setFaceSad(){
		ImageIcon sadIcon = new ImageIcon("sadface.png");
		faceIcon.setIcon(sadIcon);
	}
	
	public JLabel getMineCounterLabel() {
		return mineCounter;
	}

	public Timer getTimer1() {
		return timer1;
	}
	
}

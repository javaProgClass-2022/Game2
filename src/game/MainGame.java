package game;

/* Add comments here
 * 
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;



public class MainGame {
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainGame();
			}
		});
	}

	/***** constants *****/
	final static int PANW = 900;
	final static int PANH = 800;
	final static int TIMERSPEED = 10;
	
	
	/***** instance variables (global) *****/
	DrawingPanel drPanel = new DrawingPanel();
	Player p;
	static BetterKeyListener bKeyl= new BetterKeyListener();
	
	//constructor
	MainGame() {
		createAndShowGUI();
		startTimer();
		setupObjects();
	}
	void setupObjects() {
		p = new Player();
	}
	
	void createAndShowGUI() {
		JFrame frame = new JFrame("Awesome game!");
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );		
		frame.setResizable(false);
		

		frame.add(drPanel);
		frame.pack();
		frame.setLocationRelativeTo( null );		
		frame.setVisible(true);		
	}
	
	void startTimer() {		
		Timer timer = new Timer(TIMERSPEED, new TL1());
		timer.setInitialDelay(10);
		timer.start();
	}
	
	class DrawingPanel extends JPanel {
		DrawingPanel() {
			this.setBackground(Color.LIGHT_GRAY);
			this.setPreferredSize(new Dimension(PANW,PANH));  //remember that the JPanel size is more accurate than JFrame.
			this.addKeyListener(bKeyl);
			this.setFocusable(true);
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			g2.drawRect(p.x, p.y, p.width, p.height);
		}
	}
	class TL1 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			p.move();
			drPanel.repaint();			
		}
	}
}


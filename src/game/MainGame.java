package game;

/* Add comments here
 * 
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

//////////////////////////////
import javax.swing.event.MouseInputListener;



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
	final static int CX = PANW/2;
	final static int CY = PANH/2;
	final static int PFW = PANW*3; // the frame for 
	final static int PFH = PANH*3; //               spawning obstacles
	
	/***** instance variables (global) *****/
	DrawingPanel drPanel = new DrawingPanel();
	static Player p;
	static BetterKeyListener bKeyl= new BetterKeyListener();
	int spawnTime = 100;
	int time;
	
	/**** ArrayLists ****/
	//stores player, enemies, obstacles and eventually, powerups
	static ArrayList<Entity> entities = new ArrayList<Entity>();
	static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	//constructor
	MainGame() {
		createAndShowGUI();
		startTimer();
		setupObjects();
	}
	void setupObjects() {
		p = new Player();
		entities.add(p);
		setupObstacles();
	}
	void setupObstacles() {
		int n = 10; //number of obstacles to create
		for(int i=0;i<n;i++) {
			entities.add(new Obstacle());
		}
	}
	
	void createAndShowGUI() {
		JFrame frame = new JFrame("Murder in the Mesosoic");
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
			this.setFocusable(true);//required to make keyListener work
			this.addMouseListener(new BulletCoordinates());
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			//draw all the entities
			for(int i = 0; i < entities.size(); i++) {//does camera calculations and displays each entity
				//TODO make camera a method?
				int vx = entities.get(i).x-p.x+CX;
				int vy = entities.get(i).y-p.y+CY;
				if(vx < PANW && vx > 0 && vy > 0 && vy < PANH) {
					
					g2.drawRect(vx, vy, entities.get(i).width, entities.get(i).height);
				}
			}
			//draw all the bullets
			for(Bullet bullet: bullets) {
				int vx = (int) (bullet.x-p.x+CX);
				int vy = (int) (bullet.y-p.y+CY);
				if(vx < PANW && vx > 0 && vy > 0 && vy < PANH) {
					g2.fillRect(vx, vy, bullet.width, bullet.height);
				}
			}
		}	
	}
	
	class TL1 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			time++;
			if(time % spawnTime == 0) {//every few seconds spawns an enemy
				entities.add(new Enemy());
			}
			for(int i=0;i<entities.size();i++) {//move all the enemies. Don't move obstacles
				if(entities.get(i).aspeed!=0) {
					entities.get(i).move(p);
				}
			}
			for(int i=bullets.size()-1;i>-1;i--) {
				bullets.get(i).move();
			}
			
			p.move();
			
			drPanel.repaint();
		}
	}
	
	class BulletCoordinates implements MouseInputListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			int vx = e.getX();
			int vy = e.getY();
		
			int x = p.x+vx-CX;
			int y = p.y+vy-CY;
			
			bullets.add(new Bullet(p.x, p.y, x, y));
			

		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}





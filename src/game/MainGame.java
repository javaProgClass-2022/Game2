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
import java.util.ArrayList;

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
	final static int CX = PANW/2;
	final static int CY = PANH/2;
	final static int PFW = PANW*3;
	final static int PFH = PANH*3;
	
	/***** instance variables (global) *****/
	DrawingPanel drPanel = new DrawingPanel();
	static Player p;
	static BetterKeyListener bKeyl= new BetterKeyListener();
	int enemySpawnTime = 100;
	int hpSpawnTime = 5000;
	int time;
	
	/**** ArrayLists ****/
	//stores player, enemies, obstacles and eventually, powerups
	static ArrayList<Entity> entities = new ArrayList<Entity>();
	
	
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
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			//draw all the entities
			for(int i = 0;i<entities.size();i++) {//does camera calculations and displays each entity
				//TODO make camera a method?
				int vx = entities.get(i).x-p.x+CX;
				int vy = entities.get(i).y-p.y+CY;
				if(vx<PANW&&vx>0&&vy>0&&vy<PANH) {
					g2.drawRect(vx, vy, entities.get(i).width, entities.get(i).height);
					if (entities.get(i)==p) {
						g2.setColor(Color.BLACK);
						g2.fillRect(vx-50, vy+12 ,  100, 10);
						g2.setColor(Color.GREEN);
						g2.fillRect(vx-50, vy+12, Player.health, 10);
						
					}
				}
			}
		}	
	}
	
	class TL1 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			time++;
			for(int i=0;i<entities.size();i++) {//move all the enemies. Don't move obstacles
				if(entities.get(i).aspeed!=0) {
					entities.get(i).move(p);
				}
			}
			Spawn();
			p.move();
			drPanel.repaint();
		}
	}
	void Spawn() {
		//Enemy
		if(time%enemySpawnTime==0) {//every few seconds spawns an enemy
			entities.add(new Enemy());
		}
		//healthpack
		if(time%hpSpawnTime==0) {
			entities.add(new Healthpack());
		}
	}
}


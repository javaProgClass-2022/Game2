package game;

/* Add comments here
 * 
 */

import java.awt.BasicStroke;
import java.awt.BorderLayout;
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
	final static int PANW = 1000; //900
	final static int PANH = 600; //800
	final static int TIMERSPEED = 10;
	final static int CX = PANW/2;
	final static int CY = PANH/2;
	final static int PFW = PANW* 8; // 3
	final static int PFH = PANH * 8; // 3
	
	// quantity of bullets in a magazine
	static int magazine = 99999999;
	
	static int level = 1;
	
	// quantity of bullets in a magazine
	static int magazine = 99999999;
	
	static int level = 1;
	
	/***** instance variables (global) *****/
	DrawingPanel drPanel = new DrawingPanel();
	static Player p;
	static BetterKeyListener bKeyl= new BetterKeyListener();
	int enemySpawnTime = 100;

  int triceratopsSpawnTime = 300;
	int velociraptorSpawnTime = 500;
	int tRexSpawnTime = 800;
	int pterodactylSpawnTime = 600;
	int hpSpawnTime = 5000;

	int time;
	int levelDelay=10000; //how long between levels
	
	
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

    int n = 50; //number of obstacles to create

		for(int i=0;i<n;i++) {
			entities.add(new Obstacle());
		}
	}
	
	void createAndShowGUI() {
		JFrame frame = new JFrame("Murder in the Mesosoic");
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );		
		frame.setResizable(false);

    
		JPanel panel = new JPanel(new BorderLayout());

		JPanel menu = new JPanel(new BorderLayout());
		menu.setPreferredSize(new Dimension(1000, 150));
		
		panel.add(drPanel, BorderLayout.NORTH);
		panel.add(menu, BorderLayout.SOUTH);
		frame.add(panel);
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
			this.addMouseListener(new BulletCoordinates());
			this.addKeyListener(bKeyl);
			this.setFocusable(true);//required to make keyListener work
			this.addMouseListener(new BulletCoordinates());
			this.setBackground(new Color(155, 143, 129));

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

				if(vx < PANW && vx > 0 && vy > 0 && vy < PANH) {

          g2.setColor(entities.get(i).color);
					g2.fillRect(vx, vy, entities.get(i).width, entities.get(i).height);
				}
			}
			
			for(Bullet bullet: bullets) {
				int vx = (int) (bullet.x-p.x+CX);
				int vy = (int) (bullet.y-p.y+CY);
				if(vx < PANW && vx > 0 && vy > 0 && vy < PANH) {
					g2.setColor(Color.black);
					g2.fillRect(vx, vy, bullet.width, bullet.height);
				}
			}


		}	
	}
	
	class TL1 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			time++;

      
			for(int i=0;i<entities.size();i++) {//move all the enemies. Don't move obstacles
				if(entities.get(i) instanceof Enemy) {
					entities.get(i).move(p);
				}
			}
			
			if(time%levelDelay==0&&enemySpawnTime>2) {
				enemySpawnTime=enemySpawnTime/2;
			}

			for(int i=bullets.size()-1;i>=0;i--) {//move all the enemies. Don't move obstacles

				bullets.get(i).move();
			}
			Spawn();
			p.move();
			drPanel.repaint();
			
			if(time % 50 == 0) {
				
			}
			
			if (time % 1000 == 0) {
				level += 1;
				/*for(Entity entity: entities) {
					if (entity instanceof Enemy) {
						entity.aspeed += level / 2;
					}
				}*/
			}

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

			if (magazine != 0) {
				int vx = e.getX();
				int vy = e.getY();
			
				int x = p.x+vx-CX;
				int y = p.y+vy-CY;
				

				// bullet shooting
				switch(p.gun) {
				case shotgun: 
					// I gave up (the spread is broken
					double distance = Math.sqrt((x - p.x) * (x - p.x) + (x - p.y) * (x - p.y)) / 40;
					
					bullets.add(new Shotgun(p.x, p.y, x, y + (int)Math.floor(Math.random() * (((y - p.x) - (x - p.x)) / distance)), Player.vx, Player.vy));
					bullets.add(new Shotgun(p.x, p.y, x + (int)Math.floor(Math.random() * (((x - p.x) - (y - p.y)) / distance)), y, Player.vx, Player.vy));
					bullets.add(new Shotgun(p.x, p.y, x - (int)Math.floor(Math.random() * (((x - p.x) - (y - p.y)) / distance)), y, Player.vx, Player.vy));
					bullets.add(new Shotgun(p.x, p.y, x, y - (int)Math.floor(Math.random() * (((y - p.y) - (x - p.x)) / distance)), Player.vx, Player.vy));
				break;
				
				case assaultRifle:
					bullets.add(new AssaultRifle(p.x, p.y, x, y, p.vx, p.vy));
					
				break;
				
				case sniperRifle: 
					bullets.add(new SniperRifle(p.x, p.y, x, y, p.vx, p.vy));
				break;
				
				default: 
					bullets.add(new Pistol(p.x, p.y, x, y, p.vx, p.vy));
				}
				
				magazine -= 1;
			}

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
	void Spawn() {

		//Enemy
		if(time%triceratopsSpawnTime==0) {//every few seconds spawns an enemy
			entities.add(new Triceratops());
		}
		if(time%velociraptorSpawnTime==0) {//every few seconds spawns an enemy
			int spawnNum = 5;
			int x = (int) (MainGame.PANW*Math.random());
			int y = (int) (MainGame.PANH*Math.random());
			for(int i=0;i<spawnNum;i++) {
				entities.add(new Raptor(x, y));
			}
		}
		if(time%tRexSpawnTime==0) {//every few seconds spawns an enemy
			entities.add(new TRex());
		}
		if(time%pterodactylSpawnTime==0) {
			entities.add(new Pterodactyl());
		}
		

		if(time%hpSpawnTime==0) {
			
			entities.add(new Gun());
		}
	}
}

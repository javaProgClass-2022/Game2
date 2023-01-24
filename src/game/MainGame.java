package game;

/* Add comments here
 * 
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;

//import swing_pgm.MigEvent.Exit;
//import swing_pgm.MigEvent.Submit;



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
	final static int PFW = PANW*3;
	final static int PFH = PANH*3;
	final static int spriteW = 16;
	final static int spriteH = 16;
	final static int spriteMAXROW = 4;
	final static int spriteMAXFRAME = 2; 


	// quantity of bullets in a magazine
	static int magazine = 99999999;
	static int level = 1;
	static boolean exit = false;

	/***** instance variables (global) *****/
	DrawingPanel drPanel = new DrawingPanel();
	Image imgPlayer = null;
	static Player p;
	static BetterKeyListener bKeyl= new BetterKeyListener();
	int enemySpawnTime = 100;
	int hpSpawnTime = 50;
	int time;
	int levelDelay=10000; //how long between levels
	int rowNum = 0; //row in sprite image
	int frame = 0;  //column in sprite image

	/**** ArrayLists ****/
	//stores player, enemies, obstacles and eventually, powerups
	static ArrayList<Entity> entities = new ArrayList<Entity>();
	static ArrayList<Bullet> bullets = new ArrayList<Bullet>();

	//constructor
	MainGame() {
		createAndShowGUI();
		startTimer();
		setupObjects();
		imgPlayer = loadImage("PlayerSprites.png");
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

	static BufferedImage loadImage(String filename) {
		BufferedImage img = null; 
		try {
			img = ImageIO.read(new File(filename));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "An image failed to load: " + filename , "ERROR", JOptionPane.ERROR_MESSAGE);
		} 
		return img;
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

					if (entities.get(i) instanceof Enemy) {
						g2.setColor(new Color(28, 124, 25));
						g2.fillRect(vx, vy, entities.get(i).width, entities.get(i).height);
					} else if (entities.get(i) instanceof Obstacle) {
						g2.setColor(Color.blue);
						g2.fillRect(vx, vy, entities.get(i).width, entities.get(i).height);
					} else {
						g2.setColor(Color.black);
						g2.fillRect(vx, vy, entities.get(i).width, entities.get(i).height);
					}
					//print this if image is found
					if (imgPlayer != null) {
						g2.drawImage(imgPlayer, 
								PANW/2+spriteW/2, PANH/2+spriteH/2, PANW/2-spriteW/2, PANH/2-spriteH/2,  //destination
								(frame+1) * spriteW, (rowNum+1) * spriteH, frame * spriteW, rowNum * spriteH, 
								null);
					} else {
						//print if not found as default
						g2.setColor(Color.BLUE);
						g2.fillRect(vx, vy, entities.get(i).width, entities.get(i).height);
					}
				}

				int margin = PANW - 180;
				g2.setColor(Color.WHITE);
				g2.fillRect(PANW - 200, PANH - 160, 200, 160);
				g2.setColor(Color.GREEN);
				g2.fillRect(margin, PANH - 105, (int)(Player.health/100.0*160), 10);
				g2.fillRect(margin, PANH - 50, (int)(magazine/99999999.0*160), 10);
				g2.setColor(Color.BLACK);
				g2.drawString("Level: "+ level, margin, PANH - 130);
				g2.drawString("Health", margin, PANH - 110);
				g2.drawString("Bullets Left", margin, PANH - 55);
				g2.drawRect(margin, PANH - 105,  160, 10);
				g2.drawRect(margin, PANH - 50,  160, 10);

				String weaponType;
				switch(p.gun) {
				case shotgun: 
					weaponType = "Shotgun";
					break;

				case assaultRifle:
					weaponType = "Assault Rifle";
					break;

				case sniperRifle: 
					weaponType = "Sniper Rifle";
					break;

				default: 
					weaponType = "Pistol";
				}

				g2.drawString("Weapon: " + weaponType, margin, PANH - 75);

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
	class Exit implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			exit = true;
			System.out.println("exit");
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

			//once it reaches the end of the sprite image row, set back to beginning
			// if (time) loop slows "running"
			if (time % 10 == 0) {
				frame++; //chooses next frame in sprite image
				if (frame == spriteMAXFRAME) {
					frame = 0;
				} 
			}

			//determines the sprite image row based on the direction of movement of the player
			switch(p.direction) {
			case "up":
				rowNum = 3;
				break;
			case "down":
				rowNum = 0;
				break;
			case "left":
				rowNum = 1;
				break;
			case "right":
				rowNum = 2;
				break;
			}

			Spawn();
			p.move();
			drPanel.repaint();

			if(time % 50 == 0) {

			}

			if (time % 1000 == 0) {
				level += 1;
				for(Entity entity: entities) {
					if (entity instanceof Enemy) {
						entity.aspeed += level / 2;
					}
				}
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
			int vx = e.getX();
			int vy = e.getY();

			int x = p.x+vx-CX;
			int y = p.y+vy-CY;


			if (magazine != 0) {

				switch(p.gun) {
				case shotgun: 
					bullets.add(new Shotgun(p.x, p.y, x, y + (int)Math.floor(Math.random() * 26), Player.vx, Player.vy));
					bullets.add(new Shotgun(p.x, p.y, x + (int)Math.floor(Math.random() * 26), y, Player.vx, Player.vy));
					bullets.add(new Shotgun(p.x, p.y, x - (int)Math.floor(Math.random() * 26), y, Player.vx, Player.vy));
					bullets.add(new Shotgun(p.x, p.y, x, y - (int)Math.floor(Math.random() * 26), Player.vx, Player.vy));
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
		if(time%enemySpawnTime==0) {//every few seconds spawns an enemy
			entities.add(new Enemy());
		}
		//healthpack
		if(time%hpSpawnTime==0) {
			entities.add(new Healthpack());
		}
	}
}
package game;

/* Oh no, your time machine broke, and you got stuck in the mesozoic
 * You have no way of getting back, but you would rather survive a little longer
 * arrowkeys to move, click to fire bullets. Don't let the dinosaurs touch you
 * when you run out of bullets, collect a new gun (yellow) to refill your ammo and change your weapon type
 * when you're low on health, collect a healthpack (pink) to replenish some
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;

public class MainGame {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainGame();
			}
		});
	}

	/***** constants *****/
	final static int PANW = 1000; // 900
	final static int PANH = 600; // 800
	final static int TIMERSPEED = 10;
	final static int CX = PANW / 2;//camera center (player location on screen)
	final static int CY = PANH / 2;
	final static int PFW = PANW * 3; //(play field dimensions)
	final static int PFH = PANH * 3;
	final static Rectangle background = new Rectangle(0, 0, PFW, PFH);

	// quantity of bullets in a magazine
	static int magazine = 50;

	static int level = 1;
	static Player p;
	static BetterKeyListener bKeyl = new BetterKeyListener();

	/***** instance variables (global) *****/
	DrawingPanel drPanel = new DrawingPanel();
	int triceratopsSpawnTime = 300;
	int velociraptorSpawnTime = 1500;
	int tRexSpawnTime = 80000;
	int pterodactylSpawnTime = 60000;
	int hpSpawnTime = 2000;
	int gunSpawnTime = 3000;
	int time;
	int levelDelay = 3000; // how long between levels
	
	/**** ArrayLists ****/
	// stores player, enemies, obstacles and eventually, powerups
	static ArrayList<Entity> entities = new ArrayList<Entity>();
	static ArrayList<Bullet> bullets = new ArrayList<Bullet>();

	JLabel end;
	JLabel endSc;
	JLabel endLv;
	Timer timer;
	boolean dead = false;

	// constructor
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
		int n = 50; // number of obstacles to create
		for (int i = 0; i < n; i++) {
			entities.add(new Obstacle());
		}
	}

	void createAndShowGUI() {
		JFrame frame = new JFrame("Murder in the Mesozoic");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		frame.add(drPanel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	void startTimer() {
		timer = new Timer(TIMERSPEED, new TL1());
		timer.setInitialDelay(10);
		timer.start();
	}

	class DrawingPanel extends JPanel {
		DrawingPanel() {
			this.setBackground(Color.LIGHT_GRAY);
			this.setPreferredSize(new Dimension(PANW, PANH)); // remember that the JPanel size is more accurate than
																// JFrame.
			this.addKeyListener(bKeyl);
			this.setFocusable(true);// required to make keyListener work
			this.addMouseListener(new BulletCoordinates());
			this.setBackground(Color.GRAY);

			//set up death screen
			end = new JLabel("Dead");
			this.add(end);
			end.setSize(300, 400);
			end.setFont(new Font("Serif", Font.PLAIN, 300));
			end.setVisible(false);

			endSc = new JLabel("You survived " + time / 100 + " seconds");
			this.add(endSc);
			endSc.setSize(100, 100);
			endSc.setFont(new Font("Serif", Font.PLAIN, 100));
			endSc.setVisible(false);

			endLv = new JLabel("You got to level " + level);
			this.add(endLv);
			endLv.setSize(50, 50);
			endLv.setFont(new Font("Serif", Font.PLAIN, 50));
			endLv.setVisible(false);
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			// draw background
			int vx = background.x - p.x + CX;
			int vy = background.y - p.y + CY;
			g2.setColor(new Color(240, 245, 240));
			g2.fillRect(vx, vy, background.width, background.height);

			// draw all the entities
			for (int i = 0; i < entities.size(); i++) {// does camera calculations and displays each entity
				vx = entities.get(i).x - p.x + CX;
				vy = entities.get(i).y - p.y + CY;

				if (vx < PANW && vx > -100 && vy > -100 && vy < PANH) {
					g2.setColor(entities.get(i).color);
					g2.fillRect(vx, vy, entities.get(i).width, entities.get(i).height);
					//draw GUI
					int margin = PANW - 180;
					g2.setColor(Color.WHITE);
					g2.fillRect(PANW - 200, PANH - 160, 200, 160);
					g2.setColor(Color.GREEN);
					g2.fillRect(margin, PANH - 105, (int) (Player.health / 100.0 * 160), 10);
					g2.fillRect(margin, PANH - 50, (int) (magazine / 50.0 * 160), 10);
					g2.setColor(Color.BLACK);
					g2.drawString("Level: " + level, margin, PANH - 130);
					g2.drawString("Health", margin, PANH - 110);
					g2.drawString("Bullets Left", margin, PANH - 55);
					g2.drawRect(margin, PANH - 105, 160, 10);
					g2.drawRect(margin, PANH - 50, 160, 10);
				}
			}
			//draw bullets
			for (Bullet bullet : bullets) {
				vx = (int) (bullet.x - p.x + CX);
				vy = (int) (bullet.y - p.y + CY);
				if (vx < PANW && vx > -100 && vy > -100 && vy < PANH) {
					g2.setColor(Color.black);
					g2.fillRect(vx, vy, bullet.width, bullet.height);
				}
			}

		}
	}

	class TL1 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!dead) {

				time++;

				for (int i = 0; i < entities.size(); i++) {// move all the enemies. Don't move obstacles
					if (entities.get(i) instanceof Enemy) {
						entities.get(i).move(p);
					}
				}

				for (int i = bullets.size() - 1; i >= 0; i--) {//move all the bullets

					bullets.get(i).move();
				}

				Spawn();
				p.move();
				drPanel.repaint();

				if (time % 1000 == 0) {
					level += 1;
					triceratopsSpawnTime = 550 / level;
					velociraptorSpawnTime = 900 / level;
					if (level > 1) {
						tRexSpawnTime = 1500 / level;
						pterodactylSpawnTime = 1700 / level;
					}
				}
				if (Player.health <= 0) {
					die();
				}
				if (Player.health > 100) {
					Player.health = 100;
				}
			}
		}
	}

	class BulletCoordinates implements MouseInputListener {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			//create bullets
			if (magazine != 0) {
				int vx = e.getX();
				int vy = e.getY();

				int x = p.x + vx - CX;
				int y = p.y + vy - CY;

				switch (p.gun) {
				case shotgun:

					double distance = Math.sqrt((x - p.x) * (x - p.x) + (x - p.y) * (x - p.y)) / 40;

					bullets.add(new Shotgun(p.x, p.y, x,
							y + (int) Math.floor(Math.random() * (((y - p.x) - (x - p.x)) / distance)), Player.vx,
							Player.vy));
					bullets.add(new Shotgun(p.x, p.y,
							x + (int) Math.floor(Math.random() * (((x - p.x) - (y - p.y)) / distance)), y, Player.vx,
							Player.vy));
					bullets.add(new Shotgun(p.x, p.y,
							x - (int) Math.floor(Math.random() * (((x - p.x) - (y - p.y)) / distance)), y, Player.vx,
							Player.vy));
					bullets.add(new Shotgun(p.x, p.y, x,
							y - (int) Math.floor(Math.random() * (((y - p.y) - (x - p.x)) / distance)), Player.vx,
							Player.vy));
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
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseDragged(MouseEvent e) {
		}

		@Override
		public void mouseMoved(MouseEvent e) {
		}
	}

	void Spawn() {
		// Enemy
		if (time % triceratopsSpawnTime == 0) {// every few seconds spawns an enemy
			Triceratops n = new Triceratops();
			if (!n.collide()) {
				entities.add(n);

			}
		}
		if (time % velociraptorSpawnTime == 0) {// every few seconds spawns an enemy
			int spawnNum = 5; //spawn in a pack
			int x = (int) (MainGame.PANW * Math.random());
			int y = (int) (MainGame.PANH * Math.random());
			for (int i = 0; i < spawnNum; i++) {
				Raptor n = new Raptor(x, y);
				if (!n.collide()) {
					entities.add(n);
				}
			}
		}
		if (time % tRexSpawnTime == 0) {// every few seconds spawns an enemy
			TRex n = new TRex();
			if (!n.collide()) {
				entities.add(n);
			}
		}
		if (time % pterodactylSpawnTime == 0) {
			Pterodactyl n = new Pterodactyl();
			if (!n.collide()) {
				entities.add(n);
			}
		}

		// healthpack
		if (time % hpSpawnTime == 0) {
			Healthpack n = new Healthpack();
			if (!n.collide()) {
				entities.add(n);
			}
		}
		if (time % gunSpawnTime == 0) {
			Gun n = new Gun();
			if (!n.collide()) {
				entities.add(n);
			}
		}
	}

	void die() {
		endSc.setText("You survived " + time / 100 + " seconds");
		endLv.setText("You got to level " + level);
		end.setVisible(true);
		endSc.setVisible(true);
		endLv.setVisible(true);
		dead = true;
	}
}
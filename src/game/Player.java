package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Entity {

	static int health = 100;
	String direction = ""; //direction of the player is identified to chose correct sprite image
	static double speed = 5;
	static int vx = 0;
	static int vy = 0;
	static boolean iframe = false;
	Gun gun;

	Player(){
		height=5;
		width=5;
		x = 400; 
		y = 450;	
		gun = Gun.assaultRifle;
	}



	void move(){//does movement when it gets arrow keys. If rather than else if allows diagonals
		int y1 = y;
		int x1 = x;
		System.out.println(health);
		if(MainGame.bKeyl.up) {
			direction = "up";
			y-=speed;

		}
		if(MainGame.bKeyl.down) {
			direction = "down";
			y+=speed;


		}
		if(MainGame.bKeyl.left) {
			direction = "left";
			x-=speed;

		}
		if(MainGame.bKeyl.right) {
			direction = "right";
			x+=speed;
		}

		if(super.collide()) {//if it collides after moving, don't allow movement
			health -= 1;

			y = y1;
			x = x1;
			System.out.println(health);
		}
		iframe = false;
	}	


}

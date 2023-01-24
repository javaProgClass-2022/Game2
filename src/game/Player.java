package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Entity {

	static int health = 100;
	static double speed = 3;
	static int vx = 0;
	static int vy = 0;
	static boolean iframe = false;
	static GunType gun;
	String direction = ""; //direction of the player is identified to chose correct sprite image

	Player(){
		height= 10;
		width= 10;
	
		gun = GunType.shotgun;

		x = 450; 
		y = 400;	
	}



	void move(){//does movement when it gets arrow keys. If rather than else if allows diagonals
		int y1 = y;
		int x1 = x;

		if(MainGame.bKeyl.down&&MainGame.bKeyl.right||MainGame.bKeyl.up&&MainGame.bKeyl.left||MainGame.bKeyl.up&&MainGame.bKeyl.right||MainGame.bKeyl.down&&MainGame.bKeyl.left) {
			speed = 3;
		}else {
			speed = 4.24264;
		}

		if(MainGame.bKeyl.up) {
			direction = "up";
			y-=speed;

		}
		if(MainGame.bKeyl.down) {
			direction = "down";
			y+=speed;


		}
		
		if(super.collide()) {//if it collides after moving, don't allow movement
			y = y1;
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

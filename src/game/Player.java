package game;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player extends Entity {
	
	int health = 100;
	static double speed = 5;
	static int vx = 0;
	static int vy = 0;
	
	
	Gun gun;
	
	Player(){
		height= 10;
		width= 10;
		x = 450; 
		y = 400;
		
		gun = Gun.assaultRifle;
	}
	
	void move(){//does movement when it gets arrow keys. If rather than else if allows diagonals
		int y1 = y;
		int x1 = x;
		if(MainGame.bKeyl.up) {
			y-=speed;
//			if (vy != -speed) {
//				vy -= speed;
//			} 
		}
		if(MainGame.bKeyl.down) {
			y+=speed;
//			if (vy != speed) {
//				vy += speed;
//			}
			
		}
		if(MainGame.bKeyl.left) {
			x-=speed;
//			if (vx != -speed) {
//				vx -= speed;
//			} 
		}
		if(MainGame.bKeyl.right) {
			x+=speed;
//			if (vx != speed) {
//				vx += speed;
//			}
		}
		
		if(super.collide()) {//if it collides after moving, don't allow movement
			health -= 1;

			y = y1;
			x = x1;
		}
	}	
}
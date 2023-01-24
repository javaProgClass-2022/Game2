package game;

import java.awt.Color;

public class Player extends Entity {

	static int health = 100;
	static double speed = 3;
	static int vx = 0;
	static int vy = 0;
	static boolean iframe = false;
	static GunType gun;
	
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
			y-=speed;

		}
		if(MainGame.bKeyl.down) {
			y+=speed;

			
		}
		
		if(super.collide()) {//if it collides after moving, don't allow movement
			y = y1;
		}
		
		if(MainGame.bKeyl.left) {
			x-=speed;

		}
		if(MainGame.bKeyl.right) {
			x+=speed;
		}
		
		if(super.collide()) {//if it collides after moving, don't allow movement
			x = x1;
		}
		iframe = false;
	}	
}

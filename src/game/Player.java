package game;

import java.awt.Color;
import java.awt.Graphics2D;


public class Player extends Entity {
	int playerHealth = 100;
	double speed = 5;
	Player(){
		height=20;
		width=20;
		x = 400; 
		y=500;
	}
	
	void move(){//does movement when it gets arrow keys. If rather than else if allows diagonals
		int y1 = y;
		int x1 = x;
		if(MainGame.bKeyl.up) {
			y-=speed;
		}
		if(MainGame.bKeyl.down) {
			y+=speed;
		}
		if(MainGame.bKeyl.left) {
			x-=speed;
		}
		if(MainGame.bKeyl.right) {
			x+=speed;
		}
		if(super.Collide()) {//if it collides after moving, don't allow movement
			playerHealth -= 1;
//			System.out.println(playerHealth);//test
			y = y1;
			x = x1;
		}
	}	
}

package game;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player extends Entity {
<<<<<<< Updated upstream
	int health = 100;
	double speed = 5;
	boolean iframe = false;
=======
	static int health = 100;
	static double speed = 5;
	static boolean iframe = false;
>>>>>>> Stashed changes
	Player(){
		height=5;
		width=5;
		x = 400; 
		y = 450;
	}
	
	void move(){//does movement when it gets arrow keys. If rather than else if allows diagonals
		int y1 = y;
		int x1 = x;
		System.out.println(health);
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
		if(super.collide()) {//if it collides after moving, don't allow movement
<<<<<<< Updated upstream
			y = y1;
			x = x1;
		}
		iframe=false;
=======

			y = y1;
			x = x1;
		}
		iframe = false;
>>>>>>> Stashed changes
	}	
}

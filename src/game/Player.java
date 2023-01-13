package game;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player {
	int height=5, width=5;
	int x = 400, y=500;
	double speed = 5;
	
	void move(){//does movement when it gets arrow keys. If rather than else if allows diagonals
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
	}	
}

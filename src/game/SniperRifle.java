package game;

import java.awt.Rectangle;

public class SniperRifle extends Bullet {
	int penetrations = 0;

	SniperRifle(int startX, int startY, int targetX, int targetY, int initX, int initY) {
		super(startX, startY, targetX, targetY, initX, initY);

		damage = 10;

		width = 4; 
		height = 4;
		
		speed = 8;
		
		range = 800;
		
		magazine = 15;
	}

	boolean collide(){//goes through entities and checks if anything intersects with this
		boolean c =false;
		for(int i = 0;i<MainGame.entities.size();i++) {
			if(MainGame.entities.get(i) instanceof Enemy) {
				Rectangle intersection = this.intersection(MainGame.entities.get(i));
				if (!intersection.isEmpty()) {
					if (penetrations < 2) {
						penetrations += 1;
					} else {
						c = true;
					}
					MainGame.entities.get(i).health -=damage;
					if (MainGame.entities.get(i).health<=0) {
						MainGame.entities.remove(i);
					}
					break;
				}
			}
		}
		return c;
	}
	
	
}

package game;

import java.awt.Rectangle;

public class Bullet extends Rectangle {
	double dirX, dirY;
	int damage = 10;
	double speed = 6;
	int startX, startY;
	int range = 200;
	
	Bullet(int startX, int startY, int targetX, int targetY, int initX, int initY) {
		this.x = startX;
		this.y = startY;
		
		this.startX = startX;
		this.startY = startY;
		
		double vx = targetX - startX;
        double vy = targetY - startY;

        double distance = Math.sqrt(vx * vx + vy * vy);

        this.dirX = ((vx / distance) * speed) + initX;
        this.dirY = ((vy / distance) * speed) + initY;
        
    	width = 4; height = 4;
    	
    	
//    	damage = 10;
	}
	
	void move() {
		
		x += dirX;
        y += dirY;
        if ((Math.abs(x - startX) + Math.abs(y - startY)) > range) {
        	MainGame.bullets.remove(this);
        } else if (collide()) {
        	MainGame.bullets.remove(this);
        }
	}
	
	boolean collide(){//goes through entities and checks if anything intersects with this
		boolean c =false;
		for(int i = 0;i<MainGame.entities.size();i++) {
			if(MainGame.entities.get(i) instanceof Enemy) {
				Rectangle intersection = this.intersection(MainGame.entities.get(i));
				if (!intersection.isEmpty()) {
					c = true;
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


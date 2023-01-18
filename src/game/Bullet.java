package game;

import java.awt.Rectangle;

public class Bullet extends Rectangle{
	double dirX, dirY;
	int damage;
	double speed = 6;
	
	Bullet(int startX, int startY, int targetX, int targetY) {
		this.x = startX;
		this.y = startY;
		
		double vx = targetX - startX;
        double vy = targetY - startY;

        double distance = Math.sqrt(vx * vx + vy * vy);

        this.dirX = ((vx / distance) * speed);
        this.dirY = ((vy / distance) * speed);
        
    	width = 4; height = 4;
    	damage = 10;
	}
	
	void move() {
		
		x += dirX;
        y += dirY;
        if (collide()) {
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
					System.out.println("yep");
					i = 10000;
				}
			}
		}
		return c;
	}
}
package game;

import java.awt.Rectangle;

public class Bullet extends Rectangle{
	double dirX, dirY;
	int damage = 10;
	double xx,yy;
	double speed = 6;
	
	Bullet(int startX, int startY, int targetX, int targetY) {
		xx = startX;
		yy = startY;
		
		double vx = targetX - startX;
        double vy = targetY - startY;

        double distance = (int)Math.sqrt(vx * vx + vy * vy);

        dirX = ((vx / distance) * speed);
        dirY = ((vy / distance) * speed);

    	width = 2; height = 2;
        
	}
	
	void move() {
		
		xx += dirX;
        yy += dirY;
        x=(int)xx;
        y=(int)yy;
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
					if(!(MainGame.entities.get(i) instanceof Powerup)){
						MainGame.entities.get(i).health -=damage;
						if (MainGame.entities.get(i).health<=0) {
							MainGame.entities.remove(i);
						}
						break;
					}
				}
			}
		}
		return c;
	}
}

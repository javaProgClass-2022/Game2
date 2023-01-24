package game;

import java.awt.Rectangle;

public class Bullet extends Rectangle {
	double dirX, dirY;
	int damage = 10;
	double xx,yy;
	double speed = 6;
	int startX, startY;
	int range = 200;
	
	Bullet(int startX, int startY, int targetX, int targetY, int initX, int initY) {
		xx = startX;
		yy = startY;
		
		this.startX = startX;
		this.startY = startY;
		
		double vx = targetX - startX;
        double vy = targetY - startY;

        double distance = (int)Math.sqrt(vx * vx + vy * vy);

        dirX = ((vx / distance) * speed);
        dirY = ((vy / distance) * speed);


        this.dirX = ((vx / distance) * speed) + initX;
        this.dirY = ((vy / distance) * speed) + initY;
        
    	width = 4; height = 4;

	}
	
	void move() {
		
	xx += dirX;
        yy += dirY;
        x=(int)xx;
        y=(int)yy;

        if ((Math.abs(x - startX) + Math.abs(y - startY)) > range) {
        	MainGame.bullets.remove(this);
        } else if (collide()) {
        	MainGame.bullets.remove(this);
        }
    }

	
	boolean collide(){//goes through entities and checks if anything intersects with this
		boolean c =false;
		for(int i = 0;i<MainGame.entities.size();i++) { 

			if(MainGame.entities.get(i) instanceof Enemy && !(MainGame.entities.get(i) instanceof Powerup)) {
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


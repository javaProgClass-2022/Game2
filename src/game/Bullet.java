package game;

public class Bullet {
	int startX, startY;
	int dirX, dirY;
	int width = 2, height = 2;
	
	double speed = 6;
	
	Bullet(int startX, int startY, int targetX, int targetY) {
		this.startX = startX;
		this.startY = startY;
		
		double vx = targetX - startX;
        double vy = targetY - startY;

        double distance = (int)Math.sqrt(vx * vx + vy * vy);

        this.dirX = (int)((vx / distance) * speed);
        this.dirY = (int)((vy / distance) * speed);
        
//        System.out.println(vx);
//		System.out.println(vy);
//		System.out.println(distance);
//		System.out.println();
        
	}
	
	void move() {
		
<<<<<<< Updated upstream
		startX += dirX;
        startY += dirY;
=======
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
					i = 10000;
				}
			}
		}
		return c;
>>>>>>> Stashed changes
	}
}

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
        
//      System.out.println(vx);
//		System.out.println(vy);
//		System.out.println(distance);
//		System.out.println();
        
	}
	
	void move() {
		
		startX += dirX;
        startY += dirY;
	}
}

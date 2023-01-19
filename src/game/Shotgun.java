package game;

public class Shotgun extends Bullet {
	Shotgun(int startX, int startY, int targetX, int targetY) {
		super(startX, startY, targetX, targetY);
		
			
		damage = 3;

		width = 3; 
		height = 3;
		
		speed = 4;
		
		range = 150;
	}
}

package game;

public class Shotgun extends Bullet {

	Shotgun(int startX, int startY, int targetX, int targetY, int initX, int initY) {
		super(startX, startY, targetX, targetY, initX, initY);
	
		damage = 3;

		width = 3; 
		height = 3;
		
		speed = 4;
		
		range = 200;
	}
}

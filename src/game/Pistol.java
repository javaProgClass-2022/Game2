package game;

public class Pistol extends Bullet {

	private static final long serialVersionUID = 1L;

	
	Pistol(int startX, int startY, int targetX, int targetY, int initX, int initY) {
		super(startX, startY, targetX, targetY, initX, initY);
		
			
		damage = 10;

		width = 3; 
		height = 3;
		
		speed = 5;
		
		range = 300;
		
		magazine = 35;
	}
	
	
}
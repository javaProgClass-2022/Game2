package game;

public class AssaultRifle extends Bullet {

	AssaultRifle(int startX, int startY, int targetX, int targetY) {
		super(startX, startY, targetX, targetY);
		
		damage = 5;

		width = 4; 
		height = 4;
		
		speed = 7;
		
		range = 500;
	}

}

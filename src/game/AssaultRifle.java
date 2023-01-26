package game;

public class AssaultRifle extends Bullet {

	AssaultRifle(int startX, int startY, int targetX, int targetY, int initX, int initY) {
		super(startX, startY, targetX, targetY, initX, initY);
		
		damage = 5;

		width = 4; 
		height = 4;
		
		speed = 7;
		
		range = 500;
	}

}

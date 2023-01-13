package game;

public class Enemy {
	int hp = 10;
	int speed = 10;
	int x,y;
	Enemy(){
		//spawns at random coordinates for testing purposes 
		x = (int) (MainGame.PANW*Math.random());
		y = (int) (MainGame.PANH*Math.random());
	}
}

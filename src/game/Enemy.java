package game;

public class Enemy extends Entity{
	int hp = 10;
	int speed = 10;
	Enemy(){
		//spawns at random coordinates for testing purposes 
		x = (int) (MainGame.PANW*Math.random());
		y = (int) (MainGame.PANH*Math.random());
		width = 5;
		height = 5;
	}
}

package game;


public class Enemy extends Entity{
	int enemyHealth = 100;
	int speed = 10;
	Enemy(){
		//spawns at random coordinates for testing purposes 
		x = (int) (MainGame.PANW*Math.random());
		y = (int) (MainGame.PANH*Math.random());

		width = 15;
		height = 15;
	}
}

package game;

public class Enemy extends Entity{

	Enemy(){
		//spawns at random coordinates for testing purposes 
		x = (int) (MainGame.PANW*Math.random());
		y = (int) (MainGame.PANH*Math.random());
		width = 10;
		height = 10;
		health = 10;
		aspeed = 5;
	}
}


package game;

public class Enemy extends Entity{
	int health =100;
	int hp = 10;
	Enemy(){
		//spawns at random coordinates for testing purposes 
		x = (int) (MainGame.PANW*Math.random());
		y = (int) (MainGame.PANH*Math.random());

		width = 15;
		height = 15;

		health = 10;
		damage = 1;
		aspeed = 5;
	}	
}

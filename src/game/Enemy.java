package game;

public class Enemy extends Entity{
	int health =100;
	int hp = 10;
	Enemy(){
		//spawns at random coordinates for testing purposes 
		x = (int) (MainGame.PANW*Math.random());
		y = (int) (MainGame.PANH*Math.random());
		
		
		
		width = 5;
		height = 5;
		aspeed = 5;
		damage = 1;
	}
	
}

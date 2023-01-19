package game;

public class Enemy extends Entity{
	int health =100;
	int hp = 10;
	Enemy(){
		//spawns at random coordinates for testing purposes 
		x = (int) (MainGame.PANW*Math.random());
		y = (int) (MainGame.PANH*Math.random());
<<<<<<< Updated upstream
		
		
		
		width = 5;
		height = 5;
=======
		width = 10;
		height = 10;
		health = 10;
		damage = 1;
>>>>>>> Stashed changes
		aspeed = 5;
		damage = 1;
	}
	
}

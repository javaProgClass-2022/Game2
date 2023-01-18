package game;

public class Healthpack extends Powerup{
	Healthpack(){
		//spawns at random coordinates for testing purposes 
		x = (int) (MainGame.PANW*Math.random());
		y = (int) (MainGame.PANH*Math.random());
		width = 20;
		height = 20;
		aspeed=10;
		damage = -20;
	}
}

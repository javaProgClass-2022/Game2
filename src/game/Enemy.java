package game;

public class Enemy extends Entity{

	Enemy(){
		//spawns at random coordinates for testing purposes 
		int n = (int) (200*Math.random());
		if(n>=100) {
			x=MainGame.p.x-MainGame.PANW/2-n;
		}
		else {
			x=MainGame.p.x+MainGame.PANW/2+n;
		}
		
		int m = (int) (200*Math.random());
		if(m>=100) {
			y=MainGame.p.y-MainGame.PANH/2-m;
		}
		else {
			y=MainGame.p.y+MainGame.PANH/2+m;
		}
		
		width = 15;
		height = 15;

		health = 10;
		damage = 1;
		aspeed = 5;
	}	
}
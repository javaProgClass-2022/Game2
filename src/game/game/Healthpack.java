package game;

import java.awt.Color;

public class Healthpack extends Powerup{
	Healthpack(){
		
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
		
		width = 20;
		height = 20;
		aspeed=10;
		damage = -20;
		color = new Color(200,100,100);
	}
}

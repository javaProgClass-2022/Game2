package game;

import java.awt.Color;

public class Raptor extends Enemy{
	Raptor(int x, int y){
		this.x=(int)(x+40*Math.random()-20);
		this.y=(int)(y+40*Math.random()-20);
		if(collide()) {
			this.x=(int)(x+40*Math.random()-20);
			this.y=(int)(y+40*Math.random()-20);
		}//try one more time
		health = 5;
		aspeed = 3.8;
		damage = 2;
		width = 10;
		height = 10;
		color = new Color(112, 37, 37);
	}
}

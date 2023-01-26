package game;

import java.awt.Color;

public class Obstacle extends Entity{
	Obstacle(){//empty constructor places the obstacle randomly
		x = (int) (MainGame.PFW*Math.random());
		y = (int) (MainGame.PFH*Math.random());
		width = (int) (100*Math.random()+20);
		height = (int) (100*Math.random()+20);
		while(collide()) {
			x = (int) (MainGame.PFW*Math.random());
			y = (int) (MainGame.PFH*Math.random());
		}
		aspeed=0;
		if(width/height>1.5||height/width>1.5) {
			color = new Color(156, 86, 51);
		}
		else {
			color = new Color(20, 50, 150);
		}
		
	}
	
	Obstacle(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		aspeed=0;
		color = Color.blue;
	}
}

package game;

public class Obstacle extends Entity{
	Obstacle(){//empty constructor places the obstacle randomly
		x = (int) (MainGame.PFW*Math.random());
		y = (int) (MainGame.PFH*Math.random());
		width = (int) (100*Math.random()+5);
		height = (int) (100*Math.random()+5);
		aspeed=0;
	}
	Obstacle(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		aspeed=0;
	}
}
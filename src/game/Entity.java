package game;

import java.awt.Rectangle;

public class Entity extends Rectangle{

	int damage;//damage on touch
	int aspeed;//autospeed. For automatic movement

	int health;
	boolean collide(){//goes through entities and checks if anything intersects with this
		boolean c =false;
		for(int i = 0;i<MainGame.entities.size();i++) {
			if(MainGame.entities.get(i)!=this) {
				Rectangle intersection = this.intersection(MainGame.entities.get(i));
				//intersection is empty if doesn't intersect. Other values irrelevant.
				if (!intersection.isEmpty()) {
					c = true;
					if(MainGame.entities.get(i)==MainGame.p&&!MainGame.p.iframe) {

						MainGame.p.iframe = true;
						Player.health-=damage;
					}
					break;
				}
			}
		}
		return c;
	}
	void move(Entity m){
		int x1=x;
		int y1=y;
		double deltaX = x-m.x;
		double deltaY = y-m.y;
		int h;
		int v;
		
		//detect which general direction movement is in
		if(x>m.x)h=-1;
		else h=1;
		if(y>m.y)v=-1;
		else v=1;
		
		double angle = Math.abs(Math.atan(deltaY/deltaX));

		//move. If it collides, don't move in that direction
		y= (int) (v*aspeed*Math.sin(angle))+y;
		if(collide()) {
			y=y1;
		}
		x= (int) (h*aspeed*Math.cos(angle))+x;
		if(collide()) {
			x=x1;
		}
		//if vertical. NaN != NaN and angle is NaN if line is vertical
		if(angle!=angle) {
			x=x1;
			y=h*aspeed;
		}
		//various failed attempts at preventing enemies from getting stuck on walls
		/*if(x==x1&&y==y1) {
			//move full speed perpendicular to target
			Still really jittery
			if(Math.sin(angle)>Math.cos(angle)) {
				x=v*aspeed+x1;
			}
			else {
				y=h*aspeed+y1;
			}
			
			
			//very jittery version that makes it move in a random direction
			double theta = 2*Math.PI*Math.random();
			y= (int) (aspeed*Math.sin(theta))+y;
			x= (int) (aspeed*Math.cos(theta))+x;
			if(collide()) {
				y=y1;
				x=x1;
			}
		}*/
	}
}
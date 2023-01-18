package game;

import java.awt.Rectangle;

public class Powerup extends Enemy {

	//prevent actual movement
	@Override
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
		x= (int) (h*aspeed*Math.cos(angle))+x;

		collide();
		x=x1;
		y=y1;
	}
	
	//destroys itself when hitting player
	@Override
	boolean collide(){//goes through entities and checks if anything intersects with this
		boolean c =false;
		for(int i = 0;i<MainGame.entities.size();i++) {
			if(MainGame.entities.get(i)!=this) {
				Rectangle intersection = this.intersection(MainGame.entities.get(i));
				if (!intersection.isEmpty()) {
					c = true;
					if(MainGame.entities.get(i)==MainGame.p&&!MainGame.p.iframe) {
						MainGame.p.health-=damage;
						MainGame.p.iframe=true;
						MainGame.entities.remove(this);
					}
					i = 100000;//exit the for loop
				}
			}
		}
		return c;
	}
}

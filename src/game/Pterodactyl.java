package game;

import java.awt.Color;
import java.awt.Rectangle;

public class Pterodactyl extends Enemy{
	Pterodactyl(){
		health = 5;
		aspeed = 2.3;
		damage = 2;
		width = 10;
		height = 10;
		color = new Color(200, 160, 160);
	}
	
	boolean collide(){//goes through entities and checks if anything intersects with this
	boolean c =false;
	for(int i = 0;i<MainGame.entities.size();i++) {
		if(MainGame.entities.get(i)!=this&&!(MainGame.entities.get(i) instanceof Obstacle)) {
			Rectangle intersection = this.intersection(MainGame.entities.get(i));
			//intersection is empty if doesn't intersect. Other values irrelevant.
			if (!intersection.isEmpty()) {
				c = true;
				if(MainGame.entities.get(i)==MainGame.p&&!Player.iframe) {

					Player.iframe = true;
					Player.health-=damage;
				}
				break;
			}
		}
	}
	return c;
}
}
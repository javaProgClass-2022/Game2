package game;

import java.awt.Rectangle;

public class Entity extends Rectangle{
	
	boolean Collide(){//goes through entities and checks if anything intersects with this
		boolean c =false;
		for(int i = 0;i<MainGame.entities.size();i++) {
			if(MainGame.entities.get(i)!=this) {
				Rectangle intersection = this.intersection(MainGame.entities.get(i));
				if (!intersection.isEmpty()) {
					c = true;
					i = 100000;
				}
			}
		}
		return c;
	}
}

package game;

import java.awt.Rectangle;

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
	
	
//	@Override
//	boolean collide() { //goes through entities and checks if anything intersects with this
//		boolean c =false;
//		for(int i = 0;i<MainGame.entities.size();i++) {
//			if(MainGame.entities.get(i) != this || (MainGame.entities.get(i) instanceof Player)) {
//				Rectangle intersection = this.intersection(MainGame.entities.get(i));
//				//intersection is empty if doesn't intersect. Other values irrelevant.
//				if (!intersection.isEmpty()) {
//					c = true;
//					if(MainGame.entities.get(i)==MainGame.p&&!MainGame.p.iframe) {
//
//						Player.iframe = true;
//						Player.health-=damage;
//					}
//					break;
//				}
//			}
//		}
//		return c;
//	}
}

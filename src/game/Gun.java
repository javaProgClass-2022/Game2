package game;
import java.awt.Rectangle;
import java.util.Random;

public class Gun extends Powerup {
	Gun(){
		//spawns at random coordinates for testing purposes 
		x = (int) (MainGame.PANW*Math.random());
		y = (int) (MainGame.PANH*Math.random());
		width = 20;
		height = 20;
		aspeed=10;
		damage = -1;
	}
	
	@Override
	boolean collide(){//goes through entities and checks if anything intersects with this
		boolean c =false;
		for(int i = 0;i<MainGame.entities.size();i++) {
			if(MainGame.entities.get(i)!=this) {
				Rectangle intersection = this.intersection(MainGame.entities.get(i));
				//intersection is empty if doesn't intersect. Other values irrelevant.
				if (!intersection.isEmpty()) {
					c = true;
					if(MainGame.entities.get(i)==MainGame.p&&!MainGame.p.iframe) {

						
						GunType[] values = GunType.values();
			            int length = values.length;
			            int randIndex = new Random().nextInt(length);
			            
			            while(values[randIndex] == Player.gun) {
			            	randIndex = new Random().nextInt(length);
			            }
			            
						Player.gun = values[randIndex];
						
						Player.iframe = true;
						MainGame.entities.remove(this);
					}
					break;
				}
			}
		}
		return c;
	}
}
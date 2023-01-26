package game;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;

public class Ammo extends Powerup {
	
	Ammo(){
		super();
		
	
				
		width = 20;
		height = 20;
		aspeed=10;
		damage = 0;
		color = Color.green;
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
					if(MainGame.entities.get(i)==MainGame.p&&!Player.iframe) {
						
						refillAmmo();
						
						Player.iframe = true;
						MainGame.entities.remove(this);
					}
					break;
				}
			}
		}
		return c;
	}
	
	static void refillAmmo() {
		switch(Player.gun) {
		case shotgun: 
			// I gave up (the spread is broken
			Player.ammo = Shotgun.magazine;
		break;
		
		case assaultRifle:
			Player.ammo = AssaultRifle.magazine;
		break;
		
		case sniperRifle: 
			Player.ammo = SniperRifle.magazine;
		break;
		
		default: 
			Player.ammo = Pistol.magazine;
		}
	}
}

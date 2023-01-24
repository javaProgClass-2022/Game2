package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BetterKeyListener implements KeyListener {
		boolean up,down,left,right;
		
		BetterKeyListener(){
			
		}
		
		@Override
		public void keyTyped(KeyEvent e) {
			// not being used? Yet
			
		}
		@Override
		public void keyPressed(KeyEvent e) {//when arrow keys are pressed, sets the corresponding variables to true
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				left=true;
				

				// momentum speed
				if (Player.vx != -Player.speed) {
					Player.vx -= Player.speed;
				} 
				
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				right=true;

				
				// momentum speed
				if (Player.vx != Player.speed) {
					Player.vx += Player.speed;
				} 
				
			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				up=true;
				
				// momentum speed
				if (Player.vy != -Player.speed) {
					Player.vy -= Player.speed;
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				down=true;
				
				// momentum speed
				if (Player.vy != Player.speed) {
					Player.vy += Player.speed;
				} 
			}
			
		}
		@Override
		public void keyReleased(KeyEvent e) {//when arrow keys are released, sets corresponding variables back to false
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				left=false;
				

				// momentum speed
				Player.vx = 0;
				
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				right=false;
				
				// momentum speed

        Player.vx = 0;
			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				up=false;

				
				// momentum speed
				Player.vy = 0;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				down=false;

				
				// momentum speed
				Player.vy = 0;
			}
		}
}
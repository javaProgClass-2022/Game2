package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BetterKeyListener implements KeyListener{
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
				
				if (Player.vx != -Player.speed) {
					Player.vx -= Player.speed;
				} 
				
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				right=true;
				if (Player.vx != Player.speed) {
					Player.vx += Player.speed;
				} 
				
			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				up=true;
				
				if (Player.vy != -Player.speed) {
					Player.vy -= Player.speed;
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				down=true;
				
				if (Player.vy != Player.speed) {
					Player.vy += Player.speed;
				} 
			}
			
		}
		@Override
		public void keyReleased(KeyEvent e) {//when arrow keys are released, sets corresponding variables back to false
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				left=false;
				
				Player.vx = 0;
				
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				right=false;
				Player.vx = 0;
			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				up=false;
				Player.vy = 0;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				down=false;
				Player.vy = 0;
			}
		}
}
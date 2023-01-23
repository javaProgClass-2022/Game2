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
				
				Player.vx = -(int)Player.speed;
				
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				right=true;
				Player.vx = (int)Player.speed;
				
			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				up=true;
				Player.vy = -(int)Player.speed;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				down=true;
				Player.vy = (int)Player.speed;
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
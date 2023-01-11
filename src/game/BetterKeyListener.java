package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BetterKeyListener implements KeyListener{
		boolean up,down,left,right;
		
		BetterKeyListener(){
			
		}
		
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				left=true;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				right=true;
			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				up=true;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				down=true;
			}
			
		}
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				left=false;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				right=false;
			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				up=false;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				down=false;
			}
		}
}

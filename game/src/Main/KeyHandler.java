package Main;
// class to handle keyboard in puts
// 
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

 public class KeyHandler implements KeyListener{
	 GamePanel gp;
	public boolean upPressed, downPressed,leftPressed,rightPressed,enterPressed,shotKeyPressed;
	
	
	public KeyHandler(GamePanel gp) {
		this.gp=gp;
	}
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();		// every key has its special key code
		
		// change titleState
		if(gp.gameState == gp.titleState) {
			if(code == KeyEvent.VK_UP) {
				gp.ui.comandNum--;
				
			}
			if(code == KeyEvent.VK_DOWN) {
				gp.ui.comandNum++;
				
			}
			if(code == KeyEvent.VK_ENTER) {
				if(gp.ui.comandNum==0) {
					gp.gameState = 1;
				}
				if(gp.ui.comandNum==1) {
					System.out.print("\n\n============================================================================================================\n");
					System.out.print("   This function is not theare in game till. \n   we will try to add this function as soon as posible\n   Thank You!!!!!!!");
					System.out.print("\n\n============================================================================================================\n");
					System.exit(0);
				}
				if(gp.ui.comandNum==2) {
					System.exit(0);
				}
				
			}
			
		}
		
		if(gp.gameState == gp.gameOverState) {
			if(code == KeyEvent.VK_ENTER) {
				System.exit(0);
			}
			
		}
		
		// 8-- back space 9-- tab 16-- shift  A--65 B--66 etc
		if(code == KeyEvent.VK_W) {
			upPressed = true;
			
		}
		if(code == KeyEvent.VK_S) {
			downPressed = true;
			
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = true;
			
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = true;
			
		}
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
			
		}
		if(code == KeyEvent.VK_F) {
			shotKeyPressed = true;
			
		}
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_W) {
			upPressed = false;
	
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
	
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
	
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
	
		}
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = false;
			
		}
		if(code == KeyEvent.VK_F) {
			shotKeyPressed = false;
			
		}
	}
	

}

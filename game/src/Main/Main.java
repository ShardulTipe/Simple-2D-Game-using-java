package Main;
import javax.swing.JFrame;
class Main {

	public static void main(String[] args) {
		JFrame window = new JFrame();
		
		// THIS LETS THE WINDOW PROPERLY CLOSE WHEN USER CLICK THE CLOSE "x" button
	
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		window.setResizable(false);  				// stops to resize the window
		window.setTitle("2D Adventure");
		
		// ADDING THE GamePanel to the frame
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.pack();
		
		window.setLocationRelativeTo(null);        // window will be displayed at the center
		window.setVisible(true);
		
		gamePanel.setupGame();
		gamePanel.startGameThread();

	}

}

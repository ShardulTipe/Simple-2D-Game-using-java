package Main;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.JPanel;
import entity.*;
import tile.*;
import object.*;
public class GamePanel extends JPanel implements Runnable{
	private static final String Sysetm = null;
	// Screen settings
	final int originalTitalSize = 16; // it is the original size of game characters 
	final int scale = 3; 
	
	// scalling the size of charactor size by 3X16 if screen size is big
	
	public final int titleSize = originalTitalSize * scale; // 48X48 size of character
	public final int maxScreenCol = 16;					// 16 columns in frame
	public final int maxScreenRow=12;						// 12 rows in frame 
	public final int screenWidth= titleSize*maxScreenCol;	// 48 X 16 = 768 px
	public final int screenHight= titleSize*maxScreenRow;  // 48 X 12 = 578 px
	// world setings
	public final int maxWorldCol=50;
	public final int maxWorldRow = 50;
	public final int worldWidth = titleSize + maxWorldCol;
	public final int worldHight = titleSize + maxWorldRow;
	int FPS = 60;
	
	TileManager tileM = new TileManager(this);
	// Instantiating key handler class in
	public KeyHandler keyH = new KeyHandler(this);			
	Thread gameThread;	// thread for continuously displaying character on screen and displaying movements
	// constructor of GamePanel
	public EventHandler eHandler = new EventHandler(this);
	public UI ui = new UI(this);
	public SuperObject obj[]= new SuperObject[10];
	public ColisionCheck cCheck = new ColisionCheck(this);
	public ObjectSetter OS = new ObjectSetter(this);
	public Player player=new Player(this,keyH); // passing GamePanel class and keyHandler as parameter
	public Entity npc[] = new Entity[10];
	public Entity monster[]= new Entity[3];
	public ArrayList<Entity> projectileList = new ArrayList<>();
	ArrayList<Entity> entityList = new ArrayList<>();
	// setting default parameters of the game windowS
	public final int titleState=0;
	public int gameState;
	public final int gameOverState=2;
	// Setting player default position
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHight));   // window size = 768 X 578
		this.setBackground(Color.black);								// default back ground color = black
		this.setDoubleBuffered(true);		// buffer help to draw the graphics before putting the graphics on the frame,
		           //it help to eliminate the flickering  
		this.addKeyListener(keyH);
		this.setFocusable(true); 			// with this GamePanel can focused to receive key input
	}
	
	public void setupGame() {
		
		OS.setObject();
		OS.setnpc();
		OS.setMonster();
		gameState=titleState;
	}
	
	
	public void startGameThread() {
		gameThread =  new Thread(this);		// this --> this means passing the GamePanel class to thread 
		
		gameThread.start();
		
	}
	
	// game loop for game
	public void run() {
		
		double drawInterval = 1000000000/FPS;		// AROUND 0.01666 SEC
		
		long lastTime=System.nanoTime();
		long currentTime;
		int timer=0;
		int drawCount=0;
		double delta=0;
		while(gameThread != null) { // while game thread exists the game loop continuously update and repaint the frame
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime)/drawInterval;
			timer +=(currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >= 1000000000) {
				System.out.println("FPS:"+drawCount);
				drawCount = 0;
				timer =0;
			}
			
			
			
		}
	}
	
	
	public void update() {		// method to update the frame 
		
		player.update();
		for(int i=0;i<npc.length;i++) {
			if(npc[i]!=null) {
				npc[i].update();
			}
		}
		
		for(int i=0; i< monster.length; i++) {
			if(monster[i] != null) {
				if(monster[i].alive == true && monster[i].dying == false) {
				monster[i].update();
				}
				if(monster[i].alive == false) {
					monster[i] = null;
				}
			}
		}
		for(int i=0; i< projectileList.size(); i++) {
			if(projectileList.get(i) != null) {
				if(projectileList.get(i).alive == true && projectileList.get(i).dying == false) {
					projectileList.get(i).update();
				}
				if(projectileList.get(i).alive == false) {
					projectileList.remove(i);
				}
			}
		}
	}
	
	
	public void paintComponent(Graphics g) {		// paint method is inbuilt method to draw components on the screen 
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		if(gameState == titleState) {
			ui.drawTitleScreen(g2);
		}
		else{
			// tile
			tileM.draw(g2);			// drawing tile
			
			// objects
			for(int i = 0; i<obj.length; i++) {
				if(obj[i] != null) {
					obj[i].draw(g2,this);
				}
			}
			
			for(int i=0; i<npc.length;i++) {
				if(npc[i] != null) {
					npc[i].draw(g2);
				}
			}
			
			for(int i=0; i<monster.length;i++) {
				if(monster[i] != null) {
					monster[i].draw(g2);
				}
			}
			for(int i=0; i<projectileList.size();i++) {
				if(projectileList.get(i) != null) {
					projectileList.get(i).draw(g2);
				}
			}
			//player
			player.draw(g2);		// drawing player 
			ui.draw(g2);
			g2.dispose();
		}
		
		
		//g2.dispose();		// throw the rectangle on screen/frame
	}
	
}
// here the run() method is automatically will be called when startGameThread is called
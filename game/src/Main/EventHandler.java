package Main;

import java.awt.Rectangle;

public class EventHandler {
	
	GamePanel gp;
	Rectangle eventRect;
	int eventRectDefaultX,eventRectDefaultY;
	public EventHandler(GamePanel gp) {
		this.gp=gp;
		eventRect = new Rectangle();
		eventRect.x = 23;
		
	}

}

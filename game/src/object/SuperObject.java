package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import Main.GamePanel;
import Main.UtilityTool;

public class SuperObject {
	public BufferedImage image ,image2,image3;
	public String name;
	public boolean collision=true;
	public int worldX,worldY;
	public Rectangle solidArea = new Rectangle(0,0,48,48);
	public int solidAreaDefaultX=0;
	public int solidAreaDefaultY=0;
	public boolean collisionOn;
	 UtilityTool uTool = new UtilityTool();
	
	public void draw(Graphics2D g2 , GamePanel gp) {
		int screenX = worldX -gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if(worldX + gp.titleSize > gp.player.worldX - gp.player.screenX && worldX - gp.titleSize < gp.player.worldX + gp.player.worldX && worldY + gp.titleSize > gp.player.screenY -gp.player.screenY && worldY - gp.titleSize < gp.player.worldY + gp.player.screenY) {
			g2.drawImage(image, screenX, screenY,gp.titleSize,gp.titleSize, null);
		}
	}
}

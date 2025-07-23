package object;

import javax.imageio.ImageIO;
import Main.GamePanel;

public class Obj_door extends SuperObject{
	GamePanel gp;
public Obj_door(GamePanel gp) {
		
		name = "Door";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/tiles/door.png"));
			uTool.scaleImage(image, gp.titleSize, gp.titleSize);
		}catch(Exception e) {
			e.printStackTrace();
		}
		collision=true;
	}
}

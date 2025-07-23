package object;

import javax.imageio.ImageIO;
import Main.GamePanel;

public class Obj_Key extends SuperObject{
	GamePanel gp;
	public Obj_Key(GamePanel gp) {
		
		name = "Key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/tiles/key.png"));
			uTool.scaleImage(image, gp.titleSize, gp.titleSize);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

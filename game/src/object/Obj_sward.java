package object;

import javax.imageio.ImageIO;
import Main.GamePanel;


public class Obj_sward extends SuperObject{
	GamePanel gp;
public Obj_sward(GamePanel gp) {
		
		name = "Sward";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/tiles/treasure.png"));
			uTool.scaleImage(image, gp.titleSize, gp.titleSize);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

package object;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class Obj_Heart extends SuperObject{
	GamePanel gp;
public Obj_Heart(GamePanel gp) {
		
		name = "Heart";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/tiles/full_life.png"));
			image2 = ImageIO.read(getClass().getResourceAsStream("/tiles/half_life.png"));
			image3= ImageIO.read(getClass().getResourceAsStream("/tiles/no_life.png"));
			image=uTool.scaleImage(image, gp.titleSize, gp.titleSize);
			image2=uTool.scaleImage(image2, gp.titleSize, gp.titleSize);
			image3=uTool.scaleImage(image3, gp.titleSize, gp.titleSize);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}

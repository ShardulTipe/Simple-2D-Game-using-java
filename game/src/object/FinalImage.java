package object;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class FinalImage extends SuperObject{
	GamePanel gp;
	public FinalImage(GamePanel gp) {
			
			try {
				image = ImageIO.read(getClass().getResourceAsStream("/tiles/final image.png"));
				uTool.scaleImage(image, gp.titleSize*2, gp.titleSize*2);
			}catch(Exception e) {
				e.printStackTrace();
			}
			collision=true;
		}
}

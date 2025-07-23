package entity;

import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.UtilityTool;

public class OldMan extends Entity{
	
	public OldMan(GamePanel gp) {
		super(gp);
		direction = "down";
		speed=1;
		getImage();
	}
	
	
	public void getImage() {
		
		up1= setup("/NPC/oldman_down_1.png",gp.titleSize,gp.titleSize);
		up2 = setup("/NPC/oldman_down_2.png",gp.titleSize,gp.titleSize);
		down1= setup("/NPC/oldman_up_1.png",gp.titleSize,gp.titleSize);
		down2=setup("/NPC/oldman_up_2.png",gp.titleSize,gp.titleSize);
		right1=setup("/NPC/oldman_left_1.png",gp.titleSize,gp.titleSize);
		right2=setup("/NPC/oldman_left_2.png",gp.titleSize,gp.titleSize);
		left1=setup("/NPC/oldman_right_1.png",gp.titleSize,gp.titleSize);
		left2=setup("/NPC/oldman_right_2.png",gp.titleSize,gp.titleSize);
		
}
	public void setAction() {
		actionLockCounter++;
		
		if(actionLockCounter == 120) {
			Random random = new Random();
			int i = random.nextInt(100)+1;
			if(i<=25) {
				direction = "up";
			}
			if(i>25 && i<=50) {
				direction = "down";
			}
			if(i>50 && i<=75) {
				direction ="left";
			}
			if(i>75 && i<=100) {
				direction="right";
			}
			
			actionLockCounter =0;
		}
		
		
	}
	
}

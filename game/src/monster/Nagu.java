package monster;

import java.util.Random;

import Main.GamePanel;
import entity.Entity;

public class Nagu extends Entity{
	GamePanel gp;
	public Nagu(GamePanel gp) {
		super(gp);
		this.gp=gp;
		type=2;
		
		// TODO Auto-generated constructor stub
		name ="Nagu";
		direction = "down";
		speed=2;
		maxLife = 4;
		life= maxLife;
		
		solidArea.x =0;
		solidArea.y=0;
		solidArea.width=48;
		solidArea.height=48;
		solidAreaDefaultX=solidArea.x;
		solidAreaDefaultY= solidArea.y;
		
		getImage(gp);
	}
	public void getImage(GamePanel gp) {
		up1 = setup("/tiles/monster.png",gp.titleSize,gp.titleSize);
		up2 = setup("/tiles/monster.png",gp.titleSize,gp.titleSize);
		down1 = setup("/tiles/monster.png",gp.titleSize,gp.titleSize);
		down2 = setup("/tiles/monster.png",gp.titleSize,gp.titleSize);
		left1= setup("/tiles/monster.png",gp.titleSize,gp.titleSize);
		left2 = setup("/tiles/monster.png",gp.titleSize,gp.titleSize);
		right1 = setup("/tiles/monster.png",gp.titleSize,gp.titleSize);
		left2 = setup("/tiles/monster.png",gp.titleSize,gp.titleSize);
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
	
	public void damageReaction() {
		actionLockCounter = 0;
		direction = gp.player.direction;
	}
}

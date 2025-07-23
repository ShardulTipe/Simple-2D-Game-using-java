package object;

import Main.GamePanel;
import entity.Projectile;

public class Star extends Projectile{
	GamePanel gp;
	public Star(GamePanel gp) {
		super(gp);
		this.gp=gp;
		// TODO Auto-generated constructor stub
		name="Star";
		speed=10;
		maxLife=80;
		life=maxLife;
		attack=2;
		useCost=1;
		alive=false;
		getImage();
	}
	public void getImage() {
		up1= setup("/tiles/star.png",gp.titleSize,gp.titleSize*2);
		up2 = setup("/tiles/star.png",gp.titleSize,gp.titleSize*2);
		down1= setup("/tiles/star.png",gp.titleSize,gp.titleSize*2);
		down2=setup("/tiles/star.png",gp.titleSize,gp.titleSize*2);
		left1=setup("/tiles/star.png",gp.titleSize*2,gp.titleSize);
		left2=setup("/tiles/star.png",gp.titleSize*2,gp.titleSize);
		right1=setup("/tiles/star.png",gp.titleSize*2,gp.titleSize);
		right2=setup("/tiles/star.png",gp.titleSize*2,gp.titleSize);
		
	}

}

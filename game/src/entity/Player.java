package entity;


import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import Main.UtilityTool;
import object.Star;

import javax.imageio.ImageIO;

import Main.*;

public class Player extends Entity {
	
	
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	public int hasKey=0;
	
	public Player(GamePanel gp,KeyHandler keyH ) {
		super(gp);
		
		this.keyH=keyH;
		screenX = gp.screenWidth/2;
		screenY = gp.screenHight/2;
		
		solidArea = new Rectangle();
		solidArea.x=8;
		solidArea.y=6;
		solidAreaDefaultX=solidArea.x;
		solidAreaDefaultY=solidArea.y;
		solidArea.width=32;
		solidArea.height=32;
		
		attackArea.width= 36;
		attackArea.height=36;
		
		setDefaultvalues();
		getPlayerImage();
		getplayerAttackImages();
	}
	
	public void setDefaultvalues() {
		worldX= gp.titleSize*8;
		worldY=gp.titleSize*8;
		speed=4;
		direction="down";
		
		// Player Status
		 maxLife = 6;
		 life = maxLife;
		 projectile=new Star(gp);
	}
	
	
	
	public void update() {
		if(attacking == true) {
			attacking();
		}
		
		else if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
			if(keyH.upPressed == true) {
				direction="up";
				
			}
			else if(keyH.downPressed == true) {
				direction="down";
				
			}
			else if(keyH.leftPressed == true){
				direction="left";
				
			}
			else if(keyH.rightPressed == true) {
				direction="right";
				
			}
			
			collisionOn = false;
			gp.cCheck.checkTile(this);
			
			//CHECK OBJ COLLISION
			
			int objIndex = gp.cCheck.checkobject(this, true);
			pickObject(objIndex);
			
			int npcIndex = gp.cCheck.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			
			// CHECK MONSTER COLLISION
			int monsterIndex=gp.cCheck.checkEntity(this,gp.monster);
			contactMonster(monsterIndex);
			
			if (collisionOn == false) {
				
				switch(direction) {
				case "up": worldY -= speed;break;
				case "down": worldY += speed;break;
				case "left": worldX -= speed;break;
				case "right": worldX += speed;break;
				}		
			}
				spriteCounter++;
			
			if(spriteCounter >12) {
				if(spriteNum==1) {
					spriteNum=2;
				}
				else if(spriteNum==2){
					spriteNum=1;
				}
				spriteCounter=0;
			}
		}
		if(gp.keyH.shotKeyPressed == true && projectile.alive == false) {
			projectile.set(worldX,worldY,direction,true,this);
			
			gp.projectileList.add(projectile);
			
		}
		
		if(invincible == true) {
			invencibleCounter++;
			if(invencibleCounter > 60) {
				invincible = false;
				invencibleCounter=0;
			}
		}
		if(life <= 0) {
			gp.gameState = gp.gameOverState;
		}
	}
	
	public void attacking() {
		
		spriteCounter++;
		
		if(spriteCounter <= 5) {
			spriteNum =1;
		}
		if(spriteCounter >5 && spriteCounter <= 25) {
			spriteNum=2;
			
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth=solidArea.width;
			int solidAreaHeight= solidArea.height;
			
			switch(direction) {
			case "up": worldY -=attackArea.height;break;
			case "down": worldY += attackArea.height;break;
			case "left": worldX -= attackArea.width;break;
			case "right" : worldX += attackArea.width;break;
			}
			
			solidArea.height = attackArea.height;
			solidArea.width = attackArea.width;
			
			int monsterIndex = gp.cCheck.checkEntity(this,gp.monster);
			damageMonster(monsterIndex);
			
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
		}
		if(spriteCounter >25) {
			spriteNum=1;
			spriteCounter = 0;
			attacking = false;
		}
	}
	
	public void pickObject(int i) {
		if(i!=999) {
			String objName=gp.obj[i].name;
			switch(objName) {
			case "Key":
				hasKey++;
				gp.obj[i]=null;
				gp.ui.showMessage("You got a Key");
				break;
			case "Door" :
				if(hasKey >0) {
					gp.obj[i]=null;
					hasKey--;	
				}
				else {
					collisionOn=true;
				}
				break;
			case "Sward":
				gp.ui.gameFinished = true;
				break;
			}
		}
	}
	
	public void interactNPC(int i) {
		
			
		
			if(gp.keyH.enterPressed == true) {
				attacking=true;
		
			}
	}	
	 
	
	public void draw(Graphics2D g2) {
		//g2.setColor(Color.white);
		//g2.fillRect(x,y, gp.titleSize, gp.titleSize);  // 100 X 100 size of test character and titleSize is x and y cordinates
		BufferedImage image = null;
		switch(direction) {
		case "up":
			if(attacking == false) {
				if(spriteNum==1) {image = up1;}
				if(spriteNum==2) {image = up2;}
			}
			if(attacking == true) {
				if(spriteNum==1) {image = attackUp1;}
				if(spriteNum==2) {image = attackUp2;}
			}
			break;
		case "down":
			if(attacking == false) {
				if(spriteNum==1) {image = down1;}
				if(spriteNum==2) {image=down2;}
			}
			if(attacking == true) {
				if(spriteNum==1) {image = attackDown1;}
				if(spriteNum==2) {image=attackDown2;}
			}
			break;
		case "left":
			if(attacking == false) {
				if(spriteNum==1) {image = left1;}
				if(spriteNum==2) {image=left2;}
			}
			if(attacking == true) {
				if(spriteNum==1) {image = attackLeft1;}
				if(spriteNum==2) {image=attackLeft2;}
			}
			break;
		case "right":
			if(attacking == false) {
				if(spriteNum==1) {image=right1;}
				if(spriteNum==2) {image=right2;}
			}
			if(attacking ==true) {
				if(spriteNum==1) {image=attackRight1;}
				if(spriteNum==2) {image=attackRight2;}
			}
			
			break;
		}
		
		if(invincible == true ) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.3f));
		}
		g2.drawImage(image, screenX, screenY,null);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
	}
	
	public void getPlayerImage() {
		
			up1= setup("/player/boy_up_1.png",gp.titleSize,gp.titleSize);
			up2 = setup("/player/boy_up_2.png",gp.titleSize,gp.titleSize);
			down1= setup("/player/boy_down_1.png",gp.titleSize,gp.titleSize);
			down2=setup("/player/boy_down_2.png",gp.titleSize,gp.titleSize);
			right1=setup("/player/boy_right_1.png",gp.titleSize,gp.titleSize);
			right2=setup("/player/boy_right_2.png",gp.titleSize,gp.titleSize);
			left1=setup("/player/boy_left_1.png",gp.titleSize,gp.titleSize);
			left2=setup("/player/boy_left_2.png",gp.titleSize,gp.titleSize);
			
	}
	
	public void getplayerAttackImages() {
		
		attackUp1= setup("/player/boy_attack_up_1.png",gp.titleSize,gp.titleSize*2);
		attackUp2 = setup("/player/boy_attack_up_2.png",gp.titleSize,gp.titleSize*2);
		attackDown1= setup("/player/boy_attack_down_1.png",gp.titleSize,gp.titleSize*2);
		attackDown2=setup("/player/boy_attack_down_2.png",gp.titleSize,gp.titleSize*2);
		attackRight1=setup("/player/boy_attack_right_1.png",gp.titleSize*2,gp.titleSize);
		attackRight2=setup("/player/boy_attack_right_2.png",gp.titleSize*2,gp.titleSize);
		attackLeft1=setup("/player/boy_attack_left_1.png",gp.titleSize*2,gp.titleSize);
		attackLeft2=setup("/player/boy_attack_left_2.png",gp.titleSize*2,gp.titleSize);
		
	}
	
	public void contactMonster(int i) {
		if(i!=999) {
			if (invincible == false && gp.monster[i].dying==false)
				life-=1;
			invincible = true;
		}
	}
	
	public void damageMonster( int i) {
		if(i!=999) {
			if(gp.monster[i].invincible ==false) {
				gp.monster[i].life -=1;
				gp.monster[i].invincible = true;
				gp.monster[i].damageReaction();
				if(gp.monster[i].life <=0) {
					gp.monster[i].dying=true;
				}
			}
		}
	}
}

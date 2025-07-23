package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.UtilityTool;

public class Entity {
	GamePanel gp;
	public int worldX,worldY;
	public int speed;
	public int attack;
	public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
	public BufferedImage attackUp1,attackUp2,attackDown1,attackDown2,attackLeft1,attackLeft2,attackRight1,attackRight2;
	public String direction;
	public int spriteCounter=0;
	public int spriteNum=1;
	public Rectangle solidArea = new Rectangle(0,0,48,48);
	public int solidAreaDefaultX,solidAreaDefaultY;
	public boolean collisionOn=false;
	public int actionLockCounter=0;
	public String name;
	public int maxLife;
	public int life;
	
	public boolean invincible = false;
	public int invencibleCounter = 0;
	public boolean attacking = false;
	public Rectangle attackArea = new Rectangle(0,0,0,0);
	public boolean alive = true;
	public boolean dying=false;
	int dyingCounter = 0;
	boolean hpBarOn = false;
	int hpBarCounter =0; 
	public int type;
	public int maxManna;
	public int manna;
	public Projectile projectile; 
	public int useCost;
	public Entity(GamePanel gp) {
		this.gp=gp;
	}
	
	public void setAction() {}
	public void damageReaction() {}
	public void update() {
		setAction();
		collisionOn = false;
		gp.cCheck.checkTile(this);
		gp.cCheck.checkobject(this, false);
		gp.cCheck.checkEntity(this, gp.npc);
		gp.cCheck.checkEntity(this, gp.monster);
		boolean contactPlayer=gp.cCheck.checkPlayer(this);
		
		if(this.type==2 && contactPlayer==true) {
			damagePlayer();
		}
	
		
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
		if(invincible == true) {
			invencibleCounter++;
			if(invencibleCounter > 60) {
				invincible = false;
				invencibleCounter=0;
			}
		}
		
	}
	
	public void damagePlayer() {
		
		if(gp.player.invincible==false) {
			gp.player.life-=1;
			gp.player.invincible=true;
		}
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		int screenX = worldX -gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if(worldX + gp.titleSize > gp.player.worldX - gp.player.screenX && worldX - gp.titleSize < gp.player.worldX + gp.player.worldX && worldY + gp.titleSize > gp.player.screenY -gp.player.screenY && worldY - gp.titleSize < gp.player.worldY + gp.player.screenY) {
			
			switch(direction) {
			case "up":
				if(spriteNum==1) {
				image = up1;
				}
				if(spriteNum==2) {
					image=up2;
					}
				
				break;
			case "down":
				if(spriteNum==1) {
				image = down1;
				}
				if(spriteNum==2) {
					image=down2;
					}
				break;
			case "left":
				if(spriteNum==1) {
				image = left1;
				}
				if(spriteNum==2) {
					image=left2;
				}
				break;
			case "right":
				if(spriteNum==1) {
				image = right1;}
				if(spriteNum==2) {
					image=right2;
				}
				break;
			}
			
			// MONSTER HELTH BAR
			if(name=="Nagu" && hpBarOn == true) {
				
				double oneScale = (double)gp.titleSize/maxLife;
				double hpBarValue = oneScale*life;
				
				g2.setColor(new Color(35,35,35));
				g2.fillRect(screenX-1, screenY-16, gp.titleSize+2,12);
				
			g2.setColor(new Color(225,0,30));
			g2.fillRect(screenX, screenY-15, (int)hpBarValue,10);
			
			hpBarCounter ++;
			
			if(hpBarCounter >600) {
				hpBarCounter = 0;
				hpBarOn = false;
			}
			}
			if(invincible == true ) {
				hpBarOn = true;
				hpBarCounter = 0;
				changeAlpha(g2,0.4f);
			}
			if(dying==true) {
				dyingAnimation(g2);
			}
			
			g2.drawImage(image, screenX, screenY,gp.titleSize,gp.titleSize, null);
			
			changeAlpha(g2,1f);
		}
	}
	
	public void dyingAnimation(Graphics2D g2) {
		
		dyingCounter++;
		int i = 5;
		if(dyingCounter <= i) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0f));
			changeAlpha(g2,0);
		}
		if(dyingCounter > i && dyingCounter <=i*2) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
			changeAlpha(g2,1);
		}
		if(dyingCounter >i*2 && dyingCounter <= i*3) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0f));
			changeAlpha(g2,0);
		}
		if(dyingCounter > i*3 && dyingCounter <=i*4) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
			changeAlpha(g2,1);
		}
		if(dyingCounter > i*4 && dyingCounter <= i*5) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0f));
			changeAlpha(g2,0);
		}
		if(dyingCounter > i*5 && dyingCounter <=i*6) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
			changeAlpha(g2,1);
		}
		if(dyingCounter > i*6 && dyingCounter <= i*7) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0f));
			changeAlpha(g2,0);
		}
		if(dyingCounter > i*7 && dyingCounter <= i*8) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
			changeAlpha(g2,0);
		}
		if(dyingCounter > i*8) {
			//dying=false;
			alive=false;
		}
		
	}
	public void changeAlpha(Graphics2D g2, float alphaValue) {
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alphaValue));
		
	}
	public BufferedImage setup(String imageName,int width ,int hight) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		try {
			image= ImageIO.read(getClass().getResourceAsStream(imageName));
			image = uTool.scaleImage(image, width,hight);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return image;
	}
	
}

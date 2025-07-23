package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import object.Obj_Key;

import object.*;

public class UI {
	
	
	GamePanel gp;
	Font arial_40,arial_80B;
	BufferedImage keyImage,full_life,half_life,nolife,fimage;
	
	public boolean messageOn = false;
	public String message = "";
	int messageCounter=0;
	public boolean gameFinished=false;
	public int comandNum=0;
	public UI(GamePanel gp) {
		this.gp=gp;
		
		arial_40=new Font("Arial",Font.PLAIN,40);
		arial_80B=new Font("Arial",Font.BOLD,80);
		Obj_Key key =new Obj_Key(gp);
		keyImage = key.image;
		FinalImage im = new FinalImage(gp);
		fimage = im.image;
		// Create life object
		SuperObject heart = new Obj_Heart(gp);
		full_life=heart.image;
		half_life=heart.image2;
		nolife=heart.image3;
	}
	
	public void showMessage(String text) {
		
		message = text;
		messageOn= true;
	}
	public void draw(Graphics2D g2) {
		
		if(gameFinished == true) {
			g2.setFont(arial_80B);
			g2.setColor(Color.yellow);
			String text;
			int textLength;
			int x;
			int y;
			
			text="You Win!";
			textLength=(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			 x=gp.screenWidth/2-100;
			 y=gp.screenHight/2-100;
			g2.drawString(text, x, y);
			g2.drawImage(fimage, x,y,gp.titleSize*4,gp.titleSize*4,null);
			
			gp.gameThread=null;
		}
		else {
			drawPlayerLife(g2);
			
			if(gp.gameState == gp.titleState) {
				drawTitleScreen(g2);
			}
			
			if(messageOn == true) {
				g2.setFont(g2.getFont().deriveFont(30F));
				g2.drawString(message, gp.titleSize/2, gp.titleSize*5);
				
				messageCounter++;
				
				if (messageCounter > 120) {
					messageCounter = 0;
					messageOn =false;
					
				}
			}
			if(gp.gameState == gp.gameOverState) {
				drawGameOverScreen(g2);
			}
			
			
		}
		
		
		
	}
	
	public void drawGameOverScreen(Graphics2D g2) {
		g2.setColor(new Color(0,0,0,150));
		g2.fillRect(0,0,gp.screenWidth, gp.screenHight);
		
		int x;
		int y;
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,100f));
		
		text="Game Over";
		g2.setColor(Color.black);
		x=getXforCenteredText(text,g2);
		y=gp.titleSize*4;
		g2.drawString(text, x, y);
		g2.setColor(Color.white);
		g2.drawString(text, x-4, y-4);
		
		g2.setFont(g2.getFont().deriveFont(50f));
		text="Retry";
		x=getXforCenteredText(text,g2);
		y +=gp.titleSize*4;
		g2.drawString(text, x, y);
		if(comandNum==0) {
			g2.drawString(">", x-40, y);
		}
	
		text="Quit";
		x=getXforCenteredText(text,g2);
		y += 55;
		g2.drawString(text, x, y);
	}
	
	public void drawTitleScreen(Graphics2D g2) {
		
		arial_40=new Font("Tw Cen MT Condensed Extra Bold",Font.BOLD,69);
		g2.setFont(arial_40);
		String text="Pixal 2D Game";
		int x = getXforCenteredText(text,g2);
		int y=gp.titleSize*3;
		
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		x=gp.screenWidth/2 -(gp.titleSize*2)/2;
		y += gp.titleSize*2;
		g2.drawImage(gp.player.down1, x,y,gp.titleSize*2,gp.titleSize*2,null);
		
		arial_40=new Font("Tw Cen MT Condensed Extra Bold",Font.BOLD,48);
		g2.setFont(arial_40);
		
		text="NEW GAME";
		x=getXforCenteredText(text,g2);
		y +=gp.titleSize*3.5;
		g2.drawString(text, x, y);
		if(comandNum==0) {
			g2.drawString(">", x-gp.titleSize, y);
		}
		
		text="CONTINUE";
		x=getXforCenteredText(text,g2);
		y +=gp.titleSize;
		g2.drawString(text, x, y);
		if(comandNum==1) {
			g2.drawString(">", x-gp.titleSize, y);
		}
		
		text="QUIT";
		x=getXforCenteredText(text,g2);
		y +=gp.titleSize;
		g2.drawString(text, x, y);
		if(comandNum==2) {
			g2.drawString(">", x-gp.titleSize, y);
		}
		
	}
	
	private int getXforCenteredText(String text,Graphics2D g2) {
		
		// TODO Auto-generated method stub
		int length= (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
		int x=gp.screenWidth/2 - length/2;
		return x;
	}

	public void drawPlayerLife(Graphics2D g2) {
		int x = gp.titleSize/2;
		int y = gp.titleSize/2;
		int i=0;
		while(i<gp.player.maxLife/2) {
			g2.drawImage(nolife,x,y,null);
			i++;
			x += gp.titleSize;
		}
		
		// RESET
		 x = gp.titleSize/2;
		 y = gp.titleSize/2;
		 i=0;
		 
		 // DRAW CURRENT LIFE
		  while(i< gp.player.life) {
			  g2.drawImage(half_life, x, y,null);
			  i++;
			  if(i<gp.player.life) {
				  g2.drawImage(full_life,x,y,null);
			  }
			  i++;
			  x+=gp.titleSize;
			  
		  }
	}

}

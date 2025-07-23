package tile;

import java.awt.Graphics2D;

import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.BufferedImage;
import Main.GamePanel;
import Main.UtilityTool;


public class TileManager {
	GamePanel gp;
	public Tile[] tile;		// treating Tile class as an array and tile is an object o Tile
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[10];
		mapTileNum=new int[gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		loadMap("/map/map1.txt");
	}
	
	public void getTileImage() {
	
			setup(0,"grass",false);
			setup(1,"wall",true);
			setup(2,"water",true);
			setup(3,"tree",true);
			setup(4,"grass",false);
			setup(5,"grass",false);
			setup(6,"sand",false);
	}
	
	public void setup(int index, String imagePath, boolean collision) {
		UtilityTool uTool = new UtilityTool();
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+ imagePath+".png"));
			tile[index].image = uTool.scaleImage(tile[index].image,gp.titleSize,gp.titleSize);
			tile[index].collision=collision;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// METHOD TO DRAW TILE IMAGE ON FRAME
	public void draw(Graphics2D g2) {
		
		int Worldcol=0;
		int Worldrow=0;
		
		while(Worldcol<gp.maxWorldCol && Worldrow <gp.maxWorldRow) {
			
			int tileNum = mapTileNum[Worldcol][Worldrow];
			
			int worldX = Worldcol * gp.titleSize;
			int worldY = Worldrow * gp.titleSize;
			int screenX = worldX -gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			g2.drawImage(tile[tileNum].image ,screenX, screenY,null);
			Worldcol++;
			
			if(Worldcol == gp.maxWorldCol) {
				Worldcol=0;
				
				Worldrow++;
			
			}
		}
		//g2.drawImage(tile[2].image ,0, 0, gp.titleSize,gp.titleSize,null);
}

	public void loadMap(String map) {
		try {
			InputStream is = getClass().getResourceAsStream(map);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			int col=0;
			int row = 0;
			while(col<gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();
				//if(line==null) {break;}
				while(col<gp.maxWorldCol) {
					String numbers[]= line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row]=num;
					col++;
					
					}
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
				
				
			}br.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}

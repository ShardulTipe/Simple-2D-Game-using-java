package Main;

import entity.OldMan;
import monster.Nagu;
import object.Obj_Key;
import object.Obj_door;
import object.Obj_sward;

public class ObjectSetter {
	GamePanel gp;
	
	public ObjectSetter(GamePanel gp) {
		this.gp=gp;
	}
	public void setObject() {

		
		gp.obj[0] = new Obj_Key(gp);
		gp.obj[0].worldX= 41 * gp.titleSize;
		gp.obj[0].worldY= 7 * gp.titleSize;
		
		gp.obj[1] = new Obj_Key(gp);
		gp.obj[1].worldX= 26 * gp.titleSize;
		gp.obj[1].worldY= 18 * gp.titleSize;
		
		gp.obj[2] = new Obj_door(gp);
		gp.obj[2].worldX= 5 * gp.titleSize;
		gp.obj[2].worldY= 24 * gp.titleSize;
		
		/*gp.obj[3] = new Obj_door();
		gp.obj[3].worldX= 4 * gp.titleSize;
		gp.obj[3].worldY= 24 * gp.titleSize;
		*/
		gp.obj[4] = new Obj_door(gp);
		gp.obj[4].worldX= 41 * gp.titleSize;
		gp.obj[4].worldY= 12 * gp.titleSize;
		
		gp.obj[5] = new Obj_sward(gp);
		gp.obj[5].worldX= 27 * gp.titleSize;
		gp.obj[5].worldY= 30 * gp.titleSize;
	}
	
	public void setnpc() {
		gp.npc[0]=new OldMan(gp);
		gp.npc[0].worldX = gp.titleSize*5;
		gp.npc[0].worldY = gp.titleSize*10;
		
		gp.npc[1]=new OldMan(gp);
		gp.npc[1].worldX = gp.titleSize*24;
		gp.npc[1].worldY = gp.titleSize*8;
		
		gp.npc[2]=new OldMan(gp);
		gp.npc[2].worldX = gp.titleSize*20;
		gp.npc[2].worldY = gp.titleSize*10;
	}
	
	public void setMonster() {
		gp.monster[0]= new Nagu(gp);
		gp.monster[0].worldX = gp.titleSize*8;
		gp.monster[0].worldY =  gp.titleSize*10;
		
		gp.monster[1] = new Nagu(gp);
		gp.monster[1].worldX = gp.titleSize*42;
		gp.monster[1].worldY = gp.titleSize*14;
		
		gp.monster[2] = new Nagu(gp);
		gp.monster[2].worldX = gp.titleSize*12;
		gp.monster[2].worldY = gp.titleSize*23;
	}
}

package starparty.pilot;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Path;

import starparty.library.InterstellarObject;
import starparty.library.Player;

public class Viewscreen {
	int x, y, height, width;
	boolean xy;
	List<InterstellarObject> interstellarObjects;
	Player player;
	
	private Color alphaWhiteColor = new Color(255, 255, 255, 50);
	
	public Viewscreen(Player player, int x, int y, int height, int width, boolean xy, List<InterstellarObject> interstellarObjects){
		this.player = player;
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.interstellarObjects = interstellarObjects;
		this.xy = xy;
	}
	
	private void drawGrid(Graphics g) {
		g.setColor(Color.green);
		g.drawLine(x, y, x + width, y);
		g.drawLine(x, y, x, y + height);
		g.drawLine(x + width, y, x + width, y + height);
		g.drawLine(x, y + height, x + width, y + height);
		g.drawLine(x + width / 2, y, x + width / 2, y + height);
		g.drawLine(x, y + height / 2, x + width, y + height / 2);
		g.drawString("(0, 0)", x + width / 2, y + height / 2);
	}
	
	private void drawInterstellarObjects(Graphics g) {
		int range = 200;
		for(InterstellarObject interstellarObject : interstellarObjects){
			if(player.x + interstellarObject.x < -range || player.x + interstellarObject.x > range || player.y + interstellarObject.y < -range || player.y + interstellarObject.y > range || player.z + interstellarObject.z < -range || player.z + interstellarObject.z > range)continue;
			if(interstellarObject.z > player.z + 50)g.setColor(Color.pink);
			else if(interstellarObject.z < player.z - 50)g.setColor(Color.magenta);
			else g.setColor(Color.red);
			float centerX = x + width / 2, centerY = y + height / 2;
			if(xy)g.fillOval(player.x + centerX + (int)interstellarObject.x, player.y + centerY + (int)interstellarObject.y, 6, 6);
			else g.fillOval(player.y + centerX + (int)interstellarObject.y, player.z + centerY + (int)interstellarObject.z, 6, 6);
			//System.out.println("(" + interstellarObject.x + ", " + interstellarObject.y + ")");
			//g.drawRect(x + interstellarObject.x, interstellarObject.y, 2, 2);
			
		}
	}
	
	public void draw(Graphics g){
		g.setAntiAlias(true);
		drawInterstellarObjects(g);
		drawGrid(g);

		float centerX = x + width / 2, centerY = y + height / 2;
		Path path = new Path(centerX, centerY);
		path.curveTo(centerX + 190, centerY + 140, centerX, centerY, centerX + 50, centerY + 100);

	      g.setColor(alphaWhiteColor);
	      g.fill(path);
			g.setColor(Color.white);
	     g.draw(path);
	}
	
}
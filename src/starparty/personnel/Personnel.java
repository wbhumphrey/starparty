/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.personnel;

import com.google.gson.Gson;
import java.io.FileReader;
import org.newdawn.slick.*;
import starparty.library.Schematic;
import starparty.utilities.ImageLoader;

/**
 *
 * @author Tyler
 */
public class Personnel extends BasicGame {
  Image background;
  Schematic schematic;
  
  public Personnel() {
    super("StarParty");
  }
  
	public static void main(String ... args){
		try {
			AppGameContainer app = new AppGameContainer(new Personnel());
			app.setDisplayMode(1024, 700, false);
			app.setSmoothDeltas(true);
			app.setTargetFrameRate(60);
			app.setShowFPS(true);
			app.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
  
  @Override
  public void init(GameContainer gc) throws SlickException {
    background = ImageLoader.load("personnel/background.jpg");
    
    Gson gson = new Gson();
    try {
      schematic = gson.fromJson(new FileReader("resources/personnel/schematic.json"), Schematic.class);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update(GameContainer gc, int i) throws SlickException {
  }

  @Override
  public void render(GameContainer gc, Graphics g) throws SlickException {
    g.drawImage(background, 0, 0);
    
    g.setColor(Color.red);
    for (Schematic.Node node: schematic.nodes) {
      g.fillOval(120 + node.getX() - 1, 261 + node.getY() - 2, 4, 4);
    }
  }
}

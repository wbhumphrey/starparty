/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.component;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import starparty.personnel.Personnel;
import starparty.utilities.FontLoader;

/**
 *
 * @author Tyler
 */
public class Tooltip {
  String text;
  int x;
  int y;
  int width;
  int height;
  public Shape triggerLocation;
  
  private final static UnicodeFont FONT = FontLoader.load("TCM_____.TTF", 15);
  private final static Color BACKGROUND_COLOR = Color.magenta;
  private final static Color TEXT_COLOR = Color.black;
  
  public Tooltip(String text) {
    this.text = text;
  }
  
  public void setLocation(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  public void setSize(int width, int height) {
    this.width = width;
    this.height = height;
  }
  
  public void setTriggerLocation(Shape triggerLocation) {
    this.triggerLocation = triggerLocation;
  }

  public void draw(Graphics g) {
    // System.out.println("(" + Personnel.mouseX + ", " + Personnel.mouseY + ")");
    
    if (triggerLocation.contains(Personnel.mouseX, Personnel.mouseY)) {
      g.setColor(BACKGROUND_COLOR);
      g.fillRoundRect(x, y, width, height, 10, 20);

      g.setColor(TEXT_COLOR);
      g.drawString(text, x + 5, y + 5);
    }
  }
}

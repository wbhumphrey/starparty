/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.component;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.geom.Rectangle;
import starparty.tactical.Tactical;
import starparty.utilities.FontLoader;

/**
 *
 * @author Tyler
 */
public abstract class Button {
  public String label;
  int x;
  int y;
  int width;
  int height;
  public Color color = new Color(100, 151, 244);
  public UnicodeFont font = FontLoader.load("TCM_____.TTF", 35, true);
  
  private final Rectangle r = new Rectangle(0, 0, 0, 0);
  
  public Button(String label) {
    this.label = label;
  }
  
  public void setColor() {
    
  }
  
  public void setLocation(int x, int y) {
    this.x = x;
    this.y = y;
    
    init();
  }
  
  public void setSize(int width, int height) {
    this.width = width;
    this.height = height;
    
    init();
  }
  
  public void init() {
    r.setBounds(x, y, width, height);
  }
  
  public boolean click(int x, int y) {
    boolean clicked;
    
    if (clicked = r.contains(x, y)) {
      click();
    }
    
    return clicked;
  }
  
  public void draw(Graphics g) {
    g.setColor(color);
    g.fillRect(x, y, width, height);

    g.setFont(font);
    g.setColor(Color.black);
    g.drawString(label, x + 10, y + 10);
  }
  
  public abstract void click();
}

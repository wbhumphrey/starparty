/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.component;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.geom.Rectangle;
import starparty.utilities.FontLoader;

/**
 *
 * @author Tyler
 */
public abstract class Toggle {
  public String label;
  int x;
  int y;
  int width;
  int height;
  public boolean selected;
  public Color selectedColor = new Color(200, 151, 244);
  public Color color = new Color(100, 151, 244);
  public UnicodeFont font = FontLoader.load("TCM_____.TTF", 15);
  
  private final Rectangle r = new Rectangle(0, 0, 0, 0);
  
  public Toggle(String label) {
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
    if (selected)
      return false;
    
    boolean clicked;
    
    if (selected = clicked = r.contains(x, y)) {
      click();
    }
    
    return clicked;
  }
  
  public void draw(Graphics g) {
    g.setColor((selected ? selectedColor : color));
    g.fillRect(x, y, width, height);

    g.setFont(font);
    g.setColor(Color.black);
    g.drawString(label, x + 10, y + 4);
  }
  
  public abstract void click();
}

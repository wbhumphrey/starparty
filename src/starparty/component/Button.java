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
public abstract class Button {
  public String label;
  int x;
  int y;
  int width;
  int height;
  
  public static class ButtonStyle {
    public Color backgroundColor;
    public Color foregroundColor;
    public UnicodeFont font;
    int padding;
    
    public ButtonStyle(Color fg, Color bg, UnicodeFont font, int padding) {
      this.foregroundColor = fg;
      this.backgroundColor = bg;
      this.font = font;
      this.padding = padding;
    }
  }
  
  public static final ButtonStyle LARGE = new ButtonStyle(Color.black, new Color(100, 151, 244), FontLoader.load("TCM_____.TTF", 35, true), 15);
  public static final ButtonStyle NORMAL = new ButtonStyle(Color.black, new Color(100, 151, 244), FontLoader.load("TCM_____.TTF", 20), 10);
  public static final ButtonStyle SMALL = new ButtonStyle(Color.black, new Color(100, 151, 244), FontLoader.load("TCM_____.TTF", 10), 8);
  
  private final Rectangle r = new Rectangle(0, 0, 0, 0);
  public ButtonStyle style;
  public Button(String label) {
    this(label, Button.NORMAL);
  }
  
  public Button(String label, ButtonStyle style) {
    this.label = label;
    this.style = style;
    this.width = style.font.getWidth(label) + style.padding * 2;
    this.height = style.font.getHeight("A") + style.padding * 2;
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
    g.setColor(style.backgroundColor);
    g.fillRect(x, y, width, height);

    g.setColor(style.foregroundColor);
    g.setFont(style.font);
    g.drawString(label, x + style.padding, y + style.padding - 2);
  }
  
  public abstract void click();
}

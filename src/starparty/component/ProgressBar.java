/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.component;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import starparty.tactical.Tactical;

/**
 *
 * @author Tyler
 */
public class ProgressBar {
  String label;
  int x;
  int y;
  int width;
  int height;
  
  private final int BORDER = 2;
  private final int PADDING = 2;
  
  public ProgressBar(String label) {
    this.label = label;
  }
  
  public void setLocation(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  public void setSize(int width, int height) {
    this.width = width;
    this.height = height;
  }
  
  public void draw(Graphics g, int min, int max, int value) {
    g.setColor(Tactical.basicColor);
    g.fillRect(x, y, width, height);
    
    g.setColor(Tactical.backgroundColor);
    g.fillRect(x + BORDER, y + BORDER, width - BORDER * 2, height - BORDER * 2);
    
    double percent = (0.0 + value - min) / (max - min);
    int length = (int) (percent * (width - 2 * (PADDING + BORDER)));
    
    g.setColor(Color.darkGray);
    g.fillRect(x + PADDING + BORDER, y + PADDING + BORDER, length, height - (PADDING + BORDER) * 2);
    
    g.setColor(Tactical.basicColor);
    g.setFont(Tactical.smallFont);
    g.drawString(label, x + PADDING + BORDER + PADDING, y + PADDING + BORDER);
  }
}

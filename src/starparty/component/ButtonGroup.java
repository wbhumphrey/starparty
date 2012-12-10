/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.component;

import java.util.List;
import org.newdawn.slick.Graphics;

/**
 *
 * @author Tyler
 */
public class ButtonGroup {
  int x;
  int y;
  
  List<Button> buttons;
  int padding = 5;
  
  public ButtonGroup(List<Button> buttons) {
    this.buttons = buttons;
  }
  
  public void setLocation(int x, int y) {
    this.x = x;
    this.y = y;
    
    int currentX = x;
    
    for (Button button: buttons) {
      button.setLocation(currentX, y);
      currentX += button.width + padding;
    }
  }
  
  public void draw(Graphics g) {
    for (Button button: buttons) 
      button.draw(g);
  }
  
  public boolean click(int x, int y) {
    for (Button button: buttons) {
      if (button.click(x,y))
        return true;
    }
    
    return false;
  }
}

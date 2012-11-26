/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.tactical;

import java.util.List;
import org.newdawn.slick.Graphics;
import starparty.library.Weapon;

/**
 *
 * @author Tyler
 */
public class WeaponManager {
  int x;
  int y;
  int width;
  int height;
  
  List<Weapon> weapons;
  Weapon selectedWeapon;
  
  // Drawing constants
  final int LINE_SIZE = 42;
  
  public WeaponManager(List<Weapon> weapons) {
    this.weapons = weapons;
  }
  
  public void draw(Graphics g) {
    g.setFont(Tactical.basicFont);
    g.setColor(Tactical.basicColor);
    
    g.drawString("WEAPONS", x, y);
    
    for (int i = 0; i < weapons.size(); i++) {
      Weapon weapon = weapons.get(i);
      
      g.fillRect(x, y + LINE_SIZE + i * LINE_SIZE, 15, LINE_SIZE - 10);
      g.drawString(weapon.name, x + 20, y + LINE_SIZE + i * LINE_SIZE);
    }
  }
  
  public void setLocation(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  public void setSize(int width, int height) {
    this.width = width;
    this.height = height;
  }
}

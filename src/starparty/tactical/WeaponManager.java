/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.tactical;

import java.util.Collection;
import java.util.List;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import starparty.library.Weapon;
import starparty.library.WeaponRange;

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
  Target target;
  FiringControls firingControls;
  
  // Drawing constants
  final int LINE_SIZE = 46;
  
  // Image buffers
  
  public WeaponManager(List<Weapon> weapons, Target target) {
    this.weapons = weapons;
    this.target = target;
  }
  
  public void draw(Graphics g) {
    g.setFont(Tactical.titleFont);
    g.setColor(Tactical.basicColor);
    g.drawString("WEAPONS", x, y);
    
    g.setFont(Tactical.basicFont);
    for (int i = 0; i < weapons.size(); i++) {
      Weapon weapon = weapons.get(i);
      
      g.setColor(Color.darkGray);
      g.fillRect(x, y + LINE_SIZE + i * LINE_SIZE, width, LINE_SIZE - 10);
      
      if (weapon == selectedWeapon) {
        g.setColor(Color.red);
      } else {
        g.setColor(Tactical.basicColor);
      }
      
      g.fillRect(x, y + LINE_SIZE + i * LINE_SIZE, 15, LINE_SIZE - 10);
      g.drawString(weapon.name, x + 20, y + LINE_SIZE + i * LINE_SIZE);
    }
  }
  
  public void select(int i) {
    selectedWeapon = weapons.get(i);
    firingControls.setWeapon(selectedWeapon);
    
    target.setWeapon(selectedWeapon);
  }
  
  public boolean click(int x, int y) {
    if (x < this.x || y < this.y || x > this.x + width || y > this.y + height)
      return false;
    
    Rectangle r = new Rectangle(0, 0, 0, 0);
    for (int i = 0; i < weapons.size(); i++) {
      r.setBounds(this.x, this.y + LINE_SIZE + i * LINE_SIZE, width, LINE_SIZE - 10);
      
      if (r.contains(x, y)) {
        select(i);
        return true;
      }
    }
    
    return false;
  }
  
  public void setLocation(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  public void setSize(int width, int height) {
    this.width = width;
    this.height = height;
  }

  void setFiringControls(FiringControls firingControls) {
    this.firingControls = firingControls;
  }
}

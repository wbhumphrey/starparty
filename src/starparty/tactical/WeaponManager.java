/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.tactical;

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
  Radar radar;
  Target target;
  FiringControls firingControls;
  
  // Drawing constants
  final int LINE_SIZE = 46;
  final Color HEAVY_DAMAGE_COLOR = new Color(50, 0, 0);
  final Color MEDIUM_DAMAGE_COLOR = new Color(50, 50, 0);
  final Color LIGHT_DAMAGE_COLOR = new Color(0, 50, 0);
  
  // Image buffers
  Image weaponRange;
  Image weaponRangeBuffer;
  
  public WeaponManager(List<Weapon> weapons) {
    this.weapons = weapons;
  }
  
  public void setRadar(Radar radar) {
    this.radar = radar;
  }
  
  public void setTarget(Target target){
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
    
    int width = radar.width;
    int height = radar.height;
    
    try {
      if (weaponRange == null) {
          weaponRange = new Image(width, height);
          weaponRangeBuffer = new Image(width, height);
      }

      Graphics g = weaponRange.getGraphics();
      Graphics buffer = weaponRangeBuffer.getGraphics();
      
      g.clear();

      Weapon weapon = selectedWeapon;      
      for (WeaponRange r: weapon.ranges) {
        buffer.clear();

        if (r.percentDamage < .3)
          buffer.setColor(LIGHT_DAMAGE_COLOR);
        else if (r.percentDamage < .6)
          buffer.setColor(MEDIUM_DAMAGE_COLOR);
        else
          buffer.setColor(HEAVY_DAMAGE_COLOR);
        
        int radius = (int) (((0.0 + r.max) / radar.maxDistance) * (width / 2));
        int x = (width / 2) - radius;
        int y = (width / 2) - radius;
        
        buffer.fillOval(x, y, radius * 2, radius * 2);
        
        if (r.min > 0) {
          buffer.setDrawMode(Graphics.MODE_ALPHA_MAP);
          buffer.setColor(Color.transparent);
          radius = (int) (((0.0 + r.min) / radar.maxDistance) * (width / 2));
          x = (width / 2) - radius;
          y = (width / 2) - radius;

          buffer.fillOval(x, y, radius * 2, radius * 2);
          buffer.setDrawMode(Graphics.MODE_NORMAL);
        }
        
        buffer.flush();
        g.drawImage(weaponRangeBuffer, 0, 0);
      }
      
      g.flush();
      radar.setWeaponRangeImage(weaponRange);
    } catch (Exception e) {
      e.printStackTrace();
    }
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.tactical;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import starparty.component.Button;
import starparty.component.StatusBar;
import starparty.library.Weapon;

/**
 *
 * @author Tyler
 */
public class FiringControls {
  int x;
  int y;
  int width;
  int height;
  Weapon weapon;
  
  Button fireButton;
  StatusBar rechargeStatusBar;
  StatusBar damageStatusBar;
  StatusBar powerStatusBar;
  
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
  
  private void init() {
    rechargeStatusBar = new StatusBar("Recharge");
    rechargeStatusBar.setLocation(x + 220, y + 40);
    rechargeStatusBar.setSize(width - 220, 25);
    
    damageStatusBar = new StatusBar("Damage");
    damageStatusBar.setLocation(x + 220, y + 70);
    damageStatusBar.setSize(width - 220, 25);

    powerStatusBar = new StatusBar("Power");
    powerStatusBar.setLocation(x + 220, y + 100);
    powerStatusBar.setSize(width - 220, 25);
    
    fireButton = new Button("Fire") {
      @Override
      public void click() {
        if (weapon.canFire()) {
          weapon.fire();
          System.out.println("Fire!!!");
        }
      }
    };
    fireButton.setLocation(x + 220, y + 130);
    fireButton.setSize(width - 220, 85);
  }
  
  public void setWeapon(Weapon weapon) {
    this.weapon = weapon;
  }
  
  public void draw(Graphics g) {
    if (weapon != null) {
      g.drawImage(weapon.image, x, y);
      
      g.setFont(Tactical.basicFont);
      g.setColor(Tactical.basicColor);
      g.drawString(weapon.name, x + 220, y);

      rechargeStatusBar.draw(g, 0, weapon.cooldown, (int) weapon.getCurrentCooldown());
      // rechargeStatusBar.draw(g, 0, 100, 100);
      damageStatusBar.draw(g, 0, 100, 100);
      powerStatusBar.draw(g, 0, 100, 100);
      
      fireButton.draw(g);
    }
  }
  
  public boolean click(int x, int y) {
    if (weapon != null)
      return fireButton.click(x, y);
    
    return false;
  }
}

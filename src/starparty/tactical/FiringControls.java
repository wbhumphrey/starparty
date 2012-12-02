/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.tactical;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import starparty.component.Button;
import starparty.component.StatusBar;
import starparty.library.InterstellarObject;
import starparty.library.Weapon;
import starparty.utilities.Distance;

/**
 *
 * @author Tyler
 */
public class FiringControls implements TargetListener {

  int x;
  int y;
  int width;
  int height;
  Weapon weapon;
  InterstellarObject source;
  InterstellarObject target;
  Button fireButton;
  StatusBar rechargeStatusBar;
  StatusBar damageStatusBar;
  StatusBar powerStatusBar;

  public FiringControls(InterstellarObject source, Target target) {
    this.source = source;
    this.target = target.getTarget();
    target.addTargetListener(this);
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

    fireButton = new Button("Select target") {
      @Override
      public void click() {
        if (target != null && weapon.canFire()) {
          weapon.fire(target, Distance.calculate(source, target));
          System.out.println("Fire!!!");
        }
      }
    };
    fireButton.font = Tactical.titleFont;
    fireButton.setLocation(x + 220, y + 130);
    fireButton.setSize(width - 220, 85);
  }

  public void setWeapon(Weapon weapon) {
    this.weapon = weapon;
    updateFireButton();
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
    if (weapon != null) {
      return fireButton.click(x, y);
    }

    return false;
  }

  @Override
  public void targetChanged(Target newTarget) {
    target = newTarget.getTarget();
    updateFireButton();
  }

  public void updateFireButton() {
    if (target == null) {
      fireButton.color = new Color(255, 0, 0);
      fireButton.label = "Select Target";
    } else if (weapon == null) {
      fireButton.color = new Color(255, 0, 0);
      fireButton.label = "No Weapon Selected";
    } else if (weapon.inRange(Distance.calculate(source, target))) {
      fireButton.color = Tactical.basicColor;
      fireButton.label = "Fire";
    } else {
      fireButton.color = new Color(255, 0, 0);
      fireButton.label = "Out of Range";
    }
  }
  //make a fire button that can be called if the weapon or the target changes
//  public void 
}

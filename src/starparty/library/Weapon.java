/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.library;

import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Image;

/**
 *
 * @author Tyler
 */
public class Weapon {
  // Properties

  public String name;
  public List<WeaponRange> ranges = new ArrayList<WeaponRange>();
  public int maxDamage;
  public int requiredPower;
  public int cooldown = 10000;
  public Image image;
  // Status variables
  public double damage;
  public int power;
  public long lastFired;
  public int shieldDamage;
  public int hullDamage;
  public double shieldDamageReduction;

  public Weapon(String name) {
    this.name = name;
    try {
      this.image = new Image("resources/tactical/weapons/phaser.png");
    } catch (Exception e) {
    }
  }

  public void setCooldown(int cooldown) {
    this.cooldown = cooldown;
  }

  public boolean canFire() {
    return getCurrentCooldown() == cooldown;
  }

  public boolean inRange(double distance) {
    for (WeaponRange range : ranges) {
      if (range.min <= distance && range.max >= distance) {
        return true;
      }
    }
    return false;
  }

  public long getCurrentCooldown() {
    long timeSinceLastFired = System.currentTimeMillis() - lastFired;
    return Math.min(cooldown, timeSinceLastFired);
  }

  public void addRange(WeaponRange weaponRange) {
    ranges.add(weaponRange);
  }

  public double getDamageModifier(double distance) {
    double mod = -1;

    for (WeaponRange range : ranges) {
      if (range.min <= distance && range.max >= distance) {
        mod = (mod < 0 ? 1 : mod) * range.percentDamage;
      }
    }

    return mod;
  }

  public void fire(InterstellarObject target, double distance) {
    double damageModifier = getDamageModifier(distance);

    if (damageModifier >= 0) {
      lastFired = System.currentTimeMillis();
      target.recieveShieldDamage(shieldDamage * damageModifier);
      target.recieveHullDamage(hullDamage * damageModifier, shieldDamageReduction);
    }

  }
}

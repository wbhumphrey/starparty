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
  
  public Weapon(String name) {
    this.name = name;
    try {this.image = new Image("resources/tactical/weapons/phaser.png"); } catch (Exception e) {}
  }
  
  public void setCooldown(int cooldown) {
    this.cooldown = cooldown;
  }
  
  public boolean canFire() {
    return getCurrentCooldown() == cooldown;
  }
  
  public long getCurrentCooldown() {
    long timeSinceLastFired = System.currentTimeMillis() - lastFired;
    return Math.min(cooldown, timeSinceLastFired);
  }

  public void addRange(WeaponRange weaponRange) {
    ranges.add(weaponRange);
  }
  
  public void fire() {
    lastFired = System.currentTimeMillis();
  }
}

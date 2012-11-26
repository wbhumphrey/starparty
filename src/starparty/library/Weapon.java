/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.library;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tyler
 */
public class Weapon {
  public String name;
  public List<WeaponRange> ranges = new ArrayList<WeaponRange>();
  public int damage;
  
  public Weapon(String name) {
    this.name = name;
  }

  public void addRange(WeaponRange weaponRange) {
    ranges.add(weaponRange);
  }
}

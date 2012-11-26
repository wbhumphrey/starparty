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
  List<WeaponRange> ranges = new ArrayList<WeaponRange>();
  int damage;
  
  public Weapon(String name) {
    this.name = name;
  }
}

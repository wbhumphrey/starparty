/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.library;

/**
 *
 * @author Tyler
 */
public class WeaponRange {
  public int min;
  public int max;
  public double percentDamage;
  
  public WeaponRange(int min, int max, double percentDamage) {
    this.min = min;
    this.max = max;
    this.percentDamage = percentDamage;
  }
}

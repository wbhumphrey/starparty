/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.utilities;

import starparty.library.InterstellarObject;

/**
 *
 * @author Tyler
 */
public class Distance {
  public static float calculate(float x1, float y1, float z1, float x2, float y2, float z2) {
    return (float) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) + (z1 - z2) * (z1 - z2));
  }
  
  public static float calculate(InterstellarObject o1, InterstellarObject o2) {
    return calculate(o1.x, o1.y, o1.z, o2.x, o2.y, o2.z);
  }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.utilities;

import javax.vecmath.Point3d;
import starparty.library.InterstellarObject;

/**
 *
 * @author Tyler
 */
public class Distance {
  public static double calculate(double x1, double y1, double z1, double x2, double y2, double z2) {
    return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) + (z1 - z2) * (z1 - z2));
  }
  
  public static double calculate(InterstellarObject o1, InterstellarObject o2) {
    return o1.getLocation().distance(o2.getLocation());
  }
  
  public static boolean isDistanceGreaterThan(InterstellarObject o1, InterstellarObject o2, double distance) {
    Point3d p1 = o1.getLocation();
    Point3d p2 = o2.getLocation();
    return Math.abs(p1.x - p2.x) > distance ||
            Math.abs(p1.y - p2.y) > distance ||
            Math.abs(p1.z - p2.z) > distance ||
            p1.distance(p2) > distance;
  }
}

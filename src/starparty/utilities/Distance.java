/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.utilities;

import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;
import starparty.library.InterstellarObject;
import starparty.library.Ship;

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
    Point2d p1 = o1.getLocation();
    Point2d p2 = o2.getLocation();
    return Math.abs(p1.x - p2.x) > distance ||
            Math.abs(p1.y - p2.y) > distance ||
            p1.distance(p2) > distance;
  }
  
  public static double angle(InterstellarObject source, InterstellarObject target){
    return Math.atan2(target.getLocation().y - source.getLocation().y,
            target.getLocation().x - source.getLocation().x) ;
  }
}

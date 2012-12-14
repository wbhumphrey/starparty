/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.utilities;

import javax.vecmath.Point2d;
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
    Point2d p1 = o1.getLocation();
    Point2d p2 = o2.getLocation();
    return Math.abs(p1.x - p2.x) > distance ||
            Math.abs(p1.y - p2.y) > distance ||
            p1.distance(p2) > distance;
  }
  
  /**
   * @return Returns the angle (in radians) between the source and target object
   */
  public static double angle(InterstellarObject source, InterstellarObject target){
    return Math.atan2(target.getLocation().y - source.getLocation().y,
            target.getLocation().x - source.getLocation().x) ;
  }
  
  public static boolean isAngleBetween(Angle minAngle, Angle maxAngle, double degrees){
    double min = minAngle.getDegrees() % 360;
    double max = maxAngle.getDegrees() % 360;
    
    //if min == max, Return true
    max = (min >= max ? max + 360 : max);
    
    degrees %= 360;
    //if min == degrees, return true
    degrees = (min > degrees ? degrees + 360 : degrees);
    
    return max >= degrees;
  }
}

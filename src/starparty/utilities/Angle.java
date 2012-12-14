/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.utilities;

/**
 *
 * @author brad
 */
public class Angle {
  boolean inDegrees = true;
  double value;
  
  public void setRadians(double radians){
    value = radians;
    inDegrees = false;
  }
  
  public void setDegrees(double degrees){
    value = degrees;
    inDegrees = true;
  }
  
  public double getRadians(){
    return (inDegrees ? Math.toRadians(value) : value);
  }
  
  public double getDegrees(){
    return (inDegrees ? value : Math.toDegrees(value));
  }
  
}

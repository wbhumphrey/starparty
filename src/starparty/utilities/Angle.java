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
  
  public void addRadians(double radians){
    value += (inDegrees ? Math.toDegrees(radians) : radians);
  }
  
  public void addDegrees(double degrees){
    value += (inDegrees ? degrees : Math.toRadians(degrees));
  }
  
}

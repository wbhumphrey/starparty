package starparty.library;

import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;
import org.newdawn.slick.Image;
import starparty.utilities.Angle;

public class InterstellarObject {
  public String name;
  public String type;
  public String organization;
  private Point2d location = new Point2d();
  private Vector2d velocity = new Vector2d();
  public Angle direction = new Angle();
  public Image portrait;
  public Image icon;
  
  float acceleration = 10000;
  float turnSpeed = 15;

  public InterstellarObject(String name, double x, double y) {
    this.name = name;
    setLocation(x, y);
  }

  public InterstellarObject() {
  }

  public int hash(int mod) {
    return Math.abs(name.hashCode() % mod);
  }

  public void recieveShieldDamage(double damage) {
  }

  public void recieveHullDamage(double damage, double shieldDamageReduction) {
  }

  public boolean isDestroyed() {
    return false;
  }

  public Point2d getLocation() {
    return location;
  }

  final public void setLocation(double x, double y) {
    location.x = x;
    location.y = y;
  }

  public Vector2d getVelocity() {
    return velocity;
  }

  public void setVelocity(double x, double y) {
    velocity.x = x;
    velocity.y = y;
  }

  public void update(int delta) {
    location.x += velocity.x * delta;
    location.y += velocity.y * delta;
  }

  public void accelerate(int delta) {
    velocity.x += (float) Math.cos(direction.getRadians()) * (delta / acceleration);
    velocity.y += (float) Math.sin(direction.getRadians()) * (delta / acceleration);
  }

  public void decelerate(int delta) {
    accelerate(-delta);
  }

  public void brake(int delta) {
    if (velocity.length() < .03) {
      velocity.x = 0;
      velocity.y = 0;
      
      return;
    }
    
    double angle = Math.atan2(velocity.y, velocity.x) + Math.PI;

    velocity.x += (float) Math.cos(angle) * (delta / acceleration);
    velocity.y += (float) Math.sin(angle) * (delta / acceleration);
  }

  public void rotateCCW(int delta) {
    direction.addDegrees(-(delta / turnSpeed));
  }

  public void rotateCW(int delta) {
    direction.addDegrees(delta / turnSpeed);
  }
}

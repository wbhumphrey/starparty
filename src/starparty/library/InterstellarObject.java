package starparty.library;

import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;
import org.newdawn.slick.Image;
import starparty.utilities.Angle;
import starparty.utilities.Distance;

public class InterstellarObject {

  public String name;
  public String type;
  public String organization;
  private Point2d location = new Point2d();
  private Vector2d velocity = new Vector2d();
  public Angle direction = new Angle();
  public Image portrait;
  public Image icon;

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
    velocity.x += (float) Math.cos(direction.getRadians()) * (delta / 5000.0f);
    velocity.y += (float) Math.sin(direction.getRadians()) * (delta / 5000.0f);
  }

  public void decelerate(int delta) {
    accelerate(-delta);
  }

  public void brake(int delta) {
    double angle = Distance.angle(this) - Math.PI;
    
    velocity.x += (float) Math.cos(angle) * (delta / 5000.0f);
    velocity.y += (float) Math.sin(angle) * (delta / 5000.0f);
    
    if (velocity.x < .01)
      velocity.x = 0;
    
    if (velocity.y < .01)
      velocity.y = 0;
    }

  public void rotateCCW(int delta) {
    direction.addDegrees(-(delta / 10.0f));
  }

  public void rotateCW(int delta) {
    direction.addDegrees(delta / 10.0f);
  }
}

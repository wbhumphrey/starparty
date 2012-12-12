package starparty.library;

import java.util.ArrayList;
import java.util.List;
import javax.vecmath.Vector2d;

public class Ship extends InterstellarObject {
  private int maxShieldStrength;
  private int maxHullStrength;
  public double shieldStrength = -1;
  public double hullStrength = -1;
  public List<Weapon> weapons = new ArrayList<Weapon>();
  private Vector2d direction = new Vector2d();

  public Ship(String name, float x, float y) {
    super(name, x, y);
  }
  
  public Ship(){
  }
  
  public void initialize() {
    hullStrength = ( hullStrength >= 0 ? hullStrength : maxHullStrength);
    shieldStrength = ( shieldStrength >= 0 ? shieldStrength : maxShieldStrength);
      
    for(Weapon weapon : weapons){
      weapon.initialize();
    }
  }
  
  @Override
  public boolean isDestroyed(){
    return hullStrength <= 0;
  }

  @Override
  public void recieveShieldDamage(double damage) {
    shieldStrength = Math.max(shieldStrength - damage, 0);
  }
  
  @Override
  public void recieveHullDamage(double damage, double shieldDamageReduction) {
    double damageReduction = damage * shieldDamageReduction * ( shieldStrength / maxShieldStrength);
    double damageDelt = Math.max( damage - damageReduction, 0 );
    hullStrength = Math.max( hullStrength - damageDelt, 0);
  }
  
  /* Getters and setters */

  public int getMaxShieldStrength() {
    return maxShieldStrength;
  }

  public void setMaxShieldStrength(int maxShieldStrength) {
    this.maxShieldStrength = maxShieldStrength;
  }

  public int getMaxHullStrength() {
    return maxHullStrength;
  }

  public void setMaxHullStrength(int maxHullStrength) {
    this.maxHullStrength = maxHullStrength;
  }

  public Vector2d getDirection() {
    return direction;
  }

  public void setDirection(double x, double y) {
    direction.x = x;
    direction.y = y;
  }
  
  

}

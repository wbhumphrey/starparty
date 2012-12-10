/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.tactical;

import java.util.HashSet;
import java.util.Set;
import starparty.library.InterstellarObject;
import starparty.library.Weapon;

/**
 *
 * @author brad
 */
public class Target {

  private InterstellarObject target;
  private InterstellarObject oldTarget;
  private Weapon weapon;
  private Weapon oldWeapon;
  private Set<TargetListener> listeners = new HashSet<TargetListener>();

  public InterstellarObject getTarget() {
    return target;
  }

  public void setTarget(InterstellarObject target) {
    saveOldState();
    this.target = target;
    notifyTargetChanged();
  }
  
  public InterstellarObject getOldTarget(){
    return oldTarget;
  }
  
  public Weapon getWeapon() {
    return weapon;
  }
  
  public void setWeapon(Weapon weapon) {
    saveOldState();
    this.weapon = weapon;
    notifyTargetChanged();
  }
  
  public Weapon getOldWeapon(){
    return oldWeapon;
  }
  
  private void saveOldState(){
    oldTarget = target;
    oldWeapon = weapon;
  }
  
  public void notifyTargetChanged(){
    for(TargetListener listener : listeners){
      listener.targetChanged(this);
    }
  }
  
  public void addTargetListener(TargetListener listener){
    listeners.add(listener);
  }
  
  public void removeTargetListener(TargetListener listener){
    listeners.add(listener);
  }
}

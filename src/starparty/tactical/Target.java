/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.tactical;

import java.util.HashSet;
import java.util.Set;
import starparty.library.InterstellarObject;

/**
 *
 * @author brad
 */
public class Target {

  private InterstellarObject target;
  private Set<TargetListener> listeners = new HashSet<TargetListener>();

  public InterstellarObject getTarget() {
    return target;
  }

  public void setTarget(InterstellarObject target) {
    this.target = target;
    notifyTargetChanged();
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

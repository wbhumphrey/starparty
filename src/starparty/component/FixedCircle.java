/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.component;

import org.newdawn.slick.geom.Circle;

/**
 *
 * @author Tyler
 */
public class FixedCircle extends Circle {
  public FixedCircle(float x, float y, float radius) {
    super(x, y, radius);
  }

  @Override
  public strictfp boolean contains(float x, float y) {
    return (x - getCenterX()) * (x - getCenterX()) + (y - getCenterY()) * (y - getCenterY()) < getRadius() * getRadius();
  }
}

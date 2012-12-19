/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Tyler
 */
public abstract class Station {
    public abstract void draw(Graphics g);
    public abstract void update(int delta);

    public void init(GameContainer gc) throws SlickException {}
    public void keyPressed(int key, char c) {}
    public void keyReleased(int key, char c) {}
    public void mouseClicked(int button, int x, int y, int clickCount) {}
    public void mousePressed(int button, int x, int y) {}
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {}
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.personnel;

/**
 *
 * @author Tyler
 */
public class RoomDisplay {
  int x;
  int y;
  int width;
  int height;
  NodeSelection node;
  
  public void setLocation(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  public void setSize(int width, int height) {
    this.width = width;
    this.height = height;
  }

  void setSelectedNode(NodeSelection selectedNode) {
    this.node = selectedNode;
  }
}

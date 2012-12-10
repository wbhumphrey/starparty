/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starparty.personnel;

import com.google.gson.Gson;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.*;
import starparty.component.FixedCircle;
import starparty.library.Schematic;
import starparty.library.ShipInternals;
import starparty.library.ShipNode;
import starparty.library.Team;
import starparty.utilities.FontLoader;
import starparty.utilities.ImageLoader;

/**
 *
 * @author Tyler
 */
public class Personnel extends BasicGame {
  public static int mouseX;
  public static int mouseY;
  
  public static Color basicColor;
  public static Color backgroundColor;
  public static UnicodeFont titleFont;
  public static UnicodeFont basicFont;
  public static UnicodeFont smallFont;
  
  TeamSelection selectedTeam;
  NodeSelection selectedNode;
  
  List<Team> teams = new ArrayList<Team>();
  
  Image background;
  Schematic schematic;
  ShipInternals shipInternals;
  
  TeamManager teamManager;
  TeamControls teamControls;
  PersonnelDisplay personnelDisplay;
  RoomControls roomControls;
  
  public Personnel() {
    super("StarParty");
  }
  
	public static void main(String ... args){
		try {
			AppGameContainer app = new AppGameContainer(new Personnel());
			app.setDisplayMode(1024, 700, false);
			app.setSmoothDeltas(true);
			app.setTargetFrameRate(60);
			app.setShowFPS(true);
			app.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
  
  @Override
  public void init(GameContainer gc) throws SlickException {
    System.out.println(new FixedCircle(10, 10, 2).contains(8, 8));
    System.out.println(new FixedCircle(10, 10, 2).contains(10, 10));
    
    basicColor = new Color(100, 151, 244);
    backgroundColor = Color.black;

    basicFont = FontLoader.load("TCM_____.TTF", 30);
    titleFont = FontLoader.load("TCM_____.TTF", 35, true);
    smallFont = FontLoader.load("TCM_____.TTF", 15);

    
    background = ImageLoader.load("personnel/background.jpg");
    
    Gson gson = new Gson();
    try {
      schematic = gson.fromJson(new FileReader("resources/personnel/schematic.json"), Schematic.class);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    shipInternals = schematic.getShipInternals();
    List<ShipNode> path = shipInternals.getPath(8, 12);
    System.out.println(path);
    
    teams.add(new Team("Security Alpha", shipInternals.nodes.get(1)));
    teams.add(new Team("Security Beta", shipInternals.nodes.get(1)));
    teams.add(new Team("Medical Team", shipInternals.nodes.get(1)));
    teams.add(new Team("Damage Control", shipInternals.nodes.get(1)));
    teams.add(new Team("Engineering", shipInternals.nodes.get(1)));
    teams.add(new Team("Command", shipInternals.nodes.get(1)));
    teams.add(new Team("Communication", shipInternals.nodes.get(1)));
    teams.add(new Team("Sensor", shipInternals.nodes.get(1)));
    teams.add(new Team("Morale", shipInternals.nodes.get(1)));
    teams.add(new Team("Transporter", shipInternals.nodes.get(1)));
    teams.add(new Team("Pilot", shipInternals.nodes.get(1)));
    teams.add(new Team("Attack", shipInternals.nodes.get(1)));
    
    selectedTeam = new TeamSelection();
    selectedNode = new NodeSelection();
    
    teamControls = new TeamControls();
    teamControls.setLocation(771, 53);
    teamControls.setSize(242, 117);
    teamControls.setShipInternals(shipInternals);
    teamControls.setSelectedNode(selectedNode);
    teamControls.setSelectedTeam(selectedTeam);

    personnelDisplay = new PersonnelDisplay(shipInternals, teams);
    personnelDisplay.setLocation(120, 261);
    personnelDisplay.setSize(1000, 700);
    personnelDisplay.setSelectedNode(selectedNode);
    
    teamManager = new TeamManager(teams);
    teamManager.setLocation(102, 16);
    teamManager.setSize(648, 145);
    teamManager.setSelectedTeam(selectedTeam);
    
    roomControls = new RoomControls();
    roomControls.setLocation(93, 553);
    roomControls.setSize(183, 3);
    roomControls.setRooms(shipInternals.rooms.subList(0, 12));
    roomControls.setSelectedNode(selectedNode);
  }

  @Override
  public void mouseMoved(int oldx, int oldy, int newx, int newy) {
    mouseX = newx;
    mouseY = newy;
  }

  @Override
  public void update(GameContainer gc, int delta) throws SlickException {
    for (Team team: teams) {
      team.update(delta);
    }
  }

  @Override
  public void render(GameContainer gc, Graphics g) throws SlickException {
    g.drawImage(background, 0, 0);

    teamManager.draw(g);
    teamControls.draw(g);
    personnelDisplay.draw(g);
    roomControls.draw(g);
    
    // g.setColor(Color.white);
    // g.drawString("(" + mouseX + ", " + mouseY + ")", 10, 30);
  }

  @Override
  public void mousePressed(int button, int x, int y) {
    teamManager.click(x, y);
    teamControls.click(x, y);
    roomControls.click(x, y);
    personnelDisplay.click(x, y);
  }
}

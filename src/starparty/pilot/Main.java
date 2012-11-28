package starparty.pilot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import starparty.library.InterstellarObject;
import starparty.library.Player;
import starparty.utilities.NameGenerator;

public class Main extends BasicGame {

	static int kLEFT = 203, kUP = 200, kRIGHT = 205, kDOWN = 208, kSPACE = 57;
	
	//Temporary
	List<InterstellarObject> interstellarObjects = new ArrayList<InterstellarObject>();
	
	//Not Temporary
	Player player = new Player(0, 0, 0);
	Viewscreen viewPortXY = new Viewscreen(player, 10, 10, 400, 400, true, interstellarObjects);
	Viewscreen viewPortYZ = new Viewscreen(player, 520, 10, 400, 400, false, interstellarObjects);
	
	public Main() {
		super("StarParty");
		// TODO Auto-generated constructor stub
	}
	
	public void keyPressed(int keyCode, char c){
		if(keyCode == kLEFT)player.mLeft = true;
		else if(keyCode == kUP)player.mUp = true;
		else if(keyCode == kRIGHT)player.mRight = true;
		else if(keyCode == kDOWN)player.mDown = true;
		else if(keyCode == kSPACE)player.moving = true;
	    super.keyPressed(keyCode, c);
	}
	
	public void keyReleased(int keyCode, char c){
		if(keyCode == kLEFT)player.mLeft = false;
		else if(keyCode == kUP)player.mUp = false;
		else if(keyCode == kRIGHT)player.mRight = false;
		else if(keyCode == kDOWN)player.mDown = false;
		else if(keyCode == kSPACE)player.moving = false;
		super.keyReleased(keyCode, c);
	}

	public static void main(String ... args){
		try {
			System.out.println(new java.io.File( "." ).getCanonicalPath());
			AppGameContainer app = new AppGameContainer(new Main());
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
	public void render(GameContainer container, Graphics screen) throws SlickException {
		// TODO Auto-generated method stub
		viewPortXY.draw(screen);
		viewPortYZ.draw(screen);
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		Random r = new Random();
		for(int i = 1; i <= 1000; i++){
			interstellarObjects.add(new InterstellarObject(NameGenerator.generate("ship", "federation"), r.nextFloat() * 800 - 400, r.nextFloat() * 800 - 400, r.nextFloat() * 800 - 400));
		}
	}

	@Override
	public void update(GameContainer arg0, int delta) throws SlickException {
		// TODO Auto-generated method stub
		if(player.moving){
			if(player.mLeft)player.angle -= player.speed;
			if(player.mUp)player.z += player.speed;
			if(player.mRight)player.angle += player.speed;
			if(player.mDown)player.z -= player.speed;
			player.x += Math.cos(Math.toRadians(player.angle));
			player.y += Math.sin(Math.toRadians(player.angle));
		}
		//System.out.println(player.y);
		
	}
}

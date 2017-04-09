package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame{

	//creating the gamename string
	public static final String gamename = "SSI";
	//creating variables to reference the states of the game
	public static final int menu = 0;
	public static final int outside = 1;
	public static final int foyer = 2;
	public static final int firstfloor = 3;
	public static final int mv = 4;
	
	
	//initial constructor... will display the gamename in the top of the window and add the states
	public Game(String gamename){
		super(gamename);
		this.addState(new Menu(menu));
		this.addState(new Outside(outside));
		this.addState(new Foyer(foyer));
		this.addState(new FirstFloor(firstfloor));
		this.addState(new MV(mv));
	}
	//initialize both the states within the GameContainer
	public void initStatesList(GameContainer gc) throws SlickException{
		this.getState(menu).init(gc, this);
		//tells computer to go to the menu state when the game first opens
		this.enterState(menu);
	}
	
	public static void main(String[] args) {
		//Creating our window for the game
		AppGameContainer appgc; // this will be our window
		try{
			appgc = new AppGameContainer(new Game(gamename)); // we are going to create a window with our gamename
			appgc.setDisplayMode(640, 360, false); //the length and width of the window... set true for full screen
			appgc.start();
		}catch(SlickException e){
			e.printStackTrace(); 
		}
	}

}

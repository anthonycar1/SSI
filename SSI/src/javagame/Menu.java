package javagame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState{
	
	
	Image playNow;
	Image exitGame;
	String mystring = "hello";
	
	//constructor for the menu
	public Menu(int state){
		
	}
	
	//initialize stuff the class needs
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		playNow = new Image("res/playNow.png");
		exitGame = new Image("res/exitGame.png");
	}
	
	//rendering the game "drawing"
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.drawString("Clown-Ass Mike and the Quest to Save After-Cross!", 100, 50);
		playNow.draw(100,100);
		exitGame.draw(100, 200);
		
	}
	
	//update method to updated the images on the screen 
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		
		// activate play now button
		if((posX>100 && posX<300) && (posY>230 && posY<260)){
			if(Mouse.isButtonDown(0)){
				sbg.enterState(1);
			}
		}
		// activate exit game button
		if((posX>100 && posX<300) && (posY>130 && posY<160)){
			if(Mouse.isButtonDown(0)){
				System.exit(0);
			}
		}
	}
	
	//grabs the ID for our menu state, which is 0
	public int getID(){
		return 0;
	}
}

package javagame;


import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.*;

public class FirstFloor extends BasicGameState{
	
	Image firstFloor;
	float hoboPosX = 220;
	float hoboPosY = -800;
	float shiftX = 320; // keeps hobo in the middle of the screen
	float shiftY = 160; // keeps hobo in the middle of the screen
	float rShiftX= shiftX + 10;
	float topBound = 149;
	float botBound = -800;
	float leftBound = 328;
	float rightBound = -111;
	SpriteSheet hoboUp, hoboDown, hoboLeft, hoboRight;
	Image mvKey;
	boolean mvKeyCheck = false;
	boolean ngKeyCheck = false;
	boolean jnKeyCheck = false;
	Animation hoboAnimationUp, hoboAnimationDown, hoboAnimationLeft, hoboAnimationRight, hobo;
	private Rectangle hoboRect = null;
	private boolean esc = false;
	private boolean doorMsg = false;
	private String doorMsgString = "The door is locked.";
	
	
	
	int posX = 0; //mouse coords
	int posY = 0;
	
	//constructor for the menu
	public FirstFloor(int state){
		
	}
	
	//initialize stuff the class needs
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		firstFloor = new Image("res/State 3 - 1st floor.png");
		hoboUp = new SpriteSheet("res/maincharacterwalkingup.png", 64, 64);
		hoboDown = new SpriteSheet("res/maincharacterwalkingdown.png", 64, 64);
		hoboLeft = new SpriteSheet("res/maincharacterwalkingleft.png", 64, 64);
		hoboRight = new SpriteSheet("res/maincharacterwalkingright.png", 64, 64);
		hoboAnimationUp = new Animation(hoboUp, 250);
		hoboAnimationDown = new Animation(hoboDown, 250);
		hoboAnimationLeft = new Animation(hoboLeft, 250);
		hoboAnimationRight = new Animation(hoboRight, 250);
		hobo = hoboAnimationUp;
		hoboRect = new Rectangle(rShiftX, shiftY, 44, 64);
		mvKey = new Image("res/keyspritesheet.png");
		
	}
	
	//rendering the game "drawing"
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		posX = Mouse.getX();
		posY = Mouse.getY();
		
		firstFloor.draw(hoboPosX, hoboPosY);
		hobo.draw(shiftX, shiftY);
		g.setColor(new Color(255,255,255,0));
		g.draw(hoboRect);
		g.setColor(new Color(255,0,0));
		g.drawString("Hobo's X: "+ hoboPosX+ "\nHobo's PositionY: "+hoboPosY, 20, 40);
		g.setColor(new Color(255,0,0));
		g.drawString("mouse x: "+posX+"\nmouse y: "+ posY, 20, 150);
		
		if(mvKeyCheck == true){
			mvKey.draw(600,300, new Color(60,0,16));
		}else{
			mvKey.draw(hoboPosX + 270, hoboPosY + 125, new Color(60,0,16));
		}
		
		if(doorMsg == true){
			g.drawString(doorMsgString, 100, 100);
		}
		
		//esc menu
				if (esc == true){
					g.drawString("Resume (R)", 250, 100);
					g.drawString("Main Menu (M)", 250, 150);
					g.drawString("Quit (Q)", 250, 200);
					if(esc == false){
						g.clear();
					}
				}
		
	}
	
	//update method to updated the images on the screen 
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		Input input = gc.getInput();
		//hobo walking ------------------------------------------------------------------------------------------------
				if(input.isKeyDown(Input.KEY_UP)){
					hobo = hoboAnimationUp; //make Hobo look up
					hoboAnimationUp.start();
					hoboPosY += delta * .1f; //increase the Y position of Hobo
					if (hoboPosY > topBound){
						hoboPosY -= delta * .1f;
					}if (hoboPosY > -462 && hoboPosX < 277 & hoboPosX > 239 ){
						hoboPosY -= delta * .1f;//bathroom
					}if (hoboPosY > -456 && hoboPosX < 328 & hoboPosX > 277 ){
						hoboPosY -= delta * .1f;//bathoom
					}if (hoboPosY > -349 && hoboPosX > 164 && hoboPosY < -348){
						hoboPosY -= delta * .1f;//johns door
					}if (hoboPosY > -20 && hoboPosX < 117 && hoboPosY < 64){
						hoboPosY -= delta * .1f;//right kitchen bottom wall
					}
					}
					else{
						hoboAnimationUp.stop();
						}
				

					
				if(input.isKeyDown(Input.KEY_DOWN)){
					hobo = hoboAnimationDown; //make Hobo look up
					hoboAnimationDown.start();
					hoboPosY -= delta * .1f; //increase the Y position of Hobo
					if (hoboPosY < botBound){ 
						hoboPosY += delta * .1f; //dont allow any more values to be added to the Y position collision
					}if (hoboPosY < -463 && hoboPosX < 277 && hoboPosX > 239){
						hoboPosY += delta * .1f; //bathroom
					}if (hoboPosY < -550 && hoboPosX < 328 && hoboPosX > 286){
						hoboPosY += delta * .1f; //bathroom
					}if (hoboPosY < -351 && hoboPosX < 192){
						hoboPosY += delta * .1f; //bottom living room wall
					}if (hoboPosY < 69 && hoboPosX >171 && hoboPosY > 68.1){
						hoboPosY += delta * .1f; //left kitchen bottom wall
					}if (hoboPosY < 71  && hoboPosX < 114 && hoboPosY > 69){
						hoboPosY += delta * .1f; //right kitchen bottom wall
					}
					
					}else{
						hoboAnimationDown.stop();
					}
				
				if(input.isKeyDown(Input.KEY_LEFT)){
					hobo = hoboAnimationLeft; //make Hobo look up
					hoboAnimationLeft.start();
					hoboPosX += delta * .1f; //increase the Y position of Hobo
					if (hoboPosX > leftBound){ 
						hoboPosX -= delta * .1f; //dont allow any more values to be added to the Y position collision
					}if (hoboPosX > 239 && hoboPosX < 240 && hoboPosY < -467){ 
						hoboPosX -= delta * .1f; //hallway left wall, pre bath
					}if (hoboPosX > 239 && hoboPosX < 240 && hoboPosY > -462 && hoboPosY < -348){ 
						hoboPosX -= delta * .1f; //hallway left wall, post bath
					}if (hoboPosX > 165 && hoboPosY < 69 && hoboPosY > -349){ 
						hoboPosX -= delta * .1f; //hallway left wall, livingroom
					}if (hoboPosX > 240 && hoboPosY > 73){ 
						hoboPosX -= delta * .1f; //hallway left wall, livingroom
					}
					}
					else{
						hoboAnimationLeft.stop();
					}
				if(input.isKeyDown(Input.KEY_RIGHT)){
					hobo = hoboAnimationRight; //make Hobo look up
					hoboAnimationRight.start();
					hoboPosX -= delta * .1f; //increase the Y position of Hobo
					if (hoboPosX < rightBound){ 
						hoboPosX += delta * .1f; //dont allow any more values to be added to the Y position collision
					}if (hoboPosX < 196 && hoboPosY < -351){ 
						hoboPosX += delta * .1f; //hallway right wall
					}if (hoboPosX < 117 && hoboPosY > -22 && hoboPosY < 65){ 
						hoboPosX += delta * .1f; //kitchen doorway
					}if (hoboPosX < 286 && hoboPosY > -456 && hoboPosY < -466){ 
						hoboPosX += delta * .1f; //top of right wall bath
					}if (hoboPosX < 286 && hoboPosY < -464 && hoboPosX > 285){ 
						hoboPosX += delta * .1f; //top of right wall bath
					}
					}
					else{
						hoboAnimationRight.stop();
					}
				if(input.isKeyDown(Input.KEY_DOWN)){
					if(hoboPosY < -798 && hoboPosY >= -800){
						sbg.enterState(2);//leaving out the front door to the foyer
					}
				}
				if(hobo == hoboAnimationDown){ // grab the key for mv
					if(hoboPosY > 106 && hoboPosY < 107 && hoboPosX < 87 && hoboPosX > 49){
						if(input.isKeyDown(Input.KEY_SPACE)){
							mvKeyCheck = true;
						}
					}
				}
				
				if(input.isKeyDown(Input.KEY_SPACE)){//check mv door
					if(hoboPosX < 197 && hoboPosY > -770 && hoboPosY < -739 && (mvKeyCheck == true)){
						sbg.enterState(4);
					}if(hoboPosX < 197 && hoboPosY > -770 && hoboPosY < -739 && (mvKeyCheck == false)){
						doorMsg = true;
					}
				}else {doorMsg = false;
				}
				if(input.isKeyDown(Input.KEY_SPACE)){//check ng door
					if(hoboPosX < 197 && hoboPosY > -587 && hoboPosY < -559 && (ngKeyCheck == true)){
						sbg.enterState(6);
					}if(hoboPosX < 197 && hoboPosY > -587 && hoboPosY < -559 && (ngKeyCheck == false)){
						doorMsg = true;
					}
				}else {doorMsg = false;
				}
				if(input.isKeyDown(Input.KEY_SPACE)){//check jn door
					if(hoboPosY > -350 && hoboPosX > 189 && hoboPosX < 239 && (jnKeyCheck == true)){
						sbg.enterState(5);
					}if(hoboPosY > -350 && hoboPosX > 189 && hoboPosX < 239 && (jnKeyCheck == false)){
						doorMsg = true;
					}
				}else {doorMsg = false;
				}
				//escape menu
				if(input.isKeyDown(Input.KEY_ESCAPE)){
					esc = true;
				}
		//when menu is up
				if(esc == true){
					if(input.isKeyDown(Input.KEY_R)){
						esc = false;
					}
					if(input.isKeyDown(Input.KEY_M)){
						sbg.enterState(0);
					}
					if(input.isKeyDown(Input.KEY_Q)){
						System.exit(0);
					}
				}
				
				
				//-------------------------------------------------------------------------------
				
				}
	
	//grabs the ID for our Foyer state, which is 2
	public int getID(){
		return 3;
	}
}

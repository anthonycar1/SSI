package javagame;


import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.*;

public class Foyer extends BasicGameState{
	
	Image foyer;
	float hoboPosX = 160;
	float hoboPosY = 110;
	float shiftX = 320; // keeps hobo in the middle of the screen
	float shiftY = 160; // keeps hobo in the middle of the screen
	float rShiftX= shiftX + 10;
	float topBound = 149;
	float botBound = 108;
	float leftBound = 328;
	float rightBound = -4;
	SpriteSheet hoboUp, hoboDown, hoboLeft, hoboRight;
	Animation hoboAnimationUp, hoboAnimationDown, hoboAnimationLeft, hoboAnimationRight, hobo;
	private Rectangle hoboRect = null;
	private String leftDoor = "The door is locked";
	private String frontDoor = "Ehhh, better not go back outside";
	private boolean leftDoorMsg = false;
	private boolean frontDoorMsg = false;
	private boolean esc = false;
	
	int posX = 0; //mouse coords
	int posY = 0;
	
	//constructor for the menu
	public Foyer(int state){
		
	}
	
	//initialize stuff the class needs
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		foyer = new Image("res/State2 - Foyer.png");
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
		
	}
	
	//rendering the game "drawing"
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		posX = Mouse.getX();
		posY = Mouse.getY();
		
		foyer.draw(hoboPosX, hoboPosY);
		hobo.draw(shiftX, shiftY);
		g.setColor(new Color(255,255,255,0));
		g.draw(hoboRect);
		g.setColor(new Color(255,0,0));
		g.drawString("Hobo's X: "+ hoboPosX+ "\nHobo's PositionY: "+hoboPosY, 20, 40);
		g.setColor(new Color(255,0,0));
		g.drawString("mouse x: "+posX+"\nmouse y: "+ posY, 20, 150);
		if(leftDoorMsg == true){
		g.drawString(leftDoor, 160, 110);
		}
		if(frontDoorMsg == true){
			g.drawString(frontDoor, 160, 90);
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
					}
					}
					else{
						hoboAnimationRight.stop();
					}
				
				//-------------------------------------------------------------------------------
				if(input.isKeyDown(Input.KEY_SPACE)){ //Check if left door is locked
					if(hoboPosX < 248 && hoboPosX > 210 && hoboPosY > 148){
						leftDoorMsg = true; 
					}
				}else{
					leftDoorMsg = false;
				}
				if(input.isKeyDown(Input.KEY_SPACE)){ //leave out front door
					if(hoboPosX < 179 && hoboPosX > 135 && hoboPosY > 107){
						sbg.enterState(1);
					}
				}else{
					frontDoorMsg = false;
				}
				if(input.isKeyDown(Input.KEY_SPACE)){ //enter 1st floor
					if(hoboPosX < 114 && hoboPosX > 67 && hoboPosY > 148){
						sbg.enterState(3);
					}
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
				
				
				
				}
	
	//grabs the ID for our Foyer state, which is 2
	public int getID(){
		return 2;
	}
}

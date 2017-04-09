package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.gui.TextField;



public class Outside extends BasicGameState{
	
	TextField txt;
	TrueTypeFont font;
	String checkNums = "1492";
	String nums = "1243124234";
	private Image outside;
	float hoboPosX = 165;
	float hoboPosY = -155;
	float topBound = 160;
	float botBound = -160;
	float leftBound = (float) 343.5;
	float rightBound = -274;
	int num1 = 69, num2 = 0, num3 = 0, num4 = 0;
	String numbers = " 0 1 2 3 4 5 6 7 8 9 ";
	Rectangle numRec;
	
	
	float shiftX = 320; // keeps hobo in the middle of the screen
	float shiftY = 160; // keeps hobo in the middle of the screen
	float rShiftX= shiftX + 10;
	SpriteSheet hoboUp, hoboDown, hoboLeft, hoboRight;
	Animation hoboAnimationUp, hoboAnimationDown, hoboAnimationLeft, hoboAnimationRight, hobo;
	private boolean esc = false;
	private Rectangle hoboRect = null;
	
	private String bookTitle = "\033'The Four Voyages of Christopher Columbus'\033";
	private boolean book = false;
	private boolean frontDoorOpen = false;
	private boolean key = false;
	private boolean lock = false;
	private boolean wrongCode = false;
	private String wrongCodeMsg = "I guess that's the wrong code... There must be a hint around \nhere somewhere!";
	private String lockBox = "Hmmm, a lock box... \nEnter the 4 digit code below and press 'Enter'";
	boolean frontDoor = false;
	private String frontDoorLocked = "The door is locked.";
	int numRecX = 204;
	int numRecY = 140;
	int posX = 0;
	int posY = 0;
	Image theKey;
	
	
	//constructor for the play state
	public Outside (int state){
		
	}
	
	//initialize stuff the class needs
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		outside = new Image("res/State 1 - Porch.png");
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
		theKey = new Image("res/keyspritesheet.png");
		font = new TrueTypeFont(new java.awt.Font(java.awt.Font.SERIF,java.awt.Font.BOLD , 26), false);
		txt = new TextField(gc, font, 150, 160, 60, 40);
		
		
		
	}
	
	//rendering the game
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		posX = Mouse.getX();
		posY = Mouse.getY();
		
		
		outside.draw(hoboPosX, hoboPosY);
		hobo.draw(shiftX, shiftY);
		g.setColor(new Color(255,255,255,0));
		g.draw(hoboRect);
		g.setColor(new Color(255,0,0));
		g.drawString("Hobo's X: "+ hoboPosX+ "\nHobo's PositionY: "+hoboPosY, 20, 40);
		g.setColor(new Color(255,0,0));
		g.drawString("mouse x: "+posX+"\nmouse y: "+ posY, 20, 160);
		
		
		
		
		
		if(frontDoor == true){
		g.drawString(frontDoorLocked, 250, 100);
		}
		
		if(lock == true){
		g.drawString(lockBox, 100, 80);
		txt.render(gc, g);
		}
		
		if(wrongCode == true){
			g.drawString(wrongCodeMsg, 100, 120);
		}
		
		if(key == true){
			theKey.draw(600, 300);
			
		}
		
		
		//draw book title to screen if true
		if (book == true){
		g.drawString(bookTitle, 220, 160);
		if(book == false){
			g.clear();
		}
			
		
		
			
		
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
		
		//outer map boundries

		
		
		//hobo walking ------------------------------------------------------------------------------------------------
		if(input.isKeyDown(Input.KEY_UP)){
			hobo = hoboAnimationUp; //make Hobo look up
			hoboAnimationUp.start();
			hoboPosY += delta * .1f; //increase the Y position of Hobo
			if (hoboPosY > topBound){
				hoboPosY -= delta * .1f;
			}
			if (hoboPosY > -3.2 && hoboPosX > 289 && hoboPosY < 70){
				hoboPosY -= delta * .1f;
			}
			if(hoboPosY > -3.2 && hoboPosX < 87 && hoboPosY < 70){
				hoboPosY -= delta * .1f;
			}
			}else{
				hoboAnimationUp.stop();
				}

			
		if(input.isKeyDown(Input.KEY_DOWN)){
			hobo = hoboAnimationDown; //make Hobo look up
			hoboAnimationDown.start();
			hoboPosY -= delta * .1f; //increase the Y position of Hobo
			if (hoboPosY < botBound){ 
				hoboPosY += delta * .1f; //dont allow any more values to be added to the Y position collision
			}
			if (hoboPosY < 70 && hoboPosX > 283){
				hoboPosY += delta * .1f;
			}
			if (hoboPosY < 70 && hoboPosX < 87){
				hoboPosY += delta * .1f;
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
		if(input.isKeyDown(Input.KEY_SPACE)){
			if(hoboPosX < -241 && hoboPosY > 129){
				book = true;
			}
		}else{
			book = false;
		}
		if(input.isKeyDown(Input.KEY_SPACE)){
			if(hoboPosX < 264 && hoboPosX > 131.9 && hoboPosY > 158){
				if(key == false){
					frontDoor = true;
				}else if(key == true && frontDoorOpen == true){
					sbg.enterState(2);
				}	
			}
			}else{
				frontDoor = false;
			}
				
			if(input.isKeyDown(Input.KEY_SPACE)){
				if(hoboPosX < 108 && hoboPosX > 95.9 && hoboPosY > 158){
					if(key == false){
						lock = true;
						
					
					}
				}	else{
					lock = false;
					wrongCode = false;
				}
				}
			if(input.isKeyDown(Input.KEY_ENTER)){
				nums = txt.getText();
				if(nums.equals(checkNums)){
					key = true;
					frontDoorOpen = true;
					wrongCode = false;
				}else{wrongCode = true;
				}
			
			}
			
			
		
			//------------------------------------------------------------------------------------------------------------
		
		
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
	

					

			

	
	//grabs the ID for our play state, which is 1
	public int getID(){
		return 1;
	}
}

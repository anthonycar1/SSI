package javagame;


import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.*;

public class MV extends BasicGameState{
	
	Image MV;
	float hoboPosX = 320;
	float hoboPosY = 60;
	float shiftX = 320; // keeps hobo in the middle of the screen
	float shiftY = 160; // keeps hobo in the middle of the screen
	float rShiftX= shiftX + 10;
	float topBound = 150;
	float botBound = -263;
	float leftBound = 332;
	float rightBound = -108;
	SpriteSheet hoboUp, hoboDown, hoboLeft, hoboRight;
	Animation hoboAnimationUp, hoboAnimationDown, hoboAnimationLeft, hoboAnimationRight, hobo;
	SpriteSheet mV;
	Animation mVAnimation;
	private Rectangle hoboRect = null;
	private boolean esc = false;
	private boolean mVMsgCheck = false;
	String mVMsg = "Vitucci: 'Yo!'";

	int posX = 0; //mouse coords
	int posY = 0;
	
	//constructor for the menu
	public MV(int state){
		
	}
	
	//initialize stuff the class needs
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		MV = new Image("res/State 4 - MV.png");
		hoboUp = new SpriteSheet("res/maincharacterwalkingup.png", 64, 64);
		hoboDown = new SpriteSheet("res/maincharacterwalkingdown.png", 64, 64);
		hoboLeft = new SpriteSheet("res/maincharacterwalkingleft.png", 64, 64);
		hoboRight = new SpriteSheet("res/maincharacterwalkingright.png", 64, 64);
		hoboAnimationUp = new Animation(hoboUp, 250);
		hoboAnimationDown = new Animation(hoboDown, 250);
		hoboAnimationLeft = new Animation(hoboLeft, 250);
		hoboAnimationRight = new Animation(hoboRight, 250);
		hobo = hoboAnimationRight;
		hoboRect = new Rectangle(rShiftX, shiftY, 44, 64);
		mV = new SpriteSheet("res/MVspritesheet.png",64,64);
		mVAnimation = new Animation(mV, 250);
		
	}
	
	//rendering the game "drawing"
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		posX = Mouse.getX();
		posY = Mouse.getY();
		
		MV.draw(hoboPosX, hoboPosY);
		mVAnimation.draw(hoboPosX + 340,hoboPosY + 200);
		hobo.draw(shiftX, shiftY);
		g.setColor(new Color(255,255,255,0));
		g.draw(hoboRect);
		g.setColor(new Color(255,0,0));
		g.drawString("Hobo's X: "+ hoboPosX+ "\nHobo's PositionY: "+hoboPosY, 20, 40);
		g.setColor(new Color(255,0,0));
		g.drawString("mouse x: "+posX+"\nmouse y: "+ posY, 20, 150);
		if(mVMsgCheck == true){
		g.drawString(mVMsg, 100, 100);
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
					}if (hoboPosY < -109  && hoboPosY > -110 && hoboPosX < 16 && hoboPosX > -62){
						hoboPosY -= delta * .1f; ///mike collision
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
					if (hoboPosY < 25 && hoboPosY > 22 && hoboPosX < 16 && hoboPosX > -62){ 
						hoboPosY += delta * .1f; //mike collision
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
						}if (hoboPosX < -62 && hoboPosX > -63 && hoboPosY > -109 && hoboPosY <25){ 
							hoboPosX -= delta * .1f; //mike collision
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
					}if (hoboPosX < 16 && hoboPosX > 15 && hoboPosY > -109 && hoboPosY <25){ 
						hoboPosX += delta * .1f; //mike collision
					}
					}
					else{
						hoboAnimationRight.stop();
					}
				
				//-------------------------------------------------------------------------------
				
				if(input.isKeyDown(Input.KEY_SPACE)){
					if ((hoboPosX < 16 && hoboPosY > -109 && hoboPosY <25) || (hoboPosX > -62 && hoboPosY > -109 && hoboPosY <25) || (hoboPosY < 25 && hoboPosX < 16 && hoboPosX > -62) ||(hoboPosY > -109 && hoboPosX < 16 && hoboPosX > -62) ){
						mVMsgCheck = true;
					}
					}else{
						mVMsgCheck = false;
					
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
	
	//grabs the ID for our MV state, which is 4
	public int getID(){
		return 4;
	}
}

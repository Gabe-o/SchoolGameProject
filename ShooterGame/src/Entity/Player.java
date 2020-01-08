package Entity;

import TileMap.*;

import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;



public class Player extends MapObject{
	
	//Player
	private int health;
	public boolean dead;
	
	
	//Fireball
	private boolean firing;
	private int fireCost;
	private int fireBallDamage;
	//private ArrayList<FireBall> fireBalls;

	
	//animations
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {2,8,1,2,4,2,5};
	
	//animation actions
	private static final int IDLE = 0;
	private static final int WALKING = 1;

	
	public Player(TileMap tm) {
		super(tm);
		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 20;
		
		moveSpeed = 0.3;
		maxSpeed = 1.6;
		stopSpeed = 0.4;
		
		facingRight = true;
		
		health = 100;

		
		try {
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Images/playersprites.gif"));
			sprites = new ArrayList<BufferedImage[]>();
			for(int i = 0; i < 7;i++) {
				BufferedImage[] bi = new BufferedImage[numFrames[i]];
				for(int j = 0; j < numFrames [i];j++) {
					if (i!= 6) bi[j] = spritesheet.getSubimage(j*width, i * height,width,height);
					else bi[j] = spritesheet.getSubimage(j*width*2, i * height,width,height);
				}
				sprites.add(bi);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(sprites.get(IDLE));
	}
	public int getHealth() {return health;}

	
	public void setFiring() {
		firing = true;
	}


	private  void getNextPosition() {
		if(left) {
			dx-=moveSpeed;
			if (dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		}
		else if(right) {
			dx+=moveSpeed;
			if (dx > maxSpeed) {
				dx = maxSpeed;
			}
		}
		else {
			if (dx > 0) {
				dx-=stopSpeed;
				if (dx < 0) dx = 0;
			}
			else if (dx < 0) {
				dx+=stopSpeed;
				if (dx > 0) dx = 0;
			} 
		}
		
		if(up) {
			dy-=moveSpeed;
			if (dy < -maxSpeed) {
				dy = -maxSpeed;
			}
		}
		else if(down) {
			dy+=moveSpeed;
			if (dy > maxSpeed) {
				dy = maxSpeed;
			}
		}
		else {
			if (dy > 0) {
				dy-=stopSpeed;
				if (dy < 0) dy = 0;
			}
			else if (dy < 0) {
				dy+=stopSpeed;
				if (dy > 0) dy = 0;
			} 
		}
	}
	
	public void update() {
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		if(left || right) {
			if(currentAction != WALKING) {
				currentAction = WALKING;
				animation.setFrames(sprites.get(WALKING));
				animation.setDelay(40);
				width = 30;
			}
		}
		else {
			if(currentAction != IDLE) {
				currentAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelay(400);
				width = 30;
			}
		}
		animation.update();
	}
	public void draw(Graphics2D g) {
		setMapPosition();
		
		if(facingRight) {
			g.drawImage(animation.getImage(), (int)(x+xmap-width/2), (int)(y+ymap-height/2), null);
		}
		else {
			g.drawImage(animation.getImage(), (int)(x+xmap-width/2+width), (int)(y+ymap-height/2), -width, height, null);
		}
	}
}

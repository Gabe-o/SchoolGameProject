package Entity;

import TileMap.*;

import java.awt.*;

public class Player extends MapObject{
	
	//Player stats
	private int health;
	public boolean dead;
	
	//Firing
	//private boolean firing;

	public Player(TileMap tm) {
		super(tm);
		//player size
		width = 8;
		height = 8;
		
		//player collision size
		cwidth = 8;
		cheight = 8;
		
		//speed
		moveSpeed = 4;//acceleration
		maxSpeed = 4;
		stopSpeed = 4;//deceleration
		
		health = 100;
	}
	
	public int getHealth() {return health;}

	
	/*public void setFiring() {
		firing = true;
	}*/

	private void getNextPosition() {
		if(left) {
			dx-=moveSpeed;
			if (dx < -maxSpeed) {//accelerate to max
				dx = -maxSpeed;
			}
		}
		else if(right) {
			dx+=moveSpeed;
			if (dx > maxSpeed) {//accelerate to max
				dx = maxSpeed;
			}
		}
		else {
			if (dx > 0) {//decelerates to stop
				dx-=stopSpeed;
				if (dx < 0) dx = 0;
			}
			else if (dx < 0) {//decelerates to stop
				dx+=stopSpeed;
				if (dx > 0) dx = 0;
			} 
		}
		
		if(up) {
			dy-=moveSpeed;
			if (dy < -maxSpeed) {//accelerate to max
				dy = -maxSpeed;
			}
		}
		else if(down) {
			dy+=moveSpeed;
			if (dy > maxSpeed) {//accelerate to max
				dy = maxSpeed;
			}
		}
		else {
			if (dy > 0) {//decelerates to stop
				dy-=stopSpeed;
				if (dy < 0) dy = 0;
			}
			else if (dy < 0) {//decelerates to stop
				dy+=stopSpeed;
				if (dy > 0) dy = 0;
			} 
		}
	}
	
	public void update() {
		getNextPosition();
		checkCollision();
		setPosition(x + dx, y + dy);
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.MAGENTA);
		g.fillRect((int)(x-width/2), (int)(y-height/2), width, height);//Draws player
		g.setColor(Color.BLUE);
		g.drawRect((int)(x-cwidth/2), (int)(y-cheight/2), width, height);//Draws player
	}
}

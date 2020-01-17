package Entity;

import TileMap.*;

import java.awt.*;

public class Player extends MapObject{
	
	//Player stats
	private static int health;
	private static String score;
	public boolean dead;
	
	//Firing
	public static boolean firing;

	public Player(TileMap tm) {
		super(tm);
		//player size
		width = 10;
		height = 10;
		
		//player collision size
		cwidth = 11;
		cheight = 11;
		
		//speed
		moveSpeed = 3;//acceleration
		maxSpeed = 3;
		stopSpeed = 3;//deceleration
		
		health = 100;
		score = "0";
	}
	public static int getHealth() {return health;}

	
	public void setFiring() {
		firing = true;
	}
	public void stopFiring() {
		firing = false;
	}

	private  void getNextPosition() {
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
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
	}
	
	public void draw(Graphics2D g) {
		setMapPosition();
		g.setColor(Color.MAGENTA);
		g.fillRect((int)(x+xmap-width/2), (int)(y+ymap-height/2), width, height);//Draws player
		g.setColor(Color.BLUE);
		g.drawRect((int)(x+xmap-cwidth/2), (int)(y+ymap-cheight/2), width, height);//Draws player
	}
	public static void displayHealthBar(Graphics2D g) {
     g.setColor(Color.RED);
	 g.fillRect(495, 15, 100, 20);
	 g.setColor(Color.GREEN);
	 g.fillRect(495, 15, health, 20);	
	}
	public static void updateScore(Graphics2D g){
		
		Font font1 = new Font("bold", Font.BOLD, 30);
		int realScore = Integer.parseInt(score);
		realScore++;
		health--;
		score = String.valueOf(realScore);
		g.setColor(Color.WHITE);
		g.setFont(font1);
		g.drawString(score, 530, 65);
	}
}

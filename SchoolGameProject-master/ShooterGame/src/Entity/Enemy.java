package Entity;

import java.awt.Color;

import java.awt.Graphics;

import TileMap.TileMap;



public class Enemy extends MapObject {

	private double x,y, speed;
	
	
	
	Player player;

	
	
	public Enemy (double x, double y, Player player, TileMap tileMap) {
		super(tileMap);
		this.x=x;
		this.y=y;
		this.player=player;
		speed = 3;
	}
	
	public void tick() {
		
			
		if (Math.abs(disToPlayerX()) > Math.abs(disToPlayerY())) {
			if (disToPlayerX()<0) {
				 x -= speed;
				
			}
			else {
				x += speed;
			
			}
		}
		else {
			if (disToPlayerY()<0) {
				y-=speed;
				
			}
			else {
				y+=speed;
	
			}
		}
	}
	
	public void checkCollision() {
		int xdest, ydest;
		xdest = (int) (x + speed);
		ydest = (int) (y + speed);
		
		
		
	}
	
	public int disToPlayerX() {
		return player.getx()-(int)x;
	}
	
	public int disToPlayerY() {
		return player.gety()-(int)y;
	}
	
	
	
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, 10, 10);//Draws player
	}
	
}

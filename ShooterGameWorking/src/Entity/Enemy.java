package Entity;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import TileMap.TileMap;



public class Enemy extends MapObject {

	private double x,y, speed;
	private Controller c;
	private long lastShotTime;
	private Rectangle enemyRectangle;
	
	Player player;

	
	
	public Enemy (double x, double y, Player player, TileMap tileMap, Controller c) {
		super(tileMap);
		this.c = c;
		this.x=x;
		this.y=y;
		this.player=player;
		speed = 4;
		enemyRectangle = new Rectangle ((int) x, (int) y, 10, 10);
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
		
		
	
	for (int i = 0;i<c.getBullets().size();i++) {
		if (enemyRectangle.getBounds().intersects(c.getBullets().get(i).getRect().getBounds())) {
			System.out.println(c.getBullets().size());
			killEnemy();
		}
	}
		
		
		
	}
	
	
		
		
		
	
	
	public int disToPlayerX() {
		return player.getx()-(int)x;
	}
	
	public int disToPlayerY() {
		return player.gety()-(int)y;
	}
	
	
	public void killEnemy() {
		c.removeEnemy(this);
	}
	
	
	
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, 10, 10);//Draws player
	}
	
}

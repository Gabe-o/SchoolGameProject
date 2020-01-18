package Entity;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Rectangle;

import java.util.Random;

import GameState.Level1State;
import TileMap.TileMap;



public class Enemy extends MapObject {

	private double x,y;
	private Controller c;
	public Rectangle enemyRectangle;
	Random r = new Random();
	private int speed = r.nextInt((4 -3) +1 )+ 3; //speed range between 3 and 6
	Level1State game;
	Player player;

	
	
	public Enemy (double x, double y, Player player, TileMap tileMap, Controller c, Level1State game) {
		super(tileMap);
		this.c = c;
		this.x=x;
		this.y=y;
		this.player=player;
		this.game = game;
	
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
		enemyRectangle.setLocation((int)x,(int) y);
	
		
		for (int i=0;i<c.getEnemys().size();i++) {
			if (enemyRectangle.getBounds().intersects(player.getRectangle().getBounds())) {
					killEnemy();
					Player.health -=10;
					break;
				
				}
			}
	
	for (int i = 0;i<c.getBullets().size();i++) {
		if (enemyRectangle.getBounds().intersects(c.getBullets().get(i).getRect().getBounds())) {
	
			killEnemy();
			c.removeBullet(c.getBullets().get(i));
			Player.score += 50; 
			game.setEnemyKilled(game.getEnemyKilled() +1);
			
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
	public void removeBullet() {
		c.removeBullet(null);
	}
	
	
	
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, 10, 10);//Draws player
	}
	
}

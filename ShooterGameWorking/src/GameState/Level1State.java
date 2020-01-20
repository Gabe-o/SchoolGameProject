package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.Random;

import Entity.Bullet;
import Entity.Controller;
import Entity.Enemy;
import Entity.Player;

import Sounds.SoundClipTest;
import TileMap.*;

public class Level1State extends GameState {

	private TileMap tileMap;
	private Background bg;//background
	private static Controller c;
	private Player player;
	public static int direction;
	private long lastShotTime;
	Random r = new Random();
	Enemy e ;
	
	private int enemyCount = 1;
	private int enemyKilled = 0;
	public static int wave = 0;
	
	public Level1State(GameStateManager gsm) {//constructor for menu takes in the current game state
		
		this.gsm = gsm;

		init();
	}

	SoundClipTest test = new SoundClipTest();
	public void init() {
		tileMap = new TileMap("/Level1Map.png");
		tileMap.LoadLevel();
		bg = new Background("/ArenaFloor.jpg");
		
		c = new Controller();
		
		player = new Player(tileMap, this);
		player.setPosition(100, 100);
		createEnemy(enemyCount);
	
	}
	public static void UI(Graphics2D g){
		Font fnt0 = new Font("arial", Font.BOLD, 12);
		g.setColor(Color.BLUE);
		g.fillRect(10, 15, 100, 20);
		g.setColor(Color.WHITE);
		g.setFont(fnt0);
		g.drawString("Enemies Left: " + Integer.toString(c.getEnemys().size()), 15, 29);
		 
		g.setColor(Color.PINK);
		g.fillRect(10, 45, 100, 20);
		g.setColor(Color.BLUE);
		g.setFont(fnt0);
		g.drawString("WAVE: " + Integer.toString(wave), 15, 60);
		
		
	
	}
	public void update() {
		player.update();
		c.tick();
		
		if (c.getEnemys().size()==0) {
			wave++;
			createEnemy(wave);
		}
		if (Player.getHealth() <= 0) {	
			gsm.setState(GameStateManager.GAMEOVERSTATE);
		}
	}
		


	public void createEnemy(int enemyCount) {
		for (int i =0; i<enemyCount; i++) {
			c.addEnemy(new Enemy(r.nextInt(640), r.nextInt(480), player, tileMap, c, this));
		}
	}
	
	public void draw(java.awt.Graphics2D g) {
		
		//draw bg
		bg.draw(g);
		
		//draw tilemap
		tileMap.draw(g);
				
		//draw player
		player.draw(g);
		
		c.render(g);
	}

	public void keyPressed(int k) {
		if(k == KeyEvent.VK_A) player.setLeft(true);
		if(k == KeyEvent.VK_D) player.setRight(true);
		if(k == KeyEvent.VK_W) player.setUp(true);
		if(k == KeyEvent.VK_S) player.setDown(true);

		
		if(k == KeyEvent.VK_LEFT && (System.currentTimeMillis() - lastShotTime >= 100)) {//100 is rate of fire
			direction = 1;
			c.addBullet(new Bullet(player.getx(), player.gety(), direction));
			player.setFiring();
			lastShotTime = System.currentTimeMillis();
		}
		if(k == KeyEvent.VK_RIGHT && (System.currentTimeMillis() - lastShotTime >= 100)) {
			direction = 2;
			c.addBullet(new Bullet(player.getx(), player.gety(), direction));
			player.setFiring();
			lastShotTime = System.currentTimeMillis();
		}
		if(k == KeyEvent.VK_UP && (System.currentTimeMillis() - lastShotTime >= 100)) {
			direction = 3;
			c.addBullet(new Bullet(player.getx(), player.gety(), direction));
			player.setFiring();
			lastShotTime = System.currentTimeMillis();
		}
		if(k == KeyEvent.VK_DOWN && (System.currentTimeMillis() - lastShotTime >= 100)) {
			direction = 4;
			c.addBullet(new Bullet(player.getx(), player.gety(), direction));
			player.setFiring();
			lastShotTime = System.currentTimeMillis();
		}
		if (k == KeyEvent.VK_UP ||  k == KeyEvent.VK_DOWN || k == KeyEvent.VK_LEFT || k == KeyEvent.VK_RIGHT) test.playFireSound();
	}
	
	
	//Getter and setters
	public int getEnemyCount() {
		return enemyCount;
	}

	public void setEnemyCount(int enemyCount) {
		this.enemyCount = enemyCount;
	}

	public int getEnemyKilled() {
		return enemyKilled;
	}

	public void setEnemyKilled(int enemyKilled) {
		this.enemyKilled = enemyKilled;
	}

	public void keyReleased(int k) {
		if(k == KeyEvent.VK_A) player.setLeft(false);
		if(k == KeyEvent.VK_D) player.setRight(false);
		if(k == KeyEvent.VK_W) player.setUp(false);
		if(k == KeyEvent.VK_S) player.setDown(false);
		if(k == KeyEvent.VK_UP) player.stopFiring();
		if(k == KeyEvent.VK_DOWN) player.stopFiring();
		if(k == KeyEvent.VK_LEFT) player.stopFiring();
		if(k == KeyEvent.VK_RIGHT) player.stopFiring();
	}


}

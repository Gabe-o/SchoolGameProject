package GameState;

import java.awt.event.KeyEvent;

import Entity.Bullet;
import Entity.Controller;
import Entity.Enemy;
import Entity.Player;
import Main.GamePanel;
import Sounds.SoundClipTest;
import TileMap.*;

public class Level1State extends GameState {

	private TileMap tileMap;
	private Background bg;//background
	private Controller c;
	private Player player;
	public static int direction;
	private long lastShotTime;
	
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
		
		player = new Player(tileMap);
		player.setPosition(100, 100);
		c.addEnemy(new Enemy (20, 20, player, tileMap, c));
		c.addEnemy(new Enemy (600, 100, player, tileMap, c));
		c.addEnemy(new Enemy (100, 300, player, tileMap, c));
	
	}

	public void update() {
		player.update();
		c.tick();
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

package GameState;

import java.awt.event.KeyEvent;

import Entity.Bullet;
import Entity.Controller;
import Entity.MapObject;
import Entity.Player;
import Main.GamePanel;
import TileMap.*;

public class Level1State extends GameState {

	private TileMap tileMap;
	private Background bg;//background
	private Controller c;
	private Player player;
	public static int direction;

	public Level1State(GameStateManager gsm) {//constructor for menu takes in the current game state

		this.gsm = gsm;
		init();
	} 

	public void init() {
		tileMap = new TileMap(30);
		tileMap.loadTiles("/grasstileset.gif");
		tileMap.loadMap("/level1-1.map");
		tileMap.setPosition(0,  0);
		
		bg = new Background("/ArenaFloor.jpg");
		
		player = new Player(tileMap);
		c = new Controller(this);
		player.setPosition(100, 100);
	}

	public void update() {
		player.update();
		c.tick();
		tileMap.setPosition(GamePanel.WIDTH / 2 - player.getx(), GamePanel.HEIGHT / 2 - player.gety());
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
		if(k == KeyEvent.VK_A) {
			player.setLeft(true);
			
		}
		if(k == KeyEvent.VK_D) {
			player.setRight(true);
			
		}
		if(k == KeyEvent.VK_W) {
			player.setUp(true); 
		
		}
		if(k == KeyEvent.VK_S) {
			player.setDown(true);
			
		}

		if(k == KeyEvent.VK_LEFT) {
			direction = 1;
			c.addBullet(new Bullet(player.getx(), player.gety(), direction));
		}
		if(k == KeyEvent.VK_RIGHT) {
			direction = 2;
			c.addBullet(new Bullet(player.getx(), player.gety(), direction));
		}
		if(k == KeyEvent.VK_UP) {
			direction = 3;
			c.addBullet(new Bullet(player.getx(), player.gety(), direction));
		}
		if(k == KeyEvent.VK_DOWN) {
			direction = 4;
			c.addBullet(new Bullet(player.getx(), player.gety(), direction));
		}
	}

	public void keyReleased(int k) {
		if(k == KeyEvent.VK_A) player.setLeft(false);
		if(k == KeyEvent.VK_D) player.setRight(false);
		if(k == KeyEvent.VK_W) player.setUp(false);
		if(k == KeyEvent.VK_S) player.setDown(false);

	}
}

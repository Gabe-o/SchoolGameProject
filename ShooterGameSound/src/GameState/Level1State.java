package GameState;
import Sounds.*;
import java.awt.event.KeyEvent;

import Entity.Player;
import Main.GamePanel;
import TileMap.*;

	
	
public class Level1State extends GameState {

	private TileMap tileMap;
	private Background bg;//background
	
	private Player player;

	public Level1State(GameStateManager gsm) {//constructor for menu takes in the current game state

		this.gsm = gsm;
		init();
	}
	SoundClipTest test = new SoundClipTest();
	public void init() {
		tileMap = new TileMap(30);
		tileMap.loadTiles("/grasstileset.gif");
		tileMap.loadMap("/level1-1.map");
		tileMap.setPosition(0,  0);
		
		bg = new Background("/ArenaFloor.jpg");
		
		player = new Player(tileMap);
		player.setPosition(100, 100);
	}

	public void update() {
		player.update();
		tileMap.setPosition(GamePanel.WIDTH / 2 - player.getx(), GamePanel.HEIGHT / 2 - player.gety());
	}

	public void draw(java.awt.Graphics2D g) {
		//draw bg
		bg.draw(g);
		
		//draw tilemap
		tileMap.draw(g);
		
		//draw player
		player.draw(g);
	}

	public void keyPressed(int k) {
		if(k == KeyEvent.VK_A) player.setLeft(true);
		if(k == KeyEvent.VK_D) player.setRight(true);
		if(k == KeyEvent.VK_W) player.setUp(true);
		if(k == KeyEvent.VK_S) player.setDown(true);
		if(k == KeyEvent.VK_UP) player.setFiring();
		if(k == KeyEvent.VK_DOWN) player.setFiring();
		if(k == KeyEvent.VK_LEFT) player.setFiring();
		if(k == KeyEvent.VK_RIGHT) player.setFiring();
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

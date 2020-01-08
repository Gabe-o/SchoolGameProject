package GameState;

import java.awt.Color;
import java.awt.Font;
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

	public void init() {
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Images/grasstileset.gif");
		tileMap.loadMap("/Images/level1-1.map");
		tileMap.setPosition(0,  0);
		
		bg = new Background("/Images/Level1Floor.jpg");
		
		player = new Player(tileMap);
		player.setPosition(100, 100);
	}

	public void update() {
		bg.update();
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
		if(k == KeyEvent.VK_LEFT) player.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) player.setRight(true);
		if(k == KeyEvent.VK_UP) player.setUp(true);
		if(k == KeyEvent.VK_DOWN) player.setDown(true);
	}

	public void keyReleased(int k) {
		if(k == KeyEvent.VK_LEFT) player.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) player.setRight(false);
		if(k == KeyEvent.VK_UP) player.setUp(false);
		if(k == KeyEvent.VK_DOWN) player.setDown(false);
	}
}
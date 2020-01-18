package GameState;

import TileMap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

import Main.GamePanel;
import Sounds.SoundClipTest;

public class MenuState extends GameState{
	
	private Background bg;//Background
	
	private int currentChoice = 0;//current option selected
	private String[] options = {"Start", "Shop", "Quit"};//menu options
	
	private Color titleColor;//title color
	private Font titleFont;//title font
	
	private Font font;//text font
	
	public MenuState(GameStateManager gsm) {//constructor for menu takes in the current game state
		
		this.gsm = gsm;
		
		try {
			
			bg = new Background("/ArenaFloor.jpg");
			
			titleColor = new Color(255, 0, 0);
			titleFont = new Font ("Century Gothic", Font.PLAIN, 56);
			
			font = new Font ("Arial", Font.PLAIN, 24);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	SoundClipTest test = new SoundClipTest();
	
	public void init() {}
	public void update() {}
	public void draw(Graphics2D g) {
		
		//draw bg
		bg.draw(g);
		
		//draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Game", GamePanel.WIDTH/2-75, GamePanel.HEIGHT/4);
		
		//draw menu options
		g.setFont(font);
		for (int i = 0; i < options.length;i++) {
			if (i == currentChoice) {
				g.setColor(Color.WHITE);
			}
			else {
				g.setColor(Color.RED);
			}
			g.drawString(options[i], GamePanel.WIDTH/2-28, 160 + i * 30);
		}
	}
	
	private void select() {
		if (currentChoice == 0) {
			gsm.setState(GameStateManager.LEVEL1STATE);
			test.PlayLevel1Music(true);
		}
		if (currentChoice == 1) {
			gsm.setState(GameStateManager.SHOPSTATE);
		}
		if (currentChoice == 2) {
			System.exit(0);
		}
	}
	
	public void keyPressed(int k) {
		if (k == KeyEvent.VK_ENTER) {
			select();
		}
		if (k == KeyEvent.VK_UP) {
			currentChoice--;
			if (currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		if (k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if (currentChoice == options.length) {
				currentChoice = 0;
			}
		}
	}
	public void keyReleased(int k) {}
}

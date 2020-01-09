package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import Main.GamePanel;
import TileMap.Background;

public class ShopState extends GameState{

	private Background bg;

	private int currentChoice = 0;

	private Rectangle sniperBox = new Rectangle(GamePanel.WIDTH /4 , GamePanel.HEIGHT / 4, 150, 50);
	private Rectangle shotgunBox = new Rectangle(GamePanel.WIDTH /4 , GamePanel.HEIGHT / 4 + 50, 150, 50);
	private Rectangle backBox = new Rectangle(GamePanel.WIDTH /4 , GamePanel.HEIGHT / 4 + 125, 150, 50);
	
	private String[] options = {"Sniper", "Shotgun", "Back"}; 
	
	private int []weaponCost = {5,10};
	
public ShopState(GameStateManager gsm) {
		
		this.gsm = gsm;
		
		try {
			bg = new Background("/shopbg.jpg");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void select() {
		if (currentChoice == 0) {
			gsm.setState(GameStateManager.LEVEL1STATE);
		}
		if (currentChoice == 1) {
			gsm.setState(GameStateManager.SHOPSTATE);
		}
		if (currentChoice == 2) {
			gsm.setState(GameStateManager.MENUSTATE);
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
	

	public void init() {}
	
	public void update() {
	}

	public void draw(Graphics2D g) {
		Graphics2D g2d = (Graphics2D) g;
		//draw bg
		bg.draw(g);

		Font fnt0 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt0);
		g.setColor(Color.YELLOW); //sets Color to yellow
		g.drawString("SHOP MENU", GamePanel.WIDTH / 4 - 20, 50); //prints title of the shop
		
	g2d.draw(sniperBox);
	
	g2d.draw(shotgunBox);
	
	g2d.draw(backBox);

	for (int i = 0; i < options.length;i++) {
		Font fnt1 = new Font("ZapfDingbats", Font.BOLD, 15); //creates font zapfdingbats
		g.setFont(fnt1); //sets actual font 
		if (i == currentChoice) {
			g.setColor(Color.YELLOW);
		}
		else {
			g.setColor(Color.RED);
		}
		if (options[i] == "Back") {
			Font fnt2 = new Font("ZapfDingbats", Font.BOLD, 25); 
			g.setFont(fnt2); //sets actual font 
			g.drawString(options[i], GamePanel.WIDTH / 4 + 43, GamePanel.HEIGHT/4 + 55 + i * 50);
		}
		else {
		g.drawString(options[i], GamePanel.WIDTH / 4 + 10, GamePanel.HEIGHT/4 + 20 + i * 50);
		Font fnt3 = new Font("ZapfDingbats", Font.BOLD, 10); //creates font zapfdingbats
		g.setFont(fnt3);
		g.drawString("Cost: " + Integer.toString(weaponCost[i]), GamePanel.WIDTH / 4 + 10, GamePanel.HEIGHT/4 + 40 + i * 50);
		}
	}
	}
	public void keyReleased(int k) {}

}

package Entity;

import java.awt.Graphics;
import java.util.LinkedList;
import Entity.Player;
import GameState.Level1State;



public class Controller {

	private LinkedList<Bullet> b = new LinkedList<Bullet>();
	
	Bullet TempBullet;
	
	Level1State game;
	
	public Controller(Level1State game) {
		this.game=game;
		
		addBullet(new Bullet(10, 10, Level1State.direction));
	}

	public void tick() {
		for (int i=0; i<b.size();i++) {
			TempBullet = b.get(i);
			
			TempBullet.tick();
		}
	}
	
	public void render(Graphics g) {
		for (int i=0; i<b.size();i++) {
			TempBullet = b.get(i);
			
			TempBullet.render(g);
		}
	}
	
	public void addBullet(Bullet block) {
		b.add(block);
	}
	public void removeBullet(Bullet block) {
		b.remove(block);
	}
	
}

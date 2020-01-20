package Entity;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import TileMap.TileMap;




public class Controller {

	private LinkedList<Bullet> b = new LinkedList<Bullet>();
	private LinkedList<Enemy> e = new LinkedList<Enemy>();
	
	Bullet TempBullet;
	Enemy TempEnemy;
	Random r = new Random();

	
	public Controller() {
		
	
	}
	
	

	public void tick() {
		for (int i=0; i<b.size();i++) {
			TempBullet = b.get(i);
			
			TempBullet.tick();
		}
		for (int i=0; i<e.size();i++) {
			TempEnemy = e.get(i);
			
			TempEnemy.tick();
		}
	}
	
	public void render(Graphics g) {
		for (int i=0; i<b.size();i++) {
			TempBullet = b.get(i);
			
			TempBullet.render(g);
		}
		for (int i=0; i<e.size();i++) {
			TempEnemy = e.get(i);
			
			TempEnemy.render(g);
		}
	}
	
	
	public LinkedList<Bullet> getBullets(){
		return b;
	}
	public LinkedList<Enemy> getEnemys(){
		return e;
	}
	
	public void addBullet(Bullet block) {
		b.add(block);
	}
	public void removeBullet(Bullet block) {
		b.remove(block);
	}
	public void addEnemy(Enemy block) {
		e.add(block);
	}
	public void removeEnemy(Enemy block) {
		e.remove(block);
	}
	
}

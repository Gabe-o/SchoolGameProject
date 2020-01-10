package Entity;

import java.awt.Rectangle;

import Main.GamePanel;
import TileMap.Tile;

import TileMap.TileMap;

public abstract class MapObject {
	
	//Tile stuff
	protected TileMap tileMap;
	protected int tileSize;
	protected double xmap;
	protected double ymap;
	
	//position and vector
	protected double x;
	protected double y;
	protected double dx;
	protected double dy;
	
	//dimensions
	protected int width;
	protected int height;
	
	//collision box
	protected int cwidth;
	protected int cheight;
	
	//collision 
	protected int currCol;
	protected int currRow;
	protected double xdest;
	protected double ydest;
	protected double xtemp;
	protected double ytemp;
	protected boolean topLeft;
	protected boolean topRight;
	protected boolean bottomLeft;
	protected boolean bottomRight;
	
	protected boolean left;
	protected boolean right;
	protected boolean up;
	protected boolean down;
	
	//movement attributes
	protected double moveSpeed;
	protected double maxSpeed;
	protected double stopSpeed;
	
	//constructor
	public MapObject () {
		//tileMap = tm;
	}
	
	public Rectangle getRectangle() {
		return new Rectangle((int)x-cwidth,(int)y-cheight,cwidth,cheight);
	}
	
	public void calculateCorners(double x, double y) {
	
	}
	
	public void checkTileMapCollision() {
		currCol = (int)x / tileSize;
		currRow = (int)y / tileSize;
		xdest = x + dx;
		ydest = y + dy;
		xtemp = x;
		ytemp = y;
		
		calculateCorners(x, ydest);
		if(dy < 0) {
			if(topLeft||topRight) {
				dy = 0;
				ytemp = currRow * tileSize + cheight / 2;
			}
			else {
				ytemp += dy;
			}
		}
		if(dy > 0) {
			if(bottomLeft||bottomRight) {
				dy = 0;
				ytemp = (currRow + 1) * tileSize - cheight / 2;
			}
			else {
				ytemp += dy;
			}
		}
		calculateCorners(xdest, y);
		if(dx < 0) {
			if(topLeft||bottomLeft) {
				dx = 0;
				xtemp = currCol * tileSize + cwidth / 2;
			}
			else {
				xtemp += dx;
			}
		}
		if(dx > 0) {
			if(topRight||bottomRight) {
				dx = 0;
				xtemp = (currCol + 1) * tileSize - cwidth / 2;
			}
			else {
				xtemp += dx;
			}
		}
	}
	public int getx() {return (int)x;}
	public int gety() {return (int)y;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	public int getCWidth() {return cwidth;}
	public int getcHeight() {return cheight;}
	
	public void setPosition (double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void setVector (double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public void setMapPosition () {
		xmap = tileMap.getx();
		ymap = tileMap.gety();
	}
	
	public void setLeft(boolean b) {left = b;}
	public void setRight(boolean b) {right = b;}
	public void setUp(boolean b) {up = b;}
	public void setDown(boolean b) {down = b;}
	
	public boolean notOnScreen( ) {
		return x + xmap + width < 0 || x + xmap - width > GamePanel.WIDTH || y + ymap + height < 0 || y + ymap - height >GamePanel.HEIGHT; 
	}
	
}

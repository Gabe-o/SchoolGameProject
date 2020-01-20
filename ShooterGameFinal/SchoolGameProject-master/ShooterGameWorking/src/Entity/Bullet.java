package Entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;



import GameState.Level1State;
import Entity.MapObject;


	
	public class Bullet {
		Image newImage;
		BufferedImage image;
	    private int x;
	    private int y;
	    private int direction;
	    private int bulletSpeed;
	    private Rectangle bullet;
	    
	    
	    public Bullet(int x, int y, int direction){
	         this.x =x;
	         this.y=y; 
	         this.direction = direction;
	         this.bulletSpeed = 5;
	         bullet = new Rectangle(x,y,5,5);
	         
	    }
	    
	    public void tick() {
	  
	    	
	    	switch (direction){
            case 1:
                x -= bulletSpeed;
                break;
            case 2:
                x += bulletSpeed;
                break;
            case 3:
                y -= bulletSpeed;
                break;
            case 4:
                y += bulletSpeed;
                break;
        }
	    	bullet.setLocation(x, y);
	    	
	    
	    }
	    
	    public int getX() {
	    	return x;
	    }
	    public int getY() {
	    	return y;
	    }
	    public Rectangle getRect() {
	    	return bullet;
	    }
	    
	    
	    public void render(Graphics g) {
	    	g.setColor(Color.WHITE);
	    	g.fillRect(x, y, 5, 5);
	    	
	 
	    }
	  

	}



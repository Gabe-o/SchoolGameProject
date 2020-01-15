package Entity;

import java.awt.Graphics;
import java.awt.Image;
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
	    
	    
	    public Bullet(int x, int y, int direction){
	         this.x =x;
	         this.y=y; 
	         this.direction = direction;
	         
	         try {
	 			image = ImageIO.read(getClass().getResource("/BulletB.png"));
	 			newImage = image.getScaledInstance(5, 5, Image.SCALE_DEFAULT);
	         }
	         catch (Exception e) {
	        	 e.printStackTrace();
	         }
	    }
	    
	    public void tick() {
	  
	    	switch (direction){
            case 1:
                x -= 4;
                break;
            case 2:
                x += 4;
                break;
            case 3:
                y -= 4;
                break;
            case 4:
                y += 4;
                break;
        }
	    
	    }
	    
	    public void render(Graphics g) {
	    	g.drawImage(newImage, (int) x, (int) y, null);
	    	
	    }
	  

	}



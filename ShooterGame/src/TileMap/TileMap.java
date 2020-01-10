package TileMap;

import java.awt.*;
import java.awt.image.*;

import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class TileMap {
	
	//position
	private double x;
	private double y;
	private int width;
	private int height;
	
	//tileset
	private BufferedImage image;
	
	private ArrayList<Integer> xmap = new ArrayList<Integer>();
	private ArrayList<Integer> ymap = new ArrayList<Integer>();
	
	
	public TileMap(String path) {
		try {
			image = ImageIO.read(getClass().getResource(path));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void LoadLevel(String path) {
		
		try {
			image = ImageIO.read(getClass().getResource(path));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		int w = image.getWidth();
		int h = image.getHeight();
		
		System.out.println(w+", "+h);
		
		for(int xx = 0;xx < w;xx++) {
			for(int yy = 0;yy < h;yy++) {
				System.out.println(xx+", "+yy);
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 0 && green == 0 && blue == 0) {
					xmap.add(xx);
					ymap.add(yy);
				}
			}
		}
	}
	
	public int getx() {
		return (int)x;
	}
	public int gety() {
		return (int)y;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
	public void draw(Graphics2D g) {
		System.out.println("asd");
		for(int x:xmap) {
			for(int y:ymap) {
				System.out.println("asd "+x+", "+y);
				g.setColor(Color.BLUE);
				g.fillRect(x*4, y*4, 4, 4);
			}
		}
		
	}
	
	
	
}

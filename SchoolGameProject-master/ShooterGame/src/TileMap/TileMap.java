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
	private int blocks = 0;
	
	public TileMap(String path) {
		try {
			image = ImageIO.read(getClass().getResource(path));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void LoadLevel() {
		int w = image.getWidth();
		int h = image.getHeight();
		
		for(int xx = 0;xx < w;xx++) {
			for(int yy = 0;yy < h;yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 0 && green == 0 && blue == 0) {
					xmap.add(xx);
					ymap.add(yy);
					blocks++;
				}
			}
		}
	}
	public int getBlocks() {
		return blocks;
	}
	
	public int getXMap(int i) {
		return xmap.get(i)*4;
	}
	
	public int getYMap(int i) {
		return ymap.get(i)*4;
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
		for(int c = 0;c < xmap.size();c++) {
				g.setColor(Color.BLACK);
				g.fillRect(xmap.get(c)*4, ymap.get(c)*4, 4, 4);
		}
		
	}
	
	
	
}

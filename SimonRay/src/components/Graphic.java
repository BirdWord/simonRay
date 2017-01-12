package components;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Graphic implements Visible {
	private BufferedImage image;
	private boolean loadedImages;
	private int x;
	private int y;
	public Graphic(int x, int y, String imageLocation) {
		this.x = x;
		this.y = y;
		loadedImages = false;
		loadImages(imageLocation, 0, 0);
	}
	
	public Graphic(int x, int y, int w, int h, String imageLocation) {
		this.x = x;
		this.y = y;
		loadedImages = false;
		loadImages(imageLocation, w, h);
	}
	
	public Graphic(int x, int y, double scale, String imageLocation) {
		this.x = x;
		this.y = y;
		loadedImages = false;
		loadImages(imageLocation, scale);
	}

	private void loadImages(String imageLocation, double scale) {
		try{
			ImageIcon icon = new ImageIcon(imageLocation);
			int newWidth = (int)(icon.getIconWidth()*scale);
			int newHeight = (int)(icon.getIconHeight()*scale);
			image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = image.createGraphics();
			loadedImages = true;
			g.drawImage(icon.getImage(), 0, 0, newWidth, newHeight, 0, 0, icon.getIconWidth(), icon.getIconHeight(), null);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	private void loadImages(String imageLocation, int w, int h) {
		try{
			ImageIcon icon = new ImageIcon(imageLocation);
			if(w <= 0 && h <= 0){
				image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
				Graphics2D g = image.createGraphics();
				g.drawImage(icon.getImage(), 0, 0, null);
				loadedImages = true;
			}
			else{
				image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
				Graphics2D g = image.createGraphics();
				//cropping: image, x destination, y destination, height, width, x target, y target, width target, height target, null
				g.drawImage(icon.getImage(), 0, 0, w, h, 0, 0, icon.getIconWidth(), icon.getIconHeight(), null);
			}
			loadedImages = true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public BufferedImage getImage() {
		return image;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return image.getWidth();
	}

	@Override
	public int getHeight() {
		return image.getHeight();
	}

	@Override
	public boolean isAnimated() {
		return false;
	}
	public boolean isLoaded(){
		return loadedImages;
	}
	@Override
	public void update() {
	}

}

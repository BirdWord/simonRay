package gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public abstract class Screen {
	private BufferedImage image;
	private ArrayList<Visible> viewObjects;
	public Screen(int width, int height){
		viewObjects = new ArrayList<Visible>();
		initObjects(viewObjects);
		initImage(width, height);
	}
	public void initImage(int width, int height) {
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		update();
	}
	public abstract void initObjects(ArrayList<Visible> viewObjects);
	public void update() {
		Graphics2D g = image.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, image.getWidth(), image.getHeight());
		g.setColor(Color.BLACK);
		for(int i = 0; i<viewObjects.size(); i++){
			Visible v = viewObjects.get(i);
			g.drawImage(v.getImage(), v.getX(), v.getY(), null);
		}
	}
	public BufferedImage getImage(){
		return image;
	}
	public int getWidth(){
		return image.getWidth();
	}
	public int getHeight(){
		return image.getHeight();
	}
	public MouseMotionListener getMouseMotionListener(){
		return null;
	}
	public MouseListener getMouseListener(){
		return null;
	}
	public void addObject(Visible v){
		viewObjects.add(v);
	}
		  
	public void remove(Visible v){
		viewObjects.remove(v);
		/*removing objects shifts indices after it
		*you can either iterate backwards through list OR
		*you can subtract index by 1 after removing
		*
		*note: remove returns object removed 
		*/
	}
	public void moveToBack(Visible v){
		if(viewObjects.contains(v)){
			viewObjects.remove(v);
			viewObjects.add(0,v);
		}
	}
	public void moveToFront(Visible v){
		if(viewObjects.contains(v)){
			viewObjects.remove(v);
			viewObjects.add(v);
		}
	}
}

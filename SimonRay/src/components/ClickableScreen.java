package components;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import gui.Screen;
import gui.components.Clickable;
import gui.components.Visible;

public abstract class ClickableScreen extends Screen implements MouseListener {
	private ArrayList<Clickable> clickables;
	public ClickableScreen(int width, int height) {
		super(width, height);
		clickables = new ArrayList<Clickable>();
	}

	public abstract void initAllObjects(List<Visible> list);
	@Override
	public void mouseClicked(MouseEvent arg0) {
		for(Clickable c: clickables){
			if(c.isHovered(arg0.getX(), arg0.getY())){
				c.act();
				break;
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initObjects(ArrayList<Visible> viewObjects) {
		initAllObjects(viewObjects);
		for(Visible v: viewObjects){
			if(v instanceof Clickable)
				clickables.add((Clickable)v);
		}
	}
	public void addObject(Visible v){
		super.addObject(v);
		if(v instanceof Clickable)
			 clickables.add((Clickable) v);
	}
		 
	public void remove(Visible v){
		super.remove(v);
		clickables.remove(v);
	}
	public MouseListener getMouseListener(){
		return this;
	}
}

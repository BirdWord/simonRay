package partnercode;

import java.awt.Color;
import java.awt.Graphics2D;

import gui.components.Action;
import gui.components.Component;
import simonRay.ButtonInterfaceRay;

public class Button extends Component implements ButtonInterfaceRay {
	private Color c;
	private Color actualColor;
	private Action actionB;
	public Button(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void act() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isHovered(int x, int y) {
		if(x > getX() && x < getX()+getWidth() && y > getY() && y < getY()+getHeight()){
			return true;
		}
		return false;
	}

	@Override
	public void setColor(Color color) {
		c = color;
		update();
		
	}

	@Override
	public void setAction(Action action) {
		actionB = action;
		
	}

	@Override
	public void highlight() {
		// TODO Auto-generated method stub
		setColor(actualColor);
	}

	@Override
	public void dim() {
		setColor(Color.white);
		
	}

	@Override
	public void update(Graphics2D arg0) {
		// TODO Auto-generated method stub
		
	}

}

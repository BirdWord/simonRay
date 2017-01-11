package partnercode;

import java.awt.Color;
import java.awt.Graphics2D;

import gui.components.Action;
import gui.components.Component;
import simonRay.ButtonInterfaceRay;

public class Button extends Component implements ButtonInterfaceRay {
	private Color c;
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
	public boolean isHovered(int arg0, int arg1) {
		// TODO Auto-generated method stub
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
		
	}

	@Override
	public void dim() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Graphics2D arg0) {
		// TODO Auto-generated method stub
		
	}

}

package partnercode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import gui.Action;
import gui.Component;
import simonRay.ButtonInterfaceRay;

public class Button extends Component implements ButtonInterfaceRay{
	private Color c;
	private Color screenColor;
	private Action actionB;
	private boolean highlighted;
	public Button(int x, int y) {
		super(x, y, 70, 70);
	}

	@Override
	public void act() {
		actionB.act();
		
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
		screenColor = c;
		update();
		
	}

	@Override
	public void setAction(Action action) {
		actionB = action;
		
	}

	@Override
	public void highlight() {
		if(c != null){
			screenColor = c;
		}
		highlighted = true;
		update();
	}

	@Override
	public void dim() {
		screenColor = Color.gray;
		highlighted = false;
		update();
	}

	@Override
	public void update(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if(screenColor != null) g.setColor(screenColor);
		else g.setColor(Color.gray);
		g.fillOval(0, 0, getWidth(), getHeight());
		g.setColor(Color.black);
		g.drawOval(0, 0, getWidth()-1, getHeight()-1);
		if(highlighted){
			g.setColor(screenColor);
			g.fillOval(0, 0, getWidth(), getHeight());
		}
	}

}

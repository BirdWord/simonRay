package simonRay;

import java.awt.Color;

import gui.Action;
import gui.Clickable;

public interface ButtonInterfaceRay extends Clickable {

	void setColor(Color color);

	void setAction(Action action);

	void highlight();

	void dim();

}

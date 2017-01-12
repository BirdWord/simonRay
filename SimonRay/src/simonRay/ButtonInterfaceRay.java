package simonRay;

import java.awt.Color;

import gui.components.Action;
import gui.components.Clickable;

public interface ButtonInterfaceRay extends Clickable {

	void setColor(Color color);

	void setAction(Action action);

	void highlight();

	void dim();

	void setX(int i);

	void setY(int i);

}

package simonRay;

import java.awt.Color;

import gui.components.Action;
import gui.components.Clickable;

public interface ButtonInterfaceRay extends Clickable {

	void setColor(Color blue);

	void setX(int i);

	void setY(int i);


	void setAction(Action action);

	void highlight();

	void dim();
}

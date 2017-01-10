package simonRay;

import gui.components.Visible;

public interface ProgressInterfaceRay extends Visible {

	void increaseRound(int i);

	void gameOver();

	void setRound(int round);

	void setSequenceSize(int size);

}

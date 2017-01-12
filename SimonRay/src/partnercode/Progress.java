package partnercode;

import java.awt.Graphics2D;

import gui.Component;
import simonRay.ProgressInterfaceRay;


public class Progress extends Component implements ProgressInterfaceRay {

	private int roundLevel;
	private int sequenceSize;
	public Progress(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void gameOver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRound(int round) {
		roundLevel = round;
	}

	@Override
	public void setSequenceSize(int size) {
		sequenceSize = size;
		
	}

	@Override
	public void update(Graphics2D arg0) {
		// TODO Auto-generated method stub
		
	}

}

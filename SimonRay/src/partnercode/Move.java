package partnercode;

import simonRay.ButtonInterfaceRay;

import simonRay.SimonScreenRay;
import simonRay.MoveInterfaceRay;

public class Move implements MoveInterfaceRay {

	public Move() {
	}

	@Override
	public ButtonInterfaceRay getButton() {
		return SimonScreenRay.getButtons()[(int) (Math.random()*SimonScreenRay.getButtons().length)];
	}

}

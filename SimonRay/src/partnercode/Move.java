package partnercode;



import simonRay.ButtonInterfaceRay;
import simonRay.SimonScreenRay;
import simonRay.MoveInterfaceRay;

public class Move implements MoveInterfaceRay {
	private ButtonInterfaceRay b;
	public Move(ButtonInterfaceRay b) {
		this.b = b;
	}

	@Override
	public ButtonInterfaceRay getButton() {
		return b;
	}

}

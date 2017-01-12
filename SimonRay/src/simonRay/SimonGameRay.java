package simonRay;

import gui.GUIApplication;

public class SimonGameRay extends GUIApplication {
	private static final long serialVersionUID = 1L;

	public SimonGameRay(int width, int height) {
		super(width, height);
	}

	@Override
	public void initScreen() {
		SimonScreenRay screen = new SimonScreenRay(getWidth(), getHeight());
		setScreen(screen);
	}

	public static void main(String[] args) {
		SimonGameRay game = new SimonGameRay(500, 500);
		Thread thread = new Thread(game);
		thread.start();
	}

}

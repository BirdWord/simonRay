package simonRay;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import gui.Action;
import gui.ClickableScreen;
import gui.TextLabel;
import gui.Visible;
import partnercode.Button;
import partnercode.Move;
import partnercode.Progress;

public class SimonScreenRay extends ClickableScreen implements Runnable {
	private ProgressInterfaceRay progress;
	private ArrayList<MoveInterfaceRay> sequence;
	private int round;
	private boolean acceptInput;
	private final int BUTTONS = 5;
	private TextLabel label;
	private static ButtonInterfaceRay[] buttons;
	private int sequenceIndex;
	private int lastSelected;
	public SimonScreenRay(int width, int height) {
		super(width, height);
		Thread app = new Thread(this);
		app.start();
	}

	@Override
	public void run() {
		label.setText("");
	    nextRound();
	}

	private void nextRound() {
		acceptInput = false;
		round++;
		MoveInterfaceRay move = randomMove();
		sequence.add(move);
		progress.setRound(round);
		progress.setSequenceSize(sequence.size());
		changeText("Simon is poking buttons.");
		label.setText("");
		playSequence();
		changeText("Try to match him.");
		acceptInput = true;
		sequenceIndex = 0;
	}
	private void playSequence() {
		ButtonInterfaceRay b = null;
		for(MoveInterfaceRay m: sequence){
			if(b!=null)b.dim();
			b = m.getButton();
			b.highlight();
			try {
				Thread.sleep((long)(2000*(2.0/(round+2))));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		b.dim();
	}

	private void changeText(String s){
		label.setText(s);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		Color[] colors = {Color.red, Color.blue, new Color(240,160,70), new Color(20,255,140), Color.yellow};
		buttons = new ButtonInterfaceRay[BUTTONS];
		for(int i = 0; i < BUTTONS; i++){
			buttons[i] = getAButton();
			buttons[i].setColor(colors[i]);
			buttons[i].setX(((500*i)/BUTTONS)+15);
			buttons[i].setY(250);
			final ButtonInterfaceRay b = buttons[i];
			b.dim();
			buttons[i].setAction(new Action() {

				public void act() {

						Thread buttonPress = new Thread(new Runnable() {
							
							public void run() {
								if(acceptInput){
									b.highlight();
									try {
										Thread.sleep(500);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									b.dim();
								}
							}
						});
						buttonPress.start();
						

						if(acceptInput && sequence.get(sequenceIndex).getButton() == b){
							sequenceIndex++;
						}else if(acceptInput){
							gameOver();
							return;
						}
						if(sequenceIndex == sequence.size()){
							Thread nextRound = new Thread(SimonScreenRay.this);
							nextRound.start();
						}
					}

			});
			viewObjects.add(buttons[i]);
		}
		progress = getProgress();
		label = new TextLabel(145,400,300,40,"Let's play Simon!");
		sequence = new ArrayList<MoveInterfaceRay>();
		lastSelected = -1;
		sequence.add(randomMove());
		sequence.add(randomMove());
		round = 0;
		viewObjects.add(progress);
		viewObjects.add(label);
	}
	
	public void gameOver() {
		progress.gameOver();
	}
	
	private MoveInterfaceRay randomMove() {
		int rand;
		do{
			rand = (int)(Math.random()*buttons.length);
		}while(rand == lastSelected);
		lastSelected = rand;
		return new Move(buttons[rand]);
	}
	public static ButtonInterfaceRay[] getButtons(){
		return buttons;
	}
	private ButtonInterfaceRay getAButton() {
		return new Button(0,0);
	}

	private ProgressInterfaceRay getProgress() {
		return new Progress();
	}

}

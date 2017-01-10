package simonRay;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import gui.components.Action;
import gui.components.TextLabel;
import gui.components.Visible;
import gui.screens.ClickableScreen;

public class SimonScreenRay extends ClickableScreen implements Runnable {
	private ProgressInterfaceRay progress;
	private ArrayList<MoveInterfaceRay> sequence;
	private int round;
	private boolean acceptInput;
	private final int BUTTONS = 6;
	private TextLabel label;
	private ButtonInterfaceRay[] buttons;
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
		changeText("Simon's turn");
		label.setText("");
		playSequence();
		changeText("Your turn");
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
		Color[] colors = {Color.red, Color.blue, new Color(240,160,70), new Color(20,255,140), Color.yellow, new Color(180,90,210)};
		String[] names = {"RED", "BLUE", "ORANGE", "GREEN", "YELLOW", "PURPLE"};
		buttons = new ButtonInterfaceRay[BUTTONS];
		for(int i = 0; i < BUTTONS; i++ ){
			buttons[i] = getAButton();
			buttons[i].setName(names[i]);
			buttons[i].setColor(colors[i]);
			buttons[i].setX(160 + (int)(100*Math.cos(i*2*Math.PI/(BUTTONS))));
			buttons[i].setY(200 - (int)(100*Math.sin(i*2*Math.PI/(BUTTONS))));
			final ButtonInterfaceRay b = buttons[i];
			b.dim();
			buttons[i].setAction(new Action() {

				public void act() {

						Thread buttonPress = new Thread(new Runnable() {
							
							public void run() {
								b.highlight();
								try {
									Thread.sleep(500);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								b.dim();
								
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
		label = new TextLabel(130,230,300,40,"Let's play Simon!");
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
		return null;
		//return new Move(buttons[rand]);
		//needs Move contructor
	}

	private ButtonInterfaceRay getAButton() {
		return null;
	}

	private ProgressInterfaceRay getProgress() {
		return null;
	}
}

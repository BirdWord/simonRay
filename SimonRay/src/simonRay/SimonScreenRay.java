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
	private final int BUTTONS = 5;
	private TextLabel label;
	private ButtonInterfaceRay[] buttons;
	private int sequenceIndex;
	private int lastSelectedButton;
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
		MoveInterfaceRay b;
		for(int i = 0; i<sequence.size(); i++){
			b = sequence.get(i);
			if(b!=null){
				ButtonInterfaceRay button = b.getButton();
				button.highlight();
				int sleepTime = (int)(10000/round);
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				button.dim();
			}
			
		}
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
		generateButtons(viewObjects);
		progress = getProgress();
		label = new TextLabel(130,230,300,40,"Let's play Simon!");
		sequence = new ArrayList<MoveInterfaceRay>();
		lastSelectedButton = -1;
		sequence.add(randomMove());
		sequence.add(randomMove());
		round = 0;
		viewObjects.add(progress);
		viewObjects.add(label);
	}

	private MoveInterfaceRay randomMove() {
		ButtonInterfaceRay b;
		int rand;
		do{
			rand = (int)(Math.random()*buttons.length);
		}while(rand == lastSelectedButton);
		b = buttons[rand];
		return getMove(b);
	}

	private MoveInterfaceRay getMove(ButtonInterfaceRay b) {
		return null;
	}

	private void generateButtons(List<Visible> viewObjects) {
		Color[] colors = new Color[BUTTONS];
		for(int i = 0; i<colors.length; i++){
			colors[i] = new Color(randomRGB(),randomRGB(),randomRGB());
		}
		for(int i = 0; i<BUTTONS; i++){
			final ButtonInterfaceRay b = getAButton();
			b.setColor(colors[i]);
			b.setX(1);
			b.setY(1);
			b.setAction(new Action(){

				public void act(){
					if(acceptInput){
						Thread blink = new Thread(new Runnable(){

							public void run(){
								b.highlight();
								try {
									Thread.sleep(800);
									b.dim();
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}

						});
						blink.start();
						if(b == sequence.get(sequenceIndex).getButton()){
							sequenceIndex++;
							if(sequenceIndex == sequence.size()){
								Thread nextRound = new Thread(SimonScreenRay.this);
								nextRound.start(); 
							}
						}
						else{
							progress.gameOver();
						}
					}
				}
			});
			viewObjects.add(b);
		}
		
	}

	private ButtonInterfaceRay getAButton() {
		return null;
	}

	private ProgressInterfaceRay getProgress() {
		return null;
	}
	private int randomRGB(){
		return (int)(Math.random()*256);
	}
}

package partnercode;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import gui.Component;
import simonRay.ProgressInterfaceRay;


public class Progress extends Component implements ProgressInterfaceRay {

	private boolean gameOver;
	private int roundLevel;
	private int sequenceSize;

	public Progress() {
		super(50,50,120,50);
	}

	public void setRound(int roundNumber) {
		roundLevel = roundNumber;
		update();
	}

	public void setSequenceSize(int size) {
		sequenceSize = size;
		update();
	}

	public void gameOver() {
		gameOver = true;
		update();
	}



	@Override
	public void update(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		FontMetrics fm = g.getFontMetrics();
		if(gameOver){
			g.setColor(new Color(255,55,90));
			g.fillRect(0, 0, 120, 50);
			g.setColor(Color.white);
			String go = "Game over!";
			g.drawString(go, (120 - fm.stringWidth(go))/2, 20);
			g.drawString("Sequence Size = " + sequenceSize, (120 - fm.stringWidth("Sequence Size = " + sequenceSize))/2, 40);

		}else{
			g.setColor(new Color(220,255,230));
			g.fillRect(0, 0, 120, 50);
			g.setColor(Color.black);
			g.drawRect(0, 0, 120-1, 50-1);
			g.drawString("Round Number = " + roundLevel, (120 - fm.stringWidth("Round Number = " + roundLevel))/2, 20);
			g.drawString("Sequence Size = " + sequenceSize, (120 - fm.stringWidth("Sequence Size = " + sequenceSize))/2, 40);
			
		}
	}


}
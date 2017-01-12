package components;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MovingComponent extends Component implements Runnable {
	private long moveTime; //time when the image last moved
	private double vx; //the horizontal velocity
	private double vy; //the vertical velocity
	private double posx; //the actual x-coordinate of the object
	private double posy; //the actual y-coordinate of the object
	private boolean running;

	public static final int REFRESH_RATE = 20;
	public MovingComponent(int x, int y, int w, int h) {
		super(x, y, w, h);
		vx = 0;
		vy = 0;
		
	}
	public boolean isAnimated(){
		return true;
	}
	public void setX(int x){
		super.setX(x);
		posx = x;
	}
	public void setY(int y){
		super.setY(y);
		posy = y;
	}
	public boolean isRunning() {
		return running;
	}
	public void setRunning(boolean running) {
		this.running = running;
	}
	public double getVx() {
		return vx;
	}
	public void setVx(double vx) {
		this.vx = vx;
	}
	public double getVy() {
		return vy;
	}
	public void setVy(double vy) {
		this.vy = vy;
	}
	@Override
	public void run() {
		posx = getX();
		posy = getY();
		running = true;
		moveTime = System.currentTimeMillis();
		while(running){
			try {
				Thread.sleep(REFRESH_RATE);
				checkBehaviors();
				update();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

	@Override
	public void update(Graphics2D g) {
		long currentTime = System.currentTimeMillis();//gets time now
		int difference = (int) (currentTime - moveTime);//checks how long it has been since last update
		//updates only if amount of time is greater than frame rate
		if(difference >= REFRESH_RATE){
			//update displayTime, since an update is ocurring
			moveTime = currentTime;
			//calculate what the new position should be.
			posx += vx*(double)difference/REFRESH_RATE;
			posy += vy*(double)difference/REFRESH_RATE;
			//note: for very low velocities, position might not move by much. Therefore,
			//rounding to an int may not change
			super.setX((int)(posx));
			super.setY((int)(posy));

		}
		drawImage(g);
	}
	private void drawImage(Graphics2D g) {
		g.setColor(Color.black);
		g.fillOval(0, 0, getWidth(), getHeight());
	}
	public void play(){
		if(!running){
			Thread go = new Thread(this);
			go.start();
		}
	}
	public void checkBehaviors(){
		if(posy+vy > 300){
			posy = 300;
			vy=-vy;
		}
		else if(posy+vy < 20){
			posy = 20;
			vy=-vy;
		}
		
		if(posx+vx > 300){
			posx = 300;
			vx=-vx;
		}
		else if(posx+vx < 20){
			posx = 20;
			vx=-vx;
		}
		
	}

}

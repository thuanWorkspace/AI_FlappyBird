package flapp2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Bird {
	// the co-ordinate of bird.
	int x = (int) (GameScreen.width * 0.2), y=10;
	// falling speed of bird.
	double speed;
	BufferedImage bird = Resource.getResouceImage("dataFlapp/bird.png");
	private static double jumpValue = -5;
	static int jumpHeight = jumpHeight(jumpValue);

	Rectangle rect = new Rectangle();
	boolean alive;
	double score;

	public Bird(int y) {
		// TODO Auto-generated constructor stub
		this.y = y;
	}

	public void kill(double score) {
		this.alive = false;
		this.score = score;
	}

	public void resurrect() {
		this.alive = true;
		score = 0;
		this.y =10;
		this.speed = 0;
	}

	public final boolean isAlive() {
		return alive;
	}

	public double getScore() {
		return score;
	}
	
	public void jump() {
		speed = -2;
		y += speed;
	}

	private static int jumpHeight(double jumpValue) {
		int total = 0;
		while (jumpValue < 0) {
			total += jumpValue;
			jumpValue += GameScreen.GRAVITY;
		}
		return total;
	}

	public void update() {
		speed += GameScreen.GRAVITY;
		y += speed;

	}

	public void draw(Graphics g) {
		g.drawImage(bird, x, y, null);
		g.setColor(Color.RED);
		g.drawRect(rect.x, rect.y, rect.width, rect.height);
	}

	public Rectangle getBound() {
		rect.x = x;
		rect.y = y;
		rect.width = bird.getWidth();
		rect.height = bird.getHeight();
		return rect;
	}

	public void reset() {
		y = 0;
		speed = 0;
	}
}

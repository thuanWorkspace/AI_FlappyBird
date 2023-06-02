package flappyBird;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Bird {
	static int x = (int) (GameScreen.width * 0.2), y;
	double speed;
	BufferedImage bird = Resource.getResouceImage("dataFlapp/bird.png");
	private static double jumpValue = -5;
	static int jumpHeight = jumpHeight(jumpValue);

	Rectangle rect = new Rectangle();

	public void jump() {
		speed = jumpValue;
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
		if (y + bird.getHeight() < GameScreen.height) {
			speed += GameScreen.GRAVITY;
			y += speed;
		} else {
			GameScreen.currentState = GameScreen.die;
		}
	}

	public void DieFall() {
		if (y - 100 < GameScreen.height) {
			speed += 0.3;
			y += speed;
		}
	}

	public void draw(Graphics g) {
		g.drawImage(bird, x, y, null);
		g.setColor(Color.RED);
//		g.drawRect(rect.x, rect.y, rect.width, rect.height);
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

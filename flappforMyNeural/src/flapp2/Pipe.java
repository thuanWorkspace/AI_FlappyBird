package flapp2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Pipe {
	BufferedImage pipe = Resource.getResouceImage("dataFlapp/pipe.png");
	BufferedImage reversePipe = Resource.getResouceImage("dataFlapp/pipeReverse.png");
	int x = GameScreen.width + pipe.getWidth();
	int y = randomY();
	int xReverse = x;
	int yReverse;
	int top, bot = y;
	Rectangle topRect = new Rectangle();
	Rectangle botRect = new Rectangle();
	boolean isIncrease = false;

	public Pipe() {
		// TODO Auto-generated constructor stub
		
	}

	public boolean isNext(int offset) {
		if (offset < x)
			return true;
		return false;
	}

	public void update() {
		x -= 4;
		xReverse -= 4;
		// check increase point
		if (!isIncrease && x < (int) (GameScreen.width * 0.2)) {
			GameScreen.score++;
			if (GameScreen.score > GameScreen.highScore)
				GameScreen.highScore = GameScreen.score;
			isIncrease = true;
		}
	}

	// the core of pipe
	public int randomY() {
		int distance = -Bird.jumpHeight + 100;
		int rand = (int) (Math.random() * (GameScreen.height - distance));
		yReverse = rand - reversePipe.getHeight() + 10;
		top = rand + 10;
		return rand + distance - 10;
	}

	public void draw(Graphics g) {
		g.drawImage(pipe, x, y, null);
		g.drawImage(reversePipe, xReverse, yReverse, null);
		g.setColor(Color.RED);
//		g.drawRect(botRect.x, botRect.y, botRect.width, botRect.height);
//		g.drawRect(topRect.x, topRect.y, topRect.width,topRect.height);
	}

	public boolean isOutOfScreen() {
		return (x + pipe.getWidth()) < 0;
	}

	public Rectangle topBound() {
		topRect.x = xReverse;
		topRect.y = yReverse;
		topRect.width = reversePipe.getWidth();
		topRect.height = reversePipe.getHeight();
		return topRect;
	}

	public Rectangle botBound() {
		botRect.x = x;
		botRect.y = y;
		botRect.width = pipe.getWidth();
		botRect.height = pipe.getHeight();
		return botRect;
	}
}

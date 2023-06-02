package flappyBird;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class GameStateDie implements GameState {
	BufferedImage gameOver = Resource.getResouceImage("dataFlapp/gameOver1.png");
	int increase = 0;

	@Override
	public void update() {
		// TODO Auto-generated method stub
		GameScreen.bird.getBound();
		GameScreen.bird.DieFall();
		increase += 2;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		GameScreen.background.draw(g);
		GameScreen.enemies.draw(g);
		g.drawString("HI: " + GameScreen.highScore + " ", GameScreen.width / 2, (int) (GameScreen.height * 0.3));
		g.drawImage(gameOver, GameScreen.width / 2 - gameOver.getWidth() / 2, GameScreen.height / 2 - 50, null);
		GameScreen.land.draw(g);
		g.drawImage(GameScreen.land2, 0, GameScreen.height + GameScreen.land.image.getHeight(), null);
		GameScreen.bird.draw(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (increase >= 70) {
			GameScreen.bird.reset();
			GameScreen.enemies.reset();
			GameScreen.score = 0;
			GameScreen.currentState = GameScreen.play;
			increase = 0;
		} else {
			increase++;
		}
	}

}

package flappyBird;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class GameStateStart implements GameState {
	BufferedImage flappyBird = Resource.getResouceImage("dataFlapp/flappyBird.png");

	@Override
	public void update() {
		// TODO Auto-generated method stub
		Bird.y = GameScreen.height / 2;
		GameScreen.background.update();
		GameScreen.land.update();

	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		GameScreen.background.draw(g);
		GameScreen.bird.draw(g);
		g.drawImage(flappyBird, GameScreen.width / 2 - flappyBird.getWidth() / 2, GameScreen.height / 2 - 50, null);
		GameScreen.land.draw(g);
		g.drawImage(GameScreen.land2, 0, GameScreen.height+GameScreen.land.image.getHeight(), null);


	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		GameScreen.currentState = GameScreen.play;
	}

}

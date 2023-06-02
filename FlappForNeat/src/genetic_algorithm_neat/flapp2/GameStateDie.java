package genetic_algorithm_neat.flapp2;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


public class GameStateDie implements GameState {
	private GameScreen gameScreen;
	public GameStateDie(GameScreen gameScreen) {
		// TODO Auto-generated constructor stub
		this.gameScreen = gameScreen;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		reset();
		gameScreen.currentState= gameScreen.play;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		gameScreen.background.draw(g);
		gameScreen.enemies.draw(g);
		gameScreen.land.draw(g);
		g.drawImage(gameScreen.land2, 0, GameScreen.height + gameScreen.land.image.getHeight(), null);
	}

	public void reset() {
		gameScreen.enemies.reset();
		gameScreen.birdAlive = gameScreen.birds.size();
		GameScreen.score = 0;
		gameScreen.currentTime =0;
		gameScreen.currentMinute =0;
		for (Bird bird : gameScreen.birds) {
			bird.resurrect();
		}
	}
}

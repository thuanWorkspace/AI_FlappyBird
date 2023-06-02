package flappyBird;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;

public class GameStatePlay implements GameState {
	private AudioClip jumpSound;
	private AudioClip deadSound;

	public GameStatePlay() {
		// TODO Auto-generated constructor stub
		try {
			jumpSound = Applet.newAudioClip(new URL("file", "", "dataFlapp/jump.wav"));
			deadSound = Applet.newAudioClip(new URL("file", "", "data/hit.wav"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (!GameScreen.enemies.checkCollision(GameScreen.bird.getBound())) {
			GameScreen.background.update();
			GameScreen.bird.update();
			GameScreen.enemies.update();
			GameScreen.land.update();
		} else {
			deadSound.play();
			GameScreen.currentState = GameScreen.die;
		}
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		GameScreen.background.draw(g);
		GameScreen.enemies.draw(g);
		GameScreen.bird.draw(g);
		GameScreen.land.draw(g);
		g.drawImage(GameScreen.land2, 0, GameScreen.height + GameScreen.land.image.getHeight(), null);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		GameScreen.bird.jump();
//		jumpSound.play();
	}

}

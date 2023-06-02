package flapp2;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;

public class GameStatePlay implements GameState {
	private AudioClip jumpSound;
	private AudioClip deadSound;
	private GameScreen gameScreen;

	public GameStatePlay() {
		// TODO Auto-generated constructor stub
		try {
			jumpSound = Applet.newAudioClip(new URL("file", "", "dataFlapp/jump.wav"));
			deadSound = Applet.newAudioClip(new URL("file", "", "data/hit.wav"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public GameStatePlay(GameScreen gameScreen) {
		// TODO Auto-generated constructor stub
		this.gameScreen = gameScreen;
	}

	// input data
	public double[] getInput(Bird b) {
		Pipe next = null;
		for (Pipe p : gameScreen.enemies.pipes) {
			if (p.isNext(b.x)) {
				next = p;
				break;
			}
		}
		// convert step-force to 1.0 distance.
		double birdY = (double) b.y / (double) gameScreen.height;
//		int birdY = b.y;
		if (next == null) {
			return new double[] { birdY, 0, 0, 0 };
		} else {
			// force to 1.0
			double pipeX = (double) next.x / (double) gameScreen.width;
			double birdX = (double) b.x / (double) gameScreen.width;
			double distance = pipeX - birdX;
			double top = (double) next.top / (double) gameScreen.height;
			double bot = (double) next.bot / (double) gameScreen.height;
			// normal
//			double distance =next.x-b.x;
//			double top = next.top;
//			double bot =next.bot;
//			System.out.println("birdy: "+birdY+", distance: "+distance+", top: "+top+", bot: "+bot);
//			System.out.println("birdy: "+b.y+", pipe: "+(next.x)+",--bird: "+b.x +", top: "+next.top+", bot: "+next.bot);
			return new double[] { birdY, distance, top, bot };
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		gameScreen.enemies.update();
		gameScreen.background.update();
		gameScreen.land.update();
		
		// determine collision bird
		for (Bird bird : gameScreen.birds) {
			if (bird.isAlive() == true && gameScreen.enemies.checkCollision(bird.getBound())) {
				bird.kill(GameScreen.score);
				gameScreen.birdAlive--;
			}
		}
		// bird here
		for (Bird bird : gameScreen.birds) {
			bird.update();
			if (!bird.isAlive())
				continue;
			double bird_acc = bird.calculate(getInput(bird))[0];
//					System.out.println("bird: "+bird_acc);
			if (bird_acc > 0.5) {
				bird.jump();
			}
			// if bird out of y axis
			if ((bird.y <= 0 || bird.y >= GameScreen.height) && bird.isAlive() == true) {
				bird.kill(GameScreen.score);
				gameScreen.birdAlive--;
			}
		}

		// add bird die-> restart
		if (gameScreen.birdAlive <= 0) {
			gameScreen.ga_birds.execute();
			gameScreen.gen++;
			System.out.print("-------------");
			System.out.println("gen: " + gameScreen.gen);
			System.out.print("" + "current score: " + gameScreen.score);
			System.out.println(", " + "highest score: " + gameScreen.highScore);
			System.out.println("current time: " + gameScreen.currentMinute + "m" + " " + gameScreen.currentTime + " s");
			System.out.println("total time: " + gameScreen.total_Minute + "m" + " " + gameScreen.total_second + " s");
			System.out.print("highest time: " + gameScreen.highestMinute + "m" + " " + gameScreen.highestTime + " s");
			System.out.println("best gene: " + gameScreen.highestGene);
			gameScreen.currentState = gameScreen.die;
		}
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		gameScreen.background.draw(g);
		gameScreen.enemies.draw(g);
		for (Bird bird : gameScreen.birds) {
			if (bird.alive)
				bird.draw(g);
		}
		gameScreen.land.draw(g);
		g.drawImage(gameScreen.land2, 0, GameScreen.height + gameScreen.land.image.getHeight(), null);
	}
}

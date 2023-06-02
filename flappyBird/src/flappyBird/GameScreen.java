package flappyBird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class GameScreen extends JPanel implements KeyListener {
	static int width = 600, height = 366;
	static final double GRAVITY = 0.2;
	static Bird bird = new Bird();
	static EnemyManager enemies = new EnemyManager();
	static MovingEntity background = new MovingEntity("dataFlapp/background.png", 0);
	static MovingEntity land = new MovingEntity("dataFlapp/land.png", height);
	static BufferedImage land2 = Resource.getResouceImage("dataFlapp/land2.png");
	Timer timer;
	TimerTask task;
	static int score;
	static int highScore;
	static GameState start;
	static GameState play;
	static GameState die;
	static GameState currentState;

	public GameScreen() {
		// TODO Auto-generated constructor stub
		start = new GameStateStart();
		play = new GameStatePlay();
		die = new GameStateDie();
		currentState = start;
		timer = new Timer();
		task = new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				update();
				repaint();
			}
		};
		timer.schedule(task, 0, 1);
	}

	public void update() {
		currentState.update();
	}

	public void draw(Graphics g) {
		currentState.draw(g);
	}

	public int getDistance(Bird b) {
		Pipe next = null;
		for (Pipe pipe : enemies.getPipes()) {
			if (pipe.isNext(b.x)) {
				next = pipe;
				break;
			}
		}
		if (next == null) {
			return 0;
		}
		return next.x - b.x;
	}

	public Pipe getNextPipe(Bird b) {
		Pipe next = null;
		for (Pipe pipe : enemies.getPipes()) {
			if (pipe.isNext(b.x)) {
				next = pipe;
				break;
			}
		}
		return next;
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Font font = new Font("SansSerif", Font.PLAIN, 30);
		g.setFont(font);
		draw(g);
		g.drawString(score + " ", width / 2, (int) (height * 0.2));
		int birdX = bird.x;
		int generalY = bird.y;
		int distancex = getDistance(bird) + birdX;
		g.setColor(Color.BLUE);
		g.drawLine(birdX, generalY, distancex, generalY);
		g.drawLine(birdX, 0, birdX, generalY);
		g.setColor(Color.red);
		Pipe next = getNextPipe(bird);
		if (next != null) {
			g.drawLine(birdX, generalY, next.x, next.top);
			g.drawLine(birdX, generalY, next.x, next.bot);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		currentState.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}

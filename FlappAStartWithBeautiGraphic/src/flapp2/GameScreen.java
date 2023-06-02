package flapp2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;
import javax.swing.JSlider;

public class GameScreen extends JPanel {
	static int width = 600, height = 366;
	static final double GRAVITY = 0.2;
	Bird bird;
	int birdAlive;
	int gen;
	EnemyManager enemies = new EnemyManager();
	MovingEntity background = new MovingEntity("dataFlapp/background.png", 0);
	MovingEntity land = new MovingEntity("dataFlapp/land.png", height);
	BufferedImage land2 = Resource.getResouceImage("dataFlapp/land2.png");
	Timer timer;
	TimerTask task;
	static int score;
	static int highScore;
	GameState play;
	GameState die;
	GameState currentState;
	int currentTime;
	int currentMinute;
	int increaseMilliSecond;
	int highestGene;
	int highestTime;
	int highestMinute;
	int total_second;
	int total_Minute;
	boolean isStop = false;
	boolean runBFS;
	JSlider slider;
	boolean astart;
	public final static int TIME_UPDATE = 16;
	public final static int TIME_BACKGROUND_UPDATE = 1;
	LocalDateTime now = LocalDateTime.now();
	int startSecond = now.getSecond();

	public final boolean isRunBFS() {
		return runBFS;
	}

	public final void setRunBFS(boolean runBFS) {
		this.runBFS = runBFS;
	}

	public final void setStop(boolean isStop) {
		this.isStop = isStop;
	}

	public GameScreen(JSlider slider, int size) {
		// TODO Auto-generated constructor stub
		this.slider = slider;
		bird = new Bird(height / 2);
		// reset first.
		play = new GameStatePlay(this);
		die = new GameStateDie(this);
		currentState = die;

		timer = new Timer();
		task = new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (!isStop) {
					increaseTime();
					update();
					repaint();
				}
			}
		};
		timer.schedule(task, 0, TIME_UPDATE);
		background.setTimeUpdate(TIME_BACKGROUND_UPDATE);
	}

	public void increaseTime() {
		now = LocalDateTime.now();
		int tempSecond = now.getSecond();
		if (tempSecond != startSecond) {
			currentTime = currentTime + 1 * slider.getValue();
			total_second = total_second + 1 * slider.getValue();
			currentMinute += currentTime / 60;
			total_Minute += total_second / 60;
			startSecond = tempSecond;
			currentTime %= 60;
			total_second %= 60;
		}

		if (currentTime + currentMinute * 60 > highestTime + highestMinute * 60) {
			highestTime = currentTime;
			highestMinute = currentMinute;
			highestGene = gen;
		}
	}

	public void update() {
		for (int i = 0; i < slider.getValue(); i++) {
			currentState.update();
		}
	}

	public void draw(Graphics g) {
		for (int i = 0; i < slider.getValue(); i++) {
			currentState.draw(g);
		}
	}

	public int getDistance(Bird b) {
		Pipe next = null;
		for (int i = 0; i < enemies.getPipes().size(); i++) {
			Pipe pipe = enemies.getPipes().get(i);
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
		g.setColor(Color.decode("#0199cd"));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		Font font = new Font("SansSerif", Font.PLAIN, 30);
		g.setFont(font);
		draw(g);
		Font font2 = new Font("SansSerif", Font.PLAIN, 15);
		g.setFont(font2);
		g.setColor(Color.BLACK);

		// print parameter!
		g.drawString("gen: " + gen, (int) (width * 0.025), (int) (height * 0.1));
		g.drawString("avlie: " + birdAlive, (int) (width * 0.025), (int) (height * 0.2));
		g.drawString("score: " + score, (int) (width * 0.7), (int) (height * 0.1));
		g.drawString("highest score: " + highScore, (int) (width * 0.7), (int) (height * 0.2));
		g.drawString("current time: " + currentMinute + "m" + " " + currentTime + "s", (int) (width * 0.025),
				(int) (height * 1.1));
		g.drawString("total time: " + total_Minute + "m" + " " + total_second + "s", (int) (width * 0.025),
				(int) (height * 1.2));
		g.drawString("highest time: " + highestMinute + "m" + " " + highestTime + "s", (int) (width * 0.6),
				(int) (height * 1.1));
		g.drawString("best gene: " + highestGene, (int) (width * 0.6), (int) (height * 1.2));
		// draw line
//		for (Bird bird : birds) {
//			if (!bird.alive)
//				continue;
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
//		}
	}

	public final void setAstart(boolean astart) {
		this.astart = astart;
	}

}

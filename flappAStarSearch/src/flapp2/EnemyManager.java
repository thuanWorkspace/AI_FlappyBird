package flapp2;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

public class EnemyManager {
	List<Pipe> pipes = new LinkedList<Pipe>();
	// decide time create pipe.
	int increase=100, limit = 100;

	public void update() {
		// level
		if (GameScreen.score >= 5)
			limit = 90;
		if (GameScreen.score >= 10)
			limit = 85;
		if (GameScreen.score >= 20)
			limit = 80;
		if (GameScreen.score >= 20)
			limit = 70;
		for (int i = 0; i < pipes.size(); i++) {
			pipes.get(i).update();
		}
		makePipe();
		removeOutScreenPipes();
	}

	private void makePipe() {
		if (increase > limit) {
			pipes.add(new Pipe());
			increase = 0;
		} else {
			increase++;
		}
	}

	private void removeOutScreenPipes() {
		while (pipes.size() > 0 && pipes.get(0).isOutOfScreen())
			pipes.remove(0);
	}

	public void draw(Graphics g) {
		for (int i = 0; i < pipes.size(); i++)
			pipes.get(i).draw(g);
	}

	public boolean checkCollision(Rectangle birdBound) {
		for (int i = 0; i < pipes.size(); i++) {
			if (pipes.get(i).botBound().intersects(birdBound))
				return true;
			if (pipes.get(i).topBound().intersects(birdBound))
				return true;
		}
		return false;
	}

	public void reset() {
		limit = 100;
		while (!pipes.isEmpty())
			pipes.remove(0);
	}

	public final List<Pipe> getPipes() {
		return pipes;
	}
	
}

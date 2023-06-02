package flapp2;

public class BirdGoal {
	EnemyManager enemies;
	int goalY;
	Pipe nextPipe;

	public BirdGoal(EnemyManager enemies) {
		super();
		this.enemies = enemies;
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

	public void changeGoalXY(Bird b) {
		nextPipe = getNextPipe(b);
		if (nextPipe == null) {
			goalY = GameScreen.height / 2;
			return;
		}
		goalY = ((nextPipe.bot + nextPipe.top) / 2);
	}

	public int getGoalY(Bird b) {
		changeGoalXY(b);
		return goalY;
	}
}

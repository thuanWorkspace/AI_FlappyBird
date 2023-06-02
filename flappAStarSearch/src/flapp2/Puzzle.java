package flapp2;

import java.util.ArrayList;
import java.util.List;

//contain current bird and goal.compare here.

public class Puzzle {
	Node current;
	Node goal;
	EnemyManager enemies;

	public Puzzle(EnemyManager enemies) {
		super();
		this.enemies = enemies;
	}

	public Puzzle(Node current, Node goal, EnemyManager enemies) {
		super();
		this.current = current;
		this.goal = goal;
		this.enemies = enemies;
	}

	public int computeH(Node current) {
		int goaly = goal.getY();
		return Math.abs(goaly - current.y);
	}

	public Node newValue(Node thisNode, boolean jump) {
		double newSpeed = thisNode.fakeSpeed(thisNode.speed);
		Node node = new Node();
		int y;
		if (jump == true) {
			newSpeed = -2;
			y = thisNode.fakeJump(newSpeed, thisNode.y);
		} else {
			y = thisNode.fakeUpdate(newSpeed, thisNode.y);
		}
		node.setY(y);
		node.setSpeed(newSpeed);
		node.setJump(jump);
		node.setH(computeH(thisNode));
		return node;
	}

	public List<Node> getSuccessors() {
		ArrayList<Node> result = new ArrayList<Node>();
		result.add(newValue(current, false));
		result.add(newValue(current, true));
		return result;
	}

	public final void setGoal(Node goal) {
		this.goal = goal;
	}

	public final void setCurrent(Node current) {
		this.current = current;
	}
	
}

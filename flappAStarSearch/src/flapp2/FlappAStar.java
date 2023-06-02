package flapp2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class FlappAStar {
	
	public Node execute(EnemyManager enemy, Bird bird) {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(PuzzleUtils.HeuristicComparatorByF);
		ArrayList<Node> explorer = new ArrayList<Node>();
		// TODO Auto-generated method stub
		Puzzle puzzle = new Puzzle(enemy);
		BirdGoal goal = new BirdGoal(enemy);
//		puzzle.setGoal(new Node(goal.getGoalY(bird));
		puzzle.setGoal(new Node(goal.getGoalY(bird)));
		Node current = new Node();
		current.setY(bird.y);
		current.setJump(false);
		current.setSpeed(bird.speed);
		current.setH(puzzle.computeH(current));
		frontier.add(current);
		while (!frontier.isEmpty()) {
			Node curNode = frontier.poll();
			puzzle.setCurrent(curNode);
			if (curNode.h >-5 && curNode.h <5) {
				return curNode;
			}
			explorer.add(curNode);
			List<Node> childs = puzzle.getSuccessors();
			for (Node child : childs) {
				int newG = curNode.getG() + 1;
				if (!frontier.contains(child) && !explorer.contains(child)) {
					child.setG(newG);
					frontier.add(child);
					child.setParent(curNode);
				} else if (frontier.contains(child) && child.getG() > newG) {
					child.setG(newG);
					child.setParent(curNode);
				}
			}
		}
		return null;
	}

}

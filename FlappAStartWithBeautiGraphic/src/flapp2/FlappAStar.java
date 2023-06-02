package flapp2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class FlappAStar {
	LinkedList<Node> nodes = new LinkedList<Node>();
	LinkedList<Node> spaceNodes = new LinkedList<Node>();
	boolean getNodes;
	int leftRemain = 0;
	int x;
	int y;
	boolean runBFS;
	boolean runAstart;

	public final void setRunAstart(boolean runAstart) {
		this.runAstart = runAstart;
	}

	public final void setRunBFS(boolean runBFS) {
		this.runBFS = runBFS;
	}

	public FlappAStar(boolean runBFS) {
		super();
		this.runBFS = runBFS;
	}

	public final LinkedList<Node> getNodes() {
		return nodes;
	}

	public final LinkedList<Node> getSpaceNodes() {
		return spaceNodes;
	}

	public Node execute(EnemyManager enemy, Bird bird) {
		getNodes = false;
		if (nodes.size() > 1000) {
			nodes.removeAll(nodes);
		}
		if (spaceNodes.size() > 2000) {
			System.out.println("spcae node size: " + spaceNodes.size());
			spaceNodes.removeAll(spaceNodes);
		}
		Puzzle puzzle = new Puzzle(enemy);
		BirdGoal goal = new BirdGoal(enemy);
		puzzle.setGoal(new Node(goal.getGoalY(bird)));

		Queue<Node> nodesbfs = new LinkedList<Node>();

		Node current = new Node();
		current.setX(bird.x);
		current.setY(bird.y);
		current.setJump(false);
		current.setSpeed(bird.speed);
		current.setH(puzzle.computeH(current));
		nodesbfs.add(current);

		// bfs
		int count = 3000;
		int theLowest = Integer.MAX_VALUE;
		int theHighest = 0;
		Node childNew = null;
		if (runBFS) {
			while (count != 0 && !nodesbfs.isEmpty()) {
				Node curNode = nodesbfs.poll();
//			System.out.println("cur: " + curNode.jump + ", y node: " + curNode.y + ", goal: " + puzzle.goal.y);
				spaceNodes.add(curNode);
				if (curNode.containGoal(puzzle.goal)) {
					break;
				}
				List<Node> children = Puzzle.getSuccessors(curNode, false);
				for (Node child : children) {
					childNew = child;
					child.setParent(curNode);
					if (child.y > theHighest) {
						theHighest = child.y;
					}
					if (child.y < theLowest) {
						theLowest = child.y;
					}
					nodesbfs.add(child);
				}
				count--;
			}
		}
		//left node in bfs
		if (count <= 0) {
			int h1 = puzzle.computeH(theLowest);
			int h2 = puzzle.computeH(theHighest);
			int h = Math.min(h1, h2);
			int initialH = current.h;
			int height = height(childNew);
			int expectHeight = initialH * height / h;
			this.leftRemain = (int) ((Math.pow(2, expectHeight) - 1) - (Math.pow(2, height)));
			y = current.y;
			x = current.x + 50;
		} else {
			leftRemain = 0;
		}
		// TODO Auto-generated method stub
//		puzzle.setGoal(new Node(goal.getGoalY(bird));
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(PuzzleUtils.HeuristicComparatorByF);
		ArrayList<Node> explorer = new ArrayList<Node>();
		frontier.add(current);

		while (!frontier.isEmpty()) {
			Node curNode = frontier.poll();
			if (runAstart) {
				nodes.add(curNode);
			}
//			System.out.println(" a*: curNode: " + curNode.y + ", goal: " + puzzle.goal.y + ", curNode h: " + curNode.h);
			puzzle.setCurrent(curNode);
			if (curNode.h > -5 && curNode.h < 5) {
//			if (curNode.containGoal(puzzle.goal)) {
				getNodes = true;
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
//			try {
//				Thread.sleep(3);
//			} catch (Exception e) {
//				// TODO: handle exception
//				System.out.println("err");
//			}
		}

		getNodes = true;
		return null;
	}

	public final int getX() {
		return x;
	}

	public int height(Node node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + height(node.getParent());
		}
	}

	public final void setX(int x) {
		this.x = x;
	}

}

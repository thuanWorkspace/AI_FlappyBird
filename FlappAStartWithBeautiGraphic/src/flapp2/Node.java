package flapp2;

public class Node {
	int y;
	int h;
	int g;
	boolean jump;
	double speed;
	Node parent;
	int x;
	public Node() {
		// TODO Auto-generated constructor stub
	}

	public Node(int y) {
		super();
		this.y = y;
	}
	
	public final int getX() {
		return x;
	}

	public final void setX(int x) {
		this.x = x;
	}

	public Node(int y, boolean jump) {
		super();
		this.y = y;
		this.jump = jump;
	}

	public int fakeJump(double speed, int y) {
		y += speed;
		return y;
	}

	public int fakeUpdate(double speed, int y) {
		speed += GameScreen.GRAVITY;
		y += speed;
		return y;
	}

	public double fakeSpeed(double speed) {
		return speed += GameScreen.GRAVITY;
	}

	public final double getSpeed() {
		return speed;
	}

	public final void setSpeed(double speed) {
		this.speed = speed;
	}

	public final Node getParent() {
		return parent;
	}

	public final void setParent(Node parent) {
		this.parent = parent;
	}

	public final boolean isJump() {
		return jump;
	}

	public final void setJump(boolean jump) {
		this.jump = jump;
	}

	public int getF() {
		return this.h + this.g;
	}

	public final int getY() {
		return y;
	}

	public final int getH() {
		return h;
	}

	public final int getG() {
		return g;
	}

	public final void setY(int y) {
		this.y = y;
	}

	public final void setH(int h) {
		this.h = h;
	}

	public final void setG(int g) {
		this.g = g;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + g;
		result = prime * result + h;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
//		if ((other.jump==this.jump)&&(y > (other.y + 5)) && (y < (other.y - 0.5)))
			if ((y > (other.y + 5)) && (y < (other.y - 0.5)))
			return true;
		return false;
	}

	public boolean containGoal(Node goal) {
		if ((y < (goal.y + 20)) && (y >(goal.y - 20)))
			return true;
		return false;
	}

}

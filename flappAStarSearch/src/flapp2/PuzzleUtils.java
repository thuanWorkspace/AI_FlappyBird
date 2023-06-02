package flapp2;

import java.util.Comparator;

public class PuzzleUtils {

	// Compare 2 nodes by F values
	public static Comparator<Node> HeuristicComparatorByF = new Comparator<Node>() {

		@Override
		public int compare(Node a, Node b) {
			return a.getF() - b.getF();
		}
	};
}

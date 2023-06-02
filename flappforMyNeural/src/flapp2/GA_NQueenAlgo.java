package flapp2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

//where is the goal? when score = POSITIVE-INFINITE.
public class GA_NQueenAlgo {
	public static int POP_SIZE;// Population size
	public static final double MUTATION_RATE = 0.01;
	public static final int MAX_ITERATIONS = 100000;
	List<Bird> population = new ArrayList<Bird>();
	List<Neural> neurals = new ArrayList<Neural>();
	Random rd = new Random();

	public GA_NQueenAlgo(int size) {
		// TODO Auto-generated constructor stub
		POP_SIZE = size;
	}
	public GA_NQueenAlgo() {
		// TODO Auto-generated constructor stub
		POP_SIZE = 100;
	}
	// initialize the individuals of the population
	public void initPopulation() {
		for (int i = 0; i < POP_SIZE; i++) {
			Neural neural = new Neural();
			neurals.add(neural);
			Bird bird = new Bird(neural);
			population.add(bird);
		}
	}

	public void execute() {
		// Enter your code here
		List<Neural> neu_neurals = new LinkedList<Neural>();
		for (int i = 0; i < POP_SIZE; i++) {
//			Neural x = getParentByRandomSelection();
//			Neural y = getParentByRandomSelection();

//			Neural x = pickTopBest(10);
			Neural x = rouleteWheelSelection();
			Neural y = rouleteWheelSelection();
			
			Neural child = preproduce(x, y);
			if (Math.random() < MUTATION_RATE) {
				mutate(child);
			}
			neu_neurals.add(child);
		}
		neurals.clear();
		neurals.addAll(neu_neurals);
		neu_neurals.clear();
		// make new neurals for birds.
		for (int i = 0; i < neurals.size(); i++) {
			population.get(i).setNeural(neurals.get(i));
		}
	}

	public void mutate(Neural neural) {
		neural.mutate();
	}

//Crossover x and y to preproduce a child

	public Neural preproduce(Neural x, Neural y) {
		// Enter your code here
		double[] weights = new double[x.weights.length];
		double bias;
		int cut_point = (int) (Math.random() * x.weights.length);

		for (int i = 0; i < cut_point; i++) {
			weights[i] = x.weights[i];

		}
		for (int i = cut_point; i < x.weights.length; i++) {
			weights[i] = y.weights[i];
		}
		int ra = (int) (Math.random() * 2);
		if (ra == 1)
			bias = x.bias;
		else
			bias = y.bias;
		return new Neural(4, 1, weights, bias);
	}

	public Neural stochasticUniversalSampling() {
		return null;
	}

	public Neural rouleteWheelSelection() {
		double rand = Math.random();
		double sum = 0;
		for (Bird bird : population) {
			sum += bird.score;
		}

		// deal when sum = 0.
		if (sum == 0) {
			sum = 1;
			return getParentByRandomSelection();
		}

		double myValue = 0.0;
		for (int i = 0; i < population.size(); i++) {
			double cal = population.get(i).score / sum;
			double temp = myValue + cal;
			if (myValue <= rand && rand < temp) {
//				System.out.println(myValue + " < " + rand + " < " + temp);
				return neurals.get(i);
			}
			myValue += cal;
		}
		return null;
	}

	public Neural pickTopBest(int top) {
		Comparator<Bird> com = new Comparator<Bird>() {

			@Override
			public int compare(Bird o1, Bird o2) {
				// TODO Auto-generated method stub
				return Double.compare(o1.score, o2.score);
			}
		};
		population.sort(com);
		List<Bird> topbest = new ArrayList<Bird>(top);
		for (int i = 0; i < top; i++) {
			topbest.add(population.get(i));
		}
		int rand = (int) (Math.random() * top);
		return topbest.get(rand).neural;
	}

// Select K individuals from the population at random and
//select the best out of these to become a parent.
	public Neural getParentByTournamentSelection() {
		return null;
	}

//Select a random parent from the population
	public Neural getParentByRandomSelection() {
// Enter your code here
		int rand = (int) (Math.random() * POP_SIZE);
		return neurals.get(rand);
	}
}
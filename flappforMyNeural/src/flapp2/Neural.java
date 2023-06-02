package flapp2;

import java.util.Random;

//neural only for flapp.
//no hidden layer.
//no back propagation.
//only toy neural.
public class Neural {
	// value from -0.99 -...-> 0.999,0.99,0.9
	double[] inputs;
	double[] weights;
	double bias;
	double[] outputs;

	// constructor for birds.
	public Neural() {
		// TODO Auto-generated constructor stub
		this(4, 1);
	}
	public void backPropagation(double[] input,double target) {
		//cost function
		//error function
		//derivative
		//...
		//adjust weights and bias.
	}
	
	public Neural(int input, int output, double[] weight, double bias) {
		inputs = new double[input];
		weights = new double[input];
		this.weights = weight;
		this.bias = bias;
		outputs = new double[output];
	}

	public Neural(int input, int output) {
		// TODO Auto-generated constructor stub
		inputs = new double[input];
		weights = new double[input];
		randomArr(weights);
		bias = random();
		outputs = new double[output];
	}

	public void randomArr(double[] arr) {
		for (int i = 0; i < arr.length; i++) {
			arr[i] = random();
		}
	}

	public double random() {
		Random rand = new Random();
		double value = -2 + rand.nextInt(4) + rand.nextDouble();
		return value;
	}

	// value from 0,0.1,0.11,0.111-...->0.999,0.99,0.9
	public double sigmoid(double x) {
		return 1 / (1 + Math.exp(-x));
	}

	public double calculatey() {
		double y = 0;
		for (int i = 0; i < inputs.length; i++) {
			y += inputs[i] * weights[i];
		}
		y += bias;
		return y;
	}

	public double[] predict(double[] input) {
		for (int i = 0; i < input.length; i++) {
			inputs[i] = input[i];
		}
		double y = calculatey();
		outputs[0] = sigmoid(y);
		return outputs;
	}

	public static double random(double rangeMin, double rangeMax) {
		Random r = new Random();
		if (rangeMin < 0.0)
			rangeMin = 0;
		double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
		return randomValue;
	}

	// support for Genetic algorithm.
	public void mutate() {
		int changeWeight = (int) (Math.random() * 2);
		if (changeWeight == 0) {
			int randomWeight = (int) (Math.random() * weights.length);
			weights[randomWeight] += random(-0.05, 0.05);
		}
		int changeBias = (int) (Math.random() * 2);
		if (changeBias == 0) {
			bias += random(-0.05, 0.05);
		}
	}

}

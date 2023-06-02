package genetic_algorithm_neat.flapp2;

import javax.swing.JFrame;

public class GameJFrameTestDrive {
	public static void main(String[] args) {
		JFrame f = new JFrame("flapp");
		f.setSize(GameScreen.width, GameScreen.height + 120);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameScreen screen = new GameScreen(500);
		f.add(screen);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
	}
}

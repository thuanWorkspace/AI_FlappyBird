package flappyBird;

import javax.swing.JFrame;

public class GameJFrameTestDrive {
	public static void main(String[] args) {
		JFrame f = new JFrame("flapp");
		f.setSize(GameScreen.width, GameScreen.height + 120);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameScreen screen = new GameScreen();
		f.add(screen);
		f.addKeyListener(screen);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
	}
}

package flappyBird;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public interface GameState {
	public void update();

	public void draw(Graphics g);

	public void keyPressed(KeyEvent e);
}

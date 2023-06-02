package flapp2;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public interface GameState {
	public void update();

	public void draw(Graphics g);

}

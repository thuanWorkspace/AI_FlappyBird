package flappyBird;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class MovingEntity {
	String path;
	BufferedImage image;
	List<InforImage> images = new LinkedList<MovingEntity.InforImage>();
	int touchWidth, y;

	public MovingEntity(String path, int y) {
		super();
		this.path = path;
		this.y = y;
		image = Resource.getResouceImage(path);
	}

	public void update() {
		createToWidth();
		for (int i = 0; i < images.size(); i++) {
			images.get(i).update();
		}
		removeOutScreen();
	}

	public void draw(Graphics g) {
		for (int i = 0; i < images.size(); i++) {
			InforImage image = images.get(i);
			g.drawImage(image.image, image.x, image.y, null);
		}
	}

	private void removeOutScreen() {
		while (!images.isEmpty() && images.get(0).isOutOfScreen()) {
			images.remove(0);
			touchWidth -= image.getWidth();
		}
	}

	public void createToWidth() {
		while ((touchWidth - (image.getWidth() / 2)) < GameScreen.width + image.getWidth()) {
			images.add(new InforImage(Resource.getResouceImage(path), touchWidth, y));
			touchWidth += image.getWidth();
		}
	}

	class InforImage {
		BufferedImage image;
		int x, y;

		public InforImage(BufferedImage image, int x, int y) {
			super();
			this.image = image;
			this.x = x;
			this.y = y;
		}

		public void update() {
			x -= 2;
		}

		public boolean isOutOfScreen() {
			if (x + image.getWidth() < 0)
				return true;
			return false;
		}
	}
}

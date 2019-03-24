package shape;

import java.awt.Graphics;

public class Ellipse extends Shape{

	@Override
	public void draw(Graphics graphics) {
		graphics.drawOval(x1, y1, x2 - x1, y2 - y1);
	}
}

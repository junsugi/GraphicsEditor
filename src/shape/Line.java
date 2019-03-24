package shape;

import java.awt.Graphics;

public class Line extends Shape{

	@Override
	public void draw(Graphics graphics) {
		graphics.drawLine(x1, y1, x2, y2);
	}
}

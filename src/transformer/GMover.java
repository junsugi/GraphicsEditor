package transformer;

import java.awt.Graphics2D;

public class GMover extends GTransformer{
	
	public GMover() {

	}

	@Override
	public void initTransforming(Graphics2D graphics2d, int x, int y) {
		this.getShape().initMoving(graphics2d, x, y);
	}

	@Override
	public void keepTransforming(Graphics2D graphics2d, int x, int y) {
		this.getShape().draw(graphics2d);
		this.getShape().keepMoving(x, y);
		this.getShape().draw(graphics2d);
	}

	@Override
	public void finishTransforming(Graphics2D graphics2d, int x, int y) {
		this.getShape().finishMoving(x, y);
	}
}

package transformer;

import java.awt.Graphics2D;

public class GDrawer extends GTransformer{
	
	public GDrawer() {

	}

	@Override
	public void initTransforming(Graphics2D graphics2d, int x, int y) {
		this.getShape().setOrigin(x, y);	
	}

	@Override
	public void keepTransforming(Graphics2D graphics2d, int x, int y) {
		this.getShape().draw(graphics2d);
		this.getShape().setPoint(x, y);
		this.getShape().draw(graphics2d);
	}

	@Override
	public void finishTransforming(Graphics2D graphics2d, int x, int y) {
		
	}

	
}

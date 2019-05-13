package transformer;

import java.awt.Graphics2D;

import shape.GShape;

public abstract class GTransformer {
	
	private GShape shape;
	
	public GTransformer() {
		this.setShape(null);
	}

	//Getter, Setter
	public GShape getShape() {return shape;}
	public void setShape(GShape shape) {this.shape = shape;}
	
	//abstract
	abstract public void initTransforming(Graphics2D graphics2d, int x, int y);
	abstract public void keepTransforming(Graphics2D graphics2d, int x, int y);
	abstract public void finishTransforming(Graphics2D graphics2d, int x, int y);
}

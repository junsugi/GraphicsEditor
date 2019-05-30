package shape;

public class GEllipse extends GShape{

	private java.awt.geom.Ellipse2D ellipse2D;

	public GEllipse() {
		super();
		this.shape = new java.awt.geom.Ellipse2D.Double(0, 0, 0, 0);
		this.ellipse2D = (java.awt.geom.Ellipse2D) this.shape;
	}
	
	@Override
	public void setOrigin(int x, int y) {
		this.ellipse2D.setFrame(x, y, 0, 0);
	}

	@Override
	public void setPoint(int x, int y) {
		this.ellipse2D.setFrame(this.ellipse2D.getX(), this.ellipse2D.getY(), 
				x - this.ellipse2D.getX(), y - this.ellipse2D.getY());
	}

	public void addPoint(int x, int y) {
	}

	@Override
	public void keepMoving(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finishMoving(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GShape newInstance() {
		// TODO Auto-generated method stub
		return null;
	}
}

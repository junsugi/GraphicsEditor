package shape;

public class GLine extends GShape{

	private java.awt.geom.Line2D line2D;

	public GLine() {
		super();
		this.shape = new java.awt.geom.Line2D.Double(0, 0, 0, 0);
		this.line2D = (java.awt.geom.Line2D) this.shape;
	}
	
	public void setOrigin(int x, int y) {
		this.line2D.setLine(x, y, x, y);
	}
	
	public void setPoint(int x, int y) {
		this.line2D.setLine(this.line2D.getX1(), this.line2D.getY1(), x, y);
	}
	
	public void addPoint(int x, int y) {
	}
	
	public boolean contains(int x, int y) {
		boolean check = false;
		if(this.line2D.getX1() < x && x < this.line2D.getX2()) {
			if(this.line2D.getY1() < y && y < this.line2D.getY2()) {
				check = true;
				this.setSelected(true);
			}
		} else {
			check = false;
			this.setSelected(false);
		}
		return check;
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

package shape;

public class GPolygon extends GShape {
	private java.awt.Polygon polygon;

	public GPolygon() {
		super();
		this.polygon = new java.awt.Polygon();
		this.shape = this.polygon;
	}
	
	public void setOrigin(int x, int y) {
		this.polygon.addPoint(x, y);
		this.polygon.addPoint(x, y);
	}
	
	public void setPoint(int x, int y) {
		this.polygon.xpoints[this.polygon.npoints - 1] = x;
		this.polygon.ypoints[this.polygon.npoints - 1] = y;
	}
	
	//현재점을 고정시키고 하나를 더 추가시킨다.
	public void addPoint(int x, int y) {
		//한 점을 추가
		this.polygon.addPoint(x, y);
	}

	@Override
	public void keepMoving(int x, int y) {
		int dw = x - this.px;
		int dh = y - this.py;
		
		this.polygon.translate(dw, dh);

		this.px = x;
		this.py = y;
	}
	
	public void finishMoving(int x, int y) {
	}

}

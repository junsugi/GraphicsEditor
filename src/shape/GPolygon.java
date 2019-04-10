package shape;

import java.awt.Graphics2D;

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
	public void draw(Graphics2D graphics2d) {
		graphics2d.draw(this.polygon);
	}

	@Override
	public void keepMoving(int x, int y) {
		int dw = x - this.px;
		int dh = y - this.py;
		for(int i = 0 ; i < this.polygon.npoints; i++) {
			this.polygon.xpoints[i] += dw;
			this.polygon.ypoints[i] += dh;
		}	
		this.px = x;
		this.py = y;
	}
	
	public void finishMoving(int x, int y) {
		java.awt.Polygon newPolygon = new java.awt.Polygon();
		for(int i = 0 ; i < this.polygon.npoints; i++) {
			newPolygon.addPoint(this.polygon.xpoints[i], this.polygon.ypoints[i]);
		}	
		this.polygon = newPolygon;
		this.shape = this.polygon;
	}

}

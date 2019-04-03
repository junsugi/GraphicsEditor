package shape;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Polygon extends Shape{
	private java.awt.Polygon polygon;

	public Polygon() {
		super();
		this.shape = new java.awt.Polygon();
		this.polygon = (java.awt.Polygon) this.shape;
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
	public void draw(Graphics graphics) {
		Graphics2D graphics2d = (Graphics2D) graphics;
		graphics2d.draw(this.polygon);
	}

}

package shape;

import java.awt.Graphics;

public class Polygon extends Shape{
	
	private final static int nMaxPoint = 20;
	private int[] xPoints;
	private int[] yPoints;
	private int nPoints;

	public Polygon() {
		this.xPoints = new int[nMaxPoint];
		this.yPoints = new int[nMaxPoint];
		this.nPoints = 0;
	}
	
	public void setOrigin(int x, int y) {
		this.xPoints[this.nPoints] = x;
		this.yPoints[this.nPoints] = y;
		this.nPoints += 1;
		
		this.xPoints[this.nPoints] = x;
		this.yPoints[this.nPoints] = y;
		this.nPoints += 1;
	}
	
	public void setPoint(int x, int y) {
		this.xPoints[this.nPoints-1] = x;
		this.yPoints[this.nPoints-1] = y;
	}
	
	//현재점을 고정시키고 하나를 더 추가시킨다.
	public void addPoint(int x, int y) {
		//한 점을 추가
		this.xPoints[this.nPoints] = x;
		this.yPoints[this.nPoints] = y;
		this.nPoints += 1;
	}
	
	@Override
	public void draw(Graphics graphics) {
		graphics.drawPolygon(xPoints, yPoints, nPoints);
	}

}

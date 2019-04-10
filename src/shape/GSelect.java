package shape;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class GSelect extends GShape{
	
	//원점은 꼭 있되, 두 점을 전부 가질 필요는 없다.
	protected int x1, y1, x2, y2;
	
	public void setOrigin(int x, int y) {
		this.x1 = x;
		this.y1 = y;
		this.x2 = x;
		this.y2 = y;
	}
	
	public void setPoint(int x, int y) {
		this.x2 = x;
		this.y2 = y;
	}
	
	public void addPoint(int x, int y) {
		
	}

	@Override
	public void draw(Graphics2D graphics2d) {
	}

	@Override
	public void keepMoving(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finishMoving(int x, int y) {
		// TODO Auto-generated method stub
		
	}
}

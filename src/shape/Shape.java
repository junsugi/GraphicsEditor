package shape;
import java.awt.Graphics;

public abstract class Shape {
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
	
	public Shape clone() {
		try {
			return (Shape)this.getClass().newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	abstract public void draw(Graphics graphics);
}
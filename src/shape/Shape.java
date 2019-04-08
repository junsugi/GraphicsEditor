package shape;
import java.awt.Graphics2D;

public abstract class Shape {
	
	protected java.awt.Shape shape;
	//previous x,y
	protected int px, py;

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
	
	public void draw(Graphics2D graphics2d) {
		graphics2d.draw(this.shape);
	}
	
	public boolean contains(int x, int y) {
		return this.shape.contains(x, y);
	}
	
	
	public void initMoving(int x, int y) {
		this.px = x;
		this.py = y;
	}
	
	//무슨 도형이 올지 모르기 때문에 여기서 값을 계산할 수가 없다.
	abstract public void setOrigin(int x, int y);
	abstract public void setPoint(int x, int y);
	abstract public void addPoint(int x, int y);

	abstract public void keepMoving(int x, int y);
	abstract public void finishMoving(int x, int y);
}
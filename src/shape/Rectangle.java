package shape;

public class Rectangle extends Shape{
	
	//Adaptation
	private java.awt.Rectangle rectangle;

	public Rectangle() {
		super();
		this.shape = new java.awt.Rectangle();
		this.rectangle = (java.awt.Rectangle) this.shape;
	}
	
	public void setOrigin(int x, int y) {
		this.rectangle.setBounds(x, y, 0, 0);
	}
	
	//width, height 변경하는 부분
	public void setPoint(int x, int y) {
		this.rectangle.setSize(x - this.rectangle.x, y - this.rectangle.y);
	}
	
	public void addPoint(int x, int y) {
	}

	@Override
	public void keepMoving(int x, int y) {
		int dw = x - this.px;
		int dh = y - this.py;
		
		this.rectangle.setLocation(this.rectangle.x + dw, this.rectangle.y + dh);
		
		this.px = x;
		this.py = y;
	}

	@Override
	public void finishMoving(int x, int y) {
		// TODO Auto-generated method stub
		
	}
}

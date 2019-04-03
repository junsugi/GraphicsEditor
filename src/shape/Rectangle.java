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
}

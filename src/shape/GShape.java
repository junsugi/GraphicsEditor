package shape;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import shape.GAnchors.EAnchors;

public abstract class GShape implements Cloneable, Serializable{
	//eOnShape = Move
	public enum EOnState {eOnShape, eOnResize, eOnRotate};
	
	//attributes
	private static final long serialVersionUID = 1L;
	protected int px, py;	//previous x,y
	private boolean selected;
	
	//Components
	protected Shape shape;
	private GAnchors anchors;

	public GShape() {
		this.selected = false;
		this.anchors = new GAnchors();
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	//Deep Clone
	public GShape clone() {
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(this);
			
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
			ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
			
			return (GShape) objectInputStream.readObject();
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	abstract public GShape newInstance();
	
	public void draw(Graphics2D graphics2d) {
		graphics2d.draw(this.shape);
		if(this.selected) {
			this.anchors.setBoundingRect(this.shape.getBounds());
			this.anchors.draw(graphics2d);
		}
	}
	
	//어느 앵커에 있는지 확인하는 메서드
	public EOnState onShape(int x, int y) {
		if(this.selected == true) {
			EAnchors eAnchors = this.anchors.onShape(x, y);	
			if(eAnchors == null) {	   				  // None	
				if(this.shape.contains(x, y)) {
					return EOnState.eOnShape;
				}
			} else if(eAnchors.equals(EAnchors.RR)) { //Rotate
				return EOnState.eOnRotate;
			} else {						   		  // Resize
				return EOnState.eOnResize;
			}
		} else {
			if(this.shape.contains(x, y)) {
				return EOnState.eOnShape;
			}
		}
		return null;
	}
	
	public void initMoving(Graphics2D graphics2d, int x, int y) {
		this.px = x;
		this.py = y;
		if(this.selected) {
			this.anchors.setBoundingRect(this.shape.getBounds());
			this.anchors.draw(graphics2d);
		}
	}
	
	//Getter, Setter
	public boolean isSelected() {return selected;}
	public Shape getShape() {return shape;}

	//무슨 도형이 올지 모르기 때문에 여기서 값을 계산할 수가 없다.
	abstract public void setOrigin(int x, int y);
	abstract public void setPoint(int x, int y);
	abstract public void addPoint(int x, int y);

	abstract public void keepMoving(int x, int y);
	abstract public void finishMoving(int x, int y);
}
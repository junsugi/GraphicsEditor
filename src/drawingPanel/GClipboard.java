package drawingPanel;

import java.util.Vector;

import shape.GShape;

public class GClipboard {
	private Vector<GShape> shapes;
	
	public GClipboard() {
		this.shapes = new Vector<GShape>();
	}
	
	//멀티플 셀렉션이 올수도 있기 때문에
	public void setContents(Vector<GShape> shapes) {
		//이전의 내용을 날려버린다. (아니면 이차원 어레이로 만든다.)
		this.shapes.clear();
		this.shapes.addAll(shapes);
	}
	
	public Vector<GShape> getContents() {
		Vector<GShape> clonedShapes = new Vector<GShape>();
		for(GShape shape : this.shapes) {
			GShape clonedShape = shape.clone();
			clonedShapes.add(clonedShape);
		}
		return clonedShapes;
	}
}

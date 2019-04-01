package drawingPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;

import global.Constants.EToolbar;
import shape.Shape;

public class DrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	//상태를 n개의 점을 사용하는 도형, 두 점을 사용하는 도형으로 나눠라.
	private enum EActionState {eReady, e2PDrawing, eNPDrawing};
	private EActionState eActionState;
	
	private MouseHandler mouseHandler;
	
	private Vector<Shape> shapeVector;
	private Shape currentShape;
	private Shape currentTool;

	public void setCurrentTool(EToolbar currentTool) {
		this.currentTool = currentTool.getShape();
	}
	
	public DrawingPanel() {
		this.eActionState = EActionState.eReady;
		
		this.setBackground(Color.white);
		
		this.mouseHandler = new MouseHandler();
		this.addMouseListener(this.mouseHandler);		//버튼이벤트
		this.addMouseMotionListener(this.mouseHandler);	//마우스의 움직임을 인지하는 이벤트
		
		this.shapeVector = new Vector<Shape>();
		this.currentTool = EToolbar.select.getShape();
	}
	
	public void paint(Graphics graphics) {
		//부모님 먼저 그리세요. 이거 안하면 그림이 자칫 잘못하면 깨진다.
		super.paint(graphics);
		
		for(Shape shape : this.shapeVector) {
			shape.draw(graphics);
		}
	}
	
	private void drawShape() {
		//종합그림도구세트, 운영체제가 가지고 있다. 운영체제가 사용하는 폰트와 색상을 사용하게 된다.
		//운영체제가 가지고 있는 그림 도구를 그대로 받아온다.
		//메인프레임이 받아서 drawingPanel(자식)로 전달해준다.
		Graphics graphics = this.getGraphics();
		graphics.setXORMode(getBackground());
		this.currentShape.draw(graphics);
	}
	
	private void initDrawing(int x, int y) {
		this.currentShape = this.currentTool.clone();
		this.currentShape.setOrigin(x, y);
		this.drawShape();
	}
	
	private void keepDrawing(int x, int y) {
		this.drawShape();
		this.currentShape.setPoint(x, y);
		this.drawShape();
	}
	
	private void continueDrawing(int x, int y) {
		//중간점을 찍는 함수 (폴리곤)
		this.currentShape.addPoint(x, y);
	}
	
	private void finishDrawing(int x, int y) {
		this.shapeVector.add(this.currentShape);
	}

	//잡다한 코드 넣지말고 함수 호출만 한다. (교통정리만 한다.)
	private class MouseHandler implements MouseListener, MouseMotionListener  {
		//같은 위치에서 press와 release가 동시에 일어난 일.
		//응용이벤트, 원래 있는 이벤트가 아니다.
		//pressed와 release와 clicked에 동시에 작업하면 더블클릭같은 함수 호출이 일어난다.
		
		@Override
		public void mouseClicked(MouseEvent event) {
			if(event.getClickCount() == 1) {
				mouse1Clicked(event);
			} else if (event.getClickCount() == 2) {
				mouse2Clicked(event);
			}
		} 

		private void mouse1Clicked(MouseEvent event) {
			if(eActionState.equals(EActionState.eReady)) {
				initDrawing(event.getX(), event.getY());
				eActionState = EActionState.eNPDrawing;
			} else if (eActionState.equals(EActionState.eNPDrawing)) {
				continueDrawing(event.getX(), event.getY());
			}
		}

		private void mouse2Clicked(MouseEvent event) {
			if(eActionState.equals(EActionState.eNPDrawing)) {
				finishDrawing(event.getX(), event.getY());
				eActionState = EActionState.eReady;
			}
		}

		@Override
		public void mousePressed(MouseEvent event) {
			if(eActionState.equals(EActionState.eReady)) {
				initDrawing(event.getX(), event.getY());
				eActionState = EActionState.e2PDrawing;
			} 
		}

		@Override
		public void mouseReleased(MouseEvent event) {
			if(eActionState.equals(EActionState.e2PDrawing)) {
				finishDrawing(event.getX(), event.getY());
				eActionState = EActionState.eReady;
			}
		}

		@Override
		public void mouseDragged(MouseEvent event) {
			if(eActionState.equals(EActionState.e2PDrawing)) {
				keepDrawing(event.getX(), event.getY());
			}
		}
		
		@Override
		public void mouseMoved(MouseEvent event) {
			if(eActionState.equals(EActionState.eNPDrawing)) {
				keepDrawing(event.getX(), event.getY());
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent event) {}

		@Override
		public void mouseExited(MouseEvent event) {}

	}
}

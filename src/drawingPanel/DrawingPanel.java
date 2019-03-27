package drawingPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import global.Constants.EToolbar;
import shape.Shape;

public class DrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	//상태를 n개의 점을 사용하는 도형, 두 점을 사용하는 도형으로 나눠라.
	private enum EActionState {eReady, eCMCDrawing, ePDRDrawing};
	private EActionState eActionState;
	
	private MouseHandler mouseHandler;
	
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
		
		this.currentTool = EToolbar.select.getShape();
	}
	
	private void drawShape() {
		//종합그림도구세트, 운영체제가 가지고 있다. 운영체제가 사용하는 폰트와 색상을 사용하게 된다.
		//운영체제가 가지고 있는 그림 도구를 그대로 받아온다.
		//메인프레임이 받아서 drawingPanel(자식)로 전달해준다.
		Graphics graphics = this.getGraphics();
		graphics.setXORMode(getBackground());
		this.currentTool.draw(graphics);
	}
	
	private void initDrawing(int x, int y) {
		this.currentTool.setOrigin(x, y);
		this.drawShape();
	}
	
	private void keepDrawing(int x, int y) {
		this.drawShape();
		this.currentTool.setPoint(x, y);
		this.drawShape();
	}
	
	private void continueDrawing(int x, int y) {
		//중간점을 찍는 함수 (폴리곤)
		this.currentTool.addPoint(x, y);

	}
	
	private void finishDrawing(int x, int y) {
//		this.drawShape();
//		this.currentTool.setPoint(x, y);
//		this.drawShape();
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
				eActionState = EActionState.eCMCDrawing;
			} else if(eActionState.equals(EActionState.eCMCDrawing)){
				finishDrawing(event.getX(), event.getY());
				eActionState = EActionState.eReady;
			}
		}

		private void mouse2Clicked(MouseEvent event) {
		}

		@Override
		public void mousePressed(MouseEvent event) {
			if(eActionState.equals(EActionState.eReady)) {
				initDrawing(event.getX(), event.getY());
				eActionState = EActionState.ePDRDrawing;
			} 
		}

		@Override
		public void mouseReleased(MouseEvent event) {
			if(eActionState.equals(EActionState.ePDRDrawing)) {
				finishDrawing(event.getX(), event.getY());
				eActionState = EActionState.eReady;
			}
		}

		@Override
		public void mouseDragged(MouseEvent event) {
			if(eActionState.equals(EActionState.ePDRDrawing)) {
				keepDrawing(event.getX(), event.getY());
			}
		}
		
		@Override
		public void mouseMoved(MouseEvent event) {
			if(eActionState.equals(EActionState.eCMCDrawing)) {
				keepDrawing(event.getX(), event.getY());
			}
		}
		

		@Override
		public void mouseEntered(MouseEvent event) {}

		@Override
		public void mouseExited(MouseEvent event) {}

	}
}

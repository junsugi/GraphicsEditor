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

	private MouseHandler mouseHandler;
	
	private Shape currentTool;

	public void setCurrentTool(EToolbar currentTool) {
		this.currentTool = currentTool.getShape();
	}
	
	public DrawingPanel() {
		this.setBackground(Color.white);
		
		this.mouseHandler = new MouseHandler();
		this.addMouseListener(this.mouseHandler);		//버튼이벤트
		this.addMouseMotionListener(this.mouseHandler);	//마우스의 움직임을 인지하는 이벤트
		
		this.currentTool = EToolbar.select.getShape();
	}
	
	private void drawShape(int x0, int y0) {
		//종합그림도구세트, 운영체제가 가지고 있다. 운영체제가 사용하는 폰트와 색상을 사용하게 된다.
		//운영체제가 가지고 있는 그림 도구를 그대로 받아온다.
		//메인프레임이 받아서 drawingPanel(자식)로 전달해준다.
		Graphics graphics = this.getGraphics();
		graphics.setXORMode(getBackground());
		this.currentTool.setOrigin(x0, y0);
		this.currentTool.draw(graphics);
	}
	
	private void moveShape(int x0, int y0) {
		Graphics graphics = this.getGraphics();
		graphics.setXORMode(this.getBackground());
		this.currentTool.draw(graphics);
		this.currentTool.setPoint(x0, y0);
		this.currentTool.draw(graphics);
	}

	//잡다한 코드 넣지말고 함수 호출만 한다. (교통정리만 한다.)
	private class MouseHandler implements MouseListener, MouseMotionListener  {
		//같은 위치에서 press와 release가 동시에 일어난 일.
		//응용이벤트, 원래 있는 이벤트가 아니다.
		//pressed와 release와 clicked에 동시에 작업하면 더블클릭같은 함수 호출이 일어난다.
		@Override
		public void mouseClicked(MouseEvent event) {
		} 

		@Override
		public void mousePressed(MouseEvent event) {
			drawShape(event.getX(), event.getY());
		}

		@Override
		public void mouseReleased(MouseEvent event) {
		}

		//지운다 = 배경화면을 다시 그린다, 배경화면으로 다시 칠한다.
		@Override
		public void mouseDragged(MouseEvent event) {
			moveShape(event.getX(), event.getY());
		}

		@Override
		public void mouseEntered(MouseEvent event) {}

		@Override
		public void mouseExited(MouseEvent event) {}

		@Override
		public void mouseMoved(MouseEvent e) {}		
	}
}

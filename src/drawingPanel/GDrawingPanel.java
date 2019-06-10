package drawingPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;

import global.GConstants.EToolbar;
import shape.GGroup;
import shape.GPolygon;
import shape.GShape;
import shape.GShape.EOnState;
import transformer.GDrawer;
import transformer.GMover;
import transformer.GResizer;
import transformer.GRotator;
import transformer.GTransformer;

public class GDrawingPanel extends JPanel {
	//attributes
	private static final long serialVersionUID = 1L;

	//Components
	private MouseHandler mouseHandler;
	private Vector<GShape> shapeVector;
	private GClipboard clipboard;
	
	//Working Variable (잠깐 잠깐 사용하는 번수, 심각하게 관계를 고려하는 그런 애들은 아니다.)
	private enum EActionState {eReady, eTransforming};
	private EActionState eActionState;
	private boolean updated;
	public boolean isUpdated() {return this.updated;}
	public void setUpdated(boolean updated) {this.updated = updated;}

	private GTransformer transformer;
	private GShape currentShape;
	private GShape currentTool;
	
	public Vector<GShape> getShapeVector() {
		return this.shapeVector;
	}
	
	@SuppressWarnings("unchecked")
	public void restoreShapeVector(Object shapeVector) {
		if(shapeVector == null) {
			this.shapeVector.clear();
		} else {
			this.shapeVector = (Vector<GShape>) shapeVector;
		}
		this.repaint();
	}

	public void setCurrentTool(EToolbar currentTool) {
		this.currentTool = currentTool.getShape();
	}

	public GDrawingPanel() {
		this.eActionState = EActionState.eReady;
		this.updated = false;
		
		this.setBackground(Color.white);
		this.setForeground(Color.black);
		
		this.mouseHandler = new MouseHandler();
		this.addMouseListener(this.mouseHandler);		//버튼이벤트
		this.addMouseMotionListener(this.mouseHandler);	//마우스의 움직임을 인지하는 이벤트
		
		this.clipboard = new GClipboard();
		
		this.shapeVector = new Vector<GShape>();
		this.transformer = null;
	}
	
	public void initialize() {
	}
	
	public void paint(Graphics graphics) {
		//부모님 먼저 그리세요. 이거 안하면 그림이 자칫 잘못하면 깨진다.
		Graphics2D graphics2d = (Graphics2D)graphics;
		super.paint(graphics2d);
		for(GShape shape : this.shapeVector) {
			shape.draw(graphics2d);
		}
	}

	//좌표를 주고 밑에 누가 있냐 없냐를 판단.
	//있으면 그리고 없으면 안그린다.
	private EOnState onShape(int x, int y) {
		this.currentShape = null;
		for(GShape shape : this.shapeVector) {
			EOnState eOnstate = shape.onShape(x, y);
			if(eOnstate != null) {
				this.currentShape = shape;
				return eOnstate;
			}
		}
		return null;
	}
	
	private void clearSelected() {
		for(GShape shape : this.shapeVector) {
			shape.setSelected(false);
		}
	}
	
	//actionstate를 바꿔줘야 한다.
	//아무 shape에도 없다 = null
	//currentShape에 있으면 
	//onShape이 move, resize, rotate
	//null이면 drawing이 두 종류
	private void selectTransforming(int x, int y) {
		EOnState eOnState = this.onShape(x, y);
		if(eOnState == null) {
			this.clearSelected();
			this.transformer = new GDrawer();
		} else {
			if(!this.currentShape.isSelected()) {
				this.clearSelected();
				this.currentShape.setSelected(true);
			}
			switch(eOnState) {
			case eOnShape:
				this.transformer = new GMover();
				break;
			case eOnResize:
				this.transformer = new GResizer();
				break;
			case eOnRotate:
				this.transformer = new GRotator();
				break;
			default:
				//exception
				this.eActionState = null;
				break;
			}
		}
	}
	
	private void initTransforming(int x, int y) {
		if(this.transformer instanceof GDrawer) {
			this.currentShape = this.currentTool.newInstance();
		}
		this.transformer.setShape(this.currentShape);
		this.transformer.initTransforming((Graphics2D)this.getGraphics(), x, y);
	}

	private void keepTransforming(int x, int y) {
		Graphics2D graphics2d = (Graphics2D)this.getGraphics();
		graphics2d.setXORMode(this.getBackground());
		this.transformer.keepTransforming(graphics2d, x, y);
	}
	
	private void finishTransforming(int x, int y) {
		this.transformer.finishTransforming((Graphics2D)this.getGraphics(), x, y);
		if(this.transformer instanceof GDrawer) {
			if(this.currentShape instanceof GGroup) {
				((GGroup)this.currentShape).contains(this.shapeVector);
			} else {
				this.shapeVector.add(this.currentShape);
			}
		}
		this.repaint();
		this.updated = true;
	}
	
	private void continueDrawing(int x, int y) {
		this.currentShape.addPoint(x, y);
	}

	public void cut() {
		Vector<GShape> selectedShapes = new Vector<GShape>();
		for(int i = this.shapeVector.size() - 1 ; i >= 0; i--) {
			if(this.shapeVector.get(i).isSelected()) {
				selectedShapes.add(this.shapeVector.get(i));
				this.shapeVector.remove(i);
			}
		}
		this.clipboard.setContents(selectedShapes);
		this.repaint();
		
	}
	
	public void copy() {
		
		
	}
	
	public void paste() {
		Vector<GShape> shapes = this.clipboard.getContents();
		this.shapeVector.addAll(shapes);
		this.repaint();
		
	}

	//잡다한 코드 넣지말고 함수 호출만 한다. (교통정리만 한다. event action mapping, control tower, state transition(=event) mapping)
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
				if(currentTool instanceof GPolygon) {
				}
			} else if (eActionState.equals(EActionState.eTransforming)) {
				if(currentTool instanceof GPolygon) {
					continueDrawing(event.getX(), event.getY());
				}
			}
		}

		private void mouse2Clicked(MouseEvent event) {
			if(eActionState.equals(EActionState.eTransforming)) {
				if(currentTool instanceof GPolygon) {
					eActionState = EActionState.eReady;
				}
			}
		}
		
		@Override
		public void mouseMoved(MouseEvent event) {
			if(eActionState.equals(EActionState.eTransforming)) {
				if(currentTool instanceof GPolygon) {		
				}
			}
		}
		
		@Override
		public void mousePressed(MouseEvent event) {
			//드로잉패널의 상태 = eActionState
			if(eActionState.equals(EActionState.eReady)) {
				//드로잉패널이 무엇을 할지를 판단하는 곳
				//프레스로 시작되는 모든 이벤트를 판단하는 곳. 트랜스포머를 결정
				selectTransforming(event.getX(), event.getY());
				initTransforming(event.getX(), event.getY());
				eActionState = EActionState.eTransforming;
			}
		}

		@Override
		public void mouseDragged(MouseEvent event) {
			if(eActionState.equals(EActionState.eTransforming)) {
				keepTransforming(event.getX(), event.getY());
			} 
		}

		@Override
		public void mouseReleased(MouseEvent event) {
			if(eActionState.equals(EActionState.eTransforming)) {
				finishTransforming(event.getX(), event.getY());
				eActionState = EActionState.eReady;
			} 
		}

		@Override
		public void mouseEntered(MouseEvent event) {}

		@Override
		public void mouseExited(MouseEvent event) {}

	}
}

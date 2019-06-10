package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import drawingPanel.GDrawingPanel;
import global.GConstants.EEditMenu;

public class GEditMenu extends JMenu{
	private static final long serialVersionUID = 1L;
	
	private GDrawingPanel drawingPanel;

	public GEditMenu(String text) {
		super(text);
		ActionHandler actionHandler = new ActionHandler();
		
		for(EEditMenu eEditMenu : EEditMenu.values()) {
			JMenuItem menuItem = new JMenuItem(eEditMenu.getText());
			menuItem.setActionCommand(eEditMenu.getMethod());
			menuItem.addActionListener(actionHandler);
			this.add(menuItem);
		}
	}

	public void associate(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;	
	}

	public void initialize() {
		// TODO Auto-generated method stub
	}
	
	/*
	 * 그림을 안그리는 사각형이다. 자기 안에 있는 애들만 그린다.
	 * shape하고 똑같은 group 클래스를 만들어라.
	 * 사각형의 서브 클래스로 만든다. transforming을 할 때만 그린다.
	 * vector의 포인터를 가지고 있는다. (new 하면 절대 안됨, 이미 만들어진 도형을 가지고 있는 것이다.)
	 * union을 써서 각각의 도형의 크기를 계산하면 된다........
	 * 그룹화를 하는 순간 ShapeVector에서 해당 도형을 빼고 그룹화된 도형들을 집어 넣는다.
	 */

	public void undo() {
		
	}
	
	public void redo() {
		
	}
	
	public void cut() {
		this.drawingPanel.cut();
	}
	
	public void copy() {
		this.drawingPanel.copy();
	}
	
	public void paste() {
		this.drawingPanel.paste();
	}
	
	public void group() {
		
	}
	
	public void ungroup() {
		
	}

	private void invokeMethod(String actionCommand) {
		try {
			this.getClass().getMethod(actionCommand).invoke(this);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			e.printStackTrace();
		}
	}
	
	private class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			invokeMethod(actionEvent.getActionCommand());
		}
	}	

}

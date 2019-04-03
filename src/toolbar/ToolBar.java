package toolbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import drawingPanel.DrawingPanel;
import global.Constants.EToolbar;

public class ToolBar extends JToolBar {
	//attributes
	private static final long serialVersionUID = 1L;
	
	//components
	private Vector<JRadioButton> buttons;

	//association
	private DrawingPanel drawingPanel;
	
	public void associate(DrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;	
	}
	//여기서 자식을 new해주고 속성들의 값을 초기화 해주는 곳이다.
	public ToolBar() {
		//하나 눌리면 하나가 빠지게 만들기 위해서 만든 객체
		ButtonGroup buttonGroup = new ButtonGroup();
		this.buttons = new Vector<JRadioButton>();
		ActionHandler actionHandler = new ActionHandler();

		for(EToolbar eToolBar : EToolbar.values()) {
			JRadioButton button = new JRadioButton(eToolBar.getText());
			button.setActionCommand(eToolBar.name());
			button.addActionListener(actionHandler);
			this.buttons.add(button);
			buttonGroup.add(button);
			this.add(button);
		}
	}
	
	public void initialize() {
		//나중에 constants에서 관리하도록 하자.
		this.buttons.get(EToolbar.rectangle.ordinal()).doClick();
	}
	
	private class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			drawingPanel.setCurrentTool(EToolbar.valueOf(actionEvent.getActionCommand()));
		}
	}
}

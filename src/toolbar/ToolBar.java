package toolbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JToolBar;

import drawingPanel.DrawingPanel;
import global.Constants.EToolbar;

public class ToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;
	
	private Vector<JButton> buttons;

	//association
	private DrawingPanel drawingPanel;
	
	public void associate(DrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;	
	}
	
	public ToolBar() {
		this.buttons = new Vector<JButton>();
		ActionHandler actionHandler = new ActionHandler();

		for(EToolbar eToolBar : EToolbar.values()) {
			JButton button = new JButton(eToolBar.getText());
			button.setActionCommand(eToolBar.name());
			button.addActionListener(actionHandler);
			this.buttons.add(button);
			this.add(button);
		}
	}
	
	private class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			drawingPanel.setCurrentTool(EToolbar.valueOf(actionEvent.getActionCommand()));
		}
	}
	
}

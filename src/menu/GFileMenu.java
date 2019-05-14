package menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import drawingPanel.GDrawingPanel;
import global.GConstants.EFileMenu;
import global.GConstants.EToolbar;

public class GFileMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	
	private GDrawingPanel drawingPanel;
	
	public GFileMenu(String text) {
		super(text);

		ActionHandler actionHandler = new ActionHandler();
		
		for(EFileMenu eMenuItem : EFileMenu.values()) {
			JMenuItem menuItem = new JMenuItem(eMenuItem.getText());
			menuItem.setActionCommand(eMenuItem.getMethod());
			menuItem.addActionListener(actionHandler);
			this.add(menuItem);
		}
	}

	public void initialize(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}
	
	public void nnew() {
		System.out.println("new");
	}
	
	public void open() {
		System.out.println("open");
	}
	
	//Serialization
	public void save() {
		try {
			System.out.println("save");
			File file = new File("drawingPanel");
			ObjectOutputStream objectOutputStream;
			objectOutputStream = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(file)));
			objectOutputStream.writeObject(this.drawingPanel.getShapeVector());
			objectOutputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void saveAs() {
		System.out.println("saveAs");
	}
	
	public void close() {
		System.out.println("close");
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

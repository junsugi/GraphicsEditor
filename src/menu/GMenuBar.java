package menu;
import javax.swing.JMenuBar;

import drawingPanel.GDrawingPanel;
import global.GConstants.EMenu;

public class GMenuBar extends JMenuBar {
	//attribute
	private static final long serialVersionUID = 1L;
	
	//components
	private GFileMenu fileMenu;
	
	//associations
	private GDrawingPanel drawingPanel;
	
	public void associate(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;	
	}
	
	public GMenuBar() {
		this.fileMenu = new GFileMenu(EMenu.fileMenu.getText());
		this.add(this.fileMenu);
	}

	public void initialize() {
		//associate
		this.fileMenu.associate(this.drawingPanel);
		
		//initialize
		this.fileMenu.initialize();
	}
}

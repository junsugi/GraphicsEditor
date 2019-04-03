package main;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import drawingPanel.DrawingPanel;
import global.Constants.EMainFrame;
import menu.MenuBar;
import toolbar.ToolBar;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	//Components
	private MenuBar menuBar;
	private ToolBar toolBar;
	private DrawingPanel drawingPanel;
	
	public MainFrame() {
		//속성부터 셋팅하고 속성은 내부에서 셋팅하기
		//attributes
		this.setLocation(EMainFrame.x.getValue(), EMainFrame.y.getValue());
		this.setSize(EMainFrame.w.getValue(), EMainFrame.h.getValue());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setLayout(new BorderLayout());
		
		//components = 자식
		this.menuBar = new MenuBar();
		this.setJMenuBar(this.menuBar);
		
		this.toolBar = new ToolBar();
		this.add(this.toolBar, BorderLayout.NORTH);
		
		this.drawingPanel = new DrawingPanel();
		this.add(this.drawingPanel, BorderLayout.CENTER);
		
		//associations
		this.menuBar.associate(this.drawingPanel);
		this.toolBar.associate(this.drawingPanel);
	}

	public void initialize() {
		this.menuBar.initialize();
		this.toolBar.initialize();
		this.drawingPanel.initialize();
	}

}

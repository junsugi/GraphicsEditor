package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import drawingPanel.GDrawingPanel;
import global.GConstants.EMainFrame;
import menu.GMenuBar;
import toolbar.GToolBar;

public class GMainFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	//Components
	private GMenuBar menuBar;
	private GToolBar toolBar;
	private GDrawingPanel drawingPanel;
	
	public GMainFrame() {
		//속성부터 셋팅하고 속성은 내부에서 셋팅하기
		//attributes
		this.setSize(EMainFrame.w.getValue(), EMainFrame.h.getValue());
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(
				dim.width / 2 - this.getSize().width / 2, 
				dim.height / 2 - this.getSize().height / 2);
		//windowListener를 달아서 파일메뉴의 close를 부르게 해라
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setLayout(new BorderLayout());
		
		//components = 자식
		this.menuBar = new GMenuBar();
		this.setJMenuBar(this.menuBar);
		
		this.toolBar = new GToolBar();
		this.add(this.toolBar, BorderLayout.NORTH);
		
		this.drawingPanel = new GDrawingPanel();
		this.add(this.drawingPanel, BorderLayout.CENTER);
	}

	public void initialize() {
		//associations
		this.menuBar.associate(this.drawingPanel);
		this.toolBar.associate(this.drawingPanel);
		
		//initialize
		this.menuBar.initialize();
		this.toolBar.initialize();
		this.drawingPanel.initialize();
	}

}

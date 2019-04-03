package main;

public class Main {

	private static MainFrame mainFrame;
	
	public static void main(String[] args) {
		mainFrame = new MainFrame();
		mainFrame.initialize();
		mainFrame.setVisible(true);
	}

}

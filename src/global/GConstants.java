package global;

import shape.*;

public class GConstants {

	public enum EMainFrame {
		x(200),
		y(100),
		w(600),
		h(600)
		;
	
		private int value;
		private	EMainFrame(int value){
			this.value = value;
		}
		public int getValue() {
			return this.value;
		}
	}
	
	public enum EToolbar {
		select("select", new GSelect()),
		rectangle("retangle", new GRectangle()),
		ellipse("ellipse", new GEllipse()),
		line("line", new GLine()),
		polygon("polygon", new GPolygon())
		;
		
		private String text;
		private GShape shape;
		private	EToolbar(String text, GShape shape){
			this.text = text;
			this.shape = shape;
		}
		public String getText() {
			return this.text;
		}
		public GShape getShape() {
			return this.shape;
		}
	}
	
	public enum EMenu {
		fileMenu("File"),
		editMenu("Edit")
		;
	
		private String text;
		private	EMenu(String text){
			this.text = text;
		}
		public String getText() {
			return this.text;
		}
	}	
	
	public enum EFileMenu { 
		newItem("new"),
		openItem("open")
		;

		private String text;
		private	EFileMenu(String text){
			this.text = text;
		}
		public String getText() {
			return this.text;
		}
	}
	

}

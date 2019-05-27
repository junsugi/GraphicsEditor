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
		newItem("New", "nnew"),
		openItem("Open", "open"),
		saveItem("Save", "save"),
		saveAsItem("Save as", "saveAs"),
		printItem("Print", "print"),
		closeItem("Close", "close")
		;

		private String text;
		private String method;
		private	EFileMenu(String text, String method){
			this.text = text;
			this.method = method;
		}
		public String getText() {
			return this.text;
		}
		public String getMethod() {
			return this.method;
		}
	}
	
	public enum EEditMenu {
		undo("되돌리기", "undo"),
		redo("다시 실행", "redo"),
		cut("잘라내기", "cut"),
		copy("복사하기", "copy"),
		paste("붙여넣기", "paste"),
		group("그룹화", "group"),
		ungroup("그룹 해제", "ungroup")
		;
		
		private String text;
		private String method;
		private	EEditMenu(String text, String method){
			this.text = text;
			this.method = method;
		}
		public String getText() {
			return this.text;
		}
		public String getMethod() {
			return this.method;
		}
	}
}

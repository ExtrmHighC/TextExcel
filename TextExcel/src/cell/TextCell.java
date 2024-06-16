package cell;

import spreadsheet.SpreadsheetLocation;

public class TextCell implements Cell {
	private String fullText;
	private String location;
	private SpreadsheetLocation locObj; 
	
	public TextCell(String text, String location, SpreadsheetLocation obj) {
		this.location = location; 
		this.fullText = text; 
		this.locObj = obj;
	}
	
	public String abbreviatedCellText() {
		// text for spreadsheet cell display, must be exactly length 10
		if (fullText.length() > 10) {
			return fullText.substring(0, 10); 
		} else {
			return String.format("%10s", this.fullText);	
		}
	}
	public String fullCellText() {
		return this.fullText; 
	}
	public String getLocation() {
		return this.location; 
	}
	
	public SpreadsheetLocation returnLocationObject() {
		return this.locObj; 
	}
}

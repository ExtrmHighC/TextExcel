package cell;

import spreadsheet.SpreadsheetLocation;

public class ValueCell implements Cell {
	private double fullInt;
	private String location;
	private SpreadsheetLocation locObj; 
	
	public ValueCell(double value, String location, SpreadsheetLocation obj) {
		this.location = location; 
		this.fullInt = value + 0.0; 
		this.locObj = obj;
	}
	
	public String abbreviatedCellText() {
		// text for spreadsheet cell display, must be exactly length 10
		String intToString = Double.toString(fullInt); 
		if (intToString.length() > 10) {
			return intToString.substring(0, 10); 
		} else {
			return String.format("%10s", this.fullInt);	
		}
	}
	public String fullCellText() {
		return Double.toString(this.fullInt); 
	}
	public String getLocation() {
		return this.location; 
	}
	
	public SpreadsheetLocation returnLocationObject() {
		return this.locObj; 
	}
}

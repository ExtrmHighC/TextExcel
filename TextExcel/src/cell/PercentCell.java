package cell;

import spreadsheet.SpreadsheetLocation;

public class PercentCell implements Cell {
	private double percentAsDouble;
	private double rawPercent; 
	private String location;
	private SpreadsheetLocation locObj; 
	
	public PercentCell(double rawPercent, String location, SpreadsheetLocation obj) {
		this.location = location; 
		this.rawPercent = rawPercent;
		this.percentAsDouble = this.rawPercent / 100.0; 
		this.locObj = obj;
	}
	
	public String abbreviatedCellText() {
		// text for spreadsheet cell display, must be exactly length 10
		String intToString = Double.toString(percentAsDouble); 
		if (intToString.length() > 10) {
			return intToString.substring(0, 10); 
		} else {
			return String.format("%10s", this.percentAsDouble);	
		}
	}
	public String fullCellText() {
		return Double.toString(this.rawPercent); 
	}
	public String getLocation() {
		return this.location; 
	}
	
	public SpreadsheetLocation returnLocationObject() {
		return this.locObj; 
	}
}

package cell;

import spreadsheet.SpreadsheetLocation;

public class FormulaCell implements Cell {
	private String fullFormula;
	private double forumlaEquals; 
	private String location;
	private SpreadsheetLocation locObj; 
	
	public FormulaCell(String location, String fullFormula, double answer, SpreadsheetLocation obj) {
		this.location = location; 
		this.fullFormula = fullFormula; 
		this.forumlaEquals = answer; 
		this.locObj = obj;
	}
	
	public String abbreviatedCellText() {
		// text for spreadsheet cell display, must be exactly length 10
		return String.format("%10s", Double.toString(forumlaEquals)); 
	}
	public String fullCellText() {
		return this.fullFormula; 
	}
	public String getLocation() {
		return this.location; 
	}
	
	public SpreadsheetLocation returnLocationObject() {
		return this.locObj; 
	}
}

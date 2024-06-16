package cell;
import spreadsheet.*; 

public class EmptyCell implements Cell {
	private String locationConcat = ""; 
	private SpreadsheetLocation location; 
	
	// defines location for each cell
	public EmptyCell(String locationConcat, SpreadsheetLocation location) {
		this.locationConcat = locationConcat; 
		this.location = location; 
	}
	
	public SpreadsheetLocation returnLocationObject() {
		return this.location; 
	}

	public String getLocation() {
		return this.locationConcat;
	}
	
	@Override
	public String abbreviatedCellText() {
		return "          "; 
	}
	
	@Override
	public String fullCellText() {
		return ""; 
	}
}

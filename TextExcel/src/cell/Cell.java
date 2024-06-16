//*******************************************************
// DO NOT MODIFY THIS FILE!!!
//*******************************************************
package cell;

import spreadsheet.SpreadsheetLocation;

public interface Cell {
	public String abbreviatedCellText(); // text for spreadsheet cell display, must be exactly length 10
	public String fullCellText(); // text for individual cell inspection, not truncated or padded
	public String getLocation();
	public SpreadsheetLocation returnLocationObject();
}

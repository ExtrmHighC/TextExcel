//*******************************************************
//DO NOT MODIFY THIS FILE!!!
//*******************************************************
package spreadsheet;

public interface Location {
	// represents a location like B6, must be implemented by your SpreadsheetLocation class
	int getRow(int row); // gets row of this location
	char getCol(int col); // gets column of this location
}

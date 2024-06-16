//Update this file with your own code.
package spreadsheet;

public class SpreadsheetLocation implements Location {
	private int row; 
	private char col; 
    @Override
    public int getRow(int row) { 
    	this.row = row;
        return this.row;
    }

    @Override
	// 65 = A
	// 76 = L 
    public char getCol(int col) {
    	char column = ' '; 
		for (int i = 65; i <= 76; i++) {
			if (i == (-11 + 76) + col) 
				column = (char)i; 
		}
		this.col = column; 
		return this.col; 
    }
}

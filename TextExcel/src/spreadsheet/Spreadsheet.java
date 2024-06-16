// Update this file with your own code.



package spreadsheet;

import cell.*;
import java.util.Arrays;

public class Spreadsheet implements Grid {
	private int rows = 20; 
	private int columns = 12; 

	Cell[][] grid; 
	public Spreadsheet() {
		grid = new Cell[this.getRows() + 1][this.getCols()];
		
		for (int row = 0; row <= grid.length - 1; row++) {
			for (int column = 0; column <= grid[1].length - 1; column++) {
				SpreadsheetLocation location = new SpreadsheetLocation(); 
				char locCol = location.getCol(column); 
				int locRow = location.getRow(row);
				String locConcat = (Character.toString(locCol) + "" + locRow); 
 				grid[row][column] = new EmptyCell(locConcat, location); 
				
			}	
		}
		System.out.println(getGridText()); 
	}
	
	@Override
	// accommodate for spaces in strings
	public String processCommand(String command)
	{
		String ret = "";
		String[] split = command.split("\\s+"); 
		
		Boolean hasInt = false;
		Boolean hasString = false;
		Boolean hasPercent = false; 
		
		int intValue = 0; 
		String textValue = "";
		double percentValue = 0.0; 
		
		
		// must be a string or forumla like B6 = Hello There!
		
		if (split.length > 3) {
			textValue  = command.substring(4, command.length()).trim(); 
			if (textValue.charAt(0) == '(' && textValue.charAt(textValue.length() - 1) == ')') {
				double calculation = this.calculateFormula(textValue);
				this.setCellFormula(split[0], textValue, calculation);
				System.out.println(this.getGridText());
				return "Formula Set"; 
			}
		} else if (split.length == 3) {
			textValue = split[2]; 
			
			if (textValue.charAt(textValue.length() - 1) == '%') {
				hasPercent = true;
				percentValue = Double.parseDouble(textValue.substring(0, textValue.length() - 1)); 
			}
			
			if (textValue.matches("[0-9]+")) {
				intValue = Integer.parseInt(textValue.trim()); 
				hasInt = true; 	
			} else {
				hasString = true; 
			}
				
		}
		
		if (split.length == 1) {
			if (split[0].equals("quit")) { 
				System.out.println("Terminated"); 
				return null; 
			} else if (split[0].equals("clear")) {
				this.clearGrid(); 
				System.out.println(this.getGridText());
			}
			return ret; 
		} else if (split.length == 2) {
			if (split[0].equals("cell")) {
				ret = this.getCell(split[1]);
			} else if (split[0].equals("clear")) {
				this.clearCell(split[1]);
				System.out.println(this.getGridText());
			}
			return ret; 
		} else if (split.length >= 3) {
			if (hasInt) {
				this.setCellValue(split[0], intValue);
			} else if (hasPercent) {
				this.setCellPercent(split[0], percentValue);
			} else {
				this.setCellString(split[0], textValue );
			}
			System.out.println(this.getGridText());
			return ret;
		} else {
			return "Invalid Command"; 
		}
	}
	
	// commands
	public String getCell(String input) {
		String ret = " "; 
		for (int row = 0; row <= grid.length - 1; row++) {
			for (int column = 0; column <= grid[1].length - 1; column++) {
				if ((grid[row][column].getLocation()).equals(input)) {
					ret = grid[row][column].fullCellText(); 
					System.out.println(ret); 
				}
			}
		}
		return ret; 
	}
	
	public double calculateFormula(String formula) {
		double value = 0.0;
		String rawFormula = formula.substring(1, formula.length() - 1); 
		String[] splitRawFormula = rawFormula.split("\\s+");
		
		String locationToAddOrSub = "";
		double number = 0;
		double startNumber = 0;
		double currentCellNumber = 0;
		// index it and check each string if its something like (B6) or (+) or a integer
		for (int i = 0; i <= splitRawFormula.length - 1; i++) {
			String currentCellValue = ""; 
			if (splitRawFormula[i].matches("[A-Z][0-9]")) {
				if (splitRawFormula[0].matches("[A-Z][0-9]")) { // we need to do operations on this cell
					locationToAddOrSub = this.getCell(splitRawFormula[0]);
					startNumber = Double.parseDouble(locationToAddOrSub); 
				} else {
					currentCellValue = this.getCell(splitRawFormula[i]);
					currentCellNumber = Double.parseDouble(currentCellValue); 
				}
			} else if (splitRawFormula[i].matches("[0-9]+")) {
				number = Double.parseDouble(splitRawFormula[i]);
			} else if (splitRawFormula[i].equals("+")) {
				value += Double.parseDouble(splitRawFormula[i + 1]); 
				number = 0;
			} else if (splitRawFormula[i].equals("-")) {
				value -= Double.parseDouble(splitRawFormula[i + 1]); 
				number = 0;
			}
		}
		startNumber += value; 
		return startNumber; 
	}
	
	public void setCellString(String loc, String text) {
		for (int row = 0; row <= grid.length - 1; row++) {
			for (int column = 0; column <= grid[1].length - 1; column++) {
				if ((grid[row][column].getLocation()).equals(loc)) {
					grid[row][column] = new TextCell(text, grid[row][column].getLocation(), grid[row][column].returnLocationObject());
				}
			}
		}
	}
	
	public void setCellValue(String loc, int value) {
		for (int row = 0; row <= grid.length - 1; row++) {
			for (int column = 0; column <= grid[1].length - 1; column++) {
				if ((grid[row][column].getLocation()).equals(loc)) {
					grid[row][column] = new ValueCell(value, grid[row][column].getLocation(), grid[row][column].returnLocationObject());
				}
			}
		}
	}
	
	
	public void setCellPercent(String loc, double value) {
		for (int row = 0; row <= grid.length - 1; row++) {
			for (int column = 0; column <= grid[1].length - 1; column++) {
				if ((grid[row][column].getLocation()).equals(loc)) {
					grid[row][column] = new PercentCell(value, grid[row][column].getLocation(), grid[row][column].returnLocationObject());
				}
			}
		}
	}
	
	public void setCellFormula(String loc, String formula, double answer) {
		for (int row = 0; row <= grid.length - 1; row++) {
			for (int column = 0; column <= grid[1].length - 1; column++) {
				if ((grid[row][column].getLocation()).equals(loc)) {
					grid[row][column] = new FormulaCell(loc, formula, answer, grid[row][column].returnLocationObject()); 
				}
			}
		}
	}
	
	public void clearCell(String loc) {
		for (int row = 0; row <= grid.length - 1; row++) {
			for (int column = 0; column <= grid[1].length - 1; column++) {
				if ((grid[row][column].getLocation()).equals(loc)) {
					grid[row][column] = new EmptyCell(loc, grid[row][column].returnLocationObject()); 
				}
			}
		}
	}
	
	public void clearGrid() {
		for (int row = 0; row <= grid.length - 1; row++) {
			for (int column = 0; column <= grid[1].length - 1; column++) {
				grid[row][column] = new EmptyCell(grid[row][column].getLocation(), grid[row][column].returnLocationObject()); 
			}
		}
	}

	@Override
	public int getRows() {
		return this.rows;
	}

	@Override
	public int getCols() {
		return this.columns;
	}

	// 65 = A
	// 76 = L 
	@Override
	public String getGridText() {
		// Top letter row
		String ret = ""; 
		ret += "    |";
		for (int i = 1; i <= grid[1].length - 1;) {
			for (int j = 65; j <= 76; j++) {
				if (j == 65) 
					ret+= (char)(j) + "         "; 
				else 
					ret += "|" + (char)(j) + "         "; 
			}	
			break; 
		}
		
		ret += "\n"; 
		
		for (int i = 1; i <= grid.length - 1; i++) { // row start at A1
			if (i >= 10) 
				ret += i+"  |";
			else
				ret += i+"   |";
			for (int j = 0; j <= grid[1].length - 1; j++) { // col
				if (grid[i][j].returnLocationObject().getRow(i) >= 10 ) {
					ret += (grid[i][j].abbreviatedCellText() + "|"); 
				} else { 
					ret += (grid[i][j].abbreviatedCellText() + "|"); 
				}
				
			}
			ret += "\n"; 
		}
		
		return ret;
	}

}
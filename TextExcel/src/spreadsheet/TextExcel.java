package spreadsheet;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Update this file with your own code.

public class TextExcel
{

	public static void main(String[] args)
	{
		boolean isRunning = false; 
		Scanner console = new Scanner(System.in); 
		
		System.out.println("Please type 'run' to start Text Excel"); 
		String response = console.next(); 
		if (response.equals("run")) {
			isRunning = true; 
		} else {
			System.out.println("Quit"); 
		}
		Spreadsheet excel = new Spreadsheet(); 
		
		while (isRunning) {
			String command = console.nextLine();
			String process = excel.processCommand(command); 
			
			// Break out of loop
			if (process == null) 
				return; 
			
		}
	}
}

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * <Prog01_aOrderedList Class: Contains the main method which provides all of the actions for the user to do.
 * This class adds the user to enter an input file and output file and uses an object from the aOrderedList Class.
 * Reads the name of the users input file and checks to see whether or not it exists.
 * Asks the user for an output file and creates the output file with the sorted data that it manipulated from the input file.
 * Uses the attributes from the other classes in order to sort the data from the users input file to a new sorted output file.>
 * 
 * CSC 1351 Programming Project No <1>
 * Section <2>
 * 
 * @author <Hammaad Alam>
 * @since <March 17th, 2024>
 * 
 */

public class Prog01_aOrderedList {
	
	/**
	 * <Main Method: Prompts the user to enter a valid input file and then sorts the data from the input file. 
	 * It then asks the user to an enter the name of an output file and adds the sorted data to the newly created output file.
	 * Includes error checkers to check for any invalid files and data.>
	 * 
	 * CSC 1351 Programming Project No <1>
	 * Section <2>
	 * 
	 * @author <Hammaad Alam>
	 * @since <March 17th, 2024>
	 * 
	 */
	
	public static void main (String[] args) {
		try {
			aOrderedList orderedList = new aOrderedList(); // Creates new instance of aOrderedList;
			Scanner scanner = new Scanner(System.in); // Creates Scanner object
			Scanner inputFile = getInputFile("Enter input filename: "); // Prompts user to enter the name of the input file and gets it
			
			// Loop to read each line of data
			while(inputFile.hasNextLine()) {
				String line = inputFile.nextLine();
				String[] carData = line.split(",");
		
					// Checks to see if the command is "A" which means to add the carData
					if (carData[0].toLowerCase().equals("a")) {
						
						String make = carData[1];
						int year = Integer.parseInt(carData[2]);
	                    int price = Integer.parseInt(carData[3]);
	                    Car newCar = new Car(make, year, price); // Creates a new car object
	                    orderedList.add(newCar); // adds it to the ordered list
					}
					// Checks to see if the command is "D" which means to delete the carData
					else if (carData[0].toLowerCase().equals("d")) {
		                String make = carData[1];
		                int year = Integer.parseInt(carData[2]);
		                int index = orderedList.getObjectIndex(make, year);
		                if (index != -1) {
		                	 orderedList.remove(index); // removes the carData from the ordered list
		                }
					}
			}
			System.out.println("Enter output filename: "); // prompts user to enter name of output file

			String userOutputFile = scanner.nextLine();
			PrintWriter outputFile = getOutputFile(userOutputFile); // Gets the output file name from the user
			
			outputFile.println("Number of cars: " + orderedList.size()); // Outputs the number of cars in the ordered list
			outputFile.println();
			
			// Prints the data of each car into the ordered list in the sorted order
			for(int i = 0; i < orderedList.size(); i++) {
				Comparable Comparable = orderedList.get(i);
				Car car = (Car) Comparable;
				outputFile.println("Make: " + car.getMake());
				outputFile.println("Year: " + car.getYear());
				outputFile.println("Price: $" + car.getPrice());
				outputFile.println();
			}
			// close input and output files
			inputFile.close();
			outputFile.close();
		}
		// handles FileNotFoundException
		catch (FileNotFoundException e) {
			 System.out.println("Cancelled Program Execution");
		}
	}
	
	/**
	 * <getInputFile Method: Asks the user to enter the name of an input file and checks to see whether or not that file exists.
	 * Throws an exception if the file does not exist.>
	 * 
	 * CSC 1351 Programming Project No <1>
	 * Section <2>
	 * 
	 * @author <Hammaad Alam>
	 * @since <March 17th, 2024>
	 * 
	 */
	
	public static Scanner getInputFile(String UserPrompt) throws FileNotFoundException{
		
		Scanner scanner = new Scanner(System.in); // Creates scanner object
		String fileName;
		String choice;
			
		while (true) {
			System.out.print(UserPrompt);
			fileName = scanner.nextLine();
			File inputFile = new File(fileName);		
			
			// Checks if the inputFile exists
			if (inputFile.exists()) {
					return new Scanner(inputFile); // returns if it does exist
				}
				// Notifies the user and asks them if they want to continue if it does not exist
				else {
					System.out.println("File specified <" + fileName + "> does not exist. Would you like to continue? <Y/N>");
					choice = scanner.nextLine().toLowerCase();
					// Checks the choice of the user
					
					// if user wants to continue
					if(choice.equals("y")) {
						continue;
					} 
					// if user does not want to continue
					// Throws exception
					else {
						throw new FileNotFoundException("Cancelled Program Execution");
					}
				}
			}
		}
	
	/**
	 * <getOutputFile Method: Asks the user to enter the name of an output file which they want the sorted data to be placed into.
	 * Checks if the file path is valid.>
	 * 
	 * CSC 1351 Programming Project No <1>
	 * Section <3>
	 * 
	 * @author <Hammaad Alam>
	 * @since <March 17th, 2024>
	 * 
	 */
	
	 public static PrintWriter getOutputFile(String UserPrompt) throws FileNotFoundException{
		 	Scanner scanner = new Scanner(System.in); // Creates scanner object
			PrintWriter writer = null; // initializes writer as null
			boolean validInput = false; // initializes validInput as false
			
			// While the input path is not valid or false
			while(!validInput){
				try {
					String path = UserPrompt;
					writer = new PrintWriter(path);
					validInput = true;
				} 
				// Catches FileNotFoundException
				catch (FileNotFoundException e) {
					System.out.println("Invalid file path. Would you like to continue? <Y/N> ");
					String choice = scanner.nextLine().toLowerCase(); // Checks choice of user
					if (!choice.equals("y")) {
						throw e; // Throws exception if user does not want to continue
					}
				}
				
			}
			return writer;
	 }
}
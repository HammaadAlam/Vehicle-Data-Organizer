import java.util.Arrays;

/**
 * <aOrderedList Class: Provides functionality for creating a ordered list and changing the values within the list.
 * Provides many different methods for adding, removing, retrieving, and sorting the objects in the list.
 * Also has methods which iterate through the every index of the list in order to find certain objects.
 * Provides a string representation of the ordered list as well.>
 * 
 * CSC 1351 Programming Project No <1>
 * Section <2>
 * 
 * @author <Hammaad Alam>
 * @since <March 17th, 2024>
 * 
 */

public class aOrderedList {
	private final int SIZEINCREMENTS = 20; // Sets SIZEINCREMENTS to unchanged value of 20
	private Comparable[] oList; // Array to store objects
	private int listSize; // Size of array
	private int numObjects; // Number of objects in the list
	private int curr; //Current index
	
	/**
	 * <Constructor>
	 * 
	 * CSC 1351 Programming Project No <1>
	 * Section <2>
	 * 
	 * @author <Hammaad Alam>
	 * @since <March 17th, 2024>
	 * 
	 */
	
	public aOrderedList() {
		this.numObjects = 0;
		this.listSize = SIZEINCREMENTS;
		this.oList = new Car [SIZEINCREMENTS];
		this.curr = -1;
	}
	
	/**
	 * <add - Adds newObject object to the array in the correct position to maintain sorted order>
	 * 
	 * CSC 1351 Programming Project No <1>
	 * Section <2>
	 * 
	 * @author <Hammaad Alam>
	 * @since <March 17th, 2024>
	 * 
	 */
	
	public void add(Comparable<?> newObject) {
		int index = 0;
		// Checks if the array is full
	    if (numObjects == listSize) {
			Comparable[] newArray = new Comparable[listSize + SIZEINCREMENTS]; // resizes the array
			
			for (int i = 0; i < listSize; i++) {
				newArray[i] = oList[i];
			}
			
			oList = newArray;
			listSize += SIZEINCREMENTS;
		}
		
	    // Finds correct index to insert the new object in order to maintain a sorted array
		while (index < numObjects && oList[index].compareTo(newObject) < 0) {
			index++;
		}
		
		// shifts all other elements in order to insert new object
		for(int i = numObjects; i > index; i--) {
			oList[i] = oList[i - 1];
		}
		
		// insert object at the correct position
		oList[index] = newObject;
		numObjects++;
	}
	
	/**
	 * <size Method: returns the value of numObjects>
	 * 
	 * CSC 1351 Programming Project No <1>
	 * Section <2>
	 * 
	 * @author <Hammaad Alam>
	 * @since <March 17th, 2024>
	 * 
	 */
	
	public int size() {
		return numObjects;
	}
	
	/**
	 * <get Method: returns element at the specified index>
	 * 
	 * CSC 1351 Programming Project No <1>
	 * Section <2>
	 * 
	 * @author <Hammaad Alam>
	 * @since <March 17th, 2024>
	 * 
	 */
	
	public Comparable<?> get(int index) {
		return oList[index];
	}
	
	/**
	 * <isEmpty Method: returns true if array is empty and false otherwise>
	 * 
	 * CSC 1351 Programming Project No <1>
	 * Section <2>
	 * 
	 * @author <Hammaad Alam>
	 * @since <March 17th, 2024>
	 * 
	 */
	
	public boolean isEmpty() {
		return numObjects == 0;
	}
	
	/**
	 * <remove Method: removes elements at the specified index and moves the following elements down to fill the hole>
	 * 
	 * CSC 1351 Programming Project No <1>
	 * Section <2>
	 * 
	 * @author <Hammaad Alam>
	 * @since <March 17th, 2024>
	 * 
	 */
	
	public void remove(int index) {  
	// Checks if index is out of bounds and throws exception if it is
	if(index < 0 || index >= numObjects) {
			throw new IndexOutOfBoundsException("Index: " + index);
		}
		
		// shifts all the elements in the list in order to remove the object at the specified index
		for(int i = index; i < numObjects - 1; i++) {
			oList[i] = oList[i + 1];
		}
		
		oList[numObjects - 1] = null; // Sets new last element to null
		numObjects--; // Decrements the number of objects in the list after one has been removed
	}
	
	/**
	 * <reset Method: resets the parameters so the next element is now the first element>
	 * 
	 * CSC 1351 Programming Project No <1>
	 * Section <2>
	 * 
	 * @author <Hammaad Alam>
	 * @since <March 17th, 2024>
	 * 
	 */
	
	public void reset() {
		curr = 0;
	}
	
	/**
	 * <next Method: returns the value of the next method in the list and increments>
	 * 
	 * CSC 1351 Programming Project No <1>
	 * Section <2>
	 * 
	 * @author <Hammaad Alam>
	 * @since <March 17th, 2024>
	 * 
	 */
	
	public Comparable<?> next() {
		return oList[numObjects++];
	}
	
	/**
	 * <hasNext Method:returns true if there are more elements which need to be iterated through>
	 * 
	 * CSC 1351 Programming Project No <1>
	 * Section <2>
	 * 
	 * @author <Hammaad Alam>
	 * @since <March 17th, 2024>
	 * 
	 */
	
	public boolean hasNext() {
		return curr < numObjects && curr >= 0;
	}
	
	/**
	 * <getObjectIndex Method: gets the index of an object and check is they make and year are equal to any other object in the array>
	 * 
	 * CSC 1351 Programming Project No <1>
	 * Section <2>
	 * 
	 * @author <Hammaad Alam>
	 * @since <March 17th, 2024>
	 * 
	 */
	
	public int getObjectIndex(String make, int year) {
		for(int i = 0; i < size(); i++) {
			Car car = (Car) oList[i];
			if(car.getMake().equals(make) && car.getYear() == year) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * <toString Method: returns the objects in the specified format>
	 * 
	 * CSC 1351 Programming Project No <1>
	 * Section <2>
	 * 
	 * @author <Hammaad Alam>
	 * @since <March 17th, 2024>
	 * 
	 */
	
	@Override
	public String toString() {
	    StringBuilder values = new StringBuilder();
	    for (int i = 0; i < numObjects; i++) {
	        values.append("[").append(oList[i]).append(";]");
	        if (i < numObjects - 1) {
	            values.append(", ");
	        }
	    }
	    return values.toString();
	}
}

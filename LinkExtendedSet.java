import java.util.Arrays;
/**
 * 
 * @author Moon Bui
 *
 * @param <T>
 */

public class LinkExtendedSet<T> implements ExtendedSet<T> {
	
// Class fields
	private Node<T> head;
	private int size;
	private final int MAX_FREQUENCY;
	
	/**
	 * Workhorse constructor, takes in frequency to make
	 * a set with that maximum frequency
	 * @param frequency the maximum number of the same
	 * element in a LinkExtendedSet
	 */
	public LinkExtendedSet(int frequency) {
		MAX_FREQUENCY = frequency;
		Node<T> head = null;
	}
	
	/**
	 * Default constructor, frequency is 1
	 */
	public LinkExtendedSet() {
		MAX_FREQUENCY = 1;
		Node<T> head = null;
	}
	

	@Override
	/**
	 * Add method to add an element to the set
	 * Before adding, the method checks for the number of 
	 * that item in the set. Returns true and adds the item
	 * if the item isn't at max frequency in the set. Returns false otherwise
	 * @param item an object of class T
	 */
	public boolean add(T item) {
		// Check the items' frequency in the set
		int frequency = this.getFrequency(item);
		// Returns false if the item is at max frequency
		if (frequency == MAX_FREQUENCY)
			return false;
		
		// Add the item to the beginning of the set
		// Increase size by 1 and return true
		Node<T> stuff = new Node<T>(item);
		stuff.next = head;
		head = stuff;
		size++;
		return true;
	}

	/**
	 * Remove method to remove the first occurrence of an element from the set
	 * The method checks for the item in the set. Returns true and erases
	 * the item if the item is in the set. Returns false otherwise
	 * @param item an object of class T
	 */
	@Override
	public boolean remove(T item) {
		// Check for the first occurrence of the 
		// item in the set by calling private helper method
		// Returns false if the item is not in the set
		Node<T> temp = this.firstOcc(item);
		if (temp == null) 
			return false;
		
		// If the item is in the set, change the item
		// to an item in the first node and delete 
		// the first node. Decrease size by 1 and
		// return true
		temp.data = head.data;
		head = head.next;
		size--;
		return true;
	}

	/**
	 * Remove method to remove all occurrences of an element from the set
	 * The method checks for the item in the set. Returns true and erases
	 * all occurrences of the item if the item is in the set. Returns false otherwise
	 * @param item an object of class T
	 */
	@Override
	public boolean removeAll(T item) {
		// Check for the first occurrence of the
		// item in the set by calling private helper method
		// Returns false if the item is not in the set
		if (this.firstOcc(item) == null)
			return false;
		
		// Loop until all occurrences of the item
		// in the set is deleted (call for remove()
		// method in each loop)
		boolean check = true;
		while(check) {
			check = remove(item);
		}
		
		return true;
	} 

	/**
	 * Method to check for whether an item is in
	 * the set. Returns true if the item is found in the set,
	 * false otherwise
	 * @param item an object of class T
	 */
	@Override
	public boolean contains(T item) {
		// Calls private helper method
		// Returns false if the item returned
		// is null, true otherwise
		return (this.firstOcc(item) != null);
	}

	/**
	 * Method to return the number of times an item
	 * appears in a set
	 * @param item an object of class T
	 */
	@Override
	public int getFrequency(T item) {
		int count = 0;
		Node<T> temp = head;
		
		// Loops through the set and counts
		// all the occurrences of the item
		while(temp != null) {
			if (item != null && temp.data.equals(item))
				count++;
				temp = temp.next;
		}
		return count;
	}

	/**
	 * Method to return the size of the LinkExtendedSet
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * Method to return the number of different 
	 * elements in the set
	 */
	@Override
	public int uniqueCount() {
		int count = 0;
		
		// Declares a boolean value and
		// two nodes starting from the head
		boolean check = false;
		Node<T> curr = head;
		Node<T> temp = head;
		
		// Loops through the set
		while (curr != null) {
			// Nested loop to find all occurrences of the 
			// item from the left to the current position
			while(!temp.equals(curr)) {
				// Changes boolean value to true if there is
				// an occurrence of an item from the left
				if(temp.data.equals(curr.data))
					check = true;
				// Moves to the next node
				temp = temp.next;
			} // End of Nested loop
			
			// If the boolean value remains false
			// increases the count of unique numbers,
			// else change the boolean back to false
			if (check == false)
				count++;
			else
				check = false;
			
			// Resets the Node for the nested loop back to head
			// Moves to the next Node of the big loop
			temp = head;
			curr = curr.next;
		}
		
		return count;
	}

	@Override
	/**
	 * Method to check if the set is empty
	 * Returns true if the set is empty, false
	 * otherwise
	 */
	public boolean isEmpty() {
		return (size==0);
	}

	@Override
	/**
	 * Method to return the maximum number
	 * of occurrences allowed per item
	 */
	public int maxFrequency() {
		return MAX_FREQUENCY;
	}

	@Override
	/**
	 * Method to erase all elements in the set
	 * Set size back to 0
	 */
	public void clear() {
		head = null;
		size = 0;
	}

	@Override
	/**
	 * Method to return an array version 
	 * of the set
	 */
	public Object[] toArray() {
		Object[] resultArr = new Object[size];
		Node<T> curr = head;
		int i = 0;
		
		// Loops through the set and transfer the data
		// from the Node to an array
		while (curr != null) {
			resultArr[i] = curr.data;
			curr = curr.next;
			i++;
		}
		return resultArr; 
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * Method to return an array containing only 
	 * the unique items in the set
	 */
	public Object[] toUniqueArray() {
		// Create a default set (frequency = 1)
		LinkExtendedSet<T> result = new LinkExtendedSet<T>();
		// Create an array of the set with items
		Object[] items = this.toArray();
		// Move the items from the array into the unique set
		for (Object stuff: items) {
			result.add((T) stuff);
		}
		
		// Return result array
		Object[] resultArr = result.toArray();
		return resultArr;
	}

	@Override
	/**
	 * Method to return a sorted array
	 * version of the set
	 */
	public Object[] toSortedArray() {
		Object[] result = this.toArray();
		Arrays.sort(result);
		return result;
	}
	
	// Private helper method for contains() and remove()
	// Returns the node containing the first occurrence of an item
	// Returns null if the item is not in the set
	private Node<T> firstOcc(T item) {
		Node<T> temp = this.head;
		
		while(temp != null) {
			if (temp.data.equals(item))
				return temp;
			temp = temp.next;
		}
		
		return null;
	}
	
	// Private Node class
	private class Node<E> {
		private E data;
		private Node<E> next;
		
		public Node(E data) {
			this.data = data;
			this.next = null;
		}
	}
	
	// ToString() method to use in tester
//	public String toString() {
//		Node<T> curr = head;
//		String result = "";
//		while (curr != null) {
//			result+=curr.data+" ";
//			curr = curr.next;
//		}
//		return result;
//	}

}

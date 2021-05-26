/**
 * 
 * @author Moon Bui
 * This is a generic type class of a LinkedDeque that uses Nodes to 
 * implement DequeInterface
 * @param <T>
 */
public class LinkedDeque<T> implements DequeInterface<T> {
	// Class field
	private Node<T> head;
	
	// Default constructor 
	public LinkedDeque() {
		head = null;
	}

	/**
	 * Method to add item of type T to the front of
	 * the queue
	 * @param item Type T item that needs to be added 
	 */
	@Override
	public void addToFront(T item) {
		// If queue is empty, add item and 
		// wire the node to itself
		if (this.isEmpty()) {
			head = new Node(item);
			head.previous = head;
			head.next = head;
		}
		// Add item to the front, make it the head node
		// and wire the nodes accordingly
		else {
			Node<T> temp = new Node(item);
			temp.previous = head.previous;
			temp.next = head;
			head.previous.next = temp;
			head.previous = temp;
			head = temp;		
		}
		
	}

	/**
	 * Method to add item of type T to the front of
	 * the queue
	 * @param item Type T item that needs to be added 
	 */
	@Override
	public void addToBack(T item) {
		// If queue is empty, add item and 
		// wire the node to itself
		if (this.isEmpty()) {
			head = new Node(item);
			head.previous = head;
			head.next = head;
			
			// Add item to the back
			// and wire the nodes accordingly
			} else {
				Node<T> temp = new Node(item);
				temp.previous = head.previous;
				temp.next = head;
				head.previous.next = temp;
				head.previous = temp;
			}
		}

    /**
     * This method removes the Node that is at front
     * and makes the next node the head node
     * @return the data in the node that has been removed
     */
	@Override
	public T removeFront() {
		// Throws exception if the queue is empty
		if (this.isEmpty()) {
			throw new IllegalArgumentException("NoSuchElementException");
		}
	
		Node<T> temp = head;
		// Check if there is only one Node to remove and set it to null
		if (head.previous.equals(head) && head.next.equals(head)) {
			head = null;
			return temp.data;
		}
		
		// Rewires the Node to remove the first Node
		head.previous.next = head.next;
		head.next.previous = head.previous;
		head = head.next;
		
		
		return temp.data;
	}

	/**
     * This method removes the Node that is at back
     * and rewires the nodes accordingly
     * @return the data in the node that has been removed
     */
	@Override
	public T removeBack() {
		// Throws and exception if the queue is empty
		if (this.isEmpty()) {
			throw new IllegalArgumentException("NoSuchElementException");
		}
		Node<T> temp = head.previous; 
		
		// Checks if there is only one node to removed, if true
		// set the deque to null
		if (head.previous.equals(head) && head.next.equals(head)) {
			head = null;
			return temp.data;
		}
		// Removes the last node and rewires the other Node accordingly
		head.previous.previous.next = head;
		head.previous = head.previous.previous;
		return temp.data;
		
	}

	/**
	 * Method to show the first item
	 * @return the data of the first Node
	 */
	@Override
	public T getFront() {	
		// Throws exception if deque is empty
		if (this.isEmpty()) {
			throw new IllegalArgumentException("NoSuchElementException");
		}
		return head.data;
	}

	/**
	 * Method to show the last item
	 * @return the data of the last node
	 */
	@Override
	public T getBack() {
		// Throws exception if deque is empty
		if (this.isEmpty()) {
			throw new IllegalArgumentException("NoSuchElementException");
		}
		return head.previous.data;
	}

	/**
	 * Checks if deque is empty
	 * @return true is deque is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return (head == null);
	}

	/**
	 * Method to clear all the items in the deque
	 */
	@Override
	public void clear() {
		head = null;
	}
	
	/**
	 * Method to print all the items in the deque
	 * @return a string containing the data of all the
	 * nodes in the deque 
	 */
	public String toString() {
		String result = "FRONT TO BACK [";
		if (this.isEmpty()) {
			throw new IllegalArgumentException("NoSuchElementException");
		}
		
		Node<T> curr = head;
		// Loop to print from the first Node
		do {
			result+=curr.data + " ";
			curr = curr.next;
		} while (!curr.equals(head));
		
		result+="] BACK TO TOP [";
		curr = curr.previous;
		// Loop to print from the last Node
		do {
			result+=curr.data + " ";
			curr = curr.previous;
		} while (!curr.equals(head.previous));
		result+="]";
		return result;
	}
	
	// Private Node class
	private class Node<E> {
		@SuppressWarnings("unused")
		private E data;
		@SuppressWarnings("unused")
		private Node<E> next;
		@SuppressWarnings("unused")
		private Node<E> previous;
		
		@SuppressWarnings("unused")
		public Node (E data) {
			this.data = data;
			this.next = null;
			this.previous = null;
		}
	}

}

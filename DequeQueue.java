/**
 * @author Moon Bui 
 * This is a generic class type of DequeQueue that uses
 * LinkedDeque to implement QueueInterface 
 * @param <T>
 */
public class DequeQueue<T> implements QueueInterface<T> {
	// Class field
	private LinkedDeque<T> queue;
	
	// Default constructor
	public DequeQueue() {
		queue = new LinkedDeque<>();
	}
	
	/**
	 * Method to add a new item of type T
	 * to the back of the Queue
	 */
	@Override
	public void add(T newEntry) {
		queue.addToBack(newEntry);
		
	}

	/**
	 * Method to remove the very first item in the Queue
	 * @return the data in the very first node that is removed
	 */
	@Override
	public T remove() {
		return queue.removeFront();
	}

	/**
	 * Method to look at the very first item in the Queue
	 * @return the data in the very first node
	 */
	@Override
	public T peek() {
		return queue.getFront();
	}

	/**
	 * Method to check if the queue is empty
	 * @return true if it is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return queue.isEmpty();
	}

	/**
	 * Method to clear out all items 
	 * inside the queue
	 */
	@Override
	public void clear() {
		queue = null;
	}

}

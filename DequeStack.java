
/**
 * 
 * @author Moon Bui
 * This is a generic type class of DequeStack that uses LinkedDeque
 * to implement StackInterface
 * @param <T>
 */
public class DequeStack<T> implements StackInterface<T> {
	// Class field
	private LinkedDeque<T> deque;
	
	// Default constructor
	public DequeStack() {
		deque = new LinkedDeque<>();
	}

	/**
	 * Method to add items to the end of the stack
	 */
	@Override
	public void push(T item) {
		deque.addToBack(item);
		
	}

	/**
	 * Method to remove the last item in the stack
	 * @return the item being removed
	 */
	@Override
	public T pop() {
		return deque.removeBack();
	}

	/**
	 * Method to look at the last item in the stack
	 * @return the data of the last Node
	 */
	@Override
	public T peek() {
		// TODO Auto-generated method stub
		return deque.getBack();
	}

	/**
	 * Method to check if the stack if empty
	 * @return true if the stack is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (deque.isEmpty());
	}

	/**
	 * Method to clear out all items in the stack;
	 */
	@Override
	public void clear() {
		deque = null;	
	}
}

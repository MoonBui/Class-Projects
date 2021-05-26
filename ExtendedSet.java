/**
 * Similar to a set, but allows for limited repeats. For example,
 * an extended set might allow for at most 3 occurrences of its items.
 * If a set allowed at most one occurrence of each item, then it would
 * be a typical set.
 * 
 * @author Norm Krumpe
 *
 */
public interface ExtendedSet<T> {
    
    /**
     * Adds the specified item to this extended set
     * @param item the item to be added
     * @return true if the item is successfully added, and false otherwise. For example,
     * false would be returned if the item already appears in this extended set the maximum
     * number of allowable times.
     */
    public boolean add(T item);
    
    /**
     * Removes at one occurrence of the specified item from this extended set
     * @param item the item to be removed
     * @return true if the item is successfully removed, and false otherwise.
     */
    public boolean remove(T item);
    
    /**
     * Removes all occurrences of the specified item from this extended set
     * @param item the item to be removed
     * @return true if at least one occurrence of the item was removed, and false otherwise
     */
    public boolean removeAll(T item);
    
    /**
     * Returns whether the specified item is in this extended set
     * @param item the item to be checked
     * @return true if the item is in this extended set at least once, and false otherwise.
     */
    public boolean contains(T item);
    
    /**
     * Returns the number of times the specified item is in this extended set
     * @param item the item to be checked
     * @return the number of occurrences of the item
     */
    public int getFrequency(T item);
    
    /**
     * Returns the number of items in this extended set
     * @return the number of items in this extended set
     */
    public int size();
    
    /**
     * Returns the number of unique items in this extended set, so that
     * if an item occurs more than once, only one of its occurrences is counted
     * @return the number of unique items in this extended set
     */
    public int uniqueCount();
    
    /**
     * Returns whether this extended set is empty
     * @return true if this extended set is empty, and false otherwise
     */
    public boolean isEmpty();
    
    /**
     * Returns the maximum allowable frequency for items in this extended set.
     * @return the maximum allowable frequency for items in this extended set.
     */
    public int maxFrequency();
    
    /**
     * Removes all items from this extended set
     */
    public void clear();
    
    /**
     * Returns an array of all the items in this extended set
     * @return an array of all the items in this extended set
     */
    public Object[] toArray();
    
    /**
     * Returns an array of all the items in this extended set, but without duplicates
     * @return an array of all the items in this extended set, without duplicates
     */
    public Object[] toUniqueArray();
    
    /**
     * Returns an array of all the items in this extended set, but sorted based
     * on how the objects are meant to be sorted
     * @return a sorted array of all the items in this extended set
     */
    public Object[] toSortedArray();
    

}

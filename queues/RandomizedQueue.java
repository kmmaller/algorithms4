/*----------------------------------------------------------------
 *  Author:        Kara Maller
 *  Written:       13/1/2017
 *  Last updated:  13/1/2017
 *
 *  Compilation:   javac RandomizedQueue.java
 *  Execution:     java RandomizedQueue
 *  
 *  Implements a data type similar to a stack or queue, except that the 
 *  item removed is chosen uniformly at random from items in the data structure. 
 * 
 *----------------------------------------------------------------*/
import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

    
public class RandomizedQueue<Item> implements Iterable<Item> {
    private int n; // size of the stack
    private Item[] array;

    /**
     * construct an empty deque
     */
    public RandomizedQueue() {
        n = 0;
        array = (Item[]) new Object[2];
        
    }
   /** 
     * is the deque empty?
     */
    public boolean isEmpty() {
        return n == 0;
    }
   /** 
     * return the number of items on the queue
     */
    public int size() {
        return n;
    }
    
    // resize array to make room for more items 
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++)
            copy[i] = array[i];
        array = copy;
    }
    /*
     * add item to the stack
     */
    public void enqueue(Item item) {
        if (item == null) throw new java.lang.NullPointerException();
        if (n == array.length) resize(2*array.length);
        array[n++] = item;
    }
    /**
      * remove and return a random item
      */
    public Item dequeue() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int rand = StdRandom.uniform(n);
        Item item = array[rand];
        Item lastItem = array[n-1];
        array[rand] = lastItem;
        array[n-1] = null;
        n--;
        if (n > 0 && n == array.length/4) resize(array.length/2);
        return item;
    }
    /**
     * return, but not remove, random item
     */
    public Item sample() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        return array[StdRandom.uniform(n)];
    }
   /**
    * return an independent iterator over items in random order
    */
    public Iterator<Item> iterator() {
        return new RandomIterator();   
    }
   
    private class RandomIterator implements Iterator<Item> {
        private int current = 0;
        private int[] indices = new int[n];
        public RandomIterator() { 
            for (int i = 0; i < n; i++)
                indices[i] = i;
            StdRandom.shuffle(indices);
        }
        public boolean hasNext() { return current < n; }
        public void remove() { throw new UnsupportedOperationException(); }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return array[indices[current++]];
        }
    }
 
       
   /**
    * unit testing (optional)
    */
    public static void main(String[] args) {
        RandomizedQueue<String> s = new RandomizedQueue<String>();
        s.enqueue("large");
        s.enqueue("behind");
        //System.out.println(s.dequeue());
        //System.out.println(s.size());
        System.out.println(s.iterator());
        
      
    }
}
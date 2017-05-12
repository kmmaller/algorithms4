/*----------------------------------------------------------------
 *  Author:        Kara Maller
 *  Written:       13/1/2017
 *  Last updated:  13/1/2017
 *
 *  Compilation:   javac Deque.java
 *  Execution:     java Deque
 *  
 *  Data type for a double ended queue that suports adding and 
 * removing items from either the front or of the back end of the
 * data structure
 *----------------------------------------------------------------*/
import java.util.Iterator;
import java.util.NoSuchElementException;
    
public class Deque<Item> implements Iterable<Item> {
    private int n; // size of the stack
    private Node first; // top of the stack
    private Node last; // last of the stack
   
   // helper double linked list class
    private class Node { 
        private Item item;
        private Node next;
        private Node prev;
    }
    /**
     * construct an empty deque
     */
    public Deque() {
        first = null;
        last = null;
        n = 0;
        
    }
   /** 
     * is the deque empty?
     */
    public boolean isEmpty() {
        return n == 0;
    }
   /** 
     * return the number of items on the deque
     */
    public int size() {
        return n;
    }
   /** 
     * add the item to the front
     */
    public void addFirst(Item item) {
        if (item == null) throw new java.lang.NullPointerException();
        Node oldFirst = first;
        first = new Node();
        first.prev = null;
        first.item = item;
        if (isEmpty()) {
            first.next = null;
            last = first;
        }
        else {
            first.next = oldFirst;
            oldFirst.prev = first;
        }
        n++;
    }
   /**
    * add item to the end
    */
    public void addLast(Item item) {
        if (item == null) throw new java.lang.NullPointerException();
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
            last.prev = null;
        }
        else {
            oldlast.next = last;
            last.prev = oldlast;
        }
        n++;   
    }
   /**
    * remove and return the item from the front    
    */
    public Item removeFirst() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        Item item = first.item;
        if ( n == 1 ) {first = null; last = null;}
        else {
            first = first.next;
            first.prev = null;
        }
        n--;
        return item;
    }
   /**
    * remove and return the item from the end
    */
    public Item removeLast() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        Item item = last.item;
        if ( n == 1) { first = null; last = null; }
        else{
            last = last.prev;
            last.next = null;
        }
        n--;
        return item;
    }
   /**
    * return an iterator over items in order from front to end
    */
    public Iterator<Item> iterator() {
        return new ListIterator();   
    }
   
    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() { return current != null; }
        public void remove() { throw new UnsupportedOperationException(); }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
 
       
   /**
    * unit testing (optional)
    */
    public static void main(String[] args) {
        Deque<String> s = new Deque<String>();
        s.addFirst("whats");
        //s.addFirst("up");
        //s.addLast("dude");
        //System.out.println(s.isEmpty());
        //System.out.println(s.size());
        
        //System.out.println(s.removeLast());
        System.out.println(s.iterator());
      
    }
}
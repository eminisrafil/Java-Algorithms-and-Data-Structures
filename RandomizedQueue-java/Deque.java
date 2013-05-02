import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
   //public Deque()                     // construct an empty deque
   //public boolean isEmpty();         // is the deque empty?
   //public int size()                  // return the number of items on the deque
   //    // insert the item at the front
  // public void addLast(Item item)     // insert the item at the end
   //public Item removeFirst()          // delete and return the item at the front
   //public Item removeLast()           // delete and return the item at the end
   //public Iterator<Item> iterator()   // return an iterator over items in order from front to end

       ///iterator implementation should support the operations next() and hasNext()
       ///Throw a java.lang.NullPointerException if the client attempts to add a null item; throw a java.util.NoSuchElementException if the client attempts to remove an item from an empty deque; throw a java.lang.UnsupportedOperationException if the client calls the remove() method in the iterator; throw a java.util.NoSuchElementException if the client calls the next() method in the iterator and there are no more items to return.

    private int N;          // size of the stack
    private Node first;     // Position 0 of stack
    private Node last;      // Position N-1 of Stack
    private Node debug;

    // helper linked list class
    private class Node {
        private Item item;
        private Node next = null;
        private Node previous = null;
    }
    
    public Deque(){
        N = 0;
        first = null;
        last = null;
        debug = new Node();
        debug.item = (Item) "fuck that, you empty stack";
    }
    
    public int size(){
        return N;
    }
    
    public boolean isEmpty(){
        return first == null;
    }
    
    public void addFirst(Item item){
        StdOut.println("Add First");
        if(first == null) {
            first = new Node();
            first.item = item;
            first.next = null;
            first.previous = null;
            last = first;
            N++;
            StdOut.println("Message: First was equal to NULL.");
            test();
            return;
        }
        
        Node oldFirst = first; 
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.previous = null;
        oldFirst.previous = first;
        N++;
        
        if(first.next == null){
           last = first;
           last.next = null;
        }
        test();
        
    }
    
    public Item removeFirst(){
        StdOut.println("Remove First");       
        if (isEmpty()){
            // new NoSuchElementException("The Queue is empty you big dummy");
            return debug.item;
        }
        Item item =first.item;
        if (first.next != null){
            first = first.next;
            first.previous = null;
        } else {
            first = null;
            last = null;
        }
        N--;
        test();
        return item;
    }
    
    public void addLast(Item item){
        StdOut.println("add last");        
        if(first == null || last == null) {
            first = new Node();
            first.item = item;
            last = first;
            N++;
            return;
        }
        Node newLast = new Node();
        newLast.item = item;
        newLast.previous = last;
        last.next = newLast;
        last = newLast;
        last.next = null;
        N++;
        test();
    }
    
    public Item removeLast() {
        StdOut.println("remove last");
        if (isEmpty()){
            // new NoSuchElementException("The Queue is empty you big dummy");
            return debug.item;
        }
        Item lastItem = last.item;
        if(last.previous != null && first.next != null){
            last = last.previous;
            last.next = null;
        } else{
            last = null;
            first = null;
        }
        N--;
        test();
        return lastItem;
    }    
    
    /**
     * Return an iterator to the stack that iterates through the items in LIFO order.
     */
    public Iterator<Item> iterator()  { return new ListIterator();  }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        //change to private not
        public Node current = first;
        public boolean hasNext()  { return current.next != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
     }

     public void test() {
         boolean empty = (first == null)? true : false;
         StdOut.println("Is Empty: " + empty);
         StdOut.println("Stack count: " +N);
         StdOut.print("Stack: First ~~~~> ");
         if (N >0){
             Iterator iter =  iterator();
             while(iter.hasNext() ){
                 StdOut.print(iter.next());
                 StdOut.print(" | ");
             }
         }
         if(last != null){
             StdOut.print(last.item);
         }
         StdOut.println(" <~~~~ Last");           
    }
    
    
    public static void main(String[] args) {
        Deque<String> s = new Deque<String>();
        //                      TESTING
        //
        //             KEY      FUNCTION        ACTION 
        //               -    removeFirst();    Remove First Entry in Stack
        //               /    removeLast();     Remove Last Entry in Stack
        //               +    addLast();        Add "+" to Last Position of Stack
        //(any other char)    addFirst();       Add String to First Position of Stack
        while (1>0) {
           String item = StdIn.readString();
           if (item.equals("/")){
               StdOut.println(s.removeLast());
           }
           if(item.equals("+")){
               s.addLast(item);
           }
            if (!item.equals("-") && !item.equals("/") && !item.equals("+") ) s.addFirst(item);
            else if (!item.equals("/") && !item.equals("+")) StdOut.println(s.removeFirst());
        }
    }
}




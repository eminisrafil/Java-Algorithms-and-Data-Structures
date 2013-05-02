import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
public class RandomizedQueue<Item> implements Iterable<Item> {
   //+public RandomizedQueue()           // construct an empty randomized queue
   //+public boolean isEmpty()           // is the queue empty?
   //+public int size()                  // return the number of items on the queue
   //+public void enqueue(Item item)     // add the item
   //public Item dequeue()              // delete and return a random item
   //public Item sample()               // return (but do not delete) a random item
  // public Iterator<Item> iterator()   // return an independent iterator over items in random order
    
    private int N;          // size of the stack
    private Item[] stack;  //stack

        // create an empty stack
    public RandomizedQueue() {
        stack = (Item[]) new Object[2];
    }

    public boolean isEmpty() { return N == 0; }
    public int size()        { return N;      }
    
    // resize the underlying array holding the elements
    private void resize(int capacity) {
        //do if else and auto resize
        assert capacity >= N;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = stack[i];
        }
        stack = temp;
    }
    
    public Item dequeue() {
        if(isEmpty()){
            throw new NoSuchElementException("The Queue is empty you big dummy");
        }
        //get random number
        //save value at the spot
        //place value equal to last 
        //set last = 0
        //decrement size 
        //return saved value
    }

    // push a new item onto the stack
    public void enqueue(Item item) {
        if (N == stack.length) resize(2*stack.length);    // double size of array if necessary
        stack[N++] = item;                            // add item
    }
    
        // delete and return the item most recently added
    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = stack[N-1];
        stack[N-1] = null;                              // to avoid loitering
        N--;
        // shrink size of array if necessary
        if (N > 0 && N == stack.length/4) resize(stack.length/2);
        return item;
    }
    
    public Item sample() {
        int rand = StdRandom.uniform(N-1);
        return stack[rand];
    }
    

    public Iterator<Item> iterator()  { return new RandomizedQueueIterator();  }

    // an iterator, doesn't implement remove() since it's optional
    private class RandomizedQueueIterator implements Iterator<Item> {
        private int size;
        private Item[] randomizedStack;
        

        public RandomizedQueueIterator() {
            size = N;
            randomizedStack = (Item[]) new Object[N];
               

            for(int x = 0; x < size; x++){
                randomizedStack[x] = stack[x];
            }
            StdRandom.shuffle(randomizedStack); 
            
            for(Item x : randomizedStack){
                StdOut.println(x);
            }
            
            
        }
        /*//knuth shuffle directly into new array
            //generates new random array and leaves the original untouched
            //Linear time ~N
            //opposed to copy array ~N then shuffle ~N = ~2N 
            
           for(int i = 0; i< size; i++){
                int r = StdRandom.uniform(i+1);
                swapInto(stack, randomizedStack, i, r);
            }
         * private Item[] swapInto(Item[] a, Item[] newA, int i, int r){
            StdOut.println("swapping : " + a[i] + " and " + a[r] );
            Item temp;
            newA[i] = a[r];
            newA[r] = a[i];
            return newA;
        }
        */
        public boolean hasNext() {
            return size > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item first = randomizedStack[0];
            
            return first;
        }
    }



   /***********************************************************************
    * Test routine.
    **********************************************************************/
    public static void main(String[] args) {
        RandomizedQueue<String> s = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if(item.equals("+")){
                Iterator iter1 = s.iterator();
                //StdOut.println(s.sample());
            }
            if (!item.equals("-") && !item.equals("+")) s.enqueue(item);
            else if (!s.isEmpty() && !item.equals("+")) StdOut.print(s.pop() + " ");
        }
        StdOut.println("(" + s.size() + " left on stack)");
    }
}


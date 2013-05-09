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
    private void resizeCheck() {  
        if(N == stack.length){
            resize(N*2);
        }
        if (N > 0 && N == stack.length/4){
            resize((stack.length/2));
        }
    }
    
    private void resize(int capacity) {  
        StdOut.println("resizing array : " + capacity);
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
      
        int rand =  (N>1)? StdRandom.uniform(0, N-1) : 0;
        Item random = stack[rand];
        stack[rand] = stack[N-1]; 
        stack[N-1] = null;
        N--;
        resizeCheck();
        test();
        return random;
    }

    // enqueue a new item onto the stack
    public void enqueue(Item item) {
        if(item == null)
            throw new NullPointerException("You can't add a Null item to the Stack, you big dummy");
        resizeCheck();   // double size of array if necessary
        stack[N++] = item;   // add item
        test();
    }
    
    public Item sample() {
        int rand = StdRandom.uniform(N-1);
        return stack[rand];
    }
    

    public Iterator<Item> iterator()  { return new RandomizedQueueIterator();  }

    // an iterator, doesn't implement remove() since it's optional
    private class RandomizedQueueIterator implements Iterator<Item> {
        private int i;
        private Item[] randomizedStack;
        
        public RandomizedQueueIterator() {
            i = N;
            randomizedStack = (Item[]) new Object[N];

            for(int x = 0; x < i; x++){
                randomizedStack[x] = stack[x];
            }
            StdRandom.shuffle(randomizedStack); 
            
            //test delete
            StdOut.print("Randomized Stack Initialized with array:" );
            for(Item x : randomizedStack){
                StdOut.print(x);
            }
            StdOut.println("<~~Random Array Initialized");     
        }

        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext() && i !=0) throw new NoSuchElementException();
            i--;
            return randomizedStack[i];

        }
    }



   /***********************************************************************
    * Test routine
    **********************************************************************/
    
     public void test() {
       StdOut.println("Stack count: " +N);
       for(Item x : stack){
           StdOut.print(x);
           StdOut.print(" | ");
       }
       StdOut.println(" ");
       StdOut.println("----------------");          
    }

    
    public static void main(String[] args) {
        RandomizedQueue<String> s = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if(item.equals("+")){
                StdOut.println("Size of the Stack is : " + s.size());
                Iterator iter1 = s.iterator();
                Iterator iter2 = s.iterator();
                for(int i = 0; i <s.size() ; i++) {
                    StdOut.print(iter1.next());
                    StdOut.print("  |");
                    StdOut.print(iter2.next());
                    StdOut.print("|  ");
                }
            }
            if(item.equals("/")){
                StdOut.println(s.dequeue());
            }
            if (!item.equals("-") && !item.equals("+") && !item.equals("/")) s.enqueue(item);
            else if (!s.isEmpty() && !item.equals("+") && !item.equals("/")) StdOut.print(s.dequeue() + " ");
        }
        StdOut.println("(" + s.size() + " left on stack)");
    }
}
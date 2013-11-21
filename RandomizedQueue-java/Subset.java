/*************************************************************************
 * Name: Emin Israfil
 * Email:EminIsrafil@gmail.com
 *
 * Description: Subset.java that takes a command-line integer k, reads 
 * in a sequence of N strings from standard input using StdIn.readString(), 
 * and prints out exactly k of them, uniformly at random. Each item from the 
 * sequence can be printed out at most once. You may assume that k ³ 0 and no
 * greater than the number of string on standard input.
 *
 *************************************************************************/


public class Subset{
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> stack = new RandomizedQueue<String>();
        
        while (!StdIn.isEmpty()) {
           String item = StdIn.readString();
           stack.enqueue(item);
        }
        
        for(int i = 0; i<k; i++){
            StdOut.println(stack.dequeue());
        }
    }
}
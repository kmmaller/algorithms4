/*----------------------------------------------------------------
 *  Author:        Kara Maller
 *  Written:       13/1/2017
 *  Last updated:  13/1/2017
 *
 *  Compilation:   javac Permutation.java
 *  Execution:     java Permutation
 *  
 *  takes a command-line integer k; reads in a sequence of strings from standard
 * input using StdIn.readString(); and prints exactly k of them, 
 * uniformly at random
 * 
 *----------------------------------------------------------------*/

import edu.princeton.cs.algs4.StdIn;

    
public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        String[] input = StdIn.readAllStrings();
        int strLen = input.length;
        RandomizedQueue<String> s = new RandomizedQueue<String>();
        for (int i = 0; i < strLen; i++) { 
            s.enqueue(input[i]);
        }
        
        int j = 0;
        while (j < k) {
            System.out.println(s.dequeue());
            j++;
        }
    }
            
}
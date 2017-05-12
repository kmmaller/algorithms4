/*----------------------------------------------------------------
 *  Author:        Kara Maller
 *  Written:       13/1/2017
 *  Last updated:  13/1/2017
 *
 *  Compilation:   javac PercolationStats.java
 *  Execution:     java PercolationStats
 *  
 *  Performs a series of computational experiments to estimate
 *  the percolation threshold
 *
 *----------------------------------------------------------------*/
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdIn;

public class PercolationStats {
    
    private int size;
    private int t;
    private double[] runs;
    /**
     * perform trials independent experiments on an n-by-n grid
     */
    public PercolationStats(int n, int trials) {
        size = n;
        t = trials;
        if (size <= 0) throw new IllegalArgumentException("n argument out of bounds");  
        if (t <= 0) throw new IllegalArgumentException("trials argument out of bounds");
        runs = new double[t];
        for (int i = 0; i < t; i++) {
            Percolation perc = new Percolation(size);
            while (!perc.percolates()) {
                perc.open(StdRandom.uniform(size)+1, StdRandom.uniform(size)+1);
            }
            runs[i] = (perc.numberOfOpenSites());
        }
    }
    /**
     * sample mean of percolation threshold
     */
    public double mean() { return StdStats.mean(runs)/(size*size); }                         
    /**
     * sample standard deviation of percolation threshold
     */
    public double stddev() { return StdStats.stddev(runs)/(size*size); }                 
    /**
     * low  endpoint of 95% confidence interval
    */
    public double confidenceLo() { return mean() - 0.95 * stddev()/Math.sqrt(t); }         
    /**
     * high endpoint of 95% confidence interval
     */
    public double confidenceHi() { return mean() + 0.95 * stddev()/Math.sqrt(t); }              

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats test = new PercolationStats(n, t);
        System.out.println("mean = " + test.mean());
        System.out.println("std dev = " + test.stddev());
        System.out.println("95% confidence interval = " +test.confidenceLo() + ", " + test.confidenceHi());
        
              
    }       
}
/*----------------------------------------------------------------
 *  Author:        Kara Maller
 *  Written:       13/1/2017
 *  Last updated:  13/1/2017
 *
 *  Compilation:   javac Percolation.java
 *  Execution:     java Percolation
 *  
 *  Use weighted union find to test if a system percolations for a 
 *  given grid size NxN.
 *
 *----------------------------------------------------------------*/
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] opensites;
    private int top = 0;
    private int bottom;
    private int size;
    private WeightedQuickUnionUF qfArray;
    private WeightedQuickUnionUF qfBackwash;
    private int opencount = 0;
/**
 * Percolation created the n x n grid with all sites blocked. initiatlized 
 * sites on top and bottom
 */
    public Percolation(int n)  {
        size = n;
        if (size <= 0) throw new IllegalArgumentException();
        bottom = size*size + 1;
        qfArray = new WeightedQuickUnionUF(size*size + 2);
        qfBackwash = new WeightedQuickUnionUF(size*size + 1);
        opensites = new boolean[size+1][size+1];
        
    }
/**
 * open opens the site (row,col) if its not already opened.    
 */
   
    public void open(int row, int col)  {
        validate(row, col);
        if (!isOpen(row, col)) {
            opensites[row][col] = true;
            opencount = opencount + 1;
        }
        if (row == 1) {
            qfArray.union(xyto1d(row, col), top);
            qfBackwash.union(xyto1d(row, col), top);
        }
        if (row == size) {
            qfArray.union(xyto1d(row, col), bottom);
        }
        if (isOpen(row-1, col) && row > 1) {
            qfArray.union(xyto1d(row, col), xyto1d(row-1, col));
            qfBackwash.union(xyto1d(row, col), xyto1d(row-1, col));
        }
        if (row < size && isOpen(row+1, col)) {
            qfArray.union(xyto1d(row, col), xyto1d(row+1, col));
            qfBackwash.union(xyto1d(row, col), xyto1d(row+1, col));
        }
        if (isOpen(row, col-1) && col > 1) {
            qfArray.union(xyto1d(row, col), xyto1d(row, col-1));
            qfBackwash.union(xyto1d(row, col), xyto1d(row, col-1));
        }
        if (col < size && isOpen(row, col+1)) {
            qfArray.union(xyto1d(row, col), xyto1d(row, col+1));
            qfBackwash.union(xyto1d(row, col), xyto1d(row, col+1));
        }
    }     
 /** 
  * isOpen returns the state of (row,col) in the 2d grid
  */
    public boolean isOpen(int row, int col) {
        //validate(row, col);
        return opensites[row][col];
    }
 /*
  * checks if (row, col) is connected to the top row
  */
    public boolean isFull(int row, int col) {
        validate(row, col);
        return qfBackwash.connected(xyto1d(row, col), top); 
    }
 /**
  * returns number of sites that have been opened
  */
    public int numberOfOpenSites() {
        return opencount;
    }
 /**
  * returns true is bottom is connected to the top
  */
    public boolean percolates() {
        return qfArray.connected(top, bottom);
    }
/**
  *  xyto1d: converted indices (row,col) in a 2D NxN grid to an index (i) in a N array
  */
    private    int xyto1d(int row, int col) {
        return col + size*(row-1);
    }
   /**
  *  validate: checks if indicies are valid, throws in exception of invalid
  */
    private    void validate(int row, int col) {
        if (row <= 0 || row > size) throw new IndexOutOfBoundsException("row index out of bounds");
        if (col <= 0 || col > size) throw new IndexOutOfBoundsException("column index out of bounds");
    }
   
    private int checkarray() {
        return qfArray.count();
    }
 
    public static void main(String[] args)  {
        Percolation test = new Percolation(5);
        test.open(1, 1);
        test.open(1, 2);
        test.open(2, 2);
        test.open(3, 2);
        test.open(3, 3);
        test.open(4, 3);
        test.open(5, 3);
        System.out.println(test.checkarray());
        //System.out.println(test.isOpen(1,1));
        System.out.println(test.numberOfOpenSites());
      
    }
}
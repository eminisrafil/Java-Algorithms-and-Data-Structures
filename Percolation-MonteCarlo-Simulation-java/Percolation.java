
/************************************************************************
 *  Compilation:  javac Percolation.java
 *  Execution:  java WeightedQuickUnionUF < input.txt
 *  Dependencies: StdIn.java StdOut.java WeightedQuickUnionUF
 *
 *  Emin Israfil
 *
 *  Monte Carlo Simulation To Estimate Percolation Threshold
 *
 *  Percolation: Given a composite systems comprised of randomly distributed insulating 
 *  and metallic materials: what fraction of the materials need to be metallic so that the
 *  composite system is an electrical conductor?
 * 
 *  The model: We model a percolation system using an N-by-N grid of sites. Each site is either
 *  open or blocked. A full site is an open site that can be connected to an open site in the
 *  top row via a chain of neighboring (left, right, up, down) open sites. We say the system 
 *  percolates if there is a full site in the bottom row.
 *
 ************************************************************************/
   //public Percolation(int N)              // create N-by-N grid, with all sites blocked
   //public void open(int i, int j)         // open site (row i, column j) if it is not already
   //public boolean isOpen(int i, int j)    // is site (row i, column j) open?
   //public boolean isFull(int i, int j)    // is site (row i, column j) full?
   //public boolean percolates()            // does the system percolate?

import java.util.Random;
public class Percolation {
    public int gridSize;
    public boolean[][] grid;
    private WeightedQuickUnionUF flatGrid;
    private WeightedQuickUnionUF backWashGrid; //only fill in blocks connected to the top
    
    public Percolation(int x) {
        this.gridSize = x; 
        this.grid = new boolean[gridSize][gridSize];
        this.flatGrid = new WeightedQuickUnionUF(this.gridSize*this.gridSize + 2);
        this.backWashGrid = new WeightedQuickUnionUF(this.gridSize*this.gridSize + 2);
    }

    public void open(int i, int j){
        validate(i,j);
        
        //check if site is already open
        if (this.grid[i-1][j-1] == true){
            return;
        }
        
        //open site and convert to linear array point
        grid[i-1][j-1] = true;
        int p = xyTo1d(i-1,j-1);
        
        //Could be Switch statement - Connect to site on right, left,up,down
        if(i < this.gridSize && isOpen(i+1, j)){
            this.flatGrid.union((p+1), p);
            this.backWashGrid.union((p+1), p);
        }
        if(i > 1 && isOpen(i-1, j)){
            this.flatGrid.union((p-1), p);
            this.backWashGrid.union((p-1), p);
        }
        if(j > 1 && isOpen(i, j-1)){
            this.flatGrid.union((p-this.gridSize), p);
            this.backWashGrid.union((p-this.gridSize), p);
        }
        if(j < this.gridSize && isOpen(i, j+1)){      
           this.flatGrid.union((p+this.gridSize), p);
           this.backWashGrid.union((p+this.gridSize), p);
        }

        //connect top row to top virtual site
        if(i ==1){   
           this.flatGrid.union(p, 0);
           this.backWashGrid.union(p,0);
        }
        //Connect bottom row to bottom site 
        if(i==gridSize){
            this.flatGrid.union(p,this.gridSize*this.gridSize + 1);
        }
    }
    
    public boolean percolates(){
        if (this.flatGrid.connected(0, this.gridSize * this.gridSize+1)) {
            return true;
        } else{
            return false;
        }
    }
    
    private int xyTo1d(int i, int j){
        return (j*this.gridSize-1) +i +1;
    }   
    
    public boolean isOpen(int i, int j){
        return this.grid[i-1][j-1];    
    }
    
    public boolean isFull(int i, int j) {
        int p = xyTo1d(i-1, j-1);
        return this.backWashGrid.connected(0, p);
    } 

    private void validate(int i, int j) {   
        if(i < 1 || j <1 || i >this.gridSize || j > this.gridSize) {
            throw new IndexOutOfBoundsException("That number is out of bounds you big dummy");
        }
    }
}
/************************************************************************
 *  Compilation:  javac PercolationStats.java
 *  Execution:  java WeightedQuickUnionUF int N  int T
 *  Dependencies: StdIn.java StdOut.java WeightedQuickUnionUF Percolation
 *
 * 
 *
 ************************************************************************/

/*public class PercolationStats {
   public PercolationStats(int N, int T);    // perform T independent computational experiments on an N-by-N grid
   public double mean();                  // sample mean of percolation threshold
   public double stddev();                   // sample standard deviation of percolation threshold
   public double confidenceLo();             // returns lower bound of the 95% confidence interval
   public double confidenceHi();             // returns upper bound of the 95% confidence interval
   public static void main(String[] args);   // test client, described below
}/*/
import java.util.Random;

public class PercolationStats{
    private double iterations[];

    
    public static void main(String[] args){
        int N = Integer.parseInt(args[0]); 
        int T = Integer.parseInt(args[1]);
        PercolationStats percStats = new PercolationStats(N,T);
        StdOut.println("Mean: " + percStats.mean());
        StdOut.println("Standard Deviation: " + percStats.stddev());
        StdOut.println("Upper 95% confidence: " + percStats.confidenceHi());
        StdOut.println("Lower 95% confidence: " + percStats.confidenceLo());
    }
    
    public PercolationStats(int N, int T){
        if (N<1 || T<1) {
            throw  new java.lang.IllegalArgumentException("N(gridsize) and T(computations) should be > 0, you big dummy.");
        }
        this.iterations = new double[T];
        for(int i =0; i<T; i++){
            Percolation perc = new Percolation(N);
            double opened= 0;
            
            while(!perc.percolates()){
                int x = 1 + StdRandom.uniform(N);
                int y = 1 + StdRandom.uniform(N);
                while(perc.isOpen(x,y)){
                    x = 1 + StdRandom.uniform(N);
                    y = 1 + StdRandom.uniform(N);
                }
                
                perc.open(x,y);
                opened++;
            }
            StdOut.println(opened);
            this.iterations[i]  = opened/(N*N); 
        }
    }
        
    public double stddev(){
        if(this.iterations.length >1) {
            return StdStats.stddev(this.iterations);
        } else {
            return Double.NaN;
        }
    }
        
    public double mean(){
        return StdStats.mean(this.iterations);
    }
    
    public double confidenceLo(){
        return mean() - 1.96* stddev()/Math.sqrt(this.iterations.length);
    } 
    
    public double confidenceHi(){
        return mean() + 1.96* stddev()/Math.sqrt(this.iterations.length);
    }
    

    
}  
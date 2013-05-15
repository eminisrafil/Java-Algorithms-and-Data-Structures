/*************************************************************************
 * Name: Emin Israfil
 * Email:EminIsrafil@gmail.com
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new bySlope();      
    /*The SLOPE_ORDER comparator should compare points by the slopes they make with 
     * the invoking point (x0, y0). Formally, the point (x1, y1) is less than the point
     * (x2, y2) if and only if the slope (y1 ? y0) / (x1 ? x0) is less than the slope
     * (y2 ? y0) / (x2 ? x0). Treat horizontal, vertical, and degenerate line segments 
     * as in the slopeTo() method.
     * 
     * 
     */ 
    public class bySlope implements Comparator<Point>
    {
        public int compare(Point a, Point b){
            if(a ==null || b == null)
                throw new NullPointerException("Point equals null, you big dummy");
        
        double slopeA = slopeTo(a);
        double slopeB = slopeTo(b);
        
        if      (slopeA < slopeB) return -1;
        else if (slopeA > slopeB) return  1;
        else return 0;
        }
    }
    
    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }
    
    //delete
    public int getY(){ return this.y;}

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        /* YOUR CODE HERE
         * The slopeTo() method should return the slope between the invoking point (x0, y0)
         * and the argument point (x1, y1), which is given by the formula (y1 ? y0) / (x1 ? x0).
         * Treat the slope of a horizontal line segment as positive zero [added 7/29];
         * treat the slope of a vertical line segment as positive infinity;
         * treat the slope of a degenerate line segment (between a point and itself) 
         * as negative infinity.
         */
        if(that == null)
                throw new NullPointerException("Point equals null, you big dummy");
        
        if(that.x == this.x){
            if(that.y == this.y){
            //StdOut.print("Vertical line");
                return Double.NEGATIVE_INFINITY;
            }
            return Double.POSITIVE_INFINITY;
        }
        if(that.y == this.y){
            //StdOut.print("Horizontal line");
            double a = 1.0;
            return (a-a)/+a;
        }
        return new Double ((that.y - this.y)).doubleValue()/new Double (that.x - this.x).doubleValue();
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        /* YOUR CODE HERE 
         * The compareTo() method should compare points by their y-coordinates,
         * breaking ties by their x-coordinates. Formally, the invoking point (x0, y0) 
         * is less than the argument point (x1, y1) if and only if either y0 < y1 or if y0 = y1 
         * and x0 < x1.
         */
        if(that ==null)
            throw new NullPointerException("Point is null, you big dummy");
        
        if(this.y > that.y || (this.y == that.y && this.x > that.x)) return 1;
        if(this.y < that.y || (this.y == that.y && this.x < that.x)) return -1;       
        return 0;
        
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {

        String filename = args[0];
        /*Test if draw methods are working  
        rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        */    
        In in = new In(filename);
        int N = in.readInt();
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            int x2 = in.readInt();
            int y2 = in.readInt();
            
            Point p = new Point(x, y);
            Point p2 = new Point(x2, y2);
            
            StdOut.println("p: " + p.toString() + "    p2: " + p2.toString());
            StdOut.println((p.compareTo(p2)==1)? "              p  >  p2 " : "              p  <=  p2");
            StdOut.println("Slope p->p2: " + p.slopeTo(p2) + "  " + (p.slopeTo(p2) == p2.slopeTo(p)));
            StdOut.println("------------------------------------------------------------");           
        
            /*test is draw methods are working
            p.draw();
            p2.draw();
            p.drawTo(p2);
            StdDraw.show(0);
            */
        }

    }
}
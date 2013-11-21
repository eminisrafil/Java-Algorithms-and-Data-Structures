/*************************************************************************
 * Name: Emin Israfil
 * Email:EminIsrafil@gmail.com
 *
 * Compilation:  javac Brute.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: Brute.java that examines 4 points at a time and 
 * checks whether they all lie on the same line segment,
 * printing out any such line segments to standard output
 * and drawing them using standard drawing.
 *
 *************************************************************************/


import java.util.Arrays;
public class Brute{
    
    public Brute(Point stack[], int N) {
       //Sort to Natural Order - Makes it easier to draw a line to the 4-tuples endpoints
       Arrays.sort(stack);

        double slopeIJ;
        double slopeJK;
        double slopeKL;

        for (int i = 0; i<N; i++){
            
            for (int j = i+1; j<N; j++){
                slopeIJ = stack[i].slopeTo(stack[j]);
                
                for (int k = j+1; k<N; k++){
                    slopeJK = stack[j].slopeTo(stack[k]);
                    //continue only if the first 3 points are colinear
                    if(slopeIJ == slopeJK){                     
                        for (int l = k+1; l<N; l++){
                            slopeKL = stack[k].slopeTo(stack[l]);
                            //StdOut.println("SLopeIJ: " + slopeIJ + " SlopeJK: " + slopeJK + " SlopeKL: " + slopeKL);
                            if(slopeJK == slopeKL && slopeJK == slopeIJ && slopeKL == slopeIJ){
                                StdOut.println(stack[i] + "  ->  " + stack[j] + "  ->  "  + stack[k] + "  ->  " + stack[l]);
                                StdOut.println(i +" "+ j +" "+ k + " "+ l );
                                stack[i].draw();
                                stack[j].draw();
                                stack[k].draw();
                                stack[l].draw();
                                stack[i].drawTo(stack[l]);
                                StdDraw.show(0);
                            }
                        }
                    }
                }
            }       
        }
        StdOut.println("Thank you, come again");
    }
    
    
    public static void main(String[] args) {
        String filename = args[0]; 
        //rescale coordinates and turn on animation mode
        
        StdDraw.setXscale(0, 36000);
        StdDraw.setYscale(0, 36000);
        StdDraw.show(0);
            
        In in = new In(filename);
        int N = in.readInt();
        Point stack[] = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();   
            Point p = new Point(x, y);
            stack[i] = p;
        }
        Brute brute = new Brute(stack, N);
    }
}
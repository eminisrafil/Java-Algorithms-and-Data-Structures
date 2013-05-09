/*Brute.java that examines 4 points at a time and 
  checks whether they all lie on the same line segment,
  printing out any such line segments to standard output
  and drawing them using standard drawing. 
*/
import java.util.Arrays;
public class Brute{
    
    public Brute() {
    
    }
    
    
    public static void main(String[] args) {
        String filename = args[0];
        //Test if draw methods are working  
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
            
            //StdOut.println("p: " + p.toString());
            //StdOut.println("------------------------------------------------------------");           
        
            /*test is draw methods are working
            p.draw();
            p2.draw();
            p.drawTo(p2);
            StdDraw.show(0);
            */
        }
        StdOut.println("stack:");
        for(Point i : stack)
            StdOut.print(i);
        
        
       // Point origin = new Point(0,0);
       // origin.draw();
        
        Arrays.sort(stack);

        StdOut.println("Partially ordered stack:");
        for(Point i : stack){
           // StdOut.println(i.getY());
            //i.draw();
            //origin.drawTo(i);
            //StdDraw.show(500);
        }
        double slopeIJ;
        double slopeJK;
        double slopeKL;
        
        //if(slopeIJ != current) K = N;
        
        for (int i = 0; i<N; i++){
            for (int j = 0; j<N; j++){
                slopeIJ = stack[i].slopeTo(stack[j]);
                
                for (int k = 0; k<N; k++){
                    slopeJK = stack[j].slopeTo(stack[k]);
                    if(slopeIJ == slopeJK && i != j && j!=k && i != k){
                         
                        for (int l = 0; l<N; l++){
                            slopeKL = stack[k].slopeTo(stack[l]);
                            //StdOut.println("SLopeIJ: " + slopeIJ + " SlopeJK: " + slopeJK + " SlopeKL: " + slopeKL);
                            if(slopeJK == slopeKL && slopeJK == slopeIJ && slopeKL == slopeIJ  && i != j && j!=k && i != k && l != k && l != j && l != i ){
                                //StdOut.println("Point " +i + " " + stack[i] + " ~> " + j + " " + stack[j] + " ~> " + k + " " + stack[k] + " ~> " + l + " " + stack[l]);
                                StdOut.println(i +" "+ j +" "+ k + " "+ l );
                                stack[i].draw();
                                stack[l].draw();
                                stack[i].drawTo(stack[l]);
                                StdDraw.show(0);
                                l = N;
                                j = N;
                                k = N;
                                
                            }
                        }
                    }
                }
            }
        
        }
    }
}
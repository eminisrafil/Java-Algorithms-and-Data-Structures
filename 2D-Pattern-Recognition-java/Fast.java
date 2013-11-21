/*************************************************************************
 * Name: Emin Israfil
 * Email:EminIsrafil@gmail.com
 *
 * Description: Brute.java that examines 4 points at a time and 
 * checks whether they all lie on the same line segment,
 * printing out any such line segments to standard output
 * and drawing them using standard drawing.
 *
 *************************************************************************/

import java.util.Arrays;

public class NewFast{
    
    public void display(Point line[],Point origin){
        //copy lineOrigin-1 so that it can be replaced by origin
        int len = line.length;
        line[0] =origin;
        
        Arrays.sort(line);
        for(int i = 0; i< len-1; i++){
            StdOut.print(line[i] + " -> ");
        }
        StdOut.println(line[len-1]);
        
        line[0].drawTo(line[len-1]);
        StdDraw.show(0);
    }
    
    
    public NewFast(Point pointsArray[], int N){
      
      Arrays.sort(pointsArray);
      Point sortedPoints[] = pointsArray.clone();

      for(int p = 0; p<N; p++){
          Point point = pointsArray[p];
          Arrays.sort(sortedPoints, point.SLOPE_ORDER);
          
          double prevSlope = Double.NaN;
          double currentSlope = Double.NaN;
          double repeatSlope = point.slopeTo(point);
          int lineOrigin =1;
          for(int q = 1; q<N; q++){
              prevSlope = point.slopeTo(sortedPoints[q-1]);
              currentSlope = point.slopeTo(sortedPoints[q]);

              if(prevSlope != currentSlope){
                  if(q-lineOrigin >2){
                      display(Arrays.copyOfRange(sortedPoints, lineOrigin-1, q), point); 
                  }
                  lineOrigin = q;   
              }
              
              //Points array is arranged in natural accending order. So,
              //we ignore any points below the current point because we have already discovered
              //all the lines on them. 
              if(point.compareTo(sortedPoints[q])<0){
                  repeatSlope = point.slopeTo(sortedPoints[q]);
              }
              
              
              if(repeatSlope == currentSlope){
                  //no possible line was found in this iteration
                  lineOrigin = q+1;
              }   
              
          }
          //catch if last point on the line was N-1
          if(N-lineOrigin >2){
              display(Arrays.copyOfRange(sortedPoints, lineOrigin-1, N), point); 
          }
      }
    }
    
    
    
    
    public static void main(String[] args) {
        String filename = args[0]; 
        //rescale coordinates and turn on animation mode
        
        StdDraw.setXscale(0, 38000);
        StdDraw.setYscale(0, 38000);
        StdDraw.show(0);
            
        In in = new In(filename);
        int N = in.readInt();
        Point stack[] = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();   
            Point p = new Point(x, y);
            p.draw();
            stack[i] = p;
        }
        NewFast fast = new NewFast(stack, N);
    }
}
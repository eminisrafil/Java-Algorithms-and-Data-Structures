public class Board {
    //public Board(int[][] blocks)           // construct a board from an N-by-N array of blocks
                                           // (where blocks[i][j] = block in row i, column j)
    //public int dimension()                 // board dimension N
    //public int hamming()                   // number of blocks out of place
    //public int manhattan()                 // sum of Manhattan distances between blocks and goal
    //public boolean isGoal()                // is this board the goal board?
    //public Board twin()                    // a board obtained by exchanging two adjacent blocks in the same row
    //public boolean equals(Object y)        // does this board equal y?
    //public Iterable<Board> neighbors()     // all neighboring boards
    //public String toString()               // string representation of the board (in the output format specified below)
    private int[][] currentBoard;
    private int size;
    private int zeroCol;
    private int zeroRow;
    private int hamming;
    private int manhattan;
   
    public Board(int[][] blocks){
        size = blocks.length;
        currentBoard =new int[size][size];
        for(int i = 0; i <size; i++){
            for(int j = 0; j<size; j++){
                currentBoard[i][j] = blocks[i][j];
                if(currentBoard[i][j] == 0)
                    setZero(i,j);
            }
        }
    }
    private void setZero(int i, int j){
       this.zeroRow = i;
       this.zeroCol = j;
       StdOut.println("Zero at row: " +i +" col: " +j);
    }
    
    public int dimension(){
        return size;
    }
    
    public int hamming(){
        //need to implement iterable so you can do for eachp
    }

}
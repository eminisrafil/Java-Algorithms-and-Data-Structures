public class Solver {
    //public Solver(Board initial)            // find a solution to the initial board (using the A* algorithm)
    //public boolean isSolvable()             // is the initial board solvable?
    //public int moves()                      // min number of moves to solve initial board; -1 if no solution
    //public Iterable<Board> solution()       // sequence of boards in a shortest solution; null if no solution
    //public static void main(String[] args)  // solve a slider puzzle (given below)
    // you will want to use comparator for hamming & man
    //MinPq<node> pq as the priority queue

    private Node goalNode; 
    private MinPQ<Node> pq = new MinPQ<Node>();
    private MinPQ<Node> pqTwin = new MinPQ<Node>();  
    
    public class Node implements Comparable<Node>{
        public Board board;
        public Node previous;
        public int moves;

        public int compareTo(Node that){
            //StdOut.println("i:" + this.priority() + " j:" + that.priority() + " "+ ((this.priority() > that.priority()) ? 1 :  -1));
            if(this.priority() == that.priority()) return 0;
            return (this.priority() > that.priority()) ? 1 :  -1; 
        }
        
        public Node(Board b, Node prev, int m){
            board = b;
            previous = prev;
            moves = m;
        }
        
        public int priority(){
            return board.manhattan() + moves;
        } 
    } 
    
    public Solver(Board initial){
        Board initialBoard;
        Queue<Board> neighbors = new Queue<Board>();
        initialBoard = initial;

        Node currentNode = new Node(initial, null, 0);
        Node currentTwin = new Node(initial.twin(), null, 0);
        pq.insert(currentNode);
        pqTwin.insert(currentTwin);
        
        while(!currentNode.board.isGoal() && !currentNode.board.isGoal()){

            currentNode = pq.delMin();
            currentTwin = pqTwin.delMin();
                     
            for(Board b : currentNode.board.neighbors()) {
                if(!b.equals(currentNode.board))
                    pq.insert(new Node(b, currentNode, currentNode.moves +1)); 
            }
            
            for(Board b : currentTwin.board.neighbors()) {
                if(!b.equals(currentNode.board))
                    pqTwin.insert(new Node(b, currentTwin, currentTwin.moves +1)); 
            }
        
        }
        
        if(currentNode.board.isGoal())
            goalNode = currentNode;
        else
            goalNode = currentTwin;
        
    }
    
    public Iterable<Board> solution(){
        Queue<Board> trace = new Queue<Board>();
        trace.enqueue(goalNode.board);
        while (goalNode.previous != null){
            goalNode = goalNode.previous;
            trace.enqueue(goalNode.board);
        }
        
        return trace;
    }
    
    public boolean isSolvable(){
        return goalNode != null;
    }
    
    public int moves(){
        return goalNode.moves;
    }
    
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
            blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        
        // solve the puzzle
        Solver solver = new Solver(initial);
        
        
        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);      
        }
    }
}

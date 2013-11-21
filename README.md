Java - Algorithms and Data Structures
====

- **Percolation: Implement Monte Carlo Simulation To Estimate Percolation Threshold**  
  *Given a composite systems comprised of randomly distributed insulating and metallic 
  materials: what fraction of the materials need to be metallic so that the composite 
  system is an electrical conductor?*

  The model: We model a percolation system using an N-by-N grid of sites. Each site is
  either open or blocked. A full site is an open site that can be connected to an open 
  site in the top row via a chain of neighboring (left, right, up, down) open sites. 
  We say the system percolates if there is a full site in the bottom row.

  The problem: If sites are independently set to be open with probability p (and therefore
  blocked with probability 1 − p), what is the probability that the system percolates?

- **Randomized Queues and Deques**  
  *Write generic data types for a deque and a randomized queue, 
  implement data structures using arrays, linked lists, generics and iterators.*

  Dequeue: Implement a double-ended queue or deque, a stack and a queue that supports
  inserting and removing items from either the front or the back of the data structure.

  Randomized queue: Implement a randomized queue, similar to a stack or queue, except 
  that the item removed is chosen uniformly at random from items in the data structure.
  Your implementation should support each randomized queue operation (besides creating an
  iterator) in constant amortized time and use space proportional to the number of items 
  currently in the queue.

- **Pattern Recognition - Recognize linear patterns in a given set of points.**  
  *Given a set of N distinct points in the plane, draw every (maximal) line segment 
  that connects a subset of 4 or more of the points.*

  Point: Create an immutable data type Point that represents a point in the plane.

  Brute force: Examines 4 points at a time and checks whether they all lie on the
  same line segment, printing out any such line segments to standard output and drawing
  them using standard drawing.The order of growth of the running time of your program 
  should be N4 in the worst case and it should use space proportional to N.

  Fast: Implement a scorting algorithm that accomplishes the same thing as Brute,
  but the order of growth of the running time of your program should be N2 log N 
  in the worst case and it should use space proportional to N.

- **8 Puzzle: Solve the 8-puzzle problem using the A* search algorithm.**  
  *The 8-puzzle problem is a puzzle played on a 3-by-3 grid with 8 square 
  blocks labeled 1 through 8 anda blank square. The goal is to rearrange the blocks so that
  they are in order, using as few moves as possible.*

  Use priority queues with the Hamming & Manhattan priority functions to create a solver test client for your game.

Long Project 0 README file

Team name: G95
Team Members:
Jithendhiralal Ramlal - jgr150030
Ramakrishnan Sathyavageeswaran - rxs142530
Thiagarajan ramakrishnan - txr150430 
####################################################################################################

The submission consist of 5 files.

Vertex.java
Edge.java
Graph.java
Euler.java
README.txt

This program implements Hierholzer's algorithm and finds the Euler tour for a given undirected graph. 

We have used stack datastructure to find a cycles from unseen edge and merge the actual euler tour with the help of LinkedList Datastructure. 
When running for a larger input (used the sample input ) average running time of our implementation took 3 seconds.
Sample Input:
6 10
1 2 1
1 3 1
1 4 1
1 6 1
2 3 1
3 6 1
3 4 1
4 5 1
4 6 1
5 6 1

Sample Output:

(1,2)
(2,3)
(3,6)
(4,6)
(4,5)
(5,6)
(1,6)
(1,3)
(3,4)
(1,4)
Time taken to find the Euler Tour is 0.0001 seconds
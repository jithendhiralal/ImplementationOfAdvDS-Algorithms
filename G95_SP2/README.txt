Short Project 1 README file

Team name: G95
Team Members:
Jithendhiralal Ramlal - jgr150030
Ramakrishnan Sathyavageeswaran - rxs142530
Thiagarajan ramakrishnan - txr150430 
####################################################################################################

The submission consist of 8 files.

Vertex.java
Edge.java
Graph.java
a) SP2_a_TopologicalSort.java
b) SP2_b_DiameterOfTree.java
c) SP2_c_Kosaraju.java
d) SP2_d_OddLengthCycle.java
e) SP2_e_TestEulerian.java
README.txt

a) Topological Sort:
This is a program of two algorithms for ordering the nodes of a DAG topologically. Both algorithms will print graph has cycles if the given graph is not a DAG. To test this program, please give the no of vertices and no of edges and then give the edges in (u,v) form with weights.

Sample Input:
Enter no of Vertices
3
Enter no of Edges
2
Vertex u : Vertex v : Weight w
1 2 1 
2 3 1

Sample Output:
Toplological Order: Algorithm 1
1
2
3
Toplological Order: Algorithm 2
1
2
3

b) Diameter of a tree:
This is a program to find a diameter of tree with maximum distance from the root node.

Sample Input:
Enter no of Vertices
10
Enter no of Edges
9
Vertex u : Vertex v : Weight w
1 2 1
1 3 1
1 4 1
4 5 1
4 7 1
4 9 1
5 6 1
7 8 1
9 10 1

Sample Output:
Diameter of Tree is 4

c) Strongly Connected Component:
This is a program for finding strongly connected components of a directed graph. For this program input is hardcoded. Hardcoded input is 

No.of vertices: 10
Edges:
1 0
0 2
2 1
0 3
3 4
6 7
7 8
8 9
9 6
5 1

Sample Output:
Strongly Connected Components
6 9 8 7
5
0 1 2
3
4
Number of Strongly connected components are 5.

d) Finding an odd-length cycle in a non-bipartite graph.
This is a program to find a odd length cycle in a non bipartite graph.

Sample Input:
Enter no of Vertices
4
Enter no of Edges
4
Vertex u : Vertex v : Weight w
1 2 1
2 3 1
2 3 1
4 3 1

Sample Output:
Odd length Cycle is  4 3 2

e) Test Eulerian Graph:
This is a program to test whether the given graph is eulerian. And also it checks whether the graph is connected.
To test this program, please give the no of vertices and no of edges and then give the edges in (u,v) form with weights.

Sample Input:
Enter no of Vertices
3
Enter no of Edges
2
Vertex u : Vertex v : Weight w
1 2 1 
2 3 1

Sample Output:
Graph has an Eulerian path between vertices 1 and 3.

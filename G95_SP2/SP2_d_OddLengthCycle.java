import java.io.*;
import java.util.*;
import java.lang.Exception;

/**
 * Class that represents how to find the an odd-length cycle 
 * in a non-bipartite graph
 * 
 */
public class SP2_d_OddLengthCycle {
    public static void main(String[] args) throws FileNotFoundException {
	Scanner in;
	if (args.length > 0) {
	    File inputFile = new File(args[0]);
	    in = new Scanner(inputFile);
	} else {
	    in = new Scanner(System.in);
	}
        
	Graph g = Graph.readGraph(in, false);   // read undirected graph from stream "in"
        
        List<Vertex> oddLCycle = OddLengthCycle(g);
        if(oddLCycle != null){
            System.out.print("Odd length Cycle is ");
            for(Vertex x : oddLCycle){
                System.out.print(x + " ");

            }
        }
        
    }
    /**
     * Method to find the odd length cycle in the non bipartite graph
     * @param g : Graph 
     * @return 
     */
    public static List<Vertex> OddLengthCycle(Graph g){
        
        List<Vertex> list = new LinkedList<>();
        
        Queue<Vertex> Q = new LinkedList<>();

	for (Vertex u : g) {
	    u.seen = false;
	    u.parent = null;
	    u.distance = Integer.MAX_VALUE;
	}
        
        // Step 1: Run BFS to find the Bipartite
        // Run BFS on every component
	for (Vertex src : g) {
	    if (!src.seen) {
		src.distance = 0;
		Q.add(src);
		src.seen = true;
                
		while (!Q.isEmpty()) {
		    Vertex u = Q.remove();
		    for (Edge e : u.Adj) {
			Vertex v = e.otherEnd(u);
			if (!v.seen) {
			    v.seen = true;
			    v.parent = u;
			    v.distance = u.distance + 1;
			    Q.add(v);
			}
                        
                        // Step 2: if two adjacent nodes connected in same level
                        else if (u.distance == v.distance) {
                                // Get the cycle 
				list = getOddLengthCycle(u,v);
                                return list;
			}
		    }
		}
	    }
	}
        System.out.println("Graph is bipartite");
        return null;
    }
    
    
    /**
     * Method to combining the edge (u,v) with the paths from u and v to
     * their least common ancestor in the BFS tree and return it as a list
     * @param u : Vertex 
     * @param v : Vertex
     * @return 
     */
    private static List<Vertex> getOddLengthCycle(Vertex u, Vertex v) {
        
               
        List<Vertex> list1 = new LinkedList<>();
        List<Vertex> list2 = new LinkedList<>();
        
        list1.add(u);
        list2.add(v);
        
        Vertex u1 = u.parent;
        Vertex v1 = v.parent;
        
        // Step 3: Find the cycle and add them to a list
        while(u1 != v1){
            list1.add(u1);
            list2.add(v1);
            u1 = u1.parent;
            v1 = v1.parent; 
        }
        list1.add(u1);
        
        Collections.reverse(list2);
        list1.addAll(list2);
        return list1;
        
    }
}
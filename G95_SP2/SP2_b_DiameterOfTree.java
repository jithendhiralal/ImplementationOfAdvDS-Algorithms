import java.io.*;
import java.util.*;
import java.lang.Exception;

/**
 * Class that represent how to find a Diameter of the Tree which might not be a
 * Binary tree.
 *
  */
public class SP2_b_DiameterOfTree {

    /**
     * Method to find if a graph is cyclic or not
     *
     * @param g : Graph - To check if it is cyclic
     * @return True if it is cyclic else False
     */
    public static Boolean isCyclic(Graph g) {

        for (Vertex v : g) {
            v.seen = false;
            v.parent = null;
        }
        // Running a DFS to check for cycle
        for (Vertex v : g) {
            if (!v.seen) {
                if (isCyclicUtil(v, v.parent)) {
                    return true;
                }
            }
        }
        return false;

    }

    /**
     * Private Util Method used by isCyclic method which takes vertex v and
     * check with their adjacent ‘other’ such that other is already visited and
     * other is not parent of v, then it detects their is a cycle in graph and
     * return True
     *
     * @param v : Vertex
     * @param parent : Vertex - the parent of the vertex v
     * @return True if it is cyclic else False
     */
    private static boolean isCyclicUtil(Vertex v, Vertex parent) {
        v.seen = true;
        v.parent = parent;
        for (Edge e : v.Adj) {
            Vertex other = e.otherEnd(v);
            if (!other.seen) {
                if (isCyclicUtil(other, v)) {
                    return true;
                }
            } else if (other != parent) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to find a diameter of un rooted tree
     * @param g : Graph
     * @return : int - return -1 if the given graph is not tree (or) - return
     * the diameter of the tree
     */
    public static int diameter(Graph g) {
        // Step 1 : Check if a graph is tree or not
        if (isCyclic(g)) {
            return -1;
        }

        // Step 2 : Pass a random node to a BFS and get a Vertex 
        Random ran = new Random();
        int noOfVertex = g.verts.size();
        int randomNode = ran.nextInt((noOfVertex - 1) + 1) + 1;
        Vertex node = breathFirstSearch(g, g.verts.get(randomNode));

        //Step 3: Get the random node and do the BFS again
        node = breathFirstSearch(g, node);

        return node.distance;
    }

    /**
     * Method to run BFS to traverse until its leaf node
     *
     * @param g
     * @param root
     * @return leaf Vertex along with distance
     */
    public static Vertex breathFirstSearch(Graph g, Vertex root) {

        for (Vertex v : g) {
            v.seen = false;
            v.parent = null;
            v.distance = Integer.MAX_VALUE;
        }

        Queue<Vertex> queue = new LinkedList<Vertex>();
        root.seen = true;
        root.distance = 0;
        queue.add(root);
        Vertex maxDistNode = null;
        while (!queue.isEmpty()) {
            Vertex current = queue.remove();

            for (Edge e : current.Adj) {
                Vertex other = e.otherEnd(current);
                if (!other.seen) {
                    other.seen = true;
                    other.distance = current.distance + 1;
                    other.parent = current;
                    queue.add(other);
                }
            }
            maxDistNode = current;
        }
        return maxDistNode;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in;
        if (args.length > 0) {
            File inputFile = new File(args[0]);
            in = new Scanner(inputFile);
        } else {
            in = new Scanner(System.in);
        }
        Graph g = Graph.readGraph(in, false); // read undirected graph from stream "in"

        System.out.print("Diameter of Tree is " + diameter(g));

    }
}

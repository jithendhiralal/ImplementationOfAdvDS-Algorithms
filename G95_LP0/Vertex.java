

/**
 * Class to represent a vertex of a graph
 *
 *
 */
import java.util.*;

public class Vertex {

    public int name; // name of the vertex
    public boolean seen; // flag to check if the vertex has already been visited
    public Vertex parent; // parent of the vertex
    public int distance; // distance to the vertex from the source vertex
    public List<Edge> Adj, revAdj; // adjacency list; use LinkedList or ArrayList
    public int indegree;
    public int outdegree;
    public int degree;
    public int top;
    public int status;
    public int index;

    /**
     * Constructor for the vertex
     *
     * @param n : int - name of the vertex
     */
    Vertex(int n) {
        name = n;
        seen = false;
        parent = null;
        Adj = new ArrayList<Edge>();
        revAdj = new ArrayList<Edge>();
        /* only for directed graphs */
        indegree = 0;
        outdegree = 0;
        degree = 0;
        top = 0;
        status = 0;
        index = 0;
    }

    public Edge getUnvisitedEdge() {
        if (!isUnvisited()) {
            return null;
        }
        Edge e = Adj.get(index++);
        while (e.seen && isUnvisited()) {
            e = Adj.get(index);
            index++;
        }
        // If Still edge is seen return null
        if (e.seen) {
            return null;
        }
        return e;
    }

    public boolean isUnvisited() {
        return index < this.Adj.size();
    }

    /**
     * Method to represent a vertex by its name
     */
    public String toString() {
        return Integer.toString(name);
    }
}

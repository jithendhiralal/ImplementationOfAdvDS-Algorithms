package G95_LP2;

/**
 * Class to represent a vertex of a graph
 *
 *
 */
import java.util.*;

public class Vertex implements Index, Comparator<Vertex> {

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

    public int rank;

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

        rank = 0;
    }

    /**
     * Method to represent a vertex by its name
     */
    public String toString() {
        return Integer.toString(name);
    }

    // set the index value
    public void putIndex(int idx) {
        index = idx;
    }

    //get the index value
    public int getIndex() {
        return index;
    }

    public int compare(Vertex a, Vertex b) {
        return a.distance - b.distance;
    }
}

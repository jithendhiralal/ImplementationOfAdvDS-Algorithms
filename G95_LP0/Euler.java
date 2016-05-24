
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Euler {

    /**
     * This method is used to check whether the graph has Euler Circuit/Path and
     * accordingly it will return the List of Edges
     *
     * @param g : Graph g
     * @return
     */
    public static List<Edge> findEulerTour(Graph g) {
        int[] odd_vertex = new int[2];
        int odd_degree = 0;
        int i = 0, count = 0;
        //count the degree
        for (Vertex u : g) {
            for (Edge v : u.Adj) {
                u.degree++;
            }
        }
        for (Vertex u : g) {
            if (u.degree % 2 != 0) {
                if (i < 2) {
                    odd_vertex[i++] = u.name;
                }
                odd_degree++;
            }
        }

        List<Edge> tour = new LinkedList<>();
        if (odd_degree == 0) {
            Vertex start = null;
            for (Vertex u : g) {
                if (u.name == 1) {
                    start = u;
                    break;
                }
            }

            tour = eulerTour(start);
            verifyTour(g, tour, start);

        } else if (odd_degree == 2) {
            Vertex start = null;
            int min;
            if (odd_vertex[0] > odd_vertex[1]) {
                min = odd_vertex[1];
            } else {
                min = odd_vertex[0];
            }
            for (Vertex u : g) {
                if (u.name == min) {
                    start = u;
                    break;
                }
            }
            tour = eulerTour(start);
            verifyTour(g, tour, start);
        } else if (odd_degree > 2) {
            System.out.println("Graph is not eulerian.");
        }
        return tour;
    }

    /**
     * This method is used to check whether the generated Euler tour is correct.
     *
     * @param g : Graph g
     * @param path : Euler Circuit/Path
     * @param u : Start Vertex
     * @return
     */
    public static Boolean verifyTour(Graph g, List<Edge> path, Vertex u) {
        if (path.size() != g.Edges) {
            return false;
        }
        for (Edge e : path) {
            if (e.From == u || e.To == u) {
                u = e.otherEnd(u);
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * This method Method implements the Hierholzer's algorithm to find the
     * Edges in a path or circuit
     *
     * @param u : Start Vertex
     * @return
     */
    public static List<Edge> eulerTour(Vertex u) {
        Stack<Edge> Forward = new Stack<>();
        LinkedList<Edge> path = new LinkedList<>();
        Edge e = u.getUnvisitedEdge();
        while (e != null) {
            e.seen = true;
            Forward.add(e);
            u = e.otherEnd(u);
            e = u.getUnvisitedEdge();
        }
        while (!(Forward.isEmpty())) {
            e = Forward.pop();
            path.add(e);
            Edge e1 = e.From.getUnvisitedEdge();
            Vertex v = e.From;
            if (e1 == null) {
                e1 = e.To.getUnvisitedEdge();
                v = e.To;
            }
            e = e1;
            while (e != null) {
                e.seen = true;
                Forward.push(e);
                v = e.otherEnd(v);
                e = v.getUnvisitedEdge();
            }
        }

        return path;
    }

    /**
     * This method is used to check whether graph is connected or not
     *
     * @param g
     * @return
     */
    public static boolean isConnected(Graph g) {

        BFSUtil(g, g.verts.get(1));

        for (Vertex u : g) {
            // At least one of the node should be seen in the graph else it is 
            // return false
            if (!u.seen) {
                System.out.println("Graph is disconnected");
                return false;
            }
        }

        return true;
    }

    /**
     * This is utility method to run BFS on a given random source vertex
     *
     * @param g : Graph g
     * @param src : Source Vertex
     */
    public static void BFSUtil(Graph g, Vertex src) {

        Queue<Vertex> queue = new LinkedList<Vertex>();
        queue.add(src);
        src.seen = true;

        while (!queue.isEmpty()) {
            Vertex u = queue.remove();
            for (Edge e : u.Adj) {
                Vertex v = e.otherEnd(u);
                if (!v.seen) {
                    v.seen = true;
                    v.parent = u;
                    queue.add(v);
                }
            }
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in;
        List<Edge> tour = new LinkedList<>();
        File input = new File("lp0-big.txt");
        if (args.length > 0) {
            File inputFile = new File(args[0]);
            in = new Scanner(inputFile);
        } else {
            in = new Scanner(input);
        }
        Graph g = Graph.readGraph(in, false);// read undirected graph from stream "in"
        //Function to test the graph is Eulerian
        if (isConnected(g)) {
            long startTime = System.currentTimeMillis();
            tour = findEulerTour(g);
            long endTime = System.currentTimeMillis();
            long elapsedtime = endTime - startTime;
            
            for (int i = tour.size() - 1; i >= 0; i--) {
                System.out.println(tour.get(i));
            }
            System.out.println("Time taken to find the Euler Tour is " + elapsedtime + " ms");
            
        }
    }
}

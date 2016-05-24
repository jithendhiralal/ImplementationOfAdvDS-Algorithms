import java.io.*;
import java.util.*;

/**
 * SP2_c_Kosaraju algorithm implementation to print out the number of strongly
 * connected components
 *
 */
class SP2_c_Kosaraju {

    private int size;
    private LinkedList<Integer> Adj[]; //Adjacency List

    SP2_c_Kosaraju(int num) {
        size = num;
        Adj = new LinkedList[size];
        for (int i = 0; i < num; ++i) {
            Adj[i] = new LinkedList();
        }
    }

    //Function to add an edge into the graph
    void addEdge(int v, int w) {
        Adj[v].add(w);
    }

    // Function that returns reverse (or transpose) of this graph
    SP2_c_Kosaraju getreverse() {
        SP2_c_Kosaraju graph = new SP2_c_Kosaraju(size);
        for (int i = 0; i < size; i++) {
            Iterator<Integer> itr = Adj[i].listIterator();
            while (itr.hasNext()) {
                graph.Adj[itr.next()].add(i);
            }
        }
        return graph;
    }

    /**
     * @param vertex
     * @param visited recursive function to print DFS starting from v
     */
    void DFS(int vertex, boolean visited[]) {
        visited[vertex] = true;
        System.out.print(vertex + " ");

        int n;

        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i = Adj[vertex].iterator();
        while (i.hasNext()) {
            n = i.next();
            if (!visited[n]) {
                DFS(n, visited);
            }
        }
    }

    /**
     * @param vertex
     * @param visited
     * @param stack pushing the contents to the stack
     */
    void Entry(int vertex, boolean visited[], Stack stack) {
        // Mark the current node as visited and print it
        visited[vertex] = true;

        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i = Adj[vertex].iterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n]) {
                Entry(n, visited, stack);
            }
        }

        stack.push(new Integer(vertex));
    }

    /**
     * Function that finds and prints all strongly connected components
     * @param graph
     * @return
     */
    int stronglyConnectedComponents(SP2_c_Kosaraju graph) {
        Stack stack = new Stack();
        int count = 0;
        if (graph == null) {
            return 0;
        }
        // Mark all the vertices as not visited (For first DFS)
        boolean visited[] = new boolean[size];
        for (int i = 0; i < size; i++) {
            visited[i] = false;
        }

        // Fill vertices in stack 
        for (int i = 0; i < size; i++) {
            if (visited[i] == false) {
                Entry(i, visited, stack);
            }
        }

        // Create a reversed graph
        graph = getreverse();

        // Mark all the vertices as not visited (For second DFS)
        for (int i = 0; i < size; i++) {
            visited[i] = false;
        }

        // Now process all vertices in order defined by Stack
        while (stack.empty() == false) {
            // Pop a vertex from stack
            int v = (int) stack.pop();

            // Print Strongly connected component of the popped vertex
            if (visited[v] == false) {
                graph.DFS(v, visited);
                System.out.println();
                count++;
            }

        }
        return count;

    }

    // Driver method
    public static void main(String args[]) {
        // Creating graph
        SP2_c_Kosaraju g = new SP2_c_Kosaraju(10);
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(3, 4);
        g.addEdge(6, 7);
        g.addEdge(7, 8);
        g.addEdge(8, 9);
        g.addEdge(9, 6);
        g.addEdge(5, 1);
        System.out.println("Strongly connected components ");

        int count = g.stronglyConnectedComponents(g);
        System.out.println("Number of Strongly connectec components is " + count);
    }
}

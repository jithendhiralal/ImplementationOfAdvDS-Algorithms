package G95_LP2;

import java.util.TreeSet;

/**
 * @author Ramakrishnan Sathyavageeswaran
 * @author Thiagarajan ramakrishnan
 * @author Jithendhiralal Ramlal
 *
 */
public class Kruskal {

    /**
     * Find total weight of the Minimum Spanning Tree of a Graph using Kruskal's
     * Algorithm
     *
     * @param g : Graph
     * @return
     */
    public static int kruskal(Graph g) {
        TreeSet<Edge> list = new TreeSet<Edge>(new Edge());
        int wmst = 0;
        Vertex ru, rv;
        for (Vertex u : g) {
            makeSet(u);
        }
        for (Vertex u : g) {
            for (Edge e : u.Adj) {
                list.add(e);
            }
        }
        for (Edge e : list) {
            ru = Find(e.From);
            rv = Find(e.To);

            if (ru.name != rv.name) {
                wmst += e.Weight;

                Union(ru, rv);
            }
        }

        return wmst;
    }

    public static void makeSet(Vertex u) {
        u.parent = u;
        u.rank = 0;
    }

    public static Vertex Find(Vertex u) {
        if (u != u.parent) {
            u.parent = Find(u.parent);
        }
        return u.parent;
    }

    public static void Union(Vertex u, Vertex v) {
        if (u.rank > v.rank) {
            v.parent = u;
        } else if (v.rank > u.rank) {
            u.parent = v;
        } else {
            u.parent = v;
            v.rank++;
        }
    }
}

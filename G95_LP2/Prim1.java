package G95_LP2;

public class Prim1 {

    static final int Infinity = Integer.MAX_VALUE;

    /**
     * Find total weight of the Minimum Spanning Tree of a Graph using Binary
     * Heap
     *
     * @param g : Graph
     * @return
     */
    static int PrimMST(Graph g) {

        Vertex src = g.verts.get(1);

        // Code for Prim's algorithm needs to be written
        for (Vertex v : g) {
            v.seen = false;
            v.parent = null;
        }

        int wmst = 0;
        src.seen = true;

        //Using the PQ interface to create the BinaryHeap
        PQ<Edge> queue = new BinaryHeap<>(g.numNodes * 2, new Edge());

        for (Edge e : src.Adj) {
            queue.add(e);
        }
        //Loop through each edge
        while (!queue.isEmpty()) {
            Edge e = queue.remove();

            if (e.From.seen && e.To.seen) {
                continue;
            }

            Vertex v;
            if (!e.From.seen) {
                v = e.From;
            } else {
                v = e.To;
            }

            v.seen = true;
            wmst += e.Weight;
            for (Edge edge : v.Adj) {
                Vertex u = edge.otherEnd(v);
                if (!u.seen) {
                    queue.add(edge);
                }
            }
        }
        return wmst;
    }
}

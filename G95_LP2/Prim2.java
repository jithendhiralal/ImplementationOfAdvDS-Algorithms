package G95_LP2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Prim2 {

    static final int Infinity = Integer.MAX_VALUE;

    /**
     * Find total weight of the Minimum Spanning Tree of a Graph using Binary
     * Indexed Heap
     *
     * @param g : Graph
     * @return
     */
    static int PrimMSTUsingIndexedHeap(Graph g) {
        // create a vertices array.
        // set the distance of each vertex to infinity.

        Vertex src = g.verts.get(1);
        Vertex[] vertsArr = new Vertex[g.numNodes + 1];
        vertsArr[0] = null; // to avoid edge case condition

        int idx = 1;
        for (Vertex v : g) {
            if (v != null) {
                v.distance = Integer.MAX_VALUE;
                v.seen = false;
                v.parent = null;
                v.index = idx;
                vertsArr[idx++] = v;
            }
        }
        //set the distance of the source to zero.
        src.distance = 0;
        int wmst = 0;

        //Using the PQ interface to create the IndexedHeap
        PQ<Vertex> queue = new IndexedHeap<>(vertsArr, new Vertex(0));

        // loop through every vertex
        while (!queue.isEmpty()) {
            Vertex u = queue.remove();
            u.seen = true;
            wmst += u.distance;

            for (Edge e : u.Adj) {
                Vertex v = e.otherEnd(u);
                if (!v.seen && e.Weight < v.distance) {
                    v.distance = e.Weight;
                    v.parent = u;
                    queue.decreaseKey(v);
                }
            }

        }

        return wmst;
    }
}


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MST {

    static final int Infinity = Integer.MAX_VALUE;
    /**
     * Find total weight of the Minimum Spanning Tree of a Graph
     * using Binary Heap
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

    /**
     * Find total weight of the Minimum Spanning Tree of a Graph
     * using Binary Indexed Heap
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
        for(Vertex v : g.verts) {
            if(v != null) {
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
        PQ<Vertex> queue  = new IndexedHeap<>(vertsArr, new Vertex(0));
        
        // loop through every vertex
        while(!queue.isEmpty()){
            Vertex u = queue.remove();
            u.seen = true;
            wmst += u.distance;
            
            for(Edge e : u.Adj){
                Vertex v = e.otherEnd(u);
                if(!v.seen && e.Weight < v.distance){
                    v.distance = e.Weight;
                    v.parent = u;
                    queue.decreaseKey(v);
                }
            }
            
        }
        
        return wmst;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in;
        if (args.length > 0) {
            File inputFile = new File(args[0]);
            in = new Scanner(inputFile);
        } else {
            in = new Scanner(System.in);
        }
        
        Graph g = Graph.readGraph(in, false);
        System.out.println("PrimsMST Using Binary Heap");
        long startTime = System.currentTimeMillis();
        int wmst = PrimMST(g);
        long endTime = System.currentTimeMillis();
        System.out.println("Weight of the Minimum spanning tree " + wmst);
        long elapsedtime = endTime - startTime;
        System.out.println("Time taken " + elapsedtime + " ms");
        System.out.println("");
        System.out.println("PrimsMST Using Indexed BinaryHeap");
        long startTime2 = System.currentTimeMillis();
        wmst = PrimMSTUsingIndexedHeap(g);
        long endTime2 = System.currentTimeMillis();
        System.out.println("Weight of the Minimum spanning tree " + wmst);
        long elapsedtime2 = endTime2 - startTime2;
        System.out.println("Time taken " + elapsedtime2 + " ms");
        
    }
}

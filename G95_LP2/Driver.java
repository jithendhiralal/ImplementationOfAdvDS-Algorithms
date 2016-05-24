package G95_LP2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Ramakrishnan Sathyavageeswaran
 * @author Thiagarajan ramakrishnan
 * @author Jithendhiralal Ramlal
 */
public class Driver {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in;
        if (args.length > 0) {
            File inputFile = new File(args[0]);
            in = new Scanner(inputFile);
        } else {
            in = new Scanner(System.in);
        }

        Graph g = Graph.readGraph(in, false);
        Timer time = new Timer();
//        System.out.println("PrimsMST Using BinaryHeap");
//        time.start();
//        long wmst = Prim1.PrimMST(g);
//        System.out.println("Time taken by PrimsMST Using BinaryHeap is "+ time.end());
//        System.out.println("Weight of the Minimum spanning tree " + wmst);
//        System.out.println("PrimsMST Using Indexed BinaryHeap");
//        time.start();
//        wmst = Prim2.PrimMSTUsingIndexedHeap(g);
//        System.out.println("Time taken by PrimsMST Using Index BinaryHeap is "+ time.end());
//        System.out.println("Weight of the Minimum spanning tree " + wmst);
//        System.out.println("MST Using Kruskal");
        time.start();
        long wmst = Kruskal.kruskal(g);
        System.out.println("Time taken by Kruskal Algorithm is "+ time.end());
        System.out.println("Weight of the Minimum spanning tree " + wmst);

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author krish
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
        Graph g = Graph.readGraph(in, true);
        
        //Logic to calculate if weight of all edge equal
        
        //Logic to find 
        int totalSum =0;
        int n = 0;
        n = Shortestpath.CheckBFS(g);
        if(n == 0){
        	n = Shortestpath.CheckDAG(g);
        }
        if(n == 0){
        	n = Shortestpath.checkDijkstra(g);
        }
        switch (n) {
            case 1:
                Shortestpath.BFSShortestPath(g);
                for (Vertex v : g) {
                    if (!v.infinity) {
                        totalSum += v.distance;
                    }
                }
                System.out.println("BFS " + totalSum);
                Shortestpath.printPath(g);
                break;
            case 2:
                Shortestpath.DAGShortestPath(g);
                for (Vertex v : g) {
                    if (!v.infinity) {
                        totalSum += v.distance;
                    }
                }
                System.out.println("DAG " + totalSum);
                Shortestpath.printPath(g);
                break;
            case 3:
                Shortestpath.DijkstraShortestPath(g);
                for (Vertex v : g) {
                    if (!v.infinity) {
                        totalSum += v.distance;
                    }
                }
                System.out.println("Dij " + totalSum);
                Shortestpath.printPath(g);
                break;
            default:
                Shortestpath.BellmanFord(g);
                for (Vertex v : g) {
                    if (!v.infinity) {
                        totalSum += v.distance;
                    }
                }
                if(!g.cycle){
                System.out.println("B-F " + totalSum);
                Shortestpath.printPath(g);
            	}
                break;
        }
    }
}

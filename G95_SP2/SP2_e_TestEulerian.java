import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SP2_e_TestEulerian {
	/**
	 * 
	 * @param u - Vertex u
	 */
	public static void checkConnected(Vertex u){
		u.seen = true;
		for(Edge e : u.Adj){
			Vertex v = e.otherEnd(u);
			if(!v.seen){
				v.parent = u;
				checkConnected(v);
			}
		}
	}
	/**
	 * 
	 * @param g - Graph g
	 * @param odd_vertex - Integer array to display the odd vertices, if there is exactly two odd vertices
	 * @param odd_degree - Variable to count the odd degree vertices
	 */
	public static void testEuler(Graph g, int[] odd_vertex, int odd_degree){
		int i =0,count = 0;
		//count the outdegree
		for(Vertex u : g){
			for(Edge v: u.Adj){
				u.outdegree++;
			}
		}
		//count the indegree
		for(Vertex u : g){
			for(Edge v: u.revAdj){
				u.indegree++;
			}
		}
		// count the degree
		for(Vertex u : g){
			u.degree = u.indegree + u.outdegree;
		}
		// count the vertices with odd degree
		for(Vertex u : g){
			if(u.degree % 2 != 0){
				if(i < 2){
					odd_vertex[i++] = u.name;
				}
				odd_degree++;
			}
	    }
		for (Vertex u : g) {
		    u.seen = false;
		    u.parent = null;
		    u.distance = Integer.MAX_VALUE;
		}
		//Check if the graph is connected
		for(Vertex u : g){
			if(!u.seen){
				count++;
				checkConnected(u);
			}
		}
		// Based on the odd_degree and count, displaying the corresponding results
		if(count > 1){
			System.out.println("Graph is not connected");
		}
		else if(odd_degree == 0){
			System.out.println("Graph is Eulerian");
		}
		else if(odd_degree == 2){
			
			System.out.println("Graph has an Eulerian path between vertices "+odd_vertex[0]+" and "+odd_vertex[1]);
		}
		else if(odd_degree > 2){
			System.out.println("Graph is not eulerian. It has "+odd_degree+" vertices odd degree");
		}
	}
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in;
		int[] odd_vertex = new int[2];
		int odd_degree = 0;
		if (args.length > 0) {
		    File inputFile = new File(args[0]);
		    in = new Scanner(inputFile);
		} else {
		    in = new Scanner(System.in);
		}
		Graph g = Graph.readGraph(in, true);   // read undirected graph from stream "in"
		//Function to test the graph is Eulerian
		testEuler(g, odd_vertex, odd_degree);
	}
}

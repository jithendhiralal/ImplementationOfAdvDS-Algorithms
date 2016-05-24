import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.UUID;
/**
 * 
 * Class that represents Topological sort for directed Acyclic Graph
 *
 */
public class SP2_a_ToplogicalSort {
	/**
	 * 
	 * @param g - Graph g
	 * @return
	 */
	public static List<Vertex> topologicalOrder1(Graph g){
		List<Vertex> order = new ArrayList<>();
		Queue<Vertex> Q = new LinkedList<>();
		//Set all the vertices indegree
		for(Vertex u : g){
			for(Edge v: u.revAdj){
				u.indegree++;
			}
		}
		for (Vertex u : g) {
		    u.seen = false;
		    u.parent = null;
		    u.distance = Integer.MAX_VALUE;
		}
		
		for(Vertex u : g){
			if(u.indegree == 0)
				Q.add(u);
		}
		//Add the vertices with no incoming edges to queue until it gets empty
		int top = 1;
		while(!Q.isEmpty()){
			Vertex u = Q.remove();
			u.top = top++;
			order.add(u);
			for(Edge e : u.Adj){
				Vertex v = e.otherEnd(u);
				v.indegree--;
				if(v.indegree == 0){
					Q.add(v);
				}
			}
		}
		// to detect cycle in a graph
		for(Vertex u : g){
			if(u.indegree != 0){
				System.out.println("Graph has cycles");
				break;
			}
		}
		return order;
	}
	/**
	 * 
	 * @param g - Graph g
	 * @return
	 */
	public static Stack<Vertex> topologicalOrder2(Graph g){
		Stack<Vertex> s = new Stack<>();
		Boolean isacyclic = true;
		for(Vertex u : g){
			if(!u.seen){
				//Call DFS to add the vertices to stack and return the stack and also checking whether graph has cycle
				isacyclic = DFS(u,s);
				if(!isacyclic){
					System.out.println("Graph has cycles");
				}
			}
		}
		return s;
	}
	/**
	 * 
	 * @param u - Vertex u
	 * @param s - Stack s
	 * @return
	 */
	public static Boolean DFS(Vertex u, Stack<Vertex> s){
		u.seen = true;
		u.status = 1;
		for(Edge e: u.Adj){
			Vertex v = e.otherEnd(u);
			if(v.status == 1){
				return false;
			}
			if(!v.seen){
				v.parent = u;
				if(DFS(v, s) == false)
					return false;
			}
		}
		u.status = 2;
		s.push(u);
		return true;
	}
	/**
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in;
		List<Vertex> result = new ArrayList<>();
		Stack<Vertex> result1 = new Stack<>();
		
		if (args.length > 0) {
		    File inputFile = new File(args[0]);
		    in = new Scanner(inputFile);
		} else {
		    in = new Scanner(System.in);
		}
		Graph g = Graph.readGraph(in, true);   // read undirected graph from stream "in"
		// Function for topological order by removing vertices with no incoming edges
		result = topologicalOrder1(g);
		System.out.println("Toplological Order: Algorithm 1");
		for(Vertex v : result){
			System.out.println(v);
		}
		for (Vertex u : g) {
		    u.seen = false;
		    u.parent = null;
		    u.distance = Integer.MAX_VALUE;
		}
		//Function for Topological order by DFS and stack method
		System.out.println("Toplological Order: Algorithm 2");
		result1 = topologicalOrder2(g);
		while(!result1.isEmpty()){
			System.out.println(result1.pop());
		}
	    }

}

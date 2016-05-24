


/**
 *
 * @author Ramakrishnan Sathyavageeswaran
 * @author Thiagarajan ramakrishnan
 * @author Jithendhiralal Ramlal
 *
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;


public class TreeSetExample {

	TreeSet<Item> add(List<Item> treelist)
	{
		TreeSet<Item> treeset = new TreeSet(treelist);
		return treeset;
	}
	
	static void remove(TreeSet<Item> treesets){
		treesets.clear();
		
	}
	
	static Item contains(TreeSet<Item> treesets, Item key){
		Iterator itr = treesets.iterator();
		if(treesets.contains(key))
			return key;
		else
			return null;
	}
	
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter input size");
		int num = scan.nextInt();
		TreeSetExample treeset = new TreeSetExample();
		Random rand = new Random();
		List<Item> treelist = new ArrayList<Item>();
		//Generating random input numbers
		TreeSet<Item> treesets = new TreeSet<Item>();
		for(int i=0;i<num;i++)
			treelist.add(new Item<>(rand.nextInt(1000)));
		long start = System.nanoTime();
		treesets = treeset.add(treelist);
		long end = System.nanoTime();
		Iterator <Item> additr ;
		additr = treesets.iterator();
		System.out.println("Inserting values in treeset");
		while(additr.hasNext())
			System.out.println(additr.next());
		
		Iterator<Item> removeitr;
		removeitr = treesets.iterator();
		System.out.println("Deleting values in treeset");
		remove(treesets);
		
		
		
	}
}

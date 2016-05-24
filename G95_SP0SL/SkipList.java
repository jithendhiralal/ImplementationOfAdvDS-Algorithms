

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
/**
*
* @author Ramakrishnan Sathyavageeswaran
* @author Thiagarajan ramakrishnan
* @author Jithendhiralal Ramlal
*
*/

public class SkipList<T extends Comparable<? super T>> implements Iterable<T>{

	public class ListEntry{
		T element;
		ListEntry next[];
		ListEntry(T x , int level){
			element = x;
			next = new SkipList.ListEntry[level + 1];
		}
		
		public int level(){
			return next.length - 1;
		}
		
	}
	
	private ListEntry head;
	public class SkipEntry implements Iterator<T>{
		private ListEntry iteratorNode;
		
		public SkipEntry(){
			iteratorNode = head;
		}
		
		public boolean hasNext(){
			return iteratorNode.next[0]!=null;
		}
		
		public T next(){
			iteratorNode = iteratorNode.next[0];
			return iteratorNode.element;
		}
		
		public void remove(){
			throw new UnsupportedOperationException();
		}
	}
	
	int size;
	static int maxlevel = 5;
	
	SkipList(){
		head = new ListEntry(null, 0);
		size = 0;
	}
	
	//Helper function to find data
	public T find(T data){
		if(data == null){
			return null;
		}
		ListEntry cur = head;
		
		for(int level = cur.level(); level >=0 ; level--){
			while(cur.next[level]!=null && data.compareTo(cur.next[level].element) >= 0){
				cur = cur.next[level];
			}
			if(data.equals(cur.element))
				return cur.element;
		}
		return null;
	}
	
	// Is x in the list?
	public boolean contains(T x){
		if(x==null){
			return false;
		}
		return find(x)!=null;
	}
	
	
	// Is the list empty?
	public boolean isEmpty(){
		if(head.next[0]==null){
			return false;
		}
		return true;
	}
	
	// Number of elements in the list
	public int size(){
		return size;
	}
	
	 // Returns an iterator for the list
	public Iterator<T> iterator(){
		return new SkipEntry();
	}
	
	
	// Add an element x to the list.  Returns true if x was a new element.
	public boolean add(T x){
		if(x==null){
			return false;
		}
		
		ListEntry cur = head;
		int maxlevel = head.level();
		
		ListEntry [] prev = new SkipList.ListEntry[maxlevel + 1];
		
		// Implementing the choice function to get the level where the data has to be inserted
		for(int level = maxlevel; level >=0 ; level--){
			while(cur.next[level]!=null && x.compareTo(cur.next[level].element)>0 ){
				cur = cur.next[level];
			}
			prev[level] = cur;
		}
		
		cur = cur.next[0];
		
		if(cur!=null && x.equals(cur.element))
			return false;
		else{
			int node_level = choice();
			if(node_level > maxlevel){
				head.next = Arrays.copyOf(head.next, node_level + 1);
				prev = Arrays.copyOf(prev, node_level + 1);
				
				for(int level = maxlevel+1; level<=node_level; level++){
					prev[level] = head;
				}
				
				maxlevel = node_level;
			}
			
			cur = new ListEntry(x , node_level);
			
			for(int i=0; i <= node_level ; i++){
				cur.next[i] = prev[i].next[i]; 
				
				prev[i].next[i] = cur;
			}
			size++;
			return true;
		}
		
	}
	
	//helper function
	private int choice(){
		int level;
		
		Random rand = new Random();
		for(level = 0;rand.nextInt()%2==0;level++);
		return level;
	}
	
	
	// Remove x from list; returns false if x was not in list
	public boolean remove(T x){
		if(x==null)
			return false;
		int maxlevel = head.level();
		
		ListEntry cur = head;
		
		ListEntry [] prev = new SkipList.ListEntry[maxlevel + 1];
		
		for(int level = maxlevel; level>=0 ; level--){
			while((cur.next[level]!=null) && x.compareTo(cur.next[level].element) > 0){
				cur = cur.next[level];
			}
			prev[level] = cur;
		}
		
		cur = cur.next[0];
		
		if(cur!=null && x.equals(cur.element)){
			int node_level = cur.level();
			
			for(int level = 0;level <= node_level;level++){
				if(prev[level].next[level]!=cur){
					break;
				}
				prev[level].next[level] = cur.next[level];
			}
			
			if(node_level == maxlevel){
				while(maxlevel>0 && head.next[maxlevel]==null){
					maxlevel--;
				}
				
				head.next = Arrays.copyOf(head.next, maxlevel+1);
			}
			size--;
			return true;
		}
		else{
			return false;
		}
	}
	
	 // Return the first element of the list
	public T first(){
		return (head.next[0]==null)?null:head.next[0].element;
	}
	
	// Return the last element of the list
	 public T last() {  
		if(head.next[0]==null) 
			return null;
		
		ListEntry cur = head;
		int level=head.level();
		while(cur.next[level]!=null && level>=0){
			cur=cur.next[level];
			level--;
			
		}
		return cur.element;
	 }
	 
	 
	// Return the element at a given position (index) in the list
	 public T findIndex(int index){
		 if(index <0 || index >size){
			 throw new IllegalArgumentException("Index must be within 0 and size");
		 }
		 
		 ListEntry cur = head.next[0];
		 for(int p=0;cur!=null && p<index; p++){
			 cur = cur.next[0];
		 }
		 return (cur==null)?null: cur.element;
	 }
	
	 
	public static void main(String args[]){
		Scanner sc = null;

		if (args.length > 0) {
			File file = new File(args[0]);
			try {
				sc = new Scanner(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			sc = new Scanner(System.in);
		}
		
		
	}
}

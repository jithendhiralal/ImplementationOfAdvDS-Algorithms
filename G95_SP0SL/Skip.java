

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collection;
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
public class Skip<E extends Comparable<? super E>> implements Iterable<E> {

    
    private class Node {

      
        private E element;
        private Node[] next;
       
        public Node(E element, int level) {
        	this.element = element;
            next = new Skip.Node[level + 1];
        }

           
            public int level() {
            	 return next.length - 1;
        }
    }

   
    private class ListIterator implements Iterator<E> {
        private Node iterNode;
        public ListIterator() {
            iterNode = head;
        }
        public boolean hasNext() {
            return iterNode.next[0] != null;
        }
        
        public E next() {
            iterNode = iterNode.next[0];
            return iterNode.element;
        }

      
        public void remove() {

            throw new UnsupportedOperationException();
        }
    }

    private Node head;
    private int size;
    public Skip() {
        head = new Node(null, 0);
        size = 0;
    }

  
    /**
     * This method is used to add a node with the specified value for the element field to the skip list
     * @param element adds element of any datatype to the list
     * @return returns true if the element is successfully added to the list
     */
    public boolean add(E element) {
        if (element == null) {
            return false;
        }
        int maxLevel = head.level();
        Node current = head;
      
        Node[] prev = new Skip.Node[maxLevel + 1];

        //skip list is a sorted linkedlist with levels
        //spotting where exactly the new node has to be inserted while tracking the prev elements at each level
        for (int level = maxLevel; level >= 0; level--) {
            while (current.next[level] != null
                    && element.compareTo(current.next[level].element) > 0) {
                current = current.next[level];
            }

            prev[level] = current;
        }

        current = current.next[0];

        if (current != null && element.equals(current.element)) {
            return false;
        } else {
            int nodeLevel = randomLevel();
//if level of the node is greater than max level, then increase max level upto level of the node
//increase node path from max level to level of the node. set the path as head in the path from head to level of the node
            if (nodeLevel > maxLevel) {
                head.next = Arrays.copyOf(head.next, nodeLevel + 1);

                prev = Arrays.copyOf(prev, nodeLevel + 1);

                for (int level = maxLevel + 1; level <= nodeLevel; level++) {
                    prev[level] = head;
                }

                maxLevel = nodeLevel;
            }

            //now current is the new node to be inserted
            current = new Node(element, nodeLevel);

            //inserting the node
            for (int level = 0; level <= nodeLevel; level++) {
                current.next[level] = prev[level].next[level];

                prev[level].next[level] = current;
            }

            size++;

            return true;
        }
    }

   
    /**
     * This method is used to determine the level that a new node to be inserted should have
     * @return returns the level 
     */
    private int randomLevel() {

        int level;

        Random rand = new Random();

        for (level = 0; rand.nextInt() % 2 == 0; level++)
            ;

        return level;
    }

    /**
     * This method is used to find if the given element is present in the Skip
     * @param element -it is the element to be searched in the Skip
     * @return returns if the element is contained in the Skip
     */
    public boolean contains(E element) {

        if (element == null) {
            return false;
        }

        return find(element) != null;
    }

    /**
     * This method is used to find and re
     * @param element
     * @return -returns the element searched for
     */
    public E find(E element) {

        if (element == null) {
            return null;
        }

        Node current = head;

        for (int level = head.level(); level >= 0; level--) {
            while (current.next[level] != null
                    && element.compareTo(current.next[level].element) >= 0) {
                current = current.next[level];
            }

            if (element.equals(current.element)) {
                return current.element;
            }
        }

        return null;
    }

    
   
    /**
     * This method is used to find the element which is less than or equal to the current element 
     * @param element
     * @return -returns the element which is less than or equal to the current element
     */
    public E floor(E element) {

        if (element == null) {
            return null;
        }

        Node current = head;

        for (int level = head.level(); level >= 0; level--) {
            while (current.next[level] != null
                    && element.compareTo(current.next[level].element) >= 0) {
                current = current.next[level];
            }

            if (element.equals(current.element)) {
                return current.element;
            }
            System.out.println("current.lement"+current.element);
             
        }
        if((current.element!=null)&&(element.compareTo(current.element))>0) return current.element;
        return null;
    }
    
    //Ceiling 
    
    /**
     * This method is used to find the element which is greater than or equal to the current element 
     * @param element
     * @return -returns the element which is less than or equal to the current element
     */
    public E ceiling(E element) {

        if (element == null) {
            return null;
        }

        Node current = head;
        Node temp=head;
        int templevel=head.level();
        for (int level = head.level(); level >= 0; level--) {
        	templevel=level;
            while (current.next[level] != null
                    && element.compareTo(current.next[level].element) >= 0) {
                current = current.next[level];
                
            }

            if (element.equals(current.element)) {
                return current.element;
            }
            
            
             
        }
        System.out.println("current"+current.element+templevel);
        if(current==null)return null;
        else{
        	temp=current.next[templevel];
        	
        	if(temp==null) return null;
        	if((temp!=null)&&element.compareTo(temp.element)<0) return temp.element;
        	else{
        		temp=current.next[templevel].next[templevel];
        		 System.out.println("temp2"+temp.element);
        		if((temp!=null)&&element.compareTo(temp.element)<0) return temp.element;
            	
        	}
            return null; 
        }
        
    }
    
    /**
     * This method returns the index of the element searched for
     * @param element
     * @return -returns the index of the element searched for
     */
    public long indexOf(E element) {

        if (element == null) {
            return -1;
        }

        Node current = head.next[0];

        int pos;

        for (pos = -1; current != null && !element.equals(current.element); pos++) {
            current = current.next[0];
        }

        return (current == null) ? -1 : pos + 1;
    }
   
    /**
     * This method finds the element in the given index
     * @param index
     * @return- returns the element in the given index
     */
    public E get(int index) {

        if (index < 0 || index > size) {
            throw new IllegalArgumentException(
                    "Index must be between zero and the list size.");
        }

        Node current = head.next[0];

        for (int pos = 0; current != null && pos < index; pos++) {
            current = current.next[0];
        }

        return (current == null) ? null : current.element;
    }

    /**
     * This method is used to find the first element in the list
     * @return -returns the first element in the list 
     */
    public E first() {

        return (head.next[0] == null) ? null : head.next[0].element;
    }

    /**
     * This method returns the last element in the list
     * @return-returns the last element in the list
     */
    public E last() {

        if (head.next[0] == null) {
            return null;
        }

        Node current = head;

        for (int level = head.level(); level >= 0; level--) {//this for loop may not be necessary
            while (current.next[level] != null) {//instead use while current.next[0]!=null
                current = current.next[level];
            }
        }

        return current.element;
    }

    /**
     * This method is used to remove the element from the skip list
     * @param element
     * @return -returns true if the element is removed
     */
    public boolean remove(E element) {

        if (element == null) {
            return false;
        }

        int maxLevel = head.level();

        Node current = head;

     
        Node[] nodePath = new Skip.Node[maxLevel + 1];

        for (int level = maxLevel; level >= 0; level--) {
            while (current.next[level] != null
                    && element.compareTo(current.next[level].element) > 0) {
                current = current.next[level];
            }

            nodePath[level] = current;
        }

        current = current.next[0];

        if (current != null && element.equals(current.element)) {
            int nodeLevel = current.level();

            //making prev nodes bypass the node to be deleted
            for (int level = 0; level <= nodeLevel; level++) {
                if (nodePath[level].next[level] != current) {
                    break;
                }

                nodePath[level].next[level] = current.next[level];
            }
            
          // if the deleted node had the max level, then we reduce max level
            if (nodeLevel == maxLevel) {
                while (maxLevel > 0 && head.next[maxLevel] == null) {
                    maxLevel--;
                }

                head.next = Arrays.copyOf(head.next, maxLevel + 1);
            }

            size--;

            return true;
        } else {
            return false;
        }
    }

    
 
    /**
     * This method returns 1 if the skip list is empty
     * @return returns 1 if the skip list is empty
     */
    public int isEmpty() {

         if(head.next[0] == null) return 0;
         return 1;
    }
    
    

 
    /**
     * This method returns the size of the list
     * @return returns the size of the list
     */
    public int size() {
         
        return size;
    }

    public Iterator<E> iterator() {

        return new ListIterator();
    }


    public Comparable[] toArray() {

        Comparable[] result = new Comparable[size];

        Node current = head.next[0];

        for (int pos = 0; pos < size; pos++) {
            result[pos] = current.element;

            current = current.next[0];
        }

        return result;
    }


    public String toString() {

        StringBuilder result = new StringBuilder();

        Node current = head.next[0];

        while (current != null) {
            result.append(current.element + "\n");

            current = current.next[0];
        }

        return result.toString();
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
		String operation = "";
		long operand = 0;
		int modValue = 997;
		long result = 0;
		Long returnValue = null;
		Skip<Long> Skip = new Skip<Long>();
		// Initialize the timer
		long startTime = System.currentTimeMillis();

		while (!((operation = sc.next()).equals("End"))) {
			switch (operation) {
			case "Add": {
				operand = sc.nextLong();
				Skip.add(operand);
				result = (result + 1) % modValue;
				break;
			}
			case "Ceiling": {
				operand = sc.nextLong();
				returnValue = Skip.ceiling(operand);
				System.out.println("ceiling"+returnValue);
				if (returnValue != null) {
					result = (result + returnValue) % modValue;
				}
				break;
			}
			case "FindIndex": {
				operand = sc.nextLong();
				returnValue = Skip.indexOf(operand);
				System.out.println("findindex"+returnValue);
				
				if (returnValue != null) {
					result = (result + returnValue) % modValue;
				}
				break;
			}
			case "First": {
				returnValue = Skip.first();
				System.out.println("first"+returnValue);
				if (returnValue != null) {
					result = (result + returnValue) % modValue;
				}
				break;
			}
			case "Last": {
				returnValue = Skip.last();
				System.out.println("last"+returnValue);
				if (returnValue != null) {
					result = (result + returnValue) % modValue;
				}
				break;
			}
			case "Floor": {
				operand = sc.nextLong();
				returnValue = Skip.floor(operand);
				System.out.println("floor"+returnValue);
				if (returnValue != null) {
					result = (result + returnValue) % modValue;
				}
				break;
			}
			
			case "Contains": {
				operand = sc.nextLong();
				if (Skip.contains(operand)) {
					result = (result + 1) % modValue;
				}
				break;
			}
			case "Remove": {
				operand = sc.nextLong();
				if (Skip.remove(operand)) {
					result = (result + 1) % modValue;
				}
			
				break;
			}

			}
		}

		// End Time
		long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;

		System.out.println(result + " " + elapsedTime);

	}
   }


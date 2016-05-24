import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Ramakrishnan Sathyavageeswaran
 * @author Thiagarajan ramakrishnan
 * @author Jithendhiralal Ramlal
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<? super T>> {

    

    Entry<T> root;
    int size;
    boolean rotateFlag;

    public BinarySearchTree() {
        root = null;
        size = 0;
        rotateFlag = true;
    }
    
    /**
     * SP4 b Balanced binary search tree from a sorted array
     * Constructor to build the array to BST and store it in our private root
     * Entry
     * @param array : Sorted array
     */
    public BinarySearchTree(T[] array) {

        buildBST(array);
        size = array.length;
    }

    private void buildBST(T[] array) {
        root = buildBSTUtil(array, 0, array.length - 1, null);
    }

    private Entry<T> buildBSTUtil(T[] array, int start, int end, Entry<T> parent) {

        if (start > end) {
            return null;
        }
        int mid = start + (end - start) / 2;
        //Recursively store the mid value to the root
        Entry<T> root = new Entry<>(array[mid], null, null, parent);
        root.setLeft(buildBSTUtil(array, start, mid - 1, root));
        root.setRight(buildBSTUtil(array, mid + 1, end, root));
        return root;

    }

    public Entry<T> find(Entry<T> node, T data) {

        Entry<T> temp = node;

        while (node != null) {
            temp = node;

            int cmp = data.compareTo(temp.getElement());

            if (cmp == 0) {
                return temp;
            } else if (cmp < 0) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }

        return temp;
    }

    public boolean contains(T data) {
        Entry<T> node = find(root, data);
        return node == null ? false : data.equals(node.getElement());
    }

    public boolean add(T data) {
        if (size == 0) {
            root = new Entry<>(data, null, null, null);
            
        } else {
            Entry<T> node = find(root, data);
            int cmp = data.compareTo(node.getElement());
            if (cmp == 0) {
                node.setElement(data);
                return false;
            }
            Entry<T> newNode = new Entry<>(data, null, null, node);
            
            if (cmp < 0) {
                node.setLeft(newNode);
            } else {
                node.setRight(newNode);
            }
        }
        size++;
        return true;
    }

    public T remove(T data) {
        T value = null;

        if (size > 0) {
            Entry<T> node = find(root, data);
            if (data.equals(node.getElement())) {
                value = node.getElement();
                remove(node);
                size--;
            }
        }
        return value;
    }

    public void remove(Entry<T> node) {
        if (node.isLeaf()) {
            node = null;
        } else if (node.getLeft() != null && node.getRight() != null) {
            removeTwo(node);
        } else {
            removeOne(node);
        }
    }

    // Called when node has at most one child.  Returns that child.
    public Entry<T> oneChild(Entry<T> node) {
        return node.getLeft() == null ? node.getRight() : node.getLeft();
    }

    // remove node that has at most one child
    void removeOne(Entry<T> node) {
        if (node == root) {
            Entry<T> nc = oneChild(root);
            root = nc;
            if (nc != null) {
                nc.setParent(root);
            }
            root.setParent(null);
        } else {
            Entry<T> p = node.getParent();
            Entry<T> nc = oneChild(node);
            if (p.getLeft() == node) {
                p.setLeft(nc);
            } else {
                p.setRight(nc);
            }
            if (nc != null) {
                nc.setParent(p);
            }
        }
    }
    
    // SP4 c Modify the remove in BST class
    // remove node that has two children
    void removeTwo(Entry<T> node) {
        if(rotateFlag){
          Entry<T> minRight = getMin(node.getRight());
          node.setElement(minRight.getElement());
          removeOne(minRight);
          rotateFlag = false;
        }
        else{
          Entry<T> maxLeft = getMax(node.getLeft());
          node.setElement(maxLeft.getElement());
          removeOne(maxLeft); 
          rotateFlag = true;
        }
        
    }

    // Find minValue of the BST
    public Entry<T> getMin() {
        return getMin(root);
    }

    // Find max Value of the BST
    public Entry<T> getMax() {
        return getMax(root);
    }

    // Find minValue of the BST from the given node
    public Entry<T> getMin(Entry<T> root) {
        Entry<T> temp = root;
        if (temp == null) {
            System.out.println("Tree is empty");
        } else if (temp.getLeft() == null) {
            return temp;
        } else {
            while (temp.getLeft() != null) {
                temp = temp.getLeft();
            }
        }

        return temp;
    }

    // Find max Value of the BST from the given node
    public Entry<T> getMax(Entry<T> root) {
        Entry<T> temp = root;
        if (temp == null) {
            System.out.println("Tree is empty");
        } else if (temp.getRight() == null) {
            return temp;
        } else {
            while (temp.getRight() != null) {
                temp = temp.getRight();
            }
        }

        return temp;
    }

    // Create an array with the elements using in-order traversal of tree
    public Comparable[] toArray() {
        Comparable[] arr = new Comparable[size];
        inOrder(root, arr, 0);
        return arr;
    }

    // Recursive in-order traversal of tree rooted at "node".
    // "index" is next element of array to be written.
    // Returns index of next entry of arr to be written.
    int inOrder(Entry<T> node, Comparable[] arr, int index) {
        if (node != null) {
            index = inOrder(node.getLeft(), arr, index);
            arr[index++] = node.getElement();
            index = inOrder(node.getRight(), arr, index);
        }
        return index;
    }

    public void printTree() {
        System.out.print("[" + size + "]");
        printTree(root);
        System.out.println();
    }

    // Inorder traversal of tree
    void printTree(Entry<T> node) {
        if (node != null) {
            printTree(node.getLeft());
            System.out.print(" " + node.getElement());
            printTree(node.getRight());
        }
    }
    
    
    /**
     * SP4 a 
     * @return Level OrderArray with elements in each level from level 0
     */
    public Comparable[] levelOrderArray() {

        Comparable[] result = new Comparable[size];
        if (root == null) {
            System.out.println("Tree is empty");
            return result;
        }
        int position = 0;

        Queue<Entry> queue = new LinkedList<>();

        queue.add(root);
        // Until Queue is empty
        while (!queue.isEmpty()) {
            Entry current = queue.peek();
            result[position++] = (Comparable) current.getElement();
            
            if (current.getLeft() != null) {
                queue.add(current.getLeft());
            }
            if (current.getRight() != null) {
                queue.add(current.getRight());
            }
            queue.remove();
        }
        return result;
    }
    
    
    /**
     * SP4 f AVL Tree Verification
     * @return 
     */
    public  boolean verifyAVLTree(){
        return verifyAVLTree(root);
        
    }
    private boolean verifyAVLTree(Entry<T> root) {
        if(root == null)
            return true;
        int leftHeight, rightHeight;
        //Check for BST property
        if(root.getLeft() != null && 
                getMax(root.getLeft()).getElement().compareTo(root.getLeft().getElement()) > 0 )
            return false;
        if(root.getRight() != null && 
                getMin(root.getRight()).getElement().compareTo(root.getRight().getElement()) < 0 )
            return false;
        
        if(!verifyAVLTree(root.getRight()) || !verifyAVLTree(root.getLeft()))
            return false;
        if(root.getLeft() != null)
            leftHeight = root.getLeft().getHeight();
        else
            leftHeight = 0;
        if(root.getRight() != null)
            rightHeight = root.getRight().getHeight();
        else
            rightHeight = 0;
        // Check for AVL tree Height
        if(Math.abs( leftHeight - rightHeight) > 1)
            return false;
        
        return true;
    }
    

}

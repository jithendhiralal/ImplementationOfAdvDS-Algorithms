/*** @author Ramakrishnan Sathyavageeswaran
 * @author Thiagarajan ramakrishnan
 * @author Jithendhiralal Ramlal ***/
/** Program to illustrate effect of calling height/depth recursively from each node of a tree
 *  Sample runs:
 *  > java Tree  (uses default depth of 100)
 *  Answer: 4613 Time: 10 msec.
 *
 *  > java Tree 1000
 *  Answer: 24745 Time: 34 msec.
 *
 *  > java Tree 10000
 *  ## Stack overflow.  Need to ask for a larger stack for program to execute.
 *
 *  > java -Xss256m Tree 10000
 *  Answer: 13783 Time: 2382 msec.
 *
 *  > java -Xss256m Tree 100000
 *  Answer: 8811 Time: 293937 msec.
 *
 *  Challenge: rewrite program so that it runs quickly (0.3s for 100K, k for 1M)
 */


import java.lang.Math;
import java.util.Queue;
import java.util.LinkedList;

public class SP4_d_Tree {
    TreeNode root;
    static final long p = 999959;
    SP4_d_Tree() { root = new TreeNode(0); }

    /** binary tree node */
    public class TreeNode {
	long data;
	TreeNode left, right, parent;
	long depth, height;

	TreeNode(long x) { data = x;  left = null;  right = null;  parent = null; depth = 0L;height = 0L;}

	/** create a new node that is attached to par node as left child if goLeft is true;
	 *  otherwise, the new node is attached to par as right child
	 */
	TreeNode(long x, TreeNode par, boolean goLeft) {
	    data = x;  left = null;  right = null;  parent = par;
	    this.depth = parent.depth + 1L;
	    this.height = 0L;
	    updateheight(this.parent);
	    if (goLeft) { par.left = this; } 
	    else { par.right = this; }
	}
	void updateheight(TreeNode node){
		while(node != null){
			node.height = height(node);
			node = node.parent;
		}
	}
	long height(TreeNode t){
            long lHeight, rHeight;
            if(t.right != null)
                rHeight = t.right.height;
            else
                rHeight = -1;
            if(t.left != null)
                lHeight = t.left.height;
            else
                lHeight = -1;
	return 1L + Math.max(lHeight, rHeight); 
	}
	
    } // end of TreeNode class


    // If there is a command line argument, it is used as the depth of the tree generated
    public static void main(String[] args) {
	long depth = 100000;
	if(args.length > 0) depth = Long.parseLong(args[0]);
	SP4_d_Tree x = new SP4_d_Tree();

	// Create a tree composed of 2 long paths 
	TreeNode last = x.root;
	for(long i=1; i<=depth; i++) { last = x.new TreeNode(i, last, true); }

	last = x.root;
	for(long i=1; i<=depth; i++) { last = x.new TreeNode(depth+i, last, false); }
	    
	Timer timer = new Timer();
	// The tree is visited in level order, using a Queue.  Depth and height of each node is computed recursively
	long minSum = Long.MAX_VALUE;
	Queue<TreeNode> Q = new LinkedList<>();
	Q.add(x.root);
	while(!Q.isEmpty()) {
	    TreeNode cur = Q.remove();
	    if(cur != null) {
		minSum = Math.min(minSum, someFunction(cur));
		Q.add(cur.left);  Q.add(cur.right);
	    }
	}
	System.out.println("Answer: " + minSum + " " + timer.end());
    }

    static long someFunction(TreeNode cur) {
	return rotater(rotater((cur.depth)*(cur.depth))%p + rotater((cur.height)*(cur.height))%p);
    }

   static long rotater(long h) {
	h ^= (h >>> 20) ^ (h >>> 12);
	h = (h ^ (h >>> 7) ^ (h >>> 4));
	return h;
    }

   // commented this recursive calculation of height and depth
   
    // find the depth of a node 
//    static long depth(TreeNode t) { return t.parent == null ? 0 : 1 + depth(t.parent); }

    // height of a node
  //  static long height(TreeNode t) { return t == null ? -1 : 1 + Math.max(height(t.left), height(t.right)); }

}